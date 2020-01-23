package com.example.bawei.baselibrary.validator;

import android.text.TextUtils;
import android.widget.EditText;


import com.example.bawei.baselibrary.common.LogUtils;
import com.example.bawei.baselibrary.validator.anns.Control;
import com.example.bawei.baselibrary.validator.anns.Controls;
import com.example.bawei.baselibrary.validator.common.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ValidatorManager {
    private static ValidatorManager instance = new ValidatorManager();

    private ValidatorManager() {
    }

    public static ValidatorManager getInstance() {
        return instance;
    }

    private IValidatorCallback callback;

    public boolean check(Object obj, IValidatorCallback _callback) {
        callback=_callback;
        boolean result=false;
        try{
            Field[] fields = obj.getClass().getDeclaredFields();
            if (fields != null) {
                for (Field f :
                        fields) {
                    Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
                    if (null != declaredAnnotations) {

                        for (Annotation ann :
                                declaredAnnotations) {
                            if (ann instanceof Control) {
                                Control zyControl = (Control) ann;
                                result= checkControl(obj,zyControl, f);
                                if (!result){
                                    return result;
                                }
                            } else if (ann instanceof Controls) {
                                Controls zyControls = (Controls) ann;
                                for (Control control :
                                        zyControls.value()) {
                                    result= checkControl(obj,control, f);
                                    if (!result){
                                        return result;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex){
            LogUtils.e(ex.getMessage());
            return result;
        }
        return true;
    }

    /**
     * 校验控件是否符合条件
     * @param obj
     * @param zyControl
     * @param f
     * @return
     * @throws IllegalAccessException
     */
    private boolean checkControl(Object obj, Control zyControl, Field f) throws IllegalAccessException {
        f.setAccessible(true);
        Object o = f.get(obj);
        Object controlValue=getControlValue(o);
        if (null==controlValue){
            return false;
        }
        switch (zyControl.CType()) {
            case Equals:

                break;
            case IsNull:
               if (!TextUtils.isEmpty(controlValue.toString().trim())){
                  return true;
               }
               else{
                   this.callback.doValidator(zyControl.Msg());
                   return false;
               }
            case Regex:
                String v=controlValue.toString();
                if (Regex.validator(zyControl.Regex(), v)) {
                    return true;
                }
                else{
                    this.callback.doValidator(zyControl.Msg());
                    return false;
                }
            default:

                break;
        }
        return true;
    }

    private Object getControlValue(Object o) {
        if (o instanceof EditText){
            return ((EditText) o).getText().toString();
        }
        return null;
    }

//    public <T> T check(Class<T> obj){
//        return (T) Proxy.newProxyInstance(obj.getClassLoader(),new Class[]{ICheck.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Annotation[] annotations = proxy.getClass().getAnnotations();
//                if (null!=annotations){
//                    for (Annotation ann:
//                         annotations) {
//                        LogUtils.d(ann.annotationType().getName());
//                    }
//                }
//               // method.invoke(proxy,args);
//                return false;
//            }
//        });
//    }
}
