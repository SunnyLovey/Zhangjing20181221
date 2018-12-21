package com.example.zhangjing20181221.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.zhangjing20181221.MainActivity;
import com.example.zhangjing20181221.R;
import com.example.zhangjing20181221.adapter.GridAdapter;
import com.example.zhangjing20181221.adapter.ListAdapter;
import com.example.zhangjing20181221.adapter.ListAdapterOther;
import com.example.zhangjing20181221.adapter.StagAdapter;
import com.example.zhangjing20181221.bean.GoodsBean;
import com.example.zhangjing20181221.bean.GridBean;
import com.example.zhangjing20181221.presenter.IPresenterImpl;
import com.example.zhangjing20181221.utils.Path;
import com.example.zhangjing20181221.view.IView;

import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopActivity extends AppCompatActivity implements IView{
    private IPresenterImpl iPresenter;
    private GridAdapter adapter;
    private RecyclerView recyclerView_grid,recyclerView_list,recyclerView_flow;
    private final int lineCount=5;
    private ListAdapter listAdapter;
    private ListAdapterOther listAdapterOther;
    private ImageView imageView_change;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        iPresenter=new IPresenterImpl(this);
        initView();
        GridLayoutManager manager=new GridLayoutManager(ShopActivity.this,lineCount);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView_grid.setLayoutManager(manager);

        adapter=new GridAdapter(this);
        recyclerView_grid.setAdapter(adapter);

        Map<String,String> params=new HashMap<>();
        iPresenter.startRequest(Path.path_grid,params, GridBean.class);

        initStag();
        initFlow();
        imageView_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerView_list.getVisibility()==View.VISIBLE){
                    recyclerView_flow.setVisibility(View.VISIBLE);
                    recyclerView_list.setVisibility(View.INVISIBLE);
                }else {
                    recyclerView_flow.setVisibility(View.INVISIBLE);
                    recyclerView_list.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    private void initStag() {
        LinearLayoutManager manager=new LinearLayoutManager(ShopActivity.this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView_list.setLayoutManager(manager);

       listAdapter=new ListAdapter(this);
       recyclerView_list.setAdapter(listAdapter);


        Map<String,String> params=new HashMap<>();
        params.put("uid",71+"");
        iPresenter.startRequest(Path.path_goods,params, GoodsBean.class);


    }
    private void initFlow(){
        LinearLayoutManager manager=new LinearLayoutManager(ShopActivity.this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView_flow.setLayoutManager(manager);

        listAdapterOther=new ListAdapterOther(this);
        recyclerView_flow.setAdapter(listAdapterOther);
        Map<String,String> params=new HashMap<>();
        params.put("uid",71+"");
        iPresenter.startRequest(Path.path_goods,params, GoodsBean.class);

    }

    private void initView() {
        recyclerView_grid = findViewById(R.id.recycle_grid);
        recyclerView_flow=findViewById(R.id.recycle_flow);
        recyclerView_list=findViewById(R.id.recycle_list);
        imageView_change = findViewById(R.id.image_change);

    }

    @Override
    public void requestSuccess(Object data) {
          if(data instanceof GridBean){
              GridBean bean= (GridBean) data;
              List<GridBean.DataBean> data1 = bean.getData();
              adapter.setList(data1);
          }else if(data instanceof GoodsBean){
              GoodsBean bean= (GoodsBean) data;
              List<GoodsBean.Data> data1 = bean.getData();
              listAdapter.setList(data1);
              listAdapterOther.setList(data1);

          }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detachView();
    }
}
