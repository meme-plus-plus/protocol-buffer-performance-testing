# Protocol Buffers Performance Testing

A simple utility to run and compare performance measurements between Google's [Protocol Buffer](http://www.dropwizard.io/1.0.2/docs/)  serialization and Java's internal mechanisms.


### Prerequisites

You will need a JDK (At least 1.8 or above) and Maven installed to run this project.


### Installing

With maven installed on your system, you just need to download the project's dependencies. 

```
mvn clean install
```

In this sample project we have already generated the java class from Proto's compiler and dropped it in the project. This is for <b>addressBook.proto</b> and <b>AddressBookProtos.java</b> respectively.


## Running the tests
To run the provided performance measurements, after installing the related Maven dependencies, the simplest suggestion is to simply run the project as a Java application with <b>App.java</b> being your main class. I did this through Eclipse, but any IDE should have this feature. Currently, the results of your measurements will be streamed to the command line.

For additional information on protocol buffer installation and general project setup, you can check out my video or article on the subject:

* [Video Overview](TODO)

* [Article Overview](TODO)


## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

