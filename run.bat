@echo off
REM Limpiar carpeta bin
rmdir /s /q bin 2>nul

REM Compilar
javac -cp "miglayout15-swing.jar" -d bin src/**/*.java

REM Ejecutar
if %errorlevel% equ 0 (
    java -cp "miglayout15-swing.jar;bin" init.Iniciadora
) else (
    echo Error al compilar. Revisa los mensajes.
    pause
)