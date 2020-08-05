package cod.currency.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * JMA - 8/5/2020 21:10
 **/
@Document
@Data
public class Server {
    private String id;
    private String name;
    private boolean isActive;
    private String url;
}
