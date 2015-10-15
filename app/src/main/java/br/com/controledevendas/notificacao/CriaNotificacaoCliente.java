package br.com.controledevendas.notificacao;

import br.com.controledevendas.R;
import br.com.controledevendas.util.NotificacaoUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CriaNotificacaoCliente extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		TextView text = new TextView(this);
		text.setText("Uma notifica��o foi disparada.");
		setContentView(text);
		
		//Texto com a chamada para a notifica��o (barra de status)
		String tickerText = "Voc� recebeu uma notifica��o";
		//Quem enviou a mensagem
		CharSequence titulo = "Ricardo";
		CharSequence mensagem = "Exemplo de Notifica��o";
		
		//Exibe a notifia��o para abrir a RecebeuMensagemActivity
		criarNotificacao(this, tickerText, titulo, mensagem, ExecutaNotificacaoCliente.class);
		
	}

	protected void criarNotificacao(Context context, CharSequence mensagemBarraStatus, CharSequence titulo,
			CharSequence mensagem, Class<?> activity) {

		Intent intent = new Intent(this, ExecutaNotificacaoCliente.class);
//		NotificacaoUtil.criar(this, mensagemBarraStatus, titulo, mensagem, R.drawable.ic_launcher, R.drawable.ic_launcher, intent);
		NotificacaoUtil.criarNotificacao_V17(this, mensagemBarraStatus, titulo, mensagem, R.drawable.ic_launcher, 
						R.drawable.ic_launcher, intent);
		
	}
}
