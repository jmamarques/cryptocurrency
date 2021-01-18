# ACTUATOR

## Implementations Notes

### Health Indicator

```java 
@Component
public class RestaurantHealthCheck implements HealthIndicator {
    private final RestaurantRepository restaurantRepository;

    public RestaurantHealthCheck(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Health health() {
        Long restaurantCount = restaurantRepository.getRestaurantCount();
        if (restaurantCount > 0) {
            return Health.up()
                         .withDetail("restaurantCount", restaurantCount)
                         .build();
        } else {
            return Health.status(new Status("NO_RESTAURANTS"))
                         .build();
        }
    }
}
```

### Actuator Endpoint

```java
@Component
@Endpoint(id = "restaurant")
public class RestaurantCustomEndpoint {

    Map<String, String> map = new HashMap<>();

    public RestaurantCustomEndpoint(RestaurantRepository restaurantRepository,
                                    @Value("${info.restaurant.location: New York}") String location) {
        Long restaurantCount = restaurantRepository.getRestaurantCount();
        map.put("restaurant.count", restaurantCount.toString());
        map.put("restaurant.location", location);
    }

    @ReadOperation
    public Map<String, String> readOperation() {
        return map;
    }

    @WriteOperation
    public Map<String, String> writeOperation(String key, String value) {
        map.put(key, value);
        return map;
    }

    @DeleteOperation
    public Map<String, String> deleteOperation() {
        map.clear();
        return map;
    }
}
```

### YML Configurations

```yml
# Set the severity order
management.endpoint.health.status.order=NO_RESTAURANTS,DOWN,UP

# Custom build information
info.restaurant.location=New York
info.restaurant.discountPercentage=10
```
