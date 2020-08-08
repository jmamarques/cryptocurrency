package cod.currency.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * JMA - 8/7/2020 19:46
 **/
@Document
@Data
public class Coin {
    @Id
    private String id;
    private String name;
    private String currency;
    private boolean active;

}
