package com.example.bawei.usermodule.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.bawei.baselibrary.MyInter.YxhInjectView;
import com.example.bawei.baselibrary.base.BaseActivity;
import com.example.bawei.baselibrary.common.MsgUtils;
import com.example.bawei.baselibrary.net.protocol.resp.BaseRespEntity;
import com.example.bawei.baselibrary.net.protocol.resp.UserEntity;
import com.example.bawei.baselibrary.validator.IValidatorCallback;
import com.example.bawei.baselibrary.validator.ValidatorManager;
import com.example.bawei.baselibrary.validator.ValidatorType;
import com.example.bawei.baselibrary.validator.anns.Control;
import com.example.bawei.baselibrary.validator.anns.Controls;
import com.example.bawei.baselibrary.validator.common.Regex;
import com.example.bawei.usermodule.R;
import com.example.bawei.usermodule.viewmodel.RegisterViewModel;

public class RegisterActivity extends BaseActivity<RegisterViewModel, ViewDataBinding> {

    LiveData<BaseRespEntity<UserEntity>> register;

    @Controls({
            @Control(Msg = "请输入电话号码", CType = ValidatorType.IsNull),
            @Control(Msg = "输入的电话号码格式不正确", CType = ValidatorType.Regex, Regex = Regex.REGEX_MOBILE)
    })
    private EditText registerEtPhonenumber;

    private EditText registerEtAuthcode;

    @Control(Msg = "请输入密码", CType = ValidatorType.IsNull)
    private EditText registerEtPwd;

    @Controls({
            @Control(Msg = "请再次输入密码", CType = ValidatorType.IsNull),
            @Control(Msg = "两次密码输入不一致", EqualsTarget = "registerEtPwd", CType = ValidatorType.Equals)
    })
    private EditText registerEtPwd2;

    private Button registerBtnGetauthcode;
    private Button registerBtnRegister;


    @Override
    protected void initEvent() {
        registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = registerEtPhonenumber.getText().toString();
                String pwd = registerEtPwd.getText().toString();
                String pwd2 = registerEtPwd2.getText().toString();

                //启用验证框架
                if (!ValidatorManager.getInstance().check(RegisterActivity.this, new IValidatorCallback() {
                    @Override
                    public void doValidator(String msg) {
                        MsgUtils.showMsg(RegisterActivity.this, msg);
                    }
                })) {
                    return;
                }

                final UserEntity userEntity = new UserEntity();

                userEntity.setUsername(phoneNumber);
                userEntity.setPwd(pwd);
                 register = mViewModel.register(userEntity);
                register.observe(RegisterActivity.this, new Observer<BaseRespEntity<UserEntity>>() {
                    @Override
                    public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                        if (userEntityBaseRespEntity.getCode() != 200) {
                            MsgUtils.showMsg(RegisterActivity.this,userEntityBaseRespEntity.getMsg());
                            return;
                        }
                        MsgUtils.showMsg(RegisterActivity.this, "创建成功");
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
        registerEtPhonenumber = (EditText) findViewById(R.id.register_et_phonenumber);
        registerBtnGetauthcode = (Button) findViewById(R.id.register_btn_getauthcode);
        registerEtPwd = (EditText) findViewById(R.id.register_et_pwd);
        registerEtPwd2 = (EditText) findViewById(R.id.register_et_pwd2);
        registerBtnRegister = (Button) findViewById(R.id.register_btn_register);
        registerEtAuthcode = (EditText) findViewById(R.id.register_et_authcode);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

}
