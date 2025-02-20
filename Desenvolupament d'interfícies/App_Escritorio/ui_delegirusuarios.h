/********************************************************************************
** Form generated from reading UI file 'delegirusuarios.ui'
**
** Created by: Qt User Interface Compiler version 5.15.13
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_DELEGIRUSUARIOS_H
#define UI_DELEGIRUSUARIOS_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>

QT_BEGIN_NAMESPACE

class Ui_DElegirUsuarios
{
public:
    QLineEdit *leId_Elegir;
    QLabel *lCorreo_Elegir;
    QPushButton *btnAceptar;
    QPushButton *btnCancelar;

    void setupUi(QDialog *DElegirUsuarios)
    {
        if (DElegirUsuarios->objectName().isEmpty())
            DElegirUsuarios->setObjectName(QString::fromUtf8("DElegirUsuarios"));
        DElegirUsuarios->resize(650, 526);
        leId_Elegir = new QLineEdit(DElegirUsuarios);
        leId_Elegir->setObjectName(QString::fromUtf8("leId_Elegir"));
        leId_Elegir->setGeometry(QRect(210, 230, 221, 25));
        lCorreo_Elegir = new QLabel(DElegirUsuarios);
        lCorreo_Elegir->setObjectName(QString::fromUtf8("lCorreo_Elegir"));
        lCorreo_Elegir->setGeometry(QRect(140, 190, 361, 17));
        btnAceptar = new QPushButton(DElegirUsuarios);
        btnAceptar->setObjectName(QString::fromUtf8("btnAceptar"));
        btnAceptar->setGeometry(QRect(430, 470, 96, 33));
        btnCancelar = new QPushButton(DElegirUsuarios);
        btnCancelar->setObjectName(QString::fromUtf8("btnCancelar"));
        btnCancelar->setGeometry(QRect(530, 470, 96, 33));

        retranslateUi(DElegirUsuarios);

        QMetaObject::connectSlotsByName(DElegirUsuarios);
    } // setupUi

    void retranslateUi(QDialog *DElegirUsuarios)
    {
        DElegirUsuarios->setWindowTitle(QCoreApplication::translate("DElegirUsuarios", "Dialog", nullptr));
        leId_Elegir->setText(QString());
        lCorreo_Elegir->setText(QCoreApplication::translate("DElegirUsuarios", "Introduce el ID del usuario que quieras modificar:", nullptr));
        btnAceptar->setText(QCoreApplication::translate("DElegirUsuarios", "Aceptar", nullptr));
        btnCancelar->setText(QCoreApplication::translate("DElegirUsuarios", "Cancelar", nullptr));
    } // retranslateUi

};

namespace Ui {
    class DElegirUsuarios: public Ui_DElegirUsuarios {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DELEGIRUSUARIOS_H
