package com.duvi.vuttr.controller.exception;

public class UserNotFoundException extends Exception {

    private String login;
    public UserNotFoundException(String login) {
        this.login = login;
    }
    public String getMessage() {
        return "benutzer: \""+login+"\" nicht gefunden !!!";
    }
}