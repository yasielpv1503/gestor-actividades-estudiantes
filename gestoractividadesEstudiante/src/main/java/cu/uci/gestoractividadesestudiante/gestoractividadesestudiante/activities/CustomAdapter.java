package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.activities;

import android.content.Context;
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

import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.R;
import cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity.Estudiante;

/**
 * Created by tatos on 23/01/18.
 */

public class CustomAdapter extends ArrayAdapter<Estudiante> implements View.OnClickListener{

    private ArrayList<Estudiante> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtGrupo;
        TextView txtUser;
        ImageView info;
    }

    public CustomAdapter(ArrayList<Estudiante> data, Context context) {
        super(context, R.layout.row_item_estudiante, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Estudiante dataModel=(Estudiante) object;

        switch (v.getId())
        {
            case R.id.item_info:
                Toast.makeText(mContext, "Release date " +dataModel.getNombre(), Toast.LENGTH_SHORT).show();
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
        // Return the completed view to render on screen
        return convertView;
    }
}