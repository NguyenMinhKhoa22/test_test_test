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

import java.util.Collection;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{


    private List<MainEntity> mainEntityList;
    private Context context_main;

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

                // CREATE DIALOG
                Dialog dialog = new Dialog(context_main);
                // SET CONTENT VIEW
                dialog.setContentView(R.layout.dialog_edit);

                /////
                EditText edt_stringOne_dialog = dialog.findViewById(R.id.dialog_stringOne_edit);
                EditText edt_stringTwo_dialog = dialog.findViewById(R.id.dialog_stringTwo_edit);
                TextView tv_click_ok_edit = dialog.findViewById(R.id.tv_btn_OK_dialog_edit);

                // GET TEXt 1 and 2
                String textOne = d.getStringone();
                String textTwo = d.getStringtwo();


                // SET TEXT ON EDIT TEXT
                edt_stringOne_dialog.setText(textOne);
                edt_stringTwo_dialog.setText(textTwo);

                tv_click_ok_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        // GET UPDATED TEXT FROM EDIT TEXT
                        String uTextOne = edt_stringOne_dialog.getText().toString().trim();
                        String uTextTwo = edt_stringTwo_dialog.getText().toString().trim();

                      // UPDATE TEXT IN DATABASE

                        // :V HELP ANH ƠI


                    }
                });

                // SHOW DIALOG
                dialog.show();

            }
        });

        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context_main);
                dialog.setContentView(R.layout.dialog_delete);
                TextView tv_ok_del_dialog = dialog.findViewById(R.id.tv_btn_OK_DELETE_DIALOG);
                tv_ok_del_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                        // DELETE TEXT FROM DATABASE
               
                    }
                });

            dialog.show();


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
