package com.ravan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.design.gambler.Snakebar;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        getSupportActionBar().hide();
        findViewById(R.id.send_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileno  = ((EditText)findViewById(R.id.mobile_no_et)).getText().toString();
                if (mobileno.length()==10){
                    Intent intent  =  new Intent();
                    intent.putExtra(IntentKey.MOBILE_NO.getKey(),mobileno);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    new Snakebar(DialogActivity.this).error("enter valid mobile no").show();
                }
            }
        });

    }
}
