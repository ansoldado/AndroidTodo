package com.caldroidsample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DayTasks extends ListActivity {

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

        getTasks();
        initializeListeners();
    }

    private void getTasks(){
        PreferencesTaskStore taskStore = new PreferencesTaskStore(this);
        final ArrayList<Task> tasksList = taskStore.taskList(date);
        ArrayList<String> taskNames = new ArrayList<>();

        for(Task task : tasksList){
            taskNames.add(task.getTitle());
        }

        this.setListAdapter(new ArrayAdapter<String>(
                this, R.layout.task_row_item,
                R.id.TaskName,taskNames));

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String task = tasksList.get(position).getDescription();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(view.getContext(), task, duration);
                toast.show();

            }
        });
    }

    private void initializeListeners(){
        final Button backButton = (Button) findViewById(R.id.Back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CaldroidSampleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        final Button addTaskButton = (Button) findViewById(R.id.AddTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesTaskStore taskStore = new PreferencesTaskStore(view.getContext());
                Task taskToSave = new Task(date);
                taskStore.saveTask(taskToSave);
                getTasks();
            }
        });

    }
}
