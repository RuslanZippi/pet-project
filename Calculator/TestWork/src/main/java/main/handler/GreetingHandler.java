package main.handler;

import main.service.Calculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@RestController
public class GreetingHandler {

    @Autowired
    private Calculate calculate;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Object> test() {

        return Flux
                .push(sink -> {
                    Flux.just("function getBaseLog(y){" +
                            "return Math.log(y) / Math.log(2);" +
                            "}").subscribe(new BaseSubscriber<String>() {
                        @Override
                        protected void hookOnNext(String value) {
                            sink.next(calculate.call(value,"3"));
                        }

                        @Override
                        protected void hookOnComplete() {
                            sink.complete();
                        }
                    });
                })
                .repeat(2);
    }
}
