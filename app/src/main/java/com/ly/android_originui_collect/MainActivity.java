package com.ly.android_originui_collect;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextSwitcher ts;
    private int marker = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ts = findViewById(R.id.ts);

        initView();

        bindEvent();
    }

    ArrayList<String> texts = new ArrayList<>();

    private void initView() {
        ts.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MainActivity.this);
                textView.setSingleLine();
                textView.setTextSize(22);//字号
                textView.setTextColor(Color.BLUE);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });
        doWhile();


        TabHost th=findViewById(R.id.tabhost);
        th.setup();            //初始化TabHost容器

        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        th.addTab(th.newTabSpec("tab1").setIndicator("标签1",getResources().getDrawable(R.mipmap.ic_launcher)).setContent(R.id.tab1));
        th.addTab(th.newTabSpec("tab2").setIndicator("标签2",null).setContent(R.id.tab2));
        th.addTab(th.newTabSpec("tab3").setIndicator("标签3",null).setContent(R.id.tab3));
}

    private void doWhile() {
        for ( int i = 0; i < 10; i++) {
            texts.add("内容：" + i);
            final int finalI = i;
            final int finalI1 = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ts.setText(texts.get(finalI));
                            if (finalI1 == 9){
                                doWhile();
                            }
                        }
                    });
                }
            }, i * 1000);
        }
    }
    private void bindEvent() {

    }

}
