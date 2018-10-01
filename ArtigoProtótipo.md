**ANTLR**

_Another Tool For Language Recognition_

Murillo Gordo, Saulo Calixto, Fernando Henrique Callata, Kayo Martins, Marcos Rafael de Souza

**1. Introdução**

Para compreender melhor o  significado de ANTLR, é importante entender o conceito de _parsing_, ou em português, análise sintática. _Parsing_, normalmente, envolve reconhecer e organizar, com base numa gramática formal, informações que estão em formato de _tokens_ numa estrutura de dados, usualmente uma árvore (mais especificamente uma árvore sintática abstrata, ou árvore de análise sintática).  Para agilizar a criação da gramática formal, tem-se como ferramenta o ANTLR: um gerador de _parser_.

**2. Gramática Formal**

Uma gramática formal é composta principalmente por dois componentes, os _lexers_ (analisadores léxicos) e _parsers_ (analisadores sintáticos). Os _lexers_ identificam cada menor unidade de entrada e agrupam ela em símbolos, como por exemplo: definindo as regras léxicas para, a entrada &quot;123 + 456&quot; será interpretada pelo lexer, como cada dígito sendo um número, e os espaços como _whitespace_. O _lexer_ também definirá que todos os números juntos antes de um _whitespace_ ou caractere diferente será por si um número também. Essas definições de número e _whitespace,_ são os símbolos (_tokens_) e são usados pelo _parser_ como elementos de texto mínimos que serão identificados numa expressão. Nesse caso, a regra de _parser_ irá identificar que um token número, seguido do símbolo &quot;+&quot; e outro token número, constituem uma expressão e chama isso de &quot;adição&quot;. O ANTLR oferece suporte para a geração de _lexers_ e de _parsers_, cuja forma de criação de ambos será posteriormente explicada neste texto.
