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

## Requisitos de Ejecución

1. **JDK:** Tener instalado Java Development Kit (recomendado versión 17 o superior).
2. **JavaFX:** Asegurarse de tener configuradas las librerías de JavaFX en su IDE.
3. **Archivos:** El archivo `horario_canchas_modificado.txt` debe encontrarse en la carpeta raíz del proyecto para que el sistema cargue los datos correctamente.

## Autores

* **Vicente Agustín Araneda Sáez**
* **Alex Espinosa**
* **Antonio Bahamondes Velásquez**
