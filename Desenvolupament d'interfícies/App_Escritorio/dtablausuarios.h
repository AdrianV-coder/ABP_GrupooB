
#ifndef DTABLAUSUARIOS_H
#define DTABLAUSUARIOS_H

#include "ui_dtablausuarios.h"
#include "usuario.h"
#include "deliminarusuarios.h"
#include "danyadirusuarios.h"
#include "delegirusuarios.h"
#include "conexion.h"

#include <QVector>
#include <QDialog>
#include <QHBoxLayout>
#include <QAbstractTableModel>
#include <QModelIndex>

class ModeloTabla;

class DTablaUsuarios : public QDialog, public Ui::DTablaUsuarios {
	Q_OBJECT

	public:
		DTablaUsuarios(QVector<Usuario *> *pUsuarioPasados, QWidget *parent = nullptr);
		QVector<Usuario *> *pUsuario;
		ModeloTabla *modelo;
		
		DEliminarUsuarios *dEliminarUsuarios;
		DElegirUsuarios *dElegirUsuarios;
		DAnyadirUsuarios *dAnyadirUsuarios;
		Conexion *conexionAPI;
		
		void iniciarConexion();
		void conectarActualizaciones();
		
	public slots:
		void slotEliminarUsuario();
		void slotElegirUsuario();
		void slotAnyadirUsuario();
		void slotInicio();
		void procesarDatos(QByteArray);
		void slotActualizarTabla();
};

class ModeloTabla : public QAbstractTableModel {
	Q_OBJECT
	
	public:
		ModeloTabla(QVector<Usuario *> *pUsuarioPasados, QObject *parent = nullptr);
		QVector<Usuario *> *pUsuario;
		
		void actualizar();
		
		int	columnCount(const QModelIndex &parent = QModelIndex()) const override;
		int	rowCount(const QModelIndex &parent = QModelIndex()) const;
		QVariant data(const QModelIndex &index, int role = Qt::DisplayRole) const;
		Qt::ItemFlags flags(const QModelIndex &index) const;
		bool setData(const QModelIndex &index, const QVariant &value, int role = Qt::EditRole);
		QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const;
};

#endif 
