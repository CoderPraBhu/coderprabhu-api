
package com.coderprabhu.api.dao;

import java.util.concurrent.atomic.AtomicInteger;

import com.mongodb.client.DistinctIterable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * VisitorTemplate
 */
@Component
@Log4j2
public class VisitorTemplate {

    @Autowired MongoOperations mongoOps;

	public Integer getUniqueVisits() {
        AtomicInteger count = new AtomicInteger(0);
        DistinctIterable<String> distinct = mongoOps.getCollection("visitor").distinct("ip", String.class);
        distinct.forEach(ip -> {log.info("Unique visitor: " + ip); count.incrementAndGet();});
		return count.get();
	}
}