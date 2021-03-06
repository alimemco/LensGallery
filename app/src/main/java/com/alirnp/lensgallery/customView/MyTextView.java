package com.alirnp.lensgallery.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.alirnp.lensgallery.R;
import com.alirnp.lensgallery.base.MyApplication;

import static com.alirnp.lensgallery.base.Constants.*;

public class MyTextView extends AppCompatTextView {


    public MyTextView(Context context) {
        super(context);
        setupFont(null);
    }


    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupFont(attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupFont(attrs);
    }

    private void setupFont(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);

            try {
                int Font = attributes.getInteger(R.styleable.MyTextView_fontCustomTextView, IRAN_SANS_FONT);

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
