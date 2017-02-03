package com.orhanobut.hawk.bean;

import java.util.List;

public class SampleBean2 {

	private String name;
	private String field1;
	private int id;

	private List<User> mUsers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getmUsers() {
		return mUsers;
	}

	public void setmUsers(List<User> mUsers) {
		this.mUsers = mUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field1 == null) ? 0 : field1.hashCode());
		result = prime * result + id;
		result = prime * result + ((mUsers == null) ? 0 : mUsers.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SampleBean2 other = (SampleBean2) obj;
		if (field1 == null) {
			if (other.field1 != null)
				return false;
		} else if (!field1.equals(other.field1))
			return false;
		if (id != other.id)
			return false;
		if (mUsers == null) {
			if (other.mUsers != null)
				return false;
		} else if (!mUsers.equals(other.mUsers))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	
	
}
