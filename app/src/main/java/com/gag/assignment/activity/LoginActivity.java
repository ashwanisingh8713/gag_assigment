package com.gag.assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.gag.assignment.app.ProductApplication;
import com.gag.assignment.databinding.ActivityLoginBinding;
import com.gag.assignment.util.DefaultPref;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwanisingh on 26/06/22.
 */

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(DefaultPref.getInstance().isUserLoggedIn()) {
            launchMainScreen();
            return;
        }

        binding = binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth= FirebaseAuth.getInstance();
        callbackManager= CallbackManager.Factory.create();
        List<String> permi = new ArrayList<>();
        permi.add("email");
        permi.add("public_profile");
        permi.add("user_friends");

        binding.btnFacebook.setOnClickListener(v->{
            LoginManager.getInstance().logInWithReadPermissions(this, permi);
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.i("", "");
                    handleFacebookAccessToken(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                    Log.i("", "");
                }

                @Override
                public void onError(@NotNull FacebookException e) {
                    Log.i("", "");
                }
            });
        });

        binding.btnGuest.setOnClickListener(v->{
            launchMainScreen();
        });

    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        launchMainScreen();
                    }
                    else {
                        Toast.makeText(LoginActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void launchMainScreen() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        DefaultPref.getInstance().setIsUserLoggedIn(true);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
