package lijianchnag.birthdayapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lijianchnag.birthdayapp.bean.BirthDayBean;
import lijianchnag.birthdayapp.ui.BithdayImageFragment;
import lijianchnag.birthdayapp.utils.LogUtils;
import lijianchnag.birthdayapp.utils.SoundPoolUtils;
import lijianchnag.birthdayapp.utils.SystemBarUtils;
import lijianchnag.birthdayapp.view.alphaviewpager.ClipViewPager;
import lijianchnag.birthdayapp.view.alphaviewpager.ScalePageTransformer;
import lijianchnag.birthdayapp.view.alphaviewpager.adapter.BirthDayAdapter;
import lijianchnag.birthdayapp.view.listener.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    BithdayImageFragment bithdayImageFragment;
    @BindView(R.id.activity_main_iv_play)
    ImageView mIvPlay;
    @BindView(R.id.activity_main_iv_gif)
    ImageView mIvGif;
    @OnClick(R.id.activity_main_iv_play) void playmusic(){
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.pause();
            mIvPlay.setImageResource(R.mipmap.timg_play);
        }else{
            mMediaPlayer.start();
            mIvPlay.setImageResource(R.mipmap.timg_stop);
        }

    }
    Context mContext;
    @BindView(R.id.activity_mian_viewpager)
    ClipViewPager clipViewPager;
    @BindView(R.id.activity_main_layout)
    LinearLayout mLayoutMain;
    BirthDayAdapter birthDayAdapter;

    int[] mDrwableId = {R.mipmap.icon_b1,R.mipmap.icon_b2,R.mipmap.icon_b30,R.mipmap.icon_b3,R.mipmap.icon_b4,R.mipmap.icon_b5,R.mipmap.icon_b6,
                        R.mipmap.icon_b7,R.mipmap.icon_b8};

    int [] mContentId ={R.mipmap.birthday_1,R.mipmap.birthday_2,R.mipmap.birthday_3_0,R.mipmap.birthday_3,R.mipmap.birthday_4,

                        R.mipmap.birthday_5,R.mipmap.birthday_6,R.mipmap.birthday_7,R.mipmap.birthday_8
    };

    String [] mTitle = {"初识","重逢","机会","Be together","蜜月","危机Ⅰ","黄山","危机Ⅱ","Now&Future"};

    List<BirthDayBean>  mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SystemBarUtils.transparentStatusBar(this);
        mMediaPlayer= MediaPlayer.create(this, R.raw.birthday);
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mMediaPlayer.seekTo(0);
                mMediaPlayer.start();
            }
        });
        mContext =this;
        mIvGif.postDelayed(new Runnable() {
            @Override
            public void run() {
                Glide.with(mContext)
                        .load(R.raw.birthday1)
                        .into(mIvGif);
            }
        },300);
        mList = new ArrayList<>();
        for(int i =0;i<mDrwableId.length;i++){
            BirthDayBean birthDayBean = new BirthDayBean(mDrwableId[i],mContentId[i],mTitle[i]);
            mList.add(birthDayBean);
        }
        birthDayAdapter = new BirthDayAdapter(this,mList);
        clipViewPager.setAdapter(birthDayAdapter);
        clipViewPager.setPageTransformer(true, new ScalePageTransformer());
        clipViewPager.setOffscreenPageLimit(mList.size());
        clipViewPager.setCurrentItem(0);
        mLayoutMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return clipViewPager.dispatchTouchEvent(event);
            }

        });
        clipViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        birthDayAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LogUtils.d("~~~~~~~~~~~~~~·");
                bithdayImageFragment = new BithdayImageFragment();
                bithdayImageFragment.show(getSupportFragmentManager(),"bithdayImageFragment");
                bithdayImageFragment.setDrawId(mContentId[position]);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

//        bithdayImageFragment = new BithdayImageFragment();
//        bithdayImageFragment.show(getSupportFragmentManager(),"bithdayImageFragment");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.pause();

    }
}
