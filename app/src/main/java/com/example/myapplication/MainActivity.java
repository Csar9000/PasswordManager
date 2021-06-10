package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AddActivities.AddAlarmOrBicycleActivity;
import com.example.myapplication.AddActivities.AddBankCardActivity;
import com.example.myapplication.AddActivities.AddLaptopActivity;
import com.example.myapplication.DB.App;
import com.example.myapplication.DB.AppDatabase;
import com.example.myapplication.EditActvities.EditPassword;
import com.example.myapplication.ObjectsRoom.AlarmDAO;
import com.example.myapplication.ObjectsRoom.AlarmPasswords;
import com.example.myapplication.ObjectsRoom.BankCardDAO;
import com.example.myapplication.ObjectsRoom.BankCardPass;
import com.example.myapplication.ObjectsRoom.BicycleDAO;
import com.example.myapplication.ObjectsRoom.BicyclePasswords;
import com.example.myapplication.ObjectsRoom.Category;
import com.example.myapplication.ObjectsRoom.CategoryDAO;
import com.example.myapplication.ObjectsRoom.LaptopDAO;
import com.example.myapplication.ObjectsRoom.LaptopPasswords;
import com.example.myapplication.RecyclerViewAdapters.AlarmRecyclerAdapter;
import com.example.myapplication.RecyclerViewAdapters.BankCardRecyclerAdapter;
import com.example.myapplication.RecyclerViewAdapters.BicycleRecyclerAdapter;
import com.example.myapplication.RecyclerViewAdapters.LaptopRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private FloatingActionButton btn;
    private AlarmRecyclerAdapter alarmRecyclerAdapter;
    private BicycleRecyclerAdapter bicycleRecyclerAdapter;
    private LaptopRecyclerAdapter laptopRecyclerAdapter;
    private BankCardRecyclerAdapter bankCardRecyclerAdapter;

    private ArrayAdapter<String> adapter2;

    private List<String> headingsAlarm = Collections.singletonList("Описание кабинета");
    private List<String> headingsBicycle = Collections.singletonList("Владелец велосипеда");
    private List<String> headingsLaptop = Collections.singletonList("Описание ноутбука");
    private List<String> headingsCards = Collections.singletonList("Описание");

    private Button btn_search;

    private EditText et_toSearch;

    private Spinner spinner;

    private String item;
    private String item2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        et_toSearch = (EditText)findViewById(R.id.et_Search);

        btn = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        AppDatabase db = App.getInstance().getDatabase();
        CategoryDAO categoryDAO = db.CatDao();

        btn_search=(Button)findViewById(R.id.btn_Search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = App.getInstance().getDatabase();

                List<LaptopPasswords> listLap;
                List<BicyclePasswords> listBic;
                List<AlarmPasswords> listAll;
                List<BankCardPass> bankList;

                AlarmDAO alarmDAO = db.AlarmDAO();
                BicycleDAO bicycleDAO = db.BicycleDAO();
                LaptopDAO laptopDAO = db.LaptopDAO();
                BankCardDAO bankCardDAO = db.BankCardDAO();


                switch (item2){
                    case "Описание кабинета":{
                        recyclerView.setAdapter(null);
                        List<AlarmPasswords> result = null;
                        result = new ArrayList<AlarmPasswords>();

                        listAll = alarmDAO.getAll();

                        for (AlarmPasswords s:listAll) {
                            if(s.descriptionOffice.contains(et_toSearch.getText())){
                                result.add(s);
                            }
                        }
                        if(result.size()!=0){
                            alarmRecyclerAdapter = new AlarmRecyclerAdapter(getApplicationContext(), (List<AlarmPasswords>) result);
                            recyclerView.setAdapter(alarmRecyclerAdapter);
                            alarmRecyclerAdapter.notifyDataSetChanged();
                        }
                        break;
                    }
                    case "Владелец велосипеда":{
                        recyclerView.setAdapter(null);
                        List<BicyclePasswords> result = null;
                        result = new ArrayList<BicyclePasswords>();

                        listBic =bicycleDAO.getAll();

                        for (BicyclePasswords s:listBic) {
                            if(s.getDescription().contains(et_toSearch.getText())){
                                result.add(s);
                            }
                        }
                        if(result.size()!=0) {
                            bicycleRecyclerAdapter = new BicycleRecyclerAdapter(getApplicationContext(), result);
                            recyclerView.setAdapter(bicycleRecyclerAdapter);
                            bicycleRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                        break;

                    case "Описание ноутбука":{
                        recyclerView.setAdapter(null);
                        List<LaptopPasswords> result = null;
                        result = new ArrayList<LaptopPasswords>();

                        listLap =laptopDAO.getAll();

                        for (LaptopPasswords s:listLap) {
                            if(s.laptopDescription.contains(et_toSearch.getText())){
                                result.add(s);
                            }
                        }
                        if(result.size()!=0) {
                            laptopRecyclerAdapter  = new LaptopRecyclerAdapter(getApplicationContext(), result);
                            recyclerView.setAdapter(laptopRecyclerAdapter);
                            laptopRecyclerAdapter.notifyDataSetChanged();
                        }
                        }
                        break;
                    case "Описание":{
                        recyclerView.setAdapter(null);
                        List<BankCardPass> result = null;
                        result = new ArrayList<BankCardPass>();

                        bankList =bankCardDAO.getAll();

                        for (BankCardPass s:bankList) {
                            if(s.getDescription().contains(et_toSearch.getText())){
                                result.add(s);
                            }
                        }
                        if(result.size()!=0) {
                            bankCardRecyclerAdapter  = new BankCardRecyclerAdapter(getApplicationContext(), bankList);
                            recyclerView.setAdapter(bankCardRecyclerAdapter);
                            bankCardRecyclerAdapter.notifyDataSetChanged();
                        }else{Log.d("#","/////////////");}
                    }
                    break;
                    }
                }
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        super.onPause();
        update();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        update();
    }

    @Override
    protected void onStart() {
        super.onStart();
        update();
    }

    public void init(){

        AppDatabase db = App.getInstance().getDatabase();
        try {
            CategoryDAO categoryDAO = db.CatDao();
            categoryDAO.insert(new Category("Laptop", "Small description of Category"));
            categoryDAO.insert(new Category("Bicycle", "Small description of Category"));
            categoryDAO.insert(new Category("Alarm", "Small description of Category"));
            categoryDAO.insert(new Category("BankCard", "Small description of Category"));
        }catch(Exception ex){

        }
    }

    public void add(){
        String s = spinner.getSelectedItem().toString();
        switch (s){
            case "Bicycle":
            case "Alarm":
                Intent intent = new Intent(this, AddAlarmOrBicycleActivity.class);
                intent.putExtra("add", spinner.getSelectedItem().toString());
                this.startActivity(intent);
                break;
            case "BankCard":
                Intent intent1 = new Intent(this, AddBankCardActivity.class);
                intent1.putExtra("add", spinner.getSelectedItem().toString());
                this.startActivity(intent1);
                break;
            case "Laptop":
                Intent intent3 = new Intent(this, AddLaptopActivity.class);
                intent3.putExtra("add", spinner.getSelectedItem().toString());
                this.startActivity(intent3);
                break;
        }
    }

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 item = (String)parent.getItemAtPosition(position);
                 update();

                switch (item){
                    case "Alarm":{
                        item2 = "Описание кабинета";
                        break;
                    }
                    case "Bicycle":{
                        item2 = "Владелец велосипеда";
                        break;
                    }
                    case "Laptop":{
                        item2 = "Описание ноутбука";
                        break;
                    }
                    case "BankCard":{
                        item2 = "Описание";
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

    public void update(){
        recyclerView.setAdapter(null);

        AppDatabase db = App.getInstance().getDatabase();
        AlarmDAO alarmDAO = db.AlarmDAO();
        BicycleDAO bicycleDAO = db.BicycleDAO();
        LaptopDAO laptopDAO = db.LaptopDAO();
        BankCardDAO bankCardDAO = db.BankCardDAO();


        List<AlarmPasswords> alarmPasswords = alarmDAO.getNameCat(item);
        List<BicyclePasswords> bicyclePasswords = bicycleDAO.getNameCat(item);
        List<LaptopPasswords> laptopPasswords = laptopDAO.getNameCat(item);
        List<BankCardPass> bankCards = bankCardDAO.getNameCat(item);


        if(alarmPasswords.size()!=0){
            alarmRecyclerAdapter = new AlarmRecyclerAdapter(getApplicationContext(), alarmPasswords);
            recyclerView.setAdapter(alarmRecyclerAdapter);
            alarmRecyclerAdapter.notifyDataSetChanged();
        }
        if(bicyclePasswords.size()!=0){
            bicycleRecyclerAdapter = new BicycleRecyclerAdapter(getApplicationContext(), bicyclePasswords);
            recyclerView.setAdapter(bicycleRecyclerAdapter);
            bicycleRecyclerAdapter.notifyDataSetChanged();
        }
        if(laptopPasswords.size()!=0){
            laptopRecyclerAdapter  = new LaptopRecyclerAdapter(getApplicationContext(), laptopPasswords);
            recyclerView.setAdapter(laptopRecyclerAdapter);
            laptopRecyclerAdapter.notifyDataSetChanged();
        }
        if(bankCards.size()!=0){
            bankCardRecyclerAdapter  = new BankCardRecyclerAdapter(getApplicationContext(), bankCards);
            recyclerView.setAdapter(bankCardRecyclerAdapter);
            bankCardRecyclerAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Button btn;



        spinner = (Spinner) MenuItemCompat.getActionView(item);

        AppDatabase db = App.getInstance().getDatabase();
        CategoryDAO categoryDAO = db.CatDao();

        List<String> s =categoryDAO.getAllCategories();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(itemSelectedListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item2) {
        if (R.id.settings == item2.getItemId()) {
            startActivity(new Intent(MainActivity.this, EditPassword.class));
        }
        return super.onOptionsItemSelected(item2);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        update();
    }
}

