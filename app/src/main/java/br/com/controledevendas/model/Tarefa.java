package br.com.controledevendas.model;

import java.io.Serializable;

/**
 * Created by Marcel on 08/06/2016.
 */
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String dataHora;
    private String descricao;
    private int tempo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
