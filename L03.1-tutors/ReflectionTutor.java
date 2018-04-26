import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static util.Logger.log;


import org.junit.Test;

public class ReflectionTutor {

	@Test
	public void testReflection() {
		
	}
	
	public void showConstructors(Class clazz) {
		// list of constructors
		Constructor[] constructors = clazz.getConstructors();
		for (Constructor constr:clazz.getConstructors()) {
			StringBuilder sb = new StringBuilder();
			for (Class param: constr.getParameterTypes()) {
				if (sb.length()>0) sb.append(", ");
				sb.append(param.getSimpleName());
			}
			sb.insert(0, "constructor: "+constr.getName()+"(");
			sb.append(")");
			log(sb.toString());
		}
		log("SuperClass: "+clazz.getSuperclass().getSimpleName());
	}
	
	@Test
	public void testShowConstractors() {
		showConstructors(PrintStream.class);
		showConstructors(String.class);
	}
	
	@Test
	public void testInvoke() {
		//no paramater
		Class noparams[] = {};
	 
		//String parameter
		Class[] paramString = new Class[1];	
		paramString[0] = String.class;
	 
		//int parameter
		Class[] paramInt = new Class[1];	
		paramInt[0] = Integer.TYPE;

		try {
			// load the AppTest at runtime
			Class<?> cls = Class.forName("ExampleClass");
			//cls = ExampleClass.class;
			Object obj = cls.newInstance();

			// list all methods
			Method[] methods = cls.getMethods();
			for (Method method: methods) {
				if (method.getDeclaringClass()==cls) {
					log(method.getName()+"method: "+method.toGenericString());
					Annotation[] annotations = method.getAnnotations();
					for (Annotation annotation: annotations) {
						log("uses annotation: "+annotation.annotationType().getName());
					}
				}
			}
			
			// call the printIt method
			Method method = cls.getDeclaredMethod("printIt", noparams);
			method.invoke(obj, null);
			
			// find for annotation and print it
			ExampleClass.MyAnnotation annotation = method.getAnnotation(ExampleClass.MyAnnotation.class);
			boolean myAnnotation = method.isAnnotationPresent(ExampleClass.MyAnnotation.class);
			log("my annotation name="+annotation.name());

			// call the printItString method, pass a String param
			method = cls.getDeclaredMethod("printItString", paramString);
			method.invoke(obj, "Luxoft");

			// call the printItInt method, pass a int param
			method = cls.getDeclaredMethod("printItInt", paramInt);
			method.invoke(obj, 123);

			// call the setCounter method, pass a int param
			method = cls.getDeclaredMethod("setCounter", paramInt);
			method.invoke(obj, 999);

			// call the printCounter method
			method = cls.getDeclaredMethod("printCounter", noparams);
			method.invoke(obj, null);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
			
}
