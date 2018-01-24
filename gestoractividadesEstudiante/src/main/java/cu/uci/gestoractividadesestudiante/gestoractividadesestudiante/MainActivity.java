package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnListarEstudiante;
    private Button btnListarActividad;
    private Button btnPasarAsistencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventListarEstudiante();
        eventListarActividad();
        eventCrearAsistencia();
    }



    public void eventListarEstudiante(){
        btnListarEstudiante = (Button) findViewById(R.id.btnEstudiante);
        btnListarEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListarEstudianteActivity.class);
                startActivity(intent);
            }
        });
    }
    public void eventListarActividad(){
        btnListarActividad = (Button) findViewById(R.id.btnActividad);
        btnListarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListarActividadActivity.class);
                startActivity(intent);
            }
        });
    }
    public void eventCrearAsistencia(){
        btnPasarAsistencia = (Button) findViewById(R.id.btnPasarAsistencia);
        btnPasarAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CrearAsistenciaActivity.class);
                startActivity(intent);
            }
        });
    }
}
