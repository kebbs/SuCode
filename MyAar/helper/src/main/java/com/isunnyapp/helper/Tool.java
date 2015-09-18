package com.isunnyapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kexuebiao on 15/9/16.
 */
public class Tool {
    public static void toast(String message) {
        Toast.makeText(HelperUtil.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public static void toast(int resourceID) {
        Toast.makeText(HelperUtil.getApplicationContext(), HelperUtil.getApplicationContext().getResources().getString(resourceID)
                , Toast.LENGTH_LONG).show();
    }

    /**
     * 关闭软键盘
     *
     * @param context
     */
    public static void closeKeyBoard(Activity context, EditText etContent) {
        if (etContent != null) {
            etContent.setFocusableInTouchMode(true);
            etContent.requestFocus();
        }
        ((InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(context.getCurrentFocus()
                                .getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

    }

    /**
     * 打开软键盘
     *
     * @param etContent
     */
    public static void openKeyBoard(final EditText etContent) {
        /**
         * 自动打开软键盘,需要一定的延迟才能打开
         */
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                etContent.setFocusableInTouchMode(true);
                etContent.requestFocus();
                InputMethodManager inputManager = (InputMethodManager) etContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(etContent, 0);
            }
        }, 500);
    }


    public static ColorStateList createColorStateList(int normal, int selected) {
        return createColorStateList(normal, selected, normal);
    }

    public static ColorStateList createColorStateList(int normal, int selected, int pressed) {
        int[] colors = new int[]{pressed, selected, normal};
        int[][] states = new int[3][];

        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{android.R.attr.state_selected};
        states[2] = new int[]{};

        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    public static ColorStateList createColorStateList(String normal, String selected) {
        return createColorStateList(normal, selected, normal);
    }

    public static ColorStateList createColorStateList(String normal, String selected, String pressed) {
        return createColorStateList(Color.parseColor(normal),Color.parseColor(selected),Color.parseColor(pressed));
    }
}
