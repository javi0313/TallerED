package ListasSimples;

public class ListasSimples {
    private NodoSimple primero;

    public ListasSimples() {
        this.primero = null;
    }

    // ===================== AUXILIARES =====================

    public int Contar() {
        int cantidad = 0;
        NodoSimple actual = this.primero;
        while (actual != null) {
            cantidad++;
            actual = actual.getSiguiente();
        }
        return cantidad;
    }

    // Compara numéricamente si ambos datos son números; si no, como texto
    private int Comparar(String a, String b) {
        try {
            return Double.compare(Double.parseDouble(a), Double.parseDouble(b));
        } catch (NumberFormatException e) {
            return a.compareTo(b);
        }
    }

    // ===================== INSERCIÓN =====================

    public void InsertarInicio(NodoSimple nuevo) {
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            nuevo.setSiguiente(this.primero);
            this.primero = nuevo;
        }
    }

    public void InsertarFinal(NodoSimple nuevo) {
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            NodoSimple actual = this.primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public void InsertarAntes(NodoSimple nuevo, String ref) {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else if (this.primero.getDato().equals(ref)) {
            InsertarInicio(nuevo);
        } else {
            NodoSimple actual = this.primero;
            while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(ref)) {
                actual = actual.getSiguiente();
            }
            if (actual.getSiguiente() == null) {
                System.out.println("Nodo no encontrado");
            } else {
                nuevo.setSiguiente(actual.getSiguiente());
                actual.setSiguiente(nuevo);
            }
        }
    }

    public void InsertarDespues(NodoSimple nuevo, String ref) {
        NodoSimple actual = this.primero;
        while (actual != null) {
            if (actual.getDato().equals(ref)) {
                break;
            }
            actual = actual.getSiguiente();
        }
        if (actual == null) {
            System.out.println("Nodo no encontrado");
        } else {
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
    }

    // Posiciones desde 1 hasta Contar() + 1
    public void InsertarPosicion(NodoSimple nuevo, int posicion) {
        if (posicion < 1 || posicion > Contar() + 1) {
            System.out.println("Posicion invalida");
        } else if (posicion == 1) {
            InsertarInicio(nuevo);
        } else {
            NodoSimple actual = this.primero;
            for (int i = 1; i < posicion - 1; i++) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
    }

    // ===================== ELIMINACIÓN =====================

    public void EliminarInicio() {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else {
            NodoSimple actual = this.primero;
            this.primero = this.primero.getSiguiente();
            actual.setSiguiente(null);
            actual = null;
        }
    }

    public void EliminarFinal() {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else if (this.primero.getSiguiente() == null) {
            this.primero = null;
        } else {
            NodoSimple actual = this.primero;
            while (actual.getSiguiente().getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(null);
        }
    }

    // Elimina el nodo de referencia (primer nodo cuyo dato sea ref)
    public void EliminarNodo(String ref) {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else {
            NodoSimple actual = this.primero;
            NodoSimple actual2 = null; // anterior de actual
            while (actual != null && !actual.getDato().equals(ref)) {
                actual2 = actual;
                actual = actual.getSiguiente();
            }
            if (actual == null) {
                System.out.println("No se encontro el nodo con el dato");
            } else {
                if (actual2 == null) {
                    this.primero = actual.getSiguiente();
                } else {
                    actual2.setSiguiente(actual.getSiguiente());
                }
                actual.setSiguiente(null);
            }
        }
    }

    // Elimina el nodo que está justo antes del nodo de referencia
    public void EliminarAntes(String ref) {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else if (this.primero.getDato().equals(ref)) {
            System.out.println("No hay nodo antes de la referencia");
        } else {
            NodoSimple actual = this.primero;
            NodoSimple actual2 = null; // anterior de actual
            while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(ref)) {
                actual2 = actual;
                actual = actual.getSiguiente();
            }
            if (actual.getSiguiente() == null) {
                System.out.println("No se encontro el nodo con el dato");
            } else {
                // actual es el nodo previo a la referencia: se elimina
                if (actual2 == null) {
                    this.primero = actual.getSiguiente();
                } else {
                    actual2.setSiguiente(actual.getSiguiente());
                }
                actual.setSiguiente(null);
            }
        }
    }

    // Elimina el nodo que está justo después del nodo de referencia
    public void EliminarDespues(String ref) {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else {
            NodoSimple actual = this.primero;
            while (actual != null) {
                if (actual.getDato().equals(ref)) {
                    break;
                }
                actual = actual.getSiguiente();
            }
            if (actual == null) {
                System.out.println("No se encontro el nodo con el dato");
            } else if (actual.getSiguiente() == null) {
                System.out.println("No hay nodo despues de la referencia");
            } else {
                NodoSimple actual2 = actual.getSiguiente();
                actual.setSiguiente(actual2.getSiguiente());
                actual2.setSiguiente(null);
            }
        }
    }

    // Posiciones desde 1 hasta Contar()
    public void EliminarPosicion(int posicion) {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else if (posicion < 1 || posicion > Contar()) {
            System.out.println("Posicion invalida");
        } else if (posicion == 1) {
            EliminarInicio();
        } else {
            NodoSimple actual = this.primero;
            for (int i = 1; i < posicion - 1; i++) {
                actual = actual.getSiguiente();
            }
            NodoSimple actual2 = actual.getSiguiente();
            actual.setSiguiente(actual2.getSiguiente());
            actual2.setSiguiente(null);
        }
    }

    // Elimina TODAS las apariciones del dato
    public void EliminarPorDato(String dato) {
        int eliminados = 0;
        while (this.primero != null && this.primero.getDato().equals(dato)) {
            NodoSimple actual2 = this.primero;
            this.primero = this.primero.getSiguiente();
            actual2.setSiguiente(null);
            eliminados++;
        }
        NodoSimple actual = this.primero;
        while (actual != null && actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                NodoSimple actual2 = actual.getSiguiente();
                actual.setSiguiente(actual2.getSiguiente());
                actual2.setSiguiente(null);
                eliminados++;
            } else {
                actual = actual.getSiguiente();
            }
        }
        if (eliminados == 0) {
            System.out.println("No se encontro el nodo con el dato");
        } else {
            System.out.println("Nodos eliminados: " + eliminados);
        }
    }

    // ===================== BÚSQUEDA =====================

    // Devuelve la posición (desde 1) o -1 si no existe
    public int BusquedaSecuencial(String dato) {
        NodoSimple actual = this.primero;
        int posicion = 1;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            actual = actual.getSiguiente();
            posicion++;
        }
        return -1;
    }

    // La lista debe estar ordenada ascendentemente. Devuelve posición (desde 1) o -1
    public int BusquedaBinaria(String dato) {
        int inicio = 0;
        int fin = Contar() - 1;
        NodoSimple actual2 = this.primero; // nodo ubicado en el índice "inicio"
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            NodoSimple actual = actual2;
            for (int i = inicio; i < medio; i++) {
                actual = actual.getSiguiente();
            }
            int comparacion = Comparar(dato, actual.getDato());
            if (comparacion == 0) {
                return medio + 1;
            } else if (comparacion > 0) {
                inicio = medio + 1;
                actual2 = actual.getSiguiente();
            } else {
                fin = medio - 1;
            }
        }
        return -1;
    }

    // ===================== ORDENAMIENTO =====================

    public void OrdenarSeleccion() {
        NodoSimple actual = this.primero;
        while (actual != null && actual.getSiguiente() != null) {
            NodoSimple actual3 = actual; // nodo con el menor dato encontrado
            NodoSimple actual2 = actual.getSiguiente();
            while (actual2 != null) {
                if (Comparar(actual2.getDato(), actual3.getDato()) < 0) {
                    actual3 = actual2;
                }
                actual2 = actual2.getSiguiente();
            }
            if (actual3 != actual) {
                String temp = actual.getDato();
                actual.setDato(actual3.getDato());
                actual3.setDato(temp);
            }
            actual = actual.getSiguiente();
        }
    }

    public void OrdenarBurbuja() {
        if (this.primero == null || this.primero.getSiguiente() == null) {
            return;
        }
        NodoSimple actual3 = null; // límite: desde aquí ya está ordenado
        boolean huboCambio;
        do {
            huboCambio = false;
            NodoSimple actual = this.primero;
            while (actual.getSiguiente() != actual3) {
                NodoSimple actual2 = actual.getSiguiente();
                if (Comparar(actual.getDato(), actual2.getDato()) > 0) {
                    String temp = actual.getDato();
                    actual.setDato(actual2.getDato());
                    actual2.setDato(temp);
                    huboCambio = true;
                }
                actual = actual2;
            }
            actual3 = actual;
        } while (huboCambio);
    }

    // Reconecta los nodos: cada uno se inserta en su lugar en la lista ordenada
    public void OrdenarInsercion() {
        NodoSimple ordenada = null;
        NodoSimple actual = this.primero;
        while (actual != null) {
            NodoSimple actual2 = actual.getSiguiente(); // resto de la lista original
            if (ordenada == null || Comparar(actual.getDato(), ordenada.getDato()) < 0) {
                actual.setSiguiente(ordenada);
                ordenada = actual;
            } else {
                NodoSimple actual3 = ordenada;
                while (actual3.getSiguiente() != null
                        && Comparar(actual3.getSiguiente().getDato(), actual.getDato()) < 0) {
                    actual3 = actual3.getSiguiente();
                }
                actual.setSiguiente(actual3.getSiguiente());
                actual3.setSiguiente(actual);
            }
            actual = actual2;
        }
        this.primero = ordenada;
    }

    // ===================== RECORRIDO =====================

    public void Recorrer() {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else {
            NodoSimple actual = this.primero;
            while (actual != null) {
                System.out.print("[" + actual.getDato() + "] -> ");
                actual = actual.getSiguiente();
            }
            System.out.println("null");
        }
    }

    public NodoSimple getPrimero() {
        return primero;
    }

    public void setPrimero(NodoSimple primero) {
        this.primero = primero;
    }
}