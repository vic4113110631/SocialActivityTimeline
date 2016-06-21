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
import java.util.Calendar;

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

    public void setEventList(ArrayList<Event> eventList) {
        EventList = eventList;
    }

    public ArrayList<Event> eventSelect(ArrayList<Event> eventList, String[] clubList) {
        ArrayList<Event> selcetEventList = new ArrayList<Event>();

        for(Event event : eventList)
        {
            for(String club : clubList)
            {
                if(event.getType().equals(Type.valueOf(club)))
                {
                    selcetEventList.add(event);
                    break;
                }
            }
        }

        if(selcetEventList.isEmpty()){
            return eventList;
        }
        return selcetEventList;
    }

    public String toJson(ArrayList<Event> eventList) throws ParseException {
        /*
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
        */
        /*
        StringBuffer s = new StringBuffer("{ \"events\": [");
        //return "{ \"events\": [ { \"media\": { \"url\": \"http://gec.nuk.edu.tw/ezfiles/1/1001/pictures/372/part_3773_9185833_91240.jpg\" }, \"start_date\": { \"year\": \"2016\", \"month\": \"9\", \"day\": \"24\" }, \"text\": { \"headline\": \"<a href ='/EventInfoServlet.do?id=0' >2016Join The World青年領袖民主營</a>\", \"text\": \"(代表台灣免費參加國際會議)二天一夜的課程，與全國最優秀的青年領袖們一起向最頂尖的講者請益國際民主事務潛能。課程主題包括『培養鍛鍊團隊溝通』、『自我價值成長』、『如何參與國際事務』、『外交官看世界』、『大師觀點--國際政治經濟的脈動以及實際與外籍青年互動的『國際之夜』等，課程精彩豐富，是絕對不能錯過的擴展國際視野營隊！！\" } }, { \"media\": { \"url\": \"https://scontent-tpe1-1.xx.fbcdn.net/v/t1.0-9/12347900_496405827200968_672082238145456609_n.png?oh=662382e0449427d87dfff155283a6cb5&oe=57CE21F0\" }, \"start_date\": { \"year\": \"2016\", \"month\": \"6\", \"day\": \"16\" }, \"text\": { \"headline\": \"<a href ='/EventInfoServlet.do?id=1' >薈萃坊企業參訪阿默蛋糕工廠</a>\", \"text\": \"感謝 孫寶年教授幫忙介紹，這學期薈萃坊團隊有幸能參觀 阿默蛋糕企業工廠！（聽說阿默品管十分嚴苛常規都是拒絕參觀的！）\" } }, { \"media\": { \"url\": \"https://scontent-tpe1-1.xx.fbcdn.net/l/t31.0-8/s960x960/13418596_1163327487031344_3758416313258538140_o.jpg\" }, \"start_date\": { \"year\": \"2016\", \"month\": \"6\", \"day\": \"16\" }, \"text\": { \"headline\": \"<a href ='/EventInfoServlet.do?id=1' >★★★2016鷗海YO演唱會~~</a>\", \"text\": \"18:00 在育樂館前X型廣場 2016海大畢業演唱會\" } } ] }";
        for(Event event: eventList){
            StringBuffer ss = new StringBuffer("{ \"media\": { \"url\": \"");
            ss.append(event.getImgPath());
            ss.append("\" }, \"start_date\": { \"year\": \"");
            ss.append(String.valueOf(event.getCalendar().get(Calendar.YEAR)));
            ss.append("\", \"month\": \"");
            ss.append(String.valueOf(event.getCalendar().get(Calendar.MONTH)));
            ss.append("\", \"day\": \"");
            ss.append(String.valueOf(event.getCalendar().get(Calendar.DATE)));
            ss.append("\" }, \"text\": { \"headline\": \"<a href ='/EventInfo.do?id=");
            ss.append(String.valueOf(event.getId()));
            ss.append("'>");
            ss.append(event.getName());
            ss.append("</a>\", \"text\": \"");
            ss.append(event.getPreview());
            ss.append("\"}},");
            s.append(ss);
        }
        s.deleteCharAt(s.length()-1);
        s.append("]}");
        */
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        JSONObject eventsTop = new JSONObject();
        JSONArray events = new JSONArray();
        for (Event event : eventList){
            JSONObject eventObject = new JSONObject();

            JSONObject media = new JSONObject();
            media.put("url", "ImageServlet.do?ImageName=" + event.getImgPath());

            JSONObject start_date = new JSONObject();
            start_date.put("year", event.getCalendar().get(Calendar.YEAR));
            start_date.put("month", event.getCalendar().get(Calendar.MONTH) + 1);
            start_date.put("day", event.getCalendar().get(Calendar.DATE));

            JSONObject text = new JSONObject();
            text.put("headline", "<a href = EventInfo.do?id=" + event.getId() + ">" + event.getName() + "</a>");
            text.put("text", event.getPreview());

            eventObject.put("media", media);
            eventObject.put("start_date", start_date);
            eventObject.put("text", text);
            events.add(eventObject);
        }
        eventsTop.put("events", events);

        return gson.toJson(eventsTop);
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

    public void wirteFile(ArrayList<Event> eventList) throws ParseException, IOException {
        JSONParser parser = new JSONParser();

        JSONArray jsonArray = new JSONArray();
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        if(eventList.isEmpty()){
            System.out.println("EventList is empty");
        }

        for(int i = 0; i < eventList.size(); i++) {
            String strJson = gson.toJson(eventList.get(i));
            JSONObject jsonObject = (JSONObject) parser.parse(strJson);
            jsonArray.add(jsonObject);
        }

        String path = (String) getServletContext().getAttribute("EventFilePath");
        String realPath = getServletContext().getRealPath(path);

        File file = new File(realPath);

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),  "UTF-8"));
        writer.write("\uFEFF");
        writer.write(gson.toJson(jsonArray));

        writer.flush();
        writer.close();

    }
}
