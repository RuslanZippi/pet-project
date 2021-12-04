package main.service;

import org.springframework.stereotype.Service;

@Service
public class Calculate {

    public String calculate(String value, String iValue) {
        value = value.replace(",",".");
        if(iValue.isEmpty()){
            iValue = "0";
        }

        iValue = iValue.replace(",",".");
        float intValue = Float.parseFloat(value);
        float intIValue = Float.parseFloat(iValue);

        float result = intIValue * intValue;
        return String.valueOf("<" + result + ">");
    }
}
