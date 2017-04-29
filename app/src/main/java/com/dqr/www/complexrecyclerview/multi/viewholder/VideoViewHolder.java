package com.dqr.www.complexrecyclerview.multi.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.dqr.www.complexrecyclerview.R;
import com.dqr.www.complexrecyclerview.multi.itembean.VideoItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;


/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-14 8:54
 */

public class VideoViewHolder extends MulViewHolder<VideoItem> {
    public VideoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final VideoItem data, int position, MulAdapter adapter) {
        getView(R.id.flt_container).setBackgroundColor(data.bgColor);
        ImageView ivImg = getView(R.id.iv_img);
        setImageResource(R.id.iv_img, data.imgUrl);

        ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
