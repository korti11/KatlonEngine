package at.korti.katlonengine.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;

/**
 * Created by Korti on 08.02.2016.
 */
public class GroupingList<K, E> extends LinkedList<E> {

    public K key;

    public GroupingList() {
    }

    public GroupingList(Collection<? extends E> c) {
        super(c);
    }

    public static <K, V> GroupingList<K, V> groupBy(K key, Collection<V> values, Function<V, K> getter) {
        GroupingList<K, V> grouping = new GroupingList<>();
        grouping.key = key;
        for (V value : values) {
            K keyValue = getter.apply(value);
            if (key.equals(keyValue)) {
                grouping.add(value);
            }
        }
        return grouping;
    }

    public K getKey() {
        return key;
    }

    public Collection<E> getValues() {
        return this;
    }

}
