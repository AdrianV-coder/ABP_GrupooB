#include "delegirusuarios.h"
#include "ui_delegirusuarios.h"

#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QNetworkRequest>
#include <QUrl>
#include <QJsonDocument>
#include <QJsonObject>
#include <QDebug>
#include <QMessageBox>

DElegirUsuarios::DElegirUsuarios(QWidget *parent): QDialog(parent){
	setupUi(this);
	
	dModificarUsuarios = nullptr;
	
	connect(btnAceptar, SIGNAL(pressed()), this, SLOT(slotElegirUsuario()));
	connect(btnCancelar, SIGNAL(pressed()), this, SLOT(slotSalir()));
}

void DElegirUsuarios::slotSalir() {
    this->close();
}

void DElegirUsuarios::slotElegirUsuario() {
    QString idUsuario = leId_Elegir -> text();  // Obtiene el ID ingresado
    if (idUsuario.isEmpty()) {
        qDebug() << "Error: El ID está vacío.";
        return;
    }

    // Crear y configurar la solicitud GET a la API
    QNetworkAccessManager *manager = new QNetworkAccessManager(this);
    QUrl url("http://4.211.191.132:8080/App_Api/usuarios/" + idUsuario);  // Cambia la URL a la real
    QNetworkRequest request(url);
    request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");

    // Enviar la solicitud GET
    QNetworkReply *reply = manager->get(request);

    connect(reply, &QNetworkReply::finished, [this, reply, idUsuario]() {
        if (reply->error() == QNetworkReply::NoError) {
            QByteArray responseData = reply->readAll();
            QJsonDocument jsonDoc = QJsonDocument::fromJson(responseData);
            if (!jsonDoc.isObject()) {
                qDebug() << "Error: Respuesta JSON no válida.";
                reply->deleteLater();
                return;
            }

            QJsonObject jsonObj = jsonDoc.object();
            QString nombre = jsonObj["nombre"].toString();
            QString apellidos = jsonObj["apellidos"].toString();
            QString correo = jsonObj["correo"].toString();
            QString contrasena = jsonObj["contrasena"].toString();
            double latitud = jsonObj["latitud"].toDouble();
            double longitud = jsonObj["longitud"].toDouble();

            // Abrir la ventana de modificación con los datos obtenidos
            DModificarUsuarios *modificarUsuarios = new DModificarUsuarios(this);
			
            modificarUsuarios->cargarDatosUsuario(idUsuario, nombre, apellidos, correo, contrasena, latitud, longitud);
            modificarUsuarios->exec();
        } else {
            qDebug() << "Error al obtener usuario:" << reply->errorString();
            
            QMessageBox::warning(this, "Error al obtener el usuario", "El ID del usuario introducido no está en la base de datos", QMessageBox::Ok | QMessageBox::Cancel);
            
        }
        
        reply->deleteLater();
        this->close();
    });
}

