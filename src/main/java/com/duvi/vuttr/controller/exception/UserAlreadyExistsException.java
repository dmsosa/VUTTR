package com.duvi.vuttr.controller.exception;

public class UserAlreadyExistsException extends Exception {

    private String login;
    public UserAlreadyExistsException(String login) {
        this.login = login;
    }
    public String getMessage() {
        return "benutzer: \""+login+"\" schon ist !!!";
    }
}
