package com.coderprabhu.api.counter;

import org.springframework.stereotype.Component;

/**
 * Counter
 */
@Component
public class Counter {
    private int count = 0;
    public void increament(){
        count++;
    };
    public int getCount(){
        return count;
    }
}