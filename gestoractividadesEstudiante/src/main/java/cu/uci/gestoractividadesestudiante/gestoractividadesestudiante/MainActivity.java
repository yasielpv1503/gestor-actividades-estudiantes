package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnAddEstudiante;
    private Button btnListarEstudiante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventCreateEst();
        eventListarEstudiante();
    }

    public void eventCreateEst(){
        btnAddEstudiante = (Button) findViewById(R.id.btnAddEstudiante);
        btnAddEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CrearEstudianteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void eventListarEstudiante(){
        btnListarEstudiante = (Button) findViewById(R.id.btnListarEstudiante);
        btnListarEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListarEstudianteActivity.class);
                startActivity(intent);
            }
        });
    }
}
