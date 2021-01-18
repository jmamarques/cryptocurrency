package cod.currency.controller.rest;

import cod.currency.service.ParameterService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JMA - 22/11/2020 10:49
 **/
@RestController
@RequestMapping("/parameters")
public class ParameterController {
    private final ParameterService parameterService;

    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    // API
    @GetMapping("/{key}")
    public String getByKey(@NonNull @PathVariable String key) {
        return parameterService.get(key);
    }

}
