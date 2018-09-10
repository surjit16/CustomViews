package com.example.surjitsingh.customeditview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CET extends AppCompatEditText {

    Drawable clr;
    boolean isDarkVisible;

    public CET(Context context) {
        super(context);
        init();
    }


    public CET(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        clr = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear, null);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float clearButtonStart = getWidth() - getPaddingEnd() - clr.getIntrinsicWidth();
                if (event.getX() > clearButtonStart) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            getText().clear();
                            return true;
                        case MotionEvent.ACTION_UP:
                            return true;
                        default:
                            return false;
                    }
                }
                return false;
            }
        });

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isDarkVisible)
                    showButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0) showHide();
            }
        });
        if (getText().toString().length() == 0) showHide();
        else showButton();

    }

    private void showButton() {
        isDarkVisible = true;
        clr.setAlpha(255);
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, clr, null);
    }

    private void showHide() {
        isDarkVisible = false;
        clr.setAlpha(64);
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, clr, null);
    }

}
