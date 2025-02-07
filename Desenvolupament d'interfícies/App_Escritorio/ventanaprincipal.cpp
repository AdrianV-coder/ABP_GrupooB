#include "ventanaprincipal.h"

#include <QDebug>

VentanaPrincipal::VentanaPrincipal(QWidget *parent): QMainWindow(parent) {
	inicializarUsuarios()
	crearActions();
	crearMenus();
	
	resize(800, 600);
}

  /**********************************************/
 /*******     inicializarUsuarios()     ********/
/**********************************************/

void VentanaPrincipal::inicializarUsuarios() {
	QStringList nombresUsuarios = {"Adrian", "David", "Pau", "Juan", "Andreu"/*, "Raquel", "Dario", "Pepe"*/};
	QStringList apellidosUsuarios = {"Vernich Oltra", "Palet", "Tortosa Perales", "Talens Suñer", "Talens Cogollos"/*, "Raquel", "Dario", "Pepe"*/};

	for (int i = 0; i < nombresBolas.size(); i++) {
		Usuario *usuarioNuevo = new Usuario();
		
		usuarioNuevo -> nombre = nombresUsuarios.at(i % nombresUsuarios.length());
		usuarioNuevo -> apellidos = apellidosUsuarios.at(i % apellidosUsuarios.length());
		
		usuarios.append(usuarioNuevo);
	}
}

  /**********************************************/
 /**********     crearActions()       **********/
/**********************************************/

void VentanaPrincipal::crearActions() {
	accionListarUsuarios = new QAction("Listar Usuarios");
	//accionListarUsuarios -> setShortcut(QKeySequence::Close);
	accionListarUsuarios -> setIcon(QIcon("Imagenes/usuario.jpeg"));
}

  /**********************************************/
 /**********       crearMenus()       **********/
/**********************************************/

void VentanaPrincipal::crearMenus() {
	barraMenu = this -> menuBar();
	
	menuArchivo = barraMenu -> addMenu("Usuarios");
	menuArchivo -> addAction(accionListarUsuarios);
}

