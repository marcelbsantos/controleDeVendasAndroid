package br.com.controledevendas.activity;

import br.com.controledevendas.R;
import br.com.controledevendas.model.Cliente;
import br.com.controledevendas.util.Extra;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DetalharClientes extends Activity {

	private FormularioHelperDetallharCliente helper;
	
	@Override
	protected void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		setContentView(R.layout.detalhar_cliente);
		helper = new FormularioHelperDetallharCliente(this);
		
		Cliente clienteDetalhado = (Cliente) getIntent().getSerializableExtra(Extra.CLIENTE_SELECIONADO);
		helper.colocaClienteNoFormulario(clienteDetalhado);
		
		Button botaoVoltar = (Button) findViewById(R.id.botao_voltar);
		botaoVoltar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(DetalharClientes.this, ListarClientes.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}
}
