package com.example.claudius.saveme.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.claudius.saveme.Storages.Girlfriend;
import com.example.claudius.saveme.Storages.User;

import java.io.ByteArrayOutputStream;


public class MySQLiteHelper extends SQLiteOpenHelper {

    //Tabellenname festgelegt
    private static final String MY_GIRL = "girl";

    //Eigenschaften und Namen der Zeilen
    private static final String HER_NAME = "name";
    private static final String HER_BIRTHDAY_DAY = "bdayday";
    private static final String HER_BIRTHDAY_MONTH = "bdaymonth";
    private static final String HER_BIRTHDAY_YEAR = "bdayyear";
    private static final String HER_ANNIVERSARY_DAY = "anniversaryday";
    private static final String HER_ANNIVERSARY_MONTH = "anniversarymonth";
    private static final String HER_ANNIVERSARY_YEAR = "anniversaryyear";
    private static final String HER_COLOR = "color";
    private static final String HER_BAND = "band";
    private static final String HER_FLOWER = "flower";
    private static final String HER_FOOD = "food";
    private static final String HER_RESIDENCE = "residence";
    private static final String HER_PROFILEPIC ="profilepic";

    private static final String YOUR_USERNAME = "username";
    private static final String YOUR_PASSWORD = "password";

    private static String[] COLUMNS = {HER_NAME, HER_BIRTHDAY_DAY, HER_BIRTHDAY_MONTH, HER_BIRTHDAY_YEAR, HER_ANNIVERSARY_DAY, HER_ANNIVERSARY_MONTH, HER_ANNIVERSARY_YEAR, HER_COLOR, HER_BAND, HER_FLOWER, HER_FOOD, HER_RESIDENCE, HER_PROFILEPIC, YOUR_USERNAME, YOUR_PASSWORD};

    //Datenbank Version
    private static final int DATABASE_VERSION = 1;
    //Datenbank Name
    private static final String DATABASE_NAME = "GirlDB";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //SQL Befehl zum Erstellen der Datenbank
        String CREATE_NEW_GIRL_TABLE = "CREATE TABLE " +
                MY_GIRL +
                " ( " +
                HER_NAME + " TEXT," +
                HER_BIRTHDAY_DAY + " TEXT," +
                HER_BIRTHDAY_MONTH + " TEXT," +
                HER_BIRTHDAY_YEAR + " TEXT," +
                HER_ANNIVERSARY_DAY + " TEXT," +
                HER_ANNIVERSARY_MONTH + " TEXT," +
                HER_ANNIVERSARY_YEAR + " TEXT," +
                HER_COLOR + " TEXT," +
                HER_BAND + " TEXT," +
                HER_FLOWER + " TEXT," +
                HER_FOOD + " TEXT," +
                HER_RESIDENCE + " TEXT,"+
                HER_PROFILEPIC + " BLOB,"+
                YOUR_USERNAME +" TEXT,"+
                YOUR_PASSWORD +" TEXT"+

                " )";
        db.execSQL(CREATE_NEW_GIRL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //alte Tabelle leeren, falls existent
        String dropIfEx = "DROP TABLE IF EXISTS " + MY_GIRL;
        db.execSQL(dropIfEx);
        this.onCreate(db);
    }

    public void addGirl(Girlfriend newGirl, User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        Bitmap bitmap = newGirl.getProfilePic();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);

        values.put(HER_NAME, newGirl.getName());
        values.put(HER_BIRTHDAY_DAY, newGirl.getbDayDay());
        values.put(HER_BIRTHDAY_MONTH, newGirl.getbDayMonth());
        values.put(HER_BIRTHDAY_YEAR, newGirl.getbDayYear());
        values.put(HER_ANNIVERSARY_DAY, newGirl.getAnniversaryDay());
        values.put(HER_ANNIVERSARY_MONTH, newGirl.getAnniversaryMonth());
        values.put(HER_ANNIVERSARY_YEAR, newGirl.getAnniversaryYear());
        values.put(HER_COLOR, newGirl.getColor());
        values.put(HER_BAND, newGirl.getBand());
        values.put(HER_FLOWER, newGirl.getFlowers());
        values.put(HER_FOOD, newGirl.getFood());
        values.put(HER_RESIDENCE, newGirl.getResidence());
        values.put(HER_PROFILEPIC, stream.toByteArray());
        values.put(YOUR_USERNAME, user.getUsername());
        values.put(YOUR_PASSWORD, user.getPassword());


