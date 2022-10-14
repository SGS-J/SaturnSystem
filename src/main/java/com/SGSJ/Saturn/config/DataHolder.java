package com.SGSJ.Saturn.config;

/*
 * This class wraps temporal data through the views
 *
 * Esta clase almacena datos temporales para las vistas
 *
 * USES SINGLETON PATTERN
 */
public final class DataHolder<T> {
    private T object;
    private final static DataHolder INSTANCE = new DataHolder<>();

    private DataHolder(){
    }

    public static DataHolder getInstance() {
        return INSTANCE;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
