package com.dqr.www.complexrecyclerview.multi.itembean;


import com.dqr.www.complexrecyclerview.multi.muladapter.TypeFactory;
import com.dqr.www.complexrecyclerview.multi.muladapter.Visitable;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-17 16:24
 */

public class RecruitItem extends Attr implements Visitable {

    public String job;//职位
    public String wages;//工资
    public String place;//地址 人数描述
    public String desc;//职位信息 标题
    public String content;//招聘要求详情

    public RecruitItem(boolean isModel, int titlePosition, int bgModeColorResId) {
        super(isModel, titlePosition, bgModeColorResId);
    }


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
