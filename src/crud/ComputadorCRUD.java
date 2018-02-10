/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import bd.Conexao;
import entity.Computador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jhonatan
 */
public class ComputadorCRUD extends Conexao {

    private static ComputadorCRUD instancia = null;

    public static ComputadorCRUD getInstance() {
        if (instancia == null) {
            instancia = new ComputadorCRUD();
        }
        return instancia;
    }

    public boolean inserir(Computador c) {
        try {
            Connection con = criarConexao();
            String sql = "INSERT INTO computador VALUES (null, ?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getHost());
            ps.setString(2, c.getUsuario());
            ps.setDate(3, c.getDia());
            ps.setTime(4, c.getHora());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir ao já existe");
        }
        return false;
    }

    public ArrayList<Computador> listarTodos() {
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        ArrayList<Computador> lista = new ArrayList<Computador>();
        String sql = "SELECT * FROM computador GROUP BY usuario";

        con = criarConexao();

        try {
            st = con.createStatement();
            res = st.executeQuery(sql);
            while (res.next()) {
                Computador c = new Computador();
                c.setId(res.getInt("id"));
                c.setHost(res.getString("host"));
                c.setUsuario(res.getString("usuario"));
                c.setDia(res.getDate("dia"));
                c.setHora(res.getTime("hora"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar");
        }
        return lista;
    }
}
