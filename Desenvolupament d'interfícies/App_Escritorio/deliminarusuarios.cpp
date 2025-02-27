#include "deliminarusuarios.h"

#include <QJsonDocument>
#include <QJsonObject>
#include <QDebug>
#include <QGraphicsView>
#include <QGraphicsScene>
#include <QGraphicsPixmapItem>
#include <QPixmap>
#include <QMessageBox>

DEliminarUsuarios::DEliminarUsuarios(QWidget *parent): QDialog(parent){
	setupUi(this);
	mostrarFoto();
    manager = new QNetworkAccessManager(this);

    connect(btnAceptar, SIGNAL(pressed()), this, SLOT(slotEliminarUsuario()));
    connect(btnCancelar, SIGNAL(pressed()), this, SLOT(slotSalir()));
}

void DEliminarUsuarios::mostrarFoto() {
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
    gvFoto -> setScene(scene);
    gvFoto -> show();
}

void DEliminarUsuarios::slotEliminarUsuario() {	
	QString idUsuario = leId -> text();  // Recoge el ID del QLineEdit
    if (idUsuario.isEmpty()) {
        qDebug() << "Error: El ID está vacío.";
        return;
    }

    // Configurar la solicitud DELETE a la API
    QNetworkAccessManager *manager = new QNetworkAccessManager(this);
    QString stringUrl = QString("http://4.211.191.132:8080/App_Api/usuarios/");
    
    QNetworkRequest request(QUrl(stringUrl + idUsuario));
    request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");

    // Enviar la solicitud DELETE
    QNetworkReply *reply = manager->deleteResource(request);
    connect(reply, &QNetworkReply::finished, [reply, this]() {
        if (reply->error() == QNetworkReply::NoError) {
            qDebug() << "Usuario eliminado correctamente.";
            emit usuarioActualizado();
        } else {
            qDebug() << "Error al eliminar usuario:" << reply->errorString();
            
            QMessageBox::warning(this, "Error al eliminar el usuario", "El ID del usuario introducido no está en la base de datos", QMessageBox::Ok | QMessageBox::Cancel);
        }
        
        reply->deleteLater();
        emit actualizarTablaUsaurios();
        this->close();  // Cierra el diálogo después de procesar la respuesta
    });
}

void DEliminarUsuarios::slotSalir() {
    this->close();
}

void DEliminarUsuarios::onRespuestaRecibida(QNetworkReply* reply) {
    if (reply->error() == QNetworkReply::NoError) {
        qDebug() << "Usuario eliminado con éxito:" << reply->readAll();
    } else {
        qDebug() << "Error al eliminar el usuario:" << reply->errorString();
        qDebug() << "Código de error:" << reply->attribute(QNetworkRequest::HttpStatusCodeAttribute).toInt();
        qDebug() << "Respuesta completa del servidor:" << reply->readAll();
    }
}
