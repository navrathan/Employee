package com.Employee.Controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Pojo.Address;
import com.Employee.Pojo.Registration;
import com.Employee.Pojo.Response;
import com.Employee.Service.AccountService;
import com.google.gson.Gson;
@RestController
@RequestMapping("/account")
public class EmployeeController 
{
	private static final Logger logger = Logger.getLogger(EmployeeController.class);
	AccountService accountService = new AccountService();

	@RequestMapping(value = "/registration", method = RequestMethod.POST, headers = "Accept=application/json")
	public Response registrationUser(@RequestBody Registration registration) {
		Response response = new Response();
		try {
			System.out.println("INSIDE THE REGISTRATION"+registration.getEmail());
			response = accountService.registrationUser(registration);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusMessage("Exception occurred while Registering user");
		}
		return response;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,headers="Accept=application/json")
	public Response login(@RequestBody Registration log)
	{
		System.out.println("INSIDE THE Login" +new Gson().toJson(log));
		Response response=new Response();
		response=accountService.login(log);
		return response;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Response delete(long empId)
	{
		System.out.println("INSIDE THE Delete");
		Response response=new Response();
		response=accountService.delete(empId);
		return response;
	}
	
	@RequestMapping(value = "/update1", method = RequestMethod.GET)
	public Registration update(long empId)
	{
		
		System.out.println("INSIDE THE Update/Sending Object");
		Registration reg=new Registration();
		reg=accountService.update(empId);
		return reg;
	}
	@RequestMapping(value="/update2",method=RequestMethod.POST,headers="Accept=application/json")
	public Response update1(@RequestBody Registration update)
	{
		System.out.println("INSIDE THE Update1/Receiving Updated value");
		Response response=new Response();
		response=accountService.update1(update);
		return response;
		
	}
	
	@RequestMapping(value="/map",method= RequestMethod.GET)
	public Address map(int id)
	{
		Address address=new Address();
		address=accountService.getAddress(id);
		return address;
		
	}
}
