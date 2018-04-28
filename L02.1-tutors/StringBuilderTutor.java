
import static org.junit.Assert.*;

import org.junit.Test;

public class StringBuilderTutor {

	String [] animals =
        {"Корова", "Гусь", "Кошка", "Собака", "Слон",
        "Заяц", "Змея", "Курица", "Лошадь", "Человек"};
	
	/**
	 * Метод должен вернуть строку: 
	 * "Список животных: Корова, Гусь, ..., Человек."
	 * Для реализации используйте StringBuilder
	 */
	public String getAnimalsString() {
		return null;
	}
	
	@Test
	public void testGetAnimalsString() {
		String animalsString = getAnimalsString();
		assertEquals("Список животных: Корова, Гусь, Кошка, Собака, Слон, "+
						"Заяц, Змея, Курица, Лошадь, Человек.", animalsString);
	}
	
}
