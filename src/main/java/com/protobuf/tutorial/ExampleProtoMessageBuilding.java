package com.protobuf.tutorial;

import com.protobuf.tutorial.pojo.AddressBookProtos.Person;

public class ExampleProtoMessageBuilding {

	public static void main(String args[]) {
		
		//Build Basic Proto object
		Person johnDoe = Person.newBuilder().setId(1234).setName("John Doe").setEmail("jdoe@example.com")
				.addPhones(Person.PhoneNumber.newBuilder().setNumber("555-432-1111").setType(Person.PhoneType.HOME))
				.build();
		
		
		System.out.println(johnDoe.toString()); //Prints a human-readable json string
	}

}
