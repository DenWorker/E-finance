package ru.denvip700.telegram_bot.tests_examples;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class JUnit5ExampleTest {

    // Выполняется перед каждым тестом
    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach: Подготовка перед тестом.");
    }

    // Выполняется после каждого теста
    @AfterEach
    void tearDown() {
        System.out.println("AfterEach: Очистка после теста.");
    }

    // Выполняется перед всеми тестами (статический метод)
    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll: Выполняется перед всеми тестами.");
    }

    // Выполняется после всех тестов (статический метод)
    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll: Выполняется после всех тестов.");
    }

    // Тест с объектами
    @Test
    void testPersonEquality() {
        // Создаём два одинаковых объекта
        Person person1 = new Person("John", 30);
        Person person2 = new Person("John", 30);

        // Проверка равенства объектов
        assertEquals(person1, person2, "Ожидается, что объекты будут равны.");
    }

    // Тест с объектом на проверку null
    @Test
    void testObjectNotNull() {
        Person person = new Person("Jane", 25);

        // Проверка, что объект не равен null
        assertNotNull(person, "Объект не должен быть null.");
    }

    // Тест с объектом на проверку выбрасывания исключения
    @Test
    void testInvalidAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("Invalid", -5); // Конструктор бросает исключение на отрицательный возраст
        }, "Возраст не может быть отрицательным.");
    }

    // Параметризованный тест с объектами
    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30})
    void testPersonAge(int age) {
        Person person = new Person("Test", age);

        // Проверка, что возраст положительный
        assertTrue(person.getAge() > 0, "Возраст должен быть положительным.");
    }

    // Вложенные тесты
    @Nested
    class NestedTests {
        @Test
        void nestedTest() {
            Person person = new Person("Nested", 40);
            assertEquals("Nested", person.getName(), "Имя должно быть 'Nested'.");
        }
    }

    // Класс Person для тестирования
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            if (age < 0) throw new IllegalArgumentException("Возраст не может быть отрицательным.");
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return 31 * name.hashCode() + age;
        }
    }
}
