#ifndef DMODIFICARUSUARIOS_H
#define DMODIFICARUSUARIOS_H

#include "ui_dmodificarusuarios.h"
#include "usuario.h"
//#include "conexion.h"

#include <QDialog>
#include <QNetworkAccessManager>
#include <QNetworkReply>

class DModificarUsuarios : public QDialog, public Ui::DModificarUsuarios {
	Q_OBJECT

	public:
		DModificarUsuarios(QWidget *parent = NULL);
		//Conexion *conexionAPI;
		
		void mostrarFoto();
		void cargarDatosUsuario(QString idUsuario, QString nombre, QString apellidos, QString correo, QString contrasena, double latitud, double longitud);

	private:
    	QNetworkAccessManager* manager;
    	QString idUsuario;
	
	public slots:
		void slotModificarUsuario();
		void slotSalir();
};

#endif 
