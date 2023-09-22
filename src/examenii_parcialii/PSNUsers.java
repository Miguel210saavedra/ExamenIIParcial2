package examenii_parcialii;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PSNUsers {

    private RandomAccessFile RAF;
    private HashTable users;

    public PSNUsers() {
        try {
            RAF = new RandomAccessFile("src/psn/psn.emp", "rw");
            users = new HashTable();
            reloadHashTable();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PSNUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PSNUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reloadHashTable() throws IOException {
        RAF.seek(0);
        while (RAF.getFilePointer() < RAF.length()) {
            try {
                String Usuario = RAF.readUTF();
                long posicion = RAF.getFilePointer();
                users.add(Usuario, posicion);
                RAF.readUTF();
                boolean isActive = RAF.readBoolean();
                if (!isActive) {
                    RAF.readLong();
                }
            } catch (EOFException e) {

                break;
            }
        }
    }

    public void addUser(String usuraio) throws IOException {
        if (users.search(usuraio) != -1) {
            System.out.println("Error el usuario ya existe");
            return;
        }

        long posicion = RAF.length();
        RAF.writeUTF(usuraio);
        RAF.writeUTF("");
        RAF.writeBoolean(true);
        users.add(usuraio, posicion);
    }

    public void deactivateUser(String usuario) throws IOException {
        long pisicion = users.search(usuario);
        if (pisicion != -1) {
            RAF.seek(pisicion + usuario.length() * 2);
            RAF.writeBoolean(false);
            users.remove(usuario);
        }
    }

    public void addTrophyTo(String usuario, String trophyGame, String trophyName, Trophy type, String date) throws IOException {
        long posicion = users.search(usuario);
        if (posicion != -1) {

            RAF.seek(RAF.length());

            RAF.writeUTF(usuario);
            RAF.writeUTF(trophyGame);
            RAF.writeUTF(trophyName);
            RAF.writeUTF(type.name());
            RAF.writeUTF(date);

            users.add(usuario, RAF.getFilePointer() - usuario.length() * 2);
        }

    }

}
