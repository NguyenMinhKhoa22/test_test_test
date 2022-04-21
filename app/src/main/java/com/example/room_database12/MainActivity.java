package com.example.room_database12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewModel notesViewModel;

    MainAdapter mainAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView_btnAdd = findViewById(R.id.tv_btn_click);

        textView_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        notesViewModel = ViewModelProviders.of(this).get(ViewModel.class);

        mainAdapter = new MainAdapter();

        recyclerView = findViewById(R.id.rc_main);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesViewModel.getAllMainEntity().observe(this, new Observer<List<MainEntity>>() {
            @Override
            public void onChanged(List<MainEntity> mainEntities) {

                if(mainEntities.size() > 0) {
                    mainAdapter.setData(mainEntities);
                    recyclerView.setAdapter(mainAdapter);
                }

            }
        });


        notesViewModel.getAllMainEntity().observe(this, new Observer<List<MainEntity>>() {
            @Override
            public void onChanged(List<MainEntity> mainEntities) {

            }
        });
    }

    public void addNote() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView btnOK = dialog.findViewById(R.id.tv_btn_OK_dialog_edit);

        final EditText edt_stringOne = dialog.findViewById(R.id.dialog_stringOne_add);
        final EditText edt_stringTwo = dialog.findViewById(R.id.dialog_stringTwo_add);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MainEntity mainEntity = new MainEntity();
                mainEntity.setStringone(edt_stringOne.getText().toString());
                mainEntity.setStringtwo(edt_stringTwo.getText().toString());

                notesViewModel.insertMainEntity(mainEntity);
                dialog.dismiss();

            }
        });

        dialog.show();

    }
}