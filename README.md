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
            <groupId>br.com.gsw.slack</groupId>
            <artifactId>sonar-notifier</artifactId>
            <version>1.0-SNAPSHOT</version>
            <configuration>
                <skipNotifier>false</skipNotifier>
                <breakNotifier>false</breakNotifier>
                <sonar>
                    <key>${project.groupId}:${project.artifactId}</key>
                    <url>http://sonarqube.gsw.com.br</url>
                    <user>sonaruser</user>
                    <password>sonarpass</password>
                    <coverage>60.0</coverage>
                </sonar>
                <scm>
                    <url>https://github.com/gswteam/slack-sonar-notifier</url>
                    <branch>origin/master</branch>
                    <user>fabiohbarbosa</user>
                    <commit>caaef090d185e90132043487bee78b877455e4a1</commit>
                </scm>
                <slack>
                    <webhook>https://hooks.slack.com/services/ASHDIU98/98173JOIJ/sv9RRmWpvTes2Oc3y5QeY54G</webhook>
                    <onlyErrors>true</onlyErrors>
                </slack>
            </configuration>
        </plugin>
    </plugins>
</build>

```
**skipNotifier**: 
Não executa o plugin
* **Property**: -DskipNotifier
* **Default**: false

**breakNotifier**: 
Quebra a execução do plugin caso seja encontrado erros no sonar
* **Property**: -DbreakNotifier
* **Default**: false

**sonar.key***:
Chave do projeto no sonar, podendo utilizar o `project.groupId:project.artifactId` do projeto, ou o id no sonar (entre no projeto no sonar e verifique o ID na url).
* **Property**: -Dsonar.key
* **Default**: ${project.groupId}:${project.artifactId}

**sonar.url**:
Url do sonar
* **Property**: -Dsonar.url ou -Dsonar.host.url

**sonar.user** :
Usuário para acesso ao sonar
* **Property**: -Dsonar.user

**sonar.password**: 
Senha do usuário para acesso ao sonar
* **Property**: -Dsonar.password

###### \<sonar.coverage\>
Mínino de cobertura de testes exigida no sonar

**Propriedade**: -Dsonar.url ou -Dsonar.host.url

**Obrigatório**: Não

***

###### scm.url 
URL do repositório (SVN/GIT)

###### scm.branch 
Branch no repositório (SVN/GIT)

###### scm.user 
Usuário no repositório (SVN/GIT)

###### scm.commit 
Commit do usuário no repositório

###### slack.webhook 
URL de webhook do slack

###### slack.onlyErrors 
Só notifica os erros no slack. Default: true

Caso seu sonar esteja sem configuração de usuário e senha as propriedades`sonar.user` e `sonar.password` não são obrigatórias.

* *Só tem utilidade quando a variável `slack.onlyErrors` estiver habilitada*

### 3. Execute o plugin
```sh
mvn sonar-notifier:sonar-notifier
```

## Mais Informações sobre o plugin
```sh
mvn help:describe -Dplugin=sonar-notifier -Ddetail
```

---

## Compilando o plugin

### Install
#### Testes Unitários e Integrados
Os testes integrados dependem de um Sonar e um Slack configurados para serem executados.

Basta passar os dados de configurações como variáveis para a execução dos testes.

Maven properties:

* **sonar.key**: Chave do projeto no sonar, podendo utilizar o `groupId:artifactId` do projeto, ou o id no sonar (entre no projeto no sonar e verifique o ID na url).

* **sonar.host.url**: Url do sonar

* **sonar.user**: Usuário para acesso ao sonar

* **sonar.password**: Senha do usuário para acesso ao sonar

* **sonar.coverage**: Mínino de cobertura de testes exigida no sonar

* **slack.webhook**: URL de webhook do slack

Caso seu sonar esteja sem configuração de usuário e senha as propriedades`sonar.user` e `sonar.password` não são obrigatórias.

Exemplo:
```sh
mvn clean install \
-Dsonar.key=br.com.gsw.slack:sonar-notifier \
-Dsonar.host.url=http://sonarqube.gsw.com.br \
-Dsonar.user=sonaruser \
-Dsonar.password=sonarpass \
-Dsonar.coverage=60.0 \
-Dslack.webhook=https://hooks.slack.com/services/ASHDIU98/98173JOIJ/sv9RRmWpvTes2Oc3y5QeY54G
```

#### Somente Testes Unitários
```sh
mvn clean install -DskipITs
```
