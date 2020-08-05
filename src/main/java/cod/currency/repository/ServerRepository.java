package cod.currency.repository;

import cod.currency.model.Server;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * JMA - 8/5/2020 21:14
 **/
public interface ServerRepository extends MongoRepository<Server, String> {

}
