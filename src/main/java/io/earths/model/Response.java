package io.earths.model;

public class Response {
    public Response(boolean okay, String message) {
        this.okay = okay;
        this.message = message;
    }

    boolean okay;
    String message;
    String redirect;

    public boolean isOkay() {
        return okay;
    }

    public void setOkay(boolean okay) {
        this.okay = okay;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
