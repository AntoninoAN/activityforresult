package com.example.todolist;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView listView;
    Button btn_new_task;
    ArrayAdapter<String> adapter;
    TextView tv_empty;
    //best practice to define the RequestCode here
    public static final int NEW_TASK_REQUEST_CODE = 1;//generally random number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        btn_new_task = findViewById(R.id.btn_add_task);
        tv_empty = findViewById(R.id.tv_empty);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        listView.setAdapter(adapter);

        btn_new_task.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,
                    SaveActivity.class);
            startActivityForResult(intent,
                    MainActivity.NEW_TASK_REQUEST_CODE
                    );
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            adapter.remove(adapter.getItem(position));
            adapter.notifyDataSetChanged();
            return true;
        });
        listView.setEmptyView(tv_empty);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //after the second activity trigger finish
        if(requestCode == MainActivity.NEW_TASK_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                //now I'm complete sure this intent comes from a trustful code
                if(data != null && data.hasExtra(SaveActivity.SAVED_VALUE_KEY)) {
                    //always check for null values in Java.
                    String task = data.getStringExtra(SaveActivity.SAVED_VALUE_KEY);
                    addValueToAdapter(task);
                }
            }
        }
    }

    public void addValueToAdapter(String value){
        if(!value.isEmpty()) {
            //check always for null values
            adapter.add(value);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
