package com.javapapers.java.instrumentation;

//to be instrumented java class
public class Lion {
	public void runLion() throws InterruptedException {
		System.out.println("Lion is going to run........");
		Thread.sleep(2000L);
	}

}
