import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineCache {
    static String loadFromDB(String key) {
        System.out.println(">>> DATABASE LOAD for key: " + key);
        try { Thread.sleep(50); } catch (InterruptedException e) {}
        return "value_for_key: " + key;
    }

    public static void main(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .recordStats()
                .build();

        String val1 = cache.get("x",key -> loadFromDB(key));
        System.out.println("Got: " + val1);

//        String val2 = cache.get("x",key -> loadFromDB(key));
//        System.out.println("Got: " + val2);

//        System.out.println("Cache stats: " + cache.stats());

//        cache.put("y", "manual_value");
//        System.out.println("Manual put: " + cache.getIfPresent("y"));
    }

}
