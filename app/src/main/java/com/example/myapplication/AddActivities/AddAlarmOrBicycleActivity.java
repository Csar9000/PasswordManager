package com.example.myapplication.AddActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.DB.App;
import com.example.myapplication.DB.AppDatabase;
import com.example.myapplication.ObjectsRoom.AlarmDAO;
import com.example.myapplication.ObjectsRoom.AlarmPasswords;
import com.example.myapplication.ObjectsRoom.BicycleDAO;
import com.example.myapplication.ObjectsRoom.BicyclePasswords;
import com.example.myapplication.R;

public class AddAlarmOrBicycleActivity extends AppCompatActivity {
    //private Toolbar toolbar;

    private TextView tv_first;
   // private TextView tv_second;
    private TextView tv_third;
    private TextView tv_desc;

    private EditText et_add_first;
    //private Spinner sp_add_catName;
    private EditText et_add_password;
    private EditText et_add_description;

    private Button btnComaBack;

    private Button btnAdd;
    private Button btnComeBack;

    private String inputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        tv_first = (TextView)findViewById(R.id.id_first);
        //tv_second = (TextView)findViewById(R.id.tv_second);
        tv_third = (TextView)findViewById(R.id.tv_pass);
        tv_desc = (TextView)findViewById(R.id.tv_desc);

        et_add_first = (EditText)findViewById(R.id.et_first);
      //  sp_add_catName = (Spinner) findViewById(R.id.spinner);
        et_add_password = (EditText)findViewById(R.id.et_pass);
        et_add_description = (EditText)findViewById(R.id.et_desc);





        btnAdd = (Button)findViewById(R.id.add);
        btnComeBack = (Button)findViewById(R.id.comeback);

        btnComeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        inputValue = getIntent().getStringExtra("add");
        switch (inputValue){
            case "Bicycle":{
                tv_first.setText(getString(R.string.tv_owner_bicycle_add));
                tv_third.setText(getString(R.string.tv_password_bicycle_add));
                tv_desc.setText(R.string.tv_description_bicycle_add);

                break;
            }

            case "Alarm":{
                tv_first.setText(getString(R.string.tv_owner_alarm_add));
                tv_third.setText(getString(R.string.tv_password_alarm_add));
                tv_desc.setText(getString(R.string.tv_description_alarm_add));
                break;
            }
            default:
                break;
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(inputValue);
            }
        });

    }

    public void add(String inputValue){
        switch (inputValue){
            case "Bicycle":{
                AppDatabase db = App.getInstance().getDatabase();
                BicycleDAO bicycleDAO = db.BicycleDAO();

                String bicycleOwner = et_add_first.getText().toString();
                String bicyclePass = et_add_password.getText().toString();
                String bicycleDesc = et_add_description.getText().toString();

                if(!bicycleOwner.isEmpty() || !bicyclePass.isEmpty()||!bicycleDesc.isEmpty()) {
                    try {
                        int pass = Integer.parseInt(bicyclePass);
                        BicyclePasswords bicyclePasswords = new BicyclePasswords();
                        bicyclePasswords.setOwnerName(bicycleOwner);
                        bicyclePasswords.setNameCat(inputValue);
                        bicyclePasswords.setPassword(pass);
                        bicyclePasswords.setDescription(bicycleDesc);

                        bicycleDAO.insert(bicyclePasswords);

                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    }catch (Exception ex){
                        Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
                break;
            }

            case "Alarm":{
                AppDatabase db1 = App.getInstance().getDatabase();
                AlarmDAO alarmDAO = db1.AlarmDAO();


                String bicycleOwner = et_add_first.getText().toString();
                String bicyclePass = et_add_password.getText().toString();
                String bicycleDesc = et_add_description.getText().toString();

                if(!bicycleOwner.isEmpty() || !bicyclePass.isEmpty()||!bicycleDesc.isEmpty()) {
                    try {
                        int pass = Integer.parseInt(bicyclePass);
                        AlarmPasswords alarmPasswords = new AlarmPasswords();
                        alarmPasswords.setOfficeID(bicycleOwner);
                        alarmPasswords.setNameCat(inputValue);
                        alarmPasswords.setPassword(pass);
                        alarmPasswords.setDescriptionOffice(bicycleDesc);

                        alarmDAO.insert(alarmPasswords);

                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    }catch (Exception ex){
                        Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
                break;
            }
            default:
                break;
        }
    }
}
