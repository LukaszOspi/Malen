package com.example.malen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MaleAufBitmap extends View implements View.OnTouchListener {

    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    Paint paint = new Paint();
    private boolean isInitialized = false;

private float downX = 0, downY = 0, upX = 0, upY = 0;


    public MaleAufBitmap(Context context) {
        super(context);

        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        setClickable(true);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        this.setOnTouchListener(this);
    }
    public void onDraw(Canvas canvas) {

        if (!isInitialized) {
          init();
        }
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    private void init() {
    bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getAction();

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                upX = event.getX();
                upY = event.getY();
                bitmapCanvas.drawLine(downX, downY, upX, upY, paint);
                downX = upX;
                downY = upY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return false;
    }
}
