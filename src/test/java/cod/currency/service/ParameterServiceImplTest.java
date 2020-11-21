package cod.currency.service;

import cod.currency.repository.ParameterRepository;
import cod.currency.service.impl.ParameterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static cod.currency.util.BaseUtilTest.createCustomParameter;
import static cod.currency.util.GeneralConst.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * JMA - 13/11/2020 20:13
 **/
public class ParameterServiceImplTest {

    /**
     * DEPENDENCIES
     **/
    ParameterServiceImpl parameterServiceImpl;

    @Mock
    ParameterRepository parameterRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        parameterServiceImpl = new ParameterServiceImpl(parameterRepository);
        /* PREPARATION */
        when(parameterRepository.findByKeyAndActive(TEST_KEY, true)).thenReturn(createCustomParameter("1", ACTIVE, TEST_KEY, TEST_VALUE));
        when(parameterRepository.findByKeyAndActive(TEST_KEY_INACTIVE, false)).thenReturn(createCustomParameter("2", INACTIVE, TEST_KEY_INACTIVE, TEST_VALUE + 2));
        when(parameterRepository.findByKeyAndActive(TEST_KEY_INACTIVE, true)).thenReturn(Optional.empty());
        when(parameterRepository.findByKeyAndActive(TEST_KEY + 3, true)).thenReturn(createCustomParameter("3", INACTIVE, TEST_KEY + 3, TEST_VALUE + 3));
    }

    /**
     * TEST ZONE
     **/
    @Test
    void get() {
        // when
        Assertions.assertNotNull(parameterServiceImpl.get(TEST_KEY));
        Assertions.assertNull(parameterServiceImpl.get(TEST_KEY_INACTIVE));
        Assertions.assertEquals(TEST_VALUE + 3, parameterServiceImpl.get(TEST_KEY + 3));

        // then
        verify(parameterRepository, times(3)).findByKeyAndActive(anyString(), anyBoolean());
    }
}
