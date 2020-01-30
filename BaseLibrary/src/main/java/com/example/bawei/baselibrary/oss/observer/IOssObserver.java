package com.example.bawei.baselibrary.oss.observer;

public interface IOssObserver {

    void ossOnSuccess(String msg);

    void  ossOnError(String msg);
}
