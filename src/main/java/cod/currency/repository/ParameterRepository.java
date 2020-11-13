package cod.currency.repository;

import cod.currency.model.Parameter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * JMA - 8/5/2020 21:14
 **/
public interface ParameterRepository extends MongoRepository<Parameter, String> {
    @Query("{'key': ?0, 'active': ?1}")
    Optional<Parameter> findByKeyAndActive(String key, boolean active);

    @Query("{'active': ?0}")
    List<Parameter> findByActive(boolean active);

}
