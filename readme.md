Puedes ver el historial del desarrollo de esta aplicacion en el siguiente repositorio:

http://github.com/trebor006/bankapi




docker run -d --name bank-postgresdb -e POSTGRES_PASSWORD=password -p "5432:5432" -e PGDATA=/var/lib/postgresql/data/pgdata -v /custom/mount:/var/lib/postgresql/data postgres



- Compilar Para Test
mvn clean install -Ptest


- Compilar Para Prod
  mvn clean install -Pprod


