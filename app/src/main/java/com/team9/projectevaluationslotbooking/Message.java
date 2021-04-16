package com.team9.projectevaluationslotbooking;

public class Message {

    private String to;
    private String from;
    private String msg;

    public Message() {
    }

    public Message(String to, String from, String msg) {
        this.to = to;
        this.from = from;
        this.msg = msg;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
