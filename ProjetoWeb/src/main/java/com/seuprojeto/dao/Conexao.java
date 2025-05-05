package com.seuprojeto.dao;

import java.sql.*;

public class Conexao {
    public Connection fazendoaconexao(String bancodedados, String usuario, String senha) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + bancodedados, usuario, senha);
            if (conn != null) {
                System.out.println("Conexão concluida!");
            } else {
                System.out.println("Falha em realizar a conexão, tente novamente.");
            }
        }  catch (Exception e) {
            System.out.println("Erro ao conectar:");
            e.printStackTrace();
        }
        return conn;
    }
}