#ifndef DELIMINARUSUARIOS_H
#define DELIMINARUSUARIOS_H

#include "ui_deliminarusuarios.h"
#include "usuario.h"

#include <QVector>
#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QDialog>
#include <QHBoxLayout>

class DEliminarUsuarios : public QDialog, public Ui::DEliminarUsuarios {
	Q_OBJECT

	public:
		DEliminarUsuarios(QWidget *parent = NULL);
		QVector<Usuario *> *pUsuario;
		
		void mostrarFoto();

	private slots:
	    void slotEliminarUsuario();
    	void onRespuestaRecibida(QNetworkReply* reply);
    	void slotSalir();

	private:
    	QNetworkAccessManager* manager;
    	
    signals:
	    void usuarioActualizado();
    	void actualizarTablaUsaurios();
};

#endif 
