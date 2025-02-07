#include "ventanaprincipal.h"

#include <QApplication>

int main (int argc, char *argv[]) {
	QApplication app(argc, argv);
	
	VentanaPrincipal *ventanaprincipal = new VentanaPrincipal();
	ventanaprincipal -> show();
	
	return app.exec();
}
