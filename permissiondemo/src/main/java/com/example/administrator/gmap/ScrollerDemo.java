package com.example.administrator.gmap;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @author lijunjie on 2017/12/13 0013.
 * @description
 */

public class ScrollerDemo extends Activity {

    private ScrollView scrollView;
    TextView button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroller_view);

        button = (TextView) findViewById(R.id.scrollview_child);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("event", "MainActivity" + "--" + "onTouchEvent_down");
//                return false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("event", "MainActivity" + "--" + "onTouchEvent_move");
//                return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("event", "MainActivity" + "--" + "onTouchEvent_up");
//                return false;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("event", "MainActivity" + "--" + "dispatchTouchEvent_down");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("event", "MainActivity" + "--" + "dispatchTouchEvent_move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("event", "MainActivity" + "--" + "dispatchTouchEvent_up");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
