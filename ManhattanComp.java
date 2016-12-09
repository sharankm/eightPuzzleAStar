package com.ai;

import java.util.Comparator;

public class ManhattanComp implements Comparator<ArrayWrapper> {

    @Override
    public int compare(ArrayWrapper a, ArrayWrapper b) {
        if ((a.getCostVal() + a.getMtVal()) < (b.getCostVal() + b.getMtVal())) {
            return -1;
        }
        if ((a.getCostVal() + a.getMtVal()) > (b.getCostVal() + b.getMtVal())) {
            return 1;
        }
        return 0;
    }
}
