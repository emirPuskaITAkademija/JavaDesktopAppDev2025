package com.itakademija.four;

public class Kutija<E, T extends Number>{

    private E element;
    private T width;

    public void setElement(E element) {
        this.element = element;
    }


    public void setWidth(T width) {
        this.width = width;
    }
}
