package com.protobuf.tutorial.pojo.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.protobuf.tutorial.pojo.AddressBookProtos.Person.PhoneType;

@SuppressWarnings("serial")
public class AddressBookJava implements Serializable {
	
	List<AddressBookJava.Person> people = new ArrayList<Person>();

	public List<Person> getPeople() {
		return people;
	}


	public void setPeople(List<Person> people) {
		this.people = people;
	}


	static public class Person implements Serializable  {
		
		public String name;
		public int id;
		public String email;
		public PhoneNumber phoneNumber;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}

		public PhoneNumber getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(PhoneNumber phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		
	}
	
	
	static public class PhoneNumber implements Serializable  {
		
		String number;
		PhoneType type;
		
		public PhoneType getType() {
			return type;
		}
		
		public void setType(PhoneType type) {
			this.type = type;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}
		
	}
	
}

