package com.beech.blunettestavernmenu;

import android.content.Intent;
import android.content.SharedPreferences;
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
public class MenuCompleteActivity extends MenuActivity {

    //private Button btnPurchase;

    //images for the menus
    private Integer[] imageIDs = {
        R.drawable.barracudameuniere,
        R.drawable.boarpiccata,
        R.drawable.carrotsoup,
        R.drawable.chickensoup,
        R.drawable.creamybarracudastew,
        R.drawable.deepfriedherring,
        R.drawable.deepfriedscorpion,
        R.drawable.escargot,
        R.drawable.frenchfries,
        R.drawable.grilledenokimushrooms,
        R.drawable.grilledsnake,
        R.drawable.hamsteak,
        R.drawable.harpyeggomelette,
        R.drawable.heartymeatsoup,
        R.drawable.herringsoup,
        R.drawable.juliennedsauteedworms,
        R.drawable.lobsterthermidor,
        R.drawable.macaronisoup,
        R.drawable.musselsbouillabaisse,
        R.drawable.myconidsoup,
        R.drawable.onionrings,
        R.drawable.onionsoup,
        R.drawable.oysterstew,
        R.drawable.paellawithmussels,
        R.drawable.peacroquettes,
        R.drawable.peapotage,
        R.drawable.rabbitstew,
        R.drawable.roastedchicken,
        R.drawable.roastedsalamander,
        R.drawable.roasthamsteak,
        R.drawable.sauteedcarrots,
        R.drawable.sauteedminotaurtongue,
        R.drawable.sauteedmyconid,
        R.drawable.searedhare,
        R.drawable.snailsoup,
        R.drawable.snakestew,
        R.drawable.stewedminotaurtongue,
        R.drawable.stirfriedbat,
        R.drawable.stuffedcabbage,
        R.drawable.toastedbread
    };

    //update image array and call parent class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setImageIDs(imageIDs);
        super.onCreate(savedInstanceState);
    }
}
