package com.protobuf.tutorial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.protobuf.tutorial.pojo.AddressBookProtos.AddressBook;
import com.protobuf.tutorial.pojo.java.AddressBookJava;

public class App {
	
	@FunctionalInterface
	public interface TimestampRetriever { Long getTimestamp(); }

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//Quick performance test
//		printTimestampsForJavaSerialization();
//		printTimestampsForProtoSerializaton();
		
		//Perform load testing performance
		System.out.println("Proto Serialization...");
		printStatistics(() -> measureTimeForProtoSerialization());
		
		System.out.println("Proto Deserialization...");
		printStatistics(() -> measureTimeForProtoDerialization());
		
		System.out.println("Java Serialization...");
		printStatistics(() -> measureTimeForJavaSerialization());
		
		System.out.println("Java Deserialization...");
		printStatistics(() -> measureTimeForJavaDeserializaton());

	}
	
	public static void printStatistics(TimestampRetriever retriever) {
		List<Long> results = getTimestamps(() -> retriever.getTimestamp());
		Long totalSummation = results.stream().collect(Collectors.summingLong(Long::longValue));
		
		System.out.println("Results: " + results);

		
		System.out.println("Min: " +  Collections.min(results));
		System.out.println("Max: " +  Collections.max(results));
		
		System.out.println("Mean: " + (double) (totalSummation / results.size()));
		System.out.println("Mode: " + mode(results));
		System.out.println("Median: " + median(results));
	}
	

	
	public static void printTimestampsForProtoSerializaton() throws IOException {
		System.out.println("Proto Serialization Time: " + measureTimeForProtoSerialization());
		System.out.println("Proto Deserialization Time: " + measureTimeForProtoDerialization());
	}
	
	public static void printTimestampsForJavaSerialization() throws IOException, ClassNotFoundException {
		System.out.println("Java Serializaton Time: " + measureTimeForJavaSerialization());
		System.out.println("Java Deserialization Time: " + measureTimeForJavaDeserializaton());
	}
	
	public static Long measureTimeForJavaDeserializaton()  {
		try {
			FileInputStream fileIn = new FileInputStream(".\\data\\java.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			Long start = Instant.now().toEpochMilli();
	        AddressBookJava output = (AddressBookJava) in.readObject();
	        Long totalMiliseconds = (Instant.now().toEpochMilli() - start);
	        
			in.close();
			return totalMiliseconds;		
		} catch (Exception exe) {
			throw new RuntimeException(exe);
		}
	}
	
	public static Long measureTimeForJavaSerialization()  {
		try {
			FileOutputStream fileOut = new FileOutputStream(".\\data\\java.txt");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
			AddressBookJava addressBookJava = AddressBookData.getAddressBookJavaFromFile();
			
			Long start = Instant.now().toEpochMilli();
			out.writeObject(addressBookJava);
	        Long totalMiliseconds = (Instant.now().toEpochMilli() - start);

			out.close();
			return totalMiliseconds;			
		} catch(Exception exe) {
			throw new RuntimeException(exe);
		}

	}
	
	public static Long measureTimeForProtoSerialization()  {
		try {
			AddressBook addressBook = AddressBookData.getAddressBookFromFile();
			
			Long start = Instant.now().toEpochMilli();
			addressBook.writeTo(new FileOutputStream(".\\data\\proto.txt"));
	        Long totalMiliseconds = (Instant.now().toEpochMilli() - start);

			return totalMiliseconds;			
		} catch(Exception exe) {
			throw new RuntimeException(exe);
		}

	}
	
	public static Long measureTimeForProtoDerialization() {
		try {
			Long start = Instant.now().toEpochMilli();
			AddressBook.parseFrom(new FileInputStream(".\\data\\proto.txt"));
			
	        Long totalMiliseconds = (Instant.now().toEpochMilli() - start);
			return totalMiliseconds;
		} catch(Exception exe) {
			throw new RuntimeException(exe);
		}
	}
	
	public static List<Long> getTimestamps(TimestampRetriever retriever) {
		List<Long> timestamps = new ArrayList<Long>();
		
		for(int i = 0; i < 100; i++) {
			timestamps.add(retriever.getTimestamp());
		}
		return timestamps;
	}
	
	
	//Statistical methods
	public static Long mode(List<Long> numbers) {
	    Long maxValue = 0L, maxCount = 0L;

	    for (int i = 0; i < numbers.size(); ++i) {
	        Long count = 0L;
	        for (int j = 0; j < numbers.size(); ++j) {
	            if (numbers.get(j) == numbers.get(i)) ++count;
	        }
	        if (count > maxCount) {
	            maxCount = count;
	            maxValue = numbers.get(i);
	        }
	    }
	    return maxValue;
	}
	
	public static double median(List<Long> numbers) {
		Collections.sort(numbers);
	   return numbers.get(numbers.size()/2);
	}
}
