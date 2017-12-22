package com.xp.coordinator.coordinatortest.mvp.adapter;

import android.content.Context;

import com.commonlib.adapter.SuperAdapter;
import com.commonlib.adapter.SuperViewHolder;
import com.xp.coordinator.coordinatortest.R;
import com.xp.coordinator.coordinatortest.mvp.entity.HomeAndroidEntity;

import java.util.List;

/**
 * @类描述：首页数据adapter
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/21 0021 15:43
 * @修改人：
 * @修改时间：2017/12/21 0021 15:43
 * @修改备注：
 */

public class HomeAndroidAdapter extends SuperAdapter<HomeAndroidEntity.TypeAndroidEntity> {
    public HomeAndroidAdapter(Context context, List<HomeAndroidEntity.TypeAndroidEntity> items) {
        super(context, items, R.layout.item_normal_text);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, HomeAndroidEntity.TypeAndroidEntity item) {
        StringBuilder builder = new StringBuilder();
        builder.append("Type：").append(item.getType()).append("\n")
                .append("Who：").append(item.getWho());
        holder.setText(R.id.tv_normal_text, builder.toString());
    }
}
