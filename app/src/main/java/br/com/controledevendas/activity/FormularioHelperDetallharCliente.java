package br.com.controledevendas.activity;

import br.com.controledevendas.R;
import br.com.controledevendas.model.Cliente;
import android.app.Activity;
import android.widget.EditText;

public class FormularioHelperDetallharCliente {

	private EditText campoNome;
	private EditText campoEmail;
	private EditText campoTelefone;
	private EditText campoRua;
	private EditText campoBairro;
	private EditText campoNumero;
	private EditText campoCep;
	private EditText campoCidade;
	private EditText campoEstado;
	private EditText campoComplemento;
	private Cliente cliente;

	public FormularioHelperDetallharCliente(Activity activity) {

		campoNome = (EditText) activity.findViewById(R.id.dt_campo_nome);
		campoEmail = (EditText) activity.findViewById(R.id.dt_campo_email);
		campoTelefone = (EditText) activity.findViewById(R.id.dt_campo_telefone);
		campoRua = (EditText) activity.findViewById(R.id.dt_campo_rua);
		campoBairro = (EditText) activity.findViewById(R.id.dt_campo_bairro);
		campoNumero = (EditText) activity.findViewById(R.id.dt_campo_numero);
		campoCep = (EditText) activity.findViewById(R.id.dt_campo_cep);
		campoCidade = (EditText) activity.findViewById(R.id.dt_campo_cidade);
		campoEstado = (EditText) activity.findViewById(R.id.dt_campo_estado);
		campoComplemento = (EditText) activity.findViewById(R.id.dt_campo_complemento);
		
		this.cliente = new Cliente(); 
	}
	
	public void colocaClienteNoFormulario(Cliente clienteDetalhado) {

		this.cliente = clienteDetalhado;
		
		campoNome.setText(clienteDetalhado.getNome());
		campoEmail.setText(clienteDetalhado.getEmail());
		campoTelefone.setText(clienteDetalhado.getTelefone());
		campoRua.setText(clienteDetalhado.getRua());
		campoBairro.setText(clienteDetalhado.getBairro());
		campoNumero.setText(Integer.toString(clienteDetalhado.getNumero()));
		campoCep.setText(clienteDetalhado.getCep());
		campoCidade.setText(clienteDetalhado.getCidade());
		campoEstado.setText(clienteDetalhado.getEstado());
		campoComplemento.setText(clienteDetalhado.getComplemento());
		
	}

}
