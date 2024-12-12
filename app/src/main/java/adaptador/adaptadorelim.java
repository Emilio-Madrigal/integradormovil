package adaptador;

import static global.info.listapaciente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integrador.R;

import java.util.List;

import pojo.paciente;

public class adaptadorelim extends RecyclerView.Adapter<adaptadorelim.ViewHolder> {
    private final Context context;
    private final List<paciente> listapa;
    public adaptadorelim(Context context, List<paciente> dentistaList) {
        this.context = context;
        this.listapa = dentistaList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder2, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        paciente current= listapa.get(position);

        holder.nombre.setText(current.getNombre());
        holder.check.setChecked(current.isChecked());

        // manejar el cambio de estado del checkbox
        holder.check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // guardar el estado del checkbox en el objeto dentista
            current.setChecked(isChecked);
        });
    }
    @Override
    public int getItemCount() {
        // devolver el tamaño de la lista
        return listapa.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        CheckBox check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.theviewE);
            check = itemView.findViewById(R.id.c1);
        }
    }

}

