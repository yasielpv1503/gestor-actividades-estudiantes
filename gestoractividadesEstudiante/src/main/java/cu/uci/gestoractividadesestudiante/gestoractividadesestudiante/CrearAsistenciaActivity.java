package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities.ActividadAdapter;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities.AsistenciaAdapter;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.api_client.GrupoManager;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad_DBFactory;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante_DBFactory;

public class CrearAsistenciaActivity extends AppCompatActivity {
    Spinner grupos,actividades;
    ListView listView;
    Button btnAtras;
    ArrayList<Estudiante> dataModels;
    private static AsistenciaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_asistencia);
        loadGrupos();loadActivities();eventAtras();
    }
    public void eventAtras(){
        btnAtras = (Button) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("groupSelected",  "");

                startActivity(intent);
            }
        });
    }

    public void loadGrupos() {
        grupos = (Spinner) findViewById(R.id.grupo);
        List<String> list = new ArrayList<>();
        GrupoManager gm = new GrupoManager();
        for (int i=0;i<gm.getList().size();i++){
            list.add(gm.getList().get(i));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grupos.setAdapter(dataAdapter);
        grupos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                GrupoManager mg=new GrupoManager();
                String grupo = mg.getList().get(position);
                Actividad act = (Actividad)actividades.getSelectedItem();
                loadList(grupo,act);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    public void loadActivities() {
        actividades = (Spinner) findViewById(R.id.actividad);
        List<Actividad> list = new ArrayList<>();
        Actividad_DBFactory act = new Actividad_DBFactory(getBaseContext());
        List<Actividad> listAct = act.getAll();
        for (int i=0;i<listAct.size();i++){
            list.add(listAct.get(i));
        }

        ArrayAdapter<Actividad> dataAdapter = new ArrayAdapter<Actividad>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actividades.setAdapter(dataAdapter);
        actividades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                GrupoManager mg=new GrupoManager();
                String grupo =(String) grupos.getSelectedItem();
                Actividad act = (Actividad)actividades.getSelectedItem();
                loadList(grupo,act);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    public void loadList(String grupo,Actividad actividad){
        listView=(ListView)findViewById(R.id.listAsistencia);
        dataModels= new ArrayList<>();
        Estudiante_DBFactory est = new Estudiante_DBFactory(getBaseContext());
        List<Estudiante> estList = est.getAllByGroup(grupo);

        for (int i = 0; i < estList.size() ; i++)
            dataModels.add(estList.get(i));

        adapter= new AsistenciaAdapter(dataModels,CrearAsistenciaActivity.this,actividades);
        listView.setAdapter(adapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
