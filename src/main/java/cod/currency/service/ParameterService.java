package cod.currency.service;

import cod.currency.model.Parameter;
import cod.currency.repository.ParameterRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * JMA - 13/11/2020 17:24
 **/
@Service
@CacheConfig(cacheNames = ParameterService.CACHE_NAME)
public class ParameterService {
    public static final String CACHE_NAME = "parameters";
    private final ParameterRepository parameterRepository;

    public ParameterService(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
        // TODO DUMMY - TEST
        this.get("test");
    }

    @Cacheable(key = "#key")
    public String get(@NotNull String key) {
        Optional<Parameter> parameter = parameterRepository.findByKeyAndActive(key, true);
        return parameter.isPresent() ? parameter.get().getValue() : null;
    }
}
