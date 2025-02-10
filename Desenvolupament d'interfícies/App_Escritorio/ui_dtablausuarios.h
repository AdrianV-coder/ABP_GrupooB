/********************************************************************************
** Form generated from reading UI file 'dtablausuarios.ui'
**
** Created by: Qt User Interface Compiler version 5.15.8
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_DTABLAUSUARIOS_H
#define UI_DTABLAUSUARIOS_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTableView>

QT_BEGIN_NAMESPACE

class Ui_DTablaUsuarios
{
public:
    QPushButton *btnEliminar;
    QPushButton *btnModificar;
    QPushButton *btnInsertar;
    QTableView *vista;
    QPushButton *pushButton;

    void setupUi(QDialog *DTablaUsuarios)
    {
        if (DTablaUsuarios->objectName().isEmpty())
            DTablaUsuarios->setObjectName(QString::fromUtf8("DTablaUsuarios"));
        DTablaUsuarios->resize(1176, 731);
        btnEliminar = new QPushButton(DTablaUsuarios);
        btnEliminar->setObjectName(QString::fromUtf8("btnEliminar"));
        btnEliminar->setGeometry(QRect(880, 690, 131, 25));
        btnModificar = new QPushButton(DTablaUsuarios);
        btnModificar->setObjectName(QString::fromUtf8("btnModificar"));
        btnModificar->setGeometry(QRect(730, 690, 141, 25));
        btnInsertar = new QPushButton(DTablaUsuarios);
        btnInsertar->setObjectName(QString::fromUtf8("btnInsertar"));
        btnInsertar->setGeometry(QRect(1020, 690, 131, 25));
        vista = new QTableView(DTablaUsuarios);
        vista->setObjectName(QString::fromUtf8("vista"));
        vista->setGeometry(QRect(20, 20, 1131, 641));
        pushButton = new QPushButton(DTablaUsuarios);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));
        pushButton->setGeometry(QRect(20, 690, 141, 25));

        retranslateUi(DTablaUsuarios);

        QMetaObject::connectSlotsByName(DTablaUsuarios);
    } // setupUi

    void retranslateUi(QDialog *DTablaUsuarios)
    {
        DTablaUsuarios->setWindowTitle(QCoreApplication::translate("DTablaUsuarios", "Dialog", nullptr));
        btnEliminar->setText(QCoreApplication::translate("DTablaUsuarios", "Eliminar Usuario", nullptr));
        btnModificar->setText(QCoreApplication::translate("DTablaUsuarios", "Modificar Usuario", nullptr));
        btnInsertar->setText(QCoreApplication::translate("DTablaUsuarios", "Insertar Usuario", nullptr));
        pushButton->setText(QCoreApplication::translate("DTablaUsuarios", "<-- Volver al inicio", nullptr));
    } // retranslateUi

};

namespace Ui {
    class DTablaUsuarios: public Ui_DTablaUsuarios {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DTABLAUSUARIOS_H
