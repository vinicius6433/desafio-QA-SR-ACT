# Desafio de automação de testes - Act Digital

<div align="left">

<a target="_blank" rel="noopener noreferrer"><img src="https://github.com/fgalvess/fgalvess/blob/main/code.gif?raw=true" alt="Garoto programando com fundo em azul" width="700" height="400"></a>,

</div>


## Técnicas aplicadas e conceitos explicados:

1. **Hooks**
   - O objetivo de utilizar o hooks com um overenginering é para demonstrar que uma feature pode utilizar 1 ou mais urls.
   - Foi utilizada um HashMap para evitar utilização de condicionais aninhadas e perca de performance proveniente a isso.
   - Sistema de geração de imagens após cada cenário.
  
2. **Features**
   - Estrutura das features aninhadas utilizando dos 4 desafios básicos. É uma forma de explicar um pouco o conceito que falei na entrevista sobre micro serviços aninhados, que demandam utilizar urls diferentes, utilizei o exemplo na web para exemplificar.
   - A montagem dessa feature foi totalmente utilizada para seguir um padrão de keywords como uma criação de uma pseudo-framework, para demonstrar o quão configurável o selenium pode ser e o quão intuitivo ele consegue ser exemplificado, que fique de exemplo para quem entrar no repositório.
     
3. **Runner**
   - Runner padrão de todo projeto de selenium com cucumber e PO, não quis enfeitar muito pois existem diversos loggers que podem criar configurações robustas e bem aplicadas.
     
4. **Logger**   
   - O logger utilizado é o log4jx2, deixei alguns exemplos de sua utilização mas nada que realmente fosse necessário, somente para fins de apresentação, porém o verdadeiro poder dele se dá em amplicações muito extensas com diversas entradas e saídas.
     
5. **Steps e Page**   
   - A configuração segue o padrão americano com a glue focada na pasta stepsDefinitions que por sua vez faz sua chamada via instância de objetos pela page.
   - Não quis prosseguir com o padrão de Page Factory, mais por não ver uma necessidade de aplicar em um projeto de exemplo.
  
5. **Utils**   
   - Sem muita frescura ou complicações em excesso, ela faz justamente o que se propõem, um local para métodos úteis a aplicação, deixei 2 de exemplo por ser um sistema muito pequeno.
   - Tive que desabilitar os ADS via aplicação por conta de que a demora excessiva de carregamento na página me forçaria a utilizar esperas implicitas em elementos e multi validações que julgo desnecessária.
  
     
## Cenários do desafio:

1. **Cenário: Automatização do Formulário de Registro**
   - Automatizar o preenchimento do formulário ao acessar o menu de navegação "Register".

2. **Cenário: Manipulação de Frames**
   - Dentro do menu de navegação "Switch > Frames", realizar a escrita dentro de um frame através da automação.

3. **Cenário: Utilização do Datapicker**
   - No menu de navegação "Widgets > Datapicker", inserir a data de nascimento nos dois campos através da automação.

4. **Cenário: Controle do Slider**
   - No menu de navegação "Widgets > Slider", automatizar o movimento de 50% da barra.

## Pontos sobre os cenários:

1. **Cenário: Automatização do Formulário de Registro**
   - Automatizar o preenchimento do formulário ao acessar o menu de navegação "Register".
   - Foi automatizado todos os campos de forma simples e de fácil manutenção, demandando esforço mínimo.
   - Problemas na API de consulta do site forçaram a utilização de mocks via javascript para prosseguimento da aplicação, mas mesmo utilizando mocks não foi o suficiente, a api não respondeu mesmo assim.
   - Como a api não respondeu não há como validar que um cadastro foi efetuado, então não há validação.

2. **Cenário: Manipulação de Frames**
   - Ambos os preenchimentos de iframes 1 e 2 foram utilizando Javascript Executor, por ser a maneira mais simples e direta para lidar com iframes.

3. **Cenário: Utilização do Datapicker**
   - Utilização de inserção de javascript para lidar com Datapicker desabilitado.

4. **Cenário: Controle do Slider**
   - Utilização de inserção de javascript para manipular a barra.
   - 
## Recomendação de leitura rsrs
   - Tenho um artigo escrito por mim sobre quando utilizar (ou não) o selenium, mostrando prós e contras, POC's e mais :D : https://www.linkedin.com/pulse/avalia%C3%A7%C3%A3o-da-framework-selenium-vinicius-moreira/
## Tecnologias utilizadas

<div align="left">
  <a target="_blank" rel="noopener noreferrer"><img src="https://github.com/SeleniumHQ.png?size=40" alt="Selenium" width="75" height="75"></a>,
   <a target="_blank" rel="noopener noreferrer"><img src="https://avatars.githubusercontent.com/u/126123820?s=80&v=4" alt="Java" width="75" height="75"></a>,

  
</div>
