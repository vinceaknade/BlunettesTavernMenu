package com.beech.blunettestavernmenu;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Wayne Beech on 12/9/2015.
 */
public class MenuActivity extends AppCompatActivity {

    //constants
    private final int TABLETIME = 10;
    private final int SECONDS = 1000;

    //globals
    private int currentGallerySelection = 0;
    private Button btnPurchase;
    private FoodDatabase db;
    protected Integer[] imageIDs = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPurchase = (Button) findViewById(R.id.btnPurchase);

        //create database
        db = new FoodDatabase(this);

        //Apparently Gallery View is deprecated but whatevs
        Gallery gallery = (Gallery) findViewById(R.id.MainGallery);
        gallery.setAdapter(new ImageAdapter(getBaseContext(), imageIDs));

        //set the image to the new menus first option
        ImageView imageView = (ImageView) findViewById(R.id.imgMain);
        imageView.setImageResource(imageIDs[currentGallerySelection]);

        //On click listener for the gallery
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) { onGalleryClicked(position);
            }
        });

        //On click listener for purchase button
        btnPurchase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onPurchase();
            }
        });
    }

    private void onGalleryClicked(int position) {
        FoodItem foodItem = db.getFoodItemByID(imageIDs[position]);

        //display selected product name
        //Toast.makeText(getBaseContext(), "pic" + (position + 1) + " selected", Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(), foodItem.getName(), Toast.LENGTH_SHORT).show();

        // display selected image image
        ImageView imageView = (ImageView) findViewById(R.id.imgMain);
        imageView.setImageResource(imageIDs[position]);

        //set current position for referencing database
        currentGallerySelection = position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //toasts as placeholders and to confirm the event and ids
        //changes to new intent based of which menu is selected
        switch(item.getItemId()){
            case R.id.menu_complete:
                startActivity(new Intent(getApplicationContext(),MenuCompleteActivity.class));
                Toast.makeText(this, "Complete Menu",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_veg:
                startActivity(new Intent(getApplicationContext(),MenuVegetarianActivity.class));
                Toast.makeText(this, "Vegetarian Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_special:
                startActivity(new Intent(getApplicationContext(),MenuSpecialActivity.class));
                Toast.makeText(this, "Today's Specials", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void onPurchase(){

        //the currently selected item is needed for purchase details
        FoodItem foodItem = db.getFoodItemByID(imageIDs[currentGallerySelection]);

        // in a real world app this time would be linked to a database value for current expected wait time
        //curious how cascading Toasts behave//they display one after another with about a half second delay
        Toast.makeText(this, "Purchased " + foodItem.getName() + " $" + foodItem.getPrice(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Table ready in " + TABLETIME + " seconds.", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Meal ready in " + foodItem.getPrepTime() + " seconds.", Toast.LENGTH_LONG).show();

        //make notifications here
        //these hardcoded ids at the end of the scheduleNotification are not good practice but they do the job.
        //If I can find a way to create a unique identifier i can make it better
        //Should try concatenating foodItem (id or resource id) + timestamp + table/food identifier;
        scheduleNotification(getNotification("Table ready.",
                "Meal eta " + (foodItem.getPrepTime() - TABLETIME) + " seconds."), TABLETIME * SECONDS, 1);
        scheduleNotification(getNotification("Meal Prepared.",
                "Thank you and enjoy."), foodItem.getPrepTime() * SECONDS, 2);

    }

    //schedules the notifications transmission and builds intent
    private void scheduleNotification(Notification notification, int delay, int id) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, id);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    //builds the notification to be transmitted
    private Notification getNotification(String notification, String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(notification);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_clock);
        return builder.build();
    }

    protected void setImageIDs(Integer[] imageIds){
        this.imageIDs = imageIds;
    }
}
