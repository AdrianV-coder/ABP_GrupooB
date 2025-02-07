#ifndef _VENTANAPRINCIPAL_H
#define _VENTANAPRINCIPAL_H

#include <QMainWindow>
#include <QAction>
#include <QMenuBar>
#include <QMenu>

class VentanaPrincipal : public QMainWindow {
	Q_OBJECT
	
	public:
		VentanaPrincipal(QWidget *parent = NULL);
		QVector<Usuario *> usuarios;
		
		QMenuBar *barraMenu;
		QMenu *menuArchivo;
		QAction *accionListarUsuarios;
		
		void crearActions();
		void crearMenus();
		void inicializarUsuarios();
		
	public slots:
};

#endif
