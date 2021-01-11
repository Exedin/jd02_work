package it.academy.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "languageServlet", urlPatterns = "/languages")
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> languages= List.of("RU", "BY","EN");
        req.setAttribute("languages", languages);
        req.getRequestDispatcher("/jsp/languages.jsp").forward(req,resp);
    }
}
