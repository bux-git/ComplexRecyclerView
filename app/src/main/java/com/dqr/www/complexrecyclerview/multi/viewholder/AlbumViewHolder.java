package com.dqr.www.complexrecyclerview.multi.viewholder;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dqr.www.complexrecyclerview.R;
import com.dqr.www.complexrecyclerview.multi.itembean.AlbumItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.BaseRecyclerAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;
import com.dqr.www.complexrecyclerview.multi.muladapter.RecyclerHolder;


/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-13 19:16
 */

public class AlbumViewHolder extends MulViewHolder<AlbumItem> {

    public AlbumViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final AlbumItem data, final int position, final MulAdapter adapter) {
        RecyclerView recyclerView = getView(R.id.rl_content);
        recyclerView.setBackgroundColor(data.bgColor);
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));

        BaseRecyclerAdapter recyclerAdapter=new BaseRecyclerAdapter<String>(recyclerView, data.imgUrls, R.layout.ecard_edit_home_album_list_item_layout) {
            @Override
            public void convert(RecyclerHolder holder, final String item, final int position, boolean isScrolling) {
                ImageView imageView = holder.getView(R.id.iv_img);
                holder.setImageResource(R.id.iv_img, item);
            }

        };
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object imgUrl, int position) {
                if (adapter.mOnECardItemClickListener != null) {
                    adapter.mOnECardItemClickListener.onECardImageClickListener(data.titlePosition, position, (String) imgUrl);
                }
            }
        });

    }
}
