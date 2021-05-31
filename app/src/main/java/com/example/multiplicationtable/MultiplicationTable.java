package com.example.multiplicationtable;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.*;
import java.util.stream.Collectors;


public class MultiplicationTable {
    private Map<Example, Condition> solvedExample;
    private static final Random RND = new Random();
    @RequiresApi(api = Build.VERSION_CODES.N)
    public MultiplicationTable() {

        this.solvedExample = generateMultiplicationTable();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public MultiplicationTable(int bound) {
        this.solvedExample = generateMultiplicationTable(bound);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Map<Example, Condition> generateMultiplicationTable(int bound) {

        List<Map.Entry<Example, Condition>> keys = new ArrayList<>(generateMultiplicationTable().entrySet()).subList(bound * 9 - 9, bound * 9);
        return keys.stream().filter(x -> generateMultiplicationTable().containsKey(x.getKey()))
                .collect(Collectors.toMap(x -> x.getKey(), y -> generateMultiplicationTable().get(y.getKey())));


    }

    private Map<Example, Condition> generateMultiplicationTable() {
        Map<Example, Condition> returnValue = new LinkedHashMap<>();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {

                returnValue.put(new Example(i + "*" + j, i * j), Condition.UNRESOLVED);

            }
        }
        return returnValue;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getNextExample() {
        Map<Example, Condition> solvedFilter = solvedExample.entrySet().stream().filter(i -> i.getValue().equals(Condition.UNRESOLVED))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<Example> keys = new ArrayList<>(solvedFilter.keySet());
        return keys.get(RND.nextInt(keys.size())).getTextRepresentstion();


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean solveExample(String example, boolean question) {
        if (question) {
            solvedExample.put(getExample(example), Condition.RIGHT);
            return true;
        } else {
            solvedExample.put(getExample(example), Condition.WRONG);
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean hasNext() {
        return !solvedExample.entrySet().stream().filter(x -> x.getValue().equals(Condition.UNRESOLVED))
                .collect(Collectors.toList()).isEmpty();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getUnResolved() {
        return getAmount(Condition.WRONG);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int getAmount(Condition filter) {
        return solvedExample.entrySet().stream().filter(x -> x.getValue().equals(filter))
                .collect(Collectors.toList()).size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getResolved() {
        return getAmount(Condition.RIGHT);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getResult(String example) {
        return resultOfExamples(example).get().getResult();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
   public Example getExample(String example) {
        return resultOfExamples(example).get();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean solvexample(String example, int value) {
        if (getResult(example) == value) {
            solvedExample.put(resultOfExamples(example).get(), Condition.RIGHT);
            return true;
        } else {
            solvedExample.put(resultOfExamples(example).get(), Condition.WRONG);

            return false;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private Optional<Example> resultOfExamples(String example){
        Optional<Example> result = solvedExample.entrySet().stream().filter(i -> i.getKey().getTextRepresentstion().
                equals(example))
                .map(Map.Entry::getKey)
                .findFirst();

        return result;
    }
}
