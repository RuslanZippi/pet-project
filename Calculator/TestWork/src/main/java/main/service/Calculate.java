package main.service;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BaseSubscriber;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Service
@NoArgsConstructor
public class Calculate extends BaseSubscriber<Integer> {

    private int index = 0;
    private int index2 = 0;

    private String first = "";
    private String second = "";


    private Object run(String function, String param) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.eval(function);
            String functionName = getFunctionName(function);

            Invocable inv = (Invocable) engine;
            return inv.invokeFunction(functionName, param);
        } catch (ScriptException e) {
            return "Exception: " + e.toString();
        } catch (NoSuchMethodException e) {
            return "Exception: " + e.toString();
        }
    }

    private String getFunctionName(String function) {
        String s[] = function.split(" ");
        String name = s[1].replaceAll("\\(.+", "");
        return name;
    }

    public void clear() {
        index = 0;
        index2 = 0;
        first = "";
        second = "";
    }

    @SneakyThrows
    public String call(String x) {
        String function;
        if (first.equals("")) {
            first = x;
        }
        if (!first.equals("") && !first.equals(x)) {
            second = x;

        }

        if (first.equals(x)) {
            index++;
            long start = System.nanoTime();
            if (run(x, String.valueOf(index)).toString().contains("Exception")) {
                return run(x, String.valueOf(index)).toString();
            }
            function = run(x, String.valueOf(index)).toString();
            Thread.sleep(5 * 100);
            String time = String.valueOf(System.nanoTime() - start);
            return "Номер функции: #1" + ", Итерация: " + index + ", Время расчета " + time + ", Результат: " + function;
        } else {
            index2++;
            long start = System.nanoTime();
            if (run(x, String.valueOf(index2)).toString().contains("Exception")) {
                return run(x, String.valueOf(index2)).toString();
            }
            function = run(x, String.valueOf(index2)).toString();
            Thread.sleep(10 * 100);
            String time = String.valueOf(System.nanoTime() - start);
            return "Номер функции: #2" + ", Итерация: " + index2 + ", Время расчета " + time + ", Результат: " + function;
        }

    }
}
