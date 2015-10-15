package br.com.controledevendas.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
	private static final String CATEGORIA = "controledevendas";
	private String[] scriptSQLCreate;
	private String scriptSQLDelete;

	public SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptCreate,
			String scriptSQLDelete) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptCreate;
		this.scriptSQLDelete = scriptSQLDelete;
	}
	
	//Criar novo banco
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(CATEGORIA, "Criando Banco SQL.");
		int qtdScripts = scriptSQLCreate.length;
		//Executa cada sql passado por parametro
		for (int i = 0; i < qtdScripts; i++) {
			String sql = scriptSQLCreate[i];
			Log.i(CATEGORIA, sql);
			//Cria o banco de dados executando o script de cria��o
			db.execSQL(sql);
		}
		
	}

	//Mudou vers�o do banco
	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {

		Log.w(CATEGORIA, "Atualizando vers�o " + versaoAntiga + " para " + novaVersao
				+ ". Todos os registros ser�o deletados.");
		Log.i(CATEGORIA, scriptSQLDelete);
		//Deleta as tabelas ...
		db.execSQL(scriptSQLDelete);
		//Cria novamente...
		onCreate(db);				
	}

}
