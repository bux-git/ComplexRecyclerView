package com.dqr.www.complexrecyclerview.multi.viewholder;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dqr.www.complexrecyclerview.multi.itembean.TextImageItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;
import com.dqr.www.complexrecyclerview.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-13 17:48
 */

public class TextImageViewHolder extends MulViewHolder<TextImageItem> {

    public TextImageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final TextImageItem data, final int position, final MulAdapter adapter) {
        getView(R.id.llt_container).setBackgroundColor(data.bgColor);

        TextView textView=getView(R.id.tv_desc);
        if(TextUtils.isEmpty(data.descStr)){
            textView.setVisibility(View.GONE);

        }else{
            textView.setVisibility(View.VISIBLE);
            setTextColor(R.id.tv_desc, data.textColor);
            setText(R.id.tv_desc, Html.fromHtml(data.descStr));
        }

        setImageResource(R.id.iv_img, data.imgUrl);

        getView(R.id.iv_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.mOnECardItemClickListener != null) {
                    adapter.mOnECardItemClickListener.onECardImageClickListener(data.titlePosition, position, data.imgUrl);
                }
            }
        });
    }
}
