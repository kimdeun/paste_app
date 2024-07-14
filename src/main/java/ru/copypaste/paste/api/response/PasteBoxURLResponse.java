package ru.copypaste.paste.api.response;

public class PasteBoxURLResponse {
    private String url;

    public PasteBoxURLResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
