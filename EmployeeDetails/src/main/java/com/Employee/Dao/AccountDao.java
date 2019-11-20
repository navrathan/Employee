package com.Employee.Dao;

import java.util.Iterator;
import com.Employee.Pojo.*;

public interface AccountDao 
{
	Response registrationUser(Registration registration);

	Response delete(long empId);
	
	Response login(Registration login);

	Iterator<Registration> birthdayFetch();
	
	Iterator<Registration> empAnniversaryFetch();

	Address getAddress(int id);

	Registration update(long empId);
	
	Response update1(Registration update);
}
