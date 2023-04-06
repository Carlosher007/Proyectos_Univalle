#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "Zorro.h"
#include "Conejo.h"
#include "Lechuga.h"
#include "Robot.h"
#include "Barca.h"
#include <QMessageBox>
#include <QString>
#include "ui_mainwindow.h"


QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
    bool cazar(int&,int&);

public slots:
    void handleButtonF();
    void handleButtonB();
    void handleButtonR();
    void handleButtonL();
    void changePlaceF();
    void changePlaceB();
    void changePlaceR();
    void changePlaceL();
    void moveRight();
    void moveLeft();
    void perdio();
    bool gano();
    bool getEstadoJuego();
    void over();
    void mensajeDeGameOver( QString &mensaje);
    void restart();
private:
int c=0;
int z=0;
int r=0;
int l=0;
Barca barca;
bool juego =true;

private:
    Ui::MainWindow *ui;
};
#endif // MAINWINDOW_H
