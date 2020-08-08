package cod.currency.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * JMA - 8/7/2020 20:01
 **/
@Document
@Data
@EqualsAndHashCode
public class Mail {

    @Id
    private String id;
    private String mail;
    private String description;
    private boolean active;

}
