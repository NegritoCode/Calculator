#!/bin/bash

# Limpiar carpeta bin (opcional)
rm -rf bin

# Compilar
javac -cp "miglayout15-swing.jar" -d bin src/**/*.java

# Ejecutar
if [ $? -eq 0 ]; then
    java -cp "miglayout15-swing.jar:bin" init.Iniciadora
else
    echo "Error al compilar. Revisa los mensajes."
    read -p "Presiona Enter para salir..."
fi