package com.example.bawei.usermodule.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.bawei.baselibrary.MyInter.YxhFindViewByIdManager;
import com.example.bawei.baselibrary.MyInter.YxhInjectView;
import com.example.bawei.baselibrary.base.BaseActivity;
import com.example.bawei.baselibrary.common.MsgUtils;
import com.example.bawei.baselibrary.common.StartIntent;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.baselibrary.net.protocol.resp.UserEntity;
import com.example.bawei.baselibrary.validator.IValidatorCallback;
import com.example.bawei.baselibrary.validator.ValidatorManager;
import com.example.bawei.baselibrary.validator.ValidatorType;
import com.example.bawei.baselibrary.validator.anns.Control;
import com.example.bawei.homemodule.view.HomeActivity;
import com.example.bawei.usermodule.R;
import com.example.bawei.usermodule.viewmodel.RegisterViewModel;

public class LoginActivity extends BaseActivity<RegisterViewModel, ViewDataBinding> {

    LiveData<BaseRespEntity<UserEntity>> login;
    @Control(Msg = "请输入密码", CType = ValidatorType.IsNull)
    private EditText et_username;
    @Control(Msg = "请输入密码", CType = ValidatorType.IsNull)
    private EditText et_psw;
    private Button btn_zc;
    private Button btn_dl;

    @Override
    protected void initEvent() {
        btn_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final UserEntity userEntity = new UserEntity();
                String psw = et_psw.getText().toString();
                String username = et_username.getText().toString();
                if (!ValidatorManager.getInstance().check(LoginActivity.this, new IValidatorCallback() {
                    @Override
                    public void doValidator(String msg) {
                        MsgUtils.showMsg(LoginActivity.this, msg);
                    }
                })) {
                    return;
                }
                userEntity.setUsername(username);
                userEntity.setPwd(psw);
                login = mViewModel.Login(userEntity);
                login.observe(LoginActivity.this, new Observer<BaseRespEntity<UserEntity>>() {
                    @Override
                    public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                        if (userEntityBaseRespEntity.getCode() != 200) {
                            MsgUtils.showMsg(LoginActivity.this,userEntityBaseRespEntity.getMsg());
                            return;
                        }
                        MsgUtils.showMsg(LoginActivity.this, "登陆成功");
                        SharedPreferences yxh = getSharedPreferences("yxh", 0);
                        SharedPreferences.Editor edit = yxh.edit();
                        edit.putInt("userid",userEntityBaseRespEntity.getData().getId());
                        edit.commit();
                        edit.apply();
                        StartIntent.getInstance().start(LoginActivity.this, HomeActivity.class);
                        LoginActivity.this.finish();
                    }
                });
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        btn_dl=findViewById(R.id.btn_dl);
        btn_zc=findViewById(R.id.btn_zc);
        et_psw=findViewById(R.id.et_psw);
        et_username=findViewById(R.id.et_username);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


}
