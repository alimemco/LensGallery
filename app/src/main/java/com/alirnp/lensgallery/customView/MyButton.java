package com.alirnp.lensgallery.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.alirnp.lensgallery.R;
import com.alirnp.lensgallery.base.MyApplication;

import static com.alirnp.lensgallery.base.Constants.*;


public class MyButton extends AppCompatButton {


    public MyButton(Context context) {
        super(context);
        setupFont(null);
    }


    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupFont(attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupFont(attrs);
    }


    private void setupFont(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.MyButton);

            try {
                int Font = attributes.getInteger(R.styleable.MyButton_fontCustomButton, IRAN_SANS_FONT);

                switch (Font) {

                    case IRAN_SANS_FONT:
                        setTypeface(MyApplication.getIranSans(getContext()));
                        break;

                    case IRAN_SANS_BOLD_FONT:
                        setTypeface(MyApplication.getIranSansBold(getContext()));
                        break;


                }

            } finally {
                invalidate();
                requestLayout();
                attributes.recycle();
            }
        }
    }
}