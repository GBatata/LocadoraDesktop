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
  private String limiteQuilometragem;
  private int quantidadeDiarias;
  private BigDecimal valorDiaria;
  private BigDecimal valorCaucao;
  private BigDecimal valorTotal;
  private BigDecimal totalPago;
  private String status;

  public Locacao() {
    quantidadeDiarias = 1;
    valorDiaria = BigDecimal.ZERO;
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

  public String getLimiteQuilometragem() {
    return limiteQuilometragem;
  }

  public void setLimiteQuilometragem(String limiteQuilometragem) {
    this.limiteQuilometragem = limiteQuilometragem;
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
