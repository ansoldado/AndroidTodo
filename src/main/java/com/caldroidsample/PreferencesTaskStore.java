package com.caldroidsample;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ansoldado on 6/12/16.
 */

public class PreferencesTaskStore implements TaskStore{

    private static String PREFERENCES = "puntuaciones";
    private Context context;

    public PreferencesTaskStore(Context context){
        this.context = context;
    }

    @Override
    public void saveTask(Task taskToSave) {
        SharedPreferences sharedPreferences = context.getSharedPreferences( PREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        int taskNumber = sharedPreferences.getInt("taskNumber", 0);

        taskNumber++;
        editor.putString("Task"+taskNumber, SerializationUtil.serialize(taskToSave));
        editor.putInt("taskNumber", taskNumber);
        Log.e("Guardando la tarea", ""+taskNumber);
        editor.commit();
    }

    @Override
    public ArrayList<Task> taskList(Date date) {
        String auxTaskString = null;
        Task auxTask = null;
        ArrayList<Task> taskList = new ArrayList<>();

        SharedPreferences sharedPreferences = context.getSharedPreferences( PREFERENCES, Context.MODE_PRIVATE);

        int taskNumber = sharedPreferences.getInt("taskNumber", 0);

        for(int i = 1; i <= taskNumber; i++) {
            auxTaskString = sharedPreferences.getString("Task"+i, "");
            auxTask = SerializationUtil.deserialize(auxTaskString);
            Log.e("Comparando las fechas", auxTask.getDate().toString() + " con " + date.toString());
            if(auxTask.getDate().compareTo(date) == 0) {
                taskList.add(auxTask);
                Log.e("Recuperada la tarea", auxTask.getTitle());
            }
        }
        return taskList;
    }
}
