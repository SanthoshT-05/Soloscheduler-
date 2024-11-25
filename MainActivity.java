package com.example.soloscheduler;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText taskInput;
    private Button addTaskButton;
    private ListView taskListView;

    private ArrayList<String> tasks; // In-memory storage for tasks
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        taskInput = findViewById(R.id.taskInput);
        addTaskButton = findViewById(R.id.addTaskButton);
        taskListView = findViewById(R.id.taskListView);

        // Initialize the task list
        tasks = new ArrayList<>();

        // Set up the adapter for the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        taskListView.setAdapter(adapter);

        // Add task button functionality
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskInput.getText().toString().trim();
                if (!task.isEmpty()) {
                    tasks.add(task); // Add the task to the list
                    adapter.notifyDataSetChanged(); // Notify adapter to refresh the ListView
                    taskInput.setText(""); // Clear input field
                    Toast.makeText(MainActivity.this, "Task added!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Remove task on ListView item click
        taskListView.setOnItemClickListener((parent, view, position, id) -> {
            tasks.remove(position); // Remove the selected task
            adapter.notifyDataSetChanged(); // Notify adapter to refresh the ListView
            Toast.makeText(MainActivity.this, "Task removed!", Toast.LENGTH_SHORT).show();
        });
    }
}


