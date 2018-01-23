package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnListarEstudiante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventListarEstudiante();
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
}
