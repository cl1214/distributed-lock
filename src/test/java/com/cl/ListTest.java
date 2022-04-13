package com.cl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }

        LinkedHashSet aa = new LinkedHashSet();
        aa.add(1);
    }



}
