echo "Iniciando deploy do projeto..."

SERVIDOR=$1
USUARIO="algaworks"
TOMCAT="/opt/tomcat"
PACOTE="videoaula-jsf-hospedagem-*.war"
WEBAPP="videoaula-jsf-hospedagem.war"

# 1. Rodar build e gerar o pacote
mvn clean package

# 2. Fazer o upload para o servidor
scp target/$PACOTE $USUARIO@$SERVIDOR:/tmp/

# 3. Parar o Tomcat
ssh $USUARIO@$SERVIDOR $TOMCAT/bin/shutdown.sh

# 4. Remover o pacote da versao anterior
ssh $USUARIO@$SERVIDOR "rm $TOMCAT/webapps/$WEBAPP"

# 5. Mover o novo pacote para o Tomcat
ssh $USUARIO@$SERVIDOR "cp /tmp/$PACOTE $TOMCAT/webapps/$WEBAPP"

# 6. Iniciar o Tomcat
ssh $USUARIO@$SERVIDOR $TOMCAT/bin/startup.sh

echo "... fim."
