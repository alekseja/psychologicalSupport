package ru.aoi15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class SupportServletTest {

    private SupportServlet supportServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter responseWriter;

    @BeforeEach
    void setUp() throws Exception {
        // Инициализируем сервлет перед каждым тестом
        supportServlet = new SupportServlet();

        // Мокируем запрос и ответ
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);

        // Создаем StringWriter для перехвата вывода
        responseWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    void testDoGetReturnsRandomPhrase() throws Exception {
        // Вызываем doGet сервлета
        supportServlet.doGet(request, response);

        // Проверяем, что результат содержит одну из ожидаемых фраз
        List<String> expectedPhrases = Arrays.asList(
                "У тебя все получится",
                "Продолжай в том же духе",
                "Ты делаешь отличную работу"
        );

        // Проверяем, что Content-Type установлен корректно
        verify(response).setContentType("text/plain; charset=UTF-8");

        // Проверяем, что одна из фраз содержится в ответе
        String servletResponse = responseWriter.toString();
        assertTrue(expectedPhrases.contains(servletResponse.trim()));
    }
}
