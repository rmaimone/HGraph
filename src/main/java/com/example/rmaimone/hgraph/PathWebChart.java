package com.example.rmaimone.hgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by rmaimone on 14/09/2016.
 */
public class PathWebChart extends View {

    Paint mpaintLine;
    final int paintColor= getResources().getColor(R.color.gray);
    private ArrayList<Point> web_points = new ArrayList<Point>() ;




    public PathWebChart(Context context) {
        super(context);
        mpaintLine = new Paint();
        mpaintLine.setColor(paintColor);
        mpaintLine.setAntiAlias(true);
        mpaintLine.setStrokeWidth(2);
        mpaintLine.setAlpha(128);
        mpaintLine.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo((float)web_points.get(0).getCoordinateX(), (float)web_points.get(0).getCoordinateY());

        for (int i=0; i<web_points.size(); i++ ){

            if(i!=(web_points.size()-1)){

                  path.lineTo((float)web_points.get(i+1).getCoordinateX(),(float)web_points.get(i+1).getCoordinateY());
            }
            else {
                path.lineTo((float)web_points.get(0).getCoordinateX(),(float)web_points.get(0).getCoordinateY());
                path.close();
            }



        }
        canvas.drawPath(path, mpaintLine);

    }



    public void setPoints(ArrayList<Point> points){
        this.web_points = points;
    }
}
