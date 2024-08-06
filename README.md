If we specify a **named volume for mysql**, then everything works normally.

```yml
volumes:
  - unit-1-volume:/var/lib/mysql
```

But if we try to start the containers without specifying a named volume for mysql, then the mysql server in the mysql container **does not start automatically** for some reason. In that case we need to manually start it by entering these commands in the container shell:

```bash
docker container exec -it <mysql_container_name> bash

$ mysql -u root -p
Enter Password:

mysql > 
```

And then the server container can connect to the mysql server in mysql container.

> Note: Even if we publish the mysql port (3306), the server still cannot connect to mysql using "localhost:port" because the server is running in its own container and the mysql localhost:port is not accessible from server's container. Hence we have to use the container network for connecting to the mysql.