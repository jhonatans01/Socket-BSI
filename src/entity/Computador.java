/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author jhonatan
 */
public class Computador {

    private Integer id;
    private String host;
    private String usuario;
    private Date dia;
    private Time hora;

    public Computador() {
    }

    public Computador(Integer id, String host, String usuario, Date data, Time hora) {
        this.id = id;
        this.host = host;
        this.usuario = usuario;
        this.dia = data;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

}
