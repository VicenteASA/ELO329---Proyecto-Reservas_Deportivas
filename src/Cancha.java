public class Cancha {
    private String idCancha;
    private String tipoDeporte;

    public Cancha(String idCancha, String tipoDeporte) {
        this.idCancha = idCancha;
        this.tipoDeporte = tipoDeporte;
    }

    // Getters
    public String getIdCancha() { return idCancha; }
    public String getTipoDeporte() { return tipoDeporte; }
}