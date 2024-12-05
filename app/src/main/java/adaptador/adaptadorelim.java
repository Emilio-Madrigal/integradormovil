package adaptador;

import static global.info.listapaciente;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integrador.R;

public class adaptadorelim extends RecyclerView.Adapter<adaptadorelim.activity> {
    public Context context;

    @NonNull
    @Override
    public activity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context,R.layout.viewholder2, null);
        activity obj = new activity(v);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull activity miniactivity, int i) {
        final int pos = i;
        miniactivity.nombre.setText(listapaciente.get(i).getNombre ());
        miniactivity.check.setChecked(listapaciente.get(i).isChecked());
        miniactivity.check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Guardar el estado del checkbox en el ArrayList
            listapaciente.get(pos).setChecked(isChecked);
        });
    }

    @Override

    public int getItemCount() {
        return listapaciente.size();
    }
    public class activity extends RecyclerView.ViewHolder {
        TextView nombre;
        CheckBox check;

        public activity(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.theviewE);
            check = itemView.findViewById(R.id.c1);
        }
    }

}

