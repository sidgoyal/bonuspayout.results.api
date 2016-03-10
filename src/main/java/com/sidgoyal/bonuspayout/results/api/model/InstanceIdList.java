package com.sidgoyal.bonuspayout.results.api.model;

import java.util.Set;

public class InstanceIdList {
	Set<String> instanceIds;

	public InstanceIdList(Set<String> jobInstances) {
		this.instanceIds = jobInstances;
	}
	
	public InstanceIdList(){}

	public Set<String> getInstanceIds() {
		return instanceIds;
	}

	public void setInstanceIds(Set<String> instanceIds) {
		this.instanceIds = instanceIds;
	}

	@Override
	public String toString() {
		return "InstanceIdList [instanceIds=" + instanceIds + "]";
	}
	
}
