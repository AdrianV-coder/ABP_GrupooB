#ifndef CONEXION_H
#define CONEXION_H

#include "usuario.h"

#include <QNetworkAccessManager>
//#include <QWebSocket>

/* conexión a una api externa */
class Conexion : public QObject {
	Q_OBJECT
	public:
    	Conexion();
    	QVector<Usuario *> usuarios;
    
		//QNetworkAccessManager *manager;
		QString text;
		//QByteArray dato;
		QByteArray getDatos();

	private:
    	//QWebSocket *webSocket;
    	QNetworkAccessManager* manager;
    	QByteArray dato;
    	
	public slots:
		/*void slotRespuestaFinalizada(QNetworkReply *);
		void onMensajeRecibido(const QString &mensaje);*/
		void onRespuestaRecibida(QNetworkReply*);
	
	signals:
    	void datosActualizados();
};

#endif
