package cod.currency.repository;

import cod.currency.model.Parameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static cod.currency.util.BaseUtilTest.createCustomParameter;
import static cod.currency.util.GeneralConst.TEST_KEY;
import static cod.currency.util.GeneralConst.TEST_VALUE;

/**
 * JMA - 20/11/2020 20:54
 **/
@DataMongoTest
@ExtendWith(SpringExtension.class)
class ParameterRepositoryTest {


    @Autowired
    private ParameterRepository parameterRepository;

    @BeforeEach
    void setUp() {
        // delete all
        parameterRepository.deleteAll();
        // populate
        parameterRepository.insert(createCustomParameter(null, true, TEST_KEY, TEST_VALUE).orElseThrow(RuntimeException::new));
    }

    @Test
    void findByKeyAndActive() {
    }

    @Test
    void findByActive() {
        List<Parameter> byActive = parameterRepository.findByActive(false);
        Assertions.assertNotNull(byActive);
        Assertions.assertEquals(0, byActive.size());

        // populate with new cases
        parameterRepository.insert(createCustomParameter(null, false, TEST_KEY + 1, TEST_VALUE).orElseThrow(RuntimeException::new));
        parameterRepository.insert(createCustomParameter(null, false, TEST_KEY + 2, TEST_VALUE).orElseThrow(RuntimeException::new));
        byActive = parameterRepository.findByActive(false);
        Assertions.assertEquals(2, byActive.size());

    }
}
