package com.example.cs301_hw1;

import android.util.Log;
import android.widget.SeekBar;

/**
 * AUTHOR: Max Woods
 *
 * DATA: 2/7/2022
 *
 * DESC: Basic SeekBar listener. Updates the color of the currently selected element according
 * to the values of the bars.
 */
public class SeekBarController implements SeekBar.OnSeekBarChangeListener {

    private DrawingSurfaceController dsc;

    public SeekBarController(DrawingSurfaceController dsc) {
        this.dsc = dsc; // This controller needs to be referenced so that the models included in
                        // the surface view can be updated according to the RGB values.
    }

    /**
     * This method updates the color of the currently selected element when the seekbars are
     * changed.
     * @param seekBar
     * @param i
     * @param b
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        int seekbar_id = seekBar.getId();

        // If the user caused this change, modify the color values
        if (b) {
            switch (seekbar_id) {
                case R.id.red_seekbar:
                    this.dsc.setRed(seekBar.getProgress());
                    break;
                case R.id.green_seekbar:
                    this.dsc.setGreen(seekBar.getProgress());
                    break;
                case R.id.blue_seekbar:
                    this.dsc.setBlue(seekBar.getProgress());
                    break;
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Not needed
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Not needed
    }
}
