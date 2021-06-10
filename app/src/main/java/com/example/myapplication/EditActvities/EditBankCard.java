package com.example.myapplication.EditActvities;



import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.DB.App;
import com.example.myapplication.DB.AppDatabase;
import com.example.myapplication.InformationSave.DateConverter;
import com.example.myapplication.ObjectsRoom.BankCardDAO;
import com.example.myapplication.ObjectsRoom.BankCardPass;
import com.example.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class EditBankCard extends AppCompatActivity {
    private TextView tvDescCard;
    private TextView tvCatNameCard;
    private TextView tvCardNum;
    private TextView tvBank;
    private TextView tvPasswordCard;
    private TextView tvDate;
    private TextView tcCVV;

    private EditText edit_bankcode_update;
    private EditText  et_bankcard_edit_chage_description;

    private BankCardPass cardBuf;

    private Button btn_comeBack_from_card;
    private Button btn_change_card;
    private Button btn_delete_card;

    private int cardId;
    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bankcard);


        tvDescCard= (TextView)findViewById(R.id.tv_bankcard_desc_edit);
        tvCatNameCard= (TextView)findViewById(R.id.tv_bankcard_edit_cat);
        tvCardNum= (TextView)findViewById(R.id.tv_bankcard_numberCard_edit);
        tvBank= (TextView)findViewById(R.id.tv_bankcard_bank_edit);
        tvPasswordCard= (TextView)findViewById(R.id.tv_cardbank_edit_password);
        tvDate = (TextView)findViewById(R.id.tv_bankcard_edit_date);
        tcCVV = (TextView)findViewById(R.id.tv_bankcard_edit_cvv);


        btn_comeBack_from_card = (Button)findViewById(R.id.btn_edit_bankcode_back);
        btn_change_card = (Button)findViewById(R.id.btn_edit_bankcode_update);
        btn_delete_card = (Button)findViewById(R.id.btn_edit_bankcard_delete);

        btn_comeBack_from_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cardId = getIntent().getIntExtra("edit_card",-1);

        try {
            initialize(cardId);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edit_bankcode_update = (EditText)findViewById(R.id.et_bankcard_edit_pass2);



        et_bankcard_edit_chage_description = (EditText)findViewById(R.id.et_bankcard_edit_chage_description);

        btn_delete_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = App.getInstance().getDatabase();
               BankCardDAO bankDAO = db.BankCardDAO();
                BankCardPass card = bankDAO.getById(cardBuf.getCardNum());
                bankDAO.delete(card);
                try {
                    initialize(cardId);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                finish();

            }
        });

        btn_change_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int password = Integer.parseInt(edit_bankcode_update.getText().toString());
                    String description = et_bankcard_edit_chage_description.getText().toString();

                    if( !description.isEmpty()){
                        updateCard(password, description,cardId);
                    }
                }catch (NumberFormatException ex){
                    Toast.makeText(getApplicationContext(), getString(R.string.message_warning), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void  initialize(int id) throws ParseException {
        AppDatabase db = App.getInstance().getDatabase();
        BankCardDAO bankDAO = db.BankCardDAO();
        BankCardPass cards = bankDAO.getById(id);
        simpleDateFormat = new SimpleDateFormat("MM-yyyy", Locale.US);
        cardBuf = cards;
        if(cards!=null) {
            tvDescCard.setText(cards.getDescription());
            tvCatNameCard.setText(cards.getCardCat());
            tvCardNum.setText(String.valueOf(cards.getCardNum()));
            tvBank.setText(cards.getCardBank());
            tvPasswordCard.setText(String.valueOf(cards.getCardPass()));
            tvDate.setText(DateConverter.dateToTimestamp(cards.getUntilDate()));
            tcCVV.setText(String.valueOf(cards.getCVV()));
        }else{
            Log.d("Err","NULL");
        }
    }


    private void updateCard(int password, String description, int id) {
        AppDatabase db = App.getInstance().getDatabase();
        BankCardDAO bankcardDAO = db.BankCardDAO();
       BankCardPass card = bankcardDAO.getById(id);

        try {
            card.setDescription(description);
            card.setCardCat(cardBuf.getCardCat());
            card.setCardNum(cardBuf.getCardNum());
            card.setCardBank(cardBuf.getCardBank());
            card.setCardPass(password);
            card.setUntilDate(DateConverter.fromTimestamp(DateConverter.dateToTimestamp(cardBuf.getUntilDate())));
            card.setCVV(cardBuf.getCVV());

            bankcardDAO.update(card);

            Toast toast = Toast.makeText(this,  getString(R.string.message_success), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0,160);
            toast.show();
            //Toast.makeText(getApplicationContext(), getString(R.string.message_success), Toast.LENGTH_SHORT).show();
            initialize(card.getCardNum());


        }catch (SQLException ex){
            Toast.makeText(getApplicationContext(), "SQLexception", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            //Toast toast = new Toast.makeText(getApplicationContext(), getString(R.string.message_fail_update), Toast.LENGTH_SHORT).show();
        }

    }

}



