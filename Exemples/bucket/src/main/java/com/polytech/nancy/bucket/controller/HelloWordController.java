package com.polytech.nancy.bucket.controller;

import com.polytech.nancy.bucket.dto.Data;
import io.github.bucket4j.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RestController
public class HelloWordController {

    //rajoute 10 tokens toutes les minutes
    Refill refill = Refill.intervally(10, Duration.ofMinutes(1));
    //capacité max de 10 token
    Bandwidth limit = Bandwidth.classic(10, refill);
    Bucket bucket = Bucket.builder().addLimit(limit).build();

    final String remainning = "X-Rate-Limit-Remaining";
    final String retryAfter = "X-Rate-Limit-Retry-After-Seconds";


    @GetMapping(value = "/hello")
    public ResponseEntity<Data> hello() {

        if(bucket.tryConsume(1)) {
           return ResponseEntity.ok(new Data("world"));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @CrossOrigin(exposedHeaders = {remainning, retryAfter})
    @GetMapping(value = "/infos")
    public ResponseEntity<Data> infos() {
        HttpHeaders headers = new HttpHeaders();
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(2);

        if(probe.isConsumed()) {
            headers.add("X-Rate-Limit-Remaining", Long.toString(probe.getRemainingTokens()));
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new Data("infos"));
        }

        long delaiEnSeconde = probe.getNanosToWaitForRefill() / 1_000_000_000;
        headers.add("X-Rate-Limit-Retry-After-Seconds",String.valueOf(delaiEnSeconde));
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .headers(headers)
                .build();
    }

}
