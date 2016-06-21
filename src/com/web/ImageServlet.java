package com.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by WeiRenChen on 2016/6/20.
 */

public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        String imageName = (String) request.getParameter("ImageName");
        //System.out.println(imageName);

        ServletContext context= getServletContext();
        // Get the absolute path of the image
        String filename = context.getRealPath("/WEB-INF/Event/images/" + imageName);
        //System.out.println(filename);
        // retrieve mimeType dynamically
        String mime = context.getMimeType(filename);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setContentType(mime);
        File file = new File(filename);
        response.setContentLength((int)file.length());

        FileInputStream fileInputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();

        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = fileInputStream.read(buf)) >= 0) {
            outputStream.write(buf, 0, count);
        }
        outputStream.close();
        fileInputStream.close();
        //
    }
}
