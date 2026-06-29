import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GestorReservas {
    private List<Reserva> listaReservas;
    private final int MAX_RESERVAS_ALUMNO = 3;

    public GestorReservas() {
        this.listaReservas = new ArrayList<>();
    }

    private int contarReservasUsuario(Usuario usuario) {
        int contador = 0;
        for (Reserva r : listaReservas) {
            if (r.getUsuario().getRolUSM().equals(usuario.getRolUSM())) {
                contador++;
            }
        }
        return contador;
    }

    public String getDisponiblesPorDiaYcancha(String dia, String nombreCancha) {
        StringBuilder sb = new StringBuilder();
        boolean encontrado = false;
        for (Reserva r : listaReservas) {
            // AHORA FILTRAMOS POR DÍA Y POR CANCHA
            if (r.getDiaSemana().equalsIgnoreCase(dia) &&
                    r.getCancha().getIdCancha().equalsIgnoreCase(nombreCancha) &&
                    r.getUsuario().getNombre().equalsIgnoreCase("DISPONIBLE")) {

                String b = String.valueOf(r.getBloque());
                String visual = (b.length() > 2) ? b.substring(0, b.length()/2) + "-" + b.substring(b.length()/2) : b;

                sb.append("Bloque ").append(visual).append(" está LIBRE\n");
                encontrado = true;
            }
        }
        return encontrado ? sb.toString() : "No hay bloques disponibles en esta cancha este día.";
    }

    public boolean agendar(Usuario usuario, Cancha cancha, String dia, int bloque) {
        if (contarReservasPorRol(usuario.getRolUSM()) >= MAX_RESERVAS_ALUMNO) {
            return false;
        }

        for (Reserva r : listaReservas) {
            if (r.getCancha().getIdCancha().equalsIgnoreCase(cancha.getIdCancha()) &&
                    r.getDiaSemana().equalsIgnoreCase(dia) &&
                    r.getBloque() == bloque) {

                // Si no está disponible, retornamos false inmediatamente
                if (!r.getUsuario().getNombre().equalsIgnoreCase("DISPONIBLE")) {
                    return false;
                }

                // Si llegamos aquí es porque está DISPONIBLE, reservamos y salimos
                r.setUsuario(usuario);
                return true;
            }
        }
        return false;
    }

    public int contarReservasPorRol(String rol) {
        int count = 0;
        for (Reserva r : listaReservas) {
            if (r.getUsuario().getRolUSM().equals(rol)) {
                count++;
            }
        }
        return count;
    }

    public void cargarDesdeCSV(String rutaArchivo) {
        this.listaReservas.clear();
        String separador = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            br.readLine(); // Salta encabezado
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(separador);

                //[0]Cancha, [1]Dia, [2]Bloque, [3]Ocupante, [4]Rol
                if (datos.length < 5) continue;

                Cancha c = new Cancha(datos[0], "Deporte");
                Usuario u = new Usuario(datos[4], datos[3], "correo@usm.cl");

                listaReservas.add(new Reserva(u, c, datos[1], Integer.parseInt(datos[2])));
            }
            System.out.println("Archivo cargado exitosamente con roles.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar CSV: " + e.getMessage());
        }
    }

    public void mostrarReservasDeUsuario(Usuario usuario) {
        System.out.println("\n--- RESERVAS ACTUALES DE: " + usuario.getNombre() + " ---");
        boolean tieneReservas = false;
        for (Reserva r : listaReservas) {
            if (r.getUsuario().getRolUSM().equals(usuario.getRolUSM())) {
                r.mostrarDetalle();
                tieneReservas = true;
            }
        }
        if (!tieneReservas) System.out.println("No hay reservas registradas.");
        System.out.println("-----------------------------------------");
    }

    public boolean cancelarReserva(Usuario usuario, Cancha cancha, String dia, int bloque) {
        Iterator<Reserva> iterador = listaReservas.iterator();
        while (iterador.hasNext()) {
            Reserva r = iterador.next();
            if (r.getCancha().getIdCancha().equals(cancha.getIdCancha()) &&
                    r.getDiaSemana().equals(dia) &&
                    r.getBloque() == bloque &&
                    r.getUsuario().getRolUSM().equals(usuario.getRolUSM())) {

                r.setUsuario(new Usuario("DISPONIBLE", "DISPONIBLE", "n/a"));
                return true;
            }
        }
        return false;
    }

    public void guardarEnArchivo(String ruta) {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(ruta))) {
            bw.write("Cancha,Dia,Bloque,Ocupante,Rol");
            bw.newLine();
            for (Reserva r : listaReservas) {
                String linea = r.getCancha().getIdCancha() + "," +
                        r.getDiaSemana() + "," +
                        r.getBloque() + "," +
                        r.getUsuario().getNombre() + "," +
                        r.getUsuario().getRolUSM();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}