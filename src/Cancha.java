/**
 * Representa un recinto deportivo de la universidad.
 */

public class Cancha {
    private String idCancha;
    private String tipoDeporte;

    /**
     * Inicializa una nueva cancha.
     *
     * @param idCancha El nombre o identificador de la cancha.
     * @param tipoDeporte El deporte asignado (ej. "Futbol").
     */

    public Cancha(String idCancha, String tipoDeporte) {
        this.idCancha = idCancha;
        this.tipoDeporte = tipoDeporte;
    }

    // Getters
    public String getIdCancha() { return idCancha; }
    public String getTipoDeporte() { return tipoDeporte; }
}