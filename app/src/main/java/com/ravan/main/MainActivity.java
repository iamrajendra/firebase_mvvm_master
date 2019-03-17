package com.ravan.main;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.design.gambler.Snakebar;
import com.ravan.AppConstant;
import com.ravan.DialogActivity;
import com.ravan.IntentKey;
import com.ravan.R;
import com.ravan.model.Article;
import com.ravan.repository.ArticleRepository;
import com.ravan.repository.FirebaseDatabaseRepository;
import com.ravan.repository.UserRepo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserRepo mUserRepo = new UserRepo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.mobile_no_warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), DialogActivity.class);
                intent.setAction("mobile_no");
                startActivityForResult(intent,100);
//                mUserRepo.update(AppConstant.PRIMARY_KEY, "mobile", ((EditText) findViewById(R.id.mobile_et)).getText().toString());
            }
        });
        mUserRepo.fieldExist(AppConstant.PRIMARY_KEY, "mobile", new FirebaseDatabaseRepository.FirebaseFiledExist() {
            @Override
            public void isExist(boolean is) {
                if (!is) {
                    findViewById(R.id.mobile_no_warning).setVisibility(View.VISIBLE);
                }else {
                    findViewById(R.id.mobile_no_warning).setVisibility(View.GONE);
                }
            }
        });
findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =  new Intent(MainActivity.this,AddMedicationActivity.class);
        startActivity(intent);


    }
});



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode ==100 && resultCode ==RESULT_OK){
            mUserRepo.update(AppConstant.PRIMARY_KEY, "mobile", data.getStringExtra(IntentKey.MOBILE_NO.getKey()));
            findViewById(R.id.mobile_no_warning).setVisibility(View.GONE);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
