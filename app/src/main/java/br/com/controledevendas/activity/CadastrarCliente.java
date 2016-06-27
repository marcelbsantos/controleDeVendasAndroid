package br.com.controledevendas.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.controledevendas.R;
import br.com.controledevendas.dao.ClienteDao;
import br.com.controledevendas.dao.ClienteScriptBanco;
import br.com.controledevendas.model.Cliente;
import br.com.controledevendas.util.Extra;
import br.com.controledevendas.util.Mask;

public class CadastrarCliente extends Activity {
	
	private static ClienteDao clienteDao; 
	private FormularioHelperCadastrarCliente helper;
	
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.cadastrar_cliente);
		
		final EditText telefone = (EditText) findViewById(R.id.campo_telefone);
		telefone.addTextChangedListener(Mask.insert("(##)####-####", telefone));
		
		final EditText cep = (EditText) findViewById(R.id.campo_cep);
		cep.addTextChangedListener(Mask.insert("##.###-###", cep));
		
		final EditText bairro = (EditText) findViewById(R.id.campo_bairro);
		
		final EditText cidade = (EditText) findViewById(R.id.campo_cidade);
		
		final EditText email = (EditText) findViewById(R.id.campo_email);
		
		final EditText estado = (EditText) findViewById(R.id.campo_estado);
		
		final EditText nome = (EditText) findViewById(R.id.campo_nome);
		
		final EditText numero = (EditText) findViewById(R.id.campo_numero);
		
		final EditText rua = (EditText) findViewById(R.id.campo_rua);
		

		Button buttonSalvar = (Button) findViewById(R.id.botao_salvar);
		
		
		clienteDao = new ClienteScriptBanco(this);
		helper = new FormularioHelperCadastrarCliente(this);
		
		Cliente clienteParaSerAlterado = (Cliente) getIntent().getSerializableExtra(Extra.CLIENTE_SELECIONADO);
		
		if (clienteParaSerAlterado == null) {
			
			clienteParaSerAlterado = new Cliente();
			
		} else {
			
			buttonSalvar.setText("Alterar");
			helper.colocaClienteNoFormulario(clienteParaSerAlterado);
		}
		
		buttonSalvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				boolean verificador = testarCampoVazio();
				
				if(!verificador) {
				
					Cliente cliente = helper.pegaClienteDoFormulario();
					clienteDao = new ClienteDao(CadastrarCliente.this);					
					clienteDao.salvar(cliente);				
					clienteDao.fechar();
					finish();

				}
				
			}

			private boolean testarCampoVazio() {
				
				if(TextUtils.isEmpty(nome.getText().toString())) {
					Toast.makeText(CadastrarCliente.this, "Campo Nome esta vazio.", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if(TextUtils.isEmpty(email.getText().toString())) {
					Toast.makeText(CadastrarCliente.this, "Campo E-mail esta vazio.", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if(TextUtils.isEmpty(telefone.getText().toString())) {
					Toast.makeText(CadastrarCliente.this, "Campo Telefone esta vazio.", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if(TextUtils.isEmpty(rua.getText().toString())) {
					Toast.makeText(CadastrarCliente.this, "Campo Rua esta vazio.", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if(TextUtils.isEmpty(bairro.getText().toString())) {
					Toast.makeText(CadastrarCliente.this, "Campo Bairro esta vazio.", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if(TextUtils.isEmpty(numero.getText().toString())) {
					Toast.makeText(CadastrarCliente.this, "Campo Numero esta vazio.", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if(TextUtils.isEmpty(cep.getText().toString())) {
					Toast.makeText(CadastrarCliente.this, "Campo Cep esta vazio.", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if(TextUtils.isEmpty(cidade.getText().toString())) {
					Toast.makeText(CadastrarCliente.this, "Campo Cidade esta vazio.", Toast.LENGTH_SHORT).show();
					return true;
				}
				
				if(TextUtils.isEmpty(estado.getText().toString())) {
					Toast.makeText(CadastrarCliente.this, "Campo Estado esta vazio.", Toast.LENGTH_SHORT).show();
					return true;
				}
					
				return false;
					
			}
		});
		
		
	}

}
