QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

CONFIG += c++11

# You can make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    Barca.cpp \
    Conejo.cpp \
    Lechuga.cpp \
    Personaje.cpp \
    Robot.cpp \
    Zorro.cpp \
    main.cpp \
    mainwindow.cpp

HEADERS += \
    Barca.h \
    Conejo.h \
    Lechuga.h \
    Personaje.h \
    Robot.h \
    Zorro.h \
    mainwindow.h

FORMS += \
    mainwindow.ui

RC_ICONS = iconoUI.ico


# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

RESOURCES += \
    imagenes.qrc \
    imagenes.qrc

DISTFILES += \
    Barca.pro.qtds \
    albedo.jpg \
    bunny.png \
    fondo.jpg
