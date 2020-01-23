package com.example.bawei.baselibrary.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.bawei.baselibrary.common.ClassUtils;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 17:13
 * @Email 1151403054@qq.com
 */
public abstract class BaseFragment<VM extends ViewModel, D extends ViewDataBinding> extends Fragment {
    protected VM mViewModel;
    protected D mDataBinding;
    protected View mRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(getLayoutId(), container, false);
        //获取ViewModel的泛型类型
        Class<VM> clazz = (Class<VM>) ClassUtils.getParameterizedClazz(this);
        //ViewModel实例化
        mViewModel = ViewModelProviders.of(this).get(clazz);
        initView(savedInstanceState);
        initData();
        initView(savedInstanceState);
        initData();
        doEvent();
        return mRoot;
    }

    /***
     * 获取布局文件 id
     * @return R.layout
     */
    protected abstract int getLayoutId();

    /***
     * 初始化控件
     */
    protected abstract void initView(Bundle savedInstanceStatev);

    /***
     * 初始化数据
     */
    protected abstract void initData();

    /***
     * 点击事件
     */
    protected abstract void doEvent();
}
