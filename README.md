# AchievementView
[ ![Release](https://img.shields.io/github/release/RafaelBarbosatec/AchievementView.svg?label=jitpack) ](https://jitpack.io/#RafaelBarbosatec/AchievementView)

<img src="https://github.com/RafaelBarbosatec/AchievementView/blob/master/imagem/exemplo.png" width="350"/>
Achievemente View to show mensage for app

Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
dependencies {
    compile 'com.github.RafaelBarbosatec:AchievementView:0.1.0'
}

```

Add in view

```xml

<com.rafaelbarbosatec.archivimentview.AchievementView
        android:id="@+id/achievementView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

</com.rafaelbarbosatec.archivimentview.AchievementView>

```

Set configuration

```java

achievementView = (AchievementView) findViewById(R.id.achievementView);

achievementView
            .setTitle("Treino Finalizado")
            .setMensage("Você ganhou 50 pontos de força!")
            //.setBorderRetangle()
            .setColor(R.color.colorAccent)
            .setIcon(R.drawable.ic_sun)
            .show();

```
