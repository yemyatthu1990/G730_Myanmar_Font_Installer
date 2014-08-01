G730_Myanmar_Font_Installer
===========================

Myanmar font installer for Huawei G730-U00 which have a problem with simple zawgyi font embedded apps.
If you want a prebuilt, it is recommended to download it from [here](http://d-h.st/UVX).
For more details about how this app works, take a look at [here](https://www.facebook.com/talentambitiondestiny/posts/1435469900069838?fref=nf).

### Checking out the source

You'll need support v7. You can git clone it from this repository or get it using gradle/maven.

* $ mkdir /path/to/src/
* $ cd /path/to/src/
* $ git clone git://github.com/TAD-Dev-Team/G730_Myanmar_Font_Installer/appcompat_v7
* $ git clone git://github.com/TAD-Dev-Team/G730_Myanmar_Font_Installer/TADG730MMFI

Make sure the SDK Platform for API 19 is installed, through the Android SDK Manager.  Install NDK Revision 9b from [here](http://developer.android.com/tools/sdk/ndk/index.html).

### Eclipse

In Eclipse, import appcompat_v7 and TADG730MMFI. In your Package Explorer, right click on TADG730MMFI project -> Properties -> Android -> Add -> appcompat_v7. Now you're good to go with eclipse.

### Building the restore binary

Make sure you have the android-ndk downloaded with the tool "ndk-build" in your path.

* $ cd /path/to/src/
* $ cd TADG730MMFI
* $ ndk-build

The restore binary will built into TADG730MMFI/libs/armeabi/restore.

### Modifying the framework.odex

1. Pull all files from system/framework directory of your device.
2. Decompile framework.odex.
3. Edit "createFromAssets" method located in android/graphics/Typeface.smali. Codes to be editted are shown below.
4. SMALI the decompiled folder of framework.odex in a classes.dex file and pack it into framework.jar to make it deodexed.
5. Push it to somewhere on your device, odex and sign it
6. Now, this modded framework.odex will return only typeface of /system/framework/Roboto-Regular.ttf to Typface.createFromAssets(AssetManager mgr, String path) used in application. So that, fonts from assets of any custom font embedded apps will not take effect anymore.


##### References for decompile & recompiling odex files
* [
[HOW TO][Windows] Manually Deodex and Odex back](http://forum.xda-developers.com/galaxy-s2/themes-apps/how-to-manually-deodex-odex-t1208320). This method also works on Linux and OS X.
* [[GUIDE/TOOL] Make a new odex file (1by1_ReOdexer batch file)](http://forum.xda-developers.com/showthread.php?t=1853569).
* [
[HOW-TO] Decompile ICS and above apk and jar files MANUALLY](http://forum.xda-developers.com/showthread.php?t=1732635).

**Orginal "createFromAssets" method**
```
.method public static createFromAsset(Landroid/content/res/AssetManager;Ljava/lang/String;)Landroid/graphics/Typeface;
    .registers 4
    .param p0, "mgr"    # Landroid/content/res/AssetManager;
    .param p1, "path"    # Ljava/lang/String;

    .prologue
    .line 164
    new-instance v0, Landroid/graphics/Typeface;

    invoke-static {p0, p1}, Landroid/graphics/Typeface;->nativeCreateFromAsset(Landroid/content/res/AssetManager;Ljava/lang/String;)I

    move-result v1

    invoke-direct {v0, v1}, Landroid/graphics/Typeface;-><init>(I)V

    return-object v0
.end method
```
**Modded "createFromAssets" method**
```
.method public static createFromAsset(Landroid/content/res/AssetManager;Ljava/lang/String;)Landroid/graphics/Typeface;
    .registers 4
    .param p0, "mgr"    # Landroid/content/res/AssetManager;
    .param p1, "path"    # Ljava/lang/String;

    .prologue
    .line 164
    new-instance v0, Landroid/graphics/Typeface;

    const-string p1, "Patched By Aung Thiha"

    const-string p0, "/system/fonts/Roboto-Regular.ttf"

    invoke-static {p0}, Landroid/graphics/Typeface;->nativeCreateFromFile(Ljava/lang/String;)I

    move-result v1

    invoke-direct {v0, v1}, Landroid/graphics/Typeface;-><init>(I)V

    return-object v0
.end method
```
