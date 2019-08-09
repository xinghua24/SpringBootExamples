package com.example.mongo.config;

import com.example.mongo.entity.Employee;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import com.mongodb.MongoClient;


@Configuration
public class MongoConfig {
    @Bean
    public MongoClient mongoClient() throws IOException {
        String ip = "localhost";
        int port = 27017;

        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        return new MongoClient(ip, port);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "test");

        Employee alice = new Employee("Alice", 20);
        mongoTemplate.save(alice, "employees");

        Employee bob = new Employee("Bob", 18);
        mongoTemplate.insert(bob, "employees");

        Employee cindy = new Employee("Cindy", 22);
        mongoTemplate.insert(cindy, "employees");

        return mongoTemplate;
    }
}
