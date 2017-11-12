# PBTest
O sistema tem como objetive ser uma ferramenta que auxilie na **documentação** e **visualização** e **execução** de testes de software 
utilizando o framework de teste de unidade Junit. 

Para atingir esse objetivo, o sistema permitir que o usuário especifique dentro do  software um projeto. 
E a cada projeto deve ser possível especificar casos de uso. 

Com um projeto e com pelo menos um caso de uso cadastrodo. O usuário poderá vincular a cada artefato de seu projeto um ou mais casos de teste.
Com casos de testes especificados, o usuário deve criar sequências de testes. Estas sequências de testes devem ser compostos de pelo menos um caso de teste. 

Cada roteiro de teste deve ser executado pelo sistema quando solicitado pelo usuário. Esta execução deve gerar uma matriz de rastreabilidade. que permita ao usuário visualizar os resultados da execução dos testes.

## Documentação
 - Manual do sistema: [Documento no google docs](https://docs.google.com/document/d/1AjWuZbuOO-Qt2sVYGYHp02sNtGhvaWNTshqkfg6jZWc/edit?usp=sharing)
e [pdf]();
 - Apresentação do sistema: [slides no google apresentações](https://docs.google.com/presentation/d/1Cx5sLlpQOnTa0ejFvDROhzNnOGxgAtU_4IrUvI0Ef3E/edit?usp=sharing)
 e [pdf]();
 - Diagramas: [projeto no astah](https://github.com/matheusAle/PBTest/blob/master/recursos/diagramas/PBTestV1.11.15.asta), 
 [diagrama de classes](https://github.com/matheusAle/PBTest/blob/master/recursos/diagramas/diagrama%20de%20classes.png);
 - banco de dados: [dump mysql](https://github.com/matheusAle/PBTest/blob/master/recursos/data_base_dump.sql)
 
 ## Trabalhos futuros
 - **Relatorios:**
Implementar a extração da documentação de um projeto para o formato pdf ou odf.

- **Estatísticas:**
Painel que permita ao usuário visualizar estatísticas sobre  um projeto. Porcentagem de falhas e acertos de e casos de teste, erros em um caso de uso, etc.

- **Leitura de arquivos de propriedades**
Ler o documento de propriedades de um projeto gerado pela IDE.

- **Compilar classes:** 
Fazer com que o sistema compile as classes. Sem bagunçar os arquivos do projeto.

- **Relacionamento entre artefato e documentação**
Mostrar em um grafo ou diagrama a relação entre os casos de uso, artefatos casos de teste e sequências.

- **Execução modular:**
Criar Módulos de execução de teste. Para que a execução de teste seja independente do sistema. Para permitir a execução de testes em projetos implementados em outras linguagens.
