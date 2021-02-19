package com.mine.donemyself;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseSection {

    MyDBHelper myDBHelper;

    public DatabaseSection(Context context) {

        myDBHelper = new MyDBHelper(context);
    }

    public static class MyDBHelper extends SQLiteOpenHelper{
        /*By creating the MyDBHelper with extension of SQLiteOpenHelper
        (Database that is using in the Android to store the local data in the application).

        ==>  Soon the class extends the SQLiteOpenHelper it asks for implement the methods(override methods
        -->onCreate and onUpgrade) and also asks for constructor for Class(that you created with the name).

        Constructor will get the parameters but we don't need all those and we will go through only with context
            and also with the constructor super will be the key here()

        Lets create the database table here with the specified column names
        */

        private static final String DATA_BASE = "Mydatabase";
        private static final String TABLE_NAME = "My_table";
        private static final int DATABASE_VERSION = 1;
        private static final String UNIQUE_ID = "MY_KEY";
        private static final String NAME = "name";
        private static final String FATHER_NAME = "father_name";
        private static final String DISTRICT = "district";
        private static final String MANDAL = "mandal";
        private static final String VILLAGE = "village";
        private static final String CREATE_TABLE = " CREATE TABLE "+ TABLE_NAME +
                "("+ UNIQUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                NAME + " TEXT,"+ FATHER_NAME + " TEXT,"+
                DISTRICT+ " TEXT,"+ MANDAL+ " TEXT,"+ VILLAGE +" TEXT)";

        private Context context;

        public MyDBHelper(@Nullable Context context) {
            super(context, DATA_BASE, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /*By the override method we got the SQLiteDatabase, so that we can execute the database table that we
            created(CREATE_TABLE). The database execution should be done from here even. However if we create Object
            in another class, the database must run(execute) from here.
             */

           try{
               db.execSQL(CREATE_TABLE);
           }catch (Exception e){
               Toasting.message(context,""+e);
           }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        }

    public long insertData(String name, String fname, String dist_name, String mandal_name, String village_name){
        SQLiteDatabase databaseInsert = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDBHelper.NAME,name);
        contentValues.put(MyDBHelper.FATHER_NAME,fname);
        contentValues.put(MyDBHelper.DISTRICT,dist_name);
        contentValues.put(MyDBHelper.MANDAL,mandal_name);
        contentValues.put(MyDBHelper.VILLAGE,village_name);

        long data = databaseInsert.insert(MyDBHelper.TABLE_NAME,null,contentValues);
        return data;
    }

    public ArrayList<SetMethods> getData(){
        SetMethods dsm;
        ArrayList dal = new ArrayList();
        SQLiteDatabase getdb = myDBHelper.getWritableDatabase();
        String[] columns = {MyDBHelper.NAME,MyDBHelper.FATHER_NAME,MyDBHelper.DISTRICT,
                                        MyDBHelper.MANDAL,MyDBHelper.VILLAGE,MyDBHelper.UNIQUE_ID};
        Cursor cursor = getdb.query(MyDBHelper.TABLE_NAME,columns,
                                        null,null,null,null,null);
        while (cursor.moveToNext()){
            dsm = new SetMethods();
            String name = cursor.getString(cursor.getColumnIndex(MyDBHelper.NAME));
            String fname = cursor.getString(cursor.getColumnIndex(MyDBHelper.FATHER_NAME));
            String dist = cursor.getString(cursor.getColumnIndex(MyDBHelper.DISTRICT));
            String mandal = cursor.getString(cursor.getColumnIndex(MyDBHelper.MANDAL));
            String village = cursor.getString(cursor.getColumnIndex(MyDBHelper.VILLAGE));
            String unique = cursor.getString(cursor.getColumnIndex(MyDBHelper.UNIQUE_ID));

            dsm.setMname(name);
            dsm.setMfname(fname);
            dsm.setMdist(dist);
            dsm.setMmand(mandal);
            dsm.setMvill(village);
            dsm.setMid(unique);

            dal.add(dsm);
        }
        return dal;
    }

    public long updateData(String uname,String ufname,String udistname,String umandalname,String uvillagename,String uni_key){
        SQLiteDatabase udsdata = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDBHelper.NAME,uname);
        contentValues.put(MyDBHelper.FATHER_NAME,ufname);
        contentValues.put(MyDBHelper.DISTRICT,udistname);
        contentValues.put(MyDBHelper.MANDAL,umandalname);
        contentValues.put(MyDBHelper.VILLAGE,uvillagename);

        long uv = udsdata.update(MyDBHelper.TABLE_NAME,contentValues,"MY_KEY ="+uni_key,null);
        return uv;
    }

    public long deleteData(String uni_key){

        SQLiteDatabase ddel = myDBHelper.getWritableDatabase();
        String whereClause = "MY_KEY=?";
        String whereArgs[] = {uni_key.toString()};
//        long del = ddel.delete(MyDBHelper.TABLE_NAME,MyDBHelper.UNIQUE_ID +"=?", new String[]{uni_key}); // or
        long del = ddel.delete(MyDBHelper.TABLE_NAME,whereClause,whereArgs);
        return del;

    }
}
