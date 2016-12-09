package com.ai;

import java.util.Comparator;

public class UniformComp implements Comparator<ArrayWrapper> {
    @Override
    public int compare(ArrayWrapper a, ArrayWrapper b) {
        if (a.getCostVal() < b.getCostVal()) {
            return -1;
        }
        if (a.getCostVal() > b.getCostVal()) {
            return 1;
        }
        return 0;
    }
}

