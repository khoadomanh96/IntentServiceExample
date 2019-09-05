package com.example.intentserviceexample;

import java.util.ArrayList;
import java.util.List;

public class GlobalClass {
    private static List<ModelExample> modelExampleList;

    public static List<ModelExample> getModelExampleList() {
        if (modelExampleList == null) {
            modelExampleList = new ArrayList<>();
        }
        return modelExampleList;
    }
}
