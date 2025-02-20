#ifndef DANYADIRUSUARIOS_H
#define DANYADIRUSUARIOS_H

#include "ui_danyadirusuarios.h"

#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QDialog>

class DAnyadirUsuarios : public QDialog, public Ui::DAnyadirUsuarios {
    Q_OBJECT

	public:
    	DAnyadirUsuarios(QWidget *parent = nullptr);
    	void mostrarFoto();

	private slots:
	    void slotAnyadirUsuario();  // Método para enviar los datos
    	void onRespuestaRecibida(QNetworkReply* reply);
    	void slotSalir();

	private:
    	QNetworkAccessManager* manager;
};

#endif

