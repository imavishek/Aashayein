package org.avishek.aashayein.event;

import org.avishek.aashayein.dto.EmployeeTO;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private EmployeeTO employeeTo = null;;
	private static final long serialVersionUID = 1L;

	public OnRegistrationCompleteEvent(Object source, EmployeeTO employeeTo) {
		super(source);
		this.employeeTo = employeeTo;

	}

	public EmployeeTO getEmployeeTo() {
		return employeeTo;
	}

	@Override
	public String toString() {
		return "On Registration Complete Event Occur";
	}

}
