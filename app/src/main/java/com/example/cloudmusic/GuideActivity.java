package com.example.cloudmusic;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private float ratio;
    private TextSwitcher tsTitle;
    private TextSwitcher tsDesc;
    String[] titles=new String[]{"个 性 推 荐","精 彩 评 论","海 量 资 讯"};
    String[] descs=new String[]{"每 天 为 你 量 身 推 荐 最 合 口 味 的 好 音 乐",
            "4 亿 多 条 有 趣 的 故 事 ，听 歌 再 不 孤 单",
            "明 星 动 态 、音 乐 热 点 尽 收 眼 底"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }
    ArrayList<View> views=new ArrayList<>();

    int index  = 0 ;


    private void initView() {
        //getResources().getDisplayMetrics().heightPixels表示屏幕像素高度
        //图片的高度是1920
        //ratio表示屏幕与图片的高度比即表示图片与屏幕大小的比值关系
        ratio = 1F*(getResources().getDisplayMetrics().heightPixels/1920.0F);
        tsTitle = (TextSwitcher)findViewById(R.id.ts_title);
        tsDesc = (TextSwitcher)findViewById(R.id.ts_desc);
        tsTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv=new TextView(GuideActivity.this);
                tv.setTextSize(24);
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(getResources().getColor(android.R.color.white));
                tv.setTypeface(Typeface.DEFAULT_BOLD);
                return tv;
            }
        });
        tsDesc.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv=new TextView(GuideActivity.this);
                tv.setTextSize(13);
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(getResources().getColor(android.R.color.white));
                tv.setTypeface(Typeface.DEFAULT_BOLD);
                return tv;
            }
        });
        tsTitle.setText(titles[0]);
        tsDesc.setText(descs[0]);

        final View view1= LayoutInflater.from(this).inflate(R.layout.layout_guide1,null);
        setViewSize(view1,0);
        final View view2= LayoutInflater.from(this).inflate(R.layout.layout_guide2,null);
        setViewSize(view2,1);
        final View view3= LayoutInflater.from(this).inflate(R.layout.layout_guide3,null);
        setViewSize(view3,2);

        views.add(view1);
        views.add(view2);
        views.add(view3);
        ViewPager vp=(ViewPager)findViewById(R.id.vp_guide);
        vp.setAdapter(new MyVPAdapter());
        PageIndicatorView pageIndicatorView=(PageIndicatorView)findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(vp);
        pageIndicatorView.setRadius(11F * ratio);
        pageIndicatorView.setPadding((int) (5 * ratio), 0, (int) (5 * ratio), 0);

        vp.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                //page 当前滑动的view
                //position view所占的空间 值为0表示在屏幕中间  -1表示滑出屏幕了
                Log.e("transformPage", "transformPage: " + page.getClass() + "   " + position);
                int pageWidth = page.getWidth();
