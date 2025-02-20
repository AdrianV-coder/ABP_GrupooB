/****************************************************************************
** Meta object code from reading C++ file 'dtablausuarios.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.15.13)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include <memory>
#include "dtablausuarios.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'dtablausuarios.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.15.13. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_DTablaUsuarios_t {
    QByteArrayData data[8];
    char stringdata0[118];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_DTablaUsuarios_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_DTablaUsuarios_t qt_meta_stringdata_DTablaUsuarios = {
    {
QT_MOC_LITERAL(0, 0, 14), // "DTablaUsuarios"
QT_MOC_LITERAL(1, 15, 19), // "slotEliminarUsuario"
QT_MOC_LITERAL(2, 35, 0), // ""
QT_MOC_LITERAL(3, 36, 17), // "slotElegirUsuario"
QT_MOC_LITERAL(4, 54, 18), // "slotAnyadirUsuario"
QT_MOC_LITERAL(5, 73, 10), // "slotInicio"
QT_MOC_LITERAL(6, 84, 13), // "procesarDatos"
QT_MOC_LITERAL(7, 98, 19) // "slotActualizarTabla"

    },
    "DTablaUsuarios\0slotEliminarUsuario\0\0"
    "slotElegirUsuario\0slotAnyadirUsuario\0"
    "slotInicio\0procesarDatos\0slotActualizarTabla"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_DTablaUsuarios[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
       6,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    0,   44,    2, 0x0a /* Public */,
       3,    0,   45,    2, 0x0a /* Public */,
       4,    0,   46,    2, 0x0a /* Public */,
       5,    0,   47,    2, 0x0a /* Public */,
       6,    1,   48,    2, 0x0a /* Public */,
       7,    0,   51,    2, 0x0a /* Public */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, QMetaType::QByteArray,    2,
    QMetaType::Void,

       0        // eod
};

void DTablaUsuarios::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<DTablaUsuarios *>(_o);
        (void)_t;
        switch (_id) {
        case 0: _t->slotEliminarUsuario(); break;
        case 1: _t->slotElegirUsuario(); break;
        case 2: _t->slotAnyadirUsuario(); break;
        case 3: _t->slotInicio(); break;
        case 4: _t->procesarDatos((*reinterpret_cast< QByteArray(*)>(_a[1]))); break;
        case 5: _t->slotActualizarTabla(); break;
        default: ;
        }
    }
}

QT_INIT_METAOBJECT const QMetaObject DTablaUsuarios::staticMetaObject = { {
    QMetaObject::SuperData::link<QDialog::staticMetaObject>(),
    qt_meta_stringdata_DTablaUsuarios.data,
    qt_meta_data_DTablaUsuarios,
    qt_static_metacall,
    nullptr,
    nullptr
} };


const QMetaObject *DTablaUsuarios::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *DTablaUsuarios::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_DTablaUsuarios.stringdata0))
        return static_cast<void*>(this);
    if (!strcmp(_clname, "Ui::DTablaUsuarios"))
        return static_cast< Ui::DTablaUsuarios*>(this);
    return QDialog::qt_metacast(_clname);
}

int DTablaUsuarios::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QDialog::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 6)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 6;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 6)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 6;
    }
    return _id;
}
struct qt_meta_stringdata_ModeloTabla_t {
    QByteArrayData data[1];
    char stringdata0[12];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_ModeloTabla_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_ModeloTabla_t qt_meta_stringdata_ModeloTabla = {
    {
QT_MOC_LITERAL(0, 0, 11) // "ModeloTabla"

    },
    "ModeloTabla"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_ModeloTabla[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
       0,    0, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

       0        // eod
};

void ModeloTabla::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    (void)_o;
    (void)_id;
    (void)_c;
    (void)_a;
}

QT_INIT_METAOBJECT const QMetaObject ModeloTabla::staticMetaObject = { {
    QMetaObject::SuperData::link<QAbstractTableModel::staticMetaObject>(),
    qt_meta_stringdata_ModeloTabla.data,
    qt_meta_data_ModeloTabla,
    qt_static_metacall,
    nullptr,
    nullptr
} };


const QMetaObject *ModeloTabla::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *ModeloTabla::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_ModeloTabla.stringdata0))
        return static_cast<void*>(this);
    return QAbstractTableModel::qt_metacast(_clname);
}

int ModeloTabla::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QAbstractTableModel::qt_metacall(_c, _id, _a);
    return _id;
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
