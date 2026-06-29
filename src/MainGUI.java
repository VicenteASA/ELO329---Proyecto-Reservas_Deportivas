import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGUI extends Application {

    private GestorReservas sistema = new GestorReservas();
    private Usuario alumnoActual;
    @Override
    public void start(Stage primaryStage) {
        sistema.cargarDesdeCSV("horario_canchas_modificado.txt");

        // --- VENTANA DE LOGIN ---
        Stage loginStage = new Stage();
        loginStage.setTitle("Inicio de Sesión");
        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(20));
        loginGrid.setVgap(10);

        TextField txtRol = new TextField();
        txtRol.setPromptText("Ej: 202430515-8");

        // Etiqueta del arreglo A (Formato)
        Label lblFormato = new Label("Formato: XXXXXXXXX-X");
        lblFormato.setStyle("-fx-font-size: 10px; -fx-text-fill: gray;");

        Button btnEntrar = new Button("Entrar");

        loginGrid.add(new Label("Rol USM:"), 0, 0);
        loginGrid.add(txtRol, 1, 0);
        loginGrid.add(lblFormato, 1, 1); // Agregamos la etiqueta debajo del campo
        loginGrid.add(btnEntrar, 1, 2);  // Ajustamos el botón a la fila 2

        btnEntrar.setOnAction(e -> {
            String rolIngresado = txtRol.getText();
            // Validación simple de formato para evitar errores antes de consultar
            if (!rolIngresado.matches("\\d{9}-[0-9kK]")) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Formato Incorrecto");
                alerta.setContentText("Por favor, ingrese un rol válido (ej: 202430515-8).");
                alerta.showAndWait();
            } else {
                int reservasActuales = sistema.contarReservasPorRol(rolIngresado);
                if (reservasActuales < 3) {
                    alumnoActual = new Usuario(rolIngresado, "Estudiante USM", "estudiante@usm.cl");
                    loginStage.close();
                    abrirVentanaPrincipal(primaryStage);
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Acceso Denegado");
                    alerta.setContentText("Ya alcanzó el límite de 3 reservas.");
                    alerta.showAndWait();
                }
            }
        });

        loginStage.setScene(new Scene(loginGrid, 300, 150));
        loginStage.show();
    }

    private void abrirVentanaPrincipal(Stage primaryStage) {
        primaryStage.setTitle("Smart Canchas USM");

        ComboBox<String> cbDias = new ComboBox<>();
        cbDias.getItems().addAll("LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO");
        cbDias.setPromptText("Seleccione Día");

        ComboBox<String> cbBloques = new ComboBox<>();
        cbBloques.getItems().addAll("3-4", "5-6", "7-8", "9-10", "11-12", "13-14", "15-16", "17-18", "19-20");
        cbBloques.setPromptText("Seleccione Bloque");

        ComboBox<String> cbCanchas = new ComboBox<>();
        cbCanchas.getItems().addAll("Cancha 1 de Futbol", "Cancha 2 de Futbol", "Cancha de tenis");
        cbCanchas.setPromptText("Seleccione Cancha");

        Button btnReservar = new Button("Agendar Reserva");
        Button btnVerDisponibles = new Button("Ver Disponibles");
        Button btnCancelar = new Button("Cancelar Reserva");

        TextArea txtConsolaVisual = new TextArea();
        txtConsolaVisual.setEditable(false);

        btnVerDisponibles.setOnAction(e -> {
            String dia = cbDias.getValue();
            String cancha = cbCanchas.getValue();

            if (dia == null || dia.isEmpty() || cancha == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Atención");
                alerta.setHeaderText("Faltan datos");
                alerta.setContentText("Por favor, seleccione tanto un día como una cancha.");
                alerta.showAndWait();
            } else {

                String disponibles = sistema.getDisponiblesPorDiaYcancha(dia, cancha);
                txtConsolaVisual.appendText("\n--- Disponibles en " + cancha + " (" + dia + ") ---\n" + disponibles + "\n");
            }
        });

        btnReservar.setOnAction(e -> {
            String diaSelec = cbDias.getValue();
            String bloqueVisual = cbBloques.getValue();
            String nombreCancha = cbCanchas.getValue();

            if (diaSelec != null && bloqueVisual != null && nombreCancha != null) {
                int bloqueInt = Integer.parseInt(bloqueVisual.replace("-", ""));
                Cancha canchaSelec = new Cancha(nombreCancha, "Deporte");

                if (sistema.agendar(alumnoActual, canchaSelec, diaSelec, bloqueInt)) {
                    sistema.guardarEnArchivo("horario_canchas_modificado.txt");
                    txtConsolaVisual.appendText("¡ÉXITO! Reserva: " + nombreCancha + " | " + diaSelec + " | B" + bloqueVisual + "\n");
                } else {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setContentText("El horario seleccionado ya está ocupado.");
                    alerta.showAndWait();
                }
            } else {
                txtConsolaVisual.appendText("Por favor, complete todos los campos.\n");
            }
        });

        btnCancelar.setOnAction(e -> {
            String diaSelec = cbDias.getValue();
            String bloqueVisual = cbBloques.getValue();
            String nombreCancha = cbCanchas.getValue();

            if (diaSelec != null && bloqueVisual != null && nombreCancha != null) {
                int bloqueInt = Integer.parseInt(bloqueVisual.replace("-", ""));
                Cancha canchaSelec = new Cancha(nombreCancha, "Deporte");


                if (sistema.cancelarReserva(alumnoActual, canchaSelec, diaSelec, bloqueInt)) {
                    sistema.guardarEnArchivo("horario_canchas_modificado.txt");
                    txtConsolaVisual.appendText("CANCELACIÓN EXITOSA: " + nombreCancha + " | " + diaSelec + " | B" + bloqueVisual + "\n");
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setContentText("No se pudo cancelar. ¿Está seguro que esa reserva es suya?");
                    alerta.showAndWait();
                }
            } else {
                txtConsolaVisual.appendText("Seleccione los datos de la reserva a cancelar.\n");
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Cancha:"), 0, 0); grid.add(cbCanchas, 1, 0);
        grid.add(new Label("Día:"), 0, 1); grid.add(cbDias, 1, 1);
        grid.add(new Label("Bloque:"), 0, 2); grid.add(cbBloques, 1, 2);
        grid.add(btnReservar, 1, 3);
        grid.add(btnVerDisponibles, 1, 4);
        grid.add(btnCancelar, 1, 5);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(grid, txtConsolaVisual);
        primaryStage.setScene(new Scene(layout, 450, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


