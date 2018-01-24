package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad_DBFactory;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante_DBFactory;

public class CrearActividadActivity extends AppCompatActivity implements
        View.OnClickListener {
    private Button atrasBtn;
    private Button btnCrear;

    Button btnDatePicker;
    EditText txtDate, txtName;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_actividad);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtName=(EditText)findViewById(R.id.description);
        btnDatePicker.setOnClickListener(this);
        eventAtras();
        btnCrearAct();

    }

    public void eventAtras(){
        atrasBtn = (Button) findViewById(R.id.atrasBtn);
        atrasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListarActividadActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

    }

    public void btnCrearAct(){
        btnCrear = (Button) findViewById(R.id.btnCrearAct);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean error = false;
                if(txtDate.getText().toString().trim().isEmpty() || txtName.getText().toString().trim().isEmpty()){
                    Toast.makeText(CrearActividadActivity.this,"Los campos no pueden estar en blanco.",Toast.LENGTH_SHORT).show();
                    error=true;
                }


                Actividad_DBFactory db_Act = new Actividad_DBFactory(getBaseContext());
                List<Actividad> list = db_Act.getAll();



                for (int i=0;i<list.size();i++){
                    if(list.get(i).getNombre().equals(txtName.getText().toString().trim())){
                        Toast.makeText(CrearActividadActivity.this,"Ya existe una actividad con esta descripcion",Toast.LENGTH_SHORT).show();
                        error=true;
                    }
                }

                if(!error){
                    Actividad e = new Actividad("",txtName.getText().toString(),txtDate.getText().toString());
                    db_Act.create(e);
                    Toast.makeText(CrearActividadActivity.this,"Estudiante registrado con exito.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), ListarActividadActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
