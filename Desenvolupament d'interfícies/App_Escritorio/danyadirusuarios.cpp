#include "danyadirusuarios.h"

#include <QJsonDocument>
#include <QJsonObject>
#include <QDebug>
#include <QGraphicsView>
#include <QGraphicsScene>
#include <QGraphicsPixmapItem>
#include <QPixmap>

DAnyadirUsuarios::DAnyadirUsuarios(QWidget *parent) : QDialog(parent) {
    setupUi(this);
    mostrarFoto();
    manager = new QNetworkAccessManager(this);

    connect(btnAceptar, SIGNAL(pressed()), this, SLOT(slotAnyadirUsuario()));
    connect(btnCancelar, SIGNAL(pressed()), this, SLOT(slotSalir()));
}

void DAnyadirUsuarios::mostrarFoto() {
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
    gvFotoAnyadir -> setScene(scene);
    gvFotoAnyadir -> show();
}

void DAnyadirUsuarios::slotAnyadirUsuario() {
    // Recoger los datos del formulario
    QString nombre = leNombre_Anyadir->text();
    QString apellidos = leApellidos_Anyadir->text();
    QString correo = leCorreo_Anyadir->text();
    QString contrasena = leContrasena_Anyadir -> text();
    //QString telefono = leTelefono_Anyadir->text();
    QString localidad = leLocalidad_Anyadir->text();

    // Crear un objeto JSON con los datos
    QJsonObject json;
    json["nombre"] = nombre;
    json["apellidos"] = apellidos;
    json["correo"] = correo;
    json["contrasena"] = contrasena;
    json["localidad"] = localidad;

    QJsonDocument doc(json);
    QByteArray datos = doc.toJson();

	/*   MÉTODO POST   */

    // Configurar la solicitud POST
    QNetworkRequest request(QUrl("http://api.grupob.com/App_Api/usuarios"));
    request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");
    
    // Enviar la solicitud POST
    QNetworkReply* reply = manager->post(request, datos);
    connect(reply, &QNetworkReply::finished, this, [reply, this]() {
    	this->onRespuestaRecibida(reply);
    	this->close();  // Cierra el diálogo después de procesar la respuesta
	});

}

void DAnyadirUsuarios::slotSalir() {
    this->close();
}

void DAnyadirUsuarios::onRespuestaRecibida(QNetworkReply* reply) {
    if (reply->error() == QNetworkReply::NoError) {
        qDebug() << "Usuario añadido con éxito:" << reply->readAll();
    } else {
        qDebug() << "Error al añadir usuario:" << reply->errorString();
        qDebug() << "Código de error:" << reply->attribute(QNetworkRequest::HttpStatusCodeAttribute).toInt();
        qDebug() << "Respuesta completa del servidor:" << reply->readAll();
    }
    
    reply->deleteLater(); // Marca el objeto para su eliminación
}


