package org.servlet.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final int LIMIT = 5; 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int offset = (page - 1) * LIMIT;

        try {
            List<Transaction> transactions = DatabaseUtil.getTransactions(offset, LIMIT);
            int totalTransactions = DatabaseUtil.getTotalTransactions();
            int totalPages = (int) Math.ceil((double) totalTransactions / LIMIT);

            out.println("<html><head><title>Home</title></head><body>");
            out.println("<h1>Transaction Details</h1>");
            out.println("<table border='1'><tr><th>ID</th><th>Date</th><th>Amount</th><th>Customer ID</th><th>Payment Method</th></tr>");

            for (Transaction transaction : transactions) {
                out.println("<tr>");
                out.println("<td>" + transaction.getTransactionId() + "</td>");
                out.println("<td>" + transaction.getTransactionDate() + "</td>");
                out.println("<td>" + transaction.getAmount() + "</td>");
                out.println("<td>" + transaction.getCustomerId() + "</td>");
                out.println("<td>" + transaction.getPaymentMethod() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<div>");
            if (page > 1) {
                out.println("<a href='home?page=" + (page - 1) + "'>Previous</a> ");
            }
            if (page < totalPages) {
                out.println("<a href='home?page=" + (page + 1) + "'>Next</a>");
            }
            out.println("</div>");

            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h1>Error fetching transactions</h1>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}