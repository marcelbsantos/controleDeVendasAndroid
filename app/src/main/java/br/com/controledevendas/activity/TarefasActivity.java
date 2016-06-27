package br.com.controledevendas.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.controledevendas.R;
import br.com.controledevendas.dao.TarefaDao;
import br.com.controledevendas.model.Tarefa;
import br.com.controledevendas.notificacao.CriaNotificacaoCliente;

public class TarefasActivity extends ActionBarActivity {

    private static TarefaDao tarefaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        final EditText data = (EditText) findViewById(R.id.campo_data);
        final EditText hora = (EditText) findViewById(R.id.campo_hora);
        final EditText descricao = (EditText) findViewById(R.id.campo_descricao);

        Button botaoSalvar = (Button) findViewById(R.id.botao_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean verificador = testarCamposVazios();
                if(!verificador){
                    Tarefa tarefa = new Tarefa();

                    String dataHora = data.toString() + hora.toString();
                    tarefa.setDataHora(dataHora);
                    tarefa.setDescricao(descricao.toString());

                    tarefaDao = new TarefaDao(TarefasActivity.this);
                    tarefaDao.salvar(tarefa);
                    tarefaDao.fechar();
                }
            }


            private boolean testarCamposVazios() {

                if(TextUtils.isEmpty(data.getText().toString())) {
                    Toast.makeText(TarefasActivity.this, "Campo Data esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(hora.getText().toString())) {
                    Toast.makeText(TarefasActivity.this, "Campo Hora esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if(TextUtils.isEmpty(descricao.getText().toString())) {
                    Toast.makeText(TarefasActivity.this, "Campo Descrição esta vazio.", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


    }

}
