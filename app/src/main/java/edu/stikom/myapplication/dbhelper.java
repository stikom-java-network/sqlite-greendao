package edu.stikom.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class dbhelper {

    public static DaoSession getInstance(Context mContext) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext,"db_penjualan",null);
        SQLiteDatabase db_penjualan = helper.getWritableDatabase();

        DaoMaster dbMaster = new DaoMaster(db_penjualan);
        return dbMaster.newSession();

    }


}
