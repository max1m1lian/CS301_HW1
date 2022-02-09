package com.example.cs301_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * AUTHOR: Max Woods
 *
 * DATA: 2/7/2022
 *
 * DESC: I couldn't remember how exactly the listeners were supposed to work with objects drawn
 * on the surface view, so I decided to opt for a different method. I used GenericModel objects
 * which contain the dimensions and positions of each model drawn on the screen. My
 * DrawingSurfaceController class then detects if any touched spots are within any of the boundaries
 * of any of the models included in the DrawingSurfaceView in order to select them. The rest is
 * simple enough.
 *
 * !IMPORTANT!: This program is extremely laggy. It is drawing quite a few bitmap images at a time.
 * This could definitely be made a lot better, but I'm too lazy to fix it.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawingSurfaceView drawing_view = findViewById(R.id.drawing_view);

        // Linking models to views, and views to controllers
        generateModels(drawing_view);

        SeekBar red_bar = findViewById(R.id.red_seekbar);
        SeekBar green_bar = findViewById(R.id.green_seekbar);
        SeekBar blue_bar = findViewById(R.id.blue_seekbar);

        TextView selected_element_text = findViewById(R.id.selected_element);

        DrawingSurfaceController drawing_view_controller = new DrawingSurfaceController(red_bar,
                green_bar,blue_bar,selected_element_text);

        red_bar.setOnSeekBarChangeListener(new SeekBarController(drawing_view_controller));
        green_bar.setOnSeekBarChangeListener(new SeekBarController(drawing_view_controller));
        blue_bar.setOnSeekBarChangeListener(new SeekBarController(drawing_view_controller));

        drawing_view.setOnTouchListener(drawing_view_controller);

        drawing_view.invalidate();


    }

    // Helper method for generating a bunch of models to be drawn on the screen. Adds the
    // models to the given DrawingSurfaceView.
    private void generateModels(DrawingSurfaceView drawing_view) {
        GenericModel banana = new GenericModel(
                0.35f,0.6f,0.15f,0.35f,
               255,255,0,
               R.drawable.banana, "Banana", drawing_view);

        GenericModel apple = new GenericModel(
                0.2f,0.7f,0.15f,0.2f,
               196,40,43,
               R.drawable.apple, "Apple", drawing_view);

        GenericModel pineapple = new GenericModel(
                0.05f,0.25f,0.15f,0.65f,
                245,156,75,
                R.drawable.pineapple, "Pineapple", drawing_view);

        GenericModel strawberry = new GenericModel(
                0.55f,0.7f,0.1f,0.1f,
                228,74,57,
                R.drawable.strawberry, "Strawberry", drawing_view);

        GenericModel watermelon = new GenericModel(
                0.65f,0.7f,0.3f,0.3f,
                190,210,80,
                R.drawable.watermelon, "Watermelon", drawing_view);

        GenericModel papaya = new GenericModel(
                0.75f,0.4f,0.2f,0.2f,
                203,192,22,
                R.drawable.papaya, "Papaya", drawing_view);

        drawing_view.addModel(apple);
        drawing_view.addModel(banana);
        drawing_view.addModel(pineapple);
        drawing_view.addModel(strawberry);
        drawing_view.addModel(watermelon);
        drawing_view.addModel(papaya);
    }
}