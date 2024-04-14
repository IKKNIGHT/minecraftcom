package org.example.ikknight.templatep.utils;

import static org.example.ikknight.templatep.Main.ServerRunTime;
import static org.example.ikknight.templatep.Main.webServerRunTime;

public class Message {
    String M="";
    public Message(String Message){
        M = Message;
    }
    public void add(String log){
        M = M+log;
    }
    public void clear(){
        M="";
    }
    public String addFields(String status, String players){
        webServerRunTime +=5; // interval
        ServerRunTime = webServerRunTime+10; // init delay
        return status+", And "+players+" players online";
    }
    public String getMessage() {
        return M;
    }
}
