package com.dqr.www.complexrecyclerview.multi.viewholder;

import android.view.View;

import com.dqr.www.complexrecyclerview.R;
import com.dqr.www.complexrecyclerview.multi.itembean.TitleItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-13 11:10
 */

public class TitleViewHolder extends MulViewHolder<TitleItem> {

    public TitleViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final TitleItem data, final int position, final MulAdapter adapter) {
        getView(R.id.rlt_container).setBackgroundColor(data.bgColor);
        setImageResource(R.id.iv_left, data.icLeft);
        setTextColor(R.id.tv_title_name, data.textColor);
        setText(R.id.tv_title_name, data.title);

        if(data.isSelfCard){//自己的名片
            getView(R.id.title_driver).setVisibility(View.VISIBLE);
            getView(R.id.iv_right).setVisibility(View.VISIBLE);
            setImageResource(R.id.iv_right, data.icRight);
            getView(R.id.iv_right).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapter.mOnECardItemClickListener != null) {
                        adapter.mOnECardItemClickListener.onECardTitleItemClickLister(data.titlePosition, position);
                    }
                }
            });
        }else{//别人的名片
            getView(R.id.iv_right).setVisibility(View.GONE);
            if(position==1) {//查看别人第一项头部分割线不需要
                getView(R.id.title_driver).setVisibility(View.GONE);
            }
        }


    }
}
