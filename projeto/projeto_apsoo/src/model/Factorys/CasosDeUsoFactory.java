package model.Factorys;

import controller.ProjetoController;
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
    @Override
    public Collection listar(){
        String query;
        try {
            query = "SELECT * FROM caso_de_uso WHERE projetoID = " + ProjetoController.getInformacoesDoProjetoAtivo().getCodigo();
        }catch (NullPointerException e){
            return null;
        }
        ResultSet result = AbstractFactory.execultarBusca(query);
        LinkedList<CasoDeUso> lista = new LinkedList<>();
        try {
            while (result.next()){
                lista.add(
                        new CasoDeUso(
                                result.getString("codigo"),
                                result.getString("nome"),
                                result.getString("objetivo"),
                                result.getString("atores"),
                                result.getString("descricao"),
                                result.getString("usuario_dono"),
                                result.getString("projetoID")

                                )
                );
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection buscar(String restricao) {
        return null;
    }

    public boolean salvar(String codigo, String nome, String objetivo, String atores, String descricao){
        String dml = String.format("INSERT INTO caso_de_uso VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", codigo, nome, objetivo, atores, descricao);
        try {
            AbstractFactory.execultarAtualizacao(dml);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
