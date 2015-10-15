package br.com.controledevendas.dao;

import android.content.Context;

public class ClienteScriptBanco extends ClienteDao {

	//Script para fazer o drop da tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS clientes";
	//Cria a tabela com id sequêncial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"create table clientes (id integer primary key autoincrement, "
		+ "nome text not null, telefone text not null, email text not null, rua text not null, "
		+ "numero integer not null, bairro text not null, cep text not null, complemento  text, "
		+ "cidade text not null, estado text not null)"
	};
	
	private static final String NOME_BANCO = "controle_de_vendas";
	private static final int VERSAO_BANCO = 1;
	private static final String TABELA_CLIENTE = "clientes";
	private SQLiteHelper dbHelper;
	
	public ClienteScriptBanco(Context ctx) {
		// Cria utilizando um script SQL
		dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
		//Abre o banco no modo escrita para poder alterar também
		db = dbHelper.getWritableDatabase();
}
	
	//Fecha o banco
	public void fechar() {
		super.fechar();
		if(dbHelper != null) {
			dbHelper.close();
		}
	}
}
