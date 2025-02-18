/********************************************************************************
** Form generated from reading UI file 'dmodificarusuarios.ui'
**
** Created by: Qt User Interface Compiler version 5.15.13
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_DMODIFICARUSUARIOS_H
#define UI_DMODIFICARUSUARIOS_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>

QT_BEGIN_NAMESPACE

class Ui_DModificarUsuarios
{
public:
    QLabel *lModificarUsuario;
    QGraphicsView *gvFotoModificar;
    QLabel *lNombre;
    QLineEdit *leNombre;
    QLabel *lApellidos;
    QLineEdit *leApellidos;
    QLabel *lCorreo;
    QLineEdit *leCorreoElectronico;
    QLabel *lContrasena;
    QLineEdit *leContrasena;
    QPushButton *btnCancelar;
    QPushButton *btnAceptar;
    QLabel *lLatitud;
    QLineEdit *leLatitud;
    QLineEdit *leLongitud;
    QLabel *lLongitud;

    void setupUi(QDialog *DModificarUsuarios)
    {
        if (DModificarUsuarios->objectName().isEmpty())
            DModificarUsuarios->setObjectName(QString::fromUtf8("DModificarUsuarios"));
        DModificarUsuarios->resize(648, 526);
        lModificarUsuario = new QLabel(DModificarUsuarios);
        lModificarUsuario->setObjectName(QString::fromUtf8("lModificarUsuario"));
        lModificarUsuario->setGeometry(QRect(240, 140, 151, 71));
        lModificarUsuario->setTextFormat(Qt::RichText);
        gvFotoModificar = new QGraphicsView(DModificarUsuarios);
        gvFotoModificar->setObjectName(QString::fromUtf8("gvFotoModificar"));
        gvFotoModificar->setGeometry(QRect(20, 20, 601, 121));
        lNombre = new QLabel(DModificarUsuarios);
        lNombre->setObjectName(QString::fromUtf8("lNombre"));
        lNombre->setGeometry(QRect(20, 230, 221, 17));
        leNombre = new QLineEdit(DModificarUsuarios);
        leNombre->setObjectName(QString::fromUtf8("leNombre"));
        leNombre->setGeometry(QRect(260, 230, 221, 25));
        lApellidos = new QLabel(DModificarUsuarios);
        lApellidos->setObjectName(QString::fromUtf8("lApellidos"));
        lApellidos->setGeometry(QRect(20, 270, 221, 17));
        leApellidos = new QLineEdit(DModificarUsuarios);
        leApellidos->setObjectName(QString::fromUtf8("leApellidos"));
        leApellidos->setGeometry(QRect(260, 270, 221, 25));
        lCorreo = new QLabel(DModificarUsuarios);
        lCorreo->setObjectName(QString::fromUtf8("lCorreo"));
        lCorreo->setGeometry(QRect(20, 310, 221, 17));
        leCorreoElectronico = new QLineEdit(DModificarUsuarios);
        leCorreoElectronico->setObjectName(QString::fromUtf8("leCorreoElectronico"));
        leCorreoElectronico->setGeometry(QRect(260, 310, 221, 25));
        lContrasena = new QLabel(DModificarUsuarios);
        lContrasena->setObjectName(QString::fromUtf8("lContrasena"));
        lContrasena->setGeometry(QRect(20, 350, 221, 17));
        leContrasena = new QLineEdit(DModificarUsuarios);
        leContrasena->setObjectName(QString::fromUtf8("leContrasena"));
        leContrasena->setGeometry(QRect(260, 350, 221, 25));
        btnCancelar = new QPushButton(DModificarUsuarios);
        btnCancelar->setObjectName(QString::fromUtf8("btnCancelar"));
        btnCancelar->setGeometry(QRect(530, 470, 96, 33));
        btnAceptar = new QPushButton(DModificarUsuarios);
        btnAceptar->setObjectName(QString::fromUtf8("btnAceptar"));
        btnAceptar->setGeometry(QRect(430, 470, 96, 33));
        lLatitud = new QLabel(DModificarUsuarios);
        lLatitud->setObjectName(QString::fromUtf8("lLatitud"));
        lLatitud->setGeometry(QRect(20, 390, 221, 17));
        leLatitud = new QLineEdit(DModificarUsuarios);
        leLatitud->setObjectName(QString::fromUtf8("leLatitud"));
        leLatitud->setGeometry(QRect(260, 390, 221, 25));
        leLongitud = new QLineEdit(DModificarUsuarios);
        leLongitud->setObjectName(QString::fromUtf8("leLongitud"));
        leLongitud->setGeometry(QRect(260, 430, 221, 25));
        lLongitud = new QLabel(DModificarUsuarios);
        lLongitud->setObjectName(QString::fromUtf8("lLongitud"));
        lLongitud->setGeometry(QRect(20, 430, 221, 17));

        retranslateUi(DModificarUsuarios);

        QMetaObject::connectSlotsByName(DModificarUsuarios);
    } // setupUi

    void retranslateUi(QDialog *DModificarUsuarios)
    {
        DModificarUsuarios->setWindowTitle(QCoreApplication::translate("DModificarUsuarios", "Dialog", nullptr));
        lModificarUsuario->setText(QCoreApplication::translate("DModificarUsuarios", "MODIFICAR USUARIO", nullptr));
        lNombre->setText(QCoreApplication::translate("DModificarUsuarios", "Introduce tu nombre: ", nullptr));
        leNombre->setText(QString());
        lApellidos->setText(QCoreApplication::translate("DModificarUsuarios", "Introduce tu apellido: ", nullptr));
        leApellidos->setText(QString());
        lCorreo->setText(QCoreApplication::translate("DModificarUsuarios", "Introduce tu correo electr\303\263nico: ", nullptr));
        leCorreoElectronico->setText(QString());
        lContrasena->setText(QCoreApplication::translate("DModificarUsuarios", "Introduce tu contrase\303\261a: ", nullptr));
        leContrasena->setText(QString());
        btnCancelar->setText(QCoreApplication::translate("DModificarUsuarios", "Cancelar", nullptr));
        btnAceptar->setText(QCoreApplication::translate("DModificarUsuarios", "Aceptar", nullptr));
        lLatitud->setText(QCoreApplication::translate("DModificarUsuarios", "Introduce tu latitud: ", nullptr));
        leLatitud->setText(QString());
        leLongitud->setText(QString());
        lLongitud->setText(QCoreApplication::translate("DModificarUsuarios", "Introduce tu longitud: ", nullptr));
    } // retranslateUi

};

namespace Ui {
    class DModificarUsuarios: public Ui_DModificarUsuarios {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DMODIFICARUSUARIOS_H
