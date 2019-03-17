package com.ravan.repository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.ravan.R;

public abstract class SocialAuthentication {
    private static final int  RC_SIGN_IN =121 ;
    private GoogleSignInClient mGoogleApiClient;
    private  Activity mActivity;
    private  String TAG = SocialAuthentication.class.getSimpleName();


    public void init(Activity activity){
mActivity = activity;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleApiClient = GoogleSignIn.getClient(activity, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
        if (account!=null)
        updateUI(account);
    }

public  void signIn(){
    Intent signInIntent = mGoogleApiClient.getSignInIntent();
   mActivity. startActivityForResult(signInIntent, RC_SIGN_IN);
}

    protected abstract void updateUI(GoogleSignInAccount account);
    protected abstract void onSuccessfullyLogin(GoogleSignInAccount account);
 public  void onActivityResult(int requestCode, int resultCode, Intent data){
     if (requestCode == RC_SIGN_IN) {
         // The Task returned from this call is always completed, no need to attach
         // a listener.
         Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
         handleSignInResult(task);
     }

 }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            onSuccessfullyLogin(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            onSuccessfullyLogin(null);
        }
    }


}
