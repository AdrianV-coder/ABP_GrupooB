#include "dtablausuarios.h"

#include <QDebug>
#include <QTimer>
#include <QModelIndex>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonArray>
#include <QJsonParseError>

DTablaUsuarios::DTablaUsuarios(QVector<Usuario *> *pUsuarioPasados, QWidget *parent): QDialog(parent){
	setupUi(this);
	
	pUsuario = pUsuarioPasados;
	
	conexionAPI  = nullptr;
	dEliminarUsuarios = nullptr;
	dElegirUsuarios = nullptr;
	dAnyadirUsuarios = nullptr;
	
	
	conexionAPI = new Conexion();
    
    connect(conexionAPI, &Conexion::datosActualizados, this, [this]() {
    	QByteArray datos = conexionAPI->getDatos();
    	procesarDatos(datos);  // Reutiliza tu función procesarDatos
	});
	
	modelo = new ModeloTabla(pUsuarioPasados);
	vista -> setModel(modelo);
	
	connect(btnInicio, SIGNAL(pressed()), this, SLOT(slotInicio()));
	connect(btnEliminar, SIGNAL(pressed()), this, SLOT(slotEliminarUsuario()));
	connect(btnModificar, SIGNAL(pressed()), this, SLOT(slotElegirUsuario()));
	connect(btnInsertar, SIGNAL(pressed()), this, SLOT(slotAnyadirUsuario()));
}


  /**********************************************/
 /**********     procesarDatos()      **********/
/**********************************************/

void DTablaUsuarios::procesarDatos(QByteArray datos) {
    // Intentamos parsear el JSON recibido
    QJsonParseError parseError;
    QJsonDocument doc = QJsonDocument::fromJson(datos, &parseError);

    if (parseError.error != QJsonParseError::NoError) {
        qDebug() << "Error al parsear JSON:" << parseError.errorString();
        return;
    }

    // Verificamos si el JSON es un array
    if (!doc.isArray()) {
        qDebug() << "El JSON recibido no es un array.";
        return;
    }

    QJsonArray array = doc.array();

    // Limpia el QVector actual para evitar usuarios duplicados (y liberar memoria)
    qDeleteAll(pUsuario -> begin(), pUsuario -> end());
	pUsuario -> clear();

    // Recorremos cada elemento del array y creamos un Usuario
    for (int i = 0; i < array.size(); ++i) {
        QJsonValue valor = array.at(i);

        if (!valor.isObject()) {
            qDebug() << "Elemento en el array no es un objeto JSON. Se omite.";
            continue;
        }

        QJsonObject obj = valor.toObject();

        // Verificamos que existan los campos necesarios
        QStringList camposNecesarios = {"id", "nombre", "apellidos", "correo", "latitud", "longitud"};
        bool camposCompletos = std::all_of(camposNecesarios.begin(), camposNecesarios.end(), [&obj](const QString &campo) {
            return obj.contains(campo);
        });

        if (!camposCompletos) {
            qDebug() << "Objeto JSON incompleto. Se omite.";
            continue;
        }

        // Extraemos los valores
        int id = obj["id"].toInt();
        QString nombre = obj["nombre"].toString();
        QString apellidos = obj["apellidos"].toString();
        QString correo = obj["correo"].toString();
        QString contrasena = obj["contrasena"].toString();
        double latitud = obj["latitud"].toDouble();
        double longitud = obj["longitud"].toDouble();

        // Creamos el usuario y le asignamos un ID
        Usuario *nuevoUsuario = new Usuario(id, nombre, apellidos, correo, contrasena, latitud, longitud);
        nuevoUsuario->id = id;

        // Añadimos el usuario al QVector
        pUsuario -> append(nuevoUsuario);
        
        modelo -> actualizar();
    }

    qDebug() << "Se han procesado" << pUsuario -> size() << "usuarios desde el JSON.";
}

void DTablaUsuarios::slotInicio() {
	// Cierra la ventana actual
    this->close();
}

void DTablaUsuarios::slotEliminarUsuario() {
	if (dEliminarUsuarios == nullptr) {
		dEliminarUsuarios = new DEliminarUsuarios();
	}
	
	dEliminarUsuarios -> show();
}

void DTablaUsuarios::slotElegirUsuario() {
	if (dElegirUsuarios == nullptr) {
		dElegirUsuarios = new DElegirUsuarios();
	}
	
	dElegirUsuarios -> show();
}

void DTablaUsuarios::slotAnyadirUsuario() {
	if (dAnyadirUsuarios == nullptr) {
		dAnyadirUsuarios = new DAnyadirUsuarios();
	}
	
	dAnyadirUsuarios -> show();
}

  /**********************************************/
 /***********       ModeloTabla      ***********/
/**********************************************/

ModeloTabla::ModeloTabla(QVector<Usuario *> *pUsuarioPasados, QObject *parent) : QAbstractTableModel(parent) {
	pUsuario = pUsuarioPasados;
}

  /**********************************************/
 /**********       columnCount()      **********/
/**********************************************/

int	ModeloTabla::columnCount(const QModelIndex &parent) const {
	return 8; //id, nombre, apellidos, correo, contraseña, foto_perfil, latitud, longitud
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
			return QString(usuario -> correo);
		}
		case 4: {
			//return QString::number(usuario -> contrasena);
			return QString("************");
		}
		case 5: {
			return QString("");
		}
		case 6: {
			return QString::number(usuario -> latitud);
		}
		case 7: {
			return QString::number(usuario -> longitud);
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
	double latitud = value.toString().toDouble();
	double longitud = value.toString().toDouble();
	
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
			usuario -> latitud = latitud;
			
			return true;
		}
		case 7: {
			usuario -> longitud = longitud;
			
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
	
	/*
	if (index.column() == 3) {
		return Qt::ItemIsSelectable;
	}
	*/
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
        QStringList lista = {"ID", "Nombre", "Apellidos", "Correo", "Contraseña", "Foto de Perfil", "Longitud", "Latitud"};
        
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
 /**********       actualizar()       **********/
/**********************************************/

void ModeloTabla::actualizar(){
	QModelIndex topLeft = index(0, 0);
	QModelIndex bottomRight = index (-1, 3);
	
	beginResetModel();
	
	emit layoutChanged();
	emit dataChanged(topLeft, bottomRight);
}

