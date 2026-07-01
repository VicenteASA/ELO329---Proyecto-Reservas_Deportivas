/**
 * Representa a un estudiante o cliente del sistema.
 * Contiene la información personal y utiliza el Rol USM como identificador único.
 */

public class Usuario {
    private String rolUSM;
    private String nombre;
    private String correo;

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param rolUSM El identificador único del estudiante.
     * @param nombre El nombre completo.
     * @param correo El correo institucional.
     */

    public Usuario(String rolUSM, String nombre, String correo) {
        this.rolUSM = rolUSM;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters
    public String getRolUSM() { return rolUSM; }
    public String getNombre() { return nombre; }
}