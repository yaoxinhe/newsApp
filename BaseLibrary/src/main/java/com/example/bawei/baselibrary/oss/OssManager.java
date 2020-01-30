package com.example.bawei.baselibrary.oss;


import com.example.bawei.baselibrary.oss.contract.IOss;
import com.example.bawei.baselibrary.utils.AppUtils;

public class OssManager {

    private static volatile OssManager singleton;

    private OssManager() {
    }

    public static OssManager getInstance() {
        if (singleton == null) {
            synchronized (OssManager.class) {
                if (singleton == null) {
                    singleton = new OssManager();
                }
            }
        }
        return singleton;
    }

    private IOss iOss;

    public void init(IOss iOss) {
        this.iOss = iOss;
        this.iOss.init(AppUtils.getApplicationContext());
    }

    public void upload(String bucketName, String key, String localFilePath) {
        iOss.upload(bucketName, key, localFilePath);
    }

    public void download(String bucetName, String key, final String downloadPath) {
        iOss.download(bucetName, key, downloadPath);
    }
}