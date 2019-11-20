package com.Employee.DaoImplementation;

import java.util.Iterator;
import org.jongo.Jongo;

import com.Employee.Dao.AccountDao;
import com.Employee.Pojo.Address;
import com.Employee.Pojo.Counter;
import com.Employee.Pojo.Registration;
import com.Employee.Pojo.Response;
import com.Employee.Util.MongoDBUtil;
import com.mongodb.WriteResult;

public class AccountDaoImplementation implements AccountDao {

	@Override
	public Response registrationUser(Registration registration) {
		Response response = new Response();
		{
			Registration registrationUser = new Jongo(MongoDBUtil.getDB()).getCollection("registration")
					.findOne("{$or:[{email:#},{mobileNo:#}]}", registration.getEmail(), registration.getMobileNo())
					.as(Registration.class);

			if (registrationUser != null) {

				if (registrationUser.getMobileNo() == registration.getMobileNo()) {
					response.setStatusCode(601);
					response.setStatusMessage("Mobile Number already exists");
				}
				if (registrationUser.getEmail().equals(registration.getEmail())) {
					response.setStatusCode(601);
					response.setStatusMessage("Email already exists");
				}

			} else {
				if (registration != null && registration.getEmpId() == 0) 
				{
					String id = "empId";
					new Jongo(MongoDBUtil.getDB()).getCollection("Counter").update("{ _id:# }", id).with("{$inc:{seq:1}}");
					Counter counter = new Jongo(MongoDBUtil.getDB()).getCollection("Counter").findOne("{ _id: #}", id)
					.as(Counter.class);
					registration.setEmpId(counter.getSeq());
				}
				new Jongo(MongoDBUtil.getDB()).getCollection("registration").insert(registration);
				response.setStatusCode(200);
				response.setStatusMessage("REGISTRATION SUCCESSFUL");
				System.out.println("REGISTRATION SUCCESSFUL");
			}
		}

		return response;
	}
	

	@Override
	public Response delete(long empId) {
		Response response = new Response();
		WriteResult num = new Jongo(MongoDBUtil.getDB()).getCollection("registration").remove("{empId:#}", empId);
		if (num.getN() > 0) {
			response.setStatusCode(200);
			response.setStatusMessage("Deleted");
		} else {
			response.setStatusCode(500);
			response.setStatusMessage("User does not exist");
		}
		return response;
	}
	@Override
	public Response update1(Registration updation) 
	{
	//	System.out.println(updation.get_id());
		Response response=new Response();
		if (updation == null) {
			response.setStatusCode(600);
			response.setStatusMessage("User does not exist");
		} else {
			new Jongo(MongoDBUtil.getDB()).getCollection("registration").update("{empId:#}", updation.getEmpId())
			.upsert().with(updation);
			response.setStatusCode(200);
			response.setStatusMessage("Update");
		}
		return response;
	}
	@Override
	public Registration update(long empId) {
		Registration updation = new Jongo(MongoDBUtil.getDB()).getCollection("registration")
				.findOne("{empId:#}", empId).as(Registration.class);
		return updation;
	}


	@Override
	
	public Response login(Registration reg) 
	{
		Response response=new Response();
		Registration login= new Jongo(MongoDBUtil.getDB()).getCollection("registration")
				.findOne("{email:#,password:#}",reg.getEmail(),reg.getPassword()).as(Registration.class);
		if(login==null)
		{
			response.setStatusCode(600);
			response.setStatusMessage("Invalid Login data");
			System.out.println("Invalid registration");
		}
		else
		{
			response.setStatusCode(200);
			response.setStatusMessage("Successfully logged in");
			response.setEmpid(login.getEmpId());
		System.out.println("Successfully logged in");
		}
		return response;
	}


	@Override
	public Iterator<Registration> birthdayFetch() {
		Iterator<Registration> itr = null;
		try{
		 itr= new Jongo(MongoDBUtil.getDB()).getCollection("registration")
		.find().as(Registration.class);
		
		}catch(Exception e)
		{
			System.out.println("exception while db operation");
		}
		return itr;
	}
	public Iterator<Registration> empAnniversaryFetch() {
		Iterator<Registration> itr = null;
		try{
		 itr= new Jongo(MongoDBUtil.getDB()).getCollection("registration")
		.find().as(Registration.class);
		
		}catch(Exception e)
		{
			System.out.println("exception while db operation");
		}
		return itr;
	}


	@Override
	public Address getAddress(int id) {
		Address address=new Address();
		try{
		Registration registration	=new Jongo(MongoDBUtil.getDB()).getCollection("registration").findOne("{empId:#}",id).as(Registration.class);
		address=registration.getEmpAddress();
		System.out.println(registration.toString());
		}catch(Exception e)
		{
			System.out.println("exception while db operation");
		}
		return address;
	}
}
