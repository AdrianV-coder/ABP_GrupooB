
#ifndef DTABLAUSUARIOS_H
#define DTABLAUSUARIOS_H

#include "ui_dtablausuarios.h"
#include "usuario.h"

#include <QVector>
#include <QDialog>
#include <QHBoxLayout>
#include <QAbstractTableModel>
#include <QModelIndex>


class DTablaUsuarios : public QDialog, public Ui::DTablaUsuarios {
	Q_OBJECT

	public:
		DTablaUsuarios(QVector<Usuario *> *pUsuarioPasados, QWidget *parent = nullptr);
};

class ModeloTabla : public QAbstractTableModel {
	Q_OBJECT
	
	public:
		QVector<Usuario *> *pUsuario;
		ModeloTabla(QVector<Usuario *> *pUsuarioPasados, QObject *parent = nullptr);
		
		int	columnCount(const QModelIndex &parent = QModelIndex()) const override;
		int	rowCount(const QModelIndex &parent = QModelIndex()) const;
		QVariant data(const QModelIndex &index, int role = Qt::DisplayRole) const;
		Qt::ItemFlags flags(const QModelIndex &index) const;
		bool setData(const QModelIndex &index, const QVariant &value, int role = Qt::EditRole);
		QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const;
		
	public slots:
		void slotTemporizador();
};

#endif 
