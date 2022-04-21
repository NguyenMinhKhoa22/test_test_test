package com.example.room_database12;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterFactory;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{


    private List<MainEntity> mainEntityList = new ArrayList<>();
    private Context context_main;
    private MainAdapterListener listener;

    interface MainAdapterListener {
        void onDelete(MainEntity entity);
        void onEdit(MainEntity entity);
    }

    public void setListener(MainAdapterListener listener){
        this.listener = listener;
    }

    //Constructor
    public MainAdapter() {

    }

    public void setData(List<MainEntity> mainEntityList) {
        this.mainEntityList = mainEntityList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context_main = parent.getContext();
        return new MainAdapter.MainViewHolder(LayoutInflater.from(context_main).inflate(R.layout.row_main,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        // INITIALIZE MAIN ENTITY
        MainEntity mainEntity = mainEntityList.get(position);

        String string_one = mainEntity.getStringone();
        String string_two = mainEntity.getStringtwo();

        // SET TEXT ON TEXT VIEW ROW
        holder.txt_stringOne.setText(string_one);

        // SET TEXT ON TEXT VIEW ROW
        holder.txt_stringTwo.setText(string_two);

        // SET IMAGE DEL AND EDIT ON ROW
        holder.img_edit.setImageResource(R.drawable.ic_edit);
        holder.img_del.setImageResource(R.drawable.ic_delete);

        // CLICK TO OPEN DIALOG EDIT ROW
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainEntity d = mainEntityList.get(holder.getAdapterPosition());
                listener.onEdit(d);
            }
        });

        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainEntity d = mainEntityList.get(holder.getAdapterPosition());
                listener.onDelete(d);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainEntityList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView txt_stringOne, txt_stringTwo;
        ImageView img_edit, img_del;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_stringOne = itemView.findViewById(R.id.stringOne_row);
            txt_stringTwo = itemView.findViewById(R.id.stringTwo_row);
            img_edit = itemView.findViewById(R.id.imageView_edit);
            img_del = itemView.findViewById(R.id.imageView_delete);
        }
    }
}
