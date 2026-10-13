package ListasDobles;

public class NodoDoble {
    private String dato;
    private NodoDoble anterior;
    private NodoDoble siguiente;

    public NodoDoble(String dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
}