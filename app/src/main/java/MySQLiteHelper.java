import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.claudius.saveme.Girlfriend;

import java.sql.Date;

/**
 * Created by Andi on 20.12.16.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    //Tabellenname festgelegt
    private static final String MY_GIRL = "girl";

    //Eigenschaften und Namen der Zeilen
    private static final String HER_NAME = "name";
    private static String HER_BIRTHDAY_DAY = "1";
    private static String HER_BIRTHDAY_MONTH = "1";
    private static String HER_BIRTHDAY_YEAR = "2001";
    private static String HER_ANNIVERSARY_DAY = "1";
    private static String HER_ANNIVERSARY_MONTH = "1";
    private static String HER_ANNIVERSARY_YEAR = "2001";
    private static String HER_COLOR = "color";
    private static String HER_BAND = "band";
    private static String HER_FLOWER = "flower";
    private static String HER_FOOD = "food";

    private static String[] COLUMNS = {HER_NAME, HER_BIRTHDAY_DAY, HER_BIRTHDAY_MONTH, HER_BIRTHDAY_YEAR, HER_ANNIVERSARY_DAY, HER_ANNIVERSARY_MONTH, HER_ANNIVERSARY_YEAR, HER_COLOR, HER_BAND, HER_FLOWER, HER_FOOD};

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
                HER_FOOD + " TEXT" +
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

    public void addGirl(Girlfriend newGirl){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(HER_NAME, Girlfriend.getName());
        values.put(HER_BIRTHDAY_DAY, Girlfriend.getbDayDay());
        values.put(HER_BIRTHDAY_MONTH, Girlfriend.getbDayMonth());
        values.put(HER_BIRTHDAY_YEAR, Girlfriend.getbDayYear());
        values.put(HER_ANNIVERSARY_DAY, Girlfriend.getAnniversaryDay());
        values.put(HER_ANNIVERSARY_MONTH, Girlfriend.getAnniversaryMonth());
        values.put(HER_ANNIVERSARY_YEAR, Girlfriend.getAnniversaryYear());
        values.put(HER_COLOR, Girlfriend.getColor());
        values.put(HER_BAND, Girlfriend.getBand());
        values.put(HER_FLOWER, Girlfriend.getFlowers());
        values.put(HER_FOOD, Girlfriend.getFood());

        db.insert(MY_GIRL, null, values);
        db.close();
    }

    public Girlfriend  getGirlfriend(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor aCursor = db.query(MY_GIRL, COLUMNS, " name = ?", null, null, null, null);
        if(aCursor != null){
            aCursor.moveToFirst();
        }
        Girlfriend newGirlfriend = new Girlfriend();
        newGirlfriend.setName(aCursor.getString(0));
        newGirlfriend.setbDayDay(Integer.parseInt(aCursor.getString(1)));
        newGirlfriend.setbDayMonth(Integer.parseInt(aCursor.getString(2)));
        newGirlfriend.setbDayYear(Integer.parseInt(aCursor.getString(3)));
        newGirlfriend.setAnniversaryDay(Integer.parseInt(aCursor.getString(4)));
        newGirlfriend.setAnniversaryMonth(Integer.parseInt(aCursor.getString(5)));
        newGirlfriend.setAnniversaryYear(Integer.parseInt(aCursor.getString(6)));
        newGirlfriend.setColor(aCursor.getString(7));
        newGirlfriend.setBand(aCursor.getString(8));
        newGirlfriend.setFlowers(aCursor.getString(9));
        newGirlfriend.setFood(aCursor.getString(10));

        return newGirlfriend;
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
}
