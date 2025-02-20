#include "ventanaprincipal.h"

#include <QDebug>
#include <QGraphicsView>
#include <QGraphicsScene>
#include <QGraphicsPixmapItem>
#include <QPixmap>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonArray>
#include <QJsonParseError>

VentanaPrincipal::VentanaPrincipal(QWidget *parent): QMainWindow(parent) {
	qRegisterMetaType<Usuario *>("Usuario *");
	
	dTablaUsuarios = nullptr;
	
	trayIcon = nullptr;

	crearActions();
	crearMenus();
	crearFotoYTexto();
	

	resize(800, 600);
}

  /**********************************************/
 /**********     crearActions()       **********/
/**********************************************/

void VentanaPrincipal::crearActions() {
	accionListarUsuarios = new QAction("Listar Usuarios", this);
	connect(accionListarUsuarios, SIGNAL(triggered()), this, SLOT(slotTablaUsuarios()));
}

  /**********************************************/
 /**********       crearMenus()       **********/
/**********************************************/

void VentanaPrincipal::crearMenus() {
	barraMenu = this -> menuBar();
	
	menuArchivo = barraMenu -> addMenu("Usuarios");
	menuArchivo -> addAction(accionListarUsuarios);
	
	if (QSystemTrayIcon::isSystemTrayAvailable()) {
		trayIcon = new QSystemTrayIcon(this);
		QIcon icono ("imagenes/usuario.jpeg");
		
		if (icono.isNull()) {
			qDebug() << "El icono no es valido";
		}
		
		trayIcon -> setIcon(icono);
		trayIcon -> setContextMenu(menuArchivo);
		trayIcon -> show();
	} else {
		qDebug() << "Tu sistema no soporta systemTrayInfo";
	}
}

  /**********************************************/
 /*********     crearFotoYTexto()      *********/
/**********************************************/

void VentanaPrincipal::crearFotoYTexto() {
	// Crear un contenedor para agrupar el QGraphicsView y el QLabel.
    QWidget *container = new QWidget(this);
    QVBoxLayout *layout = new QVBoxLayout(container);
    
    // Crear y configurar el QGraphicsView con la imagen.
    QGraphicsView *graphicsView = new QGraphicsView(container);
    QGraphicsScene *scene = new QGraphicsScene(graphicsView);
    
    QPixmap pixmap("imagenes/logo.png");
    if (pixmap.isNull()) {
        qDebug() << "Error: No se pudo cargar la imagen.";
    }
    
    QGraphicsPixmapItem *item = new QGraphicsPixmapItem(pixmap);
    scene->addItem(item);
    graphicsView->setScene(scene);
    
    // Añadir el QGraphicsView al layout.
    layout->addWidget(graphicsView);
    
    // Crear el QLabel y configurarlo.
    QLabel *nombreProyecto = new QLabel("HECHO POR PAU, DAVID Y ADRIÁN", container);
    
    // Opcional: personalizar el label (fuente, alineación, etc.)
    nombreProyecto->setAlignment(Qt::AlignCenter);
    
    // Añadir el QLabel al layout, debajo del QGraphicsView.
    layout->addWidget(nombreProyecto);
    
    // Asignar el layout al contenedor y establecerlo como widget central.
    container->setLayout(layout);
    setCentralWidget(container);
}

  /**********************************************/
 /********     slotTablaUsuarios()      ********/
/**********************************************/

void VentanaPrincipal::slotTablaUsuarios() {
    if (dTablaUsuarios == nullptr) {
        dTablaUsuarios = new DTablaUsuarios(&usuarios);
    }

    dTablaUsuarios->show();
}

