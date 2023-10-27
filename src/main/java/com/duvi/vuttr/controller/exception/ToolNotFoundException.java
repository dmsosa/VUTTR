package com.duvi.vuttr.controller.exception;

public class ToolNotFoundException extends Exception {
    private String identification;

    public static ToolNotFoundException createWith(String identification) {
        return new ToolNotFoundException(identification);
    }
    public static ToolNotFoundException createWith(Long id) {
        return new ToolNotFoundException(id);
    }
    public ToolNotFoundException(String identification) {
        super("Die Tool mit Name: "+ identification +" nicht gefunden war");
        this.identification = identification;
    }
    public ToolNotFoundException(Long id) {
        super("Die Tool mit id: "+id+" nicht gefunden war!");
        this.identification = String.valueOf(id);
    }
    public String getMessage() {
        if (!identification.matches("[^0-9]]")) {
            return "Die Tool mit id: "+identification+" nicht gefunden war!";
        }
        else {
            return "Die Tool mit Name: "+identification+" nicht gefunden war";
        }
    }
}
