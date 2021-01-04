package it.academy.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet (name = "echoServlet", urlPatterns = "/echo")
public class EchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try { String id=req.getSession().getId();
       HttpSession session= req.getSession();
       if (session.getAttribute("timestamp")==null){
           session.setAttribute("timestamp", System.currentTimeMillis());
       }

       final PrintWriter writer = resp.getWriter();
       writer.println("Hello from Echo servlet! HOMEWORK TEST"+ new Date());
       writer.println("my session id="+ id+ " " + session.getAttribute("timestamp"));
        } catch (IOException e) {
          e.printStackTrace();
      }
    }
}
