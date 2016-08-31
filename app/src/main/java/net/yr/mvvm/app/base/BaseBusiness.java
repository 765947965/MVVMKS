package net.yr.mvvm.app.base;

import android.os.Handler;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/8/31 16:26
 */
public class BaseBusiness {
    private Object mTag;
    private Handler mHandler;

    public BaseBusiness(Object mTag, Handler mHandler) {
        this.mTag = mTag;
        this.mHandler = mHandler;
    }

}
