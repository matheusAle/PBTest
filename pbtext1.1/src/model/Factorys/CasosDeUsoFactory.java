package model.Factorys;

import controller.exceptions.CasoDeUsoExeption;
import controller.exceptions.ProjetoException;
import model.CasoDeUso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class CasosDeUsoFactory extends AbstractFactory {

    /**
     * Busca no banco de dados todos os casos de uso catrastrados vinculados ao projeto ativo.
     * @return Retorna uma <link>Collection</link> contendo objetos <link>CasoDeUso</link>
     * @throws ProjetoException lançada apenas quando não existir um projeto ativo.
     */

    public synchronized Collection<CasoDeUso> listar(String codProj){
        String query;
        try {
            query = "SELECT * FROM caso_de_uso WHERE projetoID = " + codProj;
        }catch (NullPointerException e){
            return null;
        }
        ResultSet result = execultarBusca(query);
        LinkedList<CasoDeUso> lista = new LinkedList<>();
        try {
            while (result.next()){
                lista.add(new CasoDeUso(
                        result.getString("codigo"),
                        result.getString("nome"),
                        result.getString("objetivo"),
                        result.getString("atores"),
                        result.getString("descricao"),
                        result.getString("usuario_dono"),
                        result.getString("projetoID"))
                );
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cria um novo caso de uso com os valores passados.
     * @param codigo codigo do novo caso de uso
     * @param nome nome do caso de uso
     * @param objetivo breve descrição do caso de uso
     * @param atores atores deste caso de uso
     * @param descricao descrição do caso de uso
     * @throws CasoDeUsoExeption Caso algo de errado.
     */
    public synchronized void salvar(String codigo, String nome, String objetivo, String atores, String descricao, String idPRJ, String email){
        String dml = String.format("INSERT INTO caso_de_uso VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", codigo, nome, atores, objetivo, descricao, idPRJ, email);
        try {
            execultarAtualizacao(dml);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CasoDeUsoExeption("Ocorreu um erro ao salvar o caso de uso no banco de dados!");
        }
    }

    /**
     * Deleta um caso de uso.
     * @param s destrição para a realizar o delete.
     */
    public void deletar(String s) {
        String dml = "DELETE FROM caso_de_uso WHERE " + s;
        try {
            execultarAtualizacao(dml);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CasoDeUsoExeption("Não foi posivel deletar o caso de uso!");
        }
    }

    /**
     * Busca o caso de uso com o codigo especificado no banco de dados
     * @param codigo codigo do caso de uso
     * @param codProj codigo do projeto.
     * @return retorna um novo objeto do tipo CasoDeUso.
     * @throws CasoDeUsoExeption caso ocorra algum erro.
     */
    public CasoDeUso buscar(String codigo, String codProj) {
        String query = String.format("SELECT * FROM caso_de_uso WHERE projetoID = %s AND codigo = '%s' ", codProj, codigo);
        ResultSet result = execultarBusca(query);
        LinkedList<CasoDeUso> lista = new LinkedList<>();
        try {
            while (result.next()){
                return new CasoDeUso(
                        result.getString("codigo"),
                        result.getString("nome"),
                        result.getString("objetivo"),
                        result.getString("atores"),
                        result.getString("descricao"),
                        result.getString("usuario_dono"),
                        result.getString("projetoID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CasoDeUsoExeption("Não foi possivel buscar o caso de uso no bamco de dados.");
        }
        return null;
    }

    public void atualizar(String codigo, String nome, String objetivo, String atores, String descricao, String codP){
        String dml = String.format("UPDATE caso_de_uso SET nome = '%s', objetivo = '%s', atores = '%s', descricao = '%s' WHERE projetoID = %s AND codigo = '%s' ", nome, objetivo, atores, descricao, codP, codigo);
        try {
            execultarAtualizacao(dml);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CasoDeUsoExeption("Erro ao atualizar as informações do caso de uso");
        }

    }
}
