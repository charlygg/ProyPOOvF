@echo off
attrib c:\vF\*.* -h -s
attrib c:\vF\src -h -s
attrib c:\vF\bin -h -s
md c:\VideoFox
md c:\VideoFox\cortes
xcopy *.*/svF c:\VideoFox\
attrib c:\VideoFox\.settings +h +s
attrib c:\VideoFox\bin +h -s
attrib c:\VideoFox\src +h -s
attrib c:\VideoFox\clientes.png +h +s
attrib c:\VideoFox\corterentas.png +h +s
attrib c:\VideoFox\corteventas.png +h +s
attrib c:\VideoFox\entregas.png +h +s
attrib c:\VideoFox\inventario.png +h +s
attrib c:\VideoFox\llave.png +h +s
attrib c:\VideoFox\logo.png +h +s
attrib c:\VideoFox\nuevoempleado.png +h +s
attrib c:\VideoFox\renta.png +h +s
attrib c:\VideoFox\VideoFox.mdb +h -s
attrib c:\VideoFox\VideoFox.ldb +h -s
attrib c:\VideoFox\instalador.bat +h +s
attrib c:\VideoFox\.classpath +h +s
attrib c:\VideoFox\.project +h +s
copy VideoFox.lnk c:\users\%username%\desktop\
del c:\VideoFox\VideoFox.lnk
attrib c:\vF\*.* +h +s
attrib c:\vF\bin +h +s
attrib c:\vF\src +h +s
attrib c:\vF\instalador.bat -h -s