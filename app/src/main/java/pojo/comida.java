package pojo;

public class comida {

    String nombreC;
    String calorias;

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }

    public String getNombre() {
        return nombreC;
    }

    public void setNombre(String nombre) {
        this.nombreC = nombre;
    }

    private boolean isChecked;
    public boolean isChecked() {
        return  isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked=checked;
    }
}
