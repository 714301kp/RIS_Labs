package com.zabello.sessions;

import com.zabello.entities.RailwayTicket;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(urlPatterns = {"/ticket-servlet"})
public class TicketServlet extends HttpServlet {

    @EJB
    private Tickets tickets;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String button = req.getParameter("button");
        if (button.equals("price_btn")) {
            BigDecimal cost = new BigDecimal(req.getParameter("price"));
            List<RailwayTicket> ticketsList = tickets.getTicketsByCost(cost);
            doResp(resp, ticketsList);
        }
        if (button.equals("number_btn"))
            tickets.getTicketsByTrainNumber(Integer.parseInt(req.getParameter("number")));
    }

    private void doResp(HttpServletResponse resp, List<RailwayTicket> tickets) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\"/>\n" +
                "    <title>Shop</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"TicketsServlet\" method=\"POST\">\n" +
                "    <h1>Tickets</h1>\n" +
                "    <br/>\n" +
                "    Price:\n" +
                "    <input name=\"price\"/>\n" +
                "    <button type=\"submit\" name=\"button\" value=\"price\">Find</button>\n" +
                "    <br/>\n" +
                "    <br/>\n");
        if (!tickets.isEmpty()) {
            for (RailwayTicket ticket : tickets) {
                writer.println("<br>" +
                        "Train number: " + ticket.getTrainNumber()
                        + "<br> Type: " + ticket.getType()
                        + "<br> Service class: " + ticket.getClassOfService()
                        + "<br> Cost: " + ticket.getCost()
                        + "<br/>");
            }

            writer.println("<br/>\n" +
                    "    <br/>\n" +
                    "    Find tickets by train number:\n" +
                    "    (tickets will be written to file tickets.txt)\n" +
                    "    <input name=\"number\"/>\n" +
                    "    <button type=\"submit\" name=\"button\" value=\"number\">Find</button>\n" +
                    "    <br/>\n" +
                    "    <br/>\n" +
                    "    <br/>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
        } else {
            writer.println("Tickets not found!" +
                    "<br/>\n" +
                    "    <br/>\n" +
                    "    Find tickets by train number:\n" +
                    "    (tickets will be written to file tickets.txt)\n" +
                    "    <input name=\"number\"/>\n" +
                    "    <button type=\"submit\" name=\"button\" value=\"number\">Find</button>\n" +
                    "    <br/>\n" +
                    "    <br/>\n" +
                    "    <br/>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }
}
