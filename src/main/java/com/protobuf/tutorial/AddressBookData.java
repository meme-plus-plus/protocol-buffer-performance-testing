package com.protobuf.tutorial;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.protobuf.tutorial.pojo.java.AddressBookJava;
import com.protobuf.tutorial.pojo.AddressBookProtos.AddressBook;
import com.protobuf.tutorial.pojo.AddressBookProtos.Person;
import com.protobuf.tutorial.pojo.AddressBookProtos.Person.PhoneNumber;
import com.protobuf.tutorial.pojo.AddressBookProtos.Person.PhoneType;

class AddressBookData {

	public static AddressBook getAddressBookFromFile() throws IOException {
		BufferedReader br = getBufferedReader();
		String strLine;

		List<Person> people = new ArrayList<Person>();

		while ((strLine = br.readLine()) != null) {
			Person.Builder person = Person.newBuilder();
			String[] tokens = strLine.split(" ");

			person.setId(Integer.valueOf(tokens[0])).setName(tokens[1]).setEmail(tokens[2])
					.addPhones(PhoneNumber.newBuilder().setNumber(tokens[3]).setType(getPhoneType(tokens[4]))).build();

			people.add(person.build());
		}

		br.close();
		return AddressBook.newBuilder().addAllPeople(people).build();
	}
	
	public static AddressBookJava getAddressBookJavaFromFile() throws IOException {
		BufferedReader br = getBufferedReader();
		String strLine;

		List<AddressBookJava.Person> people = new ArrayList<>();

		while ((strLine = br.readLine()) != null) {
			AddressBookJava.Person person = new AddressBookJava.Person();
			
			String[] tokens = strLine.split(" ");

			person.setId(Integer.valueOf(tokens[0]));
			person.setName(tokens[1]);
			person.setEmail(tokens[2]);
			
			AddressBookJava.PhoneNumber phoneNumber = new AddressBookJava.PhoneNumber();
			phoneNumber.setNumber(tokens[3]);
			phoneNumber.setType(getPhoneType(tokens[4]));
			
			person.setPhoneNumber(phoneNumber);

			people.add(person);
		}

		br.close();
		AddressBookJava addressBook = new AddressBookJava();
		addressBook.setPeople(people);
		
		return addressBook;
	}
	
	public static BufferedReader getBufferedReader() throws FileNotFoundException {
		FileInputStream fstream = new FileInputStream("data/inputdata.txt");

		DataInputStream in = new DataInputStream(fstream);
		return new BufferedReader(new InputStreamReader(in));
	}

	public static PhoneType getPhoneType(String type) {
		switch (type) {
		case "mobile":
			return PhoneType.MOBILE;
		case "home":
			return PhoneType.HOME;
		case "work":
			return PhoneType.WORK;
		default:
			return PhoneType.HOME;
		}
	}

}