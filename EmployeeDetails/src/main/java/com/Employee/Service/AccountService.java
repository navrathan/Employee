package com.Employee.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.Employee.Dao.AccountDao;
import com.Employee.DaoImplementation.AccountDaoImplementation;
import com.Employee.Pojo.Address;
import com.Employee.Pojo.Birthdaylist;
import com.Employee.Pojo.EmployeeAnniversaryList;
import com.Employee.Pojo.Registration;
import com.Employee.Pojo.Response;

public class AccountService 
{
	AccountDao accountdao = new AccountDaoImplementation();

	public Response registrationUser(Registration registration)
	{
		return accountdao.registrationUser(registration);
	}

	public Registration update(long empId) {
		return accountdao. update(empId);
	}
	
	public Response update1(Registration updation) {
		return accountdao. update1(updation);
	}
	
	public Response delete(long empId) {
		return accountdao.delete(empId);
	}

	public Response login(Registration log) {
		return accountdao.login(log) ;
	}

	public List<Birthdaylist> birthdayFetch() throws Exception {
		
		Iterator<Registration> itr=accountdao.birthdayFetch();
		List<Birthdaylist> list=new ArrayList<>();
	 try{
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		while(itr.hasNext()){
			Birthdaylist bday=new Birthdaylist();
			Registration reg=itr.next();
			Calendar dbCalendar=Calendar.getInstance();
			dbCalendar.setTimeInMillis(reg.getBirthday());
			dbCalendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR) );
			long difference=dbCalendar.getTimeInMillis()-calendar.getTimeInMillis();
			if(difference>=0&&difference<=604800000)
			{
				bday.setEmpName(reg.getName());
				if(difference<=86400000)
				bday.setDayLeft(1);
				else if(difference>86400000&&difference<=172800000)
				bday.setDayLeft(2);
				else if(difference>172800000&&difference<=259200000)
				bday.setDayLeft(3);
				else if(difference>259200000&&difference<=345600000)
				bday.setDayLeft(4);
				else if(difference>345600000&&difference<=432000000)
				bday.setDayLeft(5);
				else if(difference>432000000&&difference<=518400000)
				bday.setDayLeft(6);
				else if(difference>518400000&&difference<=604800000)
				bday.setDayLeft(7);
				list.add(bday);
			}
		}
	 }catch(Exception e)
	 {
		 e.printStackTrace();
		 System.out.println("exception:::"+e);
	 }
		return list;
	}
public List<EmployeeAnniversaryList> empAnniversaryFetch() throws Exception {
		
		Iterator<Registration> itr=accountdao.empAnniversaryFetch();
		List<EmployeeAnniversaryList> list=new ArrayList<>();
	 try{
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		while(itr.hasNext()){
			EmployeeAnniversaryList empAnniversary=new EmployeeAnniversaryList();
			Registration reg=itr.next();
			Calendar dbCalendar=Calendar.getInstance();
			dbCalendar.setTimeInMillis(reg.getJoiningDay());
			dbCalendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR) );
			long difference=dbCalendar.getTimeInMillis()-calendar.getTimeInMillis();
			if(difference>=0&&difference<=604800000)
			{
				empAnniversary.setEmpName(reg.getName());
				if(difference<=86400000)
				empAnniversary.setDayLeft(1);
				else if(difference>86400000&&difference<=172800000)
				empAnniversary.setDayLeft(2);
				else if(difference>172800000&&difference<=259200000)
				empAnniversary.setDayLeft(3);
				else if(difference>259200000&&difference<=345600000)
				empAnniversary.setDayLeft(4);
				else if(difference>345600000&&difference<=432000000)
				empAnniversary.setDayLeft(5);
				else if(difference>432000000&&difference<=518400000)
				empAnniversary.setDayLeft(6);
				else if(difference>518400000&&difference<=604800000)
				empAnniversary.setDayLeft(7);
				list.add(empAnniversary);
			}
		}
	 }catch(Exception e)
	 {
		 e.printStackTrace();
		 System.out.println("exception:::"+e);
	 }
		return list;
	}

public Address getAddress(int id) {
	
	return accountdao.getAddress(id);
}
	
}
