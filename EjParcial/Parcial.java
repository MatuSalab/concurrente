package EjParcial;

/*
 * Generador -> canciones (en realidad, va tirando numeros del 1 al 4 y los pone en el BufferContadores segun la pila correspondiente)
 * canciones se van ubicando en BufferContadores donde les corresponde
 * BufferContadores -> 4 contadores segun cada tipo (arrays de tipo, para saber cuántod hay de cada tipo)
 * Productor -> Agarra canciones de la pila que le corresponde (segun el tipo de productor que sea) y las coloca en el segundo buffer, que hace de "album"
 * BufferAlbum -> Hace de "mesita" donde se ubica el album. El productor pone canciones y según el tipo se le agrega una duración. Si se pasa de la duración, se despierta al despachador
 * Despachador -> vacía el álbum
 * 
 * 
 */

