package com.example.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SaveActivity extends AppCompatActivity {

    Button btn_save;
    EditText et_save_task;
    //best practice, define your key here
    public static final String SAVED_VALUE_KEY = "SaveValue";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        et_save_task = findViewById(R.id.et_new_task);
        btn_save = findViewById(R.id.btn_save_task);
        Intent intent = getIntent();
        btn_save.setOnClickListener(view -> {
            intent.putExtra(SAVED_VALUE_KEY, et_save_task.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        });
    }
}
