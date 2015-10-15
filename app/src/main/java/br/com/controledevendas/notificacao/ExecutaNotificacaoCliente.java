package br.com.controledevendas.notificacao;

import br.com.controledevendas.R;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ExecutaNotificacaoCliente extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		//Cancela Notificação
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
        //Para cancelar precisa utilizar o mesmo id que foi utilizado para criar
		nm.cancel(R.drawable.ic_launcher);
		TextView text = new TextView(this);
		text.setText("Usuário selecionou a notificação. É possível executar algo agora.");
		setContentView(text);
	}
}
