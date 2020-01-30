package com.example.bawei.baselibrary.common;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.*;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author:Zhangyue
 * Date:2019-12-6
 * Desc：阿里云OSS工具类
 */
public class AliyunUtils {
    private static AliyunUtils instance = new AliyunUtils();
    private OSS oss;

    private AliyunUtils() {

    }

    public static AliyunUtils getInstance() {
        return instance;
    }

    /**
     * 初始化阿里云OSS设置 一般在Application中调用
     * @param context
     */
    public void init(Context context) {

        //if null , default will be init
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // connction time out default 15s
        conf.setSocketTimeout(15 * 1000); // socket timeout，default 15s
        conf.setMaxConcurrentRequest(5); // synchronous request number，default 5
        conf.setMaxErrorRetry(2); // retry，default 2
        OSSLog.enableLog(); //write local log file ,path is SDCard_path\OSSLog\logs.csv

        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(BaseConstant.ACCESS_KEY_ID, BaseConstant.ACCESS_KEY_SECRET);

        oss = new OSSClient(context, BaseConstant.ALIYUN_OSS_ENDPOINT, credentialProvider, conf);
    }

    /**
     * 上传
     *
     * @param bucketName    空间名称
     * @param key           文件名称
     * @param localFilePath 本地文件类路径
     */
    public void upload(String bucketName, String key, String localFilePath,OSSCompletedCallback callback) {
        PutObjectRequest put = new PutObjectRequest(bucketName, key, localFilePath);

        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("yxh","PutObject "+ "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });
        oss.asyncPutObject(put,callback);
    }

    /**
     * 下载文件
     *
     * @param bucetName    空间名
     * @param key          服务器上的文件key 对应于上传的key 一般是文件名
     * @param downloadPath 下载存储的本地路径
     */
    public void download(String bucetName, String key, final String downloadPath) {
        GetObjectRequest get = new GetObjectRequest(bucetName, key);
        OSSAsyncTask getTask = oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                long length = result.getContentLength();
                byte[] buffer = new byte[(int) length];
                int readCount = 0;
                while (readCount < length) {
                    try {
                        readCount += result.getObjectContent().read(buffer, readCount, (int) length - readCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    FileOutputStream fout = new FileOutputStream(downloadPath);
                    fout.write(buffer);
                    fout.close();
                } catch (Exception e) {
                    OSSLog.logInfo(e.toString());
                }
                Log.e("yxh","下载成功");
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {
                Log.d("yxh","file download failed...");
            }
        });
        getTask.waitUntilFinished();

    }
}
