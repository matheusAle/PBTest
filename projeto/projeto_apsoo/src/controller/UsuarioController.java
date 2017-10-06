package controller;

import model.Usuario;

import java.util.LinkedList;

public class UsuarioController {
    public static LinkedList<Usuario> listaUsuario = new LinkedList<Usuario>();

    static {
        listaUsuario.add(new Usuario("Matheus Ale", "m@a", Usuario.Cargo.GERENTE_DE_PROJETO));
        listaUsuario.add(new Usuario("Marcos", "m@a", Usuario.Cargo.TESTADOR));
        listaUsuario.add(new Usuario("Amanda ", "m@a", Usuario.Cargo.GERENTE_DE_TESTES));
        listaUsuario.add(new Usuario("Thiago Souza", "m@a", Usuario.Cargo.TESTADOR));
        listaUsuario.add(new Usuario("Hercules silva", "m@a", Usuario.Cargo.TESTADOR));
        listaUsuario.add(new Usuario("Eduardo silva", "m@a", Usuario.Cargo.TESTADOR));
        listaUsuario.add(new Usuario("Ulisses carlos", "m@a", Usuario.Cargo.TESTADOR));
        listaUsuario.add(new Usuario("Silvio santos", "m@a", Usuario.Cargo.TESTADOR));
    }
}
