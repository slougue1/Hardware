package com.example.shoppinglist;
/*This program linked data + output*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.CustomViewHolder>{
    List<ShopList> shopList;

    public ShopListAdapter(List<ShopList> shopList) {
        this.shopList = shopList;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list,parent,false);
        CustomViewHolder get_result = new CustomViewHolder(view);
        return get_result;
    }
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.nameText.setText(shopList.get(position).getName());
        holder.priceText.setText(shopList.get(position).getPrice());
        holder.urlText.setText(shopList.get(position).getUrl());
    }
    @Override
    public int getItemCount() {
        return shopList.size();
    }
    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView nameText, priceText, urlText;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            priceText = itemView.findViewById(R.id.priceText);
            urlText = itemView.findViewById(R.id.urlText);
        }
    }
}


