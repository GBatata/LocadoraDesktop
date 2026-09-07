package model;

import java.util.Date;


public class Funcionario {

  private int id;
  private String nome;
  private String cpf;
  private String email;
  private Date dataNascimento;
  private String senhaHash;
  private String senhaSalt;
  private boolean ativo;

  public Funcionario() {
    this.ativo = true;
  }

  public Funcionario(String nome, String cpf, String email) {
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getSenhaHash() {
    return senhaHash;
  }

  public void setSenhaHash(String senhaHash) {
    this.senhaHash = senhaHash;
  }

  public String getSenhaSalt() {
    return senhaSalt;
  }

  public void setSenhaSalt(String senhaSalt) {
    this.senhaSalt = senhaSalt;
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