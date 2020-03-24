package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EyeDialog extends Dialog {
    private static EyeDialog msEyeDialog;

    protected EyeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public EyeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static EyeDialog getInstance(Context context, int themeResId){
        if (msEyeDialog == null){
                msEyeDialog = new EyeDialog(context,themeResId);
        }
        return  msEyeDialog;
    }

    public static EyeDialog getInstance2(){
        return  msEyeDialog;
    }

}
