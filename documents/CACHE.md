# Cache

There are multiple annotations provides from Spring such as ``@Cacheable``,
``@CacheEvict``, ``@CachePut``, ``@Caching`` and ``@CacheConfig``.
They can bring to us flexibility to react in different situations.

---

*@Cacheable*
Check the id in the cache if it exists return otherwise it will execute the method and save it in the cache
```java
@Cacheable("id1")
```
```java
// check the first id, if it not exist check the second one and go on
@Cacheable({"id1", "id2"})
```

## References
* [Spring Documentation](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/cache.html)
* [Spring Configuration](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-caching.html)
* [Baeldung](https://www.baeldung.com/spring-cache-tutorial)
