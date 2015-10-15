package br.com.controledevendas.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.controledevendas.model.Cliente;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ClienteDao {

	private static final String CATEGORIA = "controledevendas";
	//Nome do Banco 
	private static final String NOME_BANCO = "controle_de_vendas";
	//Nome da tabela 
	private static final String NOME_TABELA = "clientes";
	protected SQLiteDatabase db;
	
	public static String[] colunas = new String[] {"id", "nome", "telefone", "email", "rua", "numero",
							"bairro", "cep", "complemento", "cidade", "estado"};
	
	public ClienteDao(Context ctx) {
		//Abre o banco de dados
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	public ClienteDao() {
		// TODO Auto-generated constructor stub
	}
	
	//Salva um cliente ou atualiza
	public Long salvar(Cliente cliente) {
		Long id = cliente.getId();
		if(id != null) {
			id = (long) atualizar(cliente);
		} else {
			//Insere um novo
			id = inserir(cliente);
		}
		
		return id;
	}
	
	//Insere um carro novo
	public Long inserir(Cliente cliente) {
		
		ContentValues values = new ContentValues();
		values.put("nome", cliente.getNome());
		values.put("telefone", cliente.getTelefone());
		values.put("email", cliente.getEmail());
		values.put("rua", cliente.getRua());
		values.put("numero", cliente.getNumero());
		values.put("bairro", cliente.getBairro());
		values.put("cep", cliente.getCep());
		values.put("complemento", cliente.getComplemento());
		values.put("cidade", cliente.getCidade());
		values.put("estado", cliente.getEstado());
		
		Long id = inserir(values);
		return id;
	}
	
	//Insere em novo carro
	private Long inserir(ContentValues values) {
		Long id = db.insert(NOME_TABELA, null, values);
		return id;
	}
	
	public int atualizar(Cliente cliente) {
		
		ContentValues values = new ContentValues();
		values.put("nome", cliente.getNome());
		values.put("telefone", cliente.getTelefone());
		values.put("email", cliente.getEmail());
		values.put("rua", cliente.getRua());
		values.put("numero", cliente.getNumero());
		values.put("bairro", cliente.getBairro());
		values.put("cep", cliente.getCep());
		values.put("complemento", cliente.getComplemento());
		values.put("cidade", cliente.getCidade());
		values.put("estado", cliente.getEstado());
		
		String id = String.valueOf(cliente.getId());
		String where = "id=?";
		String[] whereArgs = new String[] { id }; 
		int count = atualizar(values, where, whereArgs);
		return count;
		
	}
	
	private int atualizar(ContentValues values, String where, String[] whereArgs) {
		
		int count = db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
		return count;
		
	}
	
	public int deletar(Long id) {
		
		String where = "id=?";
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
		
	}
	
	private int deletar(String where, String[] whereArgs) {
		
		int count = db.delete(NOME_TABELA, where, whereArgs);
		Log.i(CATEGORIA, "Deletou [" + count + "] registros");
		
		return count;
	}

	
	//Retorna o cursor com todos os carros
	public Cursor getCursor() {
		try {
			//select * from clientes
			return db.query(NOME_TABELA, colunas, null, null, null, null, null);
		} catch (SQLException e) {
			Log.i(CATEGORIA, "Erro ao buscar os clientes: " + e.toString());
			return null;
		}
		
	}
	
	public List<Cliente> listarClientes(){
		
		Cursor c = getCursor();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
//		"nome", "telefone", "email", "rua", "numero",
//		"bairro", "cep", "complemento", "cidade", "estado
		while(c.moveToNext()) {
			//Recupera os índices das colunas
			Cliente cliente = new Cliente();
			cliente.setId(c.getLong(c.getColumnIndex("id")));
			cliente.setNome(c.getString(c.getColumnIndex("nome")));
			cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
			cliente.setEmail(c.getString(c.getColumnIndex("email")));
			cliente.setRua(c.getString(c.getColumnIndex("rua")));
			cliente.setNumero(c.getInt(c.getColumnIndex("numero")));
			cliente.setBairro(c.getString(c.getColumnIndex("bairro")));
			cliente.setCep(c.getString(c.getColumnIndex("cep")));
			cliente.setComplemento(c.getString(c.getColumnIndex("complemento")));
			cliente.setCidade(c.getString(c.getColumnIndex("cidade")));
			cliente.setEstado(c.getString(c.getColumnIndex("estado")));
			
			clientes.add(cliente);
		}
		
		return clientes;
	}
	
	//Fechar o banco
	public void fechar(){
		if(db != null) {
			db.close();
		}
	}
	
}
