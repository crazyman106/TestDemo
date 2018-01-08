package com.example.administrator.gmap;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @author lijunjie on 2017/12/13 0013.
 * @description
 */

public class ScrollGroup extends ViewGroup {
    private Scroller mScroller;
    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;
    /**
     * 手按下时的屏幕坐标
     */
    private float mXDown;

    /**
     * 手当时所处的屏幕坐标
     */
    private float mXMove;
    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;
    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;
    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;

    public ScrollGroup(Context context) {
        this(context, null);
    }

    public ScrollGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
        Log.e("mTouchSlop", mTouchSlop + "---");
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            Log.e("scroller", mScroller.getCurrX() + "--");
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            if (childCount == 0) {
                return;
            }
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                child.layout(i * child.getMeasuredWidth(), 0, (i + 1) * child.getMeasuredWidth(), child.getMeasuredHeight());
            }
            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(childCount - 1).getRight();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("event", "ScrollGroup" + "--" + "dispatchTouchEvent_down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("event", "ScrollGroup" + "--" + "dispatchTouchEvent_move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("event", "ScrollGroup" + "--" + "dispatchTouchEvent_up");
                break;
            default:
        }
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("event", "ScrollGroup" + "--" + "onInterceptTouchEvent_down");
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                return true;
//                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("event", "ScrollGroup" + "--" + "onInterceptTouchEvent_move");
                mXMove = ev.getRawX();
                mXLastMove = mXMove;
                float diff = Math.abs(mXMove - mXDown);
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件 ,消费,不在传递至childview中
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
//                return true;
            case MotionEvent.ACTION_UP:
                Log.e("event", "ScrollGroup" + "--" + "onInterceptTouchEvent_up");
                break;
            default:
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.e("event", "ScrollGroup" + "--" + "onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("event", "ScrollGroup" + "--" + "onTouchEvent_down");
                mXDown = event.getRawX();
                mXLastMove = mXDown;
                return true;
//                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("event", "ScrollGroup" + "--" + "onTouchEvent_move");
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                Log.e("scroll_move", getScrollX() + "---");
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("event", "ScrollGroup" + "--" + "onTouchEvent_up");
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                // 核心代码
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                Log.e("scroll--", getScrollX() + "////" + dx);
                invalidate();
                break;
            default:
        }
        return super.onTouchEvent(event);
    }
}
