package cod.currency.repository;

import cod.currency.model.Parameter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * JMA - 8/5/2020 21:14
 **/
public interface ParameterRepository extends MongoRepository<Parameter, String> {
    @Query("{'key': ?0")
    Parameter findByKeyAnd(String key);

    @Query("{'active': ?0")
    List<Parameter> findByActive(boolean active);
}
