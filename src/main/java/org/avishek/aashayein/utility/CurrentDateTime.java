/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.utility;
 * @FileName CurrentDateTime.java
 * @CreatedDate 17-Jan-2019
 * Modified by @author avishekdas last on 2019-01-17 00:59:10
 */

package org.avishek.aashayein.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CurrentDateTime {

	public Date getCurrentDateTime() {

		String today = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

		try {
			return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(today);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
