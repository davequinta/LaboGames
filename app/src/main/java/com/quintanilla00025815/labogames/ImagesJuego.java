package com.quintanilla00025815.labogames;

/**
 * Created by David on 20-Jun-17.
 */

public class ImagesJuego {
    private int idImg;
    private String url;
    private int idjuego;


    public ImagesJuego(int idImg, String url) {
        this.idImg = idImg;
        this.url = url;
    }

    public int getIdImg() {
        return idImg;
    }

    public void setIdImg(int idImg) {
        this.idImg = idImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdJuego() {
        return idjuego;
    }

    public void setIdJuego(int idJuego) {
        this.idjuego = idJuego;
    }
}
