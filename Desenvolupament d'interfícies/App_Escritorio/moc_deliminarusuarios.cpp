/****************************************************************************
** Meta object code from reading C++ file 'deliminarusuarios.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.15.13)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include <memory>
#include "deliminarusuarios.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'deliminarusuarios.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.15.13. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_DEliminarUsuarios_t {
    QByteArrayData data[9];
    char stringdata0[133];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_DEliminarUsuarios_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_DEliminarUsuarios_t qt_meta_stringdata_DEliminarUsuarios = {
    {
QT_MOC_LITERAL(0, 0, 17), // "DEliminarUsuarios"
QT_MOC_LITERAL(1, 18, 18), // "usuarioActualizado"
QT_MOC_LITERAL(2, 37, 0), // ""
QT_MOC_LITERAL(3, 38, 23), // "actualizarTablaUsaurios"
QT_MOC_LITERAL(4, 62, 19), // "slotEliminarUsuario"
QT_MOC_LITERAL(5, 82, 19), // "onRespuestaRecibida"
QT_MOC_LITERAL(6, 102, 14), // "QNetworkReply*"
QT_MOC_LITERAL(7, 117, 5), // "reply"
QT_MOC_LITERAL(8, 123, 9) // "slotSalir"

    },
    "DEliminarUsuarios\0usuarioActualizado\0"
    "\0actualizarTablaUsaurios\0slotEliminarUsuario\0"
    "onRespuestaRecibida\0QNetworkReply*\0"
    "reply\0slotSalir"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_DEliminarUsuarios[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
       5,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       2,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    0,   39,    2, 0x06 /* Public */,
       3,    0,   40,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       4,    0,   41,    2, 0x08 /* Private */,
       5,    1,   42,    2, 0x08 /* Private */,
       8,    0,   45,    2, 0x08 /* Private */,

 // signals: parameters
    QMetaType::Void,
    QMetaType::Void,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void, 0x80000000 | 6,    7,
    QMetaType::Void,

       0        // eod
};

void DEliminarUsuarios::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<DEliminarUsuarios *>(_o);
        (void)_t;
        switch (_id) {
        case 0: _t->usuarioActualizado(); break;
        case 1: _t->actualizarTablaUsaurios(); break;
        case 2: _t->slotEliminarUsuario(); break;
        case 3: _t->onRespuestaRecibida((*reinterpret_cast< QNetworkReply*(*)>(_a[1]))); break;
        case 4: _t->slotSalir(); break;
        default: ;
        }
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        switch (_id) {
        default: *reinterpret_cast<int*>(_a[0]) = -1; break;
        case 3:
            switch (*reinterpret_cast<int*>(_a[1])) {
            default: *reinterpret_cast<int*>(_a[0]) = -1; break;
            case 0:
                *reinterpret_cast<int*>(_a[0]) = qRegisterMetaType< QNetworkReply* >(); break;
            }
            break;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        {
            using _t = void (DEliminarUsuarios::*)();
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&DEliminarUsuarios::usuarioActualizado)) {
                *result = 0;
                return;
            }
        }
        {
            using _t = void (DEliminarUsuarios::*)();
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&DEliminarUsuarios::actualizarTablaUsaurios)) {
                *result = 1;
                return;
            }
        }
    }
}

QT_INIT_METAOBJECT const QMetaObject DEliminarUsuarios::staticMetaObject = { {
    QMetaObject::SuperData::link<QDialog::staticMetaObject>(),
    qt_meta_stringdata_DEliminarUsuarios.data,
    qt_meta_data_DEliminarUsuarios,
    qt_static_metacall,
    nullptr,
    nullptr
} };


const QMetaObject *DEliminarUsuarios::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *DEliminarUsuarios::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_DEliminarUsuarios.stringdata0))
        return static_cast<void*>(this);
    if (!strcmp(_clname, "Ui::DEliminarUsuarios"))
        return static_cast< Ui::DEliminarUsuarios*>(this);
    return QDialog::qt_metacast(_clname);
}

int DEliminarUsuarios::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QDialog::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 5)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 5;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 5)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 5;
    }
    return _id;
}

// SIGNAL 0
void DEliminarUsuarios::usuarioActualizado()
{
    QMetaObject::activate(this, &staticMetaObject, 0, nullptr);
}

// SIGNAL 1
void DEliminarUsuarios::actualizarTablaUsaurios()
{
    QMetaObject::activate(this, &staticMetaObject, 1, nullptr);
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
