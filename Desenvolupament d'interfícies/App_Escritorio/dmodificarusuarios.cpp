#include "dmodificarusuarios.h"

#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QNetworkRequest>
#include <QUrl>
#include <QJsonDocument>
#include <QJsonObject>
#include <QDebug>
#include <QGraphicsView>
#include <QGraphicsScene>
#include <QGraphicsPixmapItem>
#include <QPixmap>
#include <QMessageBox>

DModificarUsuarios::DModificarUsuarios(QWidget *parent): QDialog(parent){
	setupUi(this);
	mostrarFoto();
    
    connect(btnCancelar, SIGNAL(pressed()), this, SLOT(slotSalir()));
    connect(btnAceptar, SIGNAL(pressed()), this, SLOT(slotModificarUsuario()));
}

void DModificarUsuarios::mostrarFoto() {
    // Crear y configurar el QGraphicsView con la imagen
    QGraphicsScene *scene = new QGraphicsScene(this);
    
    QPixmap pixmap("imagenes/image.png");  // Cambia el path si es necesario
    if (pixmap.isNull()) {
        qDebug() << "Error: No se pudo cargar la imagen.";
        return;
    }
    
    QGraphicsPixmapItem *item = new QGraphicsPixmapItem(pixmap);
    scene -> addItem(item);

    // Supongamos que tienes un QGraphicsView en tu archivo .ui llamado "vistaFoto"
    gvFotoModificar -> setScene(scene);
    gvFotoModificar -> show();
}

void DModificarUsuarios::slotSalir() {
    this->close();
}

void DModificarUsuarios::cargarDatosUsuario(QString idUsuario, QString nombre, QString apellidos, QString correo, QString contrasena, double latitud, double longitud) {
	this->idUsuario = idUsuario;

    leNombre->setText(nombre);
    leApellidos->setText(apellidos);
    leCorreoElectronico->setText(correo);
    leContrasena->setText(contrasena);
    leLatitud->setText(QString::number(latitud));
    leLongitud->setText(QString::number(longitud));
}

void DModificarUsuarios::slotModificarUsuario() {
    QJsonObject json;
    json["nombre"] = leNombre -> text();
    json["apellidos"] = leApellidos -> text();
    json["correo"] = leCorreoElectronico -> text();
    json["contrasena"] = leContrasena -> text();
    json["latitud"] = leLatitud -> text().toDouble();
    json["longitud"] = leLongitud -> text().toDouble();

	
	/*conexionAPI -> peticionPost(json, this -> idUsuario);
	
	this->close();*/

	
    QNetworkAccessManager *manager = new QNetworkAccessManager(this);
    QUrl url("http://4.211.191.132:8080/App_Api/usuarios/" + idUsuario);
    QNetworkRequest request(url);
    request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");

    QNetworkReply *reply = manager->put(request, QJsonDocument(json).toJson());

    connect(reply, &QNetworkReply::finished, [reply, this]() {
        if (reply->error() == QNetworkReply::NoError) {
            qDebug() << "Usuario actualizado correctamente.";
        } else {
            qDebug() << "Error al actualizar usuario:" << reply->errorString();
            
            QMessageBox::warning(this, "Error al actualizar el usuario", "ERROR, el usuario no se ha podido modificar", QMessageBox::Ok | QMessageBox::Cancel);
        }
        
        reply->deleteLater();
        this->close();
    });
}

