四次元（原微次元） weiciyuan
=========
Sina Weibo Android App, require Android 4.1+, GPL v3 License

<a href="https://play.google.com/store/apps/details?id=org.qii.weiciyuan">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

<a href="https://play.google.com/store/apps/details?id=org.qii.weiciyuan">
  <img alt="Google Play"  width="200" height="200"
       src="https://raw.github.com/qii/weiciyuan/slidingmenu/qrcode.png" />
</a>

文档
--------------

HotFix Demo
--------------
利用本版本加上会员模块，试验HotFix，个人实验项目

Gradle 构建
--------------
- 版本
    - 最新 Android SDK
    - Gradle 1.8
- 环境变量
    - ANDROID_HOME
    - GRADLE_HOME，同时把bin放入path变量
- Android SDK 安装
    - Android SDK Build-tools 19+
    - Google Repository 4+
    - Google Play services 13+
    - Android Support Repository 3+
    - Android Support Library 19+
- 移除配置
    - 修改AndroidManifest.xml里面`com.google.android.maps.v2.API_KEY`为你的Google Map key
    - 移除AndroidManifest.xml里面`com.crashlytics.ApiKey`和AccountActivity的`Crashlytics.start(this)`，以免影响四次元的崩溃统计数据
- 编译
    - `gradle build`，编译好的apk在build/apk下面，没签名，需要签名的修改build.gradle
