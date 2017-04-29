package com.dqr.www.complexrecyclerview.multi.itembean;

import android.graphics.Color;

import com.dqr.www.complexrecyclerview.R;


/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-13 13:36
 */

public class Attr {
    public static boolean isSelfCard;//是否是自己的名片 所有名片共用
    public boolean isModel;
    //模版颜色
    public  int mTextModeColorResId = R.color.eCard_white;
    public  int mBgModeColor =0xFFFFFFFF;//白色

    //正常颜色
    public int mTextColorNormal = R.color.eCard_edit_textColor;
    public int mBgModeColorNormal =Color.WHITE;

    public int textColor;//字体颜色
    public int bgColor;//背景颜色

    public int titlePosition;

    public Attr(boolean isModel,int titlePosition,int bgModeColor){
        this.titlePosition=titlePosition;
        this.mBgModeColor=bgModeColor;
        setColors(isModel);
    }

    /**
     * 根据模版 显示模式 设置字体背景颜色
     *
     * @param model
     */
    private void setColors(boolean model) {
        this.isModel=model;
        if(mBgModeColor== Color.WHITE)this.isModel=false;//如果模版是白色的 则做
        if (isModel) {
            textColor = mTextModeColorResId;
            bgColor = mBgModeColor;
        } else {
            textColor = mTextColorNormal;
            bgColor = mBgModeColorNormal;
        }
    }
}
