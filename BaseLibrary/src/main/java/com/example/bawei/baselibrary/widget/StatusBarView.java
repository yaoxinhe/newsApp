package com.example.bawei.baselibrary.widget;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/***
 * @Author yxh
 * @CreateDate 2019-12-04
 */
public class StatusBarView extends View {

    private static int mStatusBarHeight;

    public StatusBarView(Context context) {
        super(context);
        init(context);
    }


    public StatusBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBarHeight = getStausBarHeight(context);
        }
        init(context);
    }


    public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private int getStausBarHeight(Context context) {
        if (mStatusBarHeight == 0) {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                mStatusBarHeight = resources.getDimensionPixelSize(identifier);
            }
        }
        return mStatusBarHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), mStatusBarHeight);
    }

    private void init(Context context) {
    }
}
