package com.example.mad_week1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.DataUser;

public class MainActivity extends AppCompatActivity {
    private RecyclerView RecyclerView_users;
    private FloatingActionButton users_floatingActionButton;
    public ArrayList<DataUser> userdatalist;
    private UsersListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupRecyclerView();
        setListener();
    }

    private void initView(){
        RecyclerView_users = findViewById(R.id.RecyclerView_users);
        users_floatingActionButton = findViewById(R.id.users_floatingActionButton);
        Intent intent = getIntent();
        userdatalist = intent.getParcelableArrayListExtra("arraylist");
        if (userdatalist == null){
            userdatalist = new ArrayList<DataUser>();
        }
        adapter = new UsersListAdapter(userdatalist);
    }
    private void setupRecyclerView(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        RecyclerView_users.setLayoutManager(manager);
        RecyclerView_users.setAdapter(adapter);
    }
    private void setListener(){
        users_floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Add_Edit_Activity.class);
                intent.putExtra("kondisi", "add");
                intent.putParcelableArrayListExtra("arraylist", userdatalist);
                startActivity(intent);
            }
        });
    }
}