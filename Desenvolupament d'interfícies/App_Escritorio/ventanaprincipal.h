#ifndef _VENTANAPRINCIPAL_H
#define _VENTANAPRINCIPAL_H

#include "usuario.h"
#include "dtablausuarios.h"

#include <QMainWindow>
#include <QAction>
#include <QMenuBar>
#include <QMenu>
#include <QSystemTrayIcon>

class VentanaPrincipal : public QMainWindow {
	Q_OBJECT
	
	public:
		VentanaPrincipal(QWidget *parent = NULL);
		QVector<Usuario *> usuarios;
		
		QMenuBar *barraMenu;
		QMenu *menuArchivo;
		QAction *accionListarUsuarios;
		QSystemTrayIcon *trayIcon;
		
		DTablaUsuarios *dTablaUsuarios;
		
		void crearActions();
		void crearMenus();
		void inicializarUsuarios();
		
	public slots:
		void slotTablaUsuarios();
};

#endif
