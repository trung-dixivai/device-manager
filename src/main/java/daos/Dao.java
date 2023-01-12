package daos;

import models.Notification;
import models.Status;

import java.util.ArrayList;
import java.util.Vector;

public interface Dao<T> {

    public Notification<T> create(T data);
    public T findOne(int id);
    public Vector<T> findAll();
    public Notification<T> delete(int id);
    public Notification<T> update(int id, T data);



}
