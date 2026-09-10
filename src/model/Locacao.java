package model;

import java.math.BigDecimal;
import java.util.Date;

public class Locacao {

  public static final String RESERVADA = "RESERVADA";
  public static final String EM_ANDAMENTO = "EM_ANDAMENTO";
  public static final String FINALIZADA = "FINALIZADA";
  public static final String CANCELADA = "CANCELADA";

  private int id;
  private Cliente cliente;
  private Carro carro;
  private Funcionario funcionario;
  private Date dataRetirada;
  private Date dataPrevistaDevolucao;
  private Date dataDevolucao;
  private String localRetirada;
  private String localDevolucao;
  private String protecao;
  private String condutorAdicional;
  private boolean cadeirinha;
  private String limiteQuilometragem;
  private boolean cartaoConferido;
  private int quantidadeDiarias;
  private BigDecimal valorDiaria;
  private BigDecimal valorProtecao;
  private BigDecimal valorAdicionais;
  private BigDecimal valorCaucao;
  private BigDecimal valorTotal;
  private BigDecimal totalPago;
  private String status;

  public Locacao() {
    quantidadeDiarias = 1;
    valorDiaria = BigDecimal.ZERO;
    valorProtecao = BigDecimal.ZERO;
    valorAdicionais = BigDecimal.ZERO;
    valorCaucao = BigDecimal.ZERO;
    valorTotal = BigDecimal.ZERO;
    totalPago = BigDecimal.ZERO;
    status = RESERVADA;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Carro getCarro() {
    return carro;
  }

  public void setCarro(Carro carro) {
    this.carro = carro;
  }

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }

  public Date getDataRetirada() {
    return dataRetirada;
  }

  public void setDataRetirada(Date dataRetirada) {
    this.dataRetirada = dataRetirada;
  }

  public Date getDataPrevistaDevolucao() {
    return dataPrevistaDevolucao;
  }

  public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
    this.dataPrevistaDevolucao = dataPrevistaDevolucao;
  }

  public Date getDataDevolucao() {
    return dataDevolucao;
  }

  public void setDataDevolucao(Date dataDevolucao) {
    this.dataDevolucao = dataDevolucao;
  }

  public String getLocalRetirada() {
    return localRetirada;
  }

  public void setLocalRetirada(String localRetirada) {
    this.localRetirada = localRetirada;
  }

  public String getLocalDevolucao() {
    return localDevolucao;
  }

  public void setLocalDevolucao(String localDevolucao) {
    this.localDevolucao = localDevolucao;
  }

  public String getProtecao() {
    return protecao;
  }

  public void setProtecao(String protecao) {
    this.protecao = protecao;
  }

  public String getCondutorAdicional() {
    return condutorAdicional;
  }

  public void setCondutorAdicional(String condutorAdicional) {
    this.condutorAdicional = condutorAdicional;
  }

  public boolean isCadeirinha() {
    return cadeirinha;
  }

  public void setCadeirinha(boolean cadeirinha) {
    this.cadeirinha = cadeirinha;
  }

  public String getLimiteQuilometragem() {
    return limiteQuilometragem;
  }

  public void setLimiteQuilometragem(String limiteQuilometragem) {
    this.limiteQuilometragem = limiteQuilometragem;
  }

  public boolean isCartaoConferido() {
    return cartaoConferido;
  }

  public void setCartaoConferido(boolean cartaoConferido) {
    this.cartaoConferido = cartaoConferido;
  }

  public int getQuantidadeDiarias() {
    return quantidadeDiarias;
  }

  public void setQuantidadeDiarias(int quantidadeDiarias) {
    this.quantidadeDiarias = quantidadeDiarias;
  }

  public BigDecimal getValorDiaria() {
    return valorDiaria;
  }

  public void setValorDiaria(BigDecimal valorDiaria) {
    this.valorDiaria = valorDiaria;
  }

  public BigDecimal getValorProtecao() {
    return valorProtecao;
  }

  public void setValorProtecao(BigDecimal valorProtecao) {
    this.valorProtecao = valorProtecao;
  }

  public BigDecimal getValorAdicionais() {
    return valorAdicionais;
  }

  public void setValorAdicionais(BigDecimal valorAdicionais) {
    this.valorAdicionais = valorAdicionais;
  }

  public BigDecimal getValorCaucao() {
    return valorCaucao;
  }

  public void setValorCaucao(BigDecimal valorCaucao) {
    this.valorCaucao = valorCaucao;
  }

  public BigDecimal getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(BigDecimal valorTotal) {
    this.valorTotal = valorTotal;
  }

  public BigDecimal getTotalPago() {
    return totalPago;
  }

  public void setTotalPago(BigDecimal totalPago) {
    this.totalPago = totalPago;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
