package cod.currency.service.impl;

import cod.currency.model.Parameter;
import cod.currency.repository.ParameterRepository;
import cod.currency.service.ParameterService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * JMA - 13/11/2020 17:24
 **/
@Service
@CacheConfig(cacheNames = "parameters")
public class ParameterServiceImpl implements ParameterService {
    private final ParameterRepository parameterRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    @Cacheable(key = "#key")
    public String get(@NonNull String key) {
        Optional<Parameter> parameter = parameterRepository.findByKeyAndActive(key, true);
        return parameter.map(Parameter::getValue).orElse(null);
    }
}
