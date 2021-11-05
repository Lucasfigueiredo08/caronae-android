package com.unifap.caronaeunifap.models.modelsforjson;

public class ChatSendMessageForJson {

    private String message;

    public ChatSendMessageForJson(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
