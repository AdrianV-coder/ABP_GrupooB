#include "conexion.h"

#include <QNetworkReply>
#include <QDebug>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonArray>
#include <QSslSocket>
#include <QSslConfiguration>
#include <QDebug>
#include <QJsonObject>

Conexion::Conexion () {
	qDebug() << "SSL Support:" << QSslSocket::supportsSsl();
	qDebug() << "OpenSSL Version:" << QSslSocket::sslLibraryVersionString();
	
	manager = new QNetworkAccessManager(this);
	connect(manager, SIGNAL(finished(QNetworkReply * )), this,SLOT(onRespuestaRecibida(QNetworkReply *)));
	
    /*
    // Realiza la solicitud GET al servidor
    QNetworkRequest request(QUrl("http://4.211.191.132:8080/App_Api/usuarios"));
    */
    
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
    
    //manager->get(request);
    peticionGet();
}

void Conexion::peticionGet() {
	// Realiza la solicitud GET al servidor
    QNetworkRequest request(QUrl("http://4.211.191.132:8080/App_Api/usuarios"));
    
	manager->get(request);
}

/*
void Conexion::peticionPost(QJsonObject json, QString id) {
	QNetworkAccessManager *manager = new QNetworkAccessManager(this);
     QString stringUrl = QString("http://4.211.191.132:8080/App_Api/usuarios/");
    
    QNetworkRequest request(QUrl(stringUrl + id));
    request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");

    QNetworkReply *reply = manager->put(request, QJsonDocument(json).toJson());

    connect(reply, &QNetworkReply::finished, [reply, this]() {
        if (reply->error() == QNetworkReply::NoError) {
            qDebug() << "Usuario actualizado correctamente.";
        } else {
            qDebug() << "Error al actualizar usuario:" << reply->errorString();
        }
        
        reply->deleteLater();
        
		peticionGet();
    });
}*/


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