        db.insert(MY_GIRL, null, values);
        db.close();
    }

    public Girlfriend  getGirlfriend(){
        /*SQLiteDatabase db = this.getReadableDatabase();

        Cursor aCursor = db.query(MY_GIRL, COLUMNS, name, null, null, null, null);
        if(aCursor != null){
            aCursor.moveToFirst();
        }
        */


        Girlfriend newGirlfriend = new Girlfriend();
        String query = "SELECT * FROM " + MY_GIRL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {

            newGirlfriend.setName(cursor.getString(0));
            newGirlfriend.setbDayDay(Integer.parseInt(cursor.getString(1)));
            newGirlfriend.setbDayMonth(Integer.parseInt(cursor.getString(2))+1);
            newGirlfriend.setbDayYear(Integer.parseInt(cursor.getString(3)));
            newGirlfriend.setAnniversaryDay(Integer.parseInt(cursor.getString(4)));
            newGirlfriend.setAnniversaryMonth(Integer.parseInt(cursor.getString(5)));
            newGirlfriend.setAnniversaryYear(Integer.parseInt(cursor.getString(6)));
            newGirlfriend.setColor(cursor.getString(7));
            newGirlfriend.setBand(cursor.getString(8));
            newGirlfriend.setFlowers(cursor.getString(9));
            newGirlfriend.setFood(cursor.getString(10));
            newGirlfriend.setResidence(cursor.getString(11));
        }else {
            newGirlfriend = null;
        }

        return newGirlfriend;
    }


    public Bitmap getGFProfilePic(){
        String query = "SELECT * FROM " + MY_GIRL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Bitmap profilepic = null;
        if (cursor.moveToFirst()){
            byte[] image = cursor.getBlob(12);
            profilepic = BitmapFactory.decodeByteArray(image, 0, image.length);

        }
        return profilepic;
    }


    public int updateGirlfriend(Girlfriend myGirlfriend){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(HER_NAME, myGirlfriend.getName());
        values.put(HER_BIRTHDAY_DAY, String.valueOf(myGirlfriend.getbDayDay()));
        values.put(HER_BIRTHDAY_MONTH, String.valueOf(myGirlfriend.getbDayMonth()));
        values.put(HER_BIRTHDAY_YEAR, String.valueOf(myGirlfriend.getbDayYear()));
        values.put(HER_ANNIVERSARY_DAY, String.valueOf(myGirlfriend.getAnniversaryDay()));
        values.put(HER_ANNIVERSARY_MONTH, String.valueOf(myGirlfriend.getAnniversaryMonth()));
        values.put(HER_ANNIVERSARY_YEAR, String.valueOf(myGirlfriend.getAnniversaryYear()));
        values.put(HER_COLOR, myGirlfriend.getColor());
        values.put(HER_BAND, myGirlfriend.getBand());
        values.put(HER_FLOWER, myGirlfriend.getFlowers());
        values.put(HER_FOOD, myGirlfriend.getFood());

        int i = db.update(MY_GIRL, values, HER_NAME + " = ?", new String[]{String.valueOf(myGirlfriend.getName())});
        db.close();
        return i;
    }

    public void deleteGirl(Girlfriend goneGirlfriend){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MY_GIRL, HER_NAME + " = ?", new String[]{String.valueOf(goneGirlfriend.getName())});

        db.close();
    }

    public boolean usernamecorrect(String username, String password) {

        String query = "SELECT * FROM " + MY_GIRL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String tableUser = cursor.getString(13);
            String tablePassword = cursor.getString(14);

            if (username.equals(tableUser) && password.equals(tablePassword)) {
                return true;
            }
        }
        return false;
    }
}
