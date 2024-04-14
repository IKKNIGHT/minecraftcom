package org.example.ikknight.templatep.utils;

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
    public String addFields(String status, String players){return "SERVER "+status+", "+players+"online";}
    public String getMessage() {
        return M;
    }
}
