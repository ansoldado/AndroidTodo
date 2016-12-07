package com.caldroidsample;

        import java.util.ArrayList;
        import java.util.Date;

/**
 * Created by ansoldado on 6/12/16.
 */

public interface TaskStore {
    public void saveTask(Task taskToSave);
    public ArrayList<Task> taskList(Date date);
}
