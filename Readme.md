# Smart Canchas USM

Sistema de gestión de reservas de recintos deportivos para la Universidad Técnica Federico Santa María. Este proyecto permite a los estudiantes agendar, consultar y cancelar reservas de canchas mediante una interfaz gráfica, garantizando la persistencia de los datos en tiempo real.

## Características Principales

* **Autenticación:** Sistema de login mediante Rol USM (formato XXXXXXXXX-X) con validación de formato.
* **Gestión de Reservas:** Capacidad de agendar y cancelar bloques deportivos.
* **Persistencia de Datos:** Todos los cambios se guardan automáticamente en el archivo `horario_canchas_modificado.txt`.
* **Lógica de Negocio:**
    * Límite de hasta 3 reservas activas por usuario.
    * Validación de disponibilidad en tiempo real.
    * Control de concurrencia para evitar doble agendamiento.
* **Interfaz Gráfica:** Desarrollada en **JavaFX** para una experiencia de usuario intuitiva.

## Requisitos de Ejecución y Entorno

1. **JDK:** El proyecto fue desarrollado y probado utilizando **Oracle OpenJDK 26**.
2. **Librerías de Interfaz:** El proyecto requiere **JavaFX**. Se configuró utilizando **Maven con JavaFX versión 21** (`org.openjfx:javafx-controls:21`). Si utiliza un IDE como IntelliJ IDEA, asegúrese de recargar las dependencias de Maven.
3. **Persistencia:** El archivo `horario_canchas_modificado.txt` debe encontrarse obligatoriamente en la carpeta raíz del proyecto al momento de ejecutarlo.

## Instrucciones de Compilación y Ejecución (IntelliJ IDEA)

Debido a la integración con JavaFX mediante Maven, siga estos pasos para ejecutar el sistema correctamente sin errores de módulos:

1. **Clonar el repositorio:** Descargue el proyecto y ábralo en su IDE.
2. **Verificar el JDK:** Vaya a `File > Project Structure > Project` y asegúrese de tener asignado **Oracle OpenJDK 26** (o el SDK correspondiente de su entorno).
3. **Configurar dependencias:** Permita que IntelliJ sincronice las dependencias (Maven recargará automáticamente los módulos de JavaFX 21).
4. **Punto de Entrada (¡Importante!):** **NO** ejecute la clase `MainGUI`. Para iniciar el programa, debe ejecutar la clase **`Launcher.java`**. Esta clase actúa como un envoltorio que inicializa correctamente el entorno gráfico de JavaFX.
5. **Ejecutar:** Haga clic derecho sobre `Launcher.java` en la carpeta `src` y seleccione `Run 'Launcher.main()'`.

### Uso del Makefile (IMPORTANTE: UTILIZAR SU PROPIA RUTA LOCAL DE LIBRERÍAS JAVAFX)

Para ejecutarlo vía consola:
1. Abra el archivo `Makefile`.
2. Edite la variable `JAVAFX_PATH` con la ruta local donde se encuentran sus librerías de JavaFX. Si usted también utiliza Maven, la ruta típicamente será su repositorio local, por ejemplo:
   `JAVAFX_PATH = "~/.m2/repository/org/openjfx/javafx-controls/21/"` o la ruta hacia su SDK descargado manualmente.
3. Ejecute `make compile` para compilar las clases en la carpeta `out`.
4. Ejecute `make run` para iniciar la aplicación.
5. Ejecute `make clean` para eliminar los archivos compilados generados.

## Autores

* **Vicente Agustín Araneda Sáez** 
* **Alex Espinosa**
* **Antonio Bahamondes Velásquez**
