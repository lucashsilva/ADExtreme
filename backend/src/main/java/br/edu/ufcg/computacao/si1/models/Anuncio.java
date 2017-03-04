package br.edu.ufcg.computacao.si1.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_anuncio")
public class Anuncio {
    //extrair para enums

    private final static DateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "titulo")
    @NotNull(message = "Titulo do anuncio nao pode ser nulo.")
    @NotEmpty(message = "Titulo do anuncio nao pode ser vazio.")
    private String titulo;

    @Column(name = "data_criacao")
    @NotNull(message = "Data de criacao do anuncio nao pode ser nula.")
    private Date dataDeCriacao;

    @Column(name = "preco")
    @NotNull(message = "Preco do anuncio nao pode ser nulo.")
    @Min(0)
    private double preco;

    @Column(name = "nota")
    @NotNull(message = "Classificacao nao pode ser nula.")
    @Min(0) @Max(5)
    private double classificacao;
    
    @Column(name = "tipo")
    @NotNull(message = "Tipo do anuncio nao pode ser nulo.")
    private String tipo;

    public Anuncio(String titulo, Date dataDeCriacao, double preco, double classificacao, String tipo) {
        this.titulo = titulo;
        this.dataDeCriacao = dataDeCriacao;
        this.preco = preco;
        this.classificacao = classificacao;
        this.tipo = tipo;
    }
    
    public Anuncio() {}

    /**
     * Retorna o id do anuncio
     * @return o id do anuncio
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica o id do anuncio
     * @param _id id a ser colocado no anuncio
     */public void setId(Long id) {
        this.id= id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataDeCriacao() {
        return DATE_FORMATTER.format(dataDeCriacao);
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(classificacao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dataDeCriacao == null) ? 0 : dataDeCriacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anuncio other = (Anuncio) obj;
		if (Double.doubleToLongBits(classificacao) != Double.doubleToLongBits(other.classificacao))
			return false;
		if (dataDeCriacao == null) {
			if (other.dataDeCriacao != null)
				return false;
		} else if (!dataDeCriacao.equals(other.dataDeCriacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", titulo=" + titulo + ", dataDeCriacao=" + dataDeCriacao + ", preco=" + preco
				+ ", classificacao=" + classificacao + ", tipo=" + tipo + "]";
	}

	
}
