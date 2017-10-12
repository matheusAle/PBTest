package model;

import controller.ProjetoAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Classe para reealizar operaçoes realacionada a projetos no banco de dados
 */
public class ProjetoDAO extends DAO{

    /**
     * Persiste as informações do projeto passadas como paramentro.
     * @param nome
     * @param src
     * @param CASO_DE_TESTE prefixo dos casos de teste
     * @param CASO_DE_USO  prefixo dos casos de uso
     * @param ROTEIRO_DE_TESTE prefixo dos casos roteiros de teste
     * @param descricao descrição do projeto
     * @param email email do usuario que o esta cadastrando.
     * @return true se bem sicedido
     */
    public boolean salvar(
            String nome,
            String src,
            String CASO_DE_TESTE,
            String CASO_DE_USO,
            String ROTEIRO_DE_TESTE,
            String descricao,
            String email
    ){
        String query = "INSERT INTO projeto " +
                        "(nome, srcRaiz, prefixoCT, prefixoCU, prefixoRT, descricao, usuario_dono)" +
                        "VALUES (" +
                        "'" + nome + "', " +
                        "'" + src + "', " +
                        "'" + CASO_DE_TESTE + "', " +
                        "'" + CASO_DE_USO + "', " +
                        "'" + ROTEIRO_DE_TESTE + "', " +
                        "'" + descricao + "', " +
                        "'" + email + "')";
        try {
            super.execultarAtualizacao(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * busca no banco de dados o id, nome, srcRaiz e a descrição de todos os projetos.
     * @return retorna uma <link>Collection</link> de <Link>ProjetoAdapter</Link>
     */
    @Override
    public Collection listar() {
        String query = "SELECT id, nome, srcRaiz, descricao FROM projeto";
        ResultSet resultSet = super.execultarBusca(query);
        if (resultSet != null){
            try {
                LinkedList<ProjetoAdapter> lista = new LinkedList<>();
                while (resultSet.next()){
                    lista.add(new ProjetoAdapter(
                            resultSet.getString("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("descricao"),
                            resultSet.getString("srcRaiz")
                    ));
                }
                return lista;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Busca na relação de Projetos no banco de dados todos os valores de todas a tuplas que
     * passen pelo crivo da restrição.
     * @param restricao restrição da busca. Ex: id = '123'
     * @return Retorna uma <link>Collection</link> com objetos <link>Projeto</link> em seu conteudo.
     */
    @Override
    public Collection buscar(String restricao) {
        try {
            String query = "SELECT * FROM projeto WHERE " + restricao;
            ResultSet resultSet = super.execultarBusca(query);
            LinkedList<Projeto> lista = new LinkedList<Projeto>();
            while (resultSet.next()){
                lista.add(new Projeto(
                        resultSet.getString("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getString("srcRaiz"),
                        resultSet.getString("prefixoCU"),
                        resultSet.getString("prefixoCT"),
                        resultSet.getString("prefixoRT"),
                        resultSet.getInt("contadorCU"),
                        resultSet.getInt("contadorCT"),
                        resultSet.getInt("contadorRT")
                ));
            }
            return lista;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Execulta um update que atualiza sem verificação os atributos <code>nome, srcRaiz, descricao</code>
     * para a tupla que tiver o <code>id</code> igual ao do objeto passado por paramentro
     * @param prj projeto que tera seus dados atalizados no banco de dados.
     * @return true se bem sucedido.
     */
    public boolean atualizar(ProjetoAdapter prj) {
        String dml = "UPDATE projeto SET " +
                "nome = '" + prj.getNome() + "', " +
                "srcRaiz = '" + prj.getSrc() + "', " +
                "descricao = '" + prj.getDescricao() +
                "' WHERE id = " + prj.getCodigo();
        try {
            super.execultarAtualizacao(dml);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
