package com.okta.taxcalculator.implementation;

import java.util.*;

public abstract class AbstractMapService<T,ID> {

    protected Map<ID,T> map = new HashMap<>();

    T save(ID id, T object){
        map.put(id, object);
        return object;
    }

    List<T> findAll(){
        return new ArrayList<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    void update(ID id, T object){
      //  map.entrySet().removeIf(entry -> entry.getValue().equals(object));
        map.put(id, object);
    }
}
