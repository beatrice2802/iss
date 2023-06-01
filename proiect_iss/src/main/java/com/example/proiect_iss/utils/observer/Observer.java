package com.example.proiect_iss.utils.observer;


import com.example.proiect_iss.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}