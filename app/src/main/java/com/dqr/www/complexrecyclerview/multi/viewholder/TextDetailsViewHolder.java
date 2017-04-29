package com.dqr.www.complexrecyclerview.multi.viewholder;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.dqr.www.complexrecyclerview.multi.itembean.TextDetailsItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;

import com.dqr.www.complexrecyclerview.R;
/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-13 16:43
 */

public class TextDetailsViewHolder extends MulViewHolder<TextDetailsItem> {

    public TextDetailsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final TextDetailsItem data, final int position, final MulAdapter adapter) {
        getView(R.id.rlt_container).setBackgroundColor(data.bgColor);

        setTextColor(R.id.tv_fieldname, data.textColor);
        setTextColor(R.id.tv_fieldvalue, data.textColor);

        setText(R.id.tv_fieldname, Html.fromHtml(data.fileName));
        setText(R.id.tv_fieldvalue, Html.fromHtml(data.fileValue));

        ImageView imageView = getView(R.id.iv_icon);
        if (data.iv_right > 0) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(data.iv_right);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapter.mOnECardItemClickListener != null) {
                        adapter.mOnECardItemClickListener.onECardCallPhoneList(data.titlePosition, position, data.fileValue);
                    }
                }
            });
        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}
