package com.xp.coordinator.coordinatortest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xp.coordinator.coordinatortest.livedata.NameActivity;
import com.xp.coordinator.coordinatortest.mvp.HomeActivity;
import com.xp.coordinator.coordinatortest.rxjava2.TestRxjava2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_home_activity)
    Button btnHomeActivity;
    @BindView(R.id.btn_name_activity)
    Button btnNameActivity;
    @BindView(R.id.btn_rxjava2_activity)
    Button btnRxjavaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        btnHomeActivity.setOnClickListener(this);
        btnNameActivity.setOnClickListener(this);
        btnRxjavaActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_home_activity:
                intent.setClass(this, HomeActivity.class);
                break;
            case R.id.btn_name_activity:
                intent.setClass(this, NameActivity.class);
                break;
            case R.id.btn_rxjava2_activity:
                intent.setClass(this, TestRxjava2Activity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
