package com.dqr.www.complexrecyclerview.multi.viewholder;

import android.text.Html;
import android.view.View;

import com.dqr.www.complexrecyclerview.R;
import com.dqr.www.complexrecyclerview.multi.itembean.ExperienceItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;


/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-17 14:07
 */

public class ExperienceViewHolder extends MulViewHolder<ExperienceItem> {

    public ExperienceViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(ExperienceItem data, int position,MulAdapter adapter) {
        getView(R.id.rlt_container).setBackgroundColor(data.bgColor);
        if (data.isModel) {//模版的时候使用配置颜色 否则使用layout中颜色
            setTextColor(R.id.tv_file_name, data.textColor);
            setTextColor(R.id.tv_field1, data.textColor);
            setTextColor(R.id.tv_field2, data.textColor);
            setTextColor(R.id.tv_field3, data.textColor);
        } else {
            setTextColor(R.id.tv_file_name, R.color.eCard_edit_textColor);
            setTextColor(R.id.tv_field1, R.color.eCard_edit_textColor9);
            setTextColor(R.id.tv_field2, data.textColor);
            setTextColor(R.id.tv_field3, R.color.eCard_edit_textColor9);
        }

        setText(R.id.tv_file_name, Html.fromHtml(data.fileName));
        setText(R.id.tv_field1, Html.fromHtml(data.value1));
        setText(R.id.tv_field2, Html.fromHtml(data.value2));
        setText(R.id.tv_field3, Html.fromHtml(data.value3));
    }
}
