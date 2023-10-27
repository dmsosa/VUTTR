package com.duvi.vuttr.controller.exception;

public class ToolAlreadyExistsException extends Exception {

    String title;
    public static ToolAlreadyExistsException createWith(String title) {
        return new ToolAlreadyExistsException(title);
    }

    public ToolAlreadyExistsException(String title) {
        this.title = title;
    }

    public String getMessage() {
        return "Tool: "+title+" already exists!";
    }
}
