package top.xugx;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add(i);
        }
        list.forEach(item->{
            System.out.println(item);
        });
    }
}
