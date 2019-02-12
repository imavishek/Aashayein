/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.dto;
 * @FileName StateTO.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 21:53:00
 */

package org.avishek.aashayein.dto;

public class StateTO {

	private Integer stateId;

	private String stateName;

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "StateTO [stateId=" + stateId + ", stateName=" + stateName + "]";
	}

}
