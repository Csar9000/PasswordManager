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
import com.example.myapplication.ObjectsRoom.BankCardDAO;
import com.example.myapplication.ObjectsRoom.BankCardPass;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddBankCardActivity extends AppCompatActivity {

    private EditText et_desc;
    private EditText et_cardNum;
    private EditText et_cardBank;
    private EditText et_cardPass;
    private EditText et_Date;
    private EditText et_CVV;

    SimpleDateFormat simpleDateFormat;

    private Button btnAdd;
    private Button btnComeBack;

    private String inputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_add);

        et_desc = (EditText)findViewById(R.id.et_add_bankcard_desc);
        et_cardNum = (EditText)findViewById(R.id.et_add_bankcard_cardNum);
        et_cardBank = (EditText)findViewById(R.id.et_add_bankcard_bankName);
        et_cardPass = (EditText)findViewById(R.id.et_add_bankcard_password);
        et_Date = (EditText)findViewById(R.id.et_add_bankcard_DatStr);
        et_CVV = (EditText)findViewById(R.id.et_add_bankcard_CVV);

        btnAdd = (Button)findViewById(R.id.btn_bank_add);
        btnComeBack = (Button)findViewById(R.id.btn_back_from_add_bank);
        btnComeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        inputValue = getIntent().getStringExtra("add");


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

    }
    public void add(){

        simpleDateFormat = new SimpleDateFormat("MM-yyyy", Locale.US);


         AppDatabase db = App.getInstance().getDatabase();
         BankCardDAO bankCardDAO = db.BankCardDAO();

         String desc = et_desc.getText().toString();
         String cardNum = et_cardNum.getText().toString();
         String cardBank =  et_cardBank.getText().toString();
         String cardPass = et_cardPass.getText().toString();
         String Date = et_Date.getText().toString();
         String CVV = et_CVV.getText().toString();

         if(!desc.isEmpty() || !cardNum.isEmpty() || !cardBank.isEmpty()|| !cardPass.isEmpty() || !Date.isEmpty() || !CVV.isEmpty()) {
             try {

                 int cardNumber = Integer.parseInt(cardNum);
                 int pass = Integer.parseInt(cardPass);
                 int cvv = Integer.parseInt(CVV);

                 String deadlineNote = et_Date.getText().toString();
                 java.util.Date date = simpleDateFormat.parse(deadlineNote);

                 BankCardPass bankCardPass = new BankCardPass();

                 bankCardPass.setDescription(desc);
                 bankCardPass.setCardCat(inputValue);
                 bankCardPass.setCardNum(cardNumber);
                 bankCardPass.setCardBank(cardBank);
                 bankCardPass.setCardPass(pass);
                 bankCardPass.setUntilDate(date);
                 bankCardPass.setCVV(cvv);


                 bankCardDAO.insert(bankCardPass);

                 Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
             }catch (Exception ex){
                 Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                 Log.d("Error","////NumberError is " + ex.getMessage());
             }
             finish();
         }
    }
}
