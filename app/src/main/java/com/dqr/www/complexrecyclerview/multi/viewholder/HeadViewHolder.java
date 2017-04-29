package com.dqr.www.complexrecyclerview.multi.viewholder;

import android.view.View;

import com.dqr.www.complexrecyclerview.R;
import com.dqr.www.complexrecyclerview.multi.itembean.MainHeadItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;


/**
 * Description：编辑首页 头部ViewHolder
 * Author：LiuYM
 * Date： 2017-04-13 9:49
 */

public class HeadViewHolder extends MulViewHolder<MainHeadItem> {

    public HeadViewHolder(View itemView) {
        super(itemView);
    }

    public HeadViewHolder(View itemView, int count) {
        super(itemView, count);
    }

    @Override
    public void setUpView(final MainHeadItem data, int position, final MulAdapter adapter) {

        int cardTypeResId = R.id.tv_card_type;//名片类型
        int bgImgResId = R.id.iv_cover;//头部背景
        final int eyeResId = R.id.iv_eye;//右下角是否上锁

        int cardNameResId = R.id.tv_card_name;//名片名称
        int scanCodeResId = R.id.iv_scan_code;//二维码
        int editCardNameResId = R.id.iv_edit_card_name;//编辑名片名字

        int handleResId = R.id.llt_handle;//操作 父布局
        int settingResId = R.id.tv_setting;//设置
        int shareResId = R.id.tv_share;//分享
        int prevResId = R.id.tv_prev;//预览

        getView(R.id.llt_container).setBackgroundColor(data.bgColor);
        setText(cardTypeResId, data.cardType);
        setText(cardNameResId, data.cardName);
        setImageResource(bgImgResId, data.carBgImg);



        if (data.isSelfCard) {//查看自己名片`
            getView(eyeResId).setVisibility(View.VISIBLE);
            getView(handleResId).setVisibility(View.VISIBLE);
            getView(editCardNameResId).setVisibility(View.VISIBLE);

            //点击背景 触发修改背景
            getView(bgImgResId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.mOnECardItemClickListener.onECardEditBgImg();
                }
            });

            //点击名称编辑名字
            getView(editCardNameResId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.mOnECardItemClickListener.onECardEditName(data.cardName);
                }
            });

            if (data.isLock) {
                setImageResource(eyeResId, R.drawable.ic_making_card_edit_lock);
            } else {
                setImageResource(eyeResId, R.drawable.ic_making_card_edit_eye);
            }
            //处理是否对外开放
           /* getView(eyeResId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.isLock) {
                        data.isLock = false;
                        setImageResource(eyeResId, R.drawable.ic_making_card_edit_eye);
                    } else {
                        data.isLock = true;
                        setImageResource(eyeResId, R.drawable.ic_making_card_edit_lock);
                    }
                    adapter.mOnECardItemClickListener.onLockClick();
                }
            });*/

            getView(settingResId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.mOnECardItemClickListener.onECardSetting();
                }
            });

            getView(shareResId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.mOnECardItemClickListener.onECardShare();
                }
            });

            getView(prevResId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.mOnECardItemClickListener.onECardPrev();
                }
            });

            getView(scanCodeResId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.mOnECardItemClickListener.onECardScanClickListener();
                }
            });

        } else {
            getView(eyeResId).setVisibility(View.GONE);
            getView(handleResId).setVisibility(View.GONE);
            getView(editCardNameResId).setVisibility(View.GONE);
            getView(bgImgResId).setOnClickListener(null);
        }

    }
}
