
package entity;

import java.io.Serializable;



public class Aula implements Serializable{

	private static final long serialVersionUID = 1L;
	private String tipo;
	private int numeroPosti;
	private String nome;
	private int proiettore;
	private boolean microfono;
	private boolean lavagna;
	private boolean lavInterattiva;
	private boolean prese;
	private boolean ethernet;
	private String laboratorio;
	
	public String getTipo() {
		return tipo;
	}
	
	public Aula(String nome) {
		super();
		this.nome = nome;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getNumeroPosti() {
		return numeroPosti;
	}
	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getProiettore() {
		return proiettore;
	}
	public void setProiettore(int proiettore) {
		this.proiettore = proiettore;
	}
	public boolean isMicrofono() {
		return microfono;
	}
	public void setMicrofono(boolean microfono) {
		this.microfono = microfono;
	}
	public boolean isLavagna() {
		return lavagna;
	}
	public void setLavagna(boolean lavagna) {
		this.lavagna = lavagna;
	}
	public boolean isLavInterattiva() {
		return lavInterattiva;
	}
	public void setLavInterattiva(boolean lavInterattiva) {
		this.lavInterattiva = lavInterattiva;
	}
	public boolean isPrese() {
		return prese;
	}
	public void setPrese(boolean prese) {
		this.prese = prese;
	}
	public boolean isEthernet() {
		return ethernet;
	}
	public void setEthernet(boolean ethernet) {
		this.ethernet = ethernet;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	public Aula(String tipo, int numeroPosti, String nome, int proiettore, boolean microfono, boolean lavagna,
			boolean lavInterattiva, boolean prese, boolean ethernet, String laboratorio) {
		super();
		this.tipo = tipo;
		this.numeroPosti = numeroPosti;
		this.nome = nome;
		this.proiettore = proiettore;
		this.microfono = microfono;
		this.lavagna = lavagna;
		this.lavInterattiva = lavInterattiva;
		this.prese = prese;
		this.ethernet = ethernet;
		this.laboratorio = laboratorio;
	}
	
}
