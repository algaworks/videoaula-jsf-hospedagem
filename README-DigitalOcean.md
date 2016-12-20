A aula pode ser acessada em:

[http://blog.algaworks.com/deploy-jsf-digitalocean](http://blog.algaworks.com/deploy-jsf-digitalocean)

Vou deixar aqui as ações que foram executadas na aula e os comandos também.

### Atualizar o sistema

```shell
$ sudo apt-get update
```

### Criar usuário e adicionar ele ao grupo sudo

```shell
$ adduser algaworks

$ usermod -aG sudo algaworks
```

### Autenticação sem utilizar a senha

Você precisa copiar sua chave pública (na sua máquina). Eu utilizei o comando abaixo no terminal da minha máquina (equivalente a apertar CTRL+C):

```shell
$ cat ~/.ssh/id_rsa.pub | xclip -selection clipboard
```

Voltando ao servidor, na pasta home do usuário que vai autenticar sem a senha - no caso, foi na home do usuário algaworks -, eu criei a pasta ".ssh" e o arquivo "authorized_keys":

```shell
$ mkdir ~/.ssh;

$ chmod 700 ~/.ssh

$ vim ~/.ssh/authorized_keys;

$ chmod 600 ~/.ssh/authorized_keys
```

### Instalando o MySQL

```shell
$ sudo apt-get install mysql-server

$ sudo mysql_secure_installation

$ mysql -uroot -p

> create database hospedagemjsf;

> create user algaworks idenfied by '12345678';

> grant all on hospedagemjsf.* to algaworks;
```

### Instalando o Java

```shell
$ sudo apt-get install -y default-jdk
```

### Instalando o Unzip

```shell
$ sudo apt-get install -y unzip
```

### Instalando e configurando o Tomcat

```shell
$ cd /tmp

$ wget http://ftp.unicamp.br/pub/apache/tomcat/tomcat-8/v8.5.9/bin/apache-tomcat-8.5.9.zip

$ unzip /tmp/apache-tomcat-8.5.9.zip

$ sudo mv /tmp/apache-tomcat-8.5.9/ /opt/tomcat

$ vim /opt/tomcat/bin/setenv.sh # o conteúdo desse arquivo é: JAVA_OPTS='-Djava.security.egd=file:/dev/urandom'

$ chmod +x /opt/tomcat/bin/*.sh

$ /opt/tomcat/bin/startup.sh # use o shutdown.sh para parar
```

### Firewall

```shell
$ sudo ufw allow ssh # ou: sudo ufw allow 22/tcp

$ sudo ufw allow 8080

$ sudo ufw enable
```

### Publicar o projeto

```shell
./deploy-digitalocean.sh passar-o-ip-aqui
```
