public class Usuario {
    private String rolUSM;
    private String nombre;
    private String correo;

    public Usuario(String rolUSM, String nombre, String correo) {
        this.rolUSM = rolUSM;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters
    public String getRolUSM() { return rolUSM; }
    public String getNombre() { return nombre; }
}