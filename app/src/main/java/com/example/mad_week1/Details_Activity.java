package com.example.mad_week1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.DataUser;

public class Details_Activity extends AppCompatActivity {
    private TextView textView_Name,textView_Age,textView_Address;
    private ImageView imageView_backdet;
    private ImageButton imageButton_edit,imageButton_delete;
    ArrayList<DataUser> userdatalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_);

        initview();

        Intent intent = getIntent();
        userdatalist = intent.getParcelableArrayListExtra("arraylist");
        int pos = intent.getIntExtra("position",-1);

        textView_Name.setText(userdatalist.get(pos).getName());
        textView_Age.setText(String.valueOf(userdatalist.get(pos).getAge()));
        textView_Address.setText(userdatalist.get(pos).getAddress());

        imageButton_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(),Add_Edit_Activity.class);
                in.putExtra("kondisi", "edit");
                in.putExtra("position", pos);
                in.putParcelableArrayListExtra("arraylist", userdatalist);
                startActivity(in);
                finish();
            }
        });
        imageButton_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Details_Activity.this);
                alertBuilder.setTitle("Confirmation");
                alertBuilder.setMessage("Are you sure you want to delete " + userdatalist.get(pos).getName()+" data?");
                alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Loading load = new Loading(Details_Activity.this);
                        load.startLoading();

                        userdatalist.remove(pos);
                        Intent inte = new Intent(getBaseContext(),MainActivity.class);
                        inte.putParcelableArrayListExtra("arraylist", userdatalist);


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                load.dismissDialog();
                                startActivity(inte);
                                finish();
                            }
                        },1500);

                    }
                });
                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertBuilder.show();
            }
        });

        imageView_backdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void initview(){
        textView_Name = findViewById(R.id.textView_Name);
        textView_Age = findViewById(R.id.textView_Age);
        textView_Address = findViewById(R.id.textView_Address);
        imageView_backdet = findViewById(R.id.imageView_backdet);
        imageButton_edit = findViewById(R.id.imageButton_edit);
        imageButton_delete = findViewById(R.id.imageButton_delete);
    }


}