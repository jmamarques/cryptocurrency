package cod.currency.util.deserialize;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Date;

/**
 * JMA - 8/5/2020 22:15
 **/
public class CustomDateConverter extends StdConverter<Long, Date> {
    @Override
    public Date convert(Long l) {
        return new Date(l * 1000);
    }
}
