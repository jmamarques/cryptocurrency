package cod.currency.model;

import cod.currency.util.deserialize.CustomDateConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * JMA - 8/5/2020 21:20
 **/
@Document
@Data
public class CryptoCurrency {
    @Id
    private String id;
    private double high;
    private double last;
    @JsonDeserialize(converter = CustomDateConverter.class)
    private Date timestamp;
    private double bid;
    private double vwap;
    private double volume;
    private double low;
    private double ask;
    private double open;
}
