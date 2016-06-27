package br.com.controledevendas.notificacao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import br.com.controledevendas.R;
import br.com.controledevendas.model.Tarefa;
import br.com.controledevendas.util.NotificacaoUtil;

public class CriaNotificacaoCliente extends Activity {

	public String tickerText = null;
	public CharSequence titulo = null;
	public CharSequence mensagem = null;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		TextView text = new TextView(this);
		text.setText("Uma notificacao foi disparada.");
		setContentView(text);
		Intent intent = getIntent();
		Tarefa tarefa = (Tarefa) intent.getSerializableExtra("dadosDaNotificacao");
		//Texto com a chamada para a notificacao (barra de status)
		tickerText = "Voce recebeu uma notificacao";
		//Quem enviou a mensagem
		titulo = "Controle de Vendas";
		//CharSequence mensagem = "Exemplo de Notificacao";
		mensagem = tarefa.getDescricao();
		//Exibe a notifiacao para abrir a RecebeuMensagemActivity
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(20*1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						criarNotificacao(CriaNotificacaoCliente.this, tickerText, titulo, mensagem, ExecutaNotificacaoCliente.class);
					}

				}
		).start();
	}

	protected void criarNotificacao(Context context, CharSequence mensagemBarraStatus, CharSequence titulo,
			CharSequence mensagem, Class<?> activity) {

		Intent intent = new Intent(this, ExecutaNotificacaoCliente.class);
//		NotificacaoUtil.criar(this, mensagemBarraStatus, titulo, mensagem, R.drawable.ic_launcher, R.drawable.ic_launcher, intent);
		NotificacaoUtil.criarNotificacao_V17(this, mensagemBarraStatus, titulo, mensagem, R.drawable.ic_launcher, 
						R.drawable.ic_launcher, intent);
		
	}
}
