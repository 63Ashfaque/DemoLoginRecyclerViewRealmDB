package com.ashfaque.demologinrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;


import com.ashfaque.demologinrecyclerview.Utils;
import com.ashfaque.demologinrecyclerview.databinding.ActivityLoginBinding;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.inputFieldPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                passwordField();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.inputFieldUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                userNameField();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.btnLogin.setOnClickListener(v -> {

            if(userNameField()&& passwordField())
            {
                String mUserName= String.valueOf(mBinding.inputFieldUserName.getText());
                String mPassword= String.valueOf(mBinding.inputFieldPassword.getText());

                if((mUserName.equals("Fininfocom")) && (mPassword.equals("Fin@123")))
                {
                    startActivity(new Intent(this, DashboardActivity.class));
                    finish();
                }else
                {
                    Utils.showToast(LoginActivity.this,"User Name Or Password not Valid");
                }

            }else
            {
                Utils.showToast(LoginActivity.this,"Fix The Error");
            }


        });

    }

    private boolean userNameField() {
        String mUserName= String.valueOf(mBinding.inputFieldUserName.getText());
        Pattern specialChar = Pattern.compile("[^a-zA-Z0-9]");
        if(mUserName.isEmpty())
        {
            mBinding.textFieldUserName.setError("User Name is Empty");
        }
        else if(specialChar.matcher(mUserName).find())
        {
            mBinding.textFieldUserName.setError("Special Character Are Not Allowed");
        }else  if(mUserName.length()<7)
        {
            mBinding.textFieldUserName.setError("User Name Must Be 10 Characters");
        }
        else
        {
            mBinding.textFieldUserName.setError(null);
            return true;
        }
        return false;
    }

    private boolean passwordField() {
        String mPassword= String.valueOf(mBinding.inputFieldPassword.getText());

        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern specialChar = Pattern.compile("[^a-zA-Z0-9]");
        Pattern digit = Pattern.compile("[0-9]");


        if(mPassword.isEmpty())
        {
            mBinding.textFieldPassword.setError("Password s Empty");
        }else if(!uppercase.matcher(mPassword).find())
        {
            mBinding.textFieldPassword.setError("At Least One Uppercase Letter");
        }
        else if(!digit.matcher(mPassword).find())
        {
            mBinding.textFieldPassword.setError("At Least One Number");
        }
        else if(!specialChar.matcher(mPassword).find())
        {
            mBinding.textFieldPassword.setError("At Least One Special Character");
        }else  if(mPassword.length()<7)
        {
            mBinding.textFieldPassword.setError("Password Must Be 7 Characters");
        }
        else
        {
            mBinding.textFieldPassword.setError(null);
            return true;
        }
        return false;
    }
}