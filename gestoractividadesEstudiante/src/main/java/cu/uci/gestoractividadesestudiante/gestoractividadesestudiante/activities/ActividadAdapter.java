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
import android.widget.TextView;

import java.util.ArrayList;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.R;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Actividad_DBFactory;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante_DBFactory;

/**
 * Created by tatos on 23/01/18.
 */

public class ActividadAdapter extends ArrayAdapter<Actividad> implements View.OnClickListener {
    private ArrayList<Actividad> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView txtName;
        TextView txtFecha;
        ImageView info;
    }

    public ActividadAdapter(ArrayList<Actividad> data, Context context) {
        super(context, R.layout.row_item_actividad, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        final int position=(Integer) v.getTag();
        Object object= getItem(position);
        final Actividad dataModel=(Actividad) object;

        switch (v.getId())
        {
            case R.id.item_info:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Actividad_DBFactory act = new Actividad_DBFactory(mContext);
                                act.delete(dataModel.getId());
                                dataSet.remove(position);
                                notifyDataSetChanged();
                                notifyDataSetInvalidated();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Estas seguro que quieres eliminar la actividad?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Actividad dataModel = getItem(position);
        ActividadAdapter.ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ActividadAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_actividad, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtFecha = (TextView) convertView.findViewById(R.id.fecha);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ActividadAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getNombre());
        viewHolder.txtFecha.setText(dataModel.getFecha());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        return convertView;
    }
}
