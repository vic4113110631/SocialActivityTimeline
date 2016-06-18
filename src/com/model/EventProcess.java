package com.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by WeiRenChen on 2016/6/13.
 */

public class EventProcess{
    private static ArrayList<Event> EventList;
    private static ServletContext context;

    /* Called by Listener */
    public static void setServletContext(ServletContext context){
        EventProcess.context = context;
    }
    /* Use this method to access context from any location */
    public static ServletContext getServletContext(){
        return EventProcess.context;
    }

    public EventProcess (){
        EventList = new ArrayList<Event>();
    }


    public static ArrayList<Event> getEventList() {
        return EventList;
    }

    public void setEventList(ArrayList<Event> eventList) {
        EventList = eventList;
    }

    public ArrayList<Event> eventSelect(ArrayList<Event> eList, String[] clubList) {
        return eList;
    }

    public String toJson(ArrayList<Event> eventList) throws ParseException {
        JSONParser parser = new JSONParser();

        JSONArray jsonArray = new JSONArray();
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        for(int i = 0; i < eventList.size(); i++) {
            String strJson = gson.toJson(eventList.get(i));
            JSONObject jsonObject = (JSONObject) parser.parse(strJson);
            jsonArray.add(jsonObject);
        }
        System.out.println(gson.toJson(jsonArray));
        return gson.toJson(jsonArray);
    }

    public Boolean writeEvent(Event event) throws FileNotFoundException {

        /*-----------------------Get file realPath-----------------------*/
        String path = (String) getServletContext().getAttribute("EventFilePath");
        String realPath = getServletContext().getRealPath(path);
            /*----------------測試輸出----------------*/
            //System.out.println(realPath);
            /*----------------測試輸出----------------*/
        /*-----------------------End get file realPath-----------------------*/

        try {
            /*
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader read = new BufferedReader(inputStreamReader);
             */
            /*-----------------------Start read json file-----------------------*/
            File f  = new File(realPath);
            BufferedReader bufferedReader = new BufferedReader(new UnicodeReader(new FileInputStream(f), "UTF-8" ));
            StringBuilder strBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                /*
                if (line.startsWith("\uFEFF") ) {
                    line = line.substring(1);
                }
                */
                strBuilder.append(line);
            }
            bufferedReader.close();
            /*-----------------------End read json file-----------------------*/

            JSONParser parser = new JSONParser();
            //reference: http://stackoverflow.com/questions/18174064/value-of-type-java-lang-string-cannot-be-converted-to-jsonarray
           /*----------------測試輸出----------------*/
            //System.out.println(strBuilder.toString());
            /*----------------測試輸出----------------*/
            JSONArray jsonArray = (JSONArray) parser.parse(strBuilder.toString());

            /*--------------------測試輸出--------------------*/
            /*
            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String type  = (String) jsonObject.get("type");
                String location = (String) jsonObject.get("location");
                System.out.println(type);
                System.out.println(location);
            }
            */
            /*--------------------測試輸出--------------------*/
            //System.out.println(jsonArray.toJSONString());

            //reference: http://stackoverflow.com/questions/4147012/can-you-avoid-gson-converting-and-into-unicode-escape-sequences
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            String strJson = gson.toJson(event);
            /*----------------測試輸出----------------*/
            //System.out.println(strJson);
            /*----------------測試輸出----------------*/
            JSONObject jsonObject = (JSONObject) parser.parse(strJson);
            jsonArray.add(jsonObject);
            /*----------------測試輸出----------------*/
            //System.out.println(gson.toJson(jsonArray));
            /*----------------測試輸出----------------*/

            File file = new File(realPath);

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),  "UTF-8"));
            writer.write("\uFEFF");
            writer.write(gson.toJson(jsonArray));

            writer.flush();
            writer.close();
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }


}
