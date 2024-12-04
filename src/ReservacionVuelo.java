public class ReservacionVuelo {
    private String id;
    private String numVuelo;
    private String fechaSalida;
    private String destino;

    public ReservacionVuelo(String id, String numVuelo, String fechaSalida, String destino) {
        this.id = id;
        this.numVuelo = numVuelo;
        this.fechaSalida = fechaSalida;
        this.destino = destino;
    }

    // Getters y setters
    public String getId() { return id; }
    public String getNumVuelo() { return numVuelo; }
    public String getFechaSalida() { return fechaSalida; }
    public String getDestino() { return destino; }

    @Override
    public String toString() {
        return "ID: " + id + "\nVuelo: " + numVuelo + "\nFecha: " + fechaSalida + "\nDestino: " + destino;
    }
}
