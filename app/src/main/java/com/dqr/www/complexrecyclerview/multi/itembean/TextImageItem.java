package com.dqr.www.complexrecyclerview.multi.itembean;


import com.dqr.www.complexrecyclerview.multi.muladapter.TypeFactory;
import com.dqr.www.complexrecyclerview.multi.muladapter.Visitable;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-13 17:47
 */

public class TextImageItem extends Attr implements Visitable {

    public String descStr;
    public String imgUrl;

    public TextImageItem(boolean isModel, int titlePosition, int bgModeColorResId) {
        super(isModel, titlePosition, bgModeColorResId);
    }


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
