
package com.coderprabhu.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

import org.bson.Document;

/**
 * VisitorTemplate
 */
@Component
@Log4j2
public class VisitorTemplate {

    @Autowired MongoOperations mongoOps;

	public Integer getUniqueVisits() {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.group("ip"),
            Aggregation.count().as("count")
        );
        AggregationResults<Document> results = mongoOps.aggregate(aggregation, "visitor", Document.class);
        Document result = results.getUniqueMappedResult();
        int count = (result != null) ? result.getInteger("count", 0) : 0;
        log.info("UniqueVisits: " + count);
		return count;
	}
}
