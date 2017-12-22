package com.xp.coordinator.coordinatortest.mvp;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.commonlib.entity.BaseResultEntity;
import com.xp.coordinator.coordinatortest.R;
import com.xp.coordinator.coordinatortest.mvp.adapter.HomeAndroidAdapter;
import com.xp.coordinator.coordinatortest.mvp.contract.HomeContract;
import com.xp.coordinator.coordinatortest.mvp.contract.HomeModel;
import com.xp.coordinator.coordinatortest.mvp.contract.HomePresenter;
import com.xp.coordinator.coordinatortest.mvp.entity.HomeAndroidEntity;
import com.xp.coordinator.coordinatortest.mvp.widget.DividerGridItemDecoration;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @类描述：首页activity
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/20 0020 17:36
 * @修改人：
 * @修改时间：2017/12/20 0020 17:36
 * @修改备注：
 */

public class HomeActivity extends MvpActivity<HomePresenter, HomeModel> implements HomeContract.IHomeView {
    @BindView(R.id.rv_home_data)
    RecyclerView rvHomeData;
    @BindView(R.id.srl_home_layout)
    SwipeRefreshLayout srlHomeLayout;
    @BindColor(R.color.colorAccent)
    int colorAccent;
    @BindColor(R.color.colorPrimary)
    int colorPrimary;
    @BindColor(R.color.colorPrimaryDark)
    int colorPrimaryDark;

    private int PAGE_SIZE = 5;
    private int PAGE_NUM = 1;
    private HomeAndroidAdapter mAdapter;

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
        return R.layout.activity_home;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        srlHomeLayout.setColorSchemeColors(colorAccent, colorPrimary, colorPrimaryDark);
        srlHomeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                PAGE_NUM++;
                requestHomeData();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rvHomeData.addItemDecoration(new DividerGridItemDecoration(this));
        rvHomeData.setLayoutManager(gridLayoutManager);
        if (null == mAdapter) {
            mAdapter = new HomeAndroidAdapter(this, null);
        }
        rvHomeData.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        requestHomeData();
    }

    private void requestHomeData() {
        mPresenter.requestData(PAGE_NUM, PAGE_SIZE);
    }

    @Override
    public void showLoading() {
        srlHomeLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srlHomeLayout.setRefreshing(false);
    }

    @Override
    public void showError(BaseResultEntity entity) {

    }

    @Override
    public void setHomeData(List<HomeAndroidEntity.TypeAndroidEntity> datas) {
        hideLoading();
        mAdapter.addAll(datas);
    }
}
