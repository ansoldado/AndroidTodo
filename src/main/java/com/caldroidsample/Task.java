package com.caldroidsample;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ansoldado on 6/12/16.
 */

public class Task implements Serializable{

    private static final long serialVersionUID = -55857686305273843L;

    private Date date;
    private String title;
    private String description;
    private String state;

    public Task() {
        this.date = new Date();
        this.title = "Tarea de prueba";
        this.description = "Esta es una tarea para probar el almacen de preferencias";
        this.state = "TODO";
    }

    public Task(Date date) {
        this.date = date;
        this.title = "Tarea de prueba";
        this.description = "Esta es una tarea para probar el almacen de preferencias";
        this.state = "TODO";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (date != null ? !date.equals(task.date) : task.date != null) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null)
            return false;
        return state != null ? state.equals(task.state) : task.state == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
