package com.igreatstone.partyedu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.igreatstone.partyedu.common.ToastUtil;

/**
 * Created by yy on 2017/12/4.
 */

public class GoLoginActivity extends Activity {

    EditText phoneNumber;
    EditText passwordNumber;
    Button loginButton;
    private ProgressDialog checkNetDialog;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gologin);
        initView();
    }

    private void initView() {
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        passwordNumber = (EditText) findViewById(R.id.password_number);
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        String name = phoneNumber.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showShort(this, "账号不能为空");
            return;
        }

        String pass = passwordNumber.getText().toString();
        if (TextUtils.isEmpty(pass)) {
            ToastUtil.showShort(this, "密碼不能为空");
            return;
        }


    }
}
