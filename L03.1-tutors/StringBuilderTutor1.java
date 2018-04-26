
import static org.junit.Assert.*;

import org.junit.Test;

public class StringBuilderTutor1 {

	String [] animals =
        {"Корова", "Гусь", "Кошка", "Собака", "Слон",
        "Заяц", "Змея", "Курица", "Лошадь", "Человек"};
	
	/**
	 * Должен вернуть строку: 
	 * "Список животных: Корова, Гусь, ..., Человек."
	 * Для реализации используйте StringBuilder
	 */
	public String getAnimalsString() {
		StringBuilder builder = new StringBuilder();
		for (String s: animals) {
			if (builder.length()>0) builder.append(", ");
			builder.append(s);
		}
		builder.insert(0, "Список животных: ");
		builder.append(".");
		return builder.toString();
	}
	
	@Test
	public void testGetAnimalsString() {
		String animalsString = getAnimalsString();
		assertEquals("Список животных: Корова, Гусь, Кошка, Собака, Слон, "+
						"Заяц, Змея, Курица, Лошадь, Человек.", animalsString);
	}
	
}
