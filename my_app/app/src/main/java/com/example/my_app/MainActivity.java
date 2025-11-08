package com.example.my_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my_app.R;
import com.example.my_app.Second;
import com.example.my_app.Third;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSecondActivity(View view){
        startActivity(new Intent(this, Second.class));
    }

    public void openThirdActivity(View view){
        startActivity(new Intent(this, Third.class));
    }
}
