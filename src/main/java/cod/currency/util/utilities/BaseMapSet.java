package cod.currency.util.utilities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JMA - 23/01/2021 18:58
 **/
public class BaseMapSet<T, V> {
    private final HashMap<T, Set<V>> map;

    public BaseMapSet() {
        this.map = new HashMap<>();

    }

    public void put(T key, V value) {
        this.map.computeIfAbsent(key, k -> new HashSet<>()).add(value);
    }

    public void put(T key, Iterable<V> value) {
        value.forEach(v -> this.put(key, v));
    }

    public Set<V> get(T key) {
        return this.map.get(key);
    }

    public List<V> getList(T key) {
        return this.map.get(key).parallelStream().collect(Collectors.toList());
    }
}
