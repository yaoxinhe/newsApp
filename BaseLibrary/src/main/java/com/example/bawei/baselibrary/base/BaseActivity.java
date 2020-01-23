package com.example.bawei.baselibrary.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.bawei.baselibrary.common.ClassUtils;

/**
 * Base Activity
 * @param <VM> ViewModel
 * @param <D> DataBinding
 */
public abstract class BaseActivity<VM extends ViewModel,D extends ViewDataBinding> extends AppCompatActivity {
    protected VM mViewModel;
    protected D mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        //获取ViewModel的泛型类型
        Class<VM> clazz= (Class<VM>) ClassUtils.getParameterizedClazz(this);
        //ViewModel实例化
        mViewModel= ViewModelProviders.of(this).get(clazz);
        initView(savedInstanceState);
        initData();
        initEvent();
    }



    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化视图
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 获取布局ID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 沉浸式体验设置
     */
    private void setStatusBar() {
        //Android版本5.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //Android 4.4 - 5.0
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        //Android 6.0以后
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}
