package it.academy.servlet;

import it.academy.date.DaoFactory;
import it.academy.date.DatabaseName;
import it.academy.date.ProductSpec;
import it.academy.date.ProductSpecDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

@WebServlet (name="ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    DaoFactory daoFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            daoFactory=DaoFactory.getInstance(DatabaseName.MYSQL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            PrintWriter writer=resp.getWriter();
            ProductSpecDao productSpecDao= daoFactory.getProductSpecDao();
            final List<ProductSpec> productSpecs=productSpecDao.readAll();
            for (ProductSpec product : productSpecs) {
                System.out.println("id="+product.getId()+" name="+product.getProductName()+
                        " details="+product.getProductDetails());
                writer.println("id="+product.getId()+" name="+product.getProductName()+
                        " details="+product.getProductDetails()+ " date="+product.getProductDate());
            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
