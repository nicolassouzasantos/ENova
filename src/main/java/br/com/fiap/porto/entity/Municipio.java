package br.com.fiap.porto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.Normalizer;

public class Municipio {

    @JsonProperty("id_municipio")
    private Long idMunicipio;

    private Long codigo;

    private String nome;

    private String uf;

    @JsonProperty("per_hidro")
    private Double perHidro;

    @JsonProperty("per_eolica")
    private Double perEolica;

    @JsonProperty("per_solar")
    private Double perSolar;

    // Construtores
    public Municipio() {
    }

    // Getters e Setters
    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Double getPerHidro() {
        return perHidro;
    }

    public void setPerHidro(Double perHidro) {
        this.perHidro = perHidro;
    }

    public Double getPerEolica() {
        return perEolica;
    }

    public void setPerEolica(Double perEolica) {
        this.perEolica = perEolica;
    }

    public Double getPerSolar() {
        return perSolar;
    }

    public void setPerSolar(Double perSolar) {
        this.perSolar = perSolar;
    }

    public static String formatarNomeCidade(String nomeCidade) {
        if (nomeCidade == null) {
            return null;
        }

        String normalized = Normalizer.normalize(nomeCidade, Normalizer.Form.NFD);
        String minusculas = normalized.toUpperCase();

        return minusculas.trim();
    }

}
