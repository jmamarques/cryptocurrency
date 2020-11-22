# Crypto Currency Project

CryptoCurrency Project is a Java library for dealing with currencies and notify the members.
We will use Thymeleaf to create templates to send via email.

## Installation
Configuration:
1. If you want to encrypt your password, deploy your secret as follows: `-Djasypt.encryptor.password=secretkey` or `jasypt.encryptor.password=<secretkey>`
2. Put the following property to false if you don't use the secret `jasypt.encryptor.mandatory=false`
3. Add server email: `spring.mail.username=<email>`
4. Add password email: `spring.mail.password=<password>`

Steps:
1. Create and start database container `docker-compose -f docker-compose_only_db.yml up -d`, instead it we can use a local database with 
default configurations to mongo db
2. docker compose of project in progress

## Usage
in progress...

## Track problems
[Failed to Configure a DataSource](https://www.baeldung.com/spring-boot-failed-to-configure-data-source)

## Documentation
Helpful documents to understand some implementations done in this project.
* [Cache](documents/CACHE.md)

## Contributing

## License

[MIT](https://choosealicense.com/licenses/mit/)
