package lijianchnag.birthdayapp.view.alphaviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class TouchedViewPager extends ViewPager
{

	private float	mDownX;
	private float	mDownY;
	private boolean isCanScroll = false;  
	
	public TouchedViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	 public void setScanScroll(boolean isCanScroll){  
	        this.isCanScroll = isCanScroll;  
	    }  
	  
	  
//	    @Override  
//	    public void scrollTo(int x, int y){  
//	        if (isCanScroll){  
//	        	
//	        	Thread.currentThread();  
//	            try {
//					Thread.sleep(300);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}  			
//	         super.scrollTo(x, y); 
//	        		
//	        	
//	        }  
//	    }
	public TouchedViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{

		// 请求父容器不要拦截touch
		// true：请求不拦截
		// false:请求拦截

		int currentItem = getCurrentItem();

		switch (ev.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				mDownX = ev.getX();
				mDownY = ev.getY();
				getParent().requestDisallowInterceptTouchEvent(true);
				break;
			case MotionEvent.ACTION_MOVE:
				float moveX = ev.getX();
				float moveY = ev.getY();

				// 从右往左滑动--> downX >= moveX
				// 从左往右滑动--> downX < moveX

				// 如果是水平滑动时

				if (Math.abs(moveX - mDownX) > Math.abs(moveY - mDownY))
				{
					// 水平滑动

//					if (currentItem == (getAdapter().getCount() - 1) && mDownX >= moveX)
//					{
//						// 如果当前viewpager选中最后一个页面，从右往左滑动，滑动的是外部的Viewpager，外部的要拦截touch
//						//这是关键
//					
//						getParent().requestDisallowInterceptTouchEvent(false);
//					}
//					else if (currentItem == 0 && mDownX < moveX)
//					{
//						// 如果当前viewpager选中第一个页面，从左往右滑动，滑动的是外部的view(viewpager，SlidingMenu),让外部去拦截
//						getParent().requestDisallowInterceptTouchEvent(false);
//					}
//					else
//					{
//						// 3. 其他情况都是自己响应
						getParent().requestDisallowInterceptTouchEvent(true);
//					}
				}
				else
				{
					getParent().requestDisallowInterceptTouchEvent(false);
				}
				break;
			case MotionEvent.ACTION_UP:

				break;
			default:
				break;
		}

		return super.dispatchTouchEvent(ev);
	}
}
