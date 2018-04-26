
import static org.junit.Assert.*;
import static util.Logger.log;

import org.junit.Test;

public class StringTutor1  {

	/**
	 * Замените все null в assertEquals на true или false
	 */
	@Test
	public void testStringEquals() {
		String s1 = "aaa";
		String s2 = "aaa";
		String s3 = new String("aaa");
		log("адрес объекта s1: "+System.identityHashCode(s1));
		log("адрес объекта s2: "+System.identityHashCode(s2));
		assertEquals(s1==s2, true);
		assertEquals(s1.equals(s2), true);
		log("адрес объекта s3: "+System.identityHashCode(s3));
		assertEquals(s1==s3, false);
		// метод intern() позволяет получить строку из пула строк 
		String s4 = s3.intern();
		log("адрес объекта s4: "+System.identityHashCode(s4));
		assertEquals(s1==s4, true);
		// тестируем пересоздание объекта каждый раз при изменении
		s3 = s3+"bbb";
		log("адрес измененного объекта s3: "+System.identityHashCode(s3));
		s3 = s3.substring(0, 3); // s3 снова "aaa"
		assertEquals(s3==s1, false);
		assertEquals(s3.equals(s1), true);
		assertEquals(s3.intern()==s1, true);
	}
	
	/**
	 *  убедитесь, что приветствие greeting имеет вид
	 *  Привет, Иван Иванов!
	 *  или
	 *  Привет,Петр ѕервый!
	 *  т.е. начинается на Привет, заканчивается на !
	 *  и содержит 2 слова для имени и фамилии,
	 *  причем имя и фамилия не короче 3 букв
	 *  и начинаются с большой буквы
	 */
	public boolean checkGreeting(String greeting) {
		if (!greeting.startsWith("Привет,")) 
			return false;
		if (!greeting.endsWith("!")) 
			return false;
		String name = 
			greeting.substring("Привет,".length(), 
					greeting.length()-1).trim();
		//log("Полное имя: "+name);
		int blankPosition = name.indexOf(" ");
		if (blankPosition<0) return false;
		String firstName = name.substring(0, blankPosition).trim();
		//log("Имя: "+firstName);
		String lastName = name.substring(blankPosition+1).trim();
		//log("Фамилия: "+lastName);
		if (firstName.length()<3) return false;
		if (lastName.length()<3) return false;
		if (!Character.isUpperCase(firstName.charAt(0))) return false;
		if (!Character.isUpperCase(lastName.charAt(0))) return false;
		return true;
	}
	
	@Test
	public void testCheckGreeting() {
		assertTrue(checkGreeting("Привет, Иван Иванов!"));
		assertTrue(checkGreeting("Привет,Петр Первый!"));
		assertTrue(checkGreeting("Привет, Петр Первый!"));
		assertTrue(checkGreeting("Привет, Петр Первый !"));
		
		assertFalse("В начале должно быть слово Привет и запятая",
				checkGreeting("Здравствуйте, Петр Первый!"));
		assertFalse("В конце должен быть восклицательный знак",
				checkGreeting("Привет, Петр Первый"));
		assertFalse("Имя слишком короткое",
				checkGreeting("Привет, Ли Сунь!"));
		assertFalse("Фамилия слишком короткая",
				checkGreeting("Привет, Куй Ли!"));
		assertFalse("Должны быть указаны и имя, и фамилия",
				checkGreeting("Привет, Петр!"));
		assertFalse("Первая буква имени должна быть заглавной", 
				checkGreeting("Привет, петр Первый!"));
		assertFalse("Первая буква фамилии должна быть заглавной",
				checkGreeting("Привет, Петр первый!"));
	}
}
