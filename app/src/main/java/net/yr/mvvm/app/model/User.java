package net.yr.mvvm.app.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import net.yr.mvvm.app.BR;

import java.io.Serializable;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/8/23 16:24
 */
public class User extends BaseObservable implements Serializable {
    private String name;
    private int age;
    private float[] values;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public float[] getValues() {
        return values;
    }

    public void setValues(float[] values) {
        this.values = values;
        notifyPropertyChanged(BR.values);
    }
}
