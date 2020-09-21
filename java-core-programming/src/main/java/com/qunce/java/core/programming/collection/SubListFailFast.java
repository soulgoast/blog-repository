package com.qunce.java.core.programming.collection;

import java.util.ArrayList;
import java.util.List;

public class SubListFailFast {

    public static void main(String[] args) {
        List masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");

        List branchList = masterList.subList(0, 3);

        masterList.remove(0);
        masterList.add("ten");
        masterList.clear();

        branchList.clear();
        branchList.add("six");
        branchList.add("seven");
        branchList.remove(0);

        String string = "";
        System.out.println(string);



    }
}
