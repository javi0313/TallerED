package ListasDobles;

public class ListaDoble {
    private NodoDoble primero;
    private NodoDoble ultimo;

    public ListaDoble() {
        this.primero = null;
        this.ultimo = null;
    }

    // ===================== AUXILIARES =====================

    public int Contar() {
        int cantidad = 0;
        NodoDoble actual = this.primero;
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

    // Devuelve el primer nodo con ese dato, o null
    private NodoDoble BuscarNodo(String dato) {
        NodoDoble actual = this.primero;
        while (actual != null && !actual.getDato().equals(dato)) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    // Saca un nodo de la lista dejando consistentes los punteros de sus vecinos
    private void Desenlazar(NodoDoble nodo) {
        if (nodo.getAnterior() != null) {
            nodo.getAnterior().setSiguiente(nodo.getSiguiente());
        } else {
            this.primero = nodo.getSiguiente();
        }
        if (nodo.getSiguiente() != null) {
            nodo.getSiguiente().setAnterior(nodo.getAnterior());
        } else {
            this.ultimo = nodo.getAnterior();
        }
        nodo.setAnterior(null);
        nodo.setSiguiente(null);
    }

    // Inserta "nuevo" justo antes de "referencia" (que debe estar en la lista)
    private void InsertarAntesDeNodo(NodoDoble nuevo, NodoDoble referencia) {
        if (referencia == this.primero) {
            InsertarInicio(nuevo);
        } else {
            nuevo.setAnterior(referencia.getAnterior());
            nuevo.setSiguiente(referencia);
            referencia.getAnterior().setSiguiente(nuevo);
            referencia.setAnterior(nuevo);
        }
    }

    // ===================== INSERCIÓN =====================

    public void InsertarInicio(NodoDoble nuevo) {
        if (this.primero == null) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setSiguiente(this.primero);
            this.primero.setAnterior(nuevo);
            this.primero = nuevo;
        }
    }

    public void InsertarFinal(NodoDoble nuevo) {
        if (this.ultimo == null) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setAnterior(this.ultimo);
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
    }

    public void InsertarAntes(NodoDoble nuevo, String ref) {
        NodoDoble actual = BuscarNodo(ref);
        if (actual == null) {
            System.out.println("Nodo no encontrado");
        } else {
            InsertarAntesDeNodo(nuevo, actual);
        }
    }

    public void InsertarDespues(NodoDoble nuevo, String ref) {
        NodoDoble actual = BuscarNodo(ref);
        if (actual == null) {
            System.out.println("Nodo no encontrado");
        } else if (actual == this.ultimo) {
            InsertarFinal(nuevo);
        } else {
            nuevo.setSiguiente(actual.getSiguiente());
            nuevo.setAnterior(actual);
            actual.getSiguiente().setAnterior(nuevo);
            actual.setSiguiente(nuevo);
        }
    }

    // Posiciones desde 1 hasta Contar() + 1
    public void InsertarPosicion(NodoDoble nuevo, int posicion) {
        int cantidad = Contar();
        if (posicion < 1 || posicion > cantidad + 1) {
            System.out.println("Posicion invalida");
        } else if (posicion == 1) {
            InsertarInicio(nuevo);
        } else if (posicion == cantidad + 1) {
            InsertarFinal(nuevo);
        } else {
            NodoDoble actual = this.primero;
            for (int i = 1; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
            InsertarAntesDeNodo(nuevo, actual);
        }
    }

    // ===================== ELIMINACIÓN =====================

    public void EliminarInicio() {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else {
            Desenlazar(this.primero);
        }
    }

    public void EliminarFinal() {
        if (this.ultimo == null) {
            System.out.println("Lista vacia");
        } else {
            Desenlazar(this.ultimo);
        }
    }

    // Elimina el nodo de referencia (primer nodo cuyo dato sea ref)
    public void EliminarNodo(String ref) {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else {
            NodoDoble actual = BuscarNodo(ref);
            if (actual == null) {
                System.out.println("No se encontro el nodo con el dato");
            } else {
                Desenlazar(actual);
            }
        }
    }

    public void EliminarAntes(String ref) {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else {
            NodoDoble actual = BuscarNodo(ref);
            if (actual == null) {
                System.out.println("No se encontro el nodo con el dato");
            } else if (actual.getAnterior() == null) {
                System.out.println("No hay nodo antes de la referencia");
            } else {
                Desenlazar(actual.getAnterior());
            }
        }
    }

    public void EliminarDespues(String ref) {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else {
            NodoDoble actual = BuscarNodo(ref);
            if (actual == null) {
                System.out.println("No se encontro el nodo con el dato");
            } else if (actual.getSiguiente() == null) {
                System.out.println("No hay nodo despues de la referencia");
            } else {
                Desenlazar(actual.getSiguiente());
            }
        }
    }

    // Posiciones desde 1 hasta Contar()
    public void EliminarPosicion(int posicion) {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else if (posicion < 1 || posicion > Contar()) {
            System.out.println("Posicion invalida");
        } else {
            NodoDoble actual = this.primero;
            for (int i = 1; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
            Desenlazar(actual);
        }
    }

    // Elimina TODAS las apariciones del dato
    public void EliminarPorDato(String dato) {
        int eliminados = 0;
        NodoDoble actual = this.primero;
        while (actual != null) {
            NodoDoble actual2 = actual.getSiguiente(); // se guarda antes de desenlazar
            if (actual.getDato().equals(dato)) {
                Desenlazar(actual);
                eliminados++;
            }
            actual = actual2;
        }
        if (eliminados == 0) {
            System.out.println("No se encontro el nodo con el dato");
        } else {
            System.out.println("Nodos eliminados: " + eliminados);
        }
    }

    // ===================== BÚSQUEDA =====================

    // Desde el primero hacia el último. Devuelve posición (desde 1) o -1
    public int BusquedaAdelante(String dato) {
        NodoDoble actual = this.primero;
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

    // Desde el último hacia el primero. Devuelve posición (desde 1) o -1
    public int BusquedaAtras(String dato) {
        NodoDoble actual = this.ultimo;
        int posicion = Contar();
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            actual = actual.getAnterior();
            posicion--;
        }
        return -1;
    }

    // Busca desde ambos extremos a la vez hasta que se cruzan
    public int BusquedaBidireccional(String dato) {
        NodoDoble actual = this.primero;
        NodoDoble actual2 = this.ultimo;
        int i = 1;
        int j = Contar();
        while (i <= j) {
            if (actual.getDato().equals(dato)) {
                return i;
            }
            if (actual2.getDato().equals(dato)) {
                return j;
            }
            actual = actual.getSiguiente();
            actual2 = actual2.getAnterior();
            i++;
            j--;
        }
        return -1;
    }

    // La lista debe estar ordenada ascendentemente. Devuelve posición (desde 1) o -1
    public int BusquedaBinaria(String dato) {
        int inicio = 0;
        int fin = Contar() - 1;
        NodoDoble actual2 = this.primero; // nodo ubicado en el índice "inicio"
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            NodoDoble actual = actual2;
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
        NodoDoble actual = this.primero;
        while (actual != null && actual.getSiguiente() != null) {
            NodoDoble actual3 = actual; // nodo con el menor dato encontrado
            NodoDoble actual2 = actual.getSiguiente();
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
        NodoDoble actual3 = null; // límite: desde aquí ya está ordenado
        boolean huboCambio;
        do {
            huboCambio = false;
            NodoDoble actual = this.primero;
            while (actual.getSiguiente() != actual3) {
                NodoDoble actual2 = actual.getSiguiente();
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
        NodoDoble actual2 = this.primero; // recorre la lista original
        this.primero = null;
        this.ultimo = null;
        while (actual2 != null) {
            NodoDoble actual = actual2;
            actual2 = actual2.getSiguiente();
            actual.setAnterior(null);
            actual.setSiguiente(null);

            if (this.primero == null) {
                this.primero = actual;
                this.ultimo = actual;
            } else if (Comparar(actual.getDato(), this.primero.getDato()) < 0) {
                actual.setSiguiente(this.primero);
                this.primero.setAnterior(actual);
                this.primero = actual;
            } else {
                NodoDoble actual3 = this.primero;
                while (actual3.getSiguiente() != null
                        && Comparar(actual3.getSiguiente().getDato(), actual.getDato()) < 0) {
                    actual3 = actual3.getSiguiente();
                }
                actual.setSiguiente(actual3.getSiguiente());
                actual.setAnterior(actual3);
                if (actual3.getSiguiente() != null) {
                    actual3.getSiguiente().setAnterior(actual);
                } else {
                    this.ultimo = actual;
                }
                actual3.setSiguiente(actual);
            }
        }
    }

    // ===================== RECORRIDO =====================

    public void RecorrerAdelante() {
        if (this.primero == null) {
            System.out.println("Lista vacia");
        } else {
            NodoDoble actual = this.primero;
            System.out.print("null <-> ");
            while (actual != null) {
                System.out.print("[" + actual.getDato() + "] <-> ");
                actual = actual.getSiguiente();
            }
            System.out.println("null");
        }
    }

    public void RecorrerAtras() {
        if (this.ultimo == null) {
            System.out.println("Lista vacia");
        } else {
            NodoDoble actual = this.ultimo;
            System.out.print("null <-> ");
            while (actual != null) {
                System.out.print("[" + actual.getDato() + "] <-> ");
                actual = actual.getAnterior();
            }
            System.out.println("null");
        }
    }

    public NodoDoble getPrimero() {
        return primero;
    }

    public void setPrimero(NodoDoble primero) {
        this.primero = primero;
    }

    public NodoDoble getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoDoble ultimo) {
        this.ultimo = ultimo;
    }
}