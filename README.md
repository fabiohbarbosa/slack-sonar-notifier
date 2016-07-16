# slack-sonar-notifier #

Plugin do Maven para notificar possíveis problemas relatados no Sonarqube em um canal do Slack (https://slack.com/)

## Usando o plugin
Adicione o plugin do pom.xml

```xml
<build>
    <plugins>
        <plugin>
            <groupId>br.com.gsw</groupId>
            <artifactId>slack-pusher</artifactId>
            <version>1.0-SNAPSHOT</version>
            <configuration>
                <sonar>
                    <key>${groupId}:${artifactId}</key>
                    <url>http://sonarqube.gsw.com.br</url>
                    <user>sonaruser</user>
                    <password>sonarpass</password>
                </sonar>
                <slack>
                    <webhook>https://hooks.slack.com/services/ASHDIU98/98173JOIJ/sv9RRmWpvTes2Oc3y5QeY54G</webhook>
                    <coverage>60</coverage>
                </slack>
            </configuration>
        </plugin>
    </plugins>
</build>
```

```bash
mvn sonar-notifier:sonar-notifier
```

## Compilando o plugin

### Install
#### Testes Unitários e Integrados
Os testes integrados dependem de um Sonar e um Slack configurados para serem executados.

Basta passar os dados de configurações como variáveis para a execução dos testes.

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
mvn clean install \
-Dsonar.project.key=br.com.gsw.slack:sonar-notifier-client \
-Dsonar.url=http://sonarqube.gsw.com.br \
-Dsonar.user=sonaruser \
-Dsonar.password=sonarpass \
-Dslack.webhook=https://hooks.slack.com/services/ASHDIU98/98173JOIJ/sv9RRmWpvTes2Oc3y5QeY54G
-Dslack.coverage=60
```

#### Somente Testes Unitários
```bash
mvn clean install -DskipITs
```
