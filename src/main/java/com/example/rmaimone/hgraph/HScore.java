package com.example.rmaimone.hgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.provider.SyncStateContract;
import android.view.View;

import java.util.List;

/**
 * Created by rmaimone on 15/09/2016.
 */


public class HScore extends View {

    int score;
    int scoreColor;
    Paint paintScoreText;

    public HScore(Context context) {
        super(context);

        scoreColor = getResources().getColor(R.color.black);

        paintScoreText = new Paint();
        paintScoreText.setTextSize(getResources().getDimension(R.dimen.scoreTextSize));
        paintScoreText.setColor(scoreColor);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //define variable useful to calculate text coordinates
        int originX = canvas.getWidth()/2;
        int originY = canvas.getHeight()/2;

        float textSize = paintScoreText.getTextSize();

        //we want write text in the middle, so we consider :
        canvas.drawText(String.valueOf(score), originX-(textSize/2), originY+(textSize/2), paintScoreText);


    }

}
