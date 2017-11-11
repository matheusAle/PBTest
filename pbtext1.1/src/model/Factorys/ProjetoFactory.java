package model.Factorys;

import controller.exceptions.ProjetoException;
import model.Projeto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Classe para reealizar operaçoes realacionada a projetos no banco de dados
 */
public class ProjetoFactory extends AbstractFactory {

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
            String srcProducao,
            String srcTestes,
            String CASO_DE_TESTE,
            String CASO_DE_USO,
            String ROTEIRO_DE_TESTE,
            String descricao,
            String email
    ){
        String query = "INSERT INTO projeto " +
                        "(nome, srcProducao, srcTestes, prefixoCT, prefixoCU, prefixoRT, descricao, usuario_dono)" +
                        "VALUES (" +
                        "'" + nome + "', " +
                        "'" + srcProducao + "', " +
                        "'" + srcTestes + "', " +
                        "'" + CASO_DE_TESTE + "', " +
                        "'" + CASO_DE_USO + "', " +
                        "'" + ROTEIRO_DE_TESTE + "', " +
                        "'" + descricao + "', " +
                        "'" + email + "')";
        super.execultarAtualizacao(query);
        return true;
            
    }

    /**
     * busca no banco de dados o id, nome, srcRaiz e a descrição de todos os projetos.
     * @return retorna uma <link>Collection</link> de <Link>Projeto</Link>
     */
    public synchronized Collection listar() {
        String query = "SELECT * FROM projeto";
        ResultSet resultSet = super.execultarBusca(query);
        if (resultSet != null){
            try {
                LinkedList<Projeto> lista = new LinkedList<>();
                while (resultSet.next()){
                    lista.add(new Projeto(
                        resultSet.getString("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getString("srcProducao"),
                        resultSet.getString("srcTestes"),
                        resultSet.getString("prefixoCT"),
                        resultSet.getString("prefixoRT"),
                        resultSet.getString("prefixoCU"),
                        resultSet.getString("usuario_dono")
                ));
                }
                resultSet.close();
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
    public synchronized Projeto buscar(String restricao) {
        try {
            String query = "SELECT * FROM projeto WHERE " + restricao;
            ResultSet resultSet = super.execultarBusca(query);
            while (resultSet.next()){
                return new Projeto(
                        resultSet.getString("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getString("srcProducao"),
                        resultSet.getString("srcTestes"),
                        resultSet.getString("prefixoCT"),
                        resultSet.getString("prefixoRT"),
                        resultSet.getString("prefixoCU"),
                        resultSet.getString("usuario_dono")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Execulta um update que atualiza sem verificação os atributos <code>nome, srcRaiz, descricao</code>
     * para a tupla que tiver o <code>id</code> igual ao do objeto passado por paramentro
     * @return true se bem sucedido.
     */
    public synchronized boolean atualizar(String nome, String descicao, String srcProducao, String srcTestes, String codigo) {
        String dml = String.format("UPDATE projeto SET nome = '%s', srcProducao = '%s', srcTestes = '%s', descricao = '%s' WHERE id = %s ", nome, srcProducao, srcTestes, descicao, codigo);
        super.execultarAtualizacao(dml);
        return true;
    }

    /**
     * Busca o valor atau do contador no banco de dados. E atualiza a contagem
     * @param nomeContador Nome do contador: valores possiveis: contadorCT, contadorCU, contadorRT
     * @param codigoProjeto Codigo do projeto.
     * @return Uma string com o valor do contador solicitado.
     * @throws ProjetoException pode ser disparado caso algo de errado.
     */
    public String buscarContador(String nomeContador, String codigoProjeto){
        String query = String.format("SELECT %s FROM projeto WHERE id = %s ", nomeContador, codigoProjeto);
        long contador = 0;
        try {
            ResultSet resultSet = execultarBusca(query);
            while (resultSet.next()){
                contador = resultSet.getLong(nomeContador);
            }
            String dml = String.format("UPDATE projeto SET %s = %d WHERE id = %s ", nomeContador, contador+1, codigoProjeto);
            execultarAtualizacao(dml);
            return String.valueOf(contador);
        }catch (SQLException e){
            e.printStackTrace();
            throw new ProjetoException("Erro ao ler o contador " + nomeContador + "No banco de dados");
        }
    }

    /**
     * Deleta a(s) tupla databela projeto com a restrição informada.
     * @param restricao
     * @throws ProjetoException pode ser disparado caso algo de errado.
     */
    public void deletar(String restricao){
        String dml = "DELETE FROM projeto WHERE " + restricao;
        execultarAtualizacao(dml);
    }

}
