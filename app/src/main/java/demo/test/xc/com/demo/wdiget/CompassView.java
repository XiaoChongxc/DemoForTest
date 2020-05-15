package demo.test.xc.com.demo.wdiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * 指南针
 *
 */
public class CompassView extends View {
    private static final String TAG = "CompassView";

    public CompassView(Context context) {
        super(context);
        init();
    }

    public CompassView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompassView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    int  mHeight,mWidth;   //view 的宽高
    int mRadius;           //指南针的半径， 一般是宽高 /2 -边框
    float  cX,cY ;//圆心坐标

    Paint mPaint;
    Paint  mTextPaint;

    float  mBorderWidth=6;
    float  mTextSize=40;

    private   void  init(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
//        mPaint.setPathEffect()


        mTextPaint=new Paint();
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setTextSize(mTextSize);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           mHeight=MeasureSpec.getSize(heightMeasureSpec);
          mWidth=MeasureSpec.getSize(widthMeasureSpec);
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        cX=mWidth/2;
        cY=mHeight/2;
        mRadius =(Math.min(mHeight,mWidth) - 30) /2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScale(canvas);
        drawPointer(canvas);
    }

    //画背景的刻度
    private  void  drawScale(Canvas canvas){
        Log.d(TAG, "drawScale: ====="+cX+"=="+cY+mRadius);
        //画框框
        canvas.drawCircle(cX,cY,mRadius,mPaint);

        //画 NSEW
//        2种方式，  1 直接drawText   2旋转画布
        canvas.drawText("N",cX,cY-mRadius,mTextPaint);
        canvas.drawText("S",cX,cY+mRadius,mTextPaint);
        canvas.drawText("W",cX-mRadius,cY,mTextPaint);
        canvas.drawText("E",cX+mRadius,cY,mTextPaint);
        //画方位指示背景


    }
    //画指针
    private void  drawPointer(Canvas canvas){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
