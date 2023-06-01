package com.example.proiect_iss.utils.events;

import com.example.proiect_iss.domain.Book;

public class BookEntityChangeEvent implements Event {
    private ChangeEventType type;
    private Book data, oldData;

    public BookEntityChangeEvent(ChangeEventType type, Book data) {
        this.type = type;
        this.data = data;
    }
    public BookEntityChangeEvent(ChangeEventType type, Book data, Book oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Book getData() {
        return data;
    }

    public Book getOldData() {
        return oldData;
    }
}
