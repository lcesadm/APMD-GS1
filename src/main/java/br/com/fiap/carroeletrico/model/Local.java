package br.com.fiap.carroeletrico.model;

import java.util.List;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "local")
public class Local {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String rua;

	private String bairro;

	private String cidade;

	private String estado;

	private int avaliacao;

	private String tomada;

	private float preco;
		
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

	public void setEstado(String estado) {
		if (estado == "Selecione um") {
			this.estado = "";
		} else {
			this.estado = estado;
		}
	}

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTomada() {
        return tomada;
    }

	public void setTomada(List<String> lista) {
		this.tomada = lista.toString();
	}

	public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

	public Vector<String> getData() {
		Vector<String> data = new Vector<String>();
		data.add(id.toString());
		data.add(nome);
		data.add(rua);
		data.add(bairro);
		data.add(cidade);
		data.add(estado);
		data.add(tomada.substring(1, tomada.length() - 1));
		data.add(String.valueOf(preco));
		data.add(String.valueOf(avaliacao));
		return data;
	}

	@Override
	public String toString() {
		return "Local [id=" + id + ", nome=" + nome + ", rua=" + rua + ", bairro="
				+ bairro + ", cidade=" + cidade + ", estado=" + estado + ", tomada="
				+ tomada + ", preco=" + preco + ", avaliacao=" + avaliacao + "]";
	}
}
