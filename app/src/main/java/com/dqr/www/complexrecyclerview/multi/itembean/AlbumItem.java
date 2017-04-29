package com.dqr.www.complexrecyclerview.multi.itembean;

import com.dqr.www.complexrecyclerview.multi.muladapter.TypeFactory;
import com.dqr.www.complexrecyclerview.multi.muladapter.Visitable;

import java.util.List;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-13 19:14
 */

public class AlbumItem extends Attr implements Visitable {

    public List<String> imgUrls;

    public AlbumItem(boolean isModel, int titlePosition, int bgModeColorResId) {
        super(isModel, titlePosition, bgModeColorResId);
    }


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
