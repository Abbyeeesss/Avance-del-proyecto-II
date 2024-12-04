public class Notificacion {
    private String mensaje;
    private int priority;
    private String tiempo;

    public Notificacion(String mensaje, int priority, String tiempo) {
        this.mensaje = mensaje;
        this.priority = priority;
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Mensaje: " + mensaje + "\nPrioridad: " + priority + "\nHora: " + tiempo;
    }
}
