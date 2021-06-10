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
import com.example.myapplication.ObjectsRoom.BicycleDAO;
import com.example.myapplication.ObjectsRoom.BicyclePasswords;
import com.example.myapplication.R;


public class EditBicycle extends AppCompatActivity {
    private TextView tvOwnerName;
    private TextView tvCatName;
    private TextView tvPassword;
    private TextView description;

    private EditText et_change_offi;
    private EditText et_change_password;
    private EditText et_change_description;

    private BicyclePasswords bicBuf;

    private Button btnAllowChanges;
    private Button btnAllowChange;
    private Button btnDeleteBicycle;

    private Button btnBack;


    private LinearLayout lin;
    private boolean visibility = false;
    private String ownerNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bicycle);

        tvOwnerName = (TextView)findViewById(R.id.tv_Owner);
        tvCatName = (TextView)findViewById(R.id.tv_catNameB);
        tvPassword = (TextView)findViewById(R.id.id_passwordBic);
        description = (TextView)findViewById(R.id.tv_descriptionBicycle);


        btnAllowChanges = (Button)findViewById(R.id.btn_allow_changesBic);
        btnAllowChange = (Button)findViewById(R.id.btn_alarm_changeBicycle);
        lin = (LinearLayout)findViewById(R.id.linearLayout2B);


        btnBack =(Button)findViewById(R.id.btn_comeback_from_Bic);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnDeleteBicycle = (Button)findViewById(R.id.btn_delete_bicycle);

        ownerNAME = getIntent().getStringExtra("edit_bicycle");

        initialize(ownerNAME);

        et_change_password = (EditText)findViewById(R.id.et_change_password_Bic);
        et_change_description = (EditText)findViewById(R.id.et_change_description_Bic);


        btnDeleteBicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = App.getInstance().getDatabase();
                BicycleDAO bicycleDAO = db.BicycleDAO();
                BicyclePasswords bicyclePasswords = bicycleDAO.getByName(bicBuf.getOwnerName());
                bicycleDAO.delete(bicyclePasswords);
                initialize(ownerNAME);
                finish();

            }
        });


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

                    if( !description.isEmpty()){
                        updateAlarm(password, description);
                    }
                }catch (NumberFormatException ex){
                    Toast.makeText(getApplicationContext(), getString(R.string.message_warning), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void  initialize(String id){
        AppDatabase db = App.getInstance().getDatabase();
        BicycleDAO bicycleDAO = db.BicycleDAO();
        BicyclePasswords bicyclies = bicycleDAO.getByName(id);

        bicBuf = bicyclies;
        if(bicyclies!=null) {
            tvOwnerName.setText(bicyclies.getOwnerName());
            tvCatName.setText(bicyclies.getNameCat());
            tvPassword.setText(String.valueOf(bicyclies.getPassword()));
            description.setText(bicyclies.getDescription());
        }else{
            Log.d("Err","NULL");
        }
    }


    private void updateAlarm( int password, String description) {
        AppDatabase db = App.getInstance().getDatabase();
        BicycleDAO bicycleDAO = db.BicycleDAO();
        BicyclePasswords bicycle = bicycleDAO.getByName(bicBuf.getOwnerName());

        try {
            bicycle.setOwnerName(bicBuf.getOwnerName());
            bicycle.setNameCat(bicBuf.getNameCat());
            bicycle.setPassword(password);
            bicycle.setDescription(description);
            bicycleDAO.update(bicycle);

            Toast toast = Toast.makeText(this,  getString(R.string.message_success), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0,160);
            toast.show();
            //Toast.makeText(getApplicationContext(), getString(R.string.message_success), Toast.LENGTH_SHORT).show();
            initialize(bicycle.getOwnerName());


        }catch (SQLException ex){
            Toast.makeText(getApplicationContext(), "SQLexception", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            //Toast toast = new Toast.makeText(getApplicationContext(), getString(R.string.message_fail_update), Toast.LENGTH_SHORT).show();
        }

    }

}



