package cod.currency.configuration.structural;


import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
//        extends AbstractMongoConfiguration {
//    @Override
//    public MongoClient mongoClient() {
//        return new MongoClient("localhost");
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "trackzilla";
//    }
//
//    @Bean
//    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }
//
//    public @Bean
//    MongoDbFactory mongoDbFactory()  {
//        return new SimpleMongoDbFactory(new MongoClient(), "trackzilla");
//    }
//
//     @Bean
//     public ReleaseCascadeConvertMongoEventListener releaseCascadingMongoEventListener() {
//        return new ReleaseCascadeConvertMongoEventListener();
//     }
//
//    @Bean
//    public MongoCustomConversions customConversions() {
//        List<Converter<?, ?>> converters = new ArrayList<>();
//        converters.add(new ZonedDateTimeReadConverter());
//        converters.add(new ZonedDateTimeWriteConverter());
//        return new MongoCustomConversions(converters);
//    }
}
