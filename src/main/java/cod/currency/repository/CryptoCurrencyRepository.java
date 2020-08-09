package cod.currency.repository;

import cod.currency.model.Coin;
import cod.currency.model.CryptoCurrency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JMA - 8/5/2020 21:46
 **/
@Repository
public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String> {

    @Query("{'coin': ?0}")
    List<CryptoCurrency> findAllByCoin(Coin coin);
}
