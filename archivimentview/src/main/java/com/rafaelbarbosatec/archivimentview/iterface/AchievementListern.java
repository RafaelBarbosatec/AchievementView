package com.rafaelbarbosatec.archivimentview.iterface;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by rafaelbarbosa on 30/01/18.
 */

public interface AchievementListern {
    AchievementListern setClick(View.OnClickListener clickListern);
    AchievementListern setColor(int color);
    AchievementListern setBorderRetangle();
    AchievementListern setIcon(int drawable);
    AchievementListern setScaleTypeIcon(ImageView.ScaleType scaleTypeIcon);
    AchievementListern setTitle(String titulo);
    AchievementListern setMensage(String msg);
    AchievementListern setDuration(int duration);
    AchievementListern setTextColor(int color);
    AchievementListern setShowListern(ShowListern listern);
    void show();
    void dimiss();
}
