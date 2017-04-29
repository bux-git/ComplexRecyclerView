package com.dqr.www.complexrecyclerview.multi.itembean;


import com.dqr.www.complexrecyclerview.multi.muladapter.TypeFactory;
import com.dqr.www.complexrecyclerview.multi.muladapter.Visitable;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-17 14:05
 */

public class ExperienceItem extends Attr implements Visitable {

    public String fileName;
    public String value1;
    public String value2;
    public String value3;

    public ExperienceItem(boolean isModel, int titlePosition, int bgModeColorResId) {
        super(isModel, titlePosition, bgModeColorResId);
    }


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
