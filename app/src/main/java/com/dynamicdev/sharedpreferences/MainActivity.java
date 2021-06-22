package com.dynamicdev.sharedpreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText yearOfBirth;
    public  final int REQUEST_CODE=797;
    private SharedPreferences sharedPreferences;
    private TextView textView2;
    private TextView textView3;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName=findViewById(R.id.nameEditText);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.tap_continue);
        imageView=findViewById(R.id.profile);
        sharedPreferences=getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("KEY",null);
        String value2 = sharedPreferences.getString("CITY",null);
        String value3 = sharedPreferences.getString("STATE",null);
        String value4 = sharedPreferences.getString("COUNTRY",null);

        if(value!=null){
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(value+" lives in "+value2+", "+value3+", "+value4);
            textView3.setText("SAVED DETAILS:");
            Glide.with(MainActivity.this)
                    .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(R.drawable.profile_pic)
                    .into(imageView);

        }
        yearOfBirth=findViewById(R.id.yob);
        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getDataA1 =new Intent(MainActivity.this,UpdateInfoActivity.class);
                getDataA1.putExtra("NAME",firstName.getText().toString().trim());
                getDataA1.putExtra("YOB",Integer.parseInt(yearOfBirth.getText().toString()));
               // startActivity(getDataA1);


                startActivityForResult(getDataA1,REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            String dataString = data.getStringExtra("NAME_AGE");
            String dataString2 = data.getStringExtra("CITY");
            String dataString3 = data.getStringExtra("STATE");
            String dataString4 = data.getStringExtra("COUNTRY");
            Toast.makeText(this,dataString, Toast.LENGTH_LONG).show();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("KEY", dataString);
            edit.putString("CITY",dataString2);
            edit.putString("STATE",dataString3);
            edit.putString("COUNTRY",dataString4);
            edit.apply();
        }
    }
}