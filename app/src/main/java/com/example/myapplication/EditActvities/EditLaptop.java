package com.example.myapplication.EditActvities;

import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.DB.App;
import com.example.myapplication.DB.AppDatabase;
import com.example.myapplication.ObjectsRoom.LaptopDAO;
import com.example.myapplication.ObjectsRoom.LaptopPasswords;
import com.example.myapplication.R;


public class EditLaptop extends AppCompatActivity {
    private TextView tvSerial;
    private TextView tvOwner;
    private TextView tvModel;
    private TextView catNameLap;
    private TextView tvPasswordLap;
    private TextView descriptionLap;

    private EditText et_change_owner;
    private EditText et_change_password;
    private EditText et_change_description;

    private LaptopPasswords laptopBuf;

    private Button btnAllowChangesLap;
    private Button btnAllowChangeLap;
    private Button btn_delete_laptop;


    private Button btnBack;

    private LinearLayout lin;
    private boolean visibility = false;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_laptop);

        tvSerial = (TextView)findViewById(R.id.tv_Serial);
        tvOwner = (TextView)findViewById(R.id.tv_owner_laptop);
        tvModel = (TextView)findViewById(R.id.tv_laptop_model_ed);
        catNameLap = (TextView)findViewById(R.id.tv_laptop_category);
        tvPasswordLap = (TextView)findViewById(R.id.tv_laptop_password);
        descriptionLap = (TextView)findViewById(R.id.tv_laptop_description);


        btnAllowChangesLap = (Button)findViewById(R.id.btn_allow_changes_laptop);
        btnAllowChangeLap = (Button)findViewById(R.id.btn_change_laptop);
        lin = (LinearLayout)findViewById(R.id.linearLay);

        btnBack = (Button)findViewById(R.id.btn_laptop_comeback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ID = getIntent().getStringExtra("edit_laptop");

        initialize(ID);

        et_change_owner = (EditText)findViewById(R.id.et_change_laptop_owner);
        et_change_password = (EditText)findViewById(R.id.et_change_password_laptop);
        et_change_description = (EditText)findViewById(R.id.et_change_laptop_description);

        btn_delete_laptop = (Button)findViewById(R.id.btn_delete_laptop);


        btnAllowChangesLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!visibility) {
                    lin.setVisibility(View.VISIBLE);
                    btnAllowChangeLap.setVisibility(View.VISIBLE);
                    visibility = true;
                }else{lin.setVisibility(View.INVISIBLE);
                    btnAllowChangeLap.setVisibility(View.INVISIBLE);
                    visibility = false;}
            }
        });


        btn_delete_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = App.getInstance().getDatabase();
                LaptopDAO laptopDAO = db.LaptopDAO();
                LaptopPasswords laptop = laptopDAO.getById(laptopBuf.getSerialNumber());
                laptopDAO.delete(laptop);
                initialize(ID);
                finish();
            }
        });


        btnAllowChangeLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String owner = et_change_owner.getText().toString();
                    String password = et_change_password.getText().toString();
                    String description = et_change_description.getText().toString();

                    if(!description.isEmpty()){
                        updateAlarm(owner,password, description);
                    }
                }catch (NumberFormatException ex){
                    Toast.makeText(getApplicationContext(), getString(R.string.message_warning), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void  initialize(String id){
        AppDatabase db = App.getInstance().getDatabase();
        LaptopDAO laptopDAO = db.LaptopDAO();
        LaptopPasswords laptop = laptopDAO.getById(id);

        laptopBuf = laptop;
        if(laptop!=null) {

            tvSerial.setText(laptop.getSerialNumber());
             tvOwner.setText(laptop.getLaptopOwnerName());
            tvModel.setText(laptop.getLaptopModel());
            catNameLap.setText(laptop.getNameCat());
            tvPasswordLap.setText(laptop.getLaptopPass());
            descriptionLap.setText(laptop.getLaptopDescription());

        }else{
            Log.d("Err","NULL");
        }
    }
    private void updateAlarm(String owner,String password, String description) {
        AppDatabase db = App.getInstance().getDatabase();
        LaptopDAO laptopDAO = db.LaptopDAO();
        LaptopPasswords laptop = laptopDAO.getById(laptopBuf.getSerialNumber());

        try {
            laptop.setSerialNumber(laptopBuf.getSerialNumber());
            laptop.setLaptopOwnerName(owner);
            laptop.setLaptopModel(laptopBuf.getLaptopModel());
            laptop.setNameCat(laptop.getNameCat());
            laptop.setLaptopPass(password);
            laptop.setLaptopDescription(description);
            laptopDAO.update(laptop);

            Toast toast = Toast.makeText(this,  getString(R.string.message_success), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0,160);
            toast.show();
            //Toast.makeText(getApplicationContext(), getString(R.string.message_success), Toast.LENGTH_SHORT).show();
            initialize(laptop.getSerialNumber());
        }catch (SQLException ex){
            Toast.makeText(getApplicationContext(), "SQLexception", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(), getString(R.string.message_fail_update), Toast.LENGTH_SHORT).show();
        }

    }

}



