package com.ravan;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.ravan.main.MainActivity;
import com.ravan.model.User;
import com.ravan.repository.SocialAuthentication;
import com.ravan.repository.UserRepo;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
private SocialAuthentication mSocialAuthentication;
private SignInButton mSignInButton;
    private String TAG=SocialAuthentication.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSocialAuthentication =  new SocialAuthentication() {
            @Override
            protected void updateUI(GoogleSignInAccount account) {
            Intent intent =  new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            }

            @Override
            protected void onSuccessfullyLogin(GoogleSignInAccount account) {
                UserRepo userRepo  =  new UserRepo();
                User user  =  new User();
                user.setName(account.getDisplayName());
                user.setEmail(account.getEmail());
                user.setImageUrl(account.getPhotoUrl().getPath());
                userRepo.put(account.getEmail().replace("@","_").replace(".","_"),user);
                Intent intent =  new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        }
        ;
        mSignInButton =  findViewById(R.id.signInButton);
        mSignInButton.setOnClickListener(this);
        mSocialAuthentication.init(this);
    }

    @Override
    public void onClick(View v) {
        mSocialAuthentication.signIn();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mSocialAuthentication.onActivityResult(requestCode,resultCode,data);
    }
}
