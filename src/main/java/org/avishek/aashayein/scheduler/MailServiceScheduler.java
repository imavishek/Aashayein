/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.scheduler;
 * @FileName MailServiceScheduler.java
 * @CreatedDate 02-Feb-2019
 * Modified by @author avishekdas last on 2019-02-02 21:11:46
 */

package org.avishek.aashayein.scheduler;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MailServiceScheduler {

	/*
	 * This method will run in every 5 second delay of previous task completion and
	 * check the RegisterdMail column and send mail if this column contain 0.
	 */
	//@Scheduled(fixedDelay = 5000)
	public void sendRegistrationMail() throws InterruptedException {

		System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
		
	}

}
