# ASeekBarDemo
灰常灰常简单的一个自定义seekbar  
![image](https://raw.githubusercontent.com/o0o0oo00/test/master/aseekbar/ASeekBar.gif)  
支持更改一下内容（都不是必须添加的，都有默认值）：  
选择档位数量，最小3档，最大五档（因为鄙人认为两个档或者一个档没有意义了，超过五个档有点密集了，不太好）  
更改档位名称  
设置默认是第几档  
更改档位文字颜色  
更改滑块icon  
更改seekbar背景  

大家多多指教了

使用方法： 
在根目录下的build.gradle中添加     
![image](https://raw.githubusercontent.com/o0o0oo00/test/master/aseekbar/use.png)    
 maven { url 'https://dl.bintray.com/bigbd/maven' }  
 
 在app的build.gradle中添加  
 compile 'com.bigbadegg:aseekbar:1.0.0@aar'    
 然后再xml文件中进行如下使用  
 ```
  <com.bigbadegg.aseekbar.ASeekBar
        android:id="@+id/aseekbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:default_stall="1"
        app:seekbar_progress="@drawable/bg_seekbar"
        app:seekbar_thump="@mipmap/zanting"
        app:stall_count="4">
    </com.bigbadegg.aseekbar.ASeekBar>

    <com.bigbadegg.aseekbar.ASeekBar
        android:id="@+id/aseekbar2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:default_stall="2"
        app:stall_count="5"
        app:stall_name="档位"
        app:text_color="@color/colorAccent">

    </com.bigbadegg.aseekbar.ASeekBar>
    ```
