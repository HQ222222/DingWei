package com.tx.dingwei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tx.model.User;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

public class RegisterActivity extends Activity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private EditText agpasswordEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        accountEdit =findViewById(R.id.rg_account);
        passwordEdit =findViewById(R.id.rg_password);
        agpasswordEdit =findViewById(R.id.ag_password);

        Log.d("msg", "我是一条信息");
        LitePal.initialize(this);
        Connector.getDatabase();
        Button register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                String account =accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                String agpassword=agpasswordEdit.getText().toString();
               if (password.equals(agpassword)){
                   user.setName(account);
                   user.setPassword(password);
                   user.save();
                   Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                   Intent intent =new Intent(RegisterActivity.this,   LoginActivity.class);
                   startActivity(intent);
               }else{
                   Toast.makeText(RegisterActivity.this,"注册失败，两次输入的密码不一致",Toast.LENGTH_SHORT).show();
               }


            }
        });

    }
}
