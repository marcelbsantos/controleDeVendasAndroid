package br.com.controledevendas.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.controledevendas.R;
import br.com.controledevendas.model.Tarefa;
import br.com.controledevendas.notificacao.CriaNotificacaoCliente;

public class ActivityNotificar extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificar);
    }

    public void startNotificacao(View view){
//        EditText tempo = (EditText) findViewById(R.id.campo_tempo);
        EditText descricao = (EditText) findViewById(R.id.campo_descricao);

        Tarefa tarefa = new Tarefa();
//        tarefa.setTempo(Integer.parseInt(tempo.getText().toString()));
        tarefa.setDescricao(descricao.getText().toString());

        Intent intentVaiParaOFormularioNotificar = new Intent(ActivityNotificar.this, CriaNotificacaoCliente.class);
        intentVaiParaOFormularioNotificar.putExtra("dadosDaNotificacao", tarefa);
        startActivity(intentVaiParaOFormularioNotificar);

    }
}
