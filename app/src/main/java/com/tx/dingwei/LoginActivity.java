package com.tx.dingwei;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.tx.model.User;

import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginActivity extends Activity {


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit=findViewById(R.id.account);
        passwordEdit=findViewById(R.id.password);
        login=findViewById(R.id.login);
        rememberPass=findViewById(R.id.remember_pass);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isRemember=pref.getBoolean("remember_password",false);
        if (isRemember){
            String account =pref.getString("account","");
            String password =pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String account =accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                List<User> users= DataSupport.findAll(User.class);
                    User user1=DataSupport.findLast(User.class);
                    for (User user:users){
                        Log.d("dactivity",user.getName());
                        Log.d("dactivity",user.getPassword());
                        if(account.equals(user.getName()) && password.equals(user.getPassword())){

                            editor=pref.edit();
                            if (rememberPass.isChecked()){
                                editor.putBoolean("remember_password",true);
                                editor.putString("account",account);
                                editor.putString("password",password);
                            }else{
                                editor.clear();
                            }
                            editor.apply();

                            Intent intent =new Intent(LoginActivity.this,   NewsTestActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "登录成功!!", Toast.LENGTH_SHORT).show();
                        }else{
                            if (user.getName().equals(user1.getName())&&user.getPassword().equals(user1.getPassword())){
                                Toast.makeText(LoginActivity.this, "用户名或密码错误!!", Toast.LENGTH_SHORT).show();
                            }
                        }




                    }


            }
        });


        Button zhuce=findViewById(R.id.zhuce);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,   RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
