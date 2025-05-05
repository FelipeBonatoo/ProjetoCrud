package com.seuprojeto.servlet;

import java.sql.Connection;
import java.util.Date;
import com.seuprojeto.dao.Conexao;
import com.seuprojeto.dao.BancoDeDados;
import com.seuprojeto.model.Produto;

public class Main {
    public static void main(String[] args) {

        Conexao bd = new Conexao();
        Connection conn = bd.fazendoaconexao("bancodedados", "postgres", "admin");
        //criando a tabela equipamentos

        BancoDeDados bancoDeDados = new BancoDeDados();
        bancoDeDados.createTable(conn, "equipamentos");

        //criacao de um produto teste
        Produto ListaDosProdutos = new Produto();
        ListaDosProdutos.setId(1);
        ListaDosProdutos.setNome("Produto P1");
        ListaDosProdutos.setDescricao("Descrição do P1");
        ListaDosProdutos.setData_aquisicao(new Date(124, 9, 20));
        ListaDosProdutos.setValor(5000);
        ListaDosProdutos.setValidade(new Date());

        bancoDeDados.cadastrarProduto(ListaDosProdutos, conn);

        //método atualizar do CRUD

       /* Produto p2 = new Produto();
        p2.setId(1);
        p2.setNome("Produto P1 Atualizado");
        p2.setDescricao("Descrição atualizada");
        p2.setData_aquisicao(new Date());
        p2.setValor(8000);
        p2.setValidade(new Date());
        bancoDeDados.atualizar(p2, conn);

        //método deletar do CRUD
        bancoDeDados.delete(1, conn);

*/
    }
}