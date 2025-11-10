package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private EditText editTextString1, editTextString2, editTextInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Связываем элементы интерфейса
        editTextString1 = findViewById(R.id.editTextString1);
        editTextString2 = findViewById(R.id.editTextString2);
        editTextInt = findViewById(R.id.editTextInt);

        // Получаем объект настроек
        preferences = getSharedPreferences(
                getString(R.string.preferences),
                MODE_PRIVATE
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Получаем ключи из ресурсов
        String strKey1 = getString(R.string.string_element_1);
        String strKey2 = getString(R.string.string_element_2);
        String intKey = getString(R.string.int_element);

        // Восстанавливаем значения
        String str1 = preferences.getString(strKey1, "");
        String str2 = preferences.getString(strKey2, "");
        int intVal = preferences.getInt(intKey, 0);

        // Помещаем в поля
        editTextString1.setText(str1);
        editTextString2.setText(str2);
        editTextInt.setText(String.valueOf(intVal));
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Получаем значения из полей
        String str1 = editTextString1.getText().toString();
        String str2 = editTextString2.getText().toString();
        int intVal = 0;
        try {
            intVal = Integer.parseInt(editTextInt.getText().toString());
        } catch (NumberFormatException e) {
            intVal = 0; // если пользователь не ввёл число
        }

        // Сохраняем данные
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getString(R.string.string_element_1), str1);
        editor.putString(getString(R.string.string_element_2), str2);
        editor.putInt(getString(R.string.int_element), intVal);
        editor.apply(); // можно использовать commit() если нужно синхронно
    }
}
