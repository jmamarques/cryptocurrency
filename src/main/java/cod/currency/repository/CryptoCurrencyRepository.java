package cod.currency.repository;

import cod.currency.model.Coin;
import cod.currency.model.CryptoCurrency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * JMA - 8/5/2020 21:46
 **/
@Repository
public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String> {

    @Query(value = "{'coin': ?0}", sort = "{ timestamp : -1 }")
    Page<CryptoCurrency> findAllByCoin(Coin coin, Pageable page);

    @Query(value = "{'timestamp': { '$gte': ?0 }}", sort = "{ timestamp : -1 }")
    Page<CryptoCurrency> findAllByDate(Date date, Pageable page);
}
