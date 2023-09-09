package com.example.sqlapp;

import android.database.Cursor;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge;
    private Button buttonAdd;
    private ListView listViewData;
    private DataManager dataManager;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewData = findViewById(R.id.listViewData);

        dataManager = new DataManager(this);
        dataManager.open();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listViewData.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());

                long result = dataManager.insertData(name, age);
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    editTextName.getText().clear();
                    editTextAge.getText().clear();
                    displayData();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        displayData();
    }

    private void displayData() {
        adapter.clear();
        Cursor cursor = dataManager.getAllData();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE));
                adapter.add(name + " (" + age + " years old)");
            }
            cursor.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataManager.close();
    }
}
