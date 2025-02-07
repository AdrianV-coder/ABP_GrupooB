#ifndef _USUARIO_H
#define _USUARIO_H

#include <QString>
#include <QColor>
#include <QPainter>
#include <QImage>

class Usuario {
	
	public:
		Usuario(QString nombre, QString apellidos);
		Usuario();
		
		int id;
		QString nombre, apellidos, correo, contrasena;
		QImage imagen;
		//Ubicacion *ubicacion;
		//Articulo *articulosPublicados, *articulosComprados;
		bool esImagen;
		
		void establecerImagen();
		void establecerImagen(QString rutaImagen);
};

Q_DECLARE_METATYPE(Usuario *)

#endif
