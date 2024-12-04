import java.util.LinkedList;
import java.util.Queue;

public class ServicioNotificacion {
    private Queue<Notificacion> notificaciones;

    public ServicioNotificacion() {
        notificaciones = new LinkedList<>();
    }

    public void addNotification(Notificacion notification) {
        notificaciones.add(notification);
    }

    public boolean hasNotifications() {
        return !notificaciones.isEmpty();
    }

    public Notificacion getNextNotification() {
        return notificaciones.poll();
    }

    public Queue<Notificacion> getAllNotifications() {
        return notificaciones;
    }
}
