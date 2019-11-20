package com.Employee.Controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Pojo.Birthdaylist;
import com.Employee.Pojo.EmployeeAnniversaryList;
import com.Employee.Service.AccountService;
@RestController
@RequestMapping("/notification")

public class CronController 
{
	private static final Logger logger = Logger.getLogger(EmployeeController.class);
	AccountService accountService = new AccountService();
	 List<Birthdaylist> bdayList=new ArrayList<>();
	 @Async
	 @Scheduled(cron ="*/20 * * * * *")
	 @RequestMapping(value = "/birthday", method = RequestMethod.GET)
	 public List<Birthdaylist> birthdayFetch() throws Exception {
	 try {
		
	 logger.debug("creating wellness and diagnostic slots");
	 bdayList= accountService.birthdayFetch();

	 } catch (Exception e) {
	 logger.error("Exception occurred while creating wellness slot", e);
	 }
	return bdayList;
	 }
	 List<EmployeeAnniversaryList> empAnniversaryList=new ArrayList<>();
	 @Async
	 @Scheduled(cron ="*/5 * * * * *")
	 @RequestMapping(value = "/employeeAnniversary", method = RequestMethod.GET)
	 public List<EmployeeAnniversaryList> empAnniversaryFetch() throws Exception {
	 try {
		
	 logger.debug("creating wellness and diagnostic slots");
	 empAnniversaryList= accountService.empAnniversaryFetch();

	 } catch (Exception e) {
	 logger.error("Exception occurred while creating wellness slot", e);
	 }
	return empAnniversaryList;
	 }
}
