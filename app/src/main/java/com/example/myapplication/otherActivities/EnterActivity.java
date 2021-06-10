package com.example.myapplication.otherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.DB.App;
import com.example.myapplication.EditActvities.EditPassword;
import com.example.myapplication.InformationSave.KeyStore;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class EnterActivity extends AppCompatActivity{
        private KeyStore keyStore;
        private Button clearButton;
        private Button backButton;
        private String enteredPin;
        private View[] placeholder = new View[6];
        private int PHindex = 0;

        private Button btn_forgot_pass;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_authorization);
            keyStore = App.getKeystore();
        }

    @Override
        protected void onResume() {
            super.onResume();
            if (keyStore.hasPin()) {
                init();
            } else {
               keyStore.save("");
            }
        }

        private void init() {

            enteredPin = "";
            backButton = findViewById(R.id.btnBack);
            clearButton = findViewById(R.id.btnClear);
            btn_forgot_pass= (Button)findViewById(R.id.btn_createNewPass);

            btn_forgot_pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =  new Intent(EnterActivity.this,EditPassword.class);
                    startActivity(intent);
                }
            });

            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 6; i++) {
                        deleteNumb();
                    }
                }
            });

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteNumb();
                }
            });

            placeholder[0] = findViewById(R.id.view);
            placeholder[1] = findViewById(R.id.view2);
            placeholder[2] = findViewById(R.id.view3);
            placeholder[3] = findViewById(R.id.view4);
            placeholder[4] = findViewById(R.id.view5);
            placeholder[5] = findViewById(R.id.view6);


        }

        public void putNumb(View numbButton) {
            if (enteredPin.length() < 6) {
                setPlaceholder(true);
                String numb = String.valueOf(((Button) numbButton).getText());
                enteredPin += numb;
                PHindex++;
            }
            checkPin();
        }

        private void deleteNumb() {
            if (enteredPin.length() > 0) {
                enteredPin = enteredPin.substring(0, enteredPin.length() - 1);
                PHindex--;
                setPlaceholder(false);
            }
        }

        private void checkPin() {
            if (enteredPin.length() == 6) {
                if (keyStore.checkPin(enteredPin)) {
                    Intent intent = new Intent(EnterActivity.this, MainActivity.class);
                    startActivity(intent);
                    updatePlcHolders();
                } else {
                    Toast.makeText(this, "Неправильный пароль!", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < 6; i++) {
                        deleteNumb();
                    }
                }
            }
        }

        public void updatePlcHolders(){
            enteredPin="";
            for(int i = 0;i<6;i++){
                placeholder[i].setBackground(getResources().getDrawable(R.drawable.pin_placeholder_empty));
            }
            PHindex =0;
        }

        private void setPlaceholder(boolean filled) {
            if (filled) {
                placeholder[PHindex].setBackground(getResources().getDrawable(R.drawable.pin_placeholder_filled));
            } else {
                placeholder[PHindex].setBackground(getResources().getDrawable(R.drawable.pin_placeholder_empty));
            }
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            finishAffinity();
        }
}
