package com.example.cs301_hw1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * AUTHOR: Max Woods
 *
 * DATA: 2/7/2022
 *
 * DESC: Model class for containing information about elements in the DrawingSurfaceView. Contains
 * their size, position, color, name, and image id.
 */
public class GenericModel {

    private float left; // Percentage of canvas size
    private float top;
    private float width;
    private float height;

    private int red;
    private int green;
    private int blue;

    private int image_id;

    private String name;


    private DrawingSurfaceView view;

    public GenericModel(float left, float top, float width, float height,
                        int red, int green, int blue,
                        int image_id, String name, DrawingSurfaceView view) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
        this.image_id = image_id;
        this.view = view;
    }

    public float getLeft() {
        return left;
    }

    public float getTop() {
        return top;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getImageId() {
        return image_id;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public String getName() {
        return name;
    }

    public int getBlue() {
        return blue;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
