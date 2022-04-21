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

public class MainActivity extends AppCompatActivity implements MainAdapter.MainAdapterListener {

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
        mainAdapter.setListener(this);
        recyclerView = findViewById(R.id.rc_main);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesViewModel.getAllMainEntity().observe(this, new Observer<List<MainEntity>>() {
            @Override
            public void onChanged(List<MainEntity> mainEntities) {
                mainAdapter.setData(mainEntities);
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

    @Override
    public void onDelete(MainEntity entity) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete);
        TextView tv_ok_del_dialog = dialog.findViewById(R.id.tv_btn_OK_DELETE_DIALOG);
        tv_ok_del_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DELETE TEXT FROM DATABASE
                notesViewModel.deleteMainEntity(entity);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onEdit(MainEntity entity) {
        // CREATE DIALOG
        Dialog dialog = new Dialog(this);
        // SET CONTENT VIEW
        dialog.setContentView(R.layout.dialog_edit);

        /////
        EditText edt_stringOne_dialog = dialog.findViewById(R.id.dialog_stringOne_edit);
        EditText edt_stringTwo_dialog = dialog.findViewById(R.id.dialog_stringTwo_edit);
        TextView tv_click_ok_edit = dialog.findViewById(R.id.tv_btn_OK_dialog_edit);

        // GET TEXt 1 and 2
        String textOne = entity.getStringone();
        String textTwo = entity.getStringtwo();


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

                entity.setStringone(uTextOne);
                entity.setStringtwo(uTextTwo);
                // UPDATE TEXT IN DATABASE
                notesViewModel.updateMainEntity(entity);
            }
        });

        // SHOW DIALOG
        dialog.show();
    }
}