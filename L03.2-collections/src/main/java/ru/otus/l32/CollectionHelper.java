package ru.otus.l32;

import org.apache.commons.collections4.collection.UnmodifiableCollection;

import java.util.*;

/**
 * Created by tully.
 * <p>
 * To create JavaDocs in IDEA: Tools/Generate JavaDoc
 * <p>
 * <p>
 * Common cases:
 * <p>
 * {@link #setToArrayList(Set) Set to ArrayList},
 * <p>
 * {@link #listToHashSet(List) List to HashSet},
 * <p>
 * {@link #collectionToArray(Collection) List to Array},
 * <p>
 * {@link #singletonList(Object) List with one element},
 * <p>
 * {@link #singletonSet(Object) Set with one element},
 * <p>
 * {@link #listFromArray(Object[]) List from array},
 * <p>
 * {@link #immutable(Collection) Immutable collection}
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class CollectionHelper {

    private CollectionHelper() {
    }

    /**
     * Creates an ArrayList on base of the Set.
     *
     * @param set source
     * @return List with elements of the same type as the set.
     */

    public static <T> List<T> setToArrayList(Set<T> set) {
        return new ArrayList<>(set);
    }

    public static <T> Set<T> listToHashSet(List<T> list) {
        return new HashSet<>(list);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] collectionToArray(Collection<T> collection) {
        return collection.toArray((T[]) new Object[collection.size()]);
    }

    public static <T> List<T> singletonList(T object) {
        return Collections.singletonList(object);
    }

    public static <T> Set<T> singletonSet(T object) {
        return Collections.singleton(object);
    }

    public static <T> List<T> listFromArray(T[] array) {
        //return new ArrayList<T>(array);   // error
        //return Arrays.asList(array);      // 1.8
        return List.of(array);              // 1.9
    }

    public static <T> UnmodifiableCollection immutable(Collection<T> collection) {
        return (UnmodifiableCollection) Collections.unmodifiableCollection(collection);
    }
}
