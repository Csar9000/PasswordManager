package com.example.myapplication.EditActvities;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.DB.App;
import com.example.myapplication.InformationSave.KeyStore;
import com.example.myapplication.R;

public class EditPassword extends AppCompatActivity {
    private EditText etPin;
    private Button btnSave;
    private KeyStore keyStore;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init() {
        keyStore = App.getKeystore();

        etPin = findViewById(R.id.et_new_pass);
        btnSave = findViewById(R.id.btn_cahgepass);
        Toolbar toolBar = findViewById(R.id.toolbar2);
        toolBar.setTitle(getString(R.string.settings_Title));
        setSupportActionBar(toolBar);

        back= (Button)findViewById(R.id.btn_backkk);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 6) {
                    etPin.setText(etPin.getText().subSequence(0, etPin.getText().length() - 1));
                    Toast.makeText(EditPassword.this, getString(R.string.msg_pin_length_err), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredPin = etPin.getText().toString();
                if (enteredPin.length()!=6) {
                    Toast.makeText(EditPassword.this, getString(R.string.msg_pin_length_err), Toast.LENGTH_SHORT).show();
                    etPin.setText("");
                } else {
                    keyStore.save(enteredPin);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (App.getKeystore().hasPin()) {
            super.onBackPressed();
        }
    }
}

