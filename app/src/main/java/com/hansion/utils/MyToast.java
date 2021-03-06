package com.hansion.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.hansion.App;


/**
 * Toast 工具类
 *
 * @author Hansion
 */
public class MyToast {

    //TODO 是否在Debug模式   上线前需要更改为false
    public static boolean LOG_DEBUG = true;
    private static Toast mToast;

    //短时间吐司
    public static void show(int resourceID) {
        show(resourceID, Toast.LENGTH_SHORT);
    }


    //短时间吐司
    public static void show(String text) {
        show(text, Toast.LENGTH_SHORT);
    }

    //自定义时长吐司
    public static void show(Integer resourceID, int duration) {
        String text = App.getAppContext().getResources().getString(resourceID);// 用于显示的文字
        show(text, duration);
    }

    //自定义时长吐司
    public static void show(@NonNull final String text, final int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getAppContext(), text, duration);
        } else {
            mToast.cancel();
            mToast = Toast.makeText(App.getAppContext(), text, duration);
        }

        mToast.show();
    }

    public static void showDebugToast(String text) {
        try {
            if(LOG_DEBUG) {
                show(text);
            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
    }



    /**
     * 在UI线程中显示Toast消息
     */
    public static void showOnUiThread(final Activity activity, final String message) {
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
