package br.com.fiap.porto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("id_user")
    private Long idUser;

    @JsonProperty("nm_user")
    private String nmUser;

    @JsonProperty("email_user")
    private String emailUser;

    @JsonProperty("cidade_user")
    private String cidadeUser;

    @JsonProperty("uf_user")
    private String ufUser;

    // Construtores
    public User() {
    }

    public User(String nmUser, String emailUser, String cidadeUser, String ufUser) {
        this.nmUser = nmUser;
        this.emailUser = emailUser;
        this.cidadeUser = cidadeUser;
        this.ufUser = ufUser;
    }

    // Getters e Setters
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getCidadeUser() {
        return cidadeUser;
    }

    public void setCidadeUser(String cidadeUser) {
        this.cidadeUser = cidadeUser;
    }

    public String getUfUser() {
        return ufUser;
    }

    public void setUfUser(String ufUser) {
        this.ufUser = ufUser;
    }
}
