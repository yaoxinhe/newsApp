package com.example.bawei.homemodule.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bawei.baselibrary.base.BaseActivity;
import com.example.bawei.baselibrary.loader.GlideLoader;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.baselibrary.oss.OssManager;
import com.example.bawei.baselibrary.oss.impl.AliOssImpl;
import com.example.bawei.baselibrary.oss.observer.IOssObserver;
import com.example.bawei.baselibrary.oss.observer.OssObsevable;
import com.example.bawei.homemodule.R;
import com.example.bawei.homemodule.view.release.ReleaseAdapter;
import com.example.bawei.homemodule.viewmodel.HomeViewModel;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReleaseActivity extends BaseActivity<HomeViewModel, ViewDataBinding> implements View.OnClickListener, IOssObserver {
    public static int REQUEST_CODE_SELECT = 1001;

    private EditText news_release_input;
    private Button news_release_select;
    private RecyclerView news_release_recycler;
    private ArrayList<ImageItem> imageList;
    private List<String> imageRemotePathList;
    private ReleaseAdapter imageAdapter;
    private String inputString;
    private Button addNews;

    @Override
    protected void initEvent() {

        addNews.setOnClickListener(v -> {
            inputString = news_release_input.getText().toString();
            if (TextUtils.isEmpty(inputString)) {
                return;
            }
            if (imageList.isEmpty()) {
                //无图片
                SharedPreferences yxh = getSharedPreferences("yxh", 0);
                int userid = yxh.getInt("userid", 0);
                mViewModel.publishHeadLine(userid, inputString, Collections.emptyList());
                finish();
            } else {
                //有图片
                imageRemotePathList.clear();

                for (ImageItem imageItem : imageList) {
                    OssManager.getInstance().upload(AliOssImpl.ALI_BUCKETNAME, imageItem.name, imageItem.path);
                }
            }
        });

        news_release_select.setOnClickListener(v -> {
            Intent intent = new Intent(ReleaseActivity.this, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, false); // 是否是直接打开相机
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        news_release_input = (EditText) findViewById(R.id.news_release_input);
        news_release_input.setOnClickListener(this);
        news_release_select = (Button) findViewById(R.id.news_release_select);
        news_release_select.setOnClickListener(this);
        news_release_recycler = (RecyclerView) findViewById(R.id.news_release_recycler);
        news_release_recycler.setOnClickListener(this);
        imageList = new ArrayList<>(0);
        imageAdapter = new ReleaseAdapter(R.layout.item_release, imageList);
        news_release_recycler.setLayoutManager(new GridLayoutManager(this, 3));
        news_release_recycler.setAdapter(imageAdapter);
        imageRemotePathList = new ArrayList<>();
        addNews = findViewById(R.id.addNews);
        OssObsevable.getInstance().addObserver(this);
        initImagePicker();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.news_release_select) {

        }
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形 区域保存
        imagePicker.setSelectLimit(9);              //选中数量限制
        imagePicker.setMultiMode(true);                      //多选
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(1000);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(1000);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }


    @Override
    public void ossOnSuccess(String msg) {
        SharedPreferences yxh = getSharedPreferences("yxh", 0);
        int userid = yxh.getInt("userid", 0);
        imageRemotePathList.add(msg);
        LiveData<BaseRespEntity<Boolean>> baseRespEntityLiveData = mViewModel.publishHeadLine(userid, inputString, imageRemotePathList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                baseRespEntityLiveData.observe(ReleaseActivity.this, booleanBaseRespEntity -> ReleaseActivity.this.finish());
            }
        });

    }

    @Override
    public void ossOnError(String msg) {
        Log.e("fanhualuomu", msg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OssObsevable.getInstance().removeObserver(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                imageList.clear();
                imageList.addAll(images);
                imageAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
