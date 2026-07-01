/**
 * Clase principal que actúa como envoltorio (wrapper) para inicializar JavaFX.
 * Evita errores de configuración de módulos al ejecutar la aplicación desde el IDE.
 */

public class Launcher {
    public static void main(String[] args) {
        MainGUI.main(args);
    }
} //Puente para conectar a JavaFX. Java debe cargar las librerías gráficas sin problemas