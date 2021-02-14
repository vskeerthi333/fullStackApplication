curl -fsSL https://get.docker.com -o get-docker.sh

chmod +x ./get-docker.sh

./get-docker.sh


docker run --name mysqldb -p 3306:3306 -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_DATABASE=xmemes -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:latest

apt install maven -y

apt install default-jdk -y

cd backend

mvn clean install

cp target/xmeme-0.0.1-SNAPSHOT.jar xmeme.jar

cd ../