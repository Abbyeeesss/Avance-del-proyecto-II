import java.util.ArrayList;
import java.util.List;

public class ServicioReservacionVuelo {
    private List<ReservacionVuelo> reservas;

    public ServicioReservacionVuelo() {
        reservas = new ArrayList<>();
    }

    public void addReservation(ReservacionVuelo reserva) {
        reservas.add(reserva);
    }

    public List<ReservacionVuelo> getReservations() {
        return reservas;
    }
}
