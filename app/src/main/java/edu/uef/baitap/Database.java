package edu.uef.baitap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION );
    }
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ANDROID100";

    private static final String TABLE_USER = "User";
    private static final String KEY_ID_USER = "id_user";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_HOTEN = "hoTen";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IMAGE = "image";


    private static final String TABLE = "BaiTap";
    private static final String KEY_ID = "id";
    private static final String KEY_TENMON = "tenmonhoc";
    private static final String KEY_TENBAITAP = "tenbaitap";
    private static final String KEY_date = "date";
    private static final String KEY_thoiHan = "thoiHan";
    private  static final String KEY_trangthai="trangthai";
    private static final String KEY_chungLoai = "chungLoai";
    private static  final String KEY_image="image_baitap";


    String CREATE_CONTACTS_TABLE = " CREATE TABLE " + TABLE_USER + "("
            + KEY_ID_USER + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT NOT NULL,"
            + KEY_PASSWORD + " TEXT NOT NULL," + KEY_HOTEN +" TEXT ,"+
            KEY_IMAGE +" BLOG)";

    String CREATE_CONTACTS_TABLE1 = " CREATE TABLE " + TABLE + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TENMON + " TEXT,"
            + KEY_date + " TEXT,"
            + KEY_TENBAITAP + " TEXT,"
            + KEY_thoiHan + " TEXT,"
            + KEY_chungLoai + " TEXT,"
            +KEY_trangthai+" INTEGER,"
            +KEY_ID_USER+" INTEGER,"
            +KEY_image+" BLOG,"
           +"FOREIGN KEY("+KEY_ID_USER+") REFERENCES "+ TABLE_USER +" ("+KEY_ID_USER+"))";
    String CREATE_CONTACTS_TABLE2 = " CREATE TABLE " + "NopBai" + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TENMON + " TEXT,"
            + KEY_date + " TEXT,"
            + KEY_TENBAITAP + " TEXT,"
            + KEY_thoiHan + " TEXT,"
            + KEY_chungLoai + " TEXT,"
            +KEY_trangthai+" INTEGER,"
            +KEY_ID_USER+" INTEGER,"
            +KEY_image+" BLOG,"
            +"FOREIGN KEY("+KEY_ID_USER+") REFERENCES "+ TABLE_USER +" ("+KEY_ID_USER+"))";

    


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE layID(id_user INTEGER PRIMARY KEY)");
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE1);
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE);
        db.execSQL("DROP TABLE  IF EXISTS NopBai" );
        db.execSQL("DROP TABLE  IF EXISTS layID" );
        onCreate(db);
    }

    public  void InsertUser(String user,String pass,String Hoten,byte[] image)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="INSERT INTO User VALUES(null,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,user);
        statement.bindString(2,pass);
        statement.bindString(3,Hoten);
        statement.bindBlob(4,image);
        statement.executeInsert();


    }



    void addUser(User contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, contact.getUsername()); // Contact Name
        values.put(KEY_PASSWORD, contact.getPassword());
        values.put(KEY_HOTEN, contact.getHoTen());
        values.put(KEY_IMAGE, contact.getImage());

        db.insert(TABLE_USER, null, values);

        db.close();
    }
    void addLayID(layID contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_user", contact.getId()); // Contact Name
        db.insert("layID", null, values);
        db.close();
    }
    public int  uppdateUser(User contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, contact.getUsername()); // Contact Name
        values.put(KEY_PASSWORD, contact.getPassword()); // Contact Phone
        values.put(KEY_HOTEN, contact.getHoTen());
        values.put(KEY_IMAGE,contact.getImage());




        // updating row
        return db.update(TABLE_USER, values, KEY_ID_USER + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }
    void addBaiTap(BaiTap contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TENBAITAP, contact.getTenBaiTap());
        values.put(KEY_TENMON, contact.getTenMonHoc()); // Contact Name
        values.put(KEY_thoiHan, contact.getThoiHan());
        values.put(KEY_date, contact.getDate()); // Contact Phone
        values.put(KEY_trangthai,contact.getTrangthai());
        values.put(KEY_ID_USER,contact.getIdUser());
        values.put(KEY_chungLoai, contact.getChungLoai());

        // Inserting Row
        db.insert(TABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    void addBaiTap1(BaiTap contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TENBAITAP, contact.getTenBaiTap());
        values.put(KEY_TENMON, contact.getTenMonHoc()); // Contact Name
        values.put(KEY_thoiHan, contact.getThoiHan());
        values.put(KEY_date, contact.getDate()); // Contact Phone
        values.put(KEY_trangthai,contact.getTrangthai());
        values.put(KEY_ID_USER,contact.getIdUser());
        values.put(KEY_chungLoai, contact.getChungLoai());

        // Inserting Row
        db.insert("NopBai", null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public int updateBaiTap(BaiTap contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TENMON, contact.getTenMonHoc()); // Contact Name
        values.put(KEY_date, contact.getDate()); // Contact Phone
        values.put(KEY_TENBAITAP, contact.getTenBaiTap());
        values.put(KEY_thoiHan, contact.getThoiHan());
        values.put(KEY_trangthai,contact.getTrangthai());
        values.put(KEY_chungLoai, contact.getChungLoai());
        values.put(KEY_image,contact.getImage());


        // updating row
        return db.update(TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }


    // Deleting single contact
    public void deleteBaiTap(BaiTap contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    public void deletelayID(layID contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("layID",   "id_user = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    public void deleteBaiTap1(BaiTap contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("NopBai", KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    public List<User> getAllContactsUser() {
        List<User> contactList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User contact = new User();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setUsername(cursor.getString(1));
                contact.setPassword(cursor.getString(2));
                contact.setHoTen(cursor.getString(3));
                contact.setImage(cursor.getBlob(4));


                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public  List<layID>getallID()
    {
        List<layID> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM layID" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                layID contact = new layID();
                contact.setId(Integer.parseInt(cursor.getString(0)));


                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<BaiTap> getAllContactsBaiTap() {
        List<BaiTap> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BaiTap contact = new BaiTap();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTenMonHoc(cursor.getString(1));
                contact.setDate(cursor.getString(2));
                contact.setTenBaiTap(cursor.getString(3));
                contact.setThoiHan(cursor.getString(4));
                contact.setChungLoai(cursor.getString(5));
                contact.setTrangthai(cursor.getInt(6));
                contact.setIdUser(cursor.getInt(7));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<BaiTap> getSapxepBaiTap(String st) {
        List<BaiTap> contactList = new ArrayList<>();
        // Select All Query


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BaiTap contact = new BaiTap();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTenMonHoc(cursor.getString(1));
                contact.setDate(cursor.getString(2));
                contact.setTenBaiTap(cursor.getString(3));
                contact.setThoiHan(cursor.getString(4));
                contact.setChungLoai(cursor.getString(5));
                contact.setTrangthai(cursor.getInt(6));
                contact.setIdUser(cursor.getInt(7));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<BaiTap> getAllContactsBaiTap1() {
        List<BaiTap> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM NopBai ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                BaiTap contact = new BaiTap();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTenMonHoc(cursor.getString(1));
                contact.setDate(cursor.getString(2));
                contact.setTenBaiTap(cursor.getString(3));
                contact.setThoiHan(cursor.getString(4));
                contact.setChungLoai(cursor.getString(5));
                contact.setTrangthai(cursor.getInt(6));
                contact.setIdUser(cursor.getInt(7));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

//    public  void deleteBaiTapuser(int i)
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        int res =db.delete(TABLE,KEY_ID+"="+i,null);
//        return;
//    }
}
