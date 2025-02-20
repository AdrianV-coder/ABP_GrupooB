#ifndef DELEGIRUSUARIOS_H
#define DELEGIRUSUARIOS_H

#include "ui_delegirusuarios.h"
#include "usuario.h"
#include "dmodificarusuarios.h"

#include <QVector>
#include <QDialog>
#include <QHBoxLayout>

class DElegirUsuarios : public QDialog, public Ui::DElegirUsuarios {
	Q_OBJECT

	public:
		DElegirUsuarios(QWidget *parent = NULL);
		
		DModificarUsuarios *dModificarUsuarios;
		
		void actualizarTablaPrincipal();

	public slots:
		void slotSalir();
		void slotElegirUsuario();
};

#endif 
