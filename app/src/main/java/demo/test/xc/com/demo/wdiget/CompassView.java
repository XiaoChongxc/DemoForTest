package demo.test.xc.com.demo.wdiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * 指南针
 */
public class CompassView extends View {
    private static final String TAG = "CompassView";

    public CompassView(Context context) {
        super(context);
        init();
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    int mHeight, mWidth;   //view 的宽高
    int mRadius;           //指南针的半径， 一般是宽高 /2 -边框
    float cX, cY;//圆心坐标

    Paint mPaint;
    Paint mTextPaint;

    float mBorderWidth = 6;
    float mTextSize = 40;

    float  offsetDegrees=45;

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
//        mPaint.setPathEffect()


        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setTextSize(mTextSize);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        cX = mWidth / 2;
        cY = mHeight / 2;
        mRadius = (Math.min(mHeight, mWidth) - 100) / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScale(canvas);
        drawPointer(canvas);
    }

    //画背景的刻度
    private void drawScale(Canvas canvas) {
        Log.d(TAG, "drawScale: =====" + cX + "==" + cY + mRadius);
        //画框框
        canvas.drawCircle(cX, cY, mRadius, mPaint);
        canvas.drawCircle(cX, cY, mRadius-30, mPaint);
        //画 NSEW
//        2种方式，  1 直接drawText   2旋转画布
        canvas.drawText("N", cX, cY - mRadius +mTextSize +mBorderWidth, mTextPaint);
        canvas.rotate(90,cX,cY);
        canvas.drawText("E", cX, cY - mRadius +mTextSize +mBorderWidth, mTextPaint);
        canvas.rotate(90,cX,cY);
        canvas.drawText("S", cX, cY - mRadius +mTextSize +mBorderWidth, mTextPaint);
        canvas.rotate(90,cX,cY);
        canvas.drawText("W", cX, cY - mRadius +mTextSize +mBorderWidth, mTextPaint);
        canvas.rotate(90,cX,cY);


//        canvas.drawText("S", cX, cY + mRadius-mBorderWidth, mTextPaint);
//        canvas.drawText("W", cX - mRadius+mBorderWidth, cY, mTextPaint);
//        canvas.drawText("E", cX + mRadius-mBorderWidth-mTextSize, cY, mTextPaint);
        //画方位指示背景
//        Path path1 =new Path();
//        path1.moveTo(cX,cY -mRadius*2/3);
////        path1.lineTo(cX,cY*2/3,cX-100,cY);
//        path1.quadTo(cX,cY -mRadius*2/3,cX-50,cY);
//        path1.quadTo(cX-50,cY,cX,cY +mRadius*2/3);
//
//        path1.quadTo(cX,cY +mRadius*2/3,cX+50,cY);
//        path1.quadTo(cX+50,cY,cX,cY -mRadius*2/3);
//


//        path1.quadTo(cX,cY -mRadius*2/3,cX,cY+mRadius*2/3);
//path1.close();






    }

    //画指针
    private void drawPointer(Canvas canvas) {
        canvas.rotate(offsetDegrees,cX,cY);

        Path path1 =new Path();
        path1.moveTo(cX,cY -mRadius*2/3);
//        path1.lineTo(cX,cY*2/3,cX-100,cY);
        path1.quadTo(cX,cY -mRadius*2/3,cX-50,cY);
        path1.quadTo(cX-50,cY,cX,cY +mRadius*2/3);

        path1.quadTo(cX,cY +mRadius*2/3,cX+50,cY);
        path1.quadTo(cX+50,cY,cX,cY -mRadius*2/3);
        canvas.drawPath(path1,mPaint);
        canvas.rotate(-offsetDegrees,cX,cY);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
