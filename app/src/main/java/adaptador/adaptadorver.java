package adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import pojo.paciente;

import com.example.integrador.R;
import com.example.integrador.cardview;
public class adaptadorver extends RecyclerView.Adapter<adaptadorver.ViewHolder> {
    private final Context context;
    private final List<paciente> dentistasList;
    public adaptadorver(Context context, List<paciente> listapa) {
        this.context = context;
        this.dentistasList = listapa;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Aumentar el índice de posición
        int adjustedPosition = position + 1;

        // Obtener el objeto dentista actual
        paciente currentDentista = dentistasList.get(position);

        holder.tvNombre.setText(currentDentista.getNombre());
        holder.tvEspecialidad.setText(currentDentista.getApellido());

        holder.tvNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, cardview.class);
                i.putExtra("posicion", adjustedPosition);
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        // devolver el tamaño de la lista
        return dentistasList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvEspecialidad;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.nombre);
            tvEspecialidad = itemView.findViewById(R.id.apellido);
        }
    }
}
