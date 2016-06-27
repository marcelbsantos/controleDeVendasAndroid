package br.com.controledevendas.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import br.com.controledevendas.R;

public class NotificacaoUtil {

	@SuppressLint("NewApi") 
	public static void criarNotificacao_V17(Context context, CharSequence tickerText, 
			CharSequence titulo, CharSequence mensagem, int icon, int id, Intent intent) {

		//PendingIntent para executar a intent ao selecionar a notificacao
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);
		Notification.Builder builder = new Notification.Builder(context);
		builder.setTicker(tickerText);
		builder.setContentText(mensagem);
		builder.setContentTitle(titulo);
		builder.setSmallIcon(icon);
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
		builder.setContentIntent(p);
		
		//Cria a notificacao
		Notification n = builder.build();
		n.vibrate = new long[] {150, 300, 150, 600};
		
		
		//id(numero unico) que identifica esta notificacao
		NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.notify(id, n);
		
		try {
			Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone toque = RingtoneManager.getRingtone(context, som);
			toque.play();
			
		} catch (Exception e) {

		}
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void criar(Context context, CharSequence tickerText, CharSequence title,
								CharSequence message, int icon, int id, Intent intent) {

		// PendingIntent para executar a intent ao selecionar a notificacao
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

		Notification n = null;

		int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 11) {
			Notification.Builder builder = new Notification.Builder(context);
			builder.setContentTitle(tickerText);
			builder.setContentText(message);
			builder.setSmallIcon(icon);
			builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
			builder.setContentIntent(p);

			if (apiLevel >= 17) {
				// Android 4.2
				n = builder.build();
			} else {
				// Android 3.x
				n = builder.getNotification();
			}
		} else {
			// Android 2.2
			n = new Notification(icon, tickerText, System.currentTimeMillis());

			// Informacoes
			n.setLatestEventInfo(context, title, message, p);
		}

		// id (numero unico) que identifica esta notificacao
		NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.notify(id, n);
	}
}
