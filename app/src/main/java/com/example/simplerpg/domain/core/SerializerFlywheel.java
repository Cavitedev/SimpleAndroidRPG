package com.example.simplerpg.domain.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SerializerFlywheel<T extends IIdentifieable<I>,I> {


     Map<I,T> cache;

     public SerializerFlywheel(String json) {
          Type type = new TypeToken<List<T>>(){}.getType();
          ArrayList<T> objects =  new Gson().fromJson(json, type);
          cache = new HashMap<>();
          for(int i = 0; i<objects.size(); i++){
               cache.put(objects.get(i).getId(), objects.get(i));
          }
     }


     public String toJson(List<T> domainObject){
          return new Gson().toJson(cache.values());
     }

     public T getObject(I identifier){
          return cache.get(identifier);
     }

}
