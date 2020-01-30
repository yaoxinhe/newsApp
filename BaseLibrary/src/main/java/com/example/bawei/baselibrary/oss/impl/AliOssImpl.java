package com.example.bawei.baselibrary.oss.impl;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.example.bawei.baselibrary.common.LogUtils;
import com.example.bawei.baselibrary.oss.contract.IOss;
import com.example.bawei.baselibrary.oss.observer.OssObsevable;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author:Zhangyue
 * Date:2019-12-6
 * Desc：阿里云OSS工具类
 */
public class AliOssImpl implements IOss {
    private static AliOssImpl instance = new AliOssImpl();
    private OSS oss;

    /**
     * 阿里云OSS Endpoint
     */
    public static final String ALIYUN_OSS_ENDPOINT="http://oss-cn-beijing.aliyuncs.com";

    /**
     * 阿里云OSS AccessKeyID
     */
    public static final String ACCESS_KEY_ID="LTAIjuvm6vebooIu";

    /**
     * 阿里云OSS AccessKeySecret
     */
    public static final String ACCESS_KEY_SECRET="HBgCZgAb0AAmnc3C8cXOXot8Vf4dHQ";


    /**
     * 阿里云OSS 上传文件网络地址
     */
    public static final String ALI_FILE_PATH="http://baweitest6.oss-cn-beijing.aliyuncs.com/";

    /**
     * 阿里云OSS 上传文件的空间名称
     */
    public static final String ALI_BUCKETNAME="baweitest6";

    public AliOssImpl() {

    }

    public static AliOssImpl getInstance() {
        return instance;
    }

    /**
     * 初始化阿里云OSS设置 一般在Application中调用
     *
     * @param context
     */
    public void init(Context context) {

        //if null , default will be init
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000);
        conf.setSocketTimeout(15 * 1000);
        conf.setMaxConcurrentRequest(5);
        conf.setMaxErrorRetry(2);
        OSSLog.enableLog();

        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        oss = new OSSClient(context, ALIYUN_OSS_ENDPOINT, credentialProvider, conf);
    }

    /**
     * 上传
     *
     * @param bucketName    空间名称
     * @param key           文件名称
     * @param localFilePath 本地文件类路径
     */
    @Override
    public void upload(String bucketName, String key, String localFilePath) {
        PutObjectRequest put = new PutObjectRequest(bucketName, key, localFilePath);

        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                LogUtils.d("PutObject " + "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                LogUtils.d( "uploadSuccessful");
                OssObsevable.getInstance().notifySuccess(ALI_FILE_PATH+request.getObjectKey());
                LogUtils.d(ALI_FILE_PATH+request.getObjectKey());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                if (clientExcepion != null) {
                    clientExcepion.printStackTrace();
                    OssObsevable.getInstance().notifyError(clientExcepion.getMessage());
                }
                if (serviceException != null) {
                    OssObsevable.getInstance().notifyError(serviceException.getRawMessage());
                }
            }
        });
    }

    /**
     * 下载文件
     *
     * @param bucetName    空间名
     * @param key          服务器上的文件key 对应于上传的key 一般是文件名
     * @param downloadPath 下载存储的本地路径
     */
    @Override
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
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {
                LogUtils.e("file download failed...");
            }
        });
        getTask.waitUntilFinished();

    }
}