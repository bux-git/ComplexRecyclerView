package com.dqr.www.complexrecyclerview.multi.viewholder;

import android.view.View;

import com.dqr.www.complexrecyclerview.R;
import com.dqr.www.complexrecyclerview.multi.itembean.RecruitItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;
import com.dqr.www.complexrecyclerview.widget.CollapseView;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-17 16:29
 */

public class RecruitViewHolder extends MulViewHolder<RecruitItem> {

    public RecruitViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(RecruitItem data, int position, MulAdapter adapter) {
        CollapseView cv=getView(R.id.cv_container);
        cv.setTvTop(data.job);
        cv.setTvRight(data.wages);
        cv.setTvLeft(data.place);
        cv.setTvDesc(data.desc);
        cv.setTvContent(data.content);
    }
}
