package net.yr.mvvm.app.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/8/31 15:51
 */
public abstract class BaseFragment extends Fragment {

    public Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mHandDoSomeThing(msg);
        }
    };


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible()) {
            loadData();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    public abstract void mHandDoSomeThing(Message msg);

    public abstract void loadData();
}
