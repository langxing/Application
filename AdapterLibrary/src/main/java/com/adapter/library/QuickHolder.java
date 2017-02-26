package com.adapter.library;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/2.
 */

public class QuickHolder extends RecyclerView.ViewHolder {
    private QuickAdapter.OnItemClickListener mOnItemClickListener;
    private SparseArray<View> itemViews = new SparseArray<>();
    private SparseArray<View> views = new SparseArray<>();
    private int commonLayoutId;
    public <T extends View> T findView(@IdRes @NonNull int id) {
        T t = (T) views.get(id);
        if(t == null) {
            t = findView(id, commonLayoutId);
            views.put(id, t);
        }
       return t;
    }

    public <T extends View> T findView(@IdRes @NonNull int id, @LayoutRes int rId) {
        T t = (T) views.get(id);
        if(t == null) {
            if(getItemView(rId) == null) {
                return null;
            }
            t = (T) getItemView(rId).findViewById(id);
            views.put(id, t);
        }
       return t;
    }

    public QuickHolder(final View itemView, QuickAdapter.OnItemClickListener onItemClickListener, @LayoutRes int rId, @LayoutRes int cId) {
        super(itemView);
        this.mOnItemClickListener = onItemClickListener;
        commonLayoutId = cId;
        itemViews.put(rId, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    /**
                     * 这里需要注意一下，如果有添加header的情况下，要把position - 1
                     */
                    mOnItemClickListener.onClick(itemView, getAdapterPosition());
                }
            }
        });
    }

    public View getItemView(@LayoutRes int rId) {
        return itemViews.get(rId);
    }

    public QuickHolder setText(@IdRes int id, String text) {
        setText(id, commonLayoutId, text);
        return this;
    }

    public QuickHolder setText(@IdRes int id, @LayoutRes int layoutId, String text) {
        TextView textView = (TextView) views.get(id);
        if(textView == null) {
            textView = (TextView) getItemView(layoutId).findViewById(id);
            views.put(id, textView);
        }
        textView.setText(text);
        return this;
    }

    public QuickHolder setImage(@IdRes int id, @LayoutRes int layoutId, @DrawableRes int res) {
        ImageView imageView = (ImageView) views.get(id);
        if(imageView == null) {
            imageView = findView(id, layoutId);
            views.put(id, imageView);
        }
        imageView.setImageResource(res);
        return this;
    }
}
