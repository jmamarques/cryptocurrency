package cod.currency.configuration.structural;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

/**
 * JMA - 08/11/2020 10:42
 * No longer needed in SpringBoot - Automatic Configuration
 **/
//@Configuration
//@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("parameters");
    }
}
