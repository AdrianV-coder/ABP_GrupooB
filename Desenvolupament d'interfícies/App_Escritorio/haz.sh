#!/bin/bash
echo "ejecutando haz"

# Limpia la compilación anterior
make clean

# Elimina archivos .pro existentes
rm -f *.pro

# Genera un nuevo archivo .pro
qmake -project

# Verifica si se generó el archivo .pro
fichero=$(ls *.pro 2>/dev/null)
if [ -z "$fichero" ]; then
  echo "Error: No se generó el archivo .pro."
  exit 1
fi

# Añade los módulos necesarios al archivo .pro
echo "QT += widgets network websockets" >> "$fichero"

# Ejecuta qmake y make, y detiene el script si falla en algún punto
if qmake && make; then
  echo "Compilación exitosa. Ejecutando el programa..."
  ./ABP_GrupoB
else
  echo "Error durante la compilación."
  exit 1
fi

