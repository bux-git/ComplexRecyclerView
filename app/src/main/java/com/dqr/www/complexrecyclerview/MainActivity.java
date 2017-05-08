package com.dqr.www.complexrecyclerview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.dqr.www.complexrecyclerview.multi.itembean.AlbumItem;
import com.dqr.www.complexrecyclerview.multi.itembean.Attr;
import com.dqr.www.complexrecyclerview.multi.itembean.ExperienceItem;
import com.dqr.www.complexrecyclerview.multi.itembean.MainHeadItem;
import com.dqr.www.complexrecyclerview.multi.itembean.NoDataItem;
import com.dqr.www.complexrecyclerview.multi.itembean.ProductItem;
import com.dqr.www.complexrecyclerview.multi.itembean.RecruitItem;
import com.dqr.www.complexrecyclerview.multi.itembean.TextDetailsItem;
import com.dqr.www.complexrecyclerview.multi.itembean.TextImageItem;
import com.dqr.www.complexrecyclerview.multi.itembean.TitleItem;
import com.dqr.www.complexrecyclerview.multi.itembean.VideoItem;
import com.dqr.www.complexrecyclerview.multi.muladapter.MulAdapter;
import com.dqr.www.complexrecyclerview.multi.muladapter.Visitable;
import com.dqr.www.complexrecyclerview.widget.SuspensionList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MulAdapter.OnECardItemClickListener {
    public static final String SPACE_SINGLE = "&#160;";//比一个空格稍宽
    public static final String SPACE_SEAT = "&#160;&#160;&#8201;";//一个汉字宽度
    public static final String SYMBOL_SEAT = "&#8201;";//一个符号宽度

    //各种点击事件时 名片各类信息标记
    //设置数据时，将每一项title在数据集中的位置作为值
    private int edit_personal;//个人资料
    private int edit_personal_desc;//个人介绍
    private int edit_personal_album;//个人相片
    private int edit_personal_video;//个人简历
    private int edit_personal_resume;//个人资料

    private int edit_company;//企业资料
    private int edit_company_desc;//企业介绍
    private int edit_company_album;//企业相片
    private int edit_company_video;//企业视频
    private int edit_company_sample;//业务案例

    private int edit_company_zhaopin;//企业招聘
    private int edit_company_product;//企业产品

    RecyclerView mRecyclerView;
    SuspensionList mSuspensionList;
    ImageView mIvMode;

    private List<Visitable> mList;
    private MulAdapter mMulAdapter;
    private LinearLayoutManager mManager;
    List<SuspensionList.BaseType> baseTypes;//悬浮栏数据

    private int modeColor = Color.WHITE;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Attr.isSelfCard=true;


        initView();
        initEvent();
        initData();
        //加载数据方法
        refreshUpData();
    }

    private void initData() {
        modeColor = getResources().getColor(R.color.eCard_blue);
        mList = new ArrayList<>();
        mMulAdapter = new MulAdapter(mList);
        baseTypes = new ArrayList<>();

        mRecyclerView.setItemViewCacheSize(30);
        mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mMulAdapter);
        mMulAdapter.setOnECardItemClickListener(this);
    }

    private void initEvent() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int oldPosition = -1;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //滑动时 根据显示项目 去改变悬浮栏显示选中项
                int position = mManager.findFirstVisibleItemPosition() + 1;
                if (position != oldPosition) {
                    //当前显示第一项的头部Position
                    if (mList.get(position) instanceof Attr) {
                        Attr attr = (Attr) mList.get(position);
                        mSuspensionList.setSelectedByType(attr.titlePosition);
                    }
                }
                oldPosition = position;
            }

        });


        //悬浮栏点击事件 recyclerView 移动到指定项目显示
        mSuspensionList.setListener(new SuspensionList.SusItemOnclickListener() {
            @Override
            public void susOnClickListener(final SuspensionList.BaseType baseType) {
                mSuspensionList.moveToPosition(mManager, mRecyclerView, baseType.type, 200);
                mRecyclerView.postDelayed(new Runnable() {//执行两次精确一下定位，由于加载图片高度不确定
                    @Override
                    public void run() {
                        mSuspensionList.moveToPosition(mManager, mRecyclerView, baseType.type, 200);
                    }
                }, 600);

            }

            @Override
            public void requestDisallowInterceptTouchEvent(boolean isInterceptTouch) {

            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_content);
        mSuspensionList = (SuspensionList) findViewById(R.id.susList);
        mIvMode = (ImageView) findViewById(R.id.iv_model);
    }


    /**
     * 设置页面数据
     */
    private void refreshUpData() {
        mList.clear();
        baseTypes.clear();

        initHead();
        //个人名片
        initPersonal(false);
        initPersonalDesc(true);
        initPersonalAlbum(false);
        initPersonalVideo(true);
        initPersonalResume(false);
        //企业名片
        initCompany(true);
        initCompanyDesc(false);
        initCompanyProduct(true);
        initCompanyAlbum(false);
        initCompanyVideo(true);
        initCompanySample(false);
        initCompanyZhaoPin(true);


        mMulAdapter.notifyDataSetChanged();
        mSuspensionList.setData(baseTypes);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

            }
        }
    }


    private void initHead() {
        MainHeadItem headItem = new MainHeadItem(false, mList.size(), modeColor);
        headItem.carBgImg = "";
        headItem.cardName = "不修的个人名片";
        headItem.cardType = "个人名片";
        headItem.isLock = true;
        mList.add(headItem);

    }

    /**
     * 个人资料
     *
     * @param isModel 是否使用模版
     */
    private void initPersonal(boolean isModel) {
        //条目
        edit_personal = addTitleItem(isModel, R.drawable.ic_making_card_edit_persnal_white
                , R.drawable.ic_making_card_edit_paint_white
                , R.drawable.ic_making_card_edit_persnal
                , R.drawable.ic_making_card_edit_paint
                , R.string.eCard_edit_personal_title);


        //无数据时...

        //个人资料
        addDetails(isModel, "昵称:", "一瞬间", -1, edit_personal);
        addDetails(isModel, "代码:", "AA0000000", -1, edit_personal);
        addDetails(isModel, "姓名:", "周大福", -1, edit_personal);
        addDetails(isModel, "籍贯:", "湖北", -1, edit_personal);
        addDetails(isModel, "工作地:", "湖北武汉", -1, edit_personal);
        addDetails(isModel, "身高/体重:", "170cm/70kg", -1, edit_personal);
        addDetails(isModel, "婚姻状况:", "未婚", -1, edit_personal);
        addDetails(isModel, "手机1:", "18655989563", R.drawable.ic_mp_phone, edit_personal);
        addDetails(isModel, "座机:", "027-12236554", R.drawable.ic_mp_phone, edit_personal);
        addDetails(isModel, "QQ:", "7569556556", -1, edit_personal);
        addDetails(isModel, "微信:", "7569556556", -1, edit_personal);
        addDetails(isModel, "E-mail:", "7569556556@qq.com", -1, edit_personal);

    }


    /**
     * 个人介绍
     *
     * @param isModel 是否使用模版
     */
    private void initPersonalDesc(boolean isModel) {
        //条目
        edit_personal_desc = addTitleItem(isModel, R.drawable.ic_making_card_edit_persnal_desc_white
                , R.drawable.ic_making_card_edit_paint_white
                , R.drawable.ic_making_card_edit_persnal_desc
                , R.drawable.ic_making_card_edit_paint
                , R.string.eCard_edit_personal_desc_title);


        //无数据时
        /*if(xxx) {
            initNoData(0, pTitleDesc.title, isModel);
        }*/
        //个人介绍图文混排
        addTextImageItem(isModel
                , "千人会议为梦想助力！"
                , "http://www.dqr2558.com:8080/uploadFiles/1688/small_a8af0196cd494e0da815abafba3d2cc1.jpg", edit_personal_desc);

    }


    /**
     * 个人相片
     *
     * @param isModel 是否使用模版
     */
    private void initPersonalAlbum(boolean isModel) {
        //条目
        edit_personal_album = addTitleItem(isModel, R.drawable.ic_making_card_edit_persnal_album_white
                , R.drawable.ic_making_card_edit_album_white
                , R.drawable.ic_making_card_edit_persnal_album
                , R.drawable.ic_making_card_edit_album
                , R.string.eCard_edit_personal_album_title);

        //无数据时
        //initNoData(1, pTitleDesc.title, isModel);


        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("http://www.dqr2558.com:8080/img/201702/1043/small_90ffadcec05244ba817cc7a93cf88c0e.JPG");
        imgUrls.add("http://www.dqr2558.com:8080/img/201702/1043/small_82fcf44f12f0484a9aab233dfc0bd3f7.JPG");
        imgUrls.add("http://www.dqr2558.com:8080/img/201702/1043/small_eacc2a94dbc746419c018966045a1376.JPG");
        imgUrls.add("http://www.dqr2558.com:8080/img/201702/1043/small_e2646c8f3cc94fe9a93a061ebb1fd81a.JPG");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/279/a34089d08d03480d8e2fbc2dd1e586bd.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/279/small_a34089d08d03480d8e2fbc2dd1e586bd.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/279/small_383f8aa3694f48e8a93ae9174df34fd1.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/279/small_ad16f2ebf013401bafe37c1e53b13ed1.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201702/279/small_e3925dd3bb224553aab670daf7cddebd.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/279/small_72fa4dae976248198a07c254db89ddd7.jpg");
        addAlbumItem(isModel, imgUrls, edit_personal_album);
    }


    /**
     * 个人视频
     *
     * @param isModel 是否使用模版
     */
    private void initPersonalVideo(boolean isModel) {
        //条目
        edit_personal_video = addTitleItem(isModel, R.drawable.ic_making_card_edit_persnal_video_white
                , R.drawable.ic_making_card_edit_video_white
                , R.drawable.ic_making_card_edit_persnal_video
                , R.drawable.ic_making_card_edit_video
                , R.string.eCard_edit_personal_video_title);

        //无数据时
        //initNoData(1, pTitleDesc.title, isModel);
        addVideoItem(isModel
                , "http://www.dqr2558.com:8080/uploadFiles/279/small_5e0d0570dbec4fdb8ea2136c83a18d83.jpg"
                , "http://www.dqr2558.com:8080/uploadFiles/279/5e0d0570dbec4fdb8ea2136c83a18d83.mp4"
                , "", edit_personal_video);

    }


    /**
     * 个人简历
     *
     * @param isModel 是否使用模版
     */
    private void initPersonalResume(boolean isModel) {
        //条目
        edit_personal_resume = addTitleItem(isModel, R.drawable.ic_making_card_edit_persnal_resume_white
                , R.drawable.ic_making_card_edit_paint_white
                , R.drawable.ic_making_card_edit_persnal_resume
                , R.drawable.ic_making_card_edit_paint
                , R.string.eCard_edit_personal_resume_title);

        //无数据时
        // initNoData(0, R.string.eCard_edit_personal_resume_title, isModel, position);


        addDetails(isModel, "求职意向:", "Android工程师", -1, edit_personal_resume);
        addDetails(isModel, "地区:", "武汉", -1, edit_personal_resume);
        addDetails(isModel, "期望薪资:", "6000-15000", -1, edit_personal_resume);
        addDetails(isModel, "证书:", "Android工程师初级,Android工程师中级，Android工程师高级，散打国手级别", -1, edit_personal_resume);

        addExperienceItem(isModel, "教育经历:", "2004/09-2007/06", "武汉市第十二中", "高中|理科", edit_personal_resume);
        addExperienceItem(isModel, getSeat(6, true), "2004/09-2007/06", "武汉市第十二中", "高中|理科", edit_personal_resume);
        addExperienceItem(isModel, getSeat(6, true), "2004/09-2007/06", "武汉市第十二中", "高中|理科", edit_personal_resume);

        addExperienceItem(isModel, "工作经历:", "2004/09-2007/06", "武汉市第十二中", "高中|理科", edit_personal_resume);
        addExperienceItem(isModel, getSeat(6, true), "2004/09-2007/06", "武汉市第十二中", "高中|理科", edit_personal_resume);
        addExperienceItem(isModel, getSeat(6, true), "2004/09-2007/06", "武汉市第十二中", "高中|理科", edit_personal_resume);


    }

    /**
     * 企业资料
     *
     * @param isModel 是否使用模版
     */
    private void initCompany(boolean isModel) {
        //条目
        edit_company = addTitleItem(isModel, R.drawable.ic_making_card_edit_company_white
                , R.drawable.ic_making_card_edit_paint_white
                , R.drawable.ic_making_card_edit_company
                , R.drawable.ic_making_card_edit_paint
                , R.string.eCard_edit_company_title);

        //无数据时
        //initNoData(0,pTitleDesc.title,isModel);
        addDetails(isModel, "昵称:", "有时间", -1, edit_company);
        addDetails(isModel, "代码:", "AA0000000", -1, edit_company);
        addDetails(isModel, "法人:", "周大福", -1, edit_company);
        addDetails(isModel, "职位:", "总经理", -1, edit_company);
        addDetails(isModel, "公司名称:", "湖南球谱科技有限公司", -1, edit_company);
        addDetails(isModel, "行业:", "互联网", -1, edit_company);
        addDetails(isModel, "经营范围:", "软件、手机、电脑", -1, edit_company);
        addDetails(isModel, "规模:", "500-1000人", -1, edit_company);
        addDetails(isModel, "手机1:", "18655989563", R.drawable.ic_mp_phone, edit_company);
        addDetails(isModel, "座机:", "027-12236554", R.drawable.ic_mp_phone, edit_company);
        addDetails(isModel, "QQ:", "7569556556", -1, edit_company);
        addDetails(isModel, "微信:", "7569556556", -1, edit_company);
        addDetails(isModel, "E-mail:", "7569556556@qq.com", -1, edit_company);
        addDetails(isModel, "荣耀:", "长沙十大杰出企业", -1, edit_company);
        addDetails(isModel, getSeat(3, true), "全国模范良心企业", -1, edit_company);
        addDetails(isModel, "我需要的:", "IT人才，投行", -1, edit_company);

    }


    /**
     * 企业介绍
     *
     * @param isModel 是否使用模版
     */
    private void initCompanyDesc(boolean isModel) {
        //条目
        edit_company_desc = addTitleItem(isModel, R.drawable.ic_making_card_edit_company_desc_white
                , R.drawable.ic_making_card_edit_paint_white
                , R.drawable.ic_making_card_edit_company_desc
                , R.drawable.ic_making_card_edit_paint
                , R.string.eCard_edit_company_desc_title);

        //无数据时
        // initNoData(0, R.string.eCard_edit_company_desc_title, isModel);

        //企业介绍图文混排
        addTextImageItem(isModel
                , "这是公司的一个规划，一个属于自己的地球人村庄，这里免费的水费，免费的电费，免费的……想了解更多欢迎光临，记住我们地球村的第一任村长是我们的总裁~~易生亮！！\n" +
                        " 入住地球村幸福一辈子"
                , "http://www.dqr2558.com:8080/img/201701/279/small_a34089d08d03480d8e2fbc2dd1e586bd.jpg", edit_company_desc);
        addTextImageItem(isModel
                , ""
                , "http://www.dqr2558.com:8080/img/201701/279/small_08315da0f5e5467fbfdd0f6f4c5d0f06.jpg", edit_company_desc);
    }

    /**
     * 企业产品
     *
     * @param isModel 是否使用模版
     */
    private void initCompanyProduct(boolean isModel) {
        //条目
        edit_company_product = addTitleItem(isModel, R.drawable.ic_making_card_edit_company_product_white
                , R.drawable.ic_making_card_edit_paint_white
                , R.drawable.ic_making_card_edit_company_product
                , R.drawable.ic_making_card_edit_paint
                , R.string.eCard_edit_company_product_title);
        //无数据时
        //initNoData(0, R.string.eCard_edit_company_product_title, isModel, edit_company_product);

        addDetails(isModel, "产品名称:", "地球人APP", -1, edit_company_product);
        addDetails(isModel, "产品介绍:", "地球人APP地球人APP地球人APP地球人APP地球人APP地球人APP地球人APP地球人APP", -1, edit_company_product);
        //图文混排
        addTextImageItem(isModel
                , ""
                , "http://www.dqr2558.com:8080/img/201702/279/small_4a5aaf513a8e48a9be39aa1ddd8e96e7.jpg", edit_company_product);

    }


    /**
     * 企业相片
     *
     * @param isModel 是否使用模版
     */
    private void initCompanyAlbum(boolean isModel) {
        //条目
        edit_company_album = addTitleItem(isModel, R.drawable.ic_making_card_edit_persnal_album_white
                , R.drawable.ic_making_card_edit_album_white
                , R.drawable.ic_making_card_edit_persnal_album
                , R.drawable.ic_making_card_edit_album
                , R.string.eCard_edit_company_album_title);

        //无数据时
        //initNoData(1, R.string.eCard_edit_company_album_title, isModel);

        //企业相片
        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/2803/small_646e381f8ed34fc6af86a24be4d4a92b.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/2803/small_c0392037d36442cca05454f368ba930d.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/2803/small_474603cdeea94254953ed297499b8864.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/2803/small_73431e362c1f49988f3159d784d3080a.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/2803/small_3992e4b08b3b4b3391329788df95c977.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/2803/small_64bb0b9952224dc4a578e4fa199c0143.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/2803/small_5d7745ecf9864dd28f86a746402ef9d9.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/2803/small_dee92ba773cb4f88b529e70a560bbd52.jpg");
        imgUrls.add("http://www.dqr2558.com:8080/img/201701/2803/small_f8e362a5d2894421ad1c9e1f237ec32a.jpg");
        addAlbumItem(isModel, imgUrls, edit_company_album);
    }

    /**
     * 企业视频
     *
     * @param isModel 是否使用模版
     */
    private void initCompanyVideo(boolean isModel) {
        //条目
        edit_company_video = addTitleItem(isModel, R.drawable.ic_making_card_edit_persnal_video_white
                , R.drawable.ic_making_card_edit_video_white
                , R.drawable.ic_making_card_edit_persnal_video
                , R.drawable.ic_making_card_edit_video
                , R.string.eCard_edit_company_video_title);

        //无数据时
        // initNoData(1, R.string.eCard_edit_company_video_title, isModel);

        addVideoItem(isModel
                , "http://www.dqr2558.com:8080/media/201701/2803/small_3575934cf5454e22b178bc25faf7c0d7.jpg"
                , "http://www.dqr2558.com:8080/uploadFiles/279/5e0d0570dbec4fdb8ea2136c83a18d83.mp4"
                , "", edit_company_video);
    }


    /**
     * 业务案例
     *
     * @param isModel 是否使用模版
     */
    private void initCompanySample(boolean isModel) {
        //条目
        edit_company_sample = addTitleItem(isModel, R.drawable.ic_making_card_edit_company_sample_white
                , R.drawable.ic_making_card_edit_paint_white
                , R.drawable.ic_making_card_edit_company_sample
                , R.drawable.ic_making_card_edit_paint
                , R.string.eCard_edit_company_sample_title);

        //无数据时
        //initNoData(0, R.string.eCard_edit_company_sample_title, isModel);

        addProductItem(isModel, true, "名称", "时间", "合作企业", R.color.eCard_company_product_name_colorBg);
        addProductItem(isModel, false, "地球人APP", "2013.6.5", "租车宝宝", R.color.eCard_company_product_name_colorBg2);
        addProductItem(isModel, false, "地球人APP地球人APP地球人APP地球人APP", "2013.6.5", "租车宝宝租车宝宝租车宝宝租车宝宝", R.color.eCard_company_product_name_colorBg);
        addProductItem(isModel, false, "地球人APP", "2013.6.5", "租车宝宝", R.color.eCard_company_product_name_colorBg2);
        addProductItem(isModel, false, "地球人APP地球人APP地球人APP地球人APP", "2013.6.5", "租车宝宝租车宝宝租车宝宝租车宝宝", R.color.eCard_company_product_name_colorBg);
    }

    /**
     * 企业招聘
     *
     * @param isModel 是否使用模版
     */
    private void initCompanyZhaoPin(boolean isModel) {
        //条目
        edit_company_zhaopin = addTitleItem(isModel, R.drawable.ic_making_card_edit_company_zhaopin_white
                , R.drawable.ic_making_card_edit_paint_white
                , R.drawable.ic_making_card_edit_company_zhaopin
                , R.drawable.ic_making_card_edit_paint
                , R.string.eCard_edit_company_zhaopin_title);

        //无数据时
        //initNoData(0, R.string.eCard_edit_company_zhaopin_title, isModel, position);
        addRecruitItem(isModel, "Android工程师", "8000-9000千", "长沙市-长沙县 学历：本科 人数：8", "职位信息", "1.熟练掌握Java语言及面向对象的思想\n" +
                "2.掌握Java当中常用的设计模式\n" +
                "3.熟练掌握Android当中的常用布局以及基本控件\n" +
                "4.熟练掌握Android的四大组件的生命周期以及使用方法\n" +
                "5.熟练掌握Android的网络请求方式以及UI界面的更新\n" +
                "6.熟练掌握Android当中的列表/网格视图的控件，包括视图的优化，分页加载，多种布局\n" +
                "7.熟练掌握Android中的碎片化编程方式，及Fragment的生命周期，加载方式，传值方式\n" +
                "8.熟练掌握Android当中的常用动画，帧动画，补间动画，属性动画\n" +
                "9.熟练掌握Android屏幕适配的方法\n" +
                "10.熟练掌握关于图片的三级缓存和二次采样等技术\n" +
                "11.熟练使用第三方网络请求框架和图片处理框架，如volley、xUtils，Picasso\n" +
                "12.熟练使用 Android中的侧滑控件以及第三方侧滑框架的，如SlideMenu\n" +
                "13.熟练掌握 Android 5.0新特性和新增控件，如RecyclerView，TabLayout\n" +
                "14.熟悉第三方推送，第三方分享，登录的功能\n" +
                "15.熟悉百度地图API\n" +
                "熟悉monkey测试工具\n" +
                "我是一个稳重踏实的人。 为人诚实、讲信用。 对待工作认真负责、团队意识强。", edit_company_zhaopin);
        addRecruitItem(isModel, "Android工程师", "8000-9000千", "长沙市-长沙县 学历：本科 人数：8", "职位信息", "1.熟练掌握Java语言及面向对象的思想\n" +
                "2.掌握Java当中常用的设计模式\n" +
                "3.熟练掌握Android当中的常用布局以及基本控件\n" +
                "4.熟练掌握Android的四大组件的生命周期以及使用方法\n" +
                "5.熟练掌握Android的网络请求方式以及UI界面的更新\n" +
                "6.熟练掌握Android当中的列表/网格视图的控件，包括视图的优化，分页加载，多种布局\n" +
                "7.熟练掌握Android中的碎片化编程方式，及Fragment的生命周期，加载方式，传值方式\n" +
                "8.熟练掌握Android当中的常用动画，帧动画，补间动画，属性动画\n" +
                "9.熟练掌握Android屏幕适配的方法\n" +
                "10.熟练掌握关于图片的三级缓存和二次采样等技术\n" +
                "11.熟练使用第三方网络请求框架和图片处理框架，如volley、xUtils，Picasso\n" +
                "12.熟练使用 Android中的侧滑控件以及第三方侧滑框架的，如SlideMenu\n" +
                "13.熟练掌握 Android 5.0新特性和新增控件，如RecyclerView，TabLayout\n" +
                "14.熟悉第三方推送，第三方分享，登录的功能\n" +
                "15.熟悉百度地图API\n" +
                "熟悉monkey测试工具\n" +
                "我是一个稳重踏实的人。 为人诚实、讲信用。 对待工作认真负责、团队意识强。", edit_company_zhaopin);
        addRecruitItem(isModel, "Android工程师", "8000-9000千", "长沙市-长沙县 学历：本科 人数：8", "职位信息", "", edit_company_zhaopin);

    }


    /**
     * 添加业务案例
     *
     * @param isModel
     */
    private void addProductItem(boolean isModel, boolean isTitle, String name, String dateStr, String companyName, int firstBgColor) {
        ProductItem productItem = new ProductItem(isModel, mList.size(), modeColor);
        productItem.isTitle = isTitle;
        productItem.name = name;
        productItem.dateStr = dateStr;
        productItem.companyName = companyName;
        productItem.nameBgColor = firstBgColor;
        mList.add(productItem);
    }


    /**
     * 添加名片子项头目
     *
     * @param isModel
     */
    private int addTitleItem(boolean isModel, int modelLeftIcoResId, int modelRightIcoResId
            , int normalLeftIcoResId, int normalRightIcoResId, int titleResId) {
        int position = mList.size();
        TitleItem pTitle = new TitleItem(isModel, position, modeColor
                , modelLeftIcoResId
                , modelRightIcoResId
                , normalLeftIcoResId
                , normalRightIcoResId);

        pTitle.title = titleResId;
        mList.add(pTitle);

        addRightSus(titleResId, position);
        return position;
    }

    /**
     * 无数据时显示模版
     *
     * @param descResId 文字说明
     * @param type      前缀类型,0 编辑  1 上传
     */
    private void initNoData(int type, int descResId, boolean isModel, int titlePosition) {
        String desc = getString(type == 0 ? R.string.eCard_edit_title_tag_edit : R.string.eCard_edit_title_tag_upload)
                + getString(descResId);
        //无数据时
        NoDataItem noDataItem = new NoDataItem(isModel, titlePosition, modeColor);
        noDataItem.tvStr = desc;
        mList.add(noDataItem);
    }

    /**
     * //个人 企业 资料详细情况
     *
     * @param isModel
     */
    private void addDetails(boolean isModel, String name, String value, int icoResId, int titlePosition) {
        TextDetailsItem detailsItem = new TextDetailsItem(isModel, titlePosition, modeColor);
        detailsItem.fileName = name;
        detailsItem.fileValue = value;
        detailsItem.iv_right = icoResId;
        mList.add(detailsItem);
    }

    /**
     * 添加图文混排布局
     *
     * @param isModel
     */
    private void addTextImageItem(boolean isModel, String text, String imgUrl, int titlePosition) {
        TextImageItem imageItem = new TextImageItem(isModel, titlePosition, modeColor);
        imageItem.descStr = text;
        imageItem.imgUrl = imgUrl;
        mList.add(imageItem);
    }

    /**
     * 添加相册项目
     *
     * @param isModel
     * @param imgUrls
     */
    private void addAlbumItem(boolean isModel, List<String> imgUrls, int titlePosition) {
        AlbumItem albumItem = new AlbumItem(isModel, titlePosition, modeColor);
        albumItem.imgUrls = imgUrls;
        mList.add(albumItem);
    }

    /**
     * 添加视频条目
     *
     * @param isModel
     */
    private void addVideoItem(boolean isModel, String imgUrl, String videoUrl, String videoTitle, int titlePosition) {
        VideoItem videoItem = new VideoItem(isModel, titlePosition, modeColor);
        videoItem.imgUrl = imgUrl;
        videoItem.videoUrl = videoUrl;
        videoItem.videoTitle = videoTitle;
        mList.add(videoItem);
    }

    /**
     * 添加经历条目
     *
     * @param isModel
     * @param name
     * @param value1
     * @param value2
     * @param value3
     * @param titlePosition
     */
    private void addExperienceItem(boolean isModel, String name, String value1, String value2, String value3, int titlePosition) {
        ExperienceItem experienceItem = new ExperienceItem(isModel, titlePosition, modeColor);
        experienceItem.fileName = name;
        experienceItem.value1 = value1;
        experienceItem.value2 = value2;
        experienceItem.value3 = value3;
        mList.add(experienceItem);
    }

    /**
     * 添加招聘信息
     *
     * @param isModel
     * @param job
     * @param wages
     * @param place
     * @param desc
     * @param content
     * @param titlePosition
     */
    private void addRecruitItem(boolean isModel, String job, String wages, String place, String desc, String content, int titlePosition) {
        RecruitItem item = new RecruitItem(isModel, titlePosition, modeColor);
        item.job = job;
        item.wages = wages;
        item.place = place;
        item.desc = desc;
        item.content = content;
        mList.add(item);
    }

    /**
     * 为悬浮栏添加ITEM
     *
     * @param titleResId
     * @param type       用title的position作为悬浮栏类型
     */
    private void addRightSus(int titleResId, int type) {
        SuspensionList.BaseType baseType = new SuspensionList.BaseType();
        baseType.titleText = getString(titleResId);
        baseType.type = type;
        baseTypes.add(baseType);
    }

    /**
     * 汉字占位
     *
     * @param count  汉字数量
     * @param symbol 结尾是否需要符号
     */
    private String getSeat(int count, boolean symbol) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            stringBuffer.append(SPACE_SEAT);
        }
        if (symbol) {
            stringBuffer.append(SYMBOL_SEAT);
        }
        return stringBuffer.toString();
    }

    /*-------------------------Item各种事件----------------------------------------*/
    /*
        Title编辑事件
     */
    @Override
    public void onECardTitleItemClickLister(int itemType, int position) {
        if (itemType == edit_personal) {//个人资料


        } else if (itemType == edit_personal_desc) {//个人介绍


        } else if (itemType == edit_personal_album) {//个人相片

        } else if (itemType == edit_personal_video) {//个人视频

        } else if (itemType == edit_personal_resume) {//个人简历

        } else if (itemType == edit_company) { //企业资料

        } else if (itemType == edit_company_desc) {//企业介绍

        } else if (itemType == edit_company_album) {//企业照片

        } else if (itemType == edit_company_video) {//企业视频
        } else if (itemType == edit_company_sample) {//企业案例

        } else if (itemType == edit_company_zhaopin) {//企业招聘

        } else if (itemType == edit_company_product) {//企业产品

        }
    }

    /**
     * 图片点击事件
     *
     * @param itemType
     * @param position
     * @param imgUrl
     */
    @Override
    public void onECardImageClickListener(int itemType, int position, String imgUrl) {
        if (itemType == edit_personal) {

        } else if (itemType == edit_personal_desc) {

        } else if (itemType == edit_personal_album) {

        } else if (itemType == edit_personal_video) {

        } else if (itemType == edit_personal_resume) {

        } else if (itemType == edit_company) {

        } else if (itemType == edit_company_desc) {

        } else if (itemType == edit_company_album) {

        } else if (itemType == edit_company_video) {

        } else if (itemType == edit_company_sample) {

        } else if (itemType == edit_company_zhaopin) {

        } else if (itemType == edit_company_product) {

        }
    }

    /**
     * 拨打电话号码
     *
     * @param itemType
     * @param position
     * @param phoneNumber
     */
    @Override
    public void onECardCallPhoneList(int itemType, int position, String phoneNumber) {

    }

    /**
     * 更换背景
     */
    @Override
    public void onECardEditBgImg() {
    }

    /**
     * 修改名片名称
     *
     * @param name 原名称
     */
    @Override
    public void onECardEditName(String name) {
    }

    /**
     * 二维码
     */
    @Override
    public void onECardScanClickListener() {

    }

    /**
     * 设置
     */
    @Override
    public void onECardSetting() {

    }

    /**
     * 分享
     */
    @Override
    public void onECardShare() {

    }

    /**
     * 预览
     */
    @Override
    public void onECardPrev() {

    }

    @Override
    public void onLockClick() {

    }


}
