package net.yr.mvvm.app.util;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/4/25 10:58
 */
public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views = new SparseArray<>();

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public TextView setText(int resource, CharSequence mCharSequence) {
        TextView mTextView = getView(resource);
        mTextView.setText(mCharSequence);
        return mTextView;
    }

    public void setImage(int resource, int resourceImage) {
        ((ImageView) getView(resource)).setImageResource(resourceImage);
    }

    public <T extends View> T setVisibility(int resource, int visibility) {
        View view = getView(resource);
        view.setVisibility(visibility);
        return (T) view;
    }
}
