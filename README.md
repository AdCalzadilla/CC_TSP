# TSP

Práctica del **tema 3** de la asignatura *Complejidad Computacional* perteneciente al cuarto curso de *Ingeniería Informática* del itinerario de *Computación* de *La Universidad de La Laguna*.

## Objetivo

El objetivo de la práctica es elaborar una implementación de un algoritmo de ramificación acotación que resuelva de forma exacta el problema conocido como "The Traveling Salesman Problem".

### Paso 1: Lectura de ficheros en formato TSPLIB XML

Como paso previo a la implementación del algoritmo para la resolución del TSP debemos elaborar dos clases que nos faciliten la gestión de los datos de entrada:

1. Una primera clase que almacene la matriz de distancias del grado asociado a la entrada.

2. Una segunda clase que tenga como uno de sus atributos la primera clase, y que añada un campo para el nombre de la entrada. Esta última clase debe tener un procedimiento que permita la lectura de un fichero **XML** con la entrada a partir de un flujo de entrada (que puede ser la entrada standard).
