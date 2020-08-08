package cod.currency.repository;

import cod.currency.model.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JMA - 8/7/2020 20:32
 **/
@Repository
public interface MailRepository extends MongoRepository<Mail, String> {
    @Query("{'active': ?0")
    List<Mail> findByActive(boolean active);
}
