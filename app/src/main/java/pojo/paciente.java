package pojo;

public class paciente {

    public String nombre;
    public String apellido;
    public String genero;
    public String edad;
    public String peso;
    public String altura;
    public String actividad;
    public String telefono;
    public String email;
    public String metaa;

    public int dia;
    public int mes;
    public int ano;
    public int horas;
    public int minutos;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMetaa() {
        return metaa;
    }

    public void setMetaa(String metaa) {
        this.metaa = metaa;
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

    //setters y getters de calendario
    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }



    private boolean isChecked;
    public boolean isChecked() {
        return  isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked=checked;
    }
}
