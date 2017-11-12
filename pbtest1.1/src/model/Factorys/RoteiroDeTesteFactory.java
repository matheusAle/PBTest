package model.Factorys;

import controller.exceptions.RoteiroDeTesteExeption;
import model.CasoDeTeste;
import model.RoteiroDeTestes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class RoteiroDeTesteFactory extends AbstractFactory{


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
        execultarAtualizacao(dml);
        for (String codC : casosDeTeste)
            execultarAtualizacao(String.format("INSERT INTO casos_de_teste_do_Roteiro VALUES ('%s', %s, '%s')", codigo, codigo1, codC));
        
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


    /**
     * Busca no banco de dados os codigos de casos de teste vinculados com o roteiro de teste.
     * @param codRoteiro codigo do roteiro de testes
     * @param codProj codigo do projeto do roteiro de testes
     * @return retorna uma lista com os codigos de casos de testes.
     */
    public LinkedList<String> listarCasosDeTesteDoRoteiro(String codRoteiro, String codProj){
        String query = String.format("SELECT * FROM casos_de_teste_do_roteiro r, caso_de_teste c WHERE r.cod_caso_de_teste = c.codigo AND r.projetoID = %s AND r.codigo = '%s' ", codProj, codRoteiro);
        ResultSet resultSet = super.execultarBusca(query);
        if (resultSet != null){
            try {
                LinkedList<String> lista = new LinkedList<>();
                while (resultSet.next()){
                    lista.add(resultSet.getString("cod_caso_de_teste"));
                }
                return lista;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RoteiroDeTesteExeption("Não foi possivel buscar os casos de teste do roteiro " + codRoteiro);
            }
        }
        return null;
    }

    /**
     * Atualiza os dados do roteiro de testes.
     * Os casos de teste antigos são deletados do banco de dados
     * e os novos são inseridos.
     * @param codigo codigo do roteiro de teste
     * @param projetoID id do projeto do roteiro de teste
     * @param nome novo nome do roteiro
     * @param descricaoText nova descrição do roteiro
     * @param casosDeTesteDoRoteiro novos codigos de caso de teste do roteiro
     */
    public void atualizar(String codigo, String projetoID, String nome, String descricaoText, LinkedList<String> casosDeTesteDoRoteiro) {
        String dmlUP = String.format("UPDATE roteiros_teste SET nome = '%s', descricao = '%s' WHERE projetoID = %s AND codigo = '%s'", nome, descricaoText, projetoID, codigo);
        super.execultarAtualizacao(dmlUP);
        String dmlDEL = String.format("DELETE FROM casos_de_teste_do_roteiro WHERE projetoID = %s AND codigo = '%s' ", projetoID, codigo);
        super.execultarAtualizacao(dmlDEL);
        for (String codC : casosDeTesteDoRoteiro)
            execultarAtualizacao(String.format("INSERT INTO casos_de_teste_do_Roteiro VALUES ('%s', %s, '%s')", codigo, projetoID, codC));
                
    }

    /**
     * Deleta o roteiro de testes que tem o codigo e o id do projeto especificados
     * @param codigo codigo do roteiro de testes
     * @param projetoID id do projeto do roteiro de testes
     */
    public void deletar(String codigo, String projetoID) {
        String dmlDEL = String.format("DELETE FROM roteiros_teste WHERE projetoID = %s AND codigo = '%s' ", projetoID, codigo);
        super.execultarAtualizacao(dmlDEL);
    }
}
