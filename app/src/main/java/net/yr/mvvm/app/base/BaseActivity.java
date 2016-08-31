package net.yr.mvvm.app.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.yr.mvvm.app.R;
import net.yr.mvvm.app.util.CheckEvent;
import net.yr.mvvm.app.util.ViewHolder;

import org.greenrobot.eventbus.EventBus;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/8/26 14:25
 */
public abstract class BaseActivity extends AppCompatActivity {
    public ViewHolder mViewHolder;
    private LinearLayout mRootView;
    private View mContentView;
    private boolean isRegisterEventBus, isLoad;
    private long lastClickTime;
    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mHandDoSomeThing(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_activity_layout);
        initRootView();
    }

    private void initRootView() {
        mRootView = (LinearLayout) findViewById(R.id.lltRootMain);
        mViewHolder = new ViewHolder(mRootView);
        findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setContentView(int mRedid) {
        setEmptyGroup();
        mContentView = LayoutInflater.from(this).inflate(mRedid, null);
        mRootView.addView(mContentView);
    }

    public void setContentView(View view) {
        setEmptyGroup();
        mContentView = view;
        mRootView.addView(view);
    }

    private void setEmptyGroup() {
        if (mRootView.getChildCount() > 1) {
            for (int i = 1; i < mRootView.getChildCount(); i++) {
                mRootView.removeViewAt(i);
            }
        }
    }

    public void setTitle(String title) {
        mViewHolder.setText(R.id.tv_title, title);
    }

    public void setTitleBarGone() {
        mViewHolder.setVisibility(R.id.rlt_title, View.GONE);
    }

    public void setTitleBarView(View view) {
        mRootView.removeViewAt(0);
        mRootView.addView(view, 0);
    }

    public View getRootView() {
        return mRootView;
    }

    public void setEventBus() {
        if (!isRegisterEventBus) {
            isRegisterEventBus = true;
            EventBus.getDefault().register(this);
        }
    }

    public void sendEventBusMessage(String messsage) {
        EventBus.getDefault().post(new CheckEvent(messsage));
    }

    public Button getRightButton() {
        return mViewHolder.getView(R.id.btnRight);
    }

    public ImageView getRightImageViewButton() {
        return mViewHolder.getView(R.id.ivRight);
    }

    public ImageView getLeftClose() {
        return mViewHolder.getView(R.id.bt_close);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isLoad) {
            isLoad = true;
            loadData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {// 防止快速点击
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= 300) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    public abstract void loadData();

    public abstract void mHandDoSomeThing(Message msg);
}
