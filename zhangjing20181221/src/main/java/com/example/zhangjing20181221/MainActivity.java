package com.example.zhangjing20181221;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.zhangjing20181221.activity.ShopActivity;
import com.example.zhangjing20181221.adapter.ViewPagerAdapter;
import com.example.zhangjing20181221.bean.ImageBean;
import com.example.zhangjing20181221.presenter.IPresenterImpl;
import com.example.zhangjing20181221.utils.Path;
import com.example.zhangjing20181221.view.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView{
    private IPresenterImpl iPresenter;
    private ViewPager viewPager;
    private RadioGroup group;
    private ViewPagerAdapter adapter;
    private Button button;
    private int i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化IPresenterImpl
        iPresenter=new IPresenterImpl(this);
        initView(savedInstanceState);

        adapter=new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        Map<String,String> params=new HashMap<>();
        iPresenter.startRequest(Path.path_image,params, ImageBean.class);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.but1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.but2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.but3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.but4:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        group.check(R.id.but1);
                        break;
                    case 1:
                        group.check(R.id.but2);
                        break;
                    case 2:
                        group.check(R.id.but3);
                        break;
                    case 3:
                        group.check(R.id.but4);
                        button.setVisibility(View.VISIBLE);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

    }
     //获取资源ID
    private void initView(Bundle savedInstanceState) {
        viewPager = findViewById(R.id.viewpager);
        group = findViewById(R.id.group);
        button = findViewById(R.id.button_intent);
    }
//请求成功地数据
    @Override
    public void requestSuccess(Object data) {
        ImageBean bean= (ImageBean) data;
        List<ImageBean.Data> data1 = bean.getData();
        adapter.setList(data1);

    }
    //解除绑定

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detachView();
    }
}
