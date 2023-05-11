package com.example.helloworld.resources;

import com.example.helloworld.api.Saying;
import com.example.helloworld.api.Fibonacci;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Path("/fib-sequence")
@Produces(MediaType.APPLICATION_JSON)
public class FibonacciResource {
    private final int defaultNumber;

    public FibonacciResource(int defaultNumber) {
        this.defaultNumber = defaultNumber;
    }

    @GET
    @Timed
    public Fibonacci getFibSequence(@QueryParam("num") Optional<Integer> number) throws Exception {
        final int givenValue = number.orElse(defaultNumber).intValue();
        List<Integer> list = new ArrayList<Integer>();
        if(givenValue > 0 && givenValue <=100) {
            for (int i = 0; i < givenValue; i++)
                list.add(fib(i));      
        }
        return new Fibonacci(list);
    }

    // menthod to find the fibonacci Sequence
    private int fib(int n) {
        // Declare an array to store
        // Fibonacci numbers.
        // 1 extra to handle case, n = 0
        int f[] = new int[n + 2];
 
        int i;
 
        // 0th and 1st number of
        // the series are 0 and 1
        f[0] = 0;
        f[1] = 1;
 
        for (i = 2; i <= n; i++) {
 
            // Add the previous 2 numbers
            // in the series and store it
            f[i] = f[i - 1] + f[i - 2];
        }
 
        // Nth Fibonacci Number
        return f[n];
    }     
}