package com.dqr.www.complexrecyclerview.multi.muladapter;

import android.view.View;

import com.dqr.www.complexrecyclerview.R;
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
import com.dqr.www.complexrecyclerview.multi.viewholder.AlbumViewHolder;
import com.dqr.www.complexrecyclerview.multi.viewholder.ExperienceViewHolder;
import com.dqr.www.complexrecyclerview.multi.viewholder.HeadViewHolder;
import com.dqr.www.complexrecyclerview.multi.viewholder.NoDataViewHolder;
import com.dqr.www.complexrecyclerview.multi.viewholder.ProductViewHolder;
import com.dqr.www.complexrecyclerview.multi.viewholder.RecruitViewHolder;
import com.dqr.www.complexrecyclerview.multi.viewholder.TextDetailsViewHolder;
import com.dqr.www.complexrecyclerview.multi.viewholder.TextImageViewHolder;
import com.dqr.www.complexrecyclerview.multi.viewholder.TitleViewHolder;
import com.dqr.www.complexrecyclerview.multi.viewholder.VideoViewHolder;


/**
 * Description：
 * Author：LiuYM
 * Date： 2017-03-31 10:13
 */

public class TypeFactoryList implements TypeFactory {

    // 头部第一项
   private static final int TYPE_HEAD_ITEM = R.layout.making_card_edit_head_item_layout;
    //每一项标题栏
    private static final int TYPE_TITLE_ITEM=R.layout.e_card_edit_home_title_item_layout;
    //无数据时子项
    private static final int TYPE_ITEM_NO_DATA=R.layout.e_card_edit_home_item_no_data_layout;
    //个人信息文字说明项
    private static final int TYPE_PERSONAL_ITEM=R.layout.ecard_edit_home_personal_item_layout;
    //图文混排 上面文字 下面图片
    private static final int TYPE_TEXT_IMAGE=R.layout.ecard_edit_home_text_image_layout;
    //相册
    private static final int TYPE_ALBUM_ITEM=R.layout.ecard_edit_home_album_layout;
    //视频
    private static final int TYPE_VIDEO_ITEM=R.layout.ecard_edit_home_video_layout;
    //业务案例
    private static final int TYPE_COMPANY_PRODUCT=R.layout.ecard_edit_home_compay_product_layout;
    //教育经历 工作经历exerience
    private static final int TYPE_EXPERIENCE=R.layout.ecard_edit_home_item_experience_layout;
    //招聘信息
    private static final int TYPE_RECRUIT=R.layout.ecard_edit_home_item_recruit_layout;
    //企业产品 暂未使用
    private static final int TYPE_COMPANY_CP=R.layout.ecard_edit_home_company_cp;


    @Override
    public int type(MainHeadItem headItem) {
        return TYPE_HEAD_ITEM;
    }

    @Override
    public int type(TitleItem titleItem) {
        return TYPE_TITLE_ITEM;
    }

    @Override
    public int type(NoDataItem noDataItem) {
        return TYPE_ITEM_NO_DATA;
    }

    @Override
    public int type(TextDetailsItem textDetailsItem) {
        return TYPE_PERSONAL_ITEM;
    }

    @Override
    public int type(TextImageItem textImageItem) {
        return TYPE_TEXT_IMAGE;
    }

    @Override
    public int type(AlbumItem albumItem) {
        return TYPE_ALBUM_ITEM;
    }

    @Override
    public int type(VideoItem videoItem) {
        return TYPE_VIDEO_ITEM;
    }

    @Override
    public int type(ProductItem productItem) {
        return TYPE_COMPANY_PRODUCT;
    }

    @Override
    public int type(ExperienceItem experienceItem) {
        return TYPE_EXPERIENCE;
    }

    @Override
    public int type(RecruitItem recruitItem) {
        return TYPE_RECRUIT;
    }

    @Override
    public MulViewHolder createViewHolder(int viewType, View itemView) {
        if (viewType ==TYPE_HEAD_ITEM) {
            return new HeadViewHolder(itemView);
        }else if (viewType ==TYPE_TITLE_ITEM) {
            return new TitleViewHolder(itemView);
        }else if (viewType ==TYPE_ITEM_NO_DATA) {
            return new NoDataViewHolder(itemView);
        }else if(viewType==TYPE_PERSONAL_ITEM){
            return new TextDetailsViewHolder(itemView);
        }else if(viewType==TYPE_TEXT_IMAGE){
            return new TextImageViewHolder(itemView);
        }else if(viewType==TYPE_ALBUM_ITEM){
            return new AlbumViewHolder(itemView);
        }else if(viewType==TYPE_VIDEO_ITEM){
            return new VideoViewHolder(itemView);
        }else if(viewType==TYPE_COMPANY_PRODUCT){
            return new ProductViewHolder(itemView);
        }else if(viewType==TYPE_EXPERIENCE){
            return new ExperienceViewHolder(itemView);
        }else if(viewType==TYPE_RECRUIT){
            return new RecruitViewHolder(itemView);
        }
        return null;
    }


}
