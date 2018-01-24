package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities.ActividadesPorEstudianteAdapter;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities.EstudianteAdapter;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad_DBFactory;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante_DBFactory;

public class MostrarActividadesPorEstudianteActivity extends AppCompatActivity {
    String idUser = "";
    Estudiante estudiante;
    TextView textNameEstudiante;
    Button btnAtras;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_actividades_por_estudiante);
        btnAtras();
        this.idUser=getIntent().getStringExtra("ID_ESTUDIANTE");
        Estudiante_DBFactory est= new Estudiante_DBFactory(getBaseContext());
        List<Estudiante> list =  est.getAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(this.idUser)){
                this.estudiante =  list.get(i);
            }
        }
        textNameEstudiante = (TextView)findViewById(R.id.name_estudiante);
        textNameEstudiante.setText("Asistencia  a las actividades de: "+this.estudiante.getNombre());
        loadList();





    }
    ArrayList<Actividad> dataModels;
    private static ActividadesPorEstudianteAdapter adapter;

    public void loadList(){
        listView=(ListView)findViewById(R.id.listAct);
        dataModels= new ArrayList<>();
        Actividad_DBFactory est = new Actividad_DBFactory(getBaseContext());
        List<Actividad> estList =  est.getAll();

        for (int i = 0; i < estList.size() ; i++)
            dataModels.add(estList.get(i));

        adapter= new ActividadesPorEstudianteAdapter(dataModels,MostrarActividadesPorEstudianteActivity.this,this.estudiante);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Actividad dataModel= dataModels.get(position);
            }
        });
    }


    public void btnAtras(){
        btnAtras = (Button) findViewById(R.id.btnAtras);
        Estudiante e=this.estudiante;
        btnAtras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListarEstudianteActivity.class);
                intent.putExtra("groupSelected",  "");
                startActivity(intent);
            }
        });
    }

}
