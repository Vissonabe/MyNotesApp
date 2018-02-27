package com.example.viswanathankp.mynotes.View;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by viswanathan.kp on 27/02/18.
 */

public class GrayScaleImageView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener {


    public GrayScaleImageView(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public GrayScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public GrayScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    private void setGrayScale(ImageView v)
    {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
        v.setColorFilter(cf);
        v.setImageAlpha(128);
    }

    private void resetGrayScale(ImageView v)
    {
        v.setColorFilter(null);
        v.setImageAlpha(255);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: setGrayScale(this); break;
            case MotionEvent.ACTION_UP: resetGrayScale(this);break;
        }
        return true;
    }
}
