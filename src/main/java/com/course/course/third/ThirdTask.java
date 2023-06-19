package com.course.course.third;

import java.util.List;

public class ThirdTask {

    public static void segregate(List<? extends Chordates> srcCollection, List<? super Hedgehog> collection1,
                                 List<? super Manul> collection2, List<? super Lynx> collection3) {
        for (Object in : srcCollection) {
            if (in instanceof Hedgehog) {
                collection1.add((Hedgehog) in);
            } else if (in instanceof Manul) {
                collection2.add((Manul) in);
            } else if (in instanceof Lynx) {
                collection3.add((Lynx) in);
            }
        }
    }
}
