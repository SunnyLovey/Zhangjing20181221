package com.example.zhangjing20181221.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangjing20181221.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class CodeActivity extends AppCompatActivity implements QRCodeView.Delegate{

    private ZXingView zXingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        //获取资源ID
        zXingView = findViewById(R.id.zxing_view);
        zXingView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingView.startCamera();//打开相机
        zXingView.startSpotAndShowRect();
        zXingView.openFlashlight();//打开闪光灯
    }

    @Override
    protected void onStop() {
        super.onStop();
        zXingView.stopCamera();//关闭相机
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zXingView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
