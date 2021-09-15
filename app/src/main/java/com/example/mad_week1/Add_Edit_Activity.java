package com.example.mad_week1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.UserData;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import model.DataUser;

public class Add_Edit_Activity extends AppCompatActivity {
    private TextInputLayout textInput_Name,textInput_Age,textInput_Address;
    private Button button_addedit;
    private ImageView imageView_backarrow;
    private Boolean cekName, cekAge, cekAddress;
    private Loading loading;
    private TextView textView_toolbar;
    public ArrayList<DataUser> userdatalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__edit_);
        initView();

        Intent intent = getIntent();
        userdatalist = intent.getParcelableArrayListExtra("arraylist");
        if (userdatalist==null){
            userdatalist = new ArrayList<DataUser>();
        }
        int pos = intent.getIntExtra("position",-1);
        String kondisi = intent.getStringExtra("kondisi");

        imageView_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (kondisi.equalsIgnoreCase("add")){
            button_addedit.setText("Add Data");
            textView_toolbar.setText("Add User");

            button_addedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = textInput_Name.getEditText().getText().toString().trim();
                    int age = Integer.parseInt(textInput_Age.getEditText().getText().toString().trim());
                    String address = textInput_Address.getEditText().getText().toString().trim();
                    loading.startLoading();

                    DataUser temp = new DataUser(name,age,address);
                    userdatalist.add(temp);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("arraylist", userdatalist);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismissDialog();
                            startActivity(intent);
                            finish();
                        }
                    },2000);


                }
            });


            textInput_Name.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String name = textInput_Name.getEditText().getText().toString().trim();

                    if (!name.isEmpty()){
                        textInput_Name.setError("");
                        cekName = true;
                    }else {
                        cekName = false;
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (cekName ==true && cekAge==true&& cekAddress == true){
                        button_addedit.setEnabled(true);
                    }

                }
            });
            textInput_Age.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String age = textInput_Age.getEditText().getText().toString().trim();

                    if (!age.isEmpty()){
                        cekAge = true;
                    }else {
                        cekAge = false;
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (cekName ==true && cekAge==true&& cekAddress == true){
                        button_addedit.setEnabled(true);
                    }
                }
            });
            textInput_Address.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String address = textInput_Address.getEditText().getText().toString().trim();

                    if (!address.isEmpty()){
                        cekAddress = true;
                    }else {
                        cekAddress = false;
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (cekName ==true && cekAge==true&& cekAddress == true){
                        button_addedit.setEnabled(true);
                    }
                }
            });
        }else if(kondisi.equalsIgnoreCase("edit")){
            button_addedit.setEnabled(true);

            button_addedit.setText("Update Data");
            textView_toolbar.setText("Edit User");

            textInput_Name.getEditText().setText(userdatalist.get(pos).getName());
            textInput_Age.getEditText().setText(String.valueOf(userdatalist.get(pos).getAge()));
            textInput_Address.getEditText().setText(userdatalist.get(pos).getAddress());

            button_addedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = textInput_Name.getEditText().getText().toString().trim();
                    int age = Integer.parseInt(textInput_Age.getEditText().getText().toString().trim());
                    String address = textInput_Address.getEditText().getText().toString().trim();

                    DataUser temp = new DataUser(name,age,address);
                    userdatalist.set(pos,temp);

                    loading.startLoading();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("arraylist", userdatalist);

                    Handler handler =  new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.dismissDialog();
                            startActivity(intent);
                            finish();
                        }
                    },2000);

                }
            });
        }

    }
    private void initView(){
        textInput_Name = findViewById(R.id.textInput_Name);
        textInput_Age = findViewById(R.id.textInput_Age);
        textInput_Address = findViewById(R.id.textInput_Address);
        button_addedit = findViewById(R.id.button_addedit);
        imageView_backarrow= findViewById(R.id.imageView_backarrow);
        textView_toolbar = findViewById(R.id.textView_toolbar);
        button_addedit.setEnabled(false);
        cekName = false;
        cekAge = false;
        cekAddress = false;
        loading = new Loading(Add_Edit_Activity.this);

    }



}