package com.meyacom.domain;

public class Resources {
	private Integer resourcesId;

	private String resourcesName;

	private String resourcesType;

	private String resourcesUrl;

	private Integer parentId;

	private Integer resourcesStatus;

	public Integer getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(Integer resourcesId) {
		this.resourcesId = resourcesId;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getResourcesType() {
		return resourcesType;
	}

	public void setResourcesType(String resourcesType) {
		this.resourcesType = resourcesType;
	}

	public String getResourcesUrl() {
		return resourcesUrl;
	}

	public void setResourcesUrl(String resourcesUrl) {
		this.resourcesUrl = resourcesUrl;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getResourcesStatus() {
		return resourcesStatus;
	}

	public void setResourcesStatus(Integer resourcesStatus) {
		this.resourcesStatus = resourcesStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((resourcesId == null) ? 0 : resourcesId.hashCode());
		result = prime * result
				+ ((resourcesName == null) ? 0 : resourcesName.hashCode());
		result = prime * result
				+ ((resourcesStatus == null) ? 0 : resourcesStatus.hashCode());
		result = prime * result
				+ ((resourcesType == null) ? 0 : resourcesType.hashCode());
		result = prime * result
				+ ((resourcesUrl == null) ? 0 : resourcesUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resources other = (Resources) obj;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (resourcesId == null) {
			if (other.resourcesId != null)
				return false;
		} else if (!resourcesId.equals(other.resourcesId))
			return false;
		if (resourcesName == null) {
			if (other.resourcesName != null)
				return false;
		} else if (!resourcesName.equals(other.resourcesName))
			return false;
		if (resourcesStatus == null) {
			if (other.resourcesStatus != null)
				return false;
		} else if (!resourcesStatus.equals(other.resourcesStatus))
			return false;
		if (resourcesType == null) {
			if (other.resourcesType != null)
				return false;
		} else if (!resourcesType.equals(other.resourcesType))
			return false;
		if (resourcesUrl == null) {
			if (other.resourcesUrl != null)
				return false;
		} else if (!resourcesUrl.equals(other.resourcesUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resources [resourcesId=" + resourcesId + ", resourcesName="
				+ resourcesName + ", resourcesType=" + resourcesType
				+ ", resourcesUrl=" + resourcesUrl + ", parentId=" + parentId
				+ ", resourcesStatus=" + resourcesStatus + "]";
	}

	

}