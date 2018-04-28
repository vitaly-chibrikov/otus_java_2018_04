package com.javapapers.java.instrumentation;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

//this class will be registered with instrumentation agent
public class DurationTransformer implements ClassFileTransformer {
	public byte[] transform(ClassLoader loader, String className,
			Class classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		byte[] byteCode = classfileBuffer;

		// since this transformer will be called when all the classes are
		// loaded by the classloader, we are restricting the instrumentation
		// using if block only for the Lion class
		if (className.equals("com/javapapers/java/instrumentation/Lion")) {
			System.out.println("Instrumenting......");
			try {
				ClassPool classPool = ClassPool.getDefault();
				CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(
						classfileBuffer));
				CtMethod[] methods = ctClass.getDeclaredMethods();
				for (CtMethod method : methods) {
					method.addLocalVariable("startTime", CtClass.longType);
					method.insertBefore("startTime = System.nanoTime();");
					method.insertAfter("System.out.println(\"Execution Duration "
							+ "(nano sec): \"+ (System.nanoTime() - startTime) );");
				}
				byteCode = ctClass.toBytecode();
				ctClass.detach();
				System.out.println("Instrumentation complete.");
			} catch (Throwable ex) {
				System.out.println("Exception: " + ex);
				ex.printStackTrace();
			}
		}
		return byteCode;
	}
}
