# Cache

There are multiple annotations provides from Spring such as ``@Cacheable``,
``@CacheEvict``, ``@CachePut``, ``@Caching`` and ``@CacheConfig``.
They can bring to us flexibility to react in different situations.

---

*@Cacheable*
Check the id in the cache if it exists return otherwise it will execute the method and save it in the cache
```java
@Cacheable("id1")
public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)
```
```java
// check the first id, if it not exist check the second one and go on
@Cacheable({"id1", "id2"})
public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)
```
```java
// specify key to be used
@Cacheable(cacheNames="books", key="#isbn")
public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)

@Cacheable(cacheNames="books", key="#isbn.rawNumber")
public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)

@Cacheable(cacheNames="books", key="T(someType).hash(#isbn)")
public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)
```
```java
// generator
@Cacheable(cacheNames="books", keyGenerator="myKeyGenerator")
public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)
```
```java
// specify cache manager (custom)
@Cacheable(cacheNames="books", cacheManager="anotherCacheManager")
public Book findBook(ISBN isbn) {...}
```
```java
// the condition parameter - it only save in the cache if it return true otherwise it does not save
// the unless parameter can be used to veto the adding of a value to the cache

```
```java
```
```java
```

---

*@CacheEvict*

---

*@CachePut*

---

*@Caching*

---

*@CacheConfig*



## References
* [Spring Documentation](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/cache.html)
* [Spring Configuration](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-caching.html)
* [Baeldung](https://www.baeldung.com/spring-cache-tutorial)
