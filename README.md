# slack-sonar-notifier #

Plugin do Maven para notificar possíveis problemas relatados no Sonarqube em um canal do Slack (https://slack.com/)

## Usando o plugin
### 1. Crie um _webhook_ no slack
Crie um *Incoming WebHooks* no seu canal do Slack

Acesse a URL: _https://(seu-canal).slack.com/apps/manage/custom-integrations_

### 2. Adicione o plugin do pom.xml

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.wordpress.fabiohbarbosa</groupId>
            <artifactId>slack-sonar-notifier</artifactId>
            <version>1.0.0</version>
            <configuration>
                <skipNotifier>false</skipNotifier>
                <breakNotifier>false</breakNotifier>
                <levelNotifier>WARNING</levelNotifier>
                <sonar>
                    <key>${project.groupId}:${project.artifactId}</key>
                    <url>http://yoursonarqube.com.br</url>
                    <login>sonaruser</login>
                    <password>sonarpass</password>
                </sonar>
                <scm>
                    <url>https://github.com/fabiohbarbosa/slack-sonar-notifier</url>
                    <branch>origin/master</branch>
                    <user>fabiohbarbosa</user>
                    <commit>caaef090d185e90132043487bee78b877455e4a1</commit>
                </scm>
                <slack>
                    <webhook>https://hooks.slack.com/services/ASHDIU98/98173JOIJ/sv9RRmWpvTes2Oc3y5QeY54G</webhook>
                </slack>
            </configuration>
        </plugin>
    </plugins>
</build>

```

***

**skipNotifier**: 
Não executa o plugin
* _Type_: Boolean
* _Property_: -DskipNotifier
* _Default_: false

***

**breakNotifier**: 
Caso seja encontrado erros no sonar, quebra o build
* _Type_: Boolean
* _Property_: -DbreakNotifier
* _Default_: false

***

**levelNotifier**: 
Level a serem analisados no quality gates
* _Type_: String
* _Values_: **info** (success, warning e errors), **warning** (warnings e errors), **error** (errors)
* _Property_: -DlevelNotifier
* _Default_: WARNING

***

**sonar.key***:
Chave do projeto no sonar, podendo utilizar o `project.groupId:project.artifactId`, ou o id no sonar (entre no projeto no sonar e verifique o ID na url).
* _Type_: String
* _Property_: -Dsonar.key
* _Default_: ${project.groupId}:${project.artifactId}

***

**sonar.url***:
URL do sonar
* _Type_: String
* _Property_: -Dsonar.url ou -Dsonar.host.url

***

**sonar.login**:
Login para acesso ao sonar
* _Type_: String
* _Property_: -Dsonar.login

***

**sonar.password**: 
Senha do usuário para acesso ao sonar
* _Type_: String
* _Property_: -Dsonar.password

***

**scm.url**: 
URL do repositório (SVN/GIT)
* _Type_: String
* _Property_: -Dscm.url

***

**scm.branch**: 
Branch no repositório (SVN/GIT)
* _Type_: String
* _Property_: -Dscm.branch

***

**scm.user**:
Usuário no repositório (SVN/GIT)
* _Type_: String
* _Property_: -Dscm.user

***

**scm.commit**: 
Commit do usuário no repositório
* _Type_: String
* _Property_: -Dscm.commit

***

**slack.webhook***: 
URL de webhook do slack
* _Type_: String
* _Property_: -Dslack.webhook

***

> \* Obrigatórios

### 3. Execute o plugin
```sh
mvn slack-sonar-notifier:notifier
```

## Mais Informações sobre o plugin
```sh
mvn help:describe -Dplugin=slack-sonar-notifier -Ddetail
```

---

## Compilando o plugin

### Install
#### Testes Unitários e Integrados
Os testes integrados dependem de um Sonar e um Slack configurados para serem executados.

Basta passar os dados de configurações como variáveis para a execução dos testes.

Exemplo:
```sh
mvn clean install \
-Dsonar.key=com.wordpress.fabiohbarbosa \
-Dsonar.host.url=http://yoursonarqube.com.br \
-Dsonar.login=sonar-login \
-Dsonar.password=sonar-password \
-Dslack.webhook=https://hooks.slack.com/services/ASHDIU98/98173JOIJ/sv9RRmWpvTes2Oc3y5QeY54G
```

#### Somente Testes Unitários
```sh
mvn clean install -DskipITs
```
