package controller;

import model.Projeto;
import model.ProjetoDOA;

import java.util.LinkedList;

public class ProjetoController {
    Projeto projetoAtivo;
    public static LinkedList<ProjetoMetaData> listaDeprojetos  = new LinkedList<ProjetoMetaData>();
    static {
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 1","Projeto de teste 1", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("2245-564", "projeto 2","Projeto de teste 2", "c:/user/pj2/src"));
        listaDeprojetos.add(new ProjetoMetaData("22awer3-44", "projeto 3","Projeto de teste 3", "c:/user/projetos/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("22qwwee3-45", "projeto 4","Projeto de teste 4", "c:/user/pj1/src/serc"));
        listaDeprojetos.add(new ProjetoMetaData("22we3-44", "projeto 56","Projeto de teste 1", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 5","Projeto de teste 12", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 6","Projeto de teste 1235", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 7","Projeto de teste 3341", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 8","Projeto de teste 156", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 9","Projeto de teste 15", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 10","Projeto de teste 1234", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 11","Projeto de teste 1", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 12","Projeto de teste 123", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 13","Projeto de teste 1223", "c:/user/pj1/src"));
        listaDeprojetos.add(new ProjetoMetaData("223-44", "projeto 14","Projeto de teste 1rewt", "c:/user/pj1/src"));
    }


    public static boolean cadastrarProjeto (
            String nome,
            String src,
            String CASO_DE_USO,
            String ROTEIRO_DE_TESTE,
            String CASO_DE_TESTE,
            String descricao
            ){
        return ProjetoDOA.persistirProjeto(
                nome,
                src,
                CASO_DE_USO,
                ROTEIRO_DE_TESTE,
                CASO_DE_TESTE,
                descricao,
                UsuarioController.getEmailUsuarioLogado()
        );
    }
}
