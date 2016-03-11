package com.sidgoyal.bonuspayout.results.api.model;

import java.util.List;

/*
 * Wraps the list of InstanceIds for returning proper Json by jackson
 */

public class InstanceIdList {
	List<Long> instanceIds;

	public InstanceIdList(List<Long> jobInstances) {
		this.instanceIds = jobInstances;
	}
	
	public InstanceIdList(){}

	public List<Long> getInstanceIds() {
		return instanceIds;
	}

	public void setInstanceIds(List<Long> instanceIds) {
		this.instanceIds = instanceIds;
	}

	@Override
	public String toString() {
		return "InstanceIdList [instanceIds=" + instanceIds + "]";
	}
	
}
