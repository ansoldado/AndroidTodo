package com.caldroidsample;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DayTasks extends Activity {

    Date date = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        setContentView(R.layout.activity_day_tasks);

        String dateString = getIntent().getStringExtra("SELECTED_DATE");
        date = new Date(dateString) ;
        String formatDate = formatter.format(date);

        TextView dayString = (TextView) findViewById(R.id.DayString);
        dayString.setText(formatDate);
        initializeListeners();
    }

    private void initializeListeners(){
        final Button backButton = (Button) findViewById(R.id.Back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CaldroidSampleActivity.class);
                startActivity(intent);
            }
        });

        final Button addTaskButton = (Button) findViewById(R.id.AddTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesTaskStore taskStore = new PreferencesTaskStore(view.getContext());
                Task taskToSave = new Task(date);
                taskStore.saveTask(taskToSave);
            }
        });

        final Button removeTaskButton = (Button) findViewById(R.id.RemoveTask);
        removeTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesTaskStore taskStore = new PreferencesTaskStore(view.getContext());
                ArrayList<Task> tasksList = taskStore.taskList(date);
            }
        });
    }
}
