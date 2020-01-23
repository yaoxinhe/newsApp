package com.example.bawei.baselibrary.net.retrofit;

import android.os.Looper;


import java.lang.reflect.Type;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/15 17:00
 * @Email 1151403054@qq.com
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<BaseRespEntity<R>>> {
    Type type;
    public LiveDataCallAdapter(Type _t){
        type=_t;
    }
    @Override
    public Type responseType() {
        return this.type;
    }



    @Override
    public LiveData<BaseRespEntity<R>> adapt(Call<R> call) {
        final MutableLiveData<BaseRespEntity<R>> data = new MutableLiveData<>();
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    data.setValue((BaseRespEntity<R>) response.body());
                }else{
                    data.postValue((BaseRespEntity<R>) response.body());
                }

            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {

                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    data.setValue(null);
                }else{
                    data.postValue(null);
                }
            }
        });
        return data;
    }


}
