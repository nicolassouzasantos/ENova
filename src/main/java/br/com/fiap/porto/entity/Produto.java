package br.com.fiap.porto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Produto {

    @JsonProperty("id_prod")
    private Long idProd;

    @JsonProperty("nm_prod")
    private String nmProd;

    private String tipo;

    private Double tamanho;

    private Double valor;

    @JsonProperty("qtd_estoque")
    private Integer qtdEstoque;

    @JsonProperty("qtd_vendas")
    private Integer qtdVendas;

    @JsonProperty("per_economia")
    private Double perEconomia; // Nova coluna

    // Construtores
    public Produto() {
    }

    // Getters e Setters
    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getNmProd() {
        return nmProd;
    }

    public void setNmProd(String nmProd) {
        this.nmProd = nmProd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getQtdVendas() {
        return qtdVendas;
    }

    public void setQtdVendas(Integer qtdVendas) {
        this.qtdVendas = qtdVendas;
    }

    public Double getPerEconomia() {
        return perEconomia;
    }

    public void setPerEconomia(Double perEconomia) {
        this.perEconomia = perEconomia;
    }
}
