#include "mainwindow.h"
#include <QApplication>
#include "..\core_app\NoteSourceHolder.h"
#include <QLibrary>
#include <QMessageBox>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    NoteSourceHolder noteSource("input_win32.dll");

    QMessageBox mb;
    mb.setText(noteSource.GetInstance()->GetName());
    mb.show();

    MainWindow w;
    w.show();

    return a.exec();
}
