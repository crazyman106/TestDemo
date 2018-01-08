package com.example.administrator.gmap;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author lijunjie on 2017/12/14 0014.
 * @description
 */

public class CusTextView extends android.support.v7.widget.AppCompatTextView {
    public CusTextView(Context context) {
        this(context, null);
    }

    public CusTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("event", "CusTextView" + "--" + "onTouchEvent_down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("event", "CusTextView" + "--" + "onTouchEvent_move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("event", "CusTextView" + "--" + "onTouchEvent_up");
//                return false;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("event", "CusTextView" + "--" + "dispatchTouchEvent_down");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("event", "CusTextView" + "--" + "dispatchTouchEvent_move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("event", "CusTextView" + "--" + "dispatchTouchEvent_up");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
