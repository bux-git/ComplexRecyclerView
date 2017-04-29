package com.dqr.www.complexrecyclerview.multi.muladapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;


/**
 * Description：
 * Author：LiuYM
 * Date： 2017-03-31 10:26
 */

public class MulAdapter extends RecyclerView.Adapter<MulViewHolder> {
    public int width;
    private List<Visitable> mList;
    private TypeFactory mTypeFactory;

    public OnECardItemClickListener mOnECardItemClickListener;

    public void setOnECardItemClickListener(OnECardItemClickListener onECardItemClickListener) {
        mOnECardItemClickListener = onECardItemClickListener;
    }

    public MulAdapter(List<Visitable> list) {
        this.mList = list;
        mTypeFactory = new TypeFactoryList();
    }

    @Override
    public MulViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        width = parent.getContext().getResources().getDisplayMetrics().widthPixels;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return mTypeFactory.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(MulViewHolder holder, int position) {
        holder.setUpView(mList.get(position), position, this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type(mTypeFactory);
    }


    public interface OnECardItemClickListener {
        /**
         * 编辑
         * @param itemType
         * @param position
         */
        void onECardTitleItemClickLister(int itemType, int position);

        /**
         * 图片点击
         * @param itemType
         * @param position
         * @param imgUrl
         */
        void onECardImageClickListener(int itemType, int position, String imgUrl);

        /**
         * 电话号码拨打
         * @param itemType
         * @param position
         * @param phoneNumber
         */
        void onECardCallPhoneList(int itemType, int position, String phoneNumber);

        /**
         * 名片背景编辑
         */
        void onECardEditBgImg();

        /**
         * 名片名称编辑
         * @param name  原名称
         */
        void onECardEditName(String name);

        /**
         * 二维码点击
         */
        void onECardScanClickListener();

        /**
         * 设置点击
         */
        void onECardSetting();

        /**
         * 分享
         */
        void onECardShare();

        /**
         * 预览
         */
        void onECardPrev();

        /**
         * 对外开放
         */
        void onLockClick();


    }

}
