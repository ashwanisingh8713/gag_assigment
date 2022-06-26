package com.gag.assignment.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ashwanisingh on 25/06/22.
 */

public class AppUtil {
    public static void loadImage(Context context, final ImageView imageView, String url) {
        Picasso picasso = Picasso.with(context);
        picasso.load(url)
                .into(imageView);
    }


}
