package com.nazim.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import static com.nazim.constants.ConfigConstants.*;

@PropertySource("classpath:application.properties")
@Profile("!cloud")
@Configuration
public class LocalConfig {

    @Autowired
    private Environment env;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        final String uri = env.getProperty(MONGO_DB_URI_ENV_KEY, MONGO_DB_URI_DEFAULT_VALUE);
        final MongoClientURI mongoClientURI = new MongoClientURI(uri);
        final MongoClient mongoClient = new MongoClient(mongoClientURI);
        final String dbName = env.getProperty(MONGO_DB_NAME_ENV_KEY, MONGO_DB_NAME_DEFAULT_VALUE);
        return new SimpleMongoDbFactory(mongoClient, dbName);
    }

    @Bean
    public ConnectionFactory rabbitFactory() {
        final CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty(RABBIT_MQ_HOST_ENV_KEY,RABBIT_MQ_HOST_DEFAULT_VALUE));
        connectionFactory.setUsername(env.getProperty(RABBIT_MQ_USERNAME_ENV_KEY,RABBIT_MQ_USERNAME_DEFAULT_VALUE));
        connectionFactory.setPassword(env.getProperty(RABBIT_MQ_PASSWORD_ENV_KEY,RABBIT_MQ_PASSWORD_DEFAULT_VALUE));
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }
}