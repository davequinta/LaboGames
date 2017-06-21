package com.campos00050515.tabbsexample;

/**
 * Created by DOSLAP on 20/06/2017.
 */

public class User {
    int idUsuario;
    String nomUsuario, claveUsuario, imgUsuario;

    public User(){
    }

    public User(int idUsuario, String nomUsuario, String claveUsuario, String imgUsuario){
        this.idUsuario = idUsuario;
        this.nomUsuario = nomUsuario;
        this.claveUsuario = claveUsuario;
        this.imgUsuario = imgUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getImgUsuario() {
        return imgUsuario;
    }

    public void setImgUsuario(String imgUsuario) {
        this.imgUsuario = imgUsuario;
    }
}
