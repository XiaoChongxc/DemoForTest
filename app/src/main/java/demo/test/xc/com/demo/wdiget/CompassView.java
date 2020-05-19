package demo.test.xc.com.demo.wdiget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Shader;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 指南针
 * <p>
 * 可以追加动画啥子的
 * PathMeasure    path 动画进度监听
 * setPathEffect   给画笔设置样式， 画虚线，画连串的爱心等等
 */
public class CompassView extends View {
    private static final String TAG = "CompassView";

    public CompassView(Context context) {
        super(context);
        init(context);
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    int mHeight, mWidth;   //view 的宽高
    int mRadius;           //指南针的半径， 一般是宽高 /2 -边框
    float cX, cY;//圆心坐标

    Paint mPaint;
    Paint mTextPaint;
    Paint mPaint2;
    Paint mScalePaint;

    float mBorderWidth = 6;
    float mTextSize = 100;

    float offsetDegrees = 0;

    float bg_point_width = 20;
    float bg_point_width2 = 15;

    float l_bg_point = 1 / 2f;
    float l_bg_point2 = 1 / 3f;
    private static final int sensitivity = 6;//变化灵敏度，数值越小，变化月灵敏


    SensorEventListener listener = new SensorEventListener() {
        float[] accelerometerValues = new float[3], magneticFieldValues = new float[3];

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometerValues = event.values;
            }
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magneticFieldValues = event.values;
            }

            float degree = calculateOrientation();

            if (Math.abs(offsetDegrees - degree) > sensitivity) {
                //改变偏移量
                offsetDegrees = degree;
                invalidate();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        private float calculateOrientation() {
            float[] values = new float[3];
            float[] R = new float[9];
            SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues);
            SensorManager.getOrientation(R, values);
            values[0] = (float) Math.toDegrees(values[0]);
            return -values[0];
        }
    };

