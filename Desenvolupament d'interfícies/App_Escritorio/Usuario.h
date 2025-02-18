#ifndef _USUARIO_H
#define _USUARIO_H

#include <QString>
#include <QImage>

class Usuario {
	
	public:
		Usuario(int, QString, QString, QString, QString, double, double);
		Usuario(int);
		Usuario();
		
		int id;
		QString nombre, apellidos, correo, contrasena;
		QImage imagen;
		double latitud, longitud;
		//Articulo *articulosPublicados, *articulosComprados;
		bool esImagen;
		
		void establecerImagen();
		void establecerImagen(QString rutaImagen);
};

Q_DECLARE_METATYPE(Usuario *)

#endif
