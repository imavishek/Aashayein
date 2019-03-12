package org.avishek.aashayein.event;

import org.avishek.aashayein.entities.Employee;
import org.springframework.context.ApplicationEvent;

public class SendResetPasswordEvent extends ApplicationEvent {

	private Employee employee = null;
	private static final long serialVersionUID = 1L;

	public SendResetPasswordEvent(Object source, Employee employee) {
		super(source);
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	@Override
	public String toString() {
		return "Send Reset Password Link Event Occur";
	}

}