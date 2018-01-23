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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities.CustomAdapter;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.api_client.GrupoManager;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante_DBFactory;

public class ListarEstudianteActivity extends AppCompatActivity {
    Spinner grupos;
    Button btnAddEstudiante;
    ArrayList<Estudiante> dataModels;
    ListView listView;
    private static CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_estudiante);
        loadGrupos();

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

    void loadList(String grupo){
        listView=(ListView)findViewById(R.id.list);
        dataModels= new ArrayList<>();
        Estudiante_DBFactory est = new Estudiante_DBFactory(getBaseContext());
        List<Estudiante> estList =  est.getAllByGroup(grupo);
        for (int i = 0; i < estList.size() ; i++)
            dataModels.add(estList.get(i));
        adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Estudiante dataModel= dataModels.get(position);

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void loadGrupos() {
        grupos = (Spinner) findViewById(R.id.group_est);
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
                loadList(grupo);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
