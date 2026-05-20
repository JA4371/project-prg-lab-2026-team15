import java.util.*;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache);
//        cache.get(1);
//        System.out.println(cache);
//        cache.put(4, "D");
//        System.out.println(cache);

    }
}

//Notes:
//1. This <K,V> syntax is called generics. It lets our cache work with any key and value type – similar to how ArrayList<String> works with Strings."
//2. protected means our LRUCache class – being a subclass of LinkedHashMap – can override removeEldestEntry. External code cannot call this method directly, which is good design because only the map itself should decide when to check for eviction."
//3. "Map.Entry<K,V> is just the type for a single key-value pair inside the map. The removeEldestEntry method receives the eldest entry as a parameter. We don't use it in our simple eviction, but we could inspect it if we wanted to know which entry is about to be evicted."
//4.
//Step 1: [H] ⇄ 1 ⇄ 2 ⇄ 3 ⇄ [T] (after three puts)
//Step 2: get(1) → [H] ⇄ 2 ⇄ 3 ⇄ 1 ⇄ [T]
//Step 3: put(4) → evict 2 → [H] ⇄ 3 ⇄ 1 ⇄ 4 ⇄ [T]