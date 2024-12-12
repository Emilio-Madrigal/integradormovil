package pojo;

import androidx.annotation.Nullable;

public class paciente {

    public String nombre;
    public String apellido;
    public String genero;
    public String edad;
    public String peso;
    public String altura;
    public String actividad;
    public String telefono;
    public String horac;
    public String fechac;
    public  int ID;

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals (obj);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFechac() {
        return fechac;
    }

    public void setFechac(String fechac) {
        this.fechac = fechac;
    }

    public String getHorac() {
        return horac;
    }

    public void setHorac(String horac) {
        this.horac = horac;
    }

    //getters y setters de usuario
    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    private boolean isChecked;
    public boolean isChecked() {
        return  isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked=checked;
    }

}
