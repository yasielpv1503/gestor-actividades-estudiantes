package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.R;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad_DBFactory;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Asistencia;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Asistencia_DBFactory;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;

/**
 * Created by tatos on 23/01/18.
 */

public class AsistenciaAdapter extends ArrayAdapter<Estudiante> implements View.OnClickListener {
    private ArrayList<Estudiante> dataSet;
    Context mContext;
    Spinner txtActividad;

    private static class ViewHolder {
        TextView txtName;
        TextView txtUser;
        Switch presente;
    }

    public AsistenciaAdapter(ArrayList<Estudiante> data, Context context,Spinner spiner) {
        super(context, R.layout.row_item_asistencia, data);
        this.dataSet = data;
        this.mContext=context;
        txtActividad = spiner;

    }

    @Override
    public void onClick(View v) {

        final int position=(Integer) v.getTag();
        Object object= getItem(position);
        final Estudiante dataModel=(Estudiante) object;

        switch (v.getId())
        {
            case R.id.presente:
                Asistencia_DBFactory asistencia = new Asistencia_DBFactory(mContext);
                String id_Actividad = ((Actividad)this.txtActividad.getSelectedItem()).getId();
                String id_Estudiante = dataModel.getId();
                boolean presente = ((Switch)v).isChecked();
                asistencia.updateOrCreate(id_Estudiante,id_Actividad,presente);


                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Estudiante dataModel = getItem(position);
        AsistenciaAdapter.ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new AsistenciaAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_asistencia, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtUser = (TextView) convertView.findViewById(R.id.usuario);
            viewHolder.presente = (Switch) convertView.findViewById(R.id.presente);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AsistenciaAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getNombre());
        viewHolder.txtUser.setText(dataModel.getUsuario());
        viewHolder.presente.setOnClickListener(this);
        viewHolder.presente.setTag(position);

        Asistencia_DBFactory asFact = new Asistencia_DBFactory(mContext);
        boolean isPresente = asFact.isPresent(dataModel.getId(),((Actividad)this.txtActividad.getSelectedItem()).getId());
        Toast.makeText(mContext,"IS: "+isPresente+" E: "+dataModel.getId()+" A: "+((Actividad)this.txtActividad.getSelectedItem()).getId(),Toast.LENGTH_SHORT).show();
        viewHolder.presente.setChecked(isPresente);
        return convertView;
    }
}
