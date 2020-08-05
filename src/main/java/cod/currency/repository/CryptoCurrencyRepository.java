package cod.currency.repository;

import cod.currency.model.CryptoCurrency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * JMA - 8/5/2020 21:46
 **/
@Repository
public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String> {
}
