#include "usuario.h"

#include <QDebug>
#include <math.h>
#include <stdlib.h>

Usuario::Usuario(QString nombre, QString apellidos): nombre(nombre), apellidos(apellidos) {
	esImagen = false;
}

Usuario::Usuario(): Usuario("", "") {
}
