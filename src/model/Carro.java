package model;

import java.math.BigDecimal;

public class Carro {

  public static final String DISPONIVEL = "DISPONIVEL";
  public static final String LOCADO = "LOCADO";
  public static final String INATIVO = "INATIVO";

  private int id;
  private String placa;
  private String modeloCarro;
  private String grupoCarro;
  private String cambio;
  private int numeroAssentos;
  private boolean gps;
  private String localAtual;
  private String limiteQuilometragem;
  private BigDecimal valorDiaria;
  private BigDecimal valorCaucao;
  private String status;

  public Carro() {
    valorDiaria = BigDecimal.ZERO;
    valorCaucao = BigDecimal.ZERO;
    status = DISPONIVEL;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getModeloCarro() {
    return modeloCarro;
  }

  public void setModeloCarro(String modeloCarro) {
    this.modeloCarro = modeloCarro;
  }

  public String getGrupoCarro() {
    return grupoCarro;
  }

  public void setGrupoCarro(String grupoCarro) {
    this.grupoCarro = grupoCarro;
  }

  public String getCambio() {
    return cambio;
  }

  public void setCambio(String cambio) {
    this.cambio = cambio;
  }

  public int getNumeroAssentos() {
    return numeroAssentos;
  }

  public void setNumeroAssentos(int numeroAssentos) {
    this.numeroAssentos = numeroAssentos;
  }

  public boolean isGps() {
    return gps;
  }

  public void setGps(boolean gps) {
    this.gps = gps;
  }

  public String getLocalAtual() {
    return localAtual;
  }

  public void setLocalAtual(String localAtual) {
    this.localAtual = localAtual;
  }

  public String getLimiteQuilometragem() {
    return limiteQuilometragem;
  }

  public void setLimiteQuilometragem(String limiteQuilometragem) {
    this.limiteQuilometragem = limiteQuilometragem;
  }

  public BigDecimal getValorDiaria() {
    return valorDiaria;
  }

  public void setValorDiaria(BigDecimal valorDiaria) {
    this.valorDiaria = valorDiaria;
  }

  public BigDecimal getValorCaucao() {
    return valorCaucao;
  }

  public void setValorCaucao(BigDecimal valorCaucao) {
    this.valorCaucao = valorCaucao;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String toString() {
    return placa + (modeloCarro == null ? "" : " - " + modeloCarro);
  }
}
