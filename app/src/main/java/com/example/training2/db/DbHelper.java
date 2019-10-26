package com.example.training2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.training2.model.Mahasiswa;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cobaAndroid2.db";
    public static final String MAHASISWA_TABLE_NAME = "mahasiswa";
    public static final String MAHASISWA_COLUMN_NIM = "nim";
    public static final String MAHASISWA_COLUMN_NAME = "nama";
    public static final String MAHASISWA_COLUMN_PHONE = "telepon";
    private HashMap hp;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
        System.out.println("====================TERPANGGIL============================================");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table mahasiswa " + "(nim text primary key, nama text,telepon text)"
        );
        System.out.println("====================TERBUAT============================================");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(db);
    }

    public boolean insertMahasiswa(String nim, String nama, String telepon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nim", nim);
        contentValues.put("nama", nama);
        contentValues.put("telepon", telepon);
        db.insert(MAHASISWA_TABLE_NAME, null, contentValues);
        System.out.println("=======================================MASUK=======================================");
        return true;
    }

    public Cursor getData(int nim) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from mahasiswa where nim="+nim+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MAHASISWA_TABLE_NAME);
        return numRows;
    }

    public ArrayList<Mahasiswa> getAllMahasiswa() {
        ArrayList<Mahasiswa> array_list = new ArrayList<Mahasiswa>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from mahasiswa", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            Mahasiswa m = new Mahasiswa();
            m.setNama(res.getString(res.getColumnIndex(MAHASISWA_COLUMN_NAME)));
            m.setNim(res.getString(res.getColumnIndex(MAHASISWA_COLUMN_NIM)));
            m.setNotlp(res.getString(res.getColumnIndex(MAHASISWA_COLUMN_PHONE)));
            array_list.add(m);
//            System.out.println("ini dari db" + res.getString(res.getColumnIndex(MAHASISWA_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

}
