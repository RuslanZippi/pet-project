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

    private String check = "";
    private String check2 = "";

    private int repeatIndex = 1;
    private String repeat = "";


    public  Object run(String function, String param) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.eval(function);
            String functionName = getFunctionName(function);

            Invocable inv = (Invocable) engine;
            return inv.invokeFunction(functionName, param);
        } catch (ScriptException e) {
            return e.toString();
        } catch (NoSuchMethodException e) {
            return e.toString();
        }
    }

    private  String getFunctionName(String function){
        String s[] = function.split(" ");
        String name = s[1].replaceAll("\\(.+","");
        return name;
    }

    @SneakyThrows
    public String call(String x,String param) {
        String answer = "";

        if (check.equals("")) {
            check = x;
            index++;
            long start = System.nanoTime();
            String result = x + 1;
            String function = run(x,param).toString();
            Thread.sleep(1000);
            String time = String.valueOf(System.nanoTime() - start);
            return "Номер функции: " + x + ", Итерация: " + index + ", Время расчета " + time + ", Результат: " + function;
        }
        if (check.equals(x)) {
            index++;
            long start = System.nanoTime();
            String result = x + 1;
            String function = run(x,param).toString();
            Thread.sleep(1000);
            String time = String.valueOf(System.nanoTime() - start);
            return "Номер функции: " + x + ", Итерация: " + index + ", Время расчета " + time + ", Результат: " + function;
        } else {
            check2 = x;
            index2++;
            long start = System.nanoTime();
            String result = x + 1;
            String function = run(x,param).toString();
            Thread.sleep(1000);
            String time = String.valueOf(System.nanoTime() - start);
            return "Номер функции: " + x + ", Итерация: " + index2 + ", Время расчета " + time + ", Результат: " + function;
        }
    }
}
