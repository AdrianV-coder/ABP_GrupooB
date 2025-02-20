#include "conexion.h"

#include <QNetworkReply>
#include <QDebug>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonArray>
#include <QSslSocket>
#include <QSslConfiguration>
#include <QDebug>

Conexion::Conexion () {
	qDebug() << "SSL Support:" << QSslSocket::supportsSsl();
	qDebug() << "OpenSSL Version:" << QSslSocket::sslLibraryVersionString();
	
	manager = new QNetworkAccessManager(this);
	connect(manager, SIGNAL(finished(QNetworkReply * )), this,SLOT(onRespuestaRecibida(QNetworkReply *)));
    
    // Realiza la solicitud GET al servidor
    QNetworkRequest request(QUrl("http://api.grupob.com/App_Api/usuarios"));
    // Mostramos los errores... ¡ ojo ! lambda functions
	connect(manager, &QNetworkAccessManager::sslErrors,
        [](QNetworkReply *reply, const QList<QSslError> &errors) {
            qDebug() << "SSL Errors:";
            for (const QSslError &error : errors) {
                qDebug() << error.errorString();
            }
        });
        
     // Establecemos que ignoraremos errores en ssl ¡ojo! lambda functions
     connect(manager, &QNetworkAccessManager::sslErrors,
        [](QNetworkReply *reply, const QList<QSslError> &errors) {
            qDebug() << "Ignoring SSL errors:" << errors;
            reply->ignoreSslErrors();
        });
    
    manager->get(request);
}

void Conexion::onRespuestaRecibida(QNetworkReply* reply) {
	qDebug() << "He entrado en onRespuestaRecibida";
    if (reply->error() == QNetworkReply::NoError) {
        dato = reply->readAll();
        qDebug() << "Datos recibidos:" << dato;
        emit datosActualizados();  // Notifica a VentanaPrincipal para procesar los datos
    } else {
        qDebug() << "Error en la solicitud:" << reply->errorString();
    }
    reply->deleteLater();
}

QByteArray Conexion::getDatos() {
    return dato;
}
