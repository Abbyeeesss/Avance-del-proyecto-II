import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AplicacionGUI {
    private JTabbedPane tabbedPane1;
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton iniciarSesiónButton;
    private JButton registrarseButton;
    private JTextField textNumVuelo;
    private JTextField txtFechaSalida;
    private JTextField txtDestino;
    private JTextField txtIdReservar;
    private JButton reservarVueloButton;
    private JButton visualizarReservaciónButton;
    private JTextArea txtNotificacion;
    private JComboBox<String> comboBoxPrioridad;
    private JButton añadirNotificaciónButton;
    private JButton verSiguienteNotificaciónButton;
    private JButton verTodasLasNotificacionesButton;
    private JPanel pGeneral;

    private ServicioUsuario servicioUsuario;
    private ServicioReservacionVuelo servicioReservacion;
    private ServicioNotificacion servicioNotificacion;

    public AplicacionGUI() {
        servicioUsuario = new ServicioUsuario();
        servicioReservacion = new ServicioReservacionVuelo();
        servicioNotificacion = new ServicioNotificacion();

        // Iniciar sesión
        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contraseña = new String(txtContraseña.getPassword());
                if (servicioUsuario.autenticar(usuario, contraseña)) {
                    JOptionPane.showMessageDialog(null, "Login exitoso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
                }
            }
        });

        // Registrarse
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contraseña = new String(txtContraseña.getPassword());
                if (servicioUsuario.registro(usuario, contraseña)) {
                    JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
                    txtUsuario.setText("");
                    txtContraseña.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe.");
                }
            }
        });

        // Reservar vuelo
        reservarVueloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numVuelo = textNumVuelo.getText();
                String fechaSalida = txtFechaSalida.getText();
                String destino = txtDestino.getText();
                String idReservar = txtIdReservar.getText();

                if (numVuelo.isEmpty() || fechaSalida.isEmpty() || destino.isEmpty() || idReservar.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
                    return;
                }

                ReservacionVuelo reservation = new ReservacionVuelo(idReservar, numVuelo, fechaSalida, destino);
                servicioReservacion.addReservation(reservation);
                JOptionPane.showMessageDialog(null, "Vuelo reservado exitosamente.");

                // Crear una notificación cuando se realiza una reserva
                String notificationMessage = "Reserva de vuelo con ID " + idReservar + " ha sido realizada.";
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                Notificacion notification = new Notificacion(notificationMessage, 1, timestamp);
                servicioNotificacion.addNotification(notification);
            }
        });

        // Visualizar reservaciones
        visualizarReservaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ReservacionVuelo> reservas = servicioReservacion.getReservations();

                if (reservas != null && !reservas.isEmpty()) {
                    ReservacionVuelo reserva = reservas.get(0);

                    StringBuilder mensaje = new StringBuilder();
                    mensaje.append("Reserva de vuelo:\n");
                    mensaje.append("ID: ").append(reserva.getId()).append("\n");
                    mensaje.append("Vuelo: ").append(reserva.getNumVuelo()).append("\n");
                    mensaje.append("Fecha: ").append(reserva.getFechaSalida()).append("\n");
                    mensaje.append("Destino: ").append(reserva.getDestino()).append("\n");

                    JOptionPane.showMessageDialog(null, mensaje.toString(), "Detalle de la Reserva", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay reservas disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Añadir notificación
        añadirNotificaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = txtNotificacion.getText();
                int priority = comboBoxPrioridad.getSelectedIndex() + 1;
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                Notificacion notification = new Notificacion(message, priority, timestamp);
                servicioNotificacion.addNotification(notification);
                JOptionPane.showMessageDialog(null, "Notificación añadida.");
            }
        });

        // Ver siguiente notificación
        verSiguienteNotificaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (servicioNotificacion.hasNotifications()) {
                    Notificacion nextNotification = servicioNotificacion.getNextNotification();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Siguiente Notificación:\n").append(nextNotification).append("\n");

                    // Mostrar la primera reserva si existe
                    List<ReservacionVuelo> reservas = servicioReservacion.getReservations();
                    if (!reservas.isEmpty()) {
                        sb.append("\nReserva de vuelo:\n").append(reservas.get(0));  // Muestra solo la primera reserva
                    } else {
                        sb.append("\nNo hay reservas de vuelo.");
                    }

                    JOptionPane.showMessageDialog(null, sb.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No hay notificaciones.");
                }
            }
        });

        // Ver todas las notificaciones
        verTodasLasNotificacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();

                // Mostrar todas las notificaciones
                sb.append("Notificaciones:\n");
                for (Notificacion notification : servicioNotificacion.getAllNotifications()) {
                    sb.append(notification).append("\n");
                }

                // Mostrar todas las reservas
                List<ReservacionVuelo> reservas = servicioReservacion.getReservations();
                if (!reservas.isEmpty()) {
                    sb.append("\nReservas de vuelo:\n");
                    for (ReservacionVuelo reserva : reservas) {
                        sb.append(reserva).append("\n");
                    }
                } else {
                    sb.append("\nNo hay reservas de vuelo.");
                }

                // Si no hay notificaciones ni reservas
                if (sb.length() == 0) {
                    sb.append("No hay notificaciones o reservas.");
                }

                txtNotificacion.setText(sb.toString());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AplicacionGUI");
        frame.setContentPane(new AplicacionGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);
    }
}
