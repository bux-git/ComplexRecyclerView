package com.dqr.www.complexrecyclerview.multi.itembean;


import com.dqr.www.complexrecyclerview.multi.muladapter.TypeFactory;
import com.dqr.www.complexrecyclerview.multi.muladapter.Visitable;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-14 11:25
 */

public class ProductItem extends Attr implements Visitable {

    public String name;
    public String dateStr;
    public String companyName;
    public boolean isTitle;//是否标题
    public int nameBgColor;//第一项背景色

    public ProductItem(boolean isModel, int titlePosition, int bgModeColorResId) {
        super(isModel, titlePosition, bgModeColorResId);
    }


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
