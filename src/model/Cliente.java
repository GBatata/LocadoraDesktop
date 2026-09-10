package model;

import java.util.Date;

public class Cliente {

  private int id;
  private String nome;
  private String cpf;
  private String cnh;
  private Date dataNascimento;
  private String nacionalidade;
  private boolean resideBrasil;
  private String email;
  private String celular;
  private boolean ativo;

  public Cliente() {
    this.resideBrasil = true;
    this.ativo = true;
  }

  public Cliente(String nome, String cpf, String email) {
    this();
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getCnh() {
    return cnh;
  }

  public void setCnh(String cnh) {
    this.cnh = cnh;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getNacionalidade() {
    return nacionalidade;
  }

  public void setNacionalidade(String nacionalidade) {
    this.nacionalidade = nacionalidade;
  }

  public boolean isResideBrasil() {
    return resideBrasil;
  }

  public void setResideBrasil(boolean resideBrasil) {
    this.resideBrasil = resideBrasil;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public String toString() {
    return nome;
  }
}
