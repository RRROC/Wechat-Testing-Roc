package com.roc.entity;

public class TextMessage extends BaseMessage {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage() {
        this.setMsgType("text");
    }
}
