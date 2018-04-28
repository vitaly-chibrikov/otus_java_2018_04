package com.javapapers.java.instrumentation;

import java.lang.instrument.Instrumentation;

public class DurationAgent {

	// for all the class loaded, premain will be called
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("Executing premain.........");
		inst.addTransformer(new DurationTransformer());
	}
}
