G730_Myanmar_Font_Installer
===========================

### Checking out the source

You'll need support v7. You can git clone it from this repository or get it from google.

* $ mkdir /path/to/src/
* $ cd /path/to/src/
* $ git clone git://github.com/TAD-Dev-Team/G730_Myanmar_Font_Installer/appcompat_v7
* $ git clone git://github.com/TAD-Dev-Team/G730_Myanmar_Font_Installer/TADG730MMFI

Make sure the SDK Platform for API 19 is installed, through the Android SDK Manager.  Install NDK Revision 9b from [here](http://developer.android.com/tools/sdk/ndk/index.html).

### Eclipse

In Eclipse, import appcompat_v7 and TADG730MMFI. In your package explorer, right click on TADG730MMFI project -> Properties -> Android -> Add -> appcompat_v7. Now you're good to go with eclipse.

### Building the restore binary

Make sure you have the android-ndk downloaded with the tool "ndk-build" in your path.

* $ cd /path/to/src/
* $ cd TADG730MMFI
* $ ndk-build

The restore binary will built into TADG730MMFI/libs/armeabi/restore.

### Modifying the framework.odex

1. Pull all files from system/framework directory of your device
2. Decompile framework.odex
3. Edit "createFromAssets" method located in android/graphics/Typeface.smali
   Codes to be editted are shown below.


