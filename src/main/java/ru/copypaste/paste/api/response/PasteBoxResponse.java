package ru.copypaste.paste.api.response;

public class PasteBoxResponse {
    private String data;
    private boolean isPublic;

    public PasteBoxResponse(String data, boolean isPublic) {
        this.data = data;
        this.isPublic = isPublic;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
