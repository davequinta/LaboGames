package com.quintanilla00025815.labogames;

/**
 * Created by David on 20-Jun-17.
 */

public class Player {
    private int idJugador;
    private String nomJugador;
    private String imgJugador;
    private String nikcname;

    public Player(int idJugador, String nomJugador,String imgJugador, String nikcname) {
        this.idJugador = idJugador;
        this.nomJugador = nomJugador;
        this.imgJugador = imgJugador;
        this.nikcname = nikcname;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNomJugador() {
        return nomJugador;
    }

    public void setNomJugador(String nomJugador) {
        this.nomJugador = nomJugador;
    }

    public String getImgJugador() {
        return imgJugador;
    }

    public void setImgJugador(String imgJugador) {
        this.imgJugador = imgJugador;
    }

    public String getNikcname() {
        return nikcname;
    }

    public void setNikcname(String nikcname) {
        this.nikcname = nikcname;
    }
}
