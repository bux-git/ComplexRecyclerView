# RecyclerView 多类型Item 实现的复杂布局      
![image](https://github.com/bux-git/ComplexRecyclerView/raw/master/complex_recyclerview.gif)      

背景：最近项目中负责这样一个比较复杂的页面，是一个名片编辑和展示的页面 里面包含了 头部 12个子项 2个悬浮View    
还可以选择背景颜色模版 等功能      

初次看到设计界面 脑子里面冒出来的第一个想法是 ScrollView+ViewGroup 然后代码中动态添加子布局 但是由于没有View的复用机制    
导致图片过多时容易OOM,而且页面刷新频率也是较高 ViewGroup inflate，addView removeAllViews 会使屏幕刷新时闪一下 用户体验很差     
最终决定使用RecyclerView 多类型实现    

#### 多类型实现原理参考 [优雅的实现多类型列表的Adapter](http://www.jianshu.com/p/1297d2e4d27a)     

仔细观察页面 可以把页面 分成 以下11个item 类型
 
 
    头部第一项
    标题栏
    无数据时默认项
    文字：文字 项
    图文混排 上面文字 下面图片
    相册
    视频
    业务案例
    教育经历
    招聘信息
    企业产品     
    
 
   接下来就是分别创建layout文件，每一项数据javaBean。    
   然后再在主页面中组合这些javaBean 填充到数据集 设置RecyclerView Adapter      
   
 
