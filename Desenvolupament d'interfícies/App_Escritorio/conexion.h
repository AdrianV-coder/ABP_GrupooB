#ifndef CONEXION_H
#define CONEXION_H

#include "usuario.h"

#include <QNetworkAccessManager>

/* conexión a una api externa */
class Conexion : public QObject {
	Q_OBJECT
	public:
    	Conexion();
    	QVector<Usuario *> usuarios;
    
		QString text;
		QByteArray getDatos();
		void peticionGet();
		//void peticionPost(QJsonObject json, QString id);

	private:
    	QNetworkAccessManager* manager;
    	QByteArray dato;
    	
	public slots:
		void onRespuestaRecibida(QNetworkReply*);
	
	signals:
 	  	void datosActualizados();   	
};

#endif
