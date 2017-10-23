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

                achievementView2
                        .setTitle("Treino Finalizado")
                        .setMensage("Você ganhou 50 pontos de força!")
                        .setColor(R.color.colorAccent)
                        .setIcon(R.drawable.ic_sun)
                        .setScaleTypeIcon(ImageView.ScaleType.CENTER_INSIDE)
                        .setClick(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"Click AchievementView",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

                achievementView
                        .setTitle("Treino Finalizado")
                        .setMensage("Você ganhou 30 pontos de força!")
                        .setBorderRetangle()
                        .setColor(R.color.colorPrimaryDark)
                        .setIcon(R.drawable.ic_news)
                        .show();

            }
        });

    }
}
