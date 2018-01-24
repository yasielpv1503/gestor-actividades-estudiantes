package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Switch;

import java.util.ArrayList;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.R;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Asistencia_DBFactory;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;

/**
 * Created by tatos on 24/01/18.
 */

public class ActividadesPorEstudianteAdapter  extends ArrayAdapter<Actividad> implements View.OnClickListener {

    private ArrayList<Actividad> dataSet;
    Context mContext;
    Estudiante estudiante;

    @Override
    public void onClick(View view) {

    }

    private static class ViewHolder {
        TextView txtName;
        TextView txtFecha;
        Switch infoPresnet;
    }

    public ActividadesPorEstudianteAdapter(ArrayList<Actividad> data, Context context,Estudiante estudiante) {
        super(context, R.layout.row_item_actividades_by_estudiante, data);
        this.estudiante=estudiante;
        this.dataSet = data;
        this.mContext=context;

    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Actividad dataModel = getItem(position);
        ActividadesPorEstudianteAdapter.ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ActividadesPorEstudianteAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_actividades_by_estudiante, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtFecha = (TextView) convertView.findViewById(R.id.fecha);
            viewHolder.infoPresnet = (Switch) convertView.findViewById(R.id.item_info);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ActividadesPorEstudianteAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        viewHolder.txtName.setText(dataModel.getNombre());
        viewHolder.txtFecha.setText(dataModel.getFecha());
        viewHolder.infoPresnet.setTag(position);

        Asistencia_DBFactory asFact = new Asistencia_DBFactory(mContext);
        boolean isPresente = asFact.isPresent(this.estudiante.getId(),dataModel.getId());
        viewHolder.infoPresnet.setChecked(isPresente);



        return convertView;
    }


}
