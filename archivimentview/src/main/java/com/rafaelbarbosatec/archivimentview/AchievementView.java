package com.rafaelbarbosatec.archivimentview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by dev on 20/10/17.
 */

public class AchievementView extends RelativeLayout {

    private Context context;
    private LayoutInflater inflater;
    private TextView tv_titulo, tv_msg;
    private ImageView img_left;
    private RelativeLayout rl_ach ;
    private LinearLayout ll_content,ll_content_main;
    private int width;
    private boolean constainWidth = false;
    private boolean show = false;


    private String tittle = "";
    private String mensagem = "";
    private int color = R.color.colorPrimary;
    private int text_color = Color.WHITE;
    private int icon = -1;

    public AchievementView(Context context) {
        super(context);
    }

    public AchievementView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public AchievementView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.archivimentview,
                0, 0);

        try {

            tittle = typedArray.getString(R.styleable.archivimentview_ac_tittle);
            mensagem = typedArray.getString(R.styleable.archivimentview_ac_mensage);
            color = typedArray.getInt(R.styleable.archivimentview_ac_color,color);
            text_color = typedArray.getInt(R.styleable.archivimentview_ac_text_color,text_color);
            icon = typedArray.getResourceId(R.styleable.archivimentview_ac_icon,-1);

        }finally {
            typedArray.recycle();
        }

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.content_ach, this);
        assignUiElements();
    }

    private void assignUiElements() {

        img_left = (ImageView) findViewById(R.id.img_left);
        tv_titulo = (TextView) findViewById(R.id.tv_titulo);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
        rl_ach = (RelativeLayout) findViewById(R.id.rl_ach);
        ll_content_main = (LinearLayout) findViewById(R.id.ll_content_main);
        rl_ach.setAlpha(0f);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);

        tv_titulo.setText(tittle);

        tv_msg.setText(mensagem);

        GradientDrawable bgView = (GradientDrawable)ll_content_main.getBackground();
        bgView.setColor(color);

        tv_msg.setTextColor(text_color);
        tv_titulo.setTextColor(text_color);

        if (icon != -1)
        img_left.setImageDrawable(context.getResources().getDrawable(icon));

    }

    private void iniciarConf(){

        if (!constainWidth) {
            width = dpToPixel(context,250); //ll_content.getMeasuredWidth();
            ViewGroup.LayoutParams layoutParams = ll_content.getLayoutParams();
            layoutParams.width = 0;
            ll_content.setLayoutParams(layoutParams);
            tv_titulo.setAlpha(0f);
            tv_msg.setAlpha(0f);
            constainWidth = true;
        }

    }

    public AchievementView setClick(OnClickListener clickListern){
        ll_content_main.setOnClickListener(clickListern);

        return this;
    }

    public AchievementView setColor(int color){

        this.color = color;

        GradientDrawable bgView = (GradientDrawable)ll_content_main.getBackground();
        bgView.setColor(context.getResources().getColor(color));

        //ll_content_main.setBackgroundColor(context.getResources().getColor(color));

        return this;
    }

    public AchievementView setBorderRetangle(){

        ll_content_main.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.retangle_archiement));

        return this;
    }

    public AchievementView setIcon(int drawable){

        icon = drawable;

        img_left.setImageDrawable(context.getResources().getDrawable(drawable));

        return this;
    }

    public AchievementView setScaleTypeIcon(ImageView.ScaleType scaleTypeIcon){

        img_left.setScaleType(scaleTypeIcon);

        return this;
    }

    /*public AchievementView setTypeface(Typeface typeface){

        tv_titulo.setTypeface(typeface);
        tv_msg.setTypeface(typeface);

        return this;
    }*/
    public AchievementView setTitle(String titulo){

        tittle = titulo;

        tv_titulo.setText(titulo);

        return this;
    }

    public AchievementView setMensage(String msg){

        mensagem = msg;

        tv_msg.setText(msg);

        return this;
    }

    public AchievementView setTextColor(int color){

        text_color = color;
        tv_titulo.setTextColor(color);
        tv_msg.setTextColor(color);
        return this;
    }

    public void show(){

        iniciarConf();

        Log.i("LOG","width: "+width);

        if (!show) {

            Animation animation1 =
                    AnimationUtils.loadAnimation(context,
                            R.anim.scale_up);
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    ValueAnimator anim = ValueAnimator.ofInt(0, width);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = ll_content.getLayoutParams();
                            layoutParams.width = val;
                            ll_content.setLayoutParams(layoutParams);
                        }
                    });

                    anim.setDuration(600);
                    anim.start();
                    aplyAlpha(tv_titulo, 600, 0.7f, null);
                    aplyAlpha(tv_msg, 800, 1, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            new CountDownTimer(3000, 100) {

                                @Override
                                public void onTick(long l) {

                                }

                                @Override
                                public void onFinish() {
                                    hide();
                                }
                            }.start();
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            rl_ach.setAlpha(1f);
            rl_ach.startAnimation(animation1);

            show = true;
        }
    }

    private void hide() {

        aplyAlpha(tv_titulo, 200, 0,null);
        aplyAlpha(tv_msg, 400, 0, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ValueAnimator anim = ValueAnimator.ofInt(width, 0);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int val = (Integer) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = ll_content.getLayoutParams();
                        layoutParams.width = val;
                        ll_content.setLayoutParams(layoutParams);
                    }
                });

                anim.setDuration(600);
                anim.start();
                aplyAlpha(rl_ach, 650, 0, null);

                show = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    public static void aplyAlpha(View view, long delay, float alpha, Animator.AnimatorListener animatorListener){
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
