#include "bola.h"

#include <QDebug>

Usuario::Usuario(String nombre, String apellidos): nombre(nombre), apellidos(apellidos) {
	
	esImagen = false;
}

Usuario::Usuario(): Bola("", "") {
	
}
