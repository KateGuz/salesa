package com.salesa.controller;

import com.salesa.security.UserSecurity;
import com.salesa.service.cache.ReportCache;
import com.salesa.util.entity.ReportRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GenerateReportController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserSecurity userSecurity;
    @Autowired
    private ReportCache reportCache;

    @RequestMapping(value = "/admin/generateReport", method = RequestMethod.POST)
    public ResponseEntity<Void> generateReport(HttpSession session, HttpServletRequest request) throws Exception {
       if(userSecurity.getUserBySessionId(session.getId())!= null){
           String format = request.getParameter("format");
           String currency = request.getParameter("currency");
           String dateFrom = request.getParameter("dateFrom");
           String dateTo = request.getParameter("dateTo");
           String email = request.getParameter("emailTo");
           if(dateFrom == null || dateTo == null || email == null){
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           } else{
               ReportRequest reportRequest = new ReportRequest(format, dateFrom, dateTo, email, currency);
               log.info("creating request for report : {}", reportRequest);
               reportCache.getReportRequestCache().add(reportRequest);
               return new ResponseEntity<>(HttpStatus.OK);
           }
       }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
