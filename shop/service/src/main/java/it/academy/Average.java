package it.academy;

import java.util.ArrayList;
import java.util.List;

public class Average {
    public static Double getAverage(List<Integer> numbers){
        return (numbers.stream()
                .filter(x->x!=null)
                .map(x->Double.valueOf(x))
                .reduce(((integer1, integer2) -> (integer1+integer2)/2))
                .orElse(null));
    }

    public static void main(String[] args) {
        List<Integer> numbers=new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(null);

        Double avg=getAverage(numbers);
        System.out.println(avg);
    }
}
