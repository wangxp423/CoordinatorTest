package com.xp.coordinator.coordinatortest.livedata.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.commonlib.adapter.SuperAdapter;
import com.commonlib.adapter.SuperViewHolder;
import com.commonlib.imageloader.ImageLoader;
import com.xp.coordinator.coordinatortest.R;
import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;


/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/26 0026 16:34
 * @修改人：
 * @修改时间：2017/12/26 0026 16:34
 * @修改备注：
 */

public class NewsAdapter extends SuperAdapter<NewsBean> {
    public NewsAdapter(Context context) {
        super(context, null, R.layout.item_news);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, NewsBean item) {
        ImageView imageView = holder.findViewById(R.id.iv_new_pic);
        ImageLoader.loadUrlImage(getContext(), item.getPicUrl(), imageView, getContext().getResources().getDrawable(R.drawable.ic_launcher_background));
        holder.setText(R.id.tv_new_title, item.getTitle());
        holder.setText(R.id.tv_new_time, item.getCtime());
    }
}
