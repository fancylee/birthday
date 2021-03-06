package lijianchnag.birthdayapp.view.alphaviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import lijianchnag.birthdayapp.R;
import lijianchnag.birthdayapp.utils.LogUtils;


/**
 * Created by HanHailong on 15/9/27.
 *
 */
public class ClipViewPager extends TouchedViewPager {

    //默认距离
    private final static float DISTANCE = 10;
    private float downX;
    private float downY;
 
    public ClipViewPager(Context context) {
        super(context);
    }
 
    public ClipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            downX = ev.getX();
            downY = ev.getY();
        }else if (ev.getAction() == MotionEvent.ACTION_UP) {

            float upX = ev.getX();
            float upY = ev.getY();
            //如果 up的位置和down 的位置 距离 > 设置的距离,则事件继续传递,不执行下面的点击切换事件
            if(Math.abs(upX - downX) > DISTANCE || Math.abs(upY - downY) > DISTANCE){
                return super.dispatchTouchEvent(ev);
            }

            View view = viewOfClickOnScreen(ev);
            if (view != null) {
                try{
                    int index = (Integer) view.getTag();
                    LogUtils.d("index"+index);
                    if (getCurrentItem() != index) {
                        setCurrentItem(index);
                    }
                }catch (Exception e){

                }

            }else{
//                LogUtil.d("點擊是空白區域");
                return  false;
            }
        }
        return super.dispatchTouchEvent(ev);
    }
 
    /**
     * @param ev
     * @return
     */
    private View viewOfClickOnScreen(MotionEvent ev) {
        int childCount = getChildCount();
        int currentIndex = getCurrentItem();
        int[] location = new int[2];
        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            int position = (Integer) v.getTag(R.id.tag_first);
            v.getLocationOnScreen(location);
            int minX = location[0];
            int minY = location[1];

            int maxX = location[0] + v.getWidth();
            int maxY = location[1] + v.getHeight();

            if(position < currentIndex){
                maxX -= v.getWidth() * (1 - ScalePageTransformer.MIN_SCALE) * 0.5 + v.getWidth() * (Math.abs(1 - ScalePageTransformer.MAX_SCALE)) * 0.5;
                minX -= v.getWidth() * (1 - ScalePageTransformer.MIN_SCALE) * 0.5 + v.getWidth() * (Math.abs(1 - ScalePageTransformer.MAX_SCALE)) * 0.5;
            }else if(position == currentIndex){
                minX += v.getWidth() * (Math.abs(1 - ScalePageTransformer.MAX_SCALE));
            }else if(position > currentIndex){
                maxX -= v.getWidth() * (Math.abs(1 - ScalePageTransformer.MAX_SCALE)) * 0.5;
                minX -= v.getWidth() * (Math.abs(1 - ScalePageTransformer.MAX_SCALE)) * 0.5;
            }
            float x = ev.getRawX();
            float y = ev.getRawY();

            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                return v;
            }
        }
        return null;
    }
}