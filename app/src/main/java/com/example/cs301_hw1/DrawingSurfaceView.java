package com.example.cs301_hw1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import androidx.annotation.ColorInt;

import java.util.ArrayList;

/**
 * AUTHOR: Max Woods
 *
 * DATE: 2/7/2022
 *
 * DESC: This class manages the drawing of models onto the Surface View. Contains an ArrayList
 * of GenericModel objects to be drawn.
 */
public class DrawingSurfaceView extends SurfaceView {

    private Canvas canvas;
    private ArrayList<GenericModel> models;

    private Bitmap background;

    public DrawingSurfaceView(Context context, AttributeSet attrs) {

        super(context, attrs);
        setWillNotDraw(false);

        this.canvas = this.getHolder().lockCanvas();
        this.models = new ArrayList<GenericModel>();

        this.background = BitmapFactory.decodeResource(this.getResources(),R.drawable.background);
    }


   @Override
    public void onDraw(Canvas canvas) {

        // This is painfully resource intensive, and there are definitely better ways
        // of doing this.
        this.background = Bitmap.createScaledBitmap(this.background,canvas.getWidth(),
                canvas.getHeight(),true);
        canvas.drawBitmap(background,0,0,null);

        Paint sample_paint = new Paint();
        sample_paint.setARGB(255,255,0,0);

        if (this.models.size() > 0) {
            // forEach() is not supported in this API version :(
            for (int i = 0; i < models.size(); i++) {
                drawModel(canvas, models.get(i));
            }
        }
    }

    // Draws a GenericModel object on the screen.
    public void drawModel(Canvas canvas, GenericModel model) {

        Bitmap image = BitmapFactory.decodeResource(this.getResources(),model.getImageId());

        /**
         * External Citation
         * Data: 8 February 2022
         * Problem: Could not figure out how to adjust color of bitmap images
         *
         * Resource: https://gamedev.stackexchange.com/questions/5393/how-do-i-blend-a-bitmap-with-a-color
         * Solution: I used the exampled code from the post.
         */
        image = Bitmap.createScaledBitmap(image,
                (int)(model.getWidth()*canvas.getWidth()),
                (int)(model.getHeight()*canvas.getHeight()),true);

        Paint p = new Paint();

        @ColorInt
        int p_color = Color.argb(255,model.getRed(),model.getGreen(),model.getBlue());
        ColorFilter filter = new LightingColorFilter(p_color,0); //Citation needed
        p.setColorFilter(filter);

        canvas.drawBitmap(image,
                model.getLeft()*canvas.getWidth(),
                model.getTop()*canvas.getHeight(),
                p);

    }

    // Adds a model to be drawn by the view.
    public void addModel(GenericModel model) {
        this.models.add(model);
    }

    public ArrayList<GenericModel> getModels() {
        return models;
    }
}
