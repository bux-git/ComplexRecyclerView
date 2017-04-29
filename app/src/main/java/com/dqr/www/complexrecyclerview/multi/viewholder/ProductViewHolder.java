package com.dqr.www.complexrecyclerview.multi.viewholder;

import android.view.View;
import android.widget.TextView;

import com.dqr.www.complexrecyclerview.multi.itembean.ProductItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulViewHolder;
import com.dqr.www.complexrecyclerview.R;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-14 11:26
 */

public class ProductViewHolder extends MulViewHolder<ProductItem> {

    public ProductViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(ProductItem data, int position, MulAdapter adapter) {
        getView(R.id.llt_container).setBackgroundColor(data.bgColor);

        TextView tvName = getView(R.id.tv_name);
        TextView tvData = getView(R.id.tv_date);
        TextView tvCompanyName = getView(R.id.tv_companyName);

        tvName.setText(data.name);
        tvData.setText(data.dateStr);
        tvCompanyName.setText(data.companyName);

        setBackgroundResource(R.id.tv_name, data.nameBgColor);
        //背景颜色 字体大小 加粗
        if(data.isTitle){
            getView(R.id.llt_container).setPadding(0,itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.ecard_edit_margin_top_bottom),0,0);
            setTextStyle(R.id.tv_companyName,true);

            setTextSize(R.id.tv_name, R.dimen.ecard_edit_title_size);
            setTextSize(R.id.tv_date, R.dimen.ecard_edit_title_size);
            setTextSize(R.id.tv_companyName, R.dimen.ecard_edit_title_size);

            setTextStyle(R.id.tv_name,true);
            setTextStyle(R.id.tv_date,true);

        }else{
            getView(R.id.llt_container).setPadding(0,0,0,0);
            setTextStyle(R.id.tv_companyName,false);

            setTextSize(R.id.tv_name, R.dimen.ecard_edit_normal_size);
            setTextSize(R.id.tv_date, R.dimen.ecard_edit_normal_size);
            setTextSize(R.id.tv_companyName, R.dimen.ecard_edit_normal_size);

            setTextStyle(R.id.tv_name,false);
            setTextStyle(R.id.tv_date,false);

        }
    }
}
