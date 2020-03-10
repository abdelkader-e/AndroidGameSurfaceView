package com.example.plane.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

/**
 * @author  Abdelkader
 * @version 1
 * Cette classe permet de persister les données dans une base de données.
 */

public class dataBaseManager extends OrmLiteSqliteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "dbz";
    private static final int DATABASE_VERSION = 1;

    public dataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, dbScore.class);

        } catch (Exception e) {

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            TableUtils.dropTable(connectionSource, dbScore.class,true);
            this.onCreate(database,connectionSource);


        }catch (Exception e){

        }

    }

    public void insertScore(dbScore scr) {
        try {
            Dao<dbScore, Integer> dao = getDao(dbScore.class);
            dao.create(scr);
            Log.i("bd", "dbScore inséré");

        } catch (Exception e) {

            Log.i("bd", "exception :" + e.getMessage());
        }

    }
    List<dbScore> listScore;

    public List<dbScore> getAllScore() {
        try {
            Dao<dbScore, Integer> dao = getDao(dbScore.class);
            listScore = dao.queryForAll();


            return listScore;

        } catch (Exception e) {
            Log.i("bd", "exception :" + e.getMessage());
        }
        return listScore;
    }


}
