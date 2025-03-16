![](https://github.com/galaxygoldfish/waveslider/blob/main/media/WaveSliderBanner.png)
<div>
  <img src="https://img.shields.io/github/stars/galaxygoldfish/waveslider?style=for-the-badge&color=8FDEFF" />
  <img src="https://img.shields.io/github/watchers/galaxygoldfish/waveslider?color=C9A8FF&style=for-the-badge" />
  <img src="https://img.shields.io/github/v/release/galaxygoldfish/waveslider?color=8FFF9B&label=version&style=for-the-badge" />
  <img src="https://img.shields.io/static/v1?label=LICENSE&message=APACHE%202.0&color=FF9E9E&style=for-the-badge" />
</div>
<br>

🌊 Animated wavy slider component as a light library for jetpack compose, similar to the slider present in the Android 13 media player notification

- Customizable
- Lightweight 
- Easy to use 

### Implementation

1. Make sure you have JitPack added as a repository in your root-level build.gradle file
```gradle
allprojects {
    repositories {
        ..
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency in your ```:app``` level build.gradle
```gradle
dependencies {
      ..
      implementation 'com.github.galaxygoldfish:waveslider:VERSION'
 }
```

### Demo

View the full documentation [here](https://github.com/galaxygoldfish/waveslider/wiki/Documentation), and download the latest demo app [here](https://github.com/galaxygoldfish/waveslider/releases)

<img src="https://github.com/galaxygoldfish/waveslider/blob/main/media/WaveSliderDemoPreview.png" width=30% align="left" />

```kotlin
var sliderValue by remember { mutableStateOf(0.4F) }

WaveSlider(
    value = sliderValue,
    onValueChange = { sliderValue = it },
    animationOptions = WaveSliderDefaults.animationOptions(
        reverseDirection = false,
        flatlineOnDrag = true,
        animateWave = true,
        reverseFlatline = false
    ),
    thumb = { DiamondThumb() },
    colors = WaveSliderDefaults.colors(
        thumbColor = MaterialTheme.colorScheme.secondary,
        activeTrackColor = MaterialTheme.colorScheme.secondary
    ),
    waveOptions = WaveSliderDefaults.waveOptions(
        amplitude = 10F,
        frequency = 0.07F,
    ),
    modifier = Modifier.padding(
        horizontal = 20.dp, 
        vertical = 50.dp
    )
)
```


<br></br>
### License
```
Copyright 2023 Sebastian Hriscu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
