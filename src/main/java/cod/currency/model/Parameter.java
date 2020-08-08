package cod.currency.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * JMA - 8/5/2020 21:10
 **/
@Document
@Data
@EqualsAndHashCode
public class Parameter {
    private String id;
    private String key;
    private String value;
    private boolean isActive;
}
