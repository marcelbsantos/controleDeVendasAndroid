package br.com.controledevendas.activity;

import br.com.controledevendas.R;
import br.com.controledevendas.util.Validator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarFornecedor extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.cadastrar_fornecedor);
		
		final EditText fornecedor = (EditText) findViewById(R.id.campo_fornecedor);
		
		Button buttonSalvar = (Button) findViewById(R.id.botao_salvar);
		buttonSalvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Validator.validateNotNull(fornecedor, "Preencha o campo Fornecedor.");
			}
		});
		
	}
}
