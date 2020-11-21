package cod.currency.repository;

import cod.currency.model.Parameter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * JMA - 8/5/2020 21:14
 **/
public interface ParameterRepository extends MongoRepository<Parameter, String> {

    Optional<Parameter> findByKeyAndActive(String key, boolean active);

    List<Parameter> findByActive(boolean active);

}
