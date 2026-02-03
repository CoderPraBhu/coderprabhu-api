package com.coderprabhu.api;

import java.util.ArrayList;
import java.util.List;

import com.coderprabhu.api.data.ZonedDateTimeReadConverter;
import com.coderprabhu.api.data.ZonedDateTimeWriteConverter;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings.Builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;
    
    @Override
    protected void configureClientSettings(Builder builder) {
        builder.applyConnectionString(new ConnectionString(connectionString));
    }

    @Override
	public MongoCustomConversions customConversions() {
		List<Converter<?,?>> converters = new ArrayList<Converter<?,?>>();
		converters.add(new ZonedDateTimeReadConverter());
		converters.add(new ZonedDateTimeWriteConverter());
		return new MongoCustomConversions(converters);
    }
   
    @Override
    protected String getDatabaseName() {
        return "CoderPraBhuApi";
    }
}