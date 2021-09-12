package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Scanner;

//localhost:8080/TimeTable/pages/homepage/loadfile
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class LoadFileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        i_Response.setContentType("text/html");
        PrintWriter responseOut=i_Response.getWriter();
        Collection<Part> parts= i_Request.getParts();
        StringBuilder fileContent = new StringBuilder();
        for(Part part:parts)
        {
            fileContent.append(readFromInputStream(part.getInputStream())).append("\n"); //converting the file content into string

        }

    }

    private String readFromInputStream(InputStream inputStream) {
        return new Scanner(inputStream).useDelimiter("\\Z").next();
    }

}
