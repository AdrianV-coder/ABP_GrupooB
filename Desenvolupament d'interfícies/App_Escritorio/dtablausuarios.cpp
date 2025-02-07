#include "dtablausuarios.h"

#include <QDebug>
#include <QTimer>
#include <QModelIndex>

DTablaUsuarios::DTablaUsuarios(QVector<Usuario *> *pUsuarioPasados, QWidget *parent): QDialog(parent){
	setupUi(this);
	
	ModeloTabla *modelo = new ModeloTabla(pUsuarioPasados);
	vista -> setModel(modelo);
	
	QTimer *temporizador = new QTimer();
	temporizador -> setInterval(10000);
	temporizador -> setSingleShot(false);
	temporizador -> start();
	
	connect(temporizador, SIGNAL(timeout()), modelo, SLOT(slotTemporizador()));
}



ModeloTabla::ModeloTabla(QVector<Usuario *> *pUsuarioPasados, QObject *parent) : QAbstractTableModel(parent) {
	pUsuario = pUsuarioPasados;
}

  /**********************************************/
 /**********       columnCount()      **********/
/**********************************************/

int	ModeloTabla::columnCount(const QModelIndex &parent) const {
	return 9; //id, nombre, apellidos, correo, contraseña, foto_perfil, ubicacion(solo nombre), articulosPublicados, articulosComprados
}

  /**********************************************/
 /**********        rowCount()        **********/
/**********************************************/

int	ModeloTabla::rowCount(const QModelIndex &parent) const {
	return pUsuario -> size();
}

  /**********************************************/
 /************        data()        ************/
/**********************************************/

QVariant ModeloTabla::data(const QModelIndex &index, int role) const {
	int fila = index.row();
	int columna = index.column();
	
	Usuario *usuario = pUsuario -> at(fila);

	if (role != Qt::DisplayRole) {
		return QVariant();
	}

	switch (columna) {
		case 0: {
			return QString::number(usuario -> id);
		}
		case 1: {
			return QString(usuario -> nombre);
		}
		case 2: {
			return QString(usuario -> apellidos);
		}
		case 3: {
			return QString("");
		}
		case 4: {
			return QString("");
		}
		case 5: {
			return QString("");
		}
		case 6: {
			return QString("");
		}
		case 7: {
			return QString::number(usuario -> id);
		}
		case 8: {
			return QString::number(usuario -> id);
		}
		default:
			return QVariant();
	}
}

  /**********************************************/
 /***********       setData()        ***********/
/**********************************************/

bool ModeloTabla::setData(const QModelIndex &index, const QVariant &value, int role)	{
	int fila = index.row();
	int columna = index.column();
	
	QString nombre = value.toString();
	QString apellidos = value.toString();
	QString correo = value.toString();
	QString contrasena = value.toString();
	
	int dato = value.toString().toInt();
	Usuario *usuario = pUsuario -> at(fila);

	switch (columna) {
		case 0: {
			usuario -> id = dato;
			
			return true;
		}
		case 1: {
			usuario -> nombre = nombre;
			
			return true;
		}
		case 2: {
			usuario -> apellidos = apellidos;
			
			return true;
		}
		case 3: {
			usuario -> correo = correo;
			
			return true;
		}
		case 4: {
			usuario -> contrasena = contrasena;
			
			return true;
		}
		case 5: {
			/*usuario -> nombre = nombre;
			
			return true;*/
		}
		case 6: {
			/*usuario -> nombre = nombre;
			
			return true;*/
		}
		case 7: {
			usuario -> id = dato;
			
			return true;
		}
		case 8: {
			usuario -> id = dato;
			
			return true;
		}
		
		default:
			qDebug() << "Tratando de hacer un cambio inválido" << fila;
	}
	
	return false;
}

  /**********************************************/
 /************        flags()        ***********/
/**********************************************/

Qt::ItemFlags ModeloTabla::flags(const QModelIndex &index) const {
	if (index.column() != 0 && index.column() != 6) {
		return Qt::ItemIsEditable | QAbstractTableModel::flags(index);
	} else {
		return QAbstractTableModel::flags(index);
	}
}

  /**********************************************/
 /*********        headerData()        *********/
/**********************************************/

QVariant ModeloTabla::headerData(int section, Qt::Orientation orientation, int role) const {
    if (role != Qt::DisplayRole) {
        return QVariant();
    }
    
    if (orientation == Qt::Horizontal) {
        // Aseguramos que la lista tenga la misma cantidad de elementos que columnCount()
        QStringList lista = {"ID", "Nombre", "Apellidos", "Correo", "Contraseña", "Foto de Perfil", "Ubicación", "Artículos Publicados", "Artículos Comprados"};
        
        // Verifica que section esté dentro del rango de la lista
        if (section >= 0 && section < lista.size()) {
            return lista.at(section);
        } else {
            return QVariant();
        }
    } else if (orientation == Qt::Vertical) {
        // Para el encabezado vertical, usaremos el nombre del usuario correspondiente a esa fila.
        if (section >= 0 && section < pUsuario->size()) {
            return QString(pUsuario->at(section)->nombre);
        } else {
            return QVariant();
        }
    }
    
    return QVariant();
}

  /**********************************************/
 /*******       slotTemporizador()       *******/
/**********************************************/

void ModeloTabla::slotTemporizador(){
	QModelIndex topLeft = index(0, 0);
	QModelIndex bottomRight = index (-1, 3);
	
	beginResetModel();
	
	emit layoutChanged();
	emit dataChanged(topLeft, bottomRight);
}
