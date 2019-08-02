package com.javarush.task.task26.task2613;

import java.util.*;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {}

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        currencyCode = currencyCode.toUpperCase();
        if (map.containsKey(currencyCode)) return map.get(currencyCode);
        else{
            CurrencyManipulator currencyManipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, currencyManipulator);
            return currencyManipulator;
        }

    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        List<CurrencyManipulator> result = new ArrayList<>();
        map.forEach((key, value) -> result.add(value));
        return result;
    }


}
