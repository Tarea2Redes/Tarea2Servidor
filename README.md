La aplicación servidor es el componente que simula el RAID 5 en este proyecto, el cuál se comunica con la aplicación saSearch mediante sockets UDP 
y la información viaja encriptada en los DatagramPackets, cada DatagramPacket corresponde a un objeto tipo libro
el cuál se fragmenta en 4 trozos, y aleatoriamente esos fragmentos se guardan en los DiskNode disponibles
esta aplicación se desarrollo en Java, con Ntbeans IDE 8.2, y el ControllerNode gestiona los discos activos mediante consultas a SQL Server (localhost)

La simulación del RAID 5 se implementó usando un objeto llamado CintaDiscoDuro el cual simula las bandas que tienen los discos en un raid y por medio de un bit de paridad
(atributo de dicha clase) se pueden obtener los fragmentos de datos que se pierden al deshabilitar un disco que tenía contenido.

Este proyecto fue desarrollado por los estudiantes de la UCR Kevin Calderón y Valeska Molina para la segunda tarea programada del curso
Redes y comunicación de datos I semestre 2021
Es importante mencionar que estas aplicaciones no tienen ningun tipo de licencias y se apoyaron en la codificación Huffman cortesía del usuari de github "ahmedengu" 
en el repositorio https://gist.github.com/ahmedengu/aa8d85b12fccf0d08e895807edee7603
