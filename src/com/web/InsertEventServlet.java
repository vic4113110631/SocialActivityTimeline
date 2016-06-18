package com.web;

import com.model.Event;
import com.model.EventProcess;
import com.model.Type;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by WeiRenChen on 2016/6/13.
 */
public class InsertEventServlet extends HttpServlet {
    private String title;
    private Type type;
    private String date;
    private String time;
    private Calendar calendar;
    private String preview;
    private String content;
    private String location;
    private String ImgPath;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8"); // 先指定輸出的編碼
        PrintWriter out = response.getWriter(); // 再拿到輸出对象
        response.setContentType("text/html;charset=UTF-8");

        this.content = request.getParameter("content");
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);

        if(isMultiPart){
            ServletFileUpload upload = new ServletFileUpload();
            upload.setHeaderEncoding("UTF-8");
            try {
                FileItemIterator iterator = upload.getItemIterator(request);
                while(iterator.hasNext()){
                    FileItemStream item = iterator.next();
                    if(item.isFormField()){
                        //do field specific process
                        String fieldName = item.getFieldName();
                        InputStream inputStream = item.openStream();
                        byte [] bytes = new byte[inputStream.available()];
                        inputStream.read(bytes);
                        String value = new String(bytes, "UTF-8");//設定UTF-8，重要

                        selectValueByFieldName(fieldName, value);
                        /*----------------測試輸出----------------*/
                        out.println(fieldName + ":" + value + "</br>");
                        /*----------------測試輸出----------------*/
                    }else {
                        //do file up load specific process
                        String path = (String) getServletContext().getAttribute("ImgPath");
                        String realPath = getServletContext().getRealPath(path);

                        /*----------------測試輸出----------------*/
                        /*
                        System.out.println(realPath);
                        System.out.println(realPath + "images");
                        */
                        /*----------------測試輸出----------------*/
                        //call a method to upload file.
                        if(this.processFile(realPath, item)){
                            out.println("file uploaded successfully</br>");
                        }else {
                            out.println("file uploading failed</br>");
                        }
                    }
                }
            }catch (FileUploadException fue){
                fue.printStackTrace();
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" + "HH:mm", Locale.TAIWAN);
        try {
            Date date = dateFormat.parse(this.date + this.time);
            this.calendar = Calendar.getInstance();
            calendar.setTime(date);
            /*----------------測試輸出----------------*/
            /*
            System.out.println(calendar.get(Calendar.YEAR));
            System.out.println(calendar.get(Calendar.MONTH));
            //Months are indexed from 0 not 1 so 10 is November and 11 will be December.
            System.out.println(calendar.get(Calendar.DATE));
            System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
            System.out.println(calendar.get(Calendar.MINUTE));
            */
            /*----------------測試輸出----------------*/

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Event event = new Event(this.type, this.calendar, this.title, this.location, this.preview, this.content, this.ImgPath);
        EventProcess eventProcess = new EventProcess();
        Boolean isWrite = eventProcess.writeEvent(event);
        out.println("event successfully write to .josn file</br>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean processFile(String realPath, FileItemStream item) {
        try {
            File file = new File(realPath + "images");
            if(!file.exists()){
                file.mkdirs();
            }

            File savedFile = new File(file.getAbsolutePath() + File.separator + item.getName());
            this.ImgPath = item.getName();
            FileOutputStream outputStream = new FileOutputStream(savedFile);
            InputStream inputStream = item.openStream();
            int x = 0;
            byte [] bytes = new byte[1024];
            while ((x = inputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, x);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    private void selectValueByFieldName(String fieldName, String value){
        switch (fieldName){
            case "title":
                this.title = value;
                break;
            case "type":
                this.type = Type.valueOf(value);
                break;
            case "date":
                this.date = value;
                break;
            case "time":
                this.time = value;
                break;
            case "location":
                this.location = value;
                break;
            case "content":
                this.content = value;
                break;
            case "preview":
                this.preview = value;
                break;
        }
    }
}
