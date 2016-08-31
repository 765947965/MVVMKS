package net.yr.mvvm.app.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/8/24 14:08
 */
public class PeakView extends View {

    private int min, max, round;
    private int color = Color.parseColor("#FFFFFF");

    public PeakView(Context context) {
        super(context);
    }

    public PeakView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PeakView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        int valueint0 = 0xff000000 | new Random().nextInt(0x00ffffff), valueint1 = 0xff000000 | new Random().nextInt(0x00ffffff);

        min = Math.min(valueint0, valueint1);
        max = Math.max(valueint0, valueint1);
        ValueAnimator va = ValueAnimator.ofInt(1, Math.min(getWidth(), getHeight()) / 2, 1);
        va.setDuration(1900);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                round = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        va.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (min != 0) {
            Paint paint = new Paint();
            // 抗锯齿
            paint.setAntiAlias(true);
            // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
            paint.setDither(true);
            paint.setStyle(Paint.Style.FILL);
            RadialGradient rg = new RadialGradient(getWidth() / 2, getHeight() / 2, round, min, max, Shader.TileMode.REPEAT);
            paint.setShader(rg);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, round, paint);
        }
        super.onDraw(canvas);
    }
}
