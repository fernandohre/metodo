# ANTLR - _Another Tool For Language Recognition_

### Discentes
> Fernando Henrique Callata, 
> Kayo Martins, 
> Murillo Gordo, 
> Marcos Rafael Lapa de Sousa, 
> Saulo Calixto.

## 1. Introdução

O presente artigo tem o objetivo de introduzir ao leitor uma ferramenta de parsing que tem sido usada com sucesso para ler, processar e executar textos ou arquivos binários, ANTLR. Muitos softwares hoje utilizam-na para auxiliar em suas soluções, como o Twitter em seu motor de busca, o Hibernate, Netbeans, etc.

Para compreender melhor o significado de ANTLR, é importante entender o conceito de _parsing_, ou em português, análise sintática. _Parsing_, normalmente, envolve reconhecer e organizar, com base numa gramática formal, informações que estão em formato de _tokens_ numa estrutura de dados, usualmente uma árvore (mais especificamente uma árvore sintática abstrata, ou árvore de análise sintática). Para agilizar a criação da gramática formal, tem-se como ferramenta o ANTLR: um gerador de _parser_.

Com o fim didático de apresentar a ferramenta como algo que pode ser utilizada para solucionar diversos problemas, o presente artigo irá apresentar de forma prática como essa ferramenta pode ser usada, além claro de explanar alguns conceitos importantes para o entendimento da ferramenta como um todo.

## 2. Gramática Formal

Para começar a utilizar o ANTLR a primeira coisa que precisamos fazer é definir uma gramática formal, composta principalmente por dois componentes, os _lexers_ (analisadores léxicos) e _parsers_ (analisadores sintáticos).

Os _lexers_ identificam cada menor unidade de entrada e agrupam ela em símbolos, como por exemplo: definindo as regras léxicas para, a entrada "46 * 854" será interpretada pelo lexer, como cada número sendo representado com o simbolo _"número"_, e os espaços como _"whitespace"_. O _lexer_ também definirá que todos os números juntos antes de um _whitespace_ ou caractere diferente será por si um número também. Essas definições de número e _whitespace,_ são os símbolos (_tokens_) e são usados pelo _parser_ como elementos de texto mínimos que serão identificados numa expressão. Nesse caso, a regra de _parser_ irá identificar que um token número, seguido do símbolo "*" e outro token número, constituem uma expressão e chama isso de "multiplicação". O ANTLR oferece suporte para a geração de _lexers_ e de _parsers_, cuja forma de criação de ambos será posteriormente explicada neste artigo.

![imagem_lexer](./imagens/lexer-parser-center-1030x187.png)

Como o ANTLR sabe quais são os tokens, como ele consegue distinguir que isso é uma operação matemática? Simples, nós o "ensinamos" ao criar a gramática formal.
```
/*
* Regras de parsing
*/
operacao : NUMERO '+' NUMERO;

/*
* Regras lexicais
*/
NUMERO : [0-9]+;
ESPACOEMBRANCO : ' ' -> skip;
```
Esse exemplo acima é uma prévia da definição de uma gramática formal utilizando o ANTLR. Como podem ver definimos regras lexicais e de parsing.

Nas regras lexicais ficou definido dois tokens, _NUMERO_ e _ESPACOEMBRANCO_, o primeiro é composto por um ou mais dígito de 0 à 9. O segundo diz ao analisador que o espaço em branco deve ser ignorado.

Nas regras de parsing nós utilizamos os tokens para definir o que fazer com eles. Com isso ficou decidido que uma _operacao_ é composta do token NUMERO e o sinal '+', de forma que sempre que tivermos esses tokens na ordem descrita na regra o ANTLR irá considerar isso uma operação.

A gramática formal é necessária para que o ANTRL possa analisar o código passado, definindo ela podemos usar essa ferramenta para resolver diversos problemas, como um gerador de querys sql, análise de textos de buscas, etc.

### 2.1. Tipos de gramática

Existem duas grandes estratégias que pode-se adotar ao criar uma gramática, a de cima para baixo consiste em começar com a organização geral de como um arquivo é organizado, quais suas seções, qual a ordem, o que tem em cada seção, e assim ir especificando para regras mais baixo nível até a menor parte. Esta estratégia é boa quando quem está definindo a gramática tem um bom conhecimento teórico para conhecer toda a organização do arquivo em que está trabalhando.

