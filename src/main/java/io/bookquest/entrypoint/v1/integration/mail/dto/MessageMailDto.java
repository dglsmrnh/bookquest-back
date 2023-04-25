package io.bookquest.entrypoint.v1.integration.mail.dto;

public class MessageMailDto {

    private Object to;

    private final Object template;

    private Object data;

    public MessageMailDto(Object to, Object data) {
        this.to = to;
        this.template = "7KDV4ZZ4CVM6PFNXTY8CNCQPFRN3";
        this.data = data;
    }

    public Object getTemplate() {
        return template;
    }

    public Object getTo() {
        return to;
    }

    public void setTo(Object to) {
        this.to = to;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
