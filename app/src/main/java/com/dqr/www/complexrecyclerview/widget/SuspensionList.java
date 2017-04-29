package com.dqr.www.complexrecyclerview.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dqr.www.complexrecyclerview.R;
import com.dqr.www.complexrecyclerview.multi.muladapter.RecyclerHolder;

import java.util.ArrayList;
import java.util.List;

public class SuspensionList extends LinearLayout {
    private static final String TAG = "SuspensionList";

    private ImageView mIvArrow;
    private RecyclerView mRecyclerView;
    private TextView mTvBottom;
    private LinearLayout mLltContent;

    private List<BaseType> mData;
    private SusAdapter mAdapter;
    private LinearLayoutManager mManager;
    private SusItemOnclickListener mListener;

    public boolean isClickScrolled;

    public void setListener(SusItemOnclickListener listener) {
        mListener = listener;
    }

    public SuspensionList(Context context) {
        super(context);
        init();
    }


    public SuspensionList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SuspensionList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SuspensionList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.making_card_edit_suspension_list, this);
        mIvArrow = (ImageView) findViewById(R.id.iv_arrow);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_content);
        mTvBottom = (TextView) findViewById(R.id.tv_bottom);
        mLltContent = (LinearLayout) findViewById(R.id.llt_content);
        mLltContent.setVisibility(GONE);
        mIvArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inAnima();
            }
        });

        mTvBottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int last = mManager.findLastVisibleItemPosition();
                moveToPosition(mManager, mRecyclerView, last, 0);
            }
        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            float dx;
            float dy;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dx = event.getRawX();
                        dy = event.getRawY();
                        mListener.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int moveX = (int) (event.getRawX() - dx);
                        int moveY = (int) Math.abs(event.getRawY() - dy);
                        if (moveX > 30 && moveY < 100) {
                            outAnima();
                            return true;
                        }
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent event) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


    }

    /**
     * 出场动画
     */
    private void outAnima() {
        Animation recyAnimaLR = AnimationUtils.loadAnimation(getContext(), R.anim.sus_list_left_to_right);
        recyAnimaLR.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLltContent.setVisibility(GONE);
                mIvArrow.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mLltContent.startAnimation(recyAnimaLR);

    }

    /**
     * 出场动画
     */
    private void inAnima() {
        mIvArrow.setVisibility(GONE);
        Animation recyAnimaRL = AnimationUtils.loadAnimation(getContext(), R.anim.sus_list_right_to_left);
        mLltContent.setVisibility(VISIBLE);
        mLltContent.startAnimation(recyAnimaRL);

    }


    public void setData(List<BaseType> data) {
        mData = data;
        if(data==null){
            mData = new ArrayList<>();
            mTvBottom.setVisibility(GONE);
        }
        if (mAdapter == null) {
            mAdapter = new SusAdapter();
            mManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mManager);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


    private void setSelectedItem(int position) {
        for (int i = 0; i < mData.size(); i++) {
            BaseType baseType = mData.get(i);
            if (i == position) {
                baseType.isSelected = true;
            } else {
                baseType.isSelected = false;
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    public void setSelectedByType(int type) {
        int position = -1;
        for (int i = 0; i < mData.size(); i++) {
            BaseType baseType = mData.get(i);
            if (baseType.type == type) {
                baseType.isSelected = true;
                position = i;
            } else {
                baseType.isSelected = false;
            }
        }

        if (position == -1) return;//未找到

        mAdapter.notifyDataSetChanged();
        //显示全部的时候
        moveToPosition(mManager, mRecyclerView, position, 0);

    }


    class SusAdapter extends RecyclerView.Adapter<RecyclerHolder> {

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.suspension_list_item_layout, parent, false);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, final int position) {


            TextView textView = holder.getView(R.id.tv_title);

            final BaseType t = mData.get(position);
            textView.setText(t.titleText);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectedItem(position);
                    if (mListener != null) {
                        mListener.susOnClickListener(t);
                    }
                }
            });
            if (t.isSelected) {
                holder.setTextColor(R.id.tv_title, t.textSelectedColorResId);
            } else {
                holder.setTextColor(R.id.tv_title, t.textNormalColorResId);
            }


            if (position == getItemCount() - 1) {
                holder.getView(R.id.iv_driver).setVisibility(GONE);
            } else {
                holder.getView(R.id.iv_driver).setVisibility(VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return mData.size();

        }

    }


    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     * @param height        要跳转后 额外scrollBy高度 如沉浸式等
     */
    public void moveToPosition(LinearLayoutManager manager, final RecyclerView mRecyclerView, int n, final int height) {

        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            mRecyclerView.scrollToPosition(n);
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.scrollBy(0, -height);

                }
            }, 10);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top - height);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            mRecyclerView.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
        }

    }


    public interface SusItemOnclickListener {
        void susOnClickListener(BaseType baseType);

        void requestDisallowInterceptTouchEvent(boolean isInterceptTouch);
    }

    public static class BaseType {
        //item正常颜色
        public int textNormalColorResId = R.color.white;
        //Item选中颜色
        public int textSelectedColorResId = R.color.blue;
        //显示资源ID
        public String titleText;
        public int type;
        public boolean isSelected;
    }
}