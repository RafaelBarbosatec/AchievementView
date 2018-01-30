package com.rafaelbarbosatec.archivimentview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
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

import com.rafaelbarbosatec.archivimentview.iterface.AchievementListern;
import com.rafaelbarbosatec.archivimentview.iterface.ShowListern;
import com.rafaelbarbosatec.archivimentview.util.FunctionsUtil;

/**
 * Created by dev on 20/10/17.
 */

public class AchievementView extends RelativeLayout implements AchievementListern {

    //3000 milisegundos
    public static final int TIMER_DEFAUT = 3000;
    public static final int TIMER_INDETERMINATE = 0;

    private int timer_duration = TIMER_DEFAUT;
    private Context context;
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
    private ShowListern showListern;

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

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        inflater.inflate(R.layout.content_ach, this);

        assignUiElements();

    }

    @SuppressLint("ResourceAsColor")
    private void assignUiElements() {

        img_left = findViewById(R.id.img_left);
        tv_titulo =  findViewById(R.id.tv_titulo);
        tv_msg =  findViewById(R.id.tv_msg);
        rl_ach =  findViewById(R.id.rl_ach);
        ll_content_main =  findViewById(R.id.ll_content_main);
        rl_ach.setAlpha(0f);
        ll_content =  findViewById(R.id.ll_content);

        tv_titulo.setText(tittle);

        tv_msg.setText(mensagem);

        GradientDrawable bgView = (GradientDrawable)ll_content_main.getBackground();
        bgView.setColor(color);

        tv_msg.setTextColor(text_color);
        tv_titulo.setTextColor(text_color);

        if (icon != -1)
            img_left.setImageDrawable(context.getResources().getDrawable(icon));

    }

    /**
     * Configura view para iniciar animação
     */
    private void iniciarConfStart(){

        if (!constainWidth) {
            width = FunctionsUtil.dpToPixel(context,250); //ll_content.getMeasuredWidth();
            ViewGroup.LayoutParams layoutParams = ll_content.getLayoutParams();
            layoutParams.width = 0;
            ll_content.setLayoutParams(layoutParams);
            tv_titulo.setAlpha(0f);
            tv_msg.setAlpha(0f);
            constainWidth = true;
        }

    }

    /**
     * Metodo que seta onClick
     * @param clickListern
     * @return
     */
    @Override
    public AchievementListern setClick(OnClickListener clickListern){

        ll_content_main.setOnClickListener(clickListern);

        return this;
    }

    /**
     * Metodo que seta listern para escultar evendos do achievement
     * @param listern
     * @return
     */
    @Override
    public AchievementListern setShowListern(ShowListern listern){

        showListern = listern;

        return this;
    }

    /**
     * Metodo seta cor do achievement
     * @param color
     * @return
     */
    @Override
    public AchievementListern setColor(int color){

        this.color = color;

        GradientDrawable bgView = (GradientDrawable)ll_content_main.getBackground();
        bgView.setColor(context.getResources().getColor(color));

        return this;
    }

    /**
     * Metodo seta forma retangular no achievement
     * @return
     */
    @Override
    public AchievementListern setBorderRetangle(){

        ll_content_main.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.retangle_archiement));

        GradientDrawable bgView = (GradientDrawable)ll_content_main.getBackground();
        bgView.setColor(context.getResources().getColor(color));
        
        return this;
    }

    /**
     * Metodo seta icone do achievement
     * @param drawable
     * @return
     */
    @Override
    public AchievementListern setIcon(int drawable){

        icon = drawable;

        img_left.setImageDrawable(context.getResources().getDrawable(drawable));

        return this;
    }

    /**
     * Metodo seta scaletype do icone do achievement
     * @param scaleTypeIcon
     * @return
     */
    @Override
    public AchievementListern setScaleTypeIcon(ImageView.ScaleType scaleTypeIcon){

        img_left.setScaleType(scaleTypeIcon);

        return this;
    }

    /**
     * Metodo set titulo no achievement
     * @param titulo
     * @return
     */
    @Override
    public AchievementListern setTitle(String titulo){

        tittle = titulo;

        tv_titulo.setText(titulo);

        return this;
    }

    /**
     * Metodo seta mensagem no achievement
     * @param msg
     * @return
     */
    @Override
    public AchievementListern setMensage(String msg){

        mensagem = msg;

        tv_msg.setText(msg);

        return this;
    }

    /**
     * Metodo seta duração do achievement
     * @param duration_milli
     * -Tempo em milisegundos o default é 3000 (3 segundos)
     * -Se igual a 0 ele nunca dar dimiss a não ser que o mande
     * @return
     */
    @Override
    public AchievementListern setDuration(int duration_milli){

        timer_duration = duration_milli;

        return this;
    }

    /**
     * Metodo seta cor do texto do achievement
     * @param color
     * @return
     */
    @Override
    public AchievementListern setTextColor(int color){

        text_color = color;
        tv_titulo.setTextColor(color);
        tv_msg.setTextColor(color);
        return this;
    }

    /**
     * Methodo e inicia exibição do achievement
     */
    @Override
    public void show(){

        iniciarConfStart();

        if (!show) {

            Animation animation1 =
                    AnimationUtils.loadAnimation(context,
                            R.anim.scale_up);
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                    if (showListern != null){
                        showListern.start();
                    }
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
                    FunctionsUtil.applyAlpha(tv_titulo, 600, 0.7f, null);
                    FunctionsUtil.applyAlpha(tv_msg, 800, 1, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {

                            if (showListern != null){
                                showListern.show();
                            }

                            if (timer_duration > 0)
                                new CountDownTimer(timer_duration, timer_duration) {

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

    /**
     * Metodo que inicia o hide do achievement
     */
    @Override
    public void dimiss() {

        if (show){
            hide();
        }

    }

    /**
     * Metodo que dar hide no achievement
     */
    private void hide() {

        if (showListern != null){
            showListern.dimiss();
        }

        FunctionsUtil.applyAlpha(tv_titulo, 0, 0,null);
        FunctionsUtil.applyAlpha(tv_msg, 200, 0, new Animator.AnimatorListener() {
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
                FunctionsUtil.applyAlpha(rl_ach, 650, 0, new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                        if (showListern != null){
                            showListern.end();
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

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



}
