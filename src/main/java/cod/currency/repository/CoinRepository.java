package cod.currency.repository;

import cod.currency.model.Coin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JMA - 8/7/2020 20:31
 **/
@Repository
public interface CoinRepository extends MongoRepository<Coin, String> {

    @Query("{'currency' : ?0 }")
    Coin findByCurrency(String currency);

    @Query("{'active': ?0}")
    List<Coin> findByActive(boolean active);

}
