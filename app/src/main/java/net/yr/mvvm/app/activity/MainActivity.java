package net.yr.mvvm.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;

import net.yr.mvvm.app.base.BaseActivity;
import net.yr.mvvm.app.databinding.ActivityMainBinding;
import net.yr.mvvm.app.model.User;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private User user;
    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    user.setName(String.valueOf(user.getAge() * Math.PI));
                    user.setAge(user.getAge() + 1);
                    binding.mPeakView.init();
                    mHandler.sendEmptyMessageDelayed(1, 2000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setTitle("主页");
        setContentView(binding.getRoot());
        user = new User();
        user.setName("c");
        user.setAge(11);
        binding.setUser(user);
        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void mHandDoSomeThing(Message msg) {

    }
}
