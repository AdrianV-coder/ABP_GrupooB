/********************************************************************************
** Form generated from reading UI file 'deliminarusuarios.ui'
**
** Created by: Qt User Interface Compiler version 5.15.13
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_DELIMINARUSUARIOS_H
#define UI_DELIMINARUSUARIOS_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>

QT_BEGIN_NAMESPACE

class Ui_DEliminarUsuarios
{
public:
    QGraphicsView *gvFoto;
    QLineEdit *leId;
    QLabel *lEliminarUsuario;
    QLabel *lId;
    QPushButton *btnAceptar;
    QPushButton *btnCancelar;

    void setupUi(QDialog *DEliminarUsuarios)
    {
        if (DEliminarUsuarios->objectName().isEmpty())
            DEliminarUsuarios->setObjectName(QString::fromUtf8("DEliminarUsuarios"));
        DEliminarUsuarios->resize(650, 524);
        gvFoto = new QGraphicsView(DEliminarUsuarios);
        gvFoto->setObjectName(QString::fromUtf8("gvFoto"));
        gvFoto->setGeometry(QRect(20, 20, 601, 121));
        leId = new QLineEdit(DEliminarUsuarios);
        leId->setObjectName(QString::fromUtf8("leId"));
        leId->setGeometry(QRect(200, 280, 221, 25));
        lEliminarUsuario = new QLabel(DEliminarUsuarios);
        lEliminarUsuario->setObjectName(QString::fromUtf8("lEliminarUsuario"));
        lEliminarUsuario->setGeometry(QRect(240, 150, 141, 71));
        lEliminarUsuario->setTextFormat(Qt::RichText);
        lId = new QLabel(DEliminarUsuarios);
        lId->setObjectName(QString::fromUtf8("lId"));
        lId->setGeometry(QRect(150, 250, 321, 17));
        btnAceptar = new QPushButton(DEliminarUsuarios);
        btnAceptar->setObjectName(QString::fromUtf8("btnAceptar"));
        btnAceptar->setGeometry(QRect(430, 470, 96, 33));
        btnCancelar = new QPushButton(DEliminarUsuarios);
        btnCancelar->setObjectName(QString::fromUtf8("btnCancelar"));
        btnCancelar->setGeometry(QRect(530, 470, 96, 33));

        retranslateUi(DEliminarUsuarios);

        QMetaObject::connectSlotsByName(DEliminarUsuarios);
    } // setupUi

    void retranslateUi(QDialog *DEliminarUsuarios)
    {
        DEliminarUsuarios->setWindowTitle(QCoreApplication::translate("DEliminarUsuarios", "Dialog", nullptr));
        leId->setText(QString());
        lEliminarUsuario->setText(QCoreApplication::translate("DEliminarUsuarios", "ELIMINAR USUARIO", nullptr));
        lId->setText(QCoreApplication::translate("DEliminarUsuarios", "Introduce el ID del usuario que quieres eliminar: ", nullptr));
        btnAceptar->setText(QCoreApplication::translate("DEliminarUsuarios", "Aceptar", nullptr));
        btnCancelar->setText(QCoreApplication::translate("DEliminarUsuarios", "Cancelar", nullptr));
    } // retranslateUi

};

namespace Ui {
    class DEliminarUsuarios: public Ui_DEliminarUsuarios {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DELIMINARUSUARIOS_H
