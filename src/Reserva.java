public class Reserva {
    private Usuario usuario;
    private Cancha cancha;
    private String diaSemana;
    private int bloque;

    public Reserva(Usuario usuario, Cancha cancha, String diaSemana, int bloque) {
        this.usuario = usuario;
        this.cancha = cancha;
        this.diaSemana = diaSemana;
        this.bloque = bloque;
    }

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
