/**
 * @Project_Name Shopping
 * © @Author avishekdas
 * package org.avishek.shopping.propertyEditor;
 * @File_Name ReplaceSpace.java
 * @Created_Date 15-Oct-2018
 * Modified by @author avishekdas last on 2018-10-15 00:21:00
 */

package org.avishek.shopping.propertyEditor;

import java.beans.PropertyEditorSupport;

public class ReplaceSpaceEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String field) throws IllegalArgumentException{
		field = field.trim().replaceAll("\\s{2,}"," ");
		setValue(field);
	}
}
