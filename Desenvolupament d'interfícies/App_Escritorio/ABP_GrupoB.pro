######################################################################
# Automatically generated by qmake (3.1) Fri Feb 14 09:03:52 2025
######################################################################

TEMPLATE = app
TARGET = ABP_GrupoB
INCLUDEPATH += .

# You can make your code fail to compile if you use deprecated APIs.
# In order to do so, uncomment the following line.
# Please consult the documentation of the deprecated API in order to know
# how to port your code away from it.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

# Input
HEADERS += conexion.h \
           danyadirusuarios.h \
           delegirusuarios.h \
           deliminarusuarios.h \
           dmodificarusuarios.h \
           dtablausuarios.h \
           usuario.h \
           ventanaprincipal.h
FORMS += danyadirusuarios.ui \
         delegirusuarios.ui \
         deliminarusuarios.ui \
         dmodificarusuarios.ui \
         dtablausuarios.ui
SOURCES += conexion.cpp \
           danyadirusuarios.cpp \
           delegirusuarios.cpp \
           deliminarusuarios.cpp \
           dmodificarusuarios.cpp \
           dtablausuarios.cpp \
           main.cpp \
           usuario.cpp \
           ventanaprincipal.cpp
QT += widgets network
