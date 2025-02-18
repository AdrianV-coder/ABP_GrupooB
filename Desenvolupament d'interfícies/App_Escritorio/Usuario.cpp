#include "usuario.h"

#include <QDebug>
#include <math.h>
#include <stdlib.h>

Usuario::Usuario(int id, QString nombre, QString apellidos, QString correo, QString contrasena, double latitud, double longitud): id(id), nombre(nombre), apellidos(apellidos), correo(correo), contrasena(contrasena), latitud(latitud), longitud(longitud) {
}

Usuario::Usuario() {
}
