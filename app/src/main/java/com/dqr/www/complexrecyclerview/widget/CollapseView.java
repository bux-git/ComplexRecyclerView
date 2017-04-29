package com.dqr.www.complexrecyclerview.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dqr.www.complexrecyclerview.R;

public class CollapseView extends LinearLayout {

    private static final String TAG = "CollapseView";
    public TextView mTvTop;
    public TextView mTvRight;
    public TextView mTvLeft;
    public TextView mTvDesc;
    public TextView mTvContent;
    private ImageView mIvBottom;
    private LinearLayout mRlContent;
    private LinearLayout mLltContainer;

    private int duration = 200;


    int widthMeasureSpec;
    int heightMeasureSpec;

    public CollapseView(Context context) {
        super(context);
        initView(context);
    }

    public CollapseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CollapseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CollapseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context) {
        inflate(context, R.layout.collapse_view_layout, this);
        mTvTop = (TextView) findViewById(R.id.tv_top);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mTvLeft = (TextView) findViewById(R.id.tv_left);
        mTvDesc = (TextView) findViewById(R.id.tv_desc);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mIvBottom = (ImageView) findViewById(R.id.iv_bottom);
        mRlContent = (LinearLayout) findViewById(R.id.rl_content);
        mLltContainer = (LinearLayout) findViewById(R.id.rlt_container);
        mRlContent.setVisibility(GONE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;
    }

    public void setTvTextColor(int colorResId){
        mTvTop.setTextColor(getResources().getColor(colorResId));
        mTvRight.setTextColor(getResources().getColor(colorResId));
        mTvLeft.setTextColor(getResources().getColor(colorResId));
        mTvDesc.setTextColor(getResources().getColor(colorResId));
        mTvContent.setTextColor(getResources().getColor(colorResId));
    }

    public void setImgArrowBottomResId(int arrowResId){
        mIvBottom.setImageResource(arrowResId);
    }

    public void setTvLeft(String str) {
        mTvLeft.setText(str);
    }

    public void setTvRight(String str) {
        mTvRight.setText(str);
    }

    public void setTvTop(String str) {
        mTvTop.setText(str);
    }

    public void setTvDesc(String str) {

        mTvDesc.setText(str);
    }

    public void setTvContent(String str) {
        if (TextUtils.isEmpty(str)) {
            mIvBottom.setVisibility(INVISIBLE);
            mLltContainer.setOnClickListener(null);
        } else {
            mTvContent.setText(str);
            mIvBottom.setVisibility(VISIBLE);
            mLltContainer.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    rotateArrow();
                }
            });

        }

    }


    private void rotateArrow() {
        int degree = 0;
        if (mRlContent.getVisibility() == View.GONE) {
            degree = 180;
            expend();
        } else {
            degree = 0;
            collapse();
        }

        mIvBottom.animate().setDuration(duration).rotation(degree).start();
    }

    /**
     * 展开
     */
    private void expend() {
        mRlContent.measure(widthMeasureSpec, heightMeasureSpec);
        final int height = mRlContent.getMeasuredHeight();
        mRlContent.getLayoutParams().height = 0;
        mRlContent.setVisibility(VISIBLE);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    mRlContent.getLayoutParams().height = height;

                } else {
                    mRlContent.getLayoutParams().height = (int) (height * interpolatedTime);
                }
                mRlContent.requestLayout();
            }

        };
        animation.setDuration(duration);
        mRlContent.startAnimation(animation);
    }

    /**
     * 折叠
     */
    private void collapse() {
        final int height = mRlContent.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                if (interpolatedTime == 1) {
                    mRlContent.setVisibility(GONE);
                } else {
                    mRlContent.getLayoutParams().height = (int) ((1 - interpolatedTime) * height);
                    mRlContent.requestLayout();
                }
            }
        };
        animation.setDuration(duration);
        mRlContent.startAnimation(animation);
    }
}