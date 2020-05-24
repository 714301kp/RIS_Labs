package com.aleksandrova.sessions;

import com.aleksandrova.entities.Library;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/library-servlet"})
public class LibraryServlet extends HttpServlet {

    @EJB
    private Libraries libraries;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String button = req.getParameter("button");
        if (button.equals("count_btn")) {
            List<Library> librariesList = libraries.getLibrariesByCount();
            doResp(resp, librariesList);
        }
        if (button.equals("status_btn")) {
            List<Library> librariesList = libraries.getLibrariesByStatus(req.getParameter("status"));
            doResp(resp, librariesList);
        }
    }

    private void doResp(HttpServletResponse resp, List<Library> libraries) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\"/>\n" +
                "    <title>Libraries</title>\n" +
                "</head>\n" +
                "<body>\n");
        if (!libraries.isEmpty()) {
            for (Library library : libraries) {
                writer.println("<br>" +
                        "Library status: " + library.getStatus()
                        + "<br> Name: " + library.getName()
                        + "<br> Readers count: " + library.getReadersCount()
                        + "<br/>"+
                        "</body>\n" +
                        "</html>");
            }
        } else {
            writer.println("Libraries not found!" +
                    "<br/>\n" +
                    "    <br/>\n" +
                    "    Find libraries by status:\n" +
                    "    <input name=\"status\"/>\n" +
                    "    <button type=\"submit\" name=\"button\" value=\"status\">Find</button>\n" +
                    "    <br/>\n" +
                    "    <br/>\n" +
                    "    <br/>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
        }


    }
}
