# TSP

Práctica del **tema 3** de la asignatura *Complejidad Computacional* perteneciente al cuarto curso de *Ingeniería Informática* del itinerario de *Computación* de *La Universidad de La Laguna*.

## Objetivo

El objetivo de la práctica es elaborar una implementación de un algoritmo de ramificación acotación que resuelva de forma exacta el problema conocido como "The Traveling Salesman Problem".

### Paso 1: Lectura de ficheros en formato TSPLIB XML

Como paso previo a la implementación del algoritmo para la resolución del TSP debemos elaborar dos clases que nos faciliten la gestión de los datos de entrada:

1. Una primera clase que almacene la matriz de distancias del grado asociado a la entrada.

2. Una segunda clase que tenga como uno de sus atributos la primera clase, y que añada un campo para el nombre de la entrada. Esta última clase debe tener un procedimiento que permita la lectura de un fichero **XML** con la entrada a partir de un flujo de entrada (que puede ser la entrada standard).

### Paso2: Cotas superiores

Implementar un algoritmo que permita obtener cotas superiores de calidad en un tiempo de computación aceptable.

Para ello se elaborará una clase que tenga las siguientes características:

1. la clase debe contener los datos de entrada.

2. Debe almacenar el mejor **tour** obtenido hasta el momento.

3. Debe guardar el mejor **valor** obtenido hasta el momento para el tour almacenado.

La clase debe contener un método de carga para cargar una entrada determinada, cada vez que se invoque al método de carga debe eliminarse el tour almacenado e iniciarse la cota superior a 1E100; un método de computación de cota superior, el cual llamará a dos métodos privados para implementar primero un algoritmo constructivo (**NN algoritm**), y un algoritmo de refinamiento (**2 OPT**). Una vez ejecutado el método de computación de la cota superior se actualizará el mejor tour encontrado, así como el valor de la cota superior; por último, se debe incluir un método para la obtención del valor de la cota superior.
