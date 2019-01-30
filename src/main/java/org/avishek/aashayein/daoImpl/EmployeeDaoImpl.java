/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.daoImpl;
 * @FileName EmployeeDaoImpl.java
 * @CreatedDate 28-Jan-2019
 * Modified by @author avishekdas last on 2019-01-28 21:55:00
 */

package org.avishek.aashayein.daoImpl;

import org.avishek.aashayein.entities.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployeeDaoImpl {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public  void show() {
		System.out.println("d"+sessionFactory.getCurrentSession().load(Employee.class, 1));
	}

}
