package com.dynamicdev.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class UpdateInfoActivity extends AppCompatActivity {
    private TextView displayNameAge;
    private EditText cityName;
    private EditText stateName;
    private EditText countryName;
    String resultAge;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        displayNameAge=findViewById(R.id.name_age);
        imageView=findViewById(R.id.profile_2);

        Intent receiveIntent = getIntent();
        if(receiveIntent!=null){
            String name = receiveIntent.getStringExtra("NAME");
            int age = receiveIntent.getIntExtra("YOB",2000);
             resultAge = "Hi! "+name+" you are "+(2021-age)+" years old";
            displayNameAge.setText(resultAge);
        }
        cityName=findViewById(R.id.cityEditText);
        stateName=findViewById(R.id.stateEditText);
        countryName=findViewById(R.id.countryEditText);
        Glide.with(UpdateInfoActivity.this)
                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(R.drawable.profile_pic)
                .into(imageView);
        findViewById(R.id.submit_button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent getDataA2 =new Intent(UpdateInfoActivity.this,MainActivity.class);
               getDataA2.putExtra("NAME_AGE",resultAge);
               getDataA2.putExtra("CITY",cityName.getText().toString().trim());
               getDataA2.putExtra("STATE",stateName.getText().toString().trim());
               getDataA2.putExtra("COUNTRY",countryName.getText().toString().trim());

               setResult(RESULT_OK,getDataA2);
                //startActivity(getDataA2);
               finish();


            }
        });


    }
}