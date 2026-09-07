package model;

import java.util.Date;
import java.time.LocalTime;


public class Cliente {
	private int id;
	private String nome;
	private Date data_nascimento;
	private String cpf;
	private String cnh;
	private String nacionalidade;
	private String email;
	private String celular;
	private String cartao_credito;
	private int calcao;
	
	private String local_retirada;
	private String local_devolucao;
	private Date data_retirada;
	private LocalTime hora_retirada;
	private Date data_devolucao;
	private LocalTime hora_devolucao;
	
	private String grupo_carro;
	private String protecao;
	private String limite_quilometragem;
	private String condutor_adicional;
	private int id_funcionario;
	
	
	public Cliente(int id, String nome, String cpf, String cnh, String nacionalidade, String email, int calcao,
			String local_retirada, String local_devolucao, Date data_retirada, LocalTime hora_retirada,
			Date data_devolucao, LocalTime hora_devolucao, String grupo_carro, String protecao,
			String limite_quilometragem, String condutor_adicional, int id_funcionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cnh = cnh;
		this.nacionalidade = nacionalidade;
		this.email = email;
		this.calcao = calcao;
		this.local_retirada = local_retirada;
		this.local_devolucao = local_devolucao;
		this.data_retirada = data_retirada;
		this.hora_retirada = hora_retirada;
		this.data_devolucao = data_devolucao;
		this.hora_devolucao = hora_devolucao;
		this.grupo_carro = grupo_carro;
		this.protecao = protecao;
		this.limite_quilometragem = limite_quilometragem;
		this.condutor_adicional = condutor_adicional;
		this.id_funcionario = id_funcionario;
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
	public Date getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
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
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
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
	public String getCartao_credito() {
		return cartao_credito;
	}
	public void setCartao_credito(String cartao_credito) {
		this.cartao_credito = cartao_credito;
	}
	public int getCalcao() {
		return calcao;
	}
	public void setCalcao(int calcao) {
		this.calcao = calcao;
	}
	public String getLocal_retirada() {
		return local_retirada;
	}
	public void setLocal_retirada(String local_retirada) {
		this.local_retirada = local_retirada;
	}
	public String getLocal_devolucao() {
		return local_devolucao;
	}
	public void setLocal_devolucao(String local_devolucao) {
		this.local_devolucao = local_devolucao;
	}
	public Date getData_retirada() {
		return data_retirada;
	}
	public void setData_retirada(Date data_retirada) {
		this.data_retirada = data_retirada;
	}
	public LocalTime getHora_retirada() {
		return hora_retirada;
	}
	public void setHora_retirada(LocalTime hora_retirada) {
		this.hora_retirada = hora_retirada;
	}
	public Date getData_devolucao() {
		return data_devolucao;
	}
	public void setData_devolucao(Date data_devolucao) {
		this.data_devolucao = data_devolucao;
	}
	public LocalTime getHora_devolucao() {
		return hora_devolucao;
	}
	public void setHora_devolucao(LocalTime hora_devolucao) {
		this.hora_devolucao = hora_devolucao;
	}
	public String getGrupo_carro() {
		return grupo_carro;
	}
	public void setGrupo_carro(String grupo_carro) {
		this.grupo_carro = grupo_carro;
	}
	public String getProtecao() {
		return protecao;
	}
	public void setProtecao(String protecao) {
		this.protecao = protecao;
	}
	public String getLimite_quilometragem() {
		return limite_quilometragem;
	}
	public void setLimite_quilometragem(String limite_quilometragem) {
		this.limite_quilometragem = limite_quilometragem;
	}
	public String getCondutor_adicional() {
		return condutor_adicional;
	}
	public void setCondutor_adicional(String condutor_adicional) {
		this.condutor_adicional = condutor_adicional;
	}
	public int getId_funcionario() {
		return id_funcionario;
	}
	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	
	
	
}

