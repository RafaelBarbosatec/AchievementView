package com.rafaelbarbosatec.archivimenteview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rafaelbarbosatec.archivimentview.AchievementView;


public class MainActivity extends AppCompatActivity {


    private Button bt;
    private AchievementView achievementView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarViews();

    }


    private void iniciarViews() {

        achievementView = (AchievementView) findViewById(R.id.achievementView);

        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                achievementView
                        .setTitulo("Treino Finalizado")
                        .setMensagem("Você ganhou 30 pontos de força!")
                        .setColor(R.color.colorPrimaryDark)
                        .setIcon(R.drawable.ic_news)
                        .show();

            }
        });

    }
}
