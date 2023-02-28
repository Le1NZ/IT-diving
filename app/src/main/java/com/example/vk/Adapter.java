package com.example.vk;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Pair<String, String>> localData;
    private LayoutInflater localInflanter;

    public Adapter(Context context, List<Pair<String, String>> data) {
        this.localData = data;
        this.localInflanter = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    // Метод для преобразования разметки
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = localInflanter.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String, String> s = localData.get(position);
        holder.descriprion.setText(s.second);
    }

    @Override
    public int getItemCount() {
        return localData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView descriprion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.contactAvatar);
            descriprion = itemView.findViewById(R.id.contactName);
        }
    }
}
