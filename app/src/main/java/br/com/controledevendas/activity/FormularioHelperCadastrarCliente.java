package br.com.controledevendas.activity;

import android.app.Activity;
import android.widget.EditText;
import br.com.controledevendas.R;
import br.com.controledevendas.model.Cliente;

public class FormularioHelperCadastrarCliente {

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
	
	
	public FormularioHelperCadastrarCliente(Activity activity) {
		
		campoNome = (EditText) activity.findViewById(R.id.campo_nome);
		campoEmail = (EditText) activity.findViewById(R.id.campo_email);
		campoTelefone = (EditText) activity.findViewById(R.id.campo_telefone);
		campoRua = (EditText) activity.findViewById(R.id.campo_rua);
		campoBairro = (EditText) activity.findViewById(R.id.campo_bairro);
		campoNumero = (EditText) activity.findViewById(R.id.campo_numero);
		campoCep = (EditText) activity.findViewById(R.id.campo_cep);
		campoCidade = (EditText) activity.findViewById(R.id.campo_cidade);
		campoEstado = (EditText) activity.findViewById(R.id.campo_estado);
		campoComplemento = (EditText) activity.findViewById(R.id.campo_complemento);
		
		this.cliente = new Cliente(); 
		
	}
	 
	public void colocaClienteNoFormulario(Cliente clienteParaSerAlterado) {

		this.cliente = clienteParaSerAlterado;
		
		campoNome.setText(clienteParaSerAlterado.getNome());
		campoEmail.setText(clienteParaSerAlterado.getEmail());
		campoTelefone.setText(clienteParaSerAlterado.getTelefone());
		campoRua.setText(clienteParaSerAlterado.getRua());
		campoBairro.setText(clienteParaSerAlterado.getBairro());
		campoNumero.setText(Integer.toString(clienteParaSerAlterado.getNumero()));
		campoCep.setText(clienteParaSerAlterado.getCep());
		campoCidade.setText(clienteParaSerAlterado.getCidade());
		campoEstado.setText(clienteParaSerAlterado.getEstado());
		campoComplemento.setText(clienteParaSerAlterado.getComplemento());
		
	}
	
	public Cliente pegaClienteDoFormulario() {
		
		cliente.setNome(campoNome.getText().toString());
		cliente.setTelefone(campoTelefone.getText().toString());
		cliente.setEmail(campoEmail.getText().toString());
		cliente.setRua(campoRua.getText().toString());;
		cliente.setNumero(Integer.parseInt(campoNumero.getText().toString()));
		cliente.setBairro(campoBairro.getText().toString());
		cliente.setCep(campoCep.getText().toString());
		cliente.setComplemento(campoComplemento.getText().toString());
		cliente.setCidade(campoCidade.getText().toString());
		cliente.setEstado(campoEstado.getText().toString());
		
		return cliente;
	}
}
