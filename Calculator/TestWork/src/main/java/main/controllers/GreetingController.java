package main.controllers;

import main.service.Calculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
public class GreetingController {


    @Autowired
    private Calculate calculate;

    @GetMapping(value = "/calculator", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Object test(@RequestParam(name = "function1") String first, @RequestParam(name = "function2") String second, @RequestParam(name = "iteration") String iteration) {
        calculate.clear();

        return Flux.just(first,second)
                .parallel()
                .runOn(Schedulers.parallel())
                .flatMap(x-> Flux.just(x)
                        .map(y-> calculate.call(y))
                        .repeat(Integer.parseInt(iteration) - 1));
    }
}