Alternativamente, e mais comumente, podemos atacar o problema de baixo para cima, inicialmente definindo os tokens, como eles se agrupam e as expressões básicas aplicadas a eles, e após isso definimos estruturas de mais alto nível e organização do arquivo. Esta estratégia permite começar com parte menores, seguir um raciocínio mais simples subindo de nível aos poucos ao invés de saber toda a organização do arquivo desde o início.

## 3. Como o ANTLR funciona?
O Antlr é composto de duas partes, a ferramenta que tem o papel de criar o analisador léxico e o analisador sintático. Além disso tem o runtime responsável por aplicar a gramática.

Uma vez que a gramática esteja criada você poderá usá-la nas mais diferentes soluções: Java, Python, C#, Javascript, etc. Ou seja, o Antlr te dá a possibilidade de trabalhar em diversas linguagens, o que diferencia é apenas o runtime que é especifico para cada linguagem.

A despeito das diversas variedades de linguagens em que o Antlr pode ser aplicado, nesse artigo abordaremos sua aplicação apenas em Java.
Todos os exemplos da instalação serão dados para o ambiente Linux.

### 3.1. Configurando o Antlr
O Antlr pode ser baixado do [site oficial](http://www.antlr.org/download/antlr-4.7.1-complete.jar).
Ao baixar esse arquivo você deve copiá-lo para `/usr/local/lib` (Linux) ou `C:\Program Files\Java\libs` (Windows).
Para facilitar o uso você pode criar CLASSPATH para poder fazer a chamada direta do Antlr no terminal. Para adicionar o CLASSPATH, siga os passos abaixo:

```
~$ cd ~
~$ nano .bashrc
~$ sudo cp antlr-4.7.1-complete.jar /usr/local/lib/
//// Adicione essa linha ao final do bashrc.
export CLASSPATH=$CLASSPATH:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH
alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASS$
alias grun='java org.antlr.v4.gui.TestRig'
```
Agora o Antlr já está instalado e devidamente configurado, pronto para ser usado. Com isso já podemos criar a gramática com os analisadores léxicos e analisadores sintáticos, os quais são guardados em um arquivo de extensão *.g4*.

### 3.2. Antlr e Java
Agora vamos ver como podemos usar a gramática gerada pelo Antlr no Java. Para isso usaremos o *Maven* que é uma ferramenta de automação e gerenciamento de projetos Java. 
Para usar o Antlr com o Maven basta configurar o pom, incluindo as dependências para o runtime do antlr.

Para adicionar as dependências do Antlr ao projeto Maven basta adicionar a seguinte dependência no pom:
```
<dependencies>
	<dependency>
		<groupId>org.antlr</groupId>
		<artifactId>antlr4-runtime</artifactId>
		<version>4.7</version>
	</dependency>
</dependencies>
```
Você também deve colocar o plugin para que a gramática *.g4* seja compilada antes dos arquivos Java, com a seguinte sintaxe no pom.

```
<plugin>
	<groupId>org.antlr</groupId>
	<artifactId>antlr4-maven-plugin</artifactId>
	<version>4.7</version>
	<executions>
		<execution>
			<goals>
				<goal>antlr4</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```
Além disso você pode definir que o Java gere automaticamente os listeners e os visitors para a gramática, adicionando a seguinte configuração ao Pom.

```
<properties>
	<project.build.sourceEncoding>UTF-</project.build.sourceEncoding>
	<antlr4.visitor>true</antlr4.visitor>
	<antlr4.listener>true</antlr4.listener>
</properties>
```
Depois de configurado o Pom, basta colocar as gramáticas geradas dentro da pasta: `src/main/antlr4/package` e rodar o comando:

```
$~ mvn package
```
Esse comando gerará o lexer e o parser  e eles serão compilados com o resto do seu código.

## 4. Usando o ANTLR
Após ter tudo configurado podemos finalmente criar nossa primeira gramática.
As gramáticas no Antrl são guardadas em arquivos com extensão *.g4* e possuem um cabeçalho que indica o nome da gramática. Então a primeira coisa que precisamos fazer é criar o arquivo da gramática:
```
$~ touch calculadora.g4
$~ nano calculadora.g4
```
A primeira coisa que precisamos definir é o nome da gramática e o fazemos com a palavra reservada *grammar* seguida do nome da gramática, **que deve ser o mesmo nome do arquivo**.
Uma informação importante é que ao final de cada declaração deve-se colocar o "**;**" para indicar que foi finalizada a declaração.
Pode-se começar definindo primeiramente os analisadores sintáticos e depois os léxicos.
Vamos criar aqui uma gramática simples de calculadora. Ela apenas receberá uma expressão matemática que contém apenas os operadores **+ - * /**. Ela será responsável por avaliar a expressão passada.

```
grammar calculadora;  
  
/*  
*Analisador sintático  
**/  
  
prog: expr;  
expr: expr op=('*'|'/') expr  # OpBin  
    | expr op=('+'|'-') expr  # OpBin  
    | '(' expr ')'            # par  
    | INT                     # num  
    ;  
  
/*  
*Analisador léxico  
**/  
  
INT : ('0'..'9')+ ;
```
Essa é a nossa gramática. Primeiramente foi definido apenas o token INT o qual pode ser formado de dígitos que vão de 0 à 9, podendo ter mais de um dígito. Além disso os operadores e os parênteses também são tokens que não foram definidos formalmente, mas são usados no analisador sintático.
No analisador sintático temos um *prog* que é formado por uma *expr*, essa por sua vez pode ser formada por outras *expr*, números, operadores ou parênteses.
É bom notar que a regra *expr* é formado por 4 linhas, cada linha representa uma possibilidade para a regra e o pipe **|** indica que qualquer uma dessas expressões são válidas.  Ao final de cada opção é dado um nome para ela indicado pelo caractere **#**.

### 4.1. Usando a gramática em um projeto Java

Agora que a gramática está pronta o próprio ANTLR se encarregará de gerar os arquivos necessários para podermos usá-la em nosso projeto Java.
A primeira coisa a fazer é *"compilar"* a gramática com o antlr4, para isso basta rodar o comando:
```
$~ antlr4 Calculadora.g4
```
Após esse comando rodamos o compilador java para poder gerar os arquivos necessários para usarmos a gramática:
```
$~ javac Calculadora*.java
```
Após esse comando ser rodado vários arquivos *.java* são gerados, entre eles os parsers, os lexers, os listeners, etc. Esses arquivos java precisam ser adicionados aos projetos java criado para que eles possam ser usados.
Um dos arquivos mais importantes gerados é o *CalculadoraListener*, nele teremos vários métodos de entradas e saída que vão representar cada regra sintática criada. Podemos sobreescrever esses métodos afim de podermos usar os dados gerados pelo analisador em nosso software.
Os arquivos de Lexers e Parsers representam as regras que nós criamos na gramática, não precisamos mexer neles.
Nesse ponto já temos todo o insumo necessário para podermos usar nossa gramática. Caso queiramos obter os resultados do parser basta implementarmos o Listener e sobreescrever os métodos das regras, fazendo o tratamento que acharmos pertinente.
Não entraremos em detalhes da implementação, pois isso vai depender do uso que você deseja dar para sua gramática, contudo, caso queira entender melhor como foi feito, basta acessar nosso projeto no [github](./Projeto/calculadoraantlr).

## 5. Conclusão
O Antlr é de fato uma ferramenta muito útil, pois ela facilita a vida do desenvolvedor ajudando-o a criar sua própria linguagem. Fazer todo esse processo de receber uma entrada e formatar ela para uma linguagem conhecida demandaria muito esforço e um bom conhecimento de *expressões regulares*.
Por mais que dê para fazer isso sem o Antlr, o esforço para tal seria hérculeo e pouco proveitoso.
A utilidade do Antlr vem sendo provada ao longo dos anos com a quantidade de soluções que lançam mão dessa poderosa ferramenta em seus projetos.
Esperamos que com esse artigo o leitor possa ter tido um vislumbre da ferramenta, de como dar os primeiros passos e como ela pode ser útil no dia a dia.

## 6. Referências
  1. [ANTLR](http://www.antlr.org/)
  2. [The ANTLR Mega Tutorial](https://tomassetti.me/antlr-mega-tutorial/)
  3. [ANTLR Repository](https://github.com/antlr/antlr4)
  4. [Sung Kyun Kwan University ANTLR Tutorial](http://arcs.skku.edu/pmwiki/uploads/Courses/ProgrammingLanguages/ANTLR-tutorial.pdf)
  5. [MinhaCalculadora](https://gist.github.com/lrlucena/b150cba803ddab1005d3)
