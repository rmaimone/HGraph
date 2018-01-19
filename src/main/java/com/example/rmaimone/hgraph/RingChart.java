package com.example.rmaimone.hgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by rmaimone on 14/09/2016.
 */
public class RingChart extends View {

    Paint mPaint;
    Canvas mCanvas;



    // We want a circle with height and width = 400px, so calculate coordinates where to start to draw the circle

    public RingChart(Context context) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        int strokeWidth= 40;  // or whatever
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(getResources().getColor(R.color.color_ring));   //color.RED



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawCircle((canvas.getWidth()/2), (canvas.getHeight()/2), canvas.getWidth()/4, mPaint);
    }
}