//                Log.e("pageWidth",pageWidth+"    "+position);
                if (position <-1) {
                    page.setAlpha(0);//设置透明度
                }
                else if (position <= 1) {
                    if (position < 0) {
                        page.setTranslationX(-pageWidth * position);
                    } else {
                        Log.d("transformPage", "transformPage: ");
                        page.setTranslationX(pageWidth);
                        page.setTranslationX(-pageWidth * position);//抵消移动的距离
                        //系统默认的切换状态是两个view连在一起
                        //setTranslationX平移
                    }
                    page.setAlpha(Math.max(0, 1 - Math.abs(position)));
                }
                else {
                    page.setAlpha(0);
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                View view = views.get(position);

                if(index < position){
                    if (position == 1) {
                        ivAesAnimator(view.findViewById(R.id.iv_guide2), (517F * ratio));
                        ivAekAnimator(view.findViewById(R.id.iv_white));
                        ivAekAnimator(view.findViewById(R.id.iv_aet_guide2));
                    }
                    if (position == 2) {
                        ivAesAnimator(view.findViewById(R.id.iv_aet_guide3), (333F * ratio));
                        ivAekAnimator(view.findViewById(R.id.iv_white));
                    }
                    tsTitle.setInAnimation(GuideActivity.this,R.anim.right_in);
                    tsTitle.setOutAnimation(GuideActivity.this,R.anim.right_out);

                    tsDesc.setInAnimation(GuideActivity.this,R.anim.right_in);
                    tsDesc.setOutAnimation(GuideActivity.this,R.anim.right_out);
                }
                else {
                    tsTitle.setInAnimation(GuideActivity.this,R.anim.left_in);
                    tsTitle.setOutAnimation(GuideActivity.this,R.anim.left_out);

                    tsDesc.setInAnimation(GuideActivity.this,R.anim.left_in);
                    tsDesc.setOutAnimation(GuideActivity.this,R.anim.left_out);
                }
                tsTitle.setText(titles[position]);
                tsDesc.setText(descs[position]);
                index = position;
            }

            private void ivAesAnimator(View view, float size) {
                //参数： 第一个表示移动哪一个控件 第二个表示垂直还是水平运动
                // 第三个表示移动的距离  第四个表示最后回到原点，即控件原本所在的地方
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", size, 0);
                animator.setDuration(800);
                animator.start();
            }

            private void ivAekAnimator(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", new float[]{0F, 1F});
                animator.setDuration(1000);
                animator.start();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        View bg_red=findViewById(R.id.bg_red);
        bg_red.getLayoutParams().height=(int)(1185F*ratio);
        Button btnLogin=(Button)findViewById(R.id.btn_login);
        Button btnTest=(Button)findViewById(R.id.btn_test);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,LoginActivity.class));
            }
        });
    }
    private void setViewSize(View view,int index){
        //设置中间view的大小
        view.findViewById(R.id.cv_guide).getLayoutParams().width=(int)(803F*ratio);
        view.findViewById(R.id.cv_guide).getLayoutParams().height=(int)(1218F*ratio);

        int ivAesSize=(int)(237F*ratio);
        int marginLeft=(int)(40F*ratio);
        if (index==0){
            ImageView ivGuide1=(ImageView)view.findViewById(R.id.iv_guide1);
            FrameLayout.LayoutParams ivGuide1Params=(FrameLayout.LayoutParams)ivGuide1.getLayoutParams();
            ivGuide1Params.height=ivAesSize;
            ivGuide1Params.width=ivAesSize;
            int marginTop=(int)(552F*ratio);//552为在原图片中图片位置距顶部的高度
            ivGuide1Params.leftMargin=marginLeft;
            ivGuide1Params.topMargin=marginTop;
        }
        if (index==1){
            ImageView ivGuide2=(ImageView)view.findViewById(R.id.iv_guide2);
            FrameLayout.LayoutParams ivGuide2Params=(FrameLayout.LayoutParams)ivGuide2.getLayoutParams();
            ivGuide2Params.height=ivAesSize;
            ivGuide2Params.width=ivAesSize;
            ivGuide2Params.leftMargin=marginLeft;
            ivGuide2Params.topMargin=marginLeft;

            int ivAetSize=(int)(105F*ratio);
            ImageView ivAetGuide2=(ImageView)view.findViewById(R.id.iv_aet_guide2);
            FrameLayout.LayoutParams ivAetGuide2Params=(FrameLayout.LayoutParams)ivAetGuide2.getLayoutParams();
            ivAetGuide2Params.height=ivAetSize;
            ivAetGuide2Params.width=ivAetSize;
            int marginTop=(int)(337F*ratio);
            ivAetGuide2Params.leftMargin=marginLeft;
            ivAetGuide2Params.topMargin=marginTop;

        }
        if (index==2){
            int ivAetSize=(int)(105F*ratio);
            ImageView ivGuide3=(ImageView)view.findViewById(R.id.iv_aet_guide3);
            FrameLayout.LayoutParams ivGuide3Params=(FrameLayout.LayoutParams)ivGuide3.getLayoutParams();
            ivGuide3Params.height=ivAetSize;
            ivGuide3Params.width=ivAetSize;
            int marginTop=(int)(32F*ratio);
            ivGuide3Params.leftMargin=marginLeft;
            ivGuide3Params.topMargin=marginTop;
        }
    }
    class MyVPAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }
}
