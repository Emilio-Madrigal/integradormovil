package adaptador;

//import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadin.R;
import com.example.listadin.cardview;
import com.example.listadin.ver;

import global.info;

public class adaptadorver extends RecyclerView.Adapter<adaptadorver.activity> {
public Context context;

    @NonNull
    @Override
    public adaptadorver.activity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context,R.layout.viewholder,null);
        activity obj = new activity(v);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull adaptadorver.activity holder, int position) {
        holder.hola.setText("hola");

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class activity extends RecyclerView.ViewHolder {
        TextView hola;

        public activity(@NonNull View itemView) {
            super(itemView);
            hola = itemView.findViewById(R.id.NE);

        }
    }
}