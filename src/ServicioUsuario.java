import java.util.HashMap;
import java.util.Map;

public class ServicioUsuario {
    private Map<String, String> usuarios;

    public ServicioUsuario() {
        usuarios = new HashMap<>();
    }

    //Método para registrar un usuario
    public boolean registro(String username, String password) {
        if (usuarios.containsKey(username)) {
            return false;
        }
        usuarios.put(username, password);
        return true;
    }

    // Método para autenticar a un usuario
    public boolean autenticar(String username, String password) {
        return password.equals(usuarios.get(username));
    }
}
