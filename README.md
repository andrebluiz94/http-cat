# http-cat
  Está é uma aplicação SpringBoot que utiliza Java 11 e diversar tecnologias, como redis
postgresSQL, Docker-composer dentre outras.
#### Tecnologias utilizadas:  
 - Redis com uso de cache
 - Container-docker
 - Docker-composer
 - PostgresSQL
 - Jacoco
 - PItest
 - PMD
 - HikariCP(Para o gerenciamento de Threads)
 - Junit5
 - H2 para testes
## Cobertura e qualidade de testes
Para entendermos se um aplicação funciona, podemos tentar sempre testa-lá "mão", validando todas às suas'       
funcionalidade. Porém quando começamos a pensar em escalabilidade isso começa a se tornar caro e inviavel,      
devido a isso utilizamos testes automatizados para fazer com que o processo de não só testarmos um aplicação        
como também validar se nossas funcionalidade adicionais interferiram com diretamente em outro ponto do app.

###Jacoco
Para entendermos o quando da aplicação está coberta por testes podemos utilizar o Jacoco. Em nosso contexto a       
aplicação está com uma cobertura de 97% com base em suas linhas de codigo.

###PITest
Quando começamos a tentar entender sobre a qualidade de testes podemos utilizar o Pitest, que é um plugin para      
testes de mutação, ele fará diversas alterações no código base com intenção de quebrar o teste. Quando o teste      
quebra ele mata o mutante gerado validando que o teste que fizemos para situação foi efetivo.

Embora tenhamos 97% de coverage com Jacoco temos uma cobertura de 74% com o uso do PItest, o que indico que tem         
situação que não foram cobertas pelos testes na hora do desenvolvimento. 

## Modo de uso
A aplicação utiliza multiplos perfil como desenvolvimento, teste e produção.        
Para escolher o perfil corretamente acesse a pasta "env" na raiz do projeto,        abra  o arquivo "app.env" e altere a linha referente.

 - [x] Docker (opcional)
 - [x] Docker-compose (opcional)
 - [x] Maven (opcional)
 ###Variaveis de amabiente para definir perfis
```
#perfil desenvolvimento
SPRING_PROFILES_ACTIVE=dev

#perfil teste
SPRING_PROFILES_ACTIVE=test

#perfil produção
SPRING_PROFILES_ACTIVE=prod
```
### Perfil de testes 

será necessario utilizar a variável referente à teste.      
Esté perfil quando ativado roda o projecto com um banco de dados H2
```
#Primeiro passo é compilar o projeto com uso do mvn
./mvn compile
#Para rodar o projecto
./mvn -e spring.profiles.active=test spring-boot:run
```      
### Perfil de Desenvolvimento
Esté perfil quando ativado roda o projecto chamando o banco de dados,       postgres na porta 5432 e o redis em sua porta padrão
para o uso dessa imagem é necessario que você tenha um banco de dados postregreSQL e uma outra base de dados redis.     
Ambas em suas portas padrões.
```
#Primeiro passo é compilar o projeto com uso do mvn
./mvn compile
#Para rodar o projecto
./mvn -e spring.profiles.active=dev spring-boot:run
```      
### Perfil de produção
Este perfil tem como finalidade rodar dentro de um container Docker acessando outras container como         
banco de dados, redis. Ele usa de interpolações para incorporar a variveis que seram descritas tanto no dockerfile      
quanto no docker composer.
```
#Para utilizar esse perfil use o seguinte comando
#Para verificar imagem e depencias
docker-compose build

#Para subir o container e suas imagens
docker-compose up
```
Este comando utilizado ``docker-compose up``` vai subir um container redis, outro com postgres e por        
útilmo vai a aplicação por ela depender dessas duas imagens. 
  