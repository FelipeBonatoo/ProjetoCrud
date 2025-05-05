package com.seuprojeto.dao;

import java.sql.*;  // Importa todas as classes do pacote java.sql
import java.util.ArrayList;
import java.util.List;

import com.seuprojeto.model.Produto;
import org.postgresql.core.ConnectionFactory;

public class BancoDeDados {

    public void createTable(Connection conn, String nome_tabela) {
        Statement statement;

        try {
            statement = conn.createStatement();
            String queryDrop = "DROP TABLE IF EXISTS " + "equipamentos" + ";";
            statement.executeUpdate(queryDrop);
            String query = " create table " + nome_tabela + "(empid SERIAL PRIMARY KEY,  nome varchar(50),descricao varchar(300), " +
                    "data_aquisicao DATE, valor NUMERIC(10,2), validade DATE);";
            statement.executeUpdate(query);
            System.out.println("Tabela criada com sucesso.");

        } catch (SQLException e) {
            System.out.println("Não foi possivel criar a tabela no banco de dados." + e);
        }
    }

    public static void cadastrarProduto(Produto obj, Connection conn) {
        PreparedStatement stmnt = null;
        try {
            String sql = "insert into equipamentos ( nome, descricao, data_aquisicao, valor, validade) values (?,?,?,?,?)";
            stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, obj.getNome());
            stmnt.setString(2, obj.getDescricao());
            stmnt.setDate(3, new java.sql.Date(obj.getData_aquisicao().getTime()));
            stmnt.setInt(4, obj.getValor());
            stmnt.setDate(5, new java.sql.Date(obj.getValidade().getTime()));
            stmnt.execute();

            System.out.println("Produto criado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Não foi possivel inserir o produto ao banco de dados." + e);
        } finally {
            try {
                if (stmnt != null) {
                    stmnt.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void atualizar(Produto obj, Connection conn) {
        String sql = "UPDATE equipamentos SET nome = ?, descricao = ?, data_aquisicao = ?, valor = ?, validade = ? WHERE empid = ?";
        PreparedStatement stmnt = null;

        try {
            stmnt = conn.prepareStatement(sql);
            stmnt.setString(1, obj.getNome());
            stmnt.setString(2, obj.getDescricao());
            stmnt.setDate(3, new java.sql.Date(obj.getData_aquisicao().getTime()));
            stmnt.setInt(4, obj.getValor());
            stmnt.setDate(5, new java.sql.Date(obj.getValidade().getTime()));
            stmnt.setInt(6, obj.getId());
            stmnt.execute();

        } catch (SQLException e) {
            System.out.println("Nao foi possivel atualizar o produto" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmnt != null) {
                    stmnt.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void delete(int empid, Connection conn) {
        String sql = "DELETE FROM equipamentos WHERE empid = ?";

        PreparedStatement stmnt = null;
        try {
            stmnt = conn.prepareStatement(sql);
            stmnt.setInt(1, empid);
            stmnt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ListarEquipamentos(Produto  produto, Connection conn){
        String sql = "SELECT * FROM equipamentos";

        List<Produto> produtos = new ArrayList<Produto>();
        PreparedStatement stmnt = null;

        ResultSet rst = null;

        try {

            stmnt = conn.prepareStatement(sql);

            rst = stmnt.executeQuery();
            while (rst.next()){
                Produto ListaDosProdutos = new Produto();

                ListaDosProdutos.setId(rst.getInt("enpid"));
                ListaDosProdutos.setNome(rst.getString("nome"));
                ListaDosProdutos.setDescricao(rst.getString("descricao"));
                ListaDosProdutos.setData_aquisicao(rst.getDate("data_aquisicao"));
                ListaDosProdutos.setValidade(rst.getDate("validade"));
                ListaDosProdutos.setValor(rst.getInt("valor"));


            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
