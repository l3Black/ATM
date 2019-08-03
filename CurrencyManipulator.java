package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<>(Comparator.reverseOrder());
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.get(denomination) != null) {
            int oldCount = denominations.get(denomination);
            denominations.put(denomination, count + oldCount);
        } else
            denominations.put(denomination, count);

    }

    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()) {
            result += pair.getKey() * pair.getValue();
        }
        return result;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        List<Integer> listDen = new ArrayList<>();
        List<Integer> listRes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()){
            for (int i = pair.getValue(); i > 0; i--){
                listDen.add(pair.getKey());
            }
        }
        Collections.sort(listDen);
        Collections.reverse(listDen);
        List<Integer> listDenCopy = new ArrayList<>(listDen);
        int amount = expectedAmount;
        while (amount != 0 && !listDen.isEmpty()){
            int maxDen = listDen.get(0);
            boolean flag = true;
            for (Integer i : listDen){
                if (amount - i > 0){
                    amount = amount - i;
                    listRes.add(i);
                } else if (amount - i == 0){
                    amount = amount - i;
                    listRes.add(i);
                    flag = false;
                    break;
                }
            }
            if (flag)
                listDen.removeIf(den -> den == maxDen);
        }
        if (listDen.isEmpty()){
            throw new NotEnoughMoneyException();
        } else {
            for (Integer i : listRes){
                listDenCopy.remove(i);
            }
            denominations = getMapMorfFromList(listDenCopy);
            return getMapMorfFromList(listRes);
        }
    }

    private Map<Integer, Integer> getMapMorfFromList(List<Integer> list){
        Map<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < list.size(); i++){
            if (map.get(list.get(i)) != null){
                int count = map.get(list.get(i));
                map.put(list.get(i), count + 1);
            } else {
                map.put(list.get(i), 1);
            }
        }
        return map;
    }

}
