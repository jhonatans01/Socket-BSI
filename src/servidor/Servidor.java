/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import bd.Conexao;
import crud.ComputadorCRUD;
import entity.Computador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhonatan
 */
public class Servidor {

    public static void main(String[] args) {
        while (true) {
            ServerSocket serv = null;

            Socket s = null;

            BufferedReader entrada = null;

            PrintStream saida = null;

            boolean flag = true;

            String data = null, hora = null, user = null, host = null;

            try {

                serv = new ServerSocket(2020);

                s = serv.accept();

                entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));

                saida = new PrintStream(s.getOutputStream());

                System.out.println("Servidor\n");

                Computador c = new Computador();

                data = entrada.readLine();
                hora = entrada.readLine();
                user = entrada.readLine();
                host = entrada.readLine();

                DateFormat format = new SimpleDateFormat("yyyyMMdd");
                java.util.Date parsed = format.parse(data);
                c.setDia(new Date(parsed.getTime()));

                format = new SimpleDateFormat("HHmmss");
                c.setHora(new Time(format.parse(hora).getTime()));

                c.setUsuario(user);
                c.setHost(host);

                ComputadorCRUD crud = ComputadorCRUD.getInstance();
                crud.inserir(c);

                ArrayList<Computador> pcs = crud.listarTodos();

                for (Computador pc : pcs) {
                    saida.println("Host: " + pc.getHost());
                    saida.println("Usuário: " + pc.getUsuario());
                    saida.println("Dia: " + pc.getDia());
                    saida.println("Hora: " + pc.getHora());
                    saida.println("");
                }
                if (entrada.equals("true")) {
                    flag = true;
                } else {
                    flag = false;
                }
            } catch (IOException e) {

                System.out.println("Algum problema ocorreu ao criar ou receber o socket.");

            } catch (ParseException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                try {

                    s.close();
                    serv.close();

                } catch (IOException e) {
                }
            }
        }
    }
}
