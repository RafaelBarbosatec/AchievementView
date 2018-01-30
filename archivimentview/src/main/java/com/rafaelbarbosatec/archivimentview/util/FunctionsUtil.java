package com.rafaelbarbosatec.archivimentview.util;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by rafaelbarbosa on 30/01/18.
 */

public class FunctionsUtil {

    public static void applyAlpha(View view, long delay, float alpha, Animator.AnimatorListener animatorListener){
        ViewPropertyAnimator animator2 = view.animate();
        animator2.alpha(alpha);
        animator2.setInterpolator(new DecelerateInterpolator());
        animator2.setStartDelay(delay);
        animator2.setListener(animatorListener);
        animator2.start();
    }

    public static int dpToPixel(Context context, int dp){

        Resources r = context.getResources();

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());

    }
}
