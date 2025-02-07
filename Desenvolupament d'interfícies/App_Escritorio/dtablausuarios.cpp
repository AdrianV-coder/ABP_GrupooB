
#include "dtablausuarios.h"
#include <QDebug>

DTablaUsuarios::DTablaUsuarios(QWidget *parent): QDialog(parent){
	setupUi(this);
	
	ModeloTabla *modelo = new ModeloTabla(pBolasP);
	vista -> setModel(modelo);
	
	/*
	QTimer *temporizador = new QTimer();
	temporizador -> setInterval(500);
	temporizador -> setSingleShot(false);
	temporizador -> start();
	
	connect(temporizador, SIGNAL(timeout()), modelo, SLOT(slotTemporizador()));
	*/
}

ModeloTabla::ModeloTabla(/*QVector<Bola *> *pBolasPasadas,*/ QObject *parent) : QAbstractTableModel(parent) {
	//SpBolas = pBolasPasadas;
}

int	ModeloTabla::columnCount(const QModelIndex &parent) const {
	return 9; //id, nombre, apellidos, correo, contraseña, foto_perfil, ubicacion(solo nombre), articulosPublicados, articulosComprados
}
int	ModeloTabla::rowCount(const QModelIndex &parent) const {
	//return pBolas -> size();
	return 5;
}

QVariant ModeloTabla::data(const QModelIndex &index, int role) const {
	int fila = index.row();
	int columna = index.column();
	Bola *bola = pBolas -> at(fila);

	if (role != Qt::DisplayRole && role != Qt::BackgroundColorRole) {
		return QVariant();
	}

	switch (columna) {
		case 0: {
			if (role == Qt::DisplayRole) {
				return QString::number(bola -> posX);
			} else {
				return QColor ((bola -> posX / 800) * 255, 255 - (bola -> posX / 800) * 255, 0);
			}
		}
		
		case 1: {
			if (role == Qt::DisplayRole) {
				return QString::number(bola -> posY);
			} else {
				return QColor ((bola -> posY / 600) * 255, 255 - (bola -> posY / 600) * 255, 0);
			}
		}
		
		case 2: {
			return QString::number(bola -> velX);
		}
		
		case 3: {
			return QString::number(bola -> velY);
		}
	}
}
