package com.example.bawei.baselibrary.net.retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/15 17:00
 * @Email 1151403054@qq.com
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (!(returnType instanceof ParameterizedType)){
            throw new IllegalArgumentException("返回值为参数化类型");
        }
        Class<?> rawType = CallAdapter.Factory.getRawType(returnType);
        if (rawType != LiveData.class && rawType!= Call.class){
            throw new IllegalArgumentException("返回值为LiveData|Call类型");
        }
        Type t = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);
        if(rawType==Call.class){
            return new DefaultCallAdapter<>(t);
        }
        return new LiveDataCallAdapter<>(t);
    }

    public static LiveDataCallAdapterFactory create(){
        return new LiveDataCallAdapterFactory();
    }
}
