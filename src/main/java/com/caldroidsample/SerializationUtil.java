package com.caldroidsample;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by ansoldado on 6/12/16.
 */

public class SerializationUtil {
    /**
     * deserialize to Object from given file. We use the general Object so as
     * that it can work for any Java Class.
     */

    @SuppressWarnings("unchecked")
    public static Task deserialize(String string) {
        byte[] bytes = Base64.decode(string.getBytes(), 0);
        Task deserializedTask = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream( new ByteArrayInputStream(bytes) );
            deserializedTask = (Task)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return deserializedTask;
    }

    /**
     * serialize the given object and save it to given file
     */
    public static String serialize(Task obj){
        String serializedObj = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(obj);
            so.close();
            serializedObj = new String(Base64.encode(bo.toByteArray(), 0));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return serializedObj;
    }
}

