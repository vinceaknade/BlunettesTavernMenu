package com.beech.blunettestavernmenu;

import android.content.Intent;
import android.os.Bundle;
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
public class MenuVegetarianActivity extends MenuActivity {

    //image references
    private Integer[] imageIDs = {
            R.drawable.carrotsoup,
            R.drawable.frenchfries,
            R.drawable.grilledenokimushrooms,
            R.drawable.macaronisoup,
            R.drawable.onionrings,
            R.drawable.onionsoup,
            R.drawable.peacroquettes,
            R.drawable.peapotage,
            R.drawable.sauteedcarrots,
            R.drawable.stuffedcabbage,
            R.drawable.toastedbread
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setImageIDs(imageIDs);
        super.onCreate(savedInstanceState);
    }
}
