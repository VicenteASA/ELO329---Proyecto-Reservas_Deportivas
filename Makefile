#IMPORTANTE: Reemplace la ruta de JAVAFX_PATH con la ubicación local de sus librerías JavaFX.
JAVAFX_PATH = "ruta/a/tu/javafx-sdk/lib"
MODULES = javafx.controls

#Carpetas
SRC_DIR = src
OUT_DIR = out

#Archivos fuente
SOURCES = $(SRC_DIR)/Cancha.java \
          $(SRC_DIR)/GestorReservas.java \
          $(SRC_DIR)/Reserva.java \
          $(SRC_DIR)/Usuario.java \
          $(SRC_DIR)/MainGUI.java \
          $(SRC_DIR)/Launcher.java

#Clase principal
MAIN_CLASS = Launcher

all: compile

compile:
	@echo "Compilando archivos Java..."
	@mkdir -p $(OUT_DIR)
	javac --module-path $(JAVAFX_PATH) --add-modules $(MODULES) -d $(OUT_DIR) $(SOURCES)

run: compile
	@echo "Ejecutando Smart Canchas USM..."
	java --module-path $(JAVAFX_PATH) --add-modules $(MODULES) -cp $(OUT_DIR) $(MAIN_CLASS)

clean:
	@echo "Limpiando archivos compilados..."
	rm -rf $(OUT_DIR)