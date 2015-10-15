package br.com.controledevendas.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.controledevendas.R;
import br.com.controledevendas.dao.ClienteDao;
import br.com.controledevendas.model.Cliente;
import br.com.controledevendas.util.Extra;

public class ListarClientes extends Activity {
	
	private ListView listaClientes;
	private Cliente clienteSelecionado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_listar_clientes);
		
		listaClientes = (ListView) findViewById(R.id.lista_de_clientes);
		
		registerForContextMenu(listaClientes);
		
		listaClientes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicao, long id) {

				Intent edicao = new Intent(ListarClientes.this, CadastrarCliente.class);
				clienteSelecionado = (Cliente) listaClientes.getItemAtPosition(posicao);
				
				edicao.putExtra(Extra.CLIENTE_SELECIONADO, clienteSelecionado);
				startActivity(edicao);
			}
			
		});
		
		listaClientes.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adpter, View view, int posicao, long id) {
				
				clienteSelecionado = (Cliente) adpter.getItemAtPosition(posicao);
				
				return false;
			}
			
		});
		
	
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		this.carregarLista();
	}

	private void carregarLista() {

		ClienteDao dao = new ClienteDao(ListarClientes.this);
		List<Cliente> clientes = dao.listarClientes();
		ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>
										(ListarClientes.this, android.R.layout.simple_list_item_1, clientes);
		
		listaClientes.setAdapter(adapter);
		
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		
		MenuItem detalhar = menu.add("Detalhar");
		detalhar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				Intent detalhes = new Intent(ListarClientes.this, DetalharClientes.class);
				
				detalhes.putExtra(Extra.CLIENTE_SELECIONADO, clienteSelecionado);
				
				startActivity(detalhes);
				
				return false;
			}
		});
		
		MenuItem excluir = menu.add("Excluir");
		excluir.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				ClienteDao dao = new ClienteDao(ListarClientes.this);
				dao.deletar(clienteSelecionado.getId());
				dao.fechar();
				
				carregarLista();
				
				return false;
			}
		});
		
		MenuItem email = menu.add("Enviar E-mail");
		Intent intentEmail = new Intent(Intent.ACTION_SEND);
		intentEmail.setData(Uri.parse("mailto:"));
		intentEmail.setType("text/plain");
		email.setIntent(intentEmail);
		
	}
}
