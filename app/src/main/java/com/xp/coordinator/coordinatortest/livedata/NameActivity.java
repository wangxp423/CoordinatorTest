package com.xp.coordinator.coordinatortest.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.commonlib.base.BaseActivity;
import com.xp.coordinator.coordinatortest.R;
import com.xp.coordinator.coordinatortest.livedata.adapter.NewsAdapter;
import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;
import com.xp.coordinator.coordinatortest.livedata.repos.InfoRepository;
import com.xp.coordinator.coordinatortest.livedata.viewmodel.NameViewModel;
import com.xp.coordinator.coordinatortest.livedata.viewmodel.ProfileViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/25 0025 16:22
 * @修改人：
 * @修改时间：2017/12/25 0025 16:22
 * @修改备注：
 */

public class NameActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_get_name)
    Button btnGetName;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_get_news)
    Button btnGetNews;
    @BindView(R.id.rv_news)
    RecyclerView rvListNews;

    private NameViewModel mModel;
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
        return R.layout.activity_name;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        btnGetName.setOnClickListener(this);
        btnGetNews.setOnClickListener(this);
        rvListNews.setLayoutManager(new LinearLayoutManager(this));
        if (null == mAdapter) {
            mAdapter = new NewsAdapter(this);
            rvListNews.setAdapter(mAdapter);
        }
    }

    @Override
    protected void initData() {
        mModel = ViewModelProviders.of(this).get(NameViewModel.class); //此方法只有在默认构造函数下可用
        mModel.getmCurrentName().observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvName.setText(s);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_name:
                String anotherName = "wangxp";
                mModel.getmCurrentName().setValue(anotherName);
                break;
            case R.id.btn_get_news:
                getNewsData();
//                updateNewsData();
                break;
            default:
                break;
        }
    }

    private void getNewsData() {
        ProfileViewModel model = new ProfileViewModel(new InfoRepository());
        model.getInfos().observeForever(new Observer<List<NewsBean>>() {
            @Override
            public void onChanged(@Nullable List<NewsBean> beans) {
                mAdapter.addAll(beans);
            }
        });
    }

    private void updateNewsData() {
        ProfileViewModel model = new ProfileViewModel(new InfoRepository());
        model.updateInfoBeans().observeForever(new Observer<List<NewsBean>>() {
            @Override
            public void onChanged(@Nullable List<NewsBean> beans) {
                mAdapter.addAll(beans);
            }
        });
    }
}
