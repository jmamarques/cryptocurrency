package cod.currency.util;

import cod.currency.model.Parameter;

import java.util.Optional;

import static java.util.Optional.of;

/**
 * JMA - 20/11/2020 20:59
 **/
public class BaseUtilTest {
    /** AUXILIARY **/
    /**
     * Method to create a custom Parameter in test Set
     */
    public static Optional<Parameter> createCustomParameter(String id, boolean active, String key, String value) {
        Parameter parameter = new Parameter();
        parameter.setId(id);
        parameter.setActive(active);
        parameter.setKey(key);
        parameter.setValue(value);
        return of(parameter);
    }
}
