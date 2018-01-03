package com.xp.coordinator.coordinatortest.livedata.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/26 0026 18:20
 * @修改人：
 * @修改时间：2017/12/26 0026 18:20
 * @修改备注：
 */
@Dao
public interface InfoDao {
    @Query("SELECT * FROM newsbean")
    LiveData<List<NewsBean>> getAll();

    @Query("SELECT * FROM newsbean")
    List<NewsBean> getAl();

    @Insert(onConflict = REPLACE)
    void save(NewsBean info);

    @Query("SELECT * FROM newsbean WHERE ctime = :ctime")
    LiveData<NewsBean> load(String ctime);

    @Insert(onConflict = REPLACE)
    void insertAll(List<NewsBean> list);

    @Delete
    void delete(NewsBean list);
}
