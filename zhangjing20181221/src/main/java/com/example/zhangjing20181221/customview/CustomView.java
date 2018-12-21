package com.example.zhangjing20181221.customview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zhangjing20181221.R;
import com.example.zhangjing20181221.activity.CodeActivity;

public class CustomView extends LinearLayout {
    private Context mContext;
    private ImageView imageView_saomiao;

    public CustomView(Context context) {
        super(context);
        mContext=context;
        initView();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView();
    }

    private void initView() {
        View view=View.inflate(mContext, R.layout.layout_custom,null);
        imageView_saomiao = view.findViewById(R.id.saomiao);

        //开启二维码
        imageView_saomiao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, CodeActivity.class);
                mContext.startActivity(intent);
            }
        });
        addView(view);
    }
}
