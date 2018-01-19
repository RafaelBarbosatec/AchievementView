# AchievementView
[ ![Release](https://img.shields.io/github/release/RafaelBarbosatec/AchievementView.svg?label=jitpack) ](https://jitpack.io/#RafaelBarbosatec/AchievementView)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

<p align="center">
  <img src="https://github.com/RafaelBarbosatec/AchievementView/blob/master/imagem/exemplo.png" width="350"/>
  <!--<img src="https://github.com/RafaelBarbosatec/AchievementView/blob/master/imagem/example.gif" width="300"/>-->
</p>
<!--<img src="https://github.com/RafaelBarbosatec/AchievementView/blob/master/imagem/exemplo.png" width="350"/>
<img src="https://github.com/RafaelBarbosatec/AchievementView/blob/master/imagem/example.gif" width="350"/>-->
Library Achievemente View to show mensage or informative in your app

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
     compile 'com.github.RafaelBarbosatec:AchievementView:0.1.1'
}

```

Add in view
---

```xml

<com.rafaelbarbosatec.archivimentview.AchievementView
        android:id="@+id/achievementView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
	app:ac_tittle="Rafael Almeida"
        app:ac_mensage="kkkkkkkkkkkkkkk"
        app:ac_icon="@drawable/ic_news"
        app:ac_color="@color/colorPrimaryDark"
        app:ac_text_color="@color/colorAccent">

</com.rafaelbarbosatec.archivimentview.AchievementView>

```

Configuration
---

```java

achievementView = (AchievementView) findViewById(R.id.achievementView);

achievementView
            .setTitle("Treino Finalizado")
            .setMensage("Você ganhou 50 pontos de força!")
            //.setBorderRetangle()
            .setColor(R.color.colorAccent)
            .setIcon(R.drawable.ic_sun)
            //.setScaleTypeIcon(ImageView.ScaleType.CENTER_INSIDE)
            .setClick(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"Click AchievementView",Toast.LENGTH_SHORT).show();
                            }
                        })
            .show();

```

License
---

This library is licensed under the [Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

See [`LICENSE`](LICENSE) for full of the license text.

    Copyright (C) 2015 Haruki Hasegawa

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
