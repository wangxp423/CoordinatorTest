package com.xp.coordinator.coordinatortest.livedata.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;

/**
 * author wangxp
 */
@Database(entities = {NewsBean.class}, version = 1)
public abstract class InfoDataBase extends RoomDatabase {
    public abstract InfoDao infoDao();

    private static InfoDataBase INSTANCE;
    private static final Object sLock = new Object();
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //这里做一下 更新数据库插入列 或 其他的操作
        }
    };

    public static InfoDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        InfoDataBase.class, "newsbean")
                        .addMigrations(MIGRATION_1_2) //add 可以是好几个
                        .build();
            }
            return INSTANCE;
        }
    }
}
