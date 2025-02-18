/****************************************************************************
** Meta object code from reading C++ file 'danyadirusuarios.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.15.13)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include <memory>
#include "danyadirusuarios.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'danyadirusuarios.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.15.13. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_DAnyadirUsuarios_t {
    QByteArrayData data[7];
    char stringdata0[88];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_DAnyadirUsuarios_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_DAnyadirUsuarios_t qt_meta_stringdata_DAnyadirUsuarios = {
    {
QT_MOC_LITERAL(0, 0, 16), // "DAnyadirUsuarios"
QT_MOC_LITERAL(1, 17, 18), // "slotAnyadirUsuario"
QT_MOC_LITERAL(2, 36, 0), // ""
QT_MOC_LITERAL(3, 37, 19), // "onRespuestaRecibida"
QT_MOC_LITERAL(4, 57, 14), // "QNetworkReply*"
QT_MOC_LITERAL(5, 72, 5), // "reply"
QT_MOC_LITERAL(6, 78, 9) // "slotSalir"

    },
    "DAnyadirUsuarios\0slotAnyadirUsuario\0"
    "\0onRespuestaRecibida\0QNetworkReply*\0"
    "reply\0slotSalir"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_DAnyadirUsuarios[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
       3,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    0,   29,    2, 0x08 /* Private */,
       3,    1,   30,    2, 0x08 /* Private */,
       6,    0,   33,    2, 0x08 /* Private */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void, 0x80000000 | 4,    5,
    QMetaType::Void,

       0        // eod
};

void DAnyadirUsuarios::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<DAnyadirUsuarios *>(_o);
        (void)_t;
        switch (_id) {
        case 0: _t->slotAnyadirUsuario(); break;
        case 1: _t->onRespuestaRecibida((*reinterpret_cast< QNetworkReply*(*)>(_a[1]))); break;
        case 2: _t->slotSalir(); break;
        default: ;
        }
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        switch (_id) {
        default: *reinterpret_cast<int*>(_a[0]) = -1; break;
        case 1:
            switch (*reinterpret_cast<int*>(_a[1])) {
            default: *reinterpret_cast<int*>(_a[0]) = -1; break;
            case 0:
                *reinterpret_cast<int*>(_a[0]) = qRegisterMetaType< QNetworkReply* >(); break;
            }
            break;
        }
    }
}

QT_INIT_METAOBJECT const QMetaObject DAnyadirUsuarios::staticMetaObject = { {
    QMetaObject::SuperData::link<QDialog::staticMetaObject>(),
    qt_meta_stringdata_DAnyadirUsuarios.data,
    qt_meta_data_DAnyadirUsuarios,
    qt_static_metacall,
    nullptr,
    nullptr
} };


const QMetaObject *DAnyadirUsuarios::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *DAnyadirUsuarios::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_DAnyadirUsuarios.stringdata0))
        return static_cast<void*>(this);
    if (!strcmp(_clname, "Ui::DAnyadirUsuarios"))
        return static_cast< Ui::DAnyadirUsuarios*>(this);
    return QDialog::qt_metacast(_clname);
}

int DAnyadirUsuarios::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QDialog::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 3)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 3;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 3)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 3;
    }
    return _id;
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
