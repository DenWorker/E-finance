package ru.denvip700.telegram_bot.tests_examples;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MockitoExampleTest {

    // Моки
    @Mock
    private Service service;

    // Тестируемый объект
    @InjectMocks
    private Controller controller;

    // Выполняется перед каждым тестом
    @BeforeEach
    void setUp() {
        // Инициализация моков
        MockitoAnnotations.openMocks(this);
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

    // Тест с моками
    @Test
    void testControllerWithMock() {
        // Настройка мока
        when(service.getData()).thenReturn("Mocked Data");

        // Взаимодействие с контроллером
        String result = controller.getData();

        // Проверка результата
        assertEquals("Mocked Data", result, "Ожидается, что результат будет 'Mocked Data'.");

        // Верификация вызова метода на мок-объекте
        verify(service).getData();


        List<String> mockedList = mock(List.class);

        // действие
        mockedList.add("hello");
        mockedList.clear();

        // проверка
        verify(mockedList).add("hello");
        verify(mockedList).clear();

        // можно указать количество вызовов
        verify(mockedList, times(1)).add("hello");
        verify(mockedList, never()).add("world");

    }

    // Тест с частичными мока (Spy)
    @Test
    void testPartialMockWithSpy() {
        // Создаём реальный объект для частичного мока
        Service realService = new RealService();
        Service spyService = spy(realService);

        // Настройка частичного мока
        when(spyService.getData()).thenReturn("Mocked Data");

        // Взаимодействие с сервисом
        String result = spyService.getData();

        // Проверка результата
        assertEquals("Mocked Data", result, "Ожидается, что результат будет 'Mocked Data'.");

        // Верификация вызова метода на частичном моке
        verify(spyService).getData();
    }

    // Параметризованный тест с моками
    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30})
    void testMockServiceWithDifferentInputs(int input) {
        // Настройка мока
        when(service.getDataById(input)).thenReturn("Mocked Data for " + input);

        // Взаимодействие с сервисом
        String result = service.getDataById(input);

        // Проверка результата
        assertTrue(result.contains("Mocked Data"), "Результат должен содержать 'Mocked Data'.");
    }

    // Вложенные тесты
    @Nested
    class NestedTests {
        @Test
        void nestedTest() {
            Service nestedService = mock(Service.class);
            when(nestedService.getData()).thenReturn("Nested Data");

            String result = nestedService.getData();

            assertEquals("Nested Data", result, "Результат должен быть 'Nested Data'.");
        }
    }

    // Класс Controller для тестирования
    static class Controller {
        private final Service service;

        public Controller(Service service) {
            this.service = service;
        }

        public String getData() {
            return service.getData();
        }
    }

    // Интерфейс Service для тестирования
    interface Service {
        String getData();
        String getDataById(int id);
    }

    // Реализация Service для частичного мока
    static class RealService implements Service {
        @Override
        public String getData() {
            return "Real Data";
        }

        @Override
        public String getDataById(int id) {
            return "Real Data for " + id;
        }
    }
}
