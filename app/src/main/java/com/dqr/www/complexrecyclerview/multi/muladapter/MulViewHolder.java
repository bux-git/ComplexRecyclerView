package com.dqr.www.complexrecyclerview.multi.muladapter;

import android.view.View;


/**
 * Description：
 * Author：LiuYM
 * Date： 2017-03-31 10:23
 */

public abstract class MulViewHolder<T> extends RecyclerHolder {


    public MulViewHolder(View itemView) {
        super(itemView);
    }

    public MulViewHolder(View itemView, int count) {
        super(itemView, count);
    }

    public abstract void setUpView(T data, int position, MulAdapter adapter);
}
