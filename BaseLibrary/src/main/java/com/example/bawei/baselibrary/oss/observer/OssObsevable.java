package com.example.bawei.baselibrary.oss.observer;

import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class OssObsevable {

    private static volatile OssObsevable singleton;

    private OssObsevable() {
        observers = new HashSet<>();
    }

    public static OssObsevable getInstance() {
        if (singleton == null) {
            synchronized (OssObsevable.class) {
                if (singleton == null) {
                    singleton = new OssObsevable();
                }
            }
        }
        return singleton;
    }

    private Set<IOssObserver> observers;

    public void addObserver(IOssObserver iOssObserver) {
        observers.add(iOssObserver);
    }

    public void removeObserver(IOssObserver iOssObserver) {
        observers.remove(iOssObserver);
    }

    public void notifySuccess(String msg) {

        for (IOssObserver observer : observers) {

            observer.ossOnSuccess(msg);
        }
    }

    public void notifyError(String msg) {
        for (IOssObserver observer : observers) {
            observer.ossOnError(msg);
        }
    }

}