package com.bigbadegg.aseekbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by bigbadegg on 2017/8/30.
 */

public class ASeekBar extends LinearLayout implements SeekBar.OnSeekBarChangeListener {
    private int mStallCount = 4;//档位数量
    private SeekBar mSeekBar;
    private TextView StallName;//档位名称
    private String mStallName;
    private int mDefaultStall = 0;//默认档位
    private TextView mStallLast;
    private int mTextColor;//文字颜色
    private int mThump;//滑动icon
    private int mProgressDrawable;

    public ASeekBar(Context context) {
        this(context, null);
    }

    public ASeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ASeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ASeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ASeekBar);
        mStallCount = array.getInteger(R.styleable.ASeekBar_stall_count, mStallCount);
        mStallName = array.getString(R.styleable.ASeekBar_stall_name);
        mDefaultStall = array.getInteger(R.styleable.ASeekBar_default_stall, mDefaultStall);
        mTextColor = array.getColor(R.styleable.ASeekBar_text_color, getResources().getColor(R.color.colorPrimary));
        mThump = array.getResourceId(R.styleable.ASeekBar_seekbar_thump, R.drawable.default_seek);
        mProgressDrawable = array.getResourceId(R.styleable.ASeekBar_seekbar_progress, R.drawable.bg_seekbar);
        //解析布局，这里的this很关键
        LayoutInflater.from(context).inflate(R.layout.aseekbar, this);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mSeekBar.setMax((mStallCount - 1) * 10);//最大档位*10  这样会有更加丝滑的感觉，如果仅仅设置最大为档位数的话，很生硬。
        mSeekBar.setProgress(mDefaultStall * 10);//这个默认档位，最小档位是0还是1呢？
        mSeekBar.setThumb(getResources().getDrawable(mThump));
        mSeekBar.setProgressDrawable(getResources().getDrawable(mProgressDrawable));
        StallName = (TextView) findViewById(R.id.tv_stall_name);
        StallName.setTextColor(mTextColor);
        StallName.setText(TextUtils.isEmpty(mStallName) ? "" : mStallName);
        mSeekBar.setOnSeekBarChangeListener(this);
        if (mStallCount > 5) mStallCount = 5;

        //根据档位数量来选择显示几个档位(范围是最小3个档，最大5个档)
        if (mStallCount <= 3) {//档位小于三，也就是最小情况
            findViewById(R.id.tv_stall_num2).setVisibility(GONE);
            findViewById(R.id.tv_stall_num3).setVisibility(GONE);
        } else if (mStallCount == 4) {
            findViewById(R.id.tv_stall_num3).setVisibility(GONE);
        }
        //最后一个档位
        mStallLast = (TextView) findViewById(R.id.tv_stall_num_last);
        mStallLast.setText(mStallCount - 1 + "");

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        int pro = seekBar.getProgress();
        //根据不同的区间，进行选择档位
        if (pro <= 5) {
            seekBar.setProgress(0);
            if (aSeekBarListener != null) aSeekBarListener.SeekBarChange(0);
        } else if (5 < pro && pro <= 15) {
            seekBar.setProgress(10);
            if (aSeekBarListener != null) aSeekBarListener.SeekBarChange(1);
        } else if (15 < pro && pro <= 25) {
            seekBar.setProgress(20);
            if (aSeekBarListener != null) aSeekBarListener.SeekBarChange(2);
        } else if (25 < pro && pro <= 35) {
            seekBar.setProgress(30);
            if (aSeekBarListener != null) aSeekBarListener.SeekBarChange(3);
        } else if (35 < pro && pro <= 45) {
            seekBar.setProgress(40);
            if (aSeekBarListener != null) aSeekBarListener.SeekBarChange(4);
        }
    }

    /********************************************自定义监听事件***************************************/
    private ASeekBarListener aSeekBarListener;

    public void setOnASeekBarListener(ASeekBarListener aSeekBarListener) {
        this.aSeekBarListener = aSeekBarListener;
    }

    public interface ASeekBarListener {
        void SeekBarChange(int stall);
    }
    /********************************************暴露外部方法*****************************************/

    /**
     * 调节档位外部调用方法
     * 0起始
     *
     * @param level
     */
    public void setStall(int level) {

        mSeekBar.setProgress(level * 10);

    }

    /**
     * 是否可以调节
     *
     * @param b
     */
    public void setEnable(boolean b) {

        mSeekBar.setEnabled(b);
    }
}
