/********************************************************************************
** Form generated from reading UI file 'danyadirusuarios.ui'
**
** Created by: Qt User Interface Compiler version 5.15.13
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_DANYADIRUSUARIOS_H
#define UI_DANYADIRUSUARIOS_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>

QT_BEGIN_NAMESPACE

class Ui_DAnyadirUsuarios
{
public:
    QLabel *lCorreo_Modificar;
    QGraphicsView *gvFotoAnyadir;
    QLineEdit *leCorreo_Anyadir;
    QLabel *lAnyadirUsuario;
    QLineEdit *leApellidos_Anyadir;
    QLabel *lApellidos_Modificar;
    QLineEdit *leNombre_Anyadir;
    QLabel *lNombre_Modificar;
    QLineEdit *leLocalidad_Anyadir;
    QLabel *lLocalidad_Modificar;
    QPushButton *btnAceptar;
    QPushButton *btnCancelar;
    QLabel *lContrasena_Anyadir;
    QLineEdit *leContrasena_Anyadir;

    void setupUi(QDialog *DAnyadirUsuarios)
    {
        if (DAnyadirUsuarios->objectName().isEmpty())
            DAnyadirUsuarios->setObjectName(QString::fromUtf8("DAnyadirUsuarios"));
        DAnyadirUsuarios->resize(650, 526);
        lCorreo_Modificar = new QLabel(DAnyadirUsuarios);
        lCorreo_Modificar->setObjectName(QString::fromUtf8("lCorreo_Modificar"));
        lCorreo_Modificar->setGeometry(QRect(30, 290, 221, 17));
        gvFotoAnyadir = new QGraphicsView(DAnyadirUsuarios);
        gvFotoAnyadir->setObjectName(QString::fromUtf8("gvFotoAnyadir"));
        gvFotoAnyadir->setGeometry(QRect(20, 20, 601, 121));
        leCorreo_Anyadir = new QLineEdit(DAnyadirUsuarios);
        leCorreo_Anyadir->setObjectName(QString::fromUtf8("leCorreo_Anyadir"));
        leCorreo_Anyadir->setGeometry(QRect(270, 290, 221, 25));
        lAnyadirUsuario = new QLabel(DAnyadirUsuarios);
        lAnyadirUsuario->setObjectName(QString::fromUtf8("lAnyadirUsuario"));
        lAnyadirUsuario->setGeometry(QRect(240, 140, 121, 71));
        lAnyadirUsuario->setTextFormat(Qt::RichText);
        leApellidos_Anyadir = new QLineEdit(DAnyadirUsuarios);
        leApellidos_Anyadir->setObjectName(QString::fromUtf8("leApellidos_Anyadir"));
        leApellidos_Anyadir->setGeometry(QRect(270, 250, 221, 25));
        lApellidos_Modificar = new QLabel(DAnyadirUsuarios);
        lApellidos_Modificar->setObjectName(QString::fromUtf8("lApellidos_Modificar"));
        lApellidos_Modificar->setGeometry(QRect(30, 250, 221, 17));
        leNombre_Anyadir = new QLineEdit(DAnyadirUsuarios);
        leNombre_Anyadir->setObjectName(QString::fromUtf8("leNombre_Anyadir"));
        leNombre_Anyadir->setGeometry(QRect(270, 210, 221, 25));
        lNombre_Modificar = new QLabel(DAnyadirUsuarios);
        lNombre_Modificar->setObjectName(QString::fromUtf8("lNombre_Modificar"));
        lNombre_Modificar->setGeometry(QRect(30, 210, 221, 17));
        leLocalidad_Anyadir = new QLineEdit(DAnyadirUsuarios);
        leLocalidad_Anyadir->setObjectName(QString::fromUtf8("leLocalidad_Anyadir"));
        leLocalidad_Anyadir->setGeometry(QRect(270, 370, 221, 25));
        lLocalidad_Modificar = new QLabel(DAnyadirUsuarios);
        lLocalidad_Modificar->setObjectName(QString::fromUtf8("lLocalidad_Modificar"));
        lLocalidad_Modificar->setGeometry(QRect(30, 370, 221, 17));
        btnAceptar = new QPushButton(DAnyadirUsuarios);
        btnAceptar->setObjectName(QString::fromUtf8("btnAceptar"));
        btnAceptar->setGeometry(QRect(430, 470, 96, 33));
        btnCancelar = new QPushButton(DAnyadirUsuarios);
        btnCancelar->setObjectName(QString::fromUtf8("btnCancelar"));
        btnCancelar->setGeometry(QRect(530, 470, 96, 33));
        lContrasena_Anyadir = new QLabel(DAnyadirUsuarios);
        lContrasena_Anyadir->setObjectName(QString::fromUtf8("lContrasena_Anyadir"));
        lContrasena_Anyadir->setGeometry(QRect(30, 330, 221, 17));
        leContrasena_Anyadir = new QLineEdit(DAnyadirUsuarios);
        leContrasena_Anyadir->setObjectName(QString::fromUtf8("leContrasena_Anyadir"));
        leContrasena_Anyadir->setGeometry(QRect(270, 330, 221, 25));

        retranslateUi(DAnyadirUsuarios);

        QMetaObject::connectSlotsByName(DAnyadirUsuarios);
    } // setupUi

    void retranslateUi(QDialog *DAnyadirUsuarios)
    {
        DAnyadirUsuarios->setWindowTitle(QCoreApplication::translate("DAnyadirUsuarios", "Dialog", nullptr));
        lCorreo_Modificar->setText(QCoreApplication::translate("DAnyadirUsuarios", "Introduce tu correo electr\303\263nico: ", nullptr));
        leCorreo_Anyadir->setText(QString());
        lAnyadirUsuario->setText(QCoreApplication::translate("DAnyadirUsuarios", "A\303\221ADIR USUARIO", nullptr));
        leApellidos_Anyadir->setText(QString());
        lApellidos_Modificar->setText(QCoreApplication::translate("DAnyadirUsuarios", "Introduce tu apellido: ", nullptr));
        leNombre_Anyadir->setText(QString());
        lNombre_Modificar->setText(QCoreApplication::translate("DAnyadirUsuarios", "Introduce tu nombre: ", nullptr));
        leLocalidad_Anyadir->setText(QString());
        lLocalidad_Modificar->setText(QCoreApplication::translate("DAnyadirUsuarios", "Introduce tu localidad: ", nullptr));
        btnAceptar->setText(QCoreApplication::translate("DAnyadirUsuarios", "Aceptar", nullptr));
        btnCancelar->setText(QCoreApplication::translate("DAnyadirUsuarios", "Cancelar", nullptr));
        lContrasena_Anyadir->setText(QCoreApplication::translate("DAnyadirUsuarios", "Introduce tu contrase\303\261a: ", nullptr));
        leContrasena_Anyadir->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class DAnyadirUsuarios: public Ui_DAnyadirUsuarios {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DANYADIRUSUARIOS_H
