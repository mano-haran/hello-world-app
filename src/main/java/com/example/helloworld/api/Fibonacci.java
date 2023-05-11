package com.example.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Fibonacci {
    private List<Integer> sequence;
    
    public Fibonacci() {
        // Jackson deserialization
    }

    public Fibonacci(List<Integer> sequence) {
        this.sequence = sequence;
    }

    @JsonProperty
    public int getMemberCount() {
        return sequence.size();
    }

    @JsonProperty
    public String getSequence() {
        return sequence.toString();
    }

    @JsonProperty
    public int getTotal() {
        int sum = 0;
        for (int i: sequence) {
            sum += i;
        }
        return sum;
    }   
}