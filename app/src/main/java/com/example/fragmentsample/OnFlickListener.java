package com.example.fragmentsample;

import android.view.MotionEvent;
import android.view.View;

public abstract class OnFlickListener {

    public static final int FLICK_LEFT = 0;
    public static final int FLICK_RIGHT = 1;

    private float thresholdX = 150f;

    private float x1;
    private float x2;

    public OnFlickListener(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        checkFilcked();
                        v.performClick();
                        break;
                }
                return true;
            }
        });
    }

    public OnFlickListener(View view, float threshold) {
        this(view);
        thresholdX = threshold;
    }

    private void checkFilcked() {
        float diff = x2 - x1;

        if (Math.abs(diff) > thresholdX) {
            if (diff > 0) {
                /* Right */
                onFlick(FLICK_RIGHT);
            } else {
                /* Left */
                onFlick(FLICK_LEFT);
            }
        }
    }

    public abstract void onFlick(int type);
}
