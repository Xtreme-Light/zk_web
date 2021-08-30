package hello.unit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class UnitTest {
    @Test
    public void test() {
        Cache<Object, Object> build = CacheBuilder.newBuilder()
                .concurrencyLevel(10)
                .expireAfterWrite(-1L, TimeUnit.MINUTES)
                .initialCapacity(5)
                .recordStats()
                .build();
        build.put(1,"1");
        System.out.println(build.asMap().values());
    }
}
