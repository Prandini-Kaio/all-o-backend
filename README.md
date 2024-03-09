# ALL-O Backend

# Build Inicial

Clone o projeto para uma pasta limpa. Certifique-se de ter instalado o JDK com a versão 17.0.1 ou superior.

Após isso, abra o projeto em sua IDE de preferência e rode os seguintes comandos:

```
mvn clean install
mvn liquibase:update
```

Ambos os comandos devem finalizar com **Build Success.**

# End-points

Todos os end-points estão documentados e especificados em http://localhost:8080/swagger-ui/index.html

Você pode verificar todos os end-points e seus parâmetros requeridos, assim como testar também. Recomendo para o teste 
dos end-points sem o frontend, a instalação de um software como **Postman** e **Insomnia**.

# Perfis - EM DESENVOLVIMENTO

Alguns perfis do spring podem ser alterados a depender de como quer que sua aplicação suba. Temos um peril para um banco H2,
 postgres e outros, por isso é separado esses perfis. Os perfis podem ser alterados no application.properties principal do sistema, em spring.profiles.active.

# Tecnologias Usadas

Algumas das tecnologias usadas para o projeto foram:

<div align="left" style="display: inline_block">
 
<img align="center" alt="allo" height="90" width="90" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/liquibase/liquibase-original-wordmark.svg" />
<img align="center" alt="allo" height="80" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg" />
<img align="center" alt="allo" height="80" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" />
<img align="center" alt="allo" height="80" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-original-wordmark.svg" />
<img align="center" alt="allo" height="60" width="60" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg" />
<img align="center" alt="allo" height="60" width="60" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/maven/maven-original.svg" />


</div>
