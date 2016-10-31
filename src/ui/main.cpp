#include "mainwindow.h"
#include "..\core_app\NoteSourceHolder.h"
#include "../core_app/CompositionsLoader.h"
#include "../core_app/CompositionsFinder.h"
#include <QApplication>
#include <QLibrary>
#include <QMessageBox>
#include <memory>

struct TestListener : INoteListener {
    void OnNote(Note const & note) {

    }

    void OnError(QString const & error) {
        QMessageBox mb;
        mb.setText(error);
        mb.show();
    }
};

int main(int argc, char *argv[])
{
    auto loader = std::make_shared<CompositionsLoader>();
    auto finder = std::make_shared<CompositionsFinder>(loader);

    QApplication a(argc, argv);

    NoteSourceHolder noteSource("input_win32.dll");

    TestListener listener;
    noteSource->AddListener(&listener);

    MainWindow w;
    w.show();

    return a.exec();
}
