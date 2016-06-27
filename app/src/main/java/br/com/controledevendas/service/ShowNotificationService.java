package br.com.controledevendas.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.controledevendas.dao.TarefaDao;


public class ShowNotificationService extends Service {

    private TarefaDao dao;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //helper = new DatabaseHelper(this);
        dao = new TarefaDao();
        tarefas();
    }

    public void tarefas() {
        //SQLiteDatabase db = helper.getReadableDatabase();

        //Cursor cursor = db.rawQuery("SELECT * FROM tarefas", null);
        Cursor cursor = dao.getCursor();

        if (cursor == null) {

        } else {
            while(cursor.moveToNext()) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dataEntrega = new Date(cursor.getLong(2));

                Date dateNow = new Date();

                String dataEntregaFormatada = sdf.format(dataEntrega);
                String dateNowFormatada = sdf.format(dataEntrega);

                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(dateNow);
                int hora = calendar.get(Calendar.HOUR_OF_DAY);

                Log.i("Horário", String.valueOf(hora));

                SimpleDateFormat df = new SimpleDateFormat("KK:mm");
                try {
                    Date d1 = df.parse("19:00");

                    if (hora == 19) {
                        AgendarNotificacao(d1, 2, "oi", "eae");
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }

        cursor.close();
    }

    private void AgendarNotificacao(Date data, int id, String titulo, String conteudo) {

        // Obtém um novo calendário e define a data para a data da notificação
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        // Obtém um alarm manager
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(getBaseContext().ALARM_SERVICE);

        // Prepare the intent which should be launched at the date
       /* Intent intent = new Intent(this, CriarNotificacaoTarefa.class);
        intent.putExtra("id", String.valueOf(id));
        intent.putExtra("titulo", titulo);
        intent.putExtra("conteudo", conteudo);
*/
        // Obtém o pending intent
       // PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Regista o alerta no sistema.
        //alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

}

