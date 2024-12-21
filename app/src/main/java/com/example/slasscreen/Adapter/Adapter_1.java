package com.example.slasscreen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.slasscreen.R;
import com.example.slasscreen.models.Models_1;

import java.util.List;

public class Adapter_1 extends RecyclerView.Adapter<Adapter_1.Viewholder> {
    List<Models_1> list;
    Context context;

    public Adapter_1(List<Models_1> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_1.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_1.Viewholder holder, int position) {

        Models_1 models1 =list.get(position);
        holder.textView.setText(models1.getName());
        Glide.with(context).load(models1.getAvater()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(models1.getHtmlUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends  RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.username);
            imageView=itemView.findViewById(R.id.avatar);


        }
    }
}
