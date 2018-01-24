package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.ListarActividadActivity;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.ListarEstudianteActivity;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.MostrarActividadesPorEstudianteActivity;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.R;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante_DBFactory;

/**
 * Created by tatos on 23/01/18.
 */

public class EstudianteAdapter extends ArrayAdapter<Estudiante> implements View.OnClickListener{

    private ArrayList<Estudiante> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtGrupo;
        TextView txtUser;
        ImageView info;
        ImageView item_act;
    }

    public EstudianteAdapter(ArrayList<Estudiante> data, Context context) {
        super(context, R.layout.row_item_estudiante, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        final int position=(Integer) v.getTag();
        Object object= getItem(position);
        final Estudiante dataModel=(Estudiante) object;

        switch (v.getId())
        {
            case R.id.item_info:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Estudiante_DBFactory est = new Estudiante_DBFactory(mContext);
                                String g = dataModel.getGrupo();
                                est.delete(dataModel.getId());
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
                builder.setMessage("Estas seguro que quieres eliminar el estudiante?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                break;
            case R.id.item_act:

                Intent intent = new Intent(mContext, MostrarActividadesPorEstudianteActivity.class);
                intent.putExtra("ID_ESTUDIANTE", dataModel.getId());
                mContext.startActivity(intent);

                break;

        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Estudiante dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_estudiante, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtGrupo = (TextView) convertView.findViewById(R.id.grupo);
            viewHolder.txtUser = (TextView) convertView.findViewById(R.id.user);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);
            viewHolder.item_act = (ImageView) convertView.findViewById(R.id.item_act);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getNombre());
        viewHolder.txtGrupo.setText(dataModel.getGrupo());
        viewHolder.txtUser.setText(dataModel.getUsuario());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        viewHolder.item_act.setOnClickListener(this);
        viewHolder.item_act.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}