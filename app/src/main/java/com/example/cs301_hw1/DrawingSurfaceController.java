package com.example.cs301_hw1;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * AUTHOR: Max Woods
 *
 * DATA: 2/7/2022
 *
 * DESC: Modifies the GenericModels based on user input.
 */
public class DrawingSurfaceController implements View.OnTouchListener {

    private GenericModel selected_element;
    private DrawingSurfaceView real_view;

    SeekBar red_bar;
    SeekBar green_bar;
    SeekBar blue_bar;

    TextView selected_element_text;

    public DrawingSurfaceController(SeekBar red_bar, SeekBar green_bar,
                                    SeekBar blue_bar, TextView selected_element_text) {
        this.red_bar = red_bar;
        this.green_bar = green_bar;
        this.blue_bar = blue_bar;
        this.selected_element_text = selected_element_text;
    }

    // Checks through each model in the array and then checks if the user has clicked on it.
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        DrawingSurfaceView real_view = (DrawingSurfaceView) view;
        this.real_view = real_view;
        ArrayList<GenericModel> models = real_view.getModels();

        float x = motionEvent.getX();
        float y = motionEvent.getY();

        // Check if the user touched a model, and if so, update the currently selected model
        if (models.size() > 0) {
            for (int i = 0; i < models.size(); i++) {
                if (checkHitbox(real_view, x, y, models.get(i))) {
                    this.selected_element = models.get(i);
                    this.selected_element_text.setText("Selected Element: " +
                            this.selected_element.getName());
                    this.red_bar.setProgress(this.selected_element.getRed());
                    this.green_bar.setProgress(this.selected_element.getGreen());
                    this.blue_bar.setProgress(this.selected_element.getBlue());
                    break;
                }
            }
        }
        return true;
    }

    // Important helper method for checking if the user has touched an element.
    // Returns TRUE if the user has touched an element.
    // Returns FALSE otherwise.
    private boolean checkHitbox(DrawingSurfaceView real_view, float x,
                                float y, GenericModel sample_model) {

        // Convert model's percentages into actual pixels
        int x_pixels = (int)(sample_model.getLeft() * real_view.getWidth());
        int y_pixels = (int)(sample_model.getTop() * real_view.getHeight());
        int width_pixels = (int)(sample_model.getWidth() * real_view.getWidth());
        int height_pixels = (int)(sample_model.getHeight() * real_view.getHeight());

        // Now check if it's within the model's bounding box
        if ((x > x_pixels) && (x < x_pixels + width_pixels)) {
            if ((y > y_pixels) && (y < y_pixels + height_pixels)) {
                return true;
            }
        }
        return false;
    }

    // These methods are called by the SeekBarController objects when their values are
    // updates. It updates the color values in the GenericModel objects.
    public void setRed(int red) {
        if (this.selected_element != null) {
            this.selected_element.setRed(red);
            real_view.invalidate();
        }
    }
    public void setGreen(int green) {
        if (this.selected_element != null) {
            this.selected_element.setGreen(green);
            real_view.invalidate();
        }
    }
    public void setBlue(int blue) {
        if (this.selected_element != null) {
            this.selected_element.setBlue(blue);
            real_view.invalidate();
        }
    }
}
