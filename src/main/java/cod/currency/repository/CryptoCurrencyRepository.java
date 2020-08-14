package cod.currency.repository;

import cod.currency.model.Coin;
import cod.currency.model.CryptoCurrency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

/**
 * JMA - 8/5/2020 21:46
 **/
@Repository
public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String> {

    @Query(value = "{'coin': ?0}", sort = "{ timestamp : -1 }")
    Page<CryptoCurrency> findAllByCoin(Coin coin, Pageable page);


    @Aggregation(pipeline = {"{ $match:  {'timestamp': { '$gt': ?1 }}}",
            "{ $match: {'coin': ?0}}",
            "{ $group: {" +
                    "            _id: null\n" +
                    "            , 'ask' : {$avg: '$ask'}\n" +
                    "            , 'bid' : {$avg: '$bid'}\n" +
                    "            , 'high' : {$avg: '$high'}\n" +
                    "            , 'last' : {$avg: '$last'}\n" +
                    "            , 'low' : {$avg: '$low'}\n" +
                    "            , 'open' : {$avg: '$open'}\n" +
                    "            , 'volume' : {$avg: '$volume'}\n" +
                    "            , 'vwap' : {$avg: '$vwap'}\n" +
                    "            , 'coin' : {$avg: '$coin'}\n" +
                    "        }}" +
                    "    }"})
    CryptoCurrency avgByCoinAndDate(Coin coin, LocalDate date);

    @Aggregation(pipeline = {"{ $match:  {'timestamp': { '$gte': ?1 }}}",
            "{ $match: {'timestamp': { '$lte': ?2 }}}",
            "{ $match: {'coin': ?0}}",
            "{ $group: {" +
                    "            _id: null\n" +
                    "            , 'ask' : {$avg: '$ask'}\n" +
                    "            , 'bid' : {$avg: '$bid'}\n" +
                    "            , 'high' : {$avg: '$high'}\n" +
                    "            , 'last' : {$avg: '$last'}\n" +
                    "            , 'low' : {$avg: '$low'}\n" +
                    "            , 'open' : {$avg: '$open'}\n" +
                    "            , 'volume' : {$avg: '$volume'}\n" +
                    "            , 'vwap' : {$avg: '$vwap'}\n" +
                    "            , 'coin' : {$avg: '$coin'}\n" +
                    "        }}" +
                    "    }"})
    CryptoCurrency avgByCoinAndDate(Coin coin, Date date1, Date date2);
}
