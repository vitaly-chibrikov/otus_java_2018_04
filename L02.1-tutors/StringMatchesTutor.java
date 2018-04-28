
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringMatchesTutor  {

	/**
	 * Проверяет корректность адреса электронной почты
	 */
	public boolean checkIsEmail(String email) {
		return email.matches(
			"^[A-Za-z\\.-0-9]{2,}@[A-Za-z\\.-0-9]{2,}\\.[A-Za-z]{2,3}$");
	}
	
	/**
	 *  Убедитесь, что приветствие greeting имеет вид
	 *  Привет, Иван Иванов!
	 *  или
	 *  Привет,Петр Первый!
	 *  т.е. начинается на Привет, заканчивается на !
	 *  и содержит 2 слова для имени и фамилии,
	 *  причем имя и фамилия не короче 3 букв
	 *  и начинаются с большой буквы
	 */
	public boolean checkGreeting(String greeting) {
		return false;
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

	@Test
	public void testCheckIsEmail() {
		assertTrue(checkIsEmail("ivanov@mail.ru"));
		assertTrue(checkIsEmail("ivanov@mail.com.uk"));
		assertFalse(checkIsEmail("ivan ivanov@mail.com.uk"));
		assertFalse(checkIsEmail("ivanov@mailcom"));
	}
}
