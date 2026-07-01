/**
 * Entidad que representa la reserva de un recinto deportivo.
 * Asocia a un Usuario con una Cancha especifica, en un día y bloque horario determinado.
 */

public class Reserva {
    private Usuario usuario;
    private Cancha cancha;
    private String diaSemana;
    private int bloque;

    /**
     * Crea una nueva instancia de una reserva con sus datos iniciales.
     *
     * @param usuario   El usuario que realiza la reserva.
     * @param cancha    La cancha asignada a la reserva.
     * @param diaSemana El día de la semana elegido (ej. "Lunes").
     * @param bloque    El número de bloque horario asignado.
     */

    public Reserva(Usuario usuario, Cancha cancha, String diaSemana, int bloque) {
        this.usuario = usuario;
        this.cancha = cancha;
        this.diaSemana = diaSemana;
        this.bloque = bloque;
    }

    /**
     * Imprime en la consola el detalle completo de la reserva,
     * incluyendo el ID de la cancha, el día, el bloque y el cliente.
     */

    public void mostrarDetalle() {
        System.out.println("Reserva: " + cancha.getIdCancha() + " | Día: " + diaSemana + " | Bloque: " + bloque + " | A nombre de: " + usuario.getNombre());
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cancha getCancha() { return cancha; }
    public String getDiaSemana() { return diaSemana; }
    public int getBloque() { return bloque; }

    public Usuario getUsuario() { return usuario; }

}
