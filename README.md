## Maven
### Install
#### Testes Unitários e Integrados
Maven properties:

* **sonar.project.key**: Chave do projeto no sonar, podendo utilizar o `groupId:artifactId` do projeto, ou o id no sonar (entre no projeto no sonar e verifique o ID na url).

* **sonar.url**: Url do sonar

* **sonar.user**: Usuário para acesso ao sonar

* **sonar.password**: Senha do usuário para acesso ao sonar

* **slack.webhook**: URL de webhook do slack

* **slack.coverage**: Mínino de cobertura de testes exigida no sonar

Caso seu sonar esteja sem configuração de usuário e senha as propriedades`sonar.user` e `sonar.password` não são obrigatórias.

Exemplo:
```bash
mvn clean install -Dsonar.project.key=br.com.gsw:slack-pusher -Dsonar.url=http://sonarqube.gsw.com.br -Dsonar.user=sonaruser -Dsonar.password=sonarpass -Dslack.webhook=https://hooks.slack.com/services/ASHDIU98/98173JOIJ/sv9RRmWpvTes2Oc3y5QeY54G
```

#### Somente Testes Unitários
```bash
mvn clean install -DskipITs
```
