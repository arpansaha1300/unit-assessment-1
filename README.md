For some reason the mysql server in the mysql container does not start automatically.

We need to manually start it by entering these commands in the container shell:

```
docker container exec -it <mysql_container_name> bash

$ mysql -u root -p
Enter Password:

mysql > 
```

And then the server container can connect to the mysql server in mysql container.

> Note: Even if we publish the mysql port (3306), the server still cannot connect to mysql using "localhost:port" because the server is running in its own container and the mysql localhost:port is not accessible from server's container. Hence we have to use the container network for connecting to the mysql.