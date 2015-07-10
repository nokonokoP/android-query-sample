package com.example.kinjo.androidquerysample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kinjo.androidquerysample.dao.EmployeeDao;
import com.example.kinjo.androidquerysample.entity.Employee;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DB操作ヘルパー
 * Created by kinjo on 2015/07/09.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "sample_db";
    private static final int DB_VERSION = 1;

    private static DatabaseHelper sHelper = null;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private EmployeeDao mEmployeeDao;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        createTables(connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public void close() {
        if(usageCounter.decrementAndGet() == 0) {
            super.close();
            sHelper = null;
        }
    }

    /**
     * テーブル生成処理
     * @param connectionSource ConnectionSource
     * @return メッセージリスト
     */
    private List<String> createTables(ConnectionSource connectionSource) {
        List<String> resultMessages = new ArrayList<>();

        Class<?>[] createTables = {
                Employee.class
        };

        for(Class<?> table : createTables) {
            try {
                TableUtils.createTableIfNotExists(connectionSource, table);
            } catch (SQLException e) {
                resultMessages.add(table.getSimpleName());
            }
        }
        return resultMessages;
    }

    /**
     * DatabaseHelper取得
     * @param context Activity
     * @return DatabaseHelper
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if(sHelper == null) {
            sHelper = new DatabaseHelper(context);
        }
        usageCounter.incrementAndGet();
        return sHelper;
    }

    /**
     * EmployeeDao取得
     * @return EmployeeDao 社員情報Dao
     * @throws SQLException
     */
    public EmployeeDao getEmployeeDao() throws SQLException {
        if(mEmployeeDao == null) {
            Dao<Employee, Integer> dao = getDao(Employee.class);
            mEmployeeDao = new EmployeeDao(dao);
        }

        return mEmployeeDao;
    }

}
