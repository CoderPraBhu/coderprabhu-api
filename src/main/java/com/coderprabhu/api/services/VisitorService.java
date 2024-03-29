
package com.coderprabhu.api.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

import com.coderprabhu.api.dao.VisitorRepository;
import com.coderprabhu.api.dao.VisitorTemplate;
import com.coderprabhu.api.data.Visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import ua_parser.Client;
import ua_parser.Parser;

/**
 * VisitorService
 */
@Log4j2
@Component
public class VisitorService {

    private static final String UNKNOWN = "UNKNOWN";

    @Autowired Parser parser;

    @Autowired VisitorRepository repository;

    @Autowired VisitorTemplate visitorTemplate;

    public Visitor processVisit(HttpServletRequest request) {
        String ip = extractIp(request);
        String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));
        String visitorDetail = "IP: " + ip + ", Device: " + deviceDetails;
        Visitor visitor = new Visitor();
        visitor.setDevice(deviceDetails);
        visitor.setIp(ip);
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("America/Los_Angeles"));
        visitor.setZonedDateTime(zonedDateTime);
        log.info("New visit from " + visitorDetail + "at : " + zonedDateTime.toString());
        Visitor visit = repository.save(visitor);
        log.info("Visit recorded " + visit.getId() + "at : " + visit.getZonedDateTime());
		return visitor;
    }
    
    public Integer getTotalVisits() {
		return repository.findAll().size();
    }
    
    public Integer getUniqueVisits() {
        Integer uniqueVisits = visitorTemplate.getUniqueVisits();
        log.info("UniqueVisits: " + uniqueVisits);
		return uniqueVisits;
	}

    private String extractIp(HttpServletRequest request) {
        String clientIp;
        String clientXForwardedForIp = request
          .getHeader("x-forwarded-for");
        if (Objects.nonNull(clientXForwardedForIp)) {
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        } else {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

    private String getDeviceDetails(String userAgent) {
        String deviceDetails = UNKNOWN;
        Client client = parser.parse(userAgent);
        if (Objects.nonNull(client)) {
            deviceDetails = client.userAgent.family + " " + client.userAgent.major + "." + client.userAgent.minor +
                    " - " + client.os.family + " " + client.os.major + "." + client.os.minor;
        }
        return deviceDetails;
    }

    private String parseXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }

}