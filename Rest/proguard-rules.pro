-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# removes such information by default, so configure it to keep all of it. 
-keepattributes Signature 

# Gson specific classes 
-keep class sun.misc.Unsafe { *; } 
#-keep class com.google.gson.stream.** { *; } 

# Application classes that will be serialized/deserialized over Gson 
-keep class com.google.gson.examples.android.model.** { *; }

#pinyin4j-2.5.0.jar 
-dontwarn net.sourceforge.** 
-dontwarn com.hp.** 
-dontwarn demo.** 
-keep class net.sourceforge.** { *; }
-keep class com.hp.** { *; }
-keep class demo.** { *; }
-keep public class * extends net.sourceforge.**
-keep public class * extends com.hp.**

#bouncycastle.jar
-dontwarn org.bouncycastle.** 
-keep class org.bouncycastle.** { *; }
-keep public class * extends org.bouncycastle.**
-keep class org.bouncycastle.**{*;} 
-keep public interface org.bouncycastle.** {*;}

#android-support-v4.jar 
-dontwarn android.support.** 
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep class org.apache.**{*;} 

#afinal_0.5_bin.jar
-dontwarn net.tsz.afinal.** 
-keep class net.tsz.afinal.** { *; }
-keep public class * extends net.tsz.afinal.**
-keep class net.tsz.afinal.**{*;} 
-keep public interface net.tsz.afinal.** {*;}

#xutils_http_2.6.14.jar
-dontwarn com.lidroid.xutils.** 
-keep class com.lidroid.xutils.** { *; }
-keep public class * extends com.lidroid.xutils.**
-keep class com.lidroid.xutils.**{*;} 
-keep public interface com.lidroid.xutils.** {*;}

#locSDK_4.2.jar
-dontwarn com.baidu.location.** 
-keep class com.baidu.location.** { *; }
-keep public class * extends com.baidu.location.**

#alex-word-filter.jar
-dontwarn alex.zhrenjie04.**
-keep class alex.zhrenjie04.** { *; }
-keep public class * extends alex.zhrenjie04.**

#umeng-analytics-v5.5.3.jar
-dontwarn com.umeng.**
-keep class com.umeng.** { *; }
-keep public class * extends com.umeng.**

#javase-3.1.0.jar
-dontwarn com.google.zxing.**
-keep class com.google.zxing.** { *; }
-keep public class * extends com.google.zxing.**

#greendao-1.3.0-beta-1.jar
-dontwarn de.greenrobot.dao.**
-keep class de.greenrobot.dao.** { *; }
-keep public class * extends de.greenrobot.dao.**

#fastjson-1.2.6.jar
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
-keep public class * extends com.alibaba.fastjson.**


#jpush-sdk-release1.7.5.jar
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep public class * extends cn.jpush.**

#oss-android-sdk-1.2.0.jar
-dontwarn com.alibaba.sdk.**
-keep class com.alibaba.sdk.** { *; }
-keep public class * extends com.alibaba.sdk.**

#ihyphotoview.jar
-dontwarn com.imhuayou.photoview.**
-keep class com.imhuayou.photoview.** { *; }
-keep public class * extends com.imhuayou.photoview.**

#database
-keep class com.easybenefit.commons.database.** { *; }

#project
-keep class com.easybenefit.commons.ui.** { *; }

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
	public <init>(android.content.Context, android.util.AttributeSet);
	public <init>(android.content.Context, android.util.AttributeSet, int);
	public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class **.R$* { public static <fields>;}

-keepclassmembers class * { 
  public <init>(android.content.Context); 
} 
-keepattributes *Annotation*
