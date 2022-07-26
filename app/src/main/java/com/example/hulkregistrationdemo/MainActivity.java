package com.example.hulkregistrationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText uname,mail,pass;
    Button reg;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=findViewById(R.id.name);
        mail=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        reg=findViewById(R.id.register);
        textView=findViewById(R.id.tv);

    }


    public void register(View view) {
        process();
    }

    private void process() {
        Call<ModelClass> call= ApiController
                .getInstance()
                .getApi()
                .getData();
        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                if(!response.isSuccessful()) return;

                ModelClass data= response.body();
                String nonce= data.getNonce();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(nonce);
                    }
                });
                process2();
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {

            }
        });
    }

    public void process2() {
        String username= uname.getText().toString();
        String password=pass.getText().toString();
        String email=mail.getText().toString();

        Call<ModelClassRegister> call=ApiController.getInstance()
                .getApi().getRegisterInformation(username,email,password);
        call.enqueue(new Callback<ModelClassRegister>() {
            @Override
            public void onResponse(Call<ModelClassRegister> call, Response<ModelClassRegister> response) {
                ModelClassRegister data2= response.body();
                String status= data2.getStatus();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pass.setText(status);
                    }
                });
            }

            @Override
            public void onFailure(Call<ModelClassRegister> call, Throwable t) {

            }
        });

    }
}