package com.example.sumplescoreseet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btn_add,btn_viewAll;
    EditText et_name,et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;


    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
    ArrayAdapter customerArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_viewadd);
        btn_viewAll = findViewById(R.id.btn_veiwall);
        sw_activeCustomer = findViewById(R.id.sw_active);
        lv_customerList = findViewById(R.id.lv_customerList);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        final DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);


        ShowCustomersOnListView(databaseHelper);

        //button listeners for the add and view all
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                try{
                    customerModel = new CustomerModel(-1,et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()),sw_activeCustomer.checkInputConnectionProxy(v));
                    Toast.makeText(MainActivity.this,customerModel.toString(), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(MainActivity.this,"err",Toast.LENGTH_LONG).show();
                    customerModel = new CustomerModel(-1,"err",0,false);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                boolean success = databaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "success"+success , Toast.LENGTH_SHORT).show();
                ShowCustomersOnListView(databaseHelper);
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                //List<CustomerModel> everyone = databaseHelper.getEveryone();
                ShowCustomersOnListView(databaseHelper);

            }
        });

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickdCustomer = (CustomerModel) parent.getItemAtPosition(position);
                databaseHelper.deleteOne(clickdCustomer);
                ShowCustomersOnListView(databaseHelper);
            }
        });


    }

    private void ShowCustomersOnListView(DatabaseHelper databaseHelper) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, this.databaseHelper.getEveryone());
        lv_customerList.setAdapter(customerArrayAdapter);
    }


}