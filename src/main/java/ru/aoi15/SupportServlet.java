package ru.aoi15;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SupportServlet extends HttpServlet {

    private List<String> phrases = Arrays.asList(
            "У тебя все получится",
            "Продолжай в том же духе",
            "Ты делаешь отличную работу"
    );

    private Random random = new Random();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain; charset=UTF-8"); // Указываем кодировку UTF-8
        response.setCharacterEncoding("UTF-8");

        List<String> phrases = Arrays.asList(
                "У тебя все получится",
                "Продолжай в том же духе",
                "Ты делаешь отличную работу"
        );
        String randomPhrase = phrases.get(new Random().nextInt(phrases.size()));
        response.getWriter().write(randomPhrase); // Используем write вместо print для указания строки с кодировкой
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newPhrase = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        phrases.add(newPhrase);
        response.setContentType("text/plain");
        response.getWriter().print("Фраза добавлена: " + newPhrase);
    }

}
