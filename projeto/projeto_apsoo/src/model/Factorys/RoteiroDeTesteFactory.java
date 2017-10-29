package model.Factorys;

import com.sun.javafx.binding.StringFormatter;
import controller.exceptions.RoteiroDeTesteExeption;
import model.CasoDeTeste;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class RoteiroDeTesteFactory extends AbstractFactory {


    /**
     * Salva o novo roteiro de teste no banco de dados
     * @param codigo codogo do roteiro
     * @param nomeText nome do roteiro
     * @param descricaoText descrição deste roteiro
     * @param i situação deste roteiro
     * @param codigo1 codigo do projeto deste roteiro
     * @param emailUsuarioLogado usuario que o criou
     * @param casosDeTeste lista de codigos de casos de uso  do roteiro;
     * @throws RoteiroDeTesteExeption caso de algo de errado.
     */
    public void salvar(String codigo, String nomeText, String descricaoText, int i, String codigo1, String emailUsuarioLogado, LinkedList<String> casosDeTeste) {
        String dml = String.format("INSERT INTO roteiros_teste VALUES ('%s', '%s', '%s', %d, %s, '%s')", codigo, nomeText, descricaoText, i, codigo1, emailUsuarioLogado);
        System.out.println(dml);
        try {
            execultarAtualizacao(dml);
            try {
                for (String codC : casosDeTeste)
                    execultarAtualizacao(String.format("INSERT INTO casos_de_teste_do_Roteiro VALUES ('%s', %s, '%s')", codigo, codigo1, codC));
            } catch (SQLException d) {
                d.printStackTrace();
                throw new RoteiroDeTesteExeption("Erro ao salvar o os casos de teste do roteiro de teste");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RoteiroDeTesteExeption("Erro ao salvar o roteiro de teste");
        }
    }

    /**
     * lista todos os roteiros de teste do projeto com o ude informado.
     * @param idproj id pro projeto
     * @return lista de roteiros de teste.
     */
    public LinkedList<RoteiroDeTestes> listarRoteiros(String idproj) {
        String query = String.format("SELECT * FROM roteiros_teste WHERE projetoID = %s", idproj);
        ResultSet resultSet = super.execultarBusca(query);
        if (resultSet != null){
            try {
                LinkedList<RoteiroDeTestes> lista = new LinkedList<>();
                while (resultSet.next()){
                    lista.add(new RoteiroDeTestes(
                            resultSet.getString("codigo"),
                            resultSet.getString("nome"),
                            resultSet.getString("descricao"),
                            resultSet.getInt("situacao"),
                            resultSet.getString("projetoID"),
                            resultSet.getString("emailUsuario")
                    ));
                }
                return lista;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
