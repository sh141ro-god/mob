package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private EditText editText;
    private ListView listView1, listView2;
    private ArrayList<String> list1, list2;
    private ArrayAdapter<String> adapter1, adapter2;

    private int selectedIndex1 = -1;
    private int selectedIndex2 = -1;
    private boolean list1Selected = false; // флаг, какой список выбран

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText = findViewById(R.id.editTextItem);
        listView1 = findViewById(R.id.listView1);
        listView2 = findViewById(R.id.listView2);
        Button btnAdd1 = findViewById(R.id.btnAddList1);
        Button btnAdd2 = findViewById(R.id.btnAddList2);
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnBack = findViewById(R.id.btnBack);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, list1);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, list2);

        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);

        // Добавление в список 1
        btnAdd1.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();
            if (!text.isEmpty()) {
                list1.add(text);
                adapter1.notifyDataSetChanged();
                editText.setText("");
            }
        });

        // Добавление в список 2
        btnAdd2.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();
            if (!text.isEmpty()) {
                list2.add(text);
                adapter2.notifyDataSetChanged();
                editText.setText("");
            }
        });

        // Выбор элемента в ListView 1
        listView1.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex1 = position;
            selectedIndex2 = -1;
            list1Selected = true;
            listView1.setItemChecked(position, true);
            listView2.clearChoices();
            editText.setText(list1.get(position));
        });

        // Выбор элемента в ListView 2
        listView2.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex2 = position;
            selectedIndex1 = -1;
            list1Selected = false;
            listView2.setItemChecked(position, true);
            listView1.clearChoices();
            editText.setText(list2.get(position));
        });

        // Редактирование выбранного элемента
        btnEdit.setOnClickListener(v -> {
            String newText = editText.getText().toString().trim();
            if (list1Selected && selectedIndex1 >= 0 && !newText.isEmpty()) {
                list1.set(selectedIndex1, newText);
                adapter1.notifyDataSetChanged();
            } else if (!list1Selected && selectedIndex2 >= 0 && !newText.isEmpty()) {
                list2.set(selectedIndex2, newText);
                adapter2.notifyDataSetChanged();
            }
        });

        // Удаление выбранного элемента
        btnDelete.setOnClickListener(v -> {
            if (list1Selected && selectedIndex1 >= 0) {
                list1.remove(selectedIndex1);
                adapter1.notifyDataSetChanged();
                selectedIndex1 = -1;
            } else if (!list1Selected && selectedIndex2 >= 0) {
                list2.remove(selectedIndex2);
                adapter2.notifyDataSetChanged();
                selectedIndex2 = -1;
            }
            editText.setText("");
        });

        // Кнопка Back
        btnBack.setOnClickListener(v -> onBackPressed());
    }
}
