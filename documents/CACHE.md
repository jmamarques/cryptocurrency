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
@Cacheable(cacheNames="book", condition="#name.length < 32", unless="#result.hardback")
public Book findBook(String name)
```

---

*@CacheEvict* is used to remove one or more values in the cache

```java
// remove all entries
@CacheEvict(cacheNames="books", allEntries=true)
public void loadBooks(InputStream batch)
```

---

*@CachePut* is used to populate the value, this run the method all the time

```java
@CachePut(cacheNames="book", key="#isbn")
public Book updateBook(ISBN isbn, BookDescriptor descriptor)
```

---

*@Caching* is used to compose multiple annotations 

```java
@Caching(evict = { @CacheEvict("primary"), @CacheEvict(cacheNames="secondary", key="#p0") })
public Book importBooks(String deposit, Date date)
```

---

*@CacheConfig* is used to avoid repetitive code such as cacheNames 4example

```java
@CacheConfig("books")
public class BookRepositoryImpl implements BookRepository {

    @Cacheable
    public Book findBook(ISBN isbn) { return null; }
}
```

---


## References
* [Spring Documentation](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/cache.html)
* [Spring Configuration](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-caching.html)
* [Baeldung](https://www.baeldung.com/spring-cache-tutorial)
