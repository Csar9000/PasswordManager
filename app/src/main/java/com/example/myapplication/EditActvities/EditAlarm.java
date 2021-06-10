package com.example.myapplication.EditActvities;

import android.content.Intent;
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
import com.example.myapplication.MainActivity;
import com.example.myapplication.ObjectsRoom.AlarmDAO;
import com.example.myapplication.ObjectsRoom.AlarmPasswords;
import com.example.myapplication.R;


public class EditAlarm extends AppCompatActivity {
    private TextView tvOfficeId;
    private TextView tvCatName;
    private TextView tvPassword;
    private TextView description;

    private EditText et_change_password;
    private EditText et_change_description;

    private AlarmPasswords alarmBuf;

    private Button btn;
    private Button btnBack;


    private Button btnAllowChanges;
    private Button btnAllowChange;
    private LinearLayout lin;
    private boolean visibility = false;
    private String officeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alarm);

        tvOfficeId = (TextView)findViewById(R.id.tv_officeID);
        tvCatName = (TextView)findViewById(R.id.tv_catNameOffice);
        tvPassword = (TextView)findViewById(R.id.tv_passwordOffice);
        description = (TextView)findViewById(R.id.tv_descriptionOffice);

        btn = (Button)findViewById(R.id.btn_delete_alarm);

        btnBack = (Button)findViewById(R.id.btn_comeback_from_alarm);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAllowChanges = (Button)findViewById(R.id.btn_allow_changesO);
        btnAllowChange = (Button)findViewById(R.id.btn_alarm_changeO);
        lin = (LinearLayout)findViewById(R.id.linearLayout2Off);


        officeID= getIntent().getStringExtra("edit_alarm");

        initialize(officeID);

        et_change_password = (EditText)findViewById(R.id.et_change_passO);
        et_change_description = (EditText)findViewById(R.id.et_change_descriptionO);


        btnAllowChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!visibility) {
                    lin.setVisibility(View.VISIBLE);
                    btnAllowChange.setVisibility(View.VISIBLE);
                            visibility = true;
                }else{lin.setVisibility(View.INVISIBLE);
                    btnAllowChange.setVisibility(View.INVISIBLE);
                        visibility = false;}
            }
        });

        btnAllowChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int password = Integer.parseInt(et_change_password.getText().toString());
                    String description = et_change_description.getText().toString();

                    if(!description.isEmpty()){
                        updateAlarm(password, description);
                    }
                }catch (NumberFormatException ex){
                    Toast.makeText(getApplicationContext(), getString(R.string.message_warning), Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = App.getInstance().getDatabase();
                AlarmDAO alarmDAO = db.AlarmDAO();
                AlarmPasswords alarm = alarmDAO.getByName(alarmBuf.getOfficeID());
                alarmDAO.delete(alarm);
                initialize(officeID);
                finish();

            }
        });
    }

    public void  initialize(String name){
        AppDatabase db = App.getInstance().getDatabase();
        AlarmDAO alarmDAO = db.AlarmDAO();
        AlarmPasswords alarm = alarmDAO.getByName(name);

        alarmBuf = alarm;
        if(alarm!=null) {
            tvOfficeId.setText(alarm.getOfficeID());
            tvCatName.setText(alarm.getNameCat());
            tvPassword.setText(String.valueOf(alarm.getPassword()));
            description.setText(alarm.getDescriptionOffice());
        }else{
            Log.d("Err","NULL");
        }
    }
    private void updateAlarm(int password, String description) {
        AppDatabase db = App.getInstance().getDatabase();
        AlarmDAO alarmDAO = db.AlarmDAO();
        AlarmPasswords alarm = alarmDAO.getByName(alarmBuf.getOfficeID());

        try {
            alarm.setOfficeID(alarmBuf.getOfficeID());
            alarm.setNameCat(alarmBuf.getNameCat());
            alarm.setPassword(password);
            alarm.setDescriptionOffice(description);
            alarmDAO.update(alarm);

            Toast toast = Toast.makeText(this,  getString(R.string.message_success), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0,160);
            toast.show();
            Toast.makeText(getApplicationContext(), getString(R.string.message_success), Toast.LENGTH_SHORT).show();
            initialize(alarm.getOfficeID());
        }catch (SQLException ex){
            Toast.makeText(getApplicationContext(), "SQLexception", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
           //Toast toast = new Toast.makeText(getApplicationContext(), getString(R.string.message_fail_update), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

    }
}



