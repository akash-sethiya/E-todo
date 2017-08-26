package com.skytech.codepath.e_todo.utility;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by akash on 8/24/2017.
 */

public class CustomSpinnerListener implements AdapterView.OnItemSelectedListener {


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.GRAY);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
