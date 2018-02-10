/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author jhonatan
 */
public class Cliente {

    public static void main(String[] args) {
        Socket s = null;

        PrintStream ps;

        String data = null, hora = null, host = null;

        try {

            s = new Socket("127.0.0.1", 7000);

            ps = new PrintStream(s.getOutputStream());
            BufferedReader stdInput = null;

            if (System.getProperty("os.name").contains("Windows")) {
                stdInput = new BufferedReader(
                        new InputStreamReader(Runtime.getRuntime().exec("wmic os get lastBootUpTime")
                                .getInputStream()));
                String st = null;
                for (int i = 0; i < 3; i++) {
                    st = stdInput.readLine();
                }

                stdInput = new BufferedReader(
                        new InputStreamReader(Runtime.getRuntime().exec("hostname")
                                .getInputStream()));

                host = stdInput.readLine();

                data = st.substring(0, 4)
                        + st.substring(4, 6)
                        + st.substring(6, 8) + "\n";

                hora = st.substring(8, 10)
                        + st.substring(10, 12)
                        + st.substring(12, 14) + "\n";
            }

            ps.printf(data);
            ps.printf(hora);
            ps.printf(System.getProperty("user.name") + "\n");
            ps.printf(host + "\n");
            ps.flush();
        } catch (IOException e) {

            System.out.println("Algum problema ocorreu ao criar ou enviar dados pelo socket.");

        } finally {

            try {
                s.close();

            } catch (IOException e) {
            }

        }
    }
}