private   boolean hasText=false;
    private boolean hasPoint = false;

    private void init(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(listener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);


        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
//        mPaint.setPathEffect()


        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setTextSize(mTextSize);

        mScalePaint = new Paint();
        mScalePaint.setColor(Color.BLUE);
        mScalePaint.setStyle(Paint.Style.STROKE);
//        mScalePaint.setStrokeWidth(40);
        mScalePaint.setAntiAlias(true);

        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(2000);
//        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatoinX = (float) animation.getAnimatedValue();

                invalidate();
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //結束之後開始畫文字
                hasText=true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ValueAnimator animator2 = ValueAnimator.ofFloat(0, 1);
        animator2.setDuration(2000);
//        animator2.start();
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatoinX2 = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        ValueAnimator animator5 = ValueAnimator.ofFloat(0, 1);
        animator5.setDuration(2000);
        animator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatoinX5 = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(animator,animator2,animator5);
////        animatorSet.play(animator2);
////        animatorSet.play(animator5);
        animatorSet.play(animator5).before(animator2).after(2000).after(animator);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束， 开始画指针
                if (!hasPoint) {
                    hasPoint = true;
                    invalidate();
                    animatorSet2.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

         ValueAnimator animator3 = ValueAnimator.ofFloat(0, 1);
        animator3.setDuration(2000);
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatoinX3 = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        ValueAnimator animator4 = ValueAnimator.ofFloat(0, 1);
        animator3.setDuration(1000);
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatoinX4 = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animatorSet2.play(animator4 ).before(animator3);
    }

    AnimatorSet  animatorSet2=new AnimatorSet();

    PathMeasure pathMeasure;
    private float animatoinX = 0;
    private float animatoinX2 = 0;
    private float animatoinX3 = 0;
    private float animatoinX4 = 0;
    private float animatoinX5 = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        cX = mWidth / 2;
        cY = mHeight / 2;
        mRadius = (Math.min(mHeight, mWidth) - 100) / 2;
    }

    private Path pointPath;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScale(canvas);

        if(hasPoint){
            drawPointer(canvas);
        }
    }

    RectF rectF1;
    RectF rectF2;


    //画背景的刻度
    private void drawScale(Canvas canvas) {
        //画框框
//        canvas.drawCircle(cX, cY, mRadius, mPaint);
//        canvas.drawCircle(cX, cY, mRadius - 30, mPaint);
        if (rectF1 == null)
            rectF1 = new RectF(cX - mRadius, cY - mRadius, cX + mRadius, cY + mRadius);
        if (rectF2 == null)
            rectF2 = new RectF(cX - mRadius + 30, cY - mRadius + 30, cX + mRadius - 30, cY + mRadius - 30);


        canvas.drawArc(rectF1, 0 - 180 * animatoinX, 180 * animatoinX * 2, false, mPaint);
        canvas.drawArc(rectF2, 180 - 180 * animatoinX, 180 * animatoinX * 2, false, mPaint);


        //画 NSEW
//        2种方式，  1 直接drawText   2旋转画布
        if(hasText) {
            float x1 = cX - mTextPaint.measureText("N", 0, 1) / 2;
            float x2 = cX - mTextPaint.measureText("E", 0, 1) / 2;
            float x3 = cX - mTextPaint.measureText("S", 0, 1) / 2;
            float x4 = cX - mTextPaint.measureText("W", 0, 1) / 2;

            canvas.drawText("N", x1, cY - mRadius + mTextSize + mBorderWidth +20, mTextPaint);
            canvas.rotate(90, cX, cY);
            canvas.drawText("E", x2, cY - mRadius + mTextSize + mBorderWidth+20, mTextPaint);
            canvas.rotate(90, cX, cY);
            canvas.drawText("S", x3, cY - mRadius + mTextSize + mBorderWidth+20, mTextPaint);
            canvas.rotate(90, cX, cY);
            canvas.drawText("W", x4, cY - mRadius + mTextSize + mBorderWidth+20, mTextPaint);
            canvas.rotate(90, cX, cY);
        }


        //画  刻度
        Path shape=new Path();
//        shape.addCircle(0,0,5, Path.Direction.CCW);
        shape.addRect(0,0,8,12, Path.Direction.CCW);
        //计算   把圆周分成 24等分
        float  advance = (float) (2*Math.PI *(mRadius-40) /24);
        PathDashPathEffect pathDashPathEffect =new PathDashPathEffect(shape,advance,0, PathDashPathEffect.Style.MORPH);
        mScalePaint.setPathEffect(pathDashPathEffect);

//                DashPathEffect pathDashPathEffect =new DashPathEffect(new float[]{10,10},1);
//        mScalePaint.setPathEffect(pathDashPathEffect);
//        canvas.drawPath(shape,mScalePaint);
        canvas.drawArc(new RectF(cX-mRadius+40,cY-mRadius+40,cX+mRadius-40,cY+mRadius-40),0,360f*animatoinX5,false,mScalePaint);
//        canvas.draw(cX,cY,mRadius-40,mScalePaint);


        //画方位指示背景
        canvas.save();
        Path path1 = getPath(l_bg_point, bg_point_width);
//        canvas.drawPath(path1, mPaint);
        drawAPath(path1, canvas);

        canvas.rotate(45, cX, cY);
        Path path2 = getPath(l_bg_point2, bg_point_width2);
//        canvas.drawPath(path2, mPaint);
        drawAPath(path2, canvas);


        canvas.rotate(45, cX, cY);
        Path path3 = getPath(l_bg_point, bg_point_width);
//        canvas.drawPath(path3, mPaint);
        drawAPath(path3, canvas);

        canvas.rotate(45, cX, cY);
        Path path4 = getPath(l_bg_point2, bg_point_width2);
        drawAPath(path4, canvas);
//        canvas.drawPath(path4, mPaint);
        canvas.restore();
    }

    private void drawAPath(Path path, Canvas canvas) {
        Path dstPath = new Path();
        path.moveTo(0, 0);
        pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        float startD = 0;
        float stopD = length * animatoinX2;
        pathMeasure.getSegment(startD, stopD, dstPath, true);
        canvas.drawPath(dstPath, mPaint);
    }
    private void drawAPath2(Path path, Canvas canvas,float  animatoinX,Paint mPaint) {
        Path dstPath = new Path();
        path.moveTo(0, 0);
        pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        float startD = 0;
        float stopD = length * animatoinX;
        pathMeasure.getSegment(startD, stopD, dstPath, true);
        canvas.drawPath(dstPath, mPaint);
    }


    //画指针
    private void drawPointer(Canvas canvas) {
        canvas.rotate(offsetDegrees *animatoinX4, cX, cY);
        if (pointPath == null)
            pointPath = getPath(2 / 3f, 40);
        mPaint2.setShader(new LinearGradient(0, cY - mRadius * 2 / 3f, 0, cY + mRadius * 2 / 3f, new int[]{Color.BLUE, Color.RED}, new float[]{0.5f, 0.4999999f}, Shader.TileMode.CLAMP));
        drawAPath2(pointPath,canvas,animatoinX3,mPaint2);
//        canvas.drawPath(pointPath, mPaint2);
        canvas.rotate(-offsetDegrees*animatoinX4, cX, cY);
    }

    private Path getPath(float scale, float point_width) {
        Path path1 = new Path();
        path1.moveTo(cX, cY - mRadius * scale);
//        path1.lineTo(cX,cY*2/3,cX-100,cY);
        path1.quadTo(cX, cY - mRadius * scale, cX - point_width, cY);
        path1.quadTo(cX - point_width, cY, cX, cY + mRadius * scale);

        path1.quadTo(cX, cY + mRadius * scale, cX + point_width, cY);
        path1.quadTo(cX + point_width, cY, cX, cY - mRadius * scale);
        return path1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
