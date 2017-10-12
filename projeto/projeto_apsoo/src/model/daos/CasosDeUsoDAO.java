package model.daos;

import controller.ProjetoController;
import controller.adapters.CasoDeUsoAdapter;
import controller.exceptions.ProjetoException;
import model.CasoDeUso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class CasosDeUsoDAO extends DAO {

    /**
     * Busca no banco de dados todos os casos de uso catrastrados vinculados ao projeto ativo.
     * @return Retorna uma <link>Collection</link> contendo objetos <link>CasoDeUsoAdapter</link>
     * @throws ProjetoException lançada apenas quando não existir um projeto ativo.
     */
    @Override
    public Collection listar() throws ProjetoException {
        String query;
        try {
            query = "SELECT * FROM caso_de_uso WHERE projetoID = " + ProjetoController.getInformacoesDoProjetoAtivo().getCodigo();
        }catch (NullPointerException e){
            throw new ProjetoException();
        }
        ResultSet result = DAO.execultarBusca(query);
        LinkedList<CasoDeUsoAdapter> lista = new LinkedList<>();
        try {
            while (result.next()){
                lista.add(
                        new CasoDeUsoAdapter(
                                result.getString("codigo"),
                                result.getString("nome"),
                                result.getString("objetivo"),
                                result.getString("atores")
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

    public boolean salvar(CasoDeUso c){
        String dml = "INSERT INTO caso_de_uso VALUES ('" +
                c.getCodigo() + "', '" +
                c.getNome() + "', '" +
                c.getAtores()+ "', '" +
                c.getObjetovo()+ "', '" +
                c.getDescricao()+ "', " +
                c.getProjetoID()+ ", '" +
                c.getEmailUsuario()+ "')";
        try {
            DAO.execultarAtualizacao(dml);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
