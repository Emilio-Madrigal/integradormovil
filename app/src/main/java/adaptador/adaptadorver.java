package adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.integrador.R;
import com.example.integrador.cardview;
import com.example.integrador.ver;
import global.info;
public class adaptadorver extends RecyclerView.Adapter<adaptadorver.activity> {
    public Context context;
    @NonNull
    @Override
    public activity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context,R.layout.viewholder, null);
        activity obj = new activity(v);
        return obj;
    }
    @Override
    public void onBindViewHolder(@NonNull activity miniactivity, int i) {
        final int pos = i;
        miniactivity.nombre.setText(info.listapaciente.get(i).getNombre());
        miniactivity.apellido.setText(info.listapaciente.get(i).getApellido());
        miniactivity.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent card = new Intent(context, cardview.class);
                card.putExtra("posicion", pos);
                context.startActivity(card);
            }
        });
    }
    @Override
    public int getItemCount() {
        return info.listapaciente.size();
    }
    public class activity extends RecyclerView.ViewHolder {
        TextView nombre, apellido;
        public activity(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            apellido=itemView.findViewById (R.id.apellido);

        }
    }
}
