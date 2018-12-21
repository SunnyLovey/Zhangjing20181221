package com.example.zhangjing20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangjing20181221.R;
import com.example.zhangjing20181221.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<GoodsBean.Data> list;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<GoodsBean.Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.layout_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
     viewHolder.textView.setText(list.get(i).getSellerName());
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(1,OrientationHelper.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(manager);
        List<GoodsBean.Data.ListBean> list1 = this.list.get(i).getList();
        StagAdapter adapter=new StagAdapter(context);
        viewHolder.recyclerView.setAdapter(adapter);
        adapter.setList(list1);
        adapter.setOnLongItemClickListener(new StagAdapter.onLongItemClickListener() {
            @Override
            public void getUrl(int position, String detalUrl) {
                Toast.makeText(context,detalUrl,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_seller);
            recyclerView=itemView.findViewById(R.id.recycle_stag);
        }
    }

}
