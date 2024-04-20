## Docker challenge

The challenge consists of creating an application consisting of an API, a database and a front-end application, all runing in containers in the same network. The containers must communicate successfully. 

The application itself can be as simples as possible since the discipline focuses on docker and containers.

It is also required to look for vulnerabilities in the application. The ideia is to be aware that applications can be born in day one with vulnerabilities, container-related or not, and to become familiar with how one can mitigate them.

The way I decided to tackle the challenge was:
- HTML page using only jQuery (via CDN) to make a GET request on an API an display the results. Bitnami's rootless nginx image was used for serving the HTML in a container.
- An API following some of the REST principles written in Java with SpringBoot. There is only one controller, that retrieves a list of books from the database and returns to the caller.
- A volatile Postgres database that is managed entirely by Flyway migrations. To keep it simple, I created a migration to load some book data into the database so the API has something to return and the frontend has something to display.

None of this code should be used in a real world scenario.