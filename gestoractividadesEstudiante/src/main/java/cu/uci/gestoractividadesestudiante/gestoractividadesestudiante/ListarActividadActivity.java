package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities.ActividadAdapter;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities.EstudianteAdapter;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad_DBFactory;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante_DBFactory;

public class ListarActividadActivity extends AppCompatActivity {
    Button btnAddAct;
    Button btnAtras;
    ArrayList<Actividad> dataModels;
    ListView listView;
    private static ActividadAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_actividad);
        btnAddAct();
        btnAtras();
        loadList();
    }

    public void btnAddAct(){
        btnAddAct = (Button) findViewById(R.id.btnCrear);
        btnAddAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CrearActividadActivity.class);
                startActivity(intent);
            }
        });
    }
    public void btnAtras(){
        btnAtras = (Button) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void loadList(){
        listView=(ListView)findViewById(R.id.list);
        dataModels= new ArrayList<>();
        Actividad_DBFactory est = new Actividad_DBFactory(getBaseContext());
        List<Actividad> estList =  est.getAll();

        for (int i = 0; i < estList.size() ; i++)
            dataModels.add(estList.get(i));

        adapter= new ActividadAdapter(dataModels,ListarActividadActivity.this);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Actividad dataModel= dataModels.get(position);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
