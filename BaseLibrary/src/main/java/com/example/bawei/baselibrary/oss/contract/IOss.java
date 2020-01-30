package com.example.bawei.baselibrary.oss.contract;

import android.content.Context;

public interface IOss {

    void init(Context context);

    void upload(String bucketName, String key, String localFilePath);

    void download(String bucetName, String key, String downloadPath);
}
