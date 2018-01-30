package com.rafaelbarbosatec.archivimenteview;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.rafaelbarbosatec.archivimentview.AchievementView;
import com.rafaelbarbosatec.archivimentview.iterface.ShowListern;


public class MainActivity extends AppCompatActivity {


    private Button bt;
    private AchievementView achievementView, achievementView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarViews();

    }


    private void iniciarViews() {

        achievementView = (AchievementView) findViewById(R.id.achievementView);

        achievementView2 = (AchievementView) findViewById(R.id.achievementView2);

        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                achievementView.show();

                achievementView2
                        .setTitle("Treino Finalizado")
                        .setMensage("Você ganhou 50 pontos de força!")
                        .setBorderRetangle()
                        .setColor(R.color.colorAccent)
                        .setIcon(R.drawable.ic_sun)
                        .setDuration(AchievementView.TIMER_INDETERMINATE)
                        .setScaleTypeIcon(ImageView.ScaleType.CENTER_INSIDE)
                        .setClick(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                achievementView2.dimiss();
                            }
                        })
                        .setShowListern(new ShowListern() {
                            @Override
                            public void start() {
                                Log.i("LOG","start");
                            }

                            @Override
                            public void show() {
                                Log.i("LOG","show");
                            }

                            @Override
                            public void dimiss() {
                                Log.i("LOG","dimiss");
                            }

                            @Override
                            public void end() {
                                Log.i("LOG","end");
                            }
                        })
                        .show();


            }
        });

    }
}
