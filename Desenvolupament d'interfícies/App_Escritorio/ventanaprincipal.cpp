#include "ventanaprincipal.h"

#include <QDebug>

VentanaPrincipal::VentanaPrincipal(QWidget *parent): QMainWindow(parent) {
	qRegisterMetaType<Usuario *>("Usuario *");

	dTablaUsuarios = nullptr;
	trayIcon = nullptr;

	inicializarUsuarios();
	crearActions();
	crearMenus();
	
	resize(800, 600);
}

  /**********************************************/
 /*******     inicializarUsuarios()     ********/
/**********************************************/

void VentanaPrincipal::inicializarUsuarios() {
	QStringList nombresUsuarios = {"Adrian", "David", "Pau", "Juan", "Andreu"/*, "Raquel", "Dario", "Pepe"*/};
	QStringList apellidosUsuarios = {"Vernich Oltra", "Palet Molla", "Tortosa Perales", "Talens Suñer", "Talens Cogollos"/*, "Raquel", "Dario", "Pepe"*/};

	for (int i = 0; i < nombresUsuarios.size(); i++) {
		Usuario *usuarioNuevo = new Usuario(nombresUsuarios.at(i), apellidosUsuarios.at(i));
		
		static int nextId = 1;
		usuarioNuevo -> id = nextId++;
		
		usuarios.append(usuarioNuevo);
	}
}

  /**********************************************/
 /**********     crearActions()       **********/
/**********************************************/

void VentanaPrincipal::crearActions() {
	//accionListarUsuarios -> setShortcut(QKeySequence::Close);
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
 /********     slotTablaUsuarios()      ********/
/**********************************************/

void VentanaPrincipal::slotTablaUsuarios() {
	if (dTablaUsuarios == nullptr) {
		dTablaUsuarios = new DTablaUsuarios(&usuarios);
	}
	
	dTablaUsuarios -> show();
}

