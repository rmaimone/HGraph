package com.example.rmaimone.hgraph;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rmaimone on 09/08/2016.
 */
public class TextDotGraph extends View implements View.OnTouchListener{



    private ArrayList<HgraphData> labels = new ArrayList<HgraphData>();
    private ArrayList<Point> textcoordinates = new ArrayList<Point>() ;
    private ArrayList<Point> dotcoordinates = new ArrayList<Point>();
    private Canvas mCanvas;
    RingChart ring;
    int radius_text;
    int radius_dot;
    int ring_size;





    Paint mpaintText;
    Paint mpaintUnhealthyText;
    Paint mpaintHealthy;
    Paint mpaintUnhealthy;
    Path myPath;
    Bitmap mBitmap;



    public TextDotGraph(Context context) {
        super(context);
        int textColor= Color.BLACK;
        int text_unhealthyColor =  context.getResources().getColor(R.color.light_red);
        int healthyColor = context.getResources().getColor( R.color.green_sea_dark);
        int unhealthyColor = context.getResources().getColor( R.color.light_red);

        mpaintText = new Paint();
        mpaintText.setTextSize(getResources().getDimension(R.dimen.textSize));
        mpaintText.setColor(textColor);

        mpaintUnhealthyText = new Paint();
        mpaintUnhealthyText.setTextSize(getResources().getDimension(R.dimen.textSize));
        mpaintUnhealthyText.setColor(text_unhealthyColor);

        mpaintHealthy = new Paint();
        mpaintHealthy.setColor(healthyColor);
        mpaintHealthy.setAntiAlias(true);
        mpaintHealthy.setStrokeWidth(3);
        mpaintHealthy.setStyle(Paint.Style.FILL);
        mpaintHealthy.setStrokeJoin(Paint.Join.ROUND);
        mpaintHealthy.setStrokeCap(Paint.Cap.ROUND);


        mpaintUnhealthy = new Paint();
        mpaintUnhealthy.setColor(unhealthyColor);
        mpaintUnhealthy.setAntiAlias(true);
        mpaintUnhealthy.setStrokeWidth(3);
        mpaintUnhealthy.setStyle(Paint.Style.FILL);
        mpaintUnhealthy.setStrokeJoin(Paint.Join.ROUND);
        mpaintUnhealthy.setStrokeCap(Paint.Cap.ROUND);


        //take dimension of circle graph(green area) that you put into layout used to draw the chart


    }


    public int addItem(HgraphData item){
        labels.add(item);
        return labels.size()-1;

    }




    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        System.out.println(canvas.getWidth()+" "+canvas.getHeight());
        //draw text of categories:
        // take the size of categories;
        int sizeLabels = labels.size(); // size label example 10 categories food

        //define variable useful to calculate next coordinates
        int originX = canvas.getWidth()/2;        //example 360
        int originY = canvas.getHeight()/2;        //example 640

        //move text a litle bit more at left
        int origin2X = (canvas.getWidth()/2)-40;        //example 360


        radius_text = canvas.getWidth()/3;
        radius_dot = canvas.getWidth()/4;


        // how many rows do you neeed to place text? sizeLabels/2 rows
        int rows = (sizeLabels -2)/2;  // number of rows is 4 beacuse we want put also point  in the center  top and center bottom


        //where you put the text? around the chart,so take the height of the chart and divide in rows:
        int heightRows = ring_size/rows;  // each rows has height 400/4 = 100 px

        // calculate the distance among points in angle
        double angleDistance = 360/sizeLabels;
        int delta = 30;

    //define the coordinates to allocate points, then add points to the array of text coordinates
       for(int i=0; i<sizeLabels; i++){
           double x = origin2X + ((radius_text+delta) * Math.cos(Math.toRadians(angleDistance*i)));
           double y = originY + ((radius_text+delta) * Math.sin(Math.toRadians(angleDistance*i)));
           textcoordinates.add(new Point(x,y, labels.get(i).getLabel()));
       }

      //same thing we have to do with dots
      // if value of data is 0, so the quantity taken is right, the dots is the ring area


      for(int i =0; i<sizeLabels; i++)  {

          if(labels.get(i).getHealthy()==0){
              double x = originX + ((radius_dot) * Math.cos(Math.toRadians(angleDistance*i)));
              double y = originY + ((radius_dot) * Math.sin(Math.toRadians(angleDistance*i)));
              dotcoordinates.add(new Point(x,y, labels.get(i).getLabel()));
          }
          if(labels.get(i).getHealthy()>0){
              int d = 50;
              double x = originX + ((radius_dot+d) * Math.cos(Math.toRadians(angleDistance*i)));
              double y = originY + ((radius_dot+d) * Math.sin(Math.toRadians(angleDistance*i)));
              dotcoordinates.add(new Point(x,y, labels.get(i).getLabel()));
          }

          if(labels.get(i).getHealthy()<0){
              double x = originX + ((radius_dot-50) * Math.cos(Math.toRadians(angleDistance*i)));
              double y = originY + ((radius_dot-50) * Math.sin(Math.toRadians(angleDistance*i)));
              dotcoordinates.add(new Point(x,y, labels.get(i).getLabel()));
          }


      }



       // once we defined the coordinates we can draw text aroun the chart
        for(int i=0; i<sizeLabels; i++){

            if (labels.get(i).getHealthy()!=0){
                canvas.drawText(labels.get(i).getLabel(), (float)textcoordinates.get(i).getCoordinateX(),(float) textcoordinates.get(i).getCoordinateY(), mpaintUnhealthyText);
            }
            else {
                canvas.drawText(labels.get(i).getLabel(), (float)textcoordinates.get(i).getCoordinateX(),(float) textcoordinates.get(i).getCoordinateY(), mpaintText);
            }

        }

        for(int i=0; i<sizeLabels; i++){

            if (labels.get(i).getHealthy()!=0){
                canvas.drawCircle((float) dotcoordinates.get(i).getCoordinateX(), (float) dotcoordinates.get(i).getCoordinateY(), getResources().getDimension(R.dimen.dotSize), mpaintUnhealthy);
            }
            else {
                canvas.drawCircle((float) dotcoordinates.get(i).getCoordinateX(), (float) dotcoordinates.get(i).getCoordinateY(), getResources().getDimension(R.dimen.dotSize), mpaintHealthy);
            }


        }


    }

    public ArrayList<Point> getDotCoordinates() {
        return dotcoordinates;
    }

    public void setDotCoordinates(ArrayList<Point> dotcoordinates) {
        this.dotcoordinates = dotcoordinates;
    }

    public ArrayList<Point> getTextCoordinates() {
        return textcoordinates;
    }

    public void setTextCoordinates(ArrayList<Point> textcoordinates) {
        this.textcoordinates = textcoordinates;
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();

            for (Point p: dotcoordinates){
                if (inCircle(x, y ,p.getCoordinateX(),p.getCoordinateY(),radius_dot)){
                    String label = p.getLabel();
                    Toast.makeText(getContext(), "circle touched is: "+ label,Toast.LENGTH_LONG).show();
                }
            }

        }

        return true;
    }

    private boolean inCircle(float x, float y, double circleCenterX, double circleCenterY, float circleRadius) {
        double dx = Math.pow(x - circleCenterX, 2);
        double dy = Math.pow(y - circleCenterY, 2);

        if ((dx + dy) < Math.pow(circleRadius, 2)) {
            return true;
        } else {
            return false;
        }
    }
}
