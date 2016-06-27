package br.com.controledevendas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.controledevendas.model.Cliente;
import br.com.controledevendas.model.Tarefa;

/**
 * Created by Marcel on 08/06/2016.
 */
public class TarefaDao  {

    private static final String CATEGORIA = "controledevendas";
    //Nome do Banco
    private static final String NOME_BANCO = "controle_de_vendas";
    //Nome da tabela
    private static final String NOME_TABELA = "tarefas";

    protected SQLiteDatabase db;

    public static  String[] colunas = new String[] {"id", "datahora", "descricao"};

    public TarefaDao(){

    }

    public TarefaDao(Context ctx) {
        db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
    }

    public Long salvar(Tarefa tarefa) {
        Long id = tarefa.getId();
        if(id == null) {
            id = inserir(tarefa);
        }
        return id;
    }

    public Long inserir(Tarefa tarefa) {

        ContentValues values = new ContentValues();
        values.put("datahora", tarefa.getDataHora());
        values.put("descricao", tarefa.getDescricao());

        Long id = inserir(values);
        return id;
    }

    private Long inserir(ContentValues values) {
        Long id = db.insert(NOME_TABELA, null, values);
        return id;
    }

    //Retorna o cursor com todas as tarefas
    public Cursor getCursor() {

        try {
            //select * from clientes
//			return db.rawQuery("SELECT * FROM clientes", null);
            Cursor c = db.query(NOME_TABELA, colunas, null, null, null, null, null);
            return c;
        } catch (SQLException e) {
            Log.i(CATEGORIA, "Erro ao buscar os tarefas: " + e.toString());
            return null;
        }
    }

    //Fechar o banco
    public void fechar(){
        if(db != null) {
            db.close();
        }
    }
}
