# ALL-O Backend

# Build Inicial

Clone o projeto para uma pasta limpa. Certifique-se de ter instalado o JDK com a versão 17.0.1 ou superior.

Após isso, utilizando o maven, instale as dependencias do projeto e rode o build.

```
mvn clean install
mvn liquibase:update
```

**Build Success.** Indica o sucesso das operações.

# Documentação

Todos os end-points estão documentados e especificados em http://localhost:8080/swagger-ui/index.html

Você pode verificar todos os end-points e seus parâmetros requeridos, assim como testar também. O Swagger por sua vez, em alguma das requisições pode ter parâmetros inválidos, por isso recomendo fortemente o uso de um software como **Postman ou Insomnia**.

# Perfis

Configurações de banco e de segurança estão configuradas nos perfis. Para rodar o projeto sem um banco de dados local (POSTGRES), utilize o perfil de H2, para usar o banco em memôria.

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
