package model;

public class Carro {
	
	private int id;
	private String placa;
	private String modelo_carro;
	private String grupo_carro;
	private String direcao;
	private int numero_assento;
	private boolean gps;
	private boolean reservado;
	
	
	public Carro() {		
	}
	public Carro(int id, String placa, String modelo_carro, String grupo_carro, String direcao, int numero_assento,
			boolean gps, boolean reservado) {
		super();
		this.id = id;
		this.placa = placa;
		this.modelo_carro = modelo_carro;
		this.grupo_carro = grupo_carro;
		this.direcao = direcao;
		this.numero_assento = numero_assento;
		this.gps = gps;
		this.reservado = reservado;
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

	public String getModelo_carro() {
		return modelo_carro;
	}

	public void setModelo_carro(String modelo_carro) {
		this.modelo_carro = modelo_carro;
	}

	public String getGrupo_carro() {
		return grupo_carro;
	}

	public void setGrupo_carro(String grupo_carro) {
		this.grupo_carro = grupo_carro;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	public int getNumero_assento() {
		return numero_assento;
	}

	public void setNumero_assento(int numero_assento) {
		this.numero_assento = numero_assento;
	}

	public boolean isGps() {
		return gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}
	
	
}