package com.ashfaque.demologinrecyclerview;

import android.app.Activity;
import android.widget.Toast;


public class Utils {


    public static void showToast(Activity activity, String msg)
    {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}

