package net.yr.mvvm.app.base;


import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import net.yr.mvvm.app.util.ViewHolder;


/**
 * 适配器基类
 *
 * @param <T>
 * @author longluliu
 *         Create at: 2015-4-11 下午3:40:32
 * @Filename: MyBaseAdapter.java
 * @Description: TODO
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 */
public abstract class MyBaseAdapter<T> extends ArrayAdapter<T> implements AdapterView.OnItemClickListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<T> mDatas;
    private int mItemLayoutId;

    public MyBaseAdapter(AbsListView mListView, Context context, List<T> datas, int itemLayoutId) {
        super(context, 0, datas);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mDatas = datas;
        mItemLayoutId = itemLayoutId;
        mListView.setOnItemClickListener(this);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        if (mDatas.size() > position) {
            return mDatas.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder(mInflater.inflate(mItemLayoutId, parent, false));
            convertView = viewHolder.getmRootView();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        convert(viewHolder, getItem(position), mDatas, position);
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MyonItemClick(parent, view, getItem(position), mDatas, position, id);
    }

    protected abstract void convert(ViewHolder holder, T item, List<T> list, int position);

    protected abstract void MyonItemClick(AdapterView<?> parent, View view, T item, List<T> list, int position, long id);
}
