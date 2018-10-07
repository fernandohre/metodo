**ANTLR**

_Another Tool For Language Recognition_

Fernando Henrique Callata, Kayo Martins, Murillo Gordo, Marcos Rafael de Souza, Saulo Calixto

**1. Introdução**

O presente artigo tem o objetivo de introduzir ao leitor uma ferramenta de parsing que tem sido usada com sucesso para ler, processar e executar textos ou arquivos binários, ANTLR. Muitos softwares hoje utilizam-na para auxiliar em suas soluções, como o Twitter em seu motor de busca, o Hibernate, Netbeans, etc.
Para compreender melhor o  significado de ANTLR, é importante entender o conceito de _parsing_, ou em português, análise sintática. _Parsing_, normalmente, envolve reconhecer e organizar, com base numa gramática formal, informações que estão em formato de _tokens_ numa estrutura de dados, usualmente uma árvore (mais especificamente uma árvore sintática abstrata, ou árvore de análise sintática).  Para agilizar a criação da gramática formal, tem-se como ferramenta o ANTLR: um gerador de _parser_.
Com o fim didático de apresentar a ferramenta como algo que pode ser utilizada para solucionar diversos problemas, o presente artigo irá apresentar de forma prática como essa ferramenta pode ser usada, além claro de explanar alguns conceitos importantes para o entendimento da ferramenta como um todo.

**2. Gramática Formal**

Para começar a utilizar o ANTLR a primeira coisa que precisamos fazer é definir uma gramática formal, composta principalmente por dois componentes, os _lexers_ (analisadores léxicos) e _parsers_ (analisadores sintáticos).
Os _lexers_ identificam cada menor unidade de entrada e agrupam ela em símbolos, como por exemplo: definindo as regras léxicas para, a entrada "437 + 734" será interpretada pelo lexer, como cada dígito sendo um número, e os espaços como _whitespace_. O _lexer_ também definirá que todos os números juntos antes de um _whitespace_ ou caractere diferente será por si um número também. Essas definições de número e _whitespace,_ são os símbolos (_tokens_) e são usados pelo _parser_ como elementos de texto mínimos que serão identificados numa expressão. Nesse caso, a regra de _parser_ irá identificar que um token número, seguido do símbolo "+" e outro token número, constituem uma expressão e chama isso de "adição". O ANTLR oferece suporte para a geração de _lexers_ e de _parsers_, cuja forma de criação de ambos será posteriormente explicada neste artigo.

**Colar aqui a imagem do lexer**

Como o ANTLR sabe quais são os tokens, como ele consegue distinguir que isso é uma operação matemática? Simples, nós o "ensinamos" ao criar a gramática formal.

```
/*
* Regras de parsing
*/

operacao : NUMERO '+' NUMERO ;

/*
* Regras lexicais
*/

NUMERO : [0-9]+ ;

ESPACOEMBRANCO : ' ' -> skip ;
```
Esse exemplo acima é uma prévia da definição de uma gramática formal utilizando o ANTLR. Como podem ver definimos regras lexicais e de parsing.
Nas regras lexicais ficou definido dois tokens, _NUMERO_ e _ESPACOEMBRANCO_, o primeiro é composto por um ou mais dígito de 0 à 9. O segundo diz ao analisador que o espaço em branco deve ser ignorado.
Nas regras de parsing nós utilizamos os tokens para definir o que fazer com eles. Com isso ficou decidido que uma _operacao_ é composta do token NUMERO e o sinal '+', de forma que sempre que tivermos esses tokens na ordem descrita na regra o ANTLR irá considerar isso uma operação.
A gramática formal é necessária para que o ANTRL possa analisar o código passado, definindo ela podemos usar essa ferramenta para resolver diversos problemas, como um gerador de querys sql, análise de textos de buscas, etc.
