package com.dqr.www.complexrecyclerview.multi.muladapter;

import android.view.View;

import com.dqr.www.complexrecyclerview.multi.itembean.AlbumItem;
import com.dqr.www.complexrecyclerview.multi.itembean.ExperienceItem;
import com.dqr.www.complexrecyclerview.multi.itembean.MainHeadItem;
import com.dqr.www.complexrecyclerview.multi.itembean.NoDataItem;
import com.dqr.www.complexrecyclerview.multi.itembean.ProductItem;
import com.dqr.www.complexrecyclerview.multi.itembean.RecruitItem;
import com.dqr.www.complexrecyclerview.multi.itembean.TextDetailsItem;
import com.dqr.www.complexrecyclerview.multi.itembean.TextImageItem;
import com.dqr.www.complexrecyclerview.multi.itembean.TitleItem;
import com.dqr.www.complexrecyclerview.multi.itembean.VideoItem;


/**
 * Description：
 * Author：LiuYM
 * Date： 2017-03-31 10:09
 */

public interface TypeFactory {

    int type(MainHeadItem headItem);
    int type(TitleItem titleItem);
    int type(NoDataItem noDataItem);
    int type(TextDetailsItem textDetailsItem);
    int type(TextImageItem textImageItem);
    int type(AlbumItem albumItem);
    int type(VideoItem videoItem);
    int type(ProductItem productItem);
    int type(ExperienceItem experienceItem);
    int type(RecruitItem recruitItem);

    MulViewHolder createViewHolder(int viewType, View itemView);
}
