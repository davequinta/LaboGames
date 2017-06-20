package com.quintanilla00025815.labogames;

/**
 * Created by David on 20-Jun-17.
 */

public class ImagesJuego {
    private int idImg;
    private String url;
    private int idJuego;


    public ImagesJuego(int idImg, String url, int idJuego) {
        this.idImg = idImg;
        this.url = url;
        this.idJuego = idJuego;
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
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }
}
