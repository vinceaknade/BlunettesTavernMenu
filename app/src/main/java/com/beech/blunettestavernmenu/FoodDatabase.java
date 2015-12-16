package com.beech.blunettestavernmenu;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Wayne Beech on 12/9/2015.
 */
public class FoodDatabase {

    //Database constants
    public static final String DB_NAME = "FoodDatabase.db";
    public static final int    DB_VERSION = 5; //must update this everytime a database revision is made

    //Table Constants
    public static final String FOOD_TABLE = "food";

    public static final String ID = "_id"       //INT
            , NAME = "name"                     //REAL
            , PREP_TIME = "prep_time"           //INT
            , PRICE = "price";                  //REAL

    //column identifiers
    public static final int ID_COL = 0
            , NAME_COL = 1
            , PREP_TIME_COL = 2
            , PRICE_COL = 3;

    // CREATE and DROP TABLE statements
    public static final String CREATE_FOOD_TABLE =
            "CREATE TABLE " + FOOD_TABLE + " (" +
                    ID          + " INTEGER PRIMARY KEY, " +
                    NAME        + " REAL NOT NULL, " +
                    PREP_TIME   + " INTEGER NOT NULL, " +
                    PRICE       + " REAL NOT NULL);";

    public static final String DROP_FOOD_TABLE =
            "DROP TABLE IF EXISTS " + FOOD_TABLE;


    //Helper class
    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create table
            db.execSQL(CREATE_FOOD_TABLE);

            // insert default values
            // this may take a while
            //(database, id, name, prepTime, price)
            insert(db,R.drawable.barracudameuniere,"Barracuda Mmeuniere",30,10f);
            insert(db,R.drawable.boarpiccata,"Boar Piccata",30,10f);
            insert(db,R.drawable.carrotsoup,"Carrot Soup",30,10f);
            insert(db,R.drawable.chickensoup,"Chicken Soup",30,10f);
            insert(db,R.drawable.creamybarracudastew,"Creamy Barracuda Stew",30,10f);
            insert(db,R.drawable.deepfriedherring,"Deep Fried Herring",30,20f);
            insert(db,R.drawable.deepfriedscorpion,"Deep Fried Scorpion",30,20f);
            insert(db,R.drawable.escargot,"Escargot",30,20f);
            insert(db,R.drawable.frenchfries,"French Fries",30,20f);
            insert(db,R.drawable.grilledenokimushrooms,"Grilled Enoki Mushrooms",30,20f);
            insert(db,R.drawable.grilledsnake,"Grilled Snake",30,30f);
            insert(db,R.drawable.hamsteak,"Hamsteak",30,30f);
            insert(db,R.drawable.harpyeggomelette,"Harpy Egg Omelette",30,30f);
            insert(db,R.drawable.heartymeatsoup,"Hearty Meat Soup",30,30f);
            insert(db,R.drawable.herringsoup,"Herring Soup",30,30f);
            insert(db,R.drawable.juliennedsauteedworms,"Julienned Sauteed Worms",30,40f);
            insert(db,R.drawable.lobsterthermidor,"Lobster Thermidor",30,40f);
            insert(db,R.drawable.macaronisoup,"Macaroni Soup",30,40f);
            insert(db,R.drawable.musselsbouillabaisse,"Mussels Bouillabaisse",30,40f);
            insert(db,R.drawable.myconidsoup,"Myconid Soup",30,40f);
            insert(db,R.drawable.onionrings,"Onion Rings",30,50f);
            insert(db,R.drawable.onionsoup,"Onion Soup",30,50f);
            insert(db,R.drawable.oysterstew,"Oyster Stew",30,50f);
            insert(db,R.drawable.paellawithmussels,"Paella With Mussels",30,50f);
            insert(db,R.drawable.peacroquettes,"Pea Croquettes",30,50f);
            insert(db,R.drawable.peapotage,"Pea Potage",30,60f);
            insert(db,R.drawable.rabbitstew,"Rabbit Stew",30,60f);
            insert(db,R.drawable.roastedchicken,"Roasted Chicken",30,60f);
            insert(db,R.drawable.roastedsalamander,"Roasted Salamander",30,60f);
            insert(db,R.drawable.roasthamsteak,"Roast Hamsteak",30,60f);
            insert(db,R.drawable.sauteedcarrots,"Sauteed Carrots",30,10.5f);
            insert(db,R.drawable.sauteedminotaurtongue,"Sauteed Minotaur Tongue",30,10.5f);
            insert(db,R.drawable.sauteedmyconid,"Sauteed Myconid",30,10.5f);
            insert(db,R.drawable.searedhare,"Seared Hare",30,10.5f);
            insert(db,R.drawable.snailsoup,"Snail Soup",30,10.5f);
            insert(db,R.drawable.snakestew,"Snake Stew",30,20.5f);
            insert(db,R.drawable.stewedminotaurtongue,"Stewed Minotaur Tongue",30,20.5f);
            insert(db,R.drawable.stirfriedbat,"Stirfried Bat",30,20.5f);
            insert(db,R.drawable.stuffedcabbage,"Stuffed Cabbage",30,20.5f);
            insert(db,R.drawable.toastedbread,"Toasted Bread",30,20.5f);
        }

        //insert with a controlled id
        public void insert(SQLiteDatabase db, int id, String name, int prepTime, float price)
        {
            db.execSQL("INSERT INTO " + FOOD_TABLE + " VALUES ("
                    + id + ", "
                    + "'" + name + "'" + ", " //gotta include single quotes for strings apparently
                    + prepTime + ", "
                    + price + ")");
        }

        // insert with incrementing id
        // *NOTE* autoincrement removed must assign IDs
        /*public void insert(SQLiteDatabase db, String name, int prepTime, float price)
        {
            db.execSQL("INSERT INTO " + FOOD_TABLE + " VALUES ("
                    + name + ", "
                    + prepTime + ", "
                    + price + ")");
        }*/

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.d("Food Database", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            db.execSQL(FoodDatabase.DROP_FOOD_TABLE);
            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public FoodDatabase(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    public FoodItem getFoodItemByID(int id)
    {
        String where = ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };
        openReadableDB();

        //obtain the cursor
        Cursor cursor = db.query(FOOD_TABLE,null, null, null, null, null, null);
        //Cursor cursor = db.query(null,null,where,whereArgs,null,null,null);
        FoodItem foodItem = new FoodItem();

        while(cursor.moveToNext()) {
            foodItem.setId(cursor.getInt(ID_COL));
            foodItem.setName(cursor.getString(NAME_COL));
            foodItem.setPrepTime(cursor.getInt(PREP_TIME_COL));
            foodItem.setPrice(cursor.getFloat(PRICE_COL));

            if(foodItem.getId() == id) break;
        }
        return foodItem;
    }
}