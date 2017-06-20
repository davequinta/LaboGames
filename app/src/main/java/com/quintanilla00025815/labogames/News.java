package com.campos00050515.tabbsexample;

/**
 * Created by DOSLAP on 20/06/2017.
 */

public class News {
    int idNoticia, idJuego;
    String titulo, subtitulo, imgNoticia, desnoticia;

    public News(){
    }

    public News(int IdNoticia, int idJuego, String titulo, String subtitulo, String imgNoticia, String desnoticia){
        this.idNoticia= idNoticia;
        this.idJuego = idJuego;
        this.titulo = titulo;
        this.subtitulo= subtitulo;
        this.imgNoticia = imgNoticia;
        this.desnoticia = desnoticia;
    }

    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getImgNoticia() {
        return imgNoticia;
    }

    public void setImgNoticia(String imgNoticia) {
        this.imgNoticia = imgNoticia;
    }

    public String getDesnoticia() {
        return desnoticia;
    }

    public void setDesnoticia(String desnoticia) {
        this.desnoticia = desnoticia;
    }
}
