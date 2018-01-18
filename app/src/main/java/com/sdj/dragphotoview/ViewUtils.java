package com.sdj.dragphotoview;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import java.util.logging.Handler;

/**
 * Created by sdj on 2018/1/17.
 */

public class ViewUtils {

    public static int[] getLocation(View v){
        int[] location = new int[2];
        v.getLocationInWindow(location);
        return location;
    }

    public static int getStatusBarHeight(Context context){
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        return resources.getDimensionPixelSize(resourceId);
    }
}
