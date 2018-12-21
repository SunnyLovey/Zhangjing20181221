package com.example.zhangjing20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhangjing20181221.R;
import com.example.zhangjing20181221.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class FlowAdapter extends RecyclerView.Adapter<FlowAdapter.ViewHolder> {
    private List<GoodsBean.Data.ListBean> list;
    private Context context;

    public FlowAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();

    }

    public void setList(List<GoodsBean.Data.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_flow,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textView_title.setText(list.get(i).getTitle());
        viewHolder.textView_price.setText("￥"+list.get(i).getPrice());
        String images = list.get(i).getImages();
        String[] split = images.split("\\|");//分割字符串
        Glide.with(context).load(split[0]).into(viewHolder.imageView);

       viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               if(longItemClickListener!=null){
                   longItemClickListener.getUrl(i,list.get(i).getDetailUrl());
               }
               return true;
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView_title,textView_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView_flow);
            textView_price=itemView.findViewById(R.id.textView_price_flow);
            textView_title=itemView.findViewById(R.id.textView_title_flow);
        }
    }
    public onLongItemClickListener longItemClickListener;
    public void setOnLongItemClickListener(onLongItemClickListener itemClickListener){
        this.longItemClickListener=itemClickListener;
    }
    public interface onLongItemClickListener{
        void getUrl(int position,String detalUrl);
    }
}
