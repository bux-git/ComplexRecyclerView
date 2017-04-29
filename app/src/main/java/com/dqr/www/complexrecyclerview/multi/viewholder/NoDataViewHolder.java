package com.dqr.www.complexrecyclerview.multi.viewholder;

import android.view.View;

import com.dqr.www.complexrecyclerview.multi.itembean.NoDataItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;
import com.dqr.www.complexrecyclerview.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-13 15:40
 */

public class NoDataViewHolder extends MulViewHolder<NoDataItem> {

    public NoDataViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(NoDataItem data, int position, MulAdapter adapter) {
        getView(R.id.tv_no_data).setBackgroundColor(data.bgColor);
        setText(R.id.tv_no_data, data.tvStr);
        setTextColor(R.id.tv_no_data, data.textColor);
    }
}
