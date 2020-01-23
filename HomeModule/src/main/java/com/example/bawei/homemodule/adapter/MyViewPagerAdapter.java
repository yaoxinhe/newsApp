package com.example.bawei.homemodule.adapter;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bawei.homemodule.bean.TypeBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/17 16:45
 * @Email 1151403054@qq.com
 */
@RequiresApi(api = Build.VERSION_CODES.P)
public class MyViewPagerAdapter extends FragmentPagerAdapter {
    List<TypeBean> list;
    List<Fragment> fragmentList;

    public MyViewPagerAdapter(@NonNull FragmentManager fm, List<TypeBean> list, List<Fragment> fragmentList) {
        super(fm);
        this.list = list;
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTypename();
    }
}
