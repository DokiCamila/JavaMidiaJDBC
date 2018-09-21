package br.unip.sicc.model;

import java.util.Objects;

public class Midia {

	private Integer id;
	private String descricao;
	private Double valor;
	
	private TipoMidia tipo;
 
	
	public Midia() {
		
	}
	
	public Midia(Integer id,TipoMidia tipo, String descricao, Double valor) {
		this(tipo,descricao,valor);
		this.id= id;
	}
	public Midia(TipoMidia tipo,String descricao, Double valor) {
		this.tipo= tipo;
		this.descricao = descricao;
		this.valor= valor;	
	}




	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoMidia getTipo() {
		return tipo;
	}
	public void setTipo(TipoMidia tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	@Override
    public int hashCode() {
        int hash = 4;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.descricao);
        hash = 19 * hash + Objects.hashCode(this.valor);
        hash = 19 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Midia other = (Midia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.descricao != other.descricao) {
            return false;
        }
        if (this.valor != other.valor
        		) {
            return false;
        }if (this.tipo != other.tipo) {
            return false;
            }
        return true;
    }
	
	
	
	public String toString() {
		
		return "Midia{" + "id=" + id + ", tipo=" + tipo + ", descricao=" + descricao + ", valor=" + valor +  '}';
		
	}


	
}
