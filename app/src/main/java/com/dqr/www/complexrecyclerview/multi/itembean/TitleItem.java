package com.dqr.www.complexrecyclerview.multi.itembean;


import com.dqr.www.complexrecyclerview.multi.muladapter.TypeFactory;
import com.dqr.www.complexrecyclerview.multi.muladapter.Visitable;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-13 11:08
 */

public class TitleItem extends Attr implements Visitable {

    public int icLeft;
    public int icRight;
    public int title;

    public TitleItem(boolean model, int titlePosition
            ,int bgModeColorResId
            ,int modelLeftIcoResId
            ,int modelRightIcoResId
            ,int normalLeftIcoResId
            ,int normalRightIcoResId) {
        super(model, titlePosition, bgModeColorResId);

        if (isModel) {
            icLeft = modelLeftIcoResId;
            icRight = modelRightIcoResId;
        } else {
            icLeft = normalLeftIcoResId;
            icRight = normalRightIcoResId;
        }
    }


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
