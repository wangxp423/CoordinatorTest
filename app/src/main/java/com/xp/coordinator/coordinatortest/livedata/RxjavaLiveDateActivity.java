package com.xp.coordinator.coordinatortest.livedata;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.commonlib.base.BaseActivity;
import com.iwangfan.foundationlibary.utils.ToastUtils;
import com.xp.coordinator.coordinatortest.R;
import com.xp.coordinator.coordinatortest.livedata.adapter.NewsAdapter;
import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;
import com.xp.coordinator.coordinatortest.livedata.viewmodel.ProfileViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类描述：Rxjava 和 LiveData 结合
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/10 0010 17:55
 * @修改人：
 * @修改时间：2018/1/10 0010 17:55
 * @修改备注：
 */

public class RxjavaLiveDateActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_rxjava_livedata_get_news)
    Button btnRxjavaLivedataGetNews;
    @BindView(R.id.rv_rxjava_livedata_news)
    RecyclerView rvRxjavaLivedataNews;

    private NewsAdapter mAdapter;

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    protected void getExtras(Intent intent) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_rxjava_livedata;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        btnRxjavaLivedataGetNews.setOnClickListener(this);
        rvRxjavaLivedataNews.setLayoutManager(new LinearLayoutManager(this));
        if (null == mAdapter) {
            mAdapter = new NewsAdapter(this);
            rvRxjavaLivedataNews.setAdapter(mAdapter);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rxjava_livedata_get_news:
                getNews1();
//                getNews2();
                break;
            default:
                break;
        }
    }

    private void getNews1() {
        ProfileViewModel model = new ProfileViewModel();
        model.getNews().observeForever(new Observer<List<NewsBean>>() {
            @Override
            public void onChanged(@Nullable List<NewsBean> beans) {
                mAdapter.addAll(beans);
            }
        });
    }

    private void getNews2() {
        ProfileViewModel model = new ProfileViewModel();
        model.getNews2().observeForever(new Observer<List<NewsBean>>() {
            @Override
            public void onChanged(@Nullable List<NewsBean> beans) {
                if (null != beans && beans.size() > 0) {
                    mAdapter.addAll(beans);
                } else {
                    ToastUtils.showLongToast("数据异常");
                }
            }
        });
    }
}
