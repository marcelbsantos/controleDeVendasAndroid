package br.com.controledevendas;

import br.com.controledevendas.activity.CadastrarCliente;
import br.com.controledevendas.activity.CadastrarFornecedor;
import br.com.controledevendas.activity.ListarClientes;
import br.com.controledevendas.notificacao.CriaNotificacaoCliente;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		String[] itens = new String[] {"Cadastrar Cliente", "Listar Clientes", "Notificar", "Sair"};
		
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		switch (position) {
		
		case 0:
			startActivity(new Intent(this, CadastrarCliente.class));
			break;
		
/*		case 1:
			startActivity(new Intent(this, CadastrarFornecedor.class));
			break;
*/
		case 1:
			startActivity(new Intent(this, ListarClientes.class));
			break;

		case 2:
			startActivity(new Intent(this, CriaNotificacaoCliente.class));
			break;
			
		
		default:
			finish();
//			break;
		}
		
	}

}
