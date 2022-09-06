# 忽略警告
#-ignorewarning

# 混淆保护自己项目的部分代码以及引用的第三方jar包
#-libraryjars libs/xxxxxxxxx.jar

# 不混淆被 Log 注解的方法信息
-keepclassmembernames class ** {
    @com.can.album.aop.Log <methods>;
}