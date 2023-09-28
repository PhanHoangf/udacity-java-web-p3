package com.udacity.jdnd.course3.critter.utils;

import java.util.Collection;
import java.util.stream.Stream;

public class Utils {

    public static <T> Stream<T> collectionAsStream(Collection<T> collection) {
        return collection == null
                ? Stream.empty()
                : collection.stream();
    }
}
