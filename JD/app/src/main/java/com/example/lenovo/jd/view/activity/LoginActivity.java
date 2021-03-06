package com.example.lenovo.jd.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.LoginPresenter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseActivity;
import com.example.lenovo.jd.view.bean.LoginSuperClass;

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener,ILoginView {

    /**
     * 请输入手机号
     */
    private EditText mEditTel;
    /**
     * 请输入密码
     */
    private EditText mEditPass;
    /**
     * 登录
     */
    private Button mBtnDl;
    /**
     * 注册
     */
    private Button mBtnZc;
    private ImageView mIamgeWeixin;
    private ImageView mIamgeQq;
    private ImageView mIamgeWeibo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter getPresenter() {
        presenter = new LoginPresenter(this);
        return presenter;
    }

    @Override
    protected void initView() {

        mEditTel = (EditText) findViewById(R.id.edit_tel);
        mEditPass = (EditText) findViewById(R.id.edit_pass);
        mBtnDl = (Button) findViewById(R.id.btn_dl);
        mBtnDl.setOnClickListener(this);
        mBtnZc = (Button) findViewById(R.id.btn_zc);
        mBtnZc.setOnClickListener(this);
        mIamgeWeixin = (ImageView) findViewById(R.id.iamge_weixin);
        mIamgeWeixin.setOnClickListener(this);
        mIamgeQq = (ImageView) findViewById(R.id.iamge_qq);
        mIamgeQq.setOnClickListener(this);
        mIamgeWeibo = (ImageView) findViewById(R.id.iamge_weibo);
        mIamgeWeibo.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_dl:
                String tel = mEditTel.getText().toString().trim();
                String pass = mEditPass.getText().toString().trim();
                presenter.login(Api.HOME_NAME,tel,pass);
                break;
            case R.id.btn_zc:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.iamge_weixin:
                break;
            case R.id.iamge_qq:
                break;
            case R.id.iamge_weibo:
                break;
        }
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSccuess(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
