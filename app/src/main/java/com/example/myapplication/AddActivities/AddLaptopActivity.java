package com.example.myapplication.AddActivities;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.DB.App;
import com.example.myapplication.DB.AppDatabase;
import com.example.myapplication.ObjectsRoom.LaptopDAO;
import com.example.myapplication.ObjectsRoom.LaptopPasswords;
import com.example.myapplication.R;

public class AddLaptopActivity extends AppCompatActivity {

    private EditText et_Serial;
    private EditText et_LaptopOwner;
    private EditText et_model;
    private EditText et_add_password;
    private EditText et_add_description;

    private Button btnAddL;
    private Button btnComeBack;

    private String inputValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laptop);

        et_Serial =(EditText)findViewById(R.id.et_input_serialNum_laptop);
        et_LaptopOwner=(EditText)findViewById(R.id.et_input_ownerName_laptop);
        et_model = (EditText)findViewById(R.id.et_input_laptopModel_laptop);
        et_add_password = (EditText)findViewById(R.id.et_input_password_laptop);
        et_add_description = (EditText)findViewById(R.id.et_input_description_laptop);

        btnAddL = (Button)findViewById(R.id.btn_add_laptop_laptopAdd);
        btnComeBack =(Button)findViewById(R.id.btn_comeback_from_add_laptop);

        inputValue = getIntent().getStringExtra("add");

        btnComeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnAddL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }
    public void add(){

        AppDatabase db = App.getInstance().getDatabase();
        LaptopDAO laptopDAO = db.LaptopDAO();

        String Serial = et_Serial.getText().toString();
        String Owner = et_LaptopOwner.getText().toString();
        String model =  et_model.getText().toString();
        String pass = et_add_password.getText().toString();
        String description = et_add_description.getText().toString();


        if(!Serial.isEmpty() || !Owner.isEmpty() || !model.isEmpty()|| !pass.isEmpty() || !description.isEmpty() ) {
            try {
                LaptopPasswords laptops = new LaptopPasswords();

                laptops.setSerialNumber(Serial);
                laptops.setNameCat(inputValue);
                laptops.setLaptopModel(model);
                laptops.setLaptopOwnerName(Owner);
                laptops.setLaptopPass(pass);
                laptops.setLaptopDescription(description);

                laptopDAO.insert(laptops);

                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                Log.d("Error","////NumberError is " + ex.getMessage());
            }
        }
        finish();
    }

}
