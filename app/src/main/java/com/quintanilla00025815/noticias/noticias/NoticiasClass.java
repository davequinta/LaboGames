package com.quintanilla00025815.noticias.noticias;

/**
 * Created by César on 18/06/2017.
 */

public class NoticiasClass {

        private String titulo, subtitulo,imgNoticia,descNoticia;
        private int idNoticia,idJuego;
        public NoticiasClass() {
        }

        public NoticiasClass(int idNoticia, String titulo, String subtitulo, String imgNoticia, int idJuego, String descNoticia) {
            this.idNoticia= idNoticia;
            this.titulo = titulo;
            this.subtitulo = subtitulo;
            this.imgNoticia = imgNoticia;
            this.idJuego = idJuego;
            this.descNoticia = descNoticia;
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

        public String getDescNoticia() {
            return descNoticia;
        }

        public void setDescNoticia(String descNoticia) {
            this.descNoticia = descNoticia;
        }

}
