package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.api_client.GrupoManager;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante_DBFactory;

public class CrearEstudianteActivity extends AppCompatActivity {
    private Spinner grupos;
    private Button atrasBtn;
    private Button btnCrearYListar;
    private Button btnCrearYCrear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_estudiante);
        loadGrupos();
        eventAtras();
        btnCrearYCrear();
        btnCrearYListar();
    }

    public void eventAtras(){
        atrasBtn = (Button) findViewById(R.id.atrasBtn);
        atrasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListarEstudianteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void btnCrearYCrear(){
        btnCrearYCrear = (Button) findViewById(R.id.btnCrearYCrear);
        btnCrearYCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView user = (TextView) findViewById(R.id.user_est);
                TextView name = (TextView) findViewById(R.id.name_est);
                Spinner grupo = (Spinner) findViewById(R.id.group_est);
                boolean error = false;

                if(user.getText().toString().trim().isEmpty() || name.getText().toString().trim().isEmpty()|| grupo.getSelectedItem().toString().trim().isEmpty()){
                    Toast.makeText(CrearEstudianteActivity.this,"Los campos no pueden estar en blanco.",Toast.LENGTH_SHORT).show();
                    error=true;
                }


                Estudiante_DBFactory db_Est = new Estudiante_DBFactory(getBaseContext());
                List<Estudiante> list = db_Est.getAll();



                for (int i=0;i<list.size();i++){
                    if(list.get(i).getUsuario().equals(user.getText().toString().trim())){
                        Toast.makeText(CrearEstudianteActivity.this,"Ya existe un estudiante con este usuario",Toast.LENGTH_SHORT).show();
                        error=true;
                    }
                    if(list.get(i).getNombre().equals(name.getText().toString().trim())){
                        Toast.makeText(CrearEstudianteActivity.this,"Ya existe un estudiante con este nombre",Toast.LENGTH_SHORT).show();
                        error=true;
                    }
                }

                if(!error){
                    Estudiante e = new Estudiante("",user.getText().toString(),name.getText().toString(),grupo.getSelectedItem().toString());
                    long id = db_Est.create(e);
                    Toast.makeText(CrearEstudianteActivity.this,"Estudiante registrado con exito."+String.valueOf(id),Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(getBaseContext(), CrearEstudianteActivity.class);
                    //startActivity(intent);
                }
                }
        });
    }

    public void btnCrearYListar(){
        btnCrearYListar = (Button) findViewById(R.id.btnCrearYListar);
        btnCrearYListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView user = (TextView) findViewById(R.id.user_est);
                TextView name = (TextView) findViewById(R.id.name_est);
                Spinner grupo = (Spinner) findViewById(R.id.group_est);
                boolean error = false;
                if(user.getText().toString().trim().isEmpty() || name.getText().toString().trim().isEmpty()|| grupo.getSelectedItem().toString().trim().isEmpty()){
                    Toast.makeText(CrearEstudianteActivity.this,"Los campos no pueden estar en blanco.",Toast.LENGTH_SHORT).show();
                    error=true;
                }


                Estudiante_DBFactory db_Est = new Estudiante_DBFactory(getBaseContext());
                List<Estudiante> list = db_Est.getAll();



                for (int i=0;i<list.size();i++){
                    if(list.get(i).getUsuario().equals(user.getText().toString().trim())){
                        Toast.makeText(CrearEstudianteActivity.this,"Ya existe un estudiante con este usuario",Toast.LENGTH_SHORT).show();
                        error=true;
                    }
                    if(list.get(i).getNombre().equals(name.getText().toString().trim())){
                        Toast.makeText(CrearEstudianteActivity.this,"Ya existe un estudiante con este nombre",Toast.LENGTH_SHORT).show();
                        error=true;
                    }
                }

                if(!error){
                    Estudiante e = new Estudiante("",user.getText().toString(),name.getText().toString(),grupo.getSelectedItem().toString());
                    db_Est.create(e);
                    Toast.makeText(CrearEstudianteActivity.this,"Estudiante registrado con exito.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), ListarEstudianteActivity.class);
                    intent.putExtra("groupSelected", grupo.getSelectedItem().toString().trim());
                    startActivity(intent);
                }
            }
        });
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
    }


}
