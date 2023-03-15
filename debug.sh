curl http://localhost:8080/api/v1/books?title=A%20revolu%C3%A7%C3%A3o%20dos%20bichos

curl --request POST 'http://localhost:8080/api/v1/users' \
--header 'Content-Type: application/json' \
--data '{
    "username": "random",
    "name": "nome",
    "password": "senha",
    "coins": 0,
    "level_xp": 0,
    "account_type": "Free"
}'

curl --request POST 'http://localhost:8080/api/v1/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "random",
    "password": "senha"
}'

curl --location --request PATCH 'http://localhost:8080/api/v1/users/salve/books/8175257660' \
--header 'Content-Type: application/json' \

--data '{
    "reading_chapter": 10,
    "reading_percentage": 10.0,
    "quiz_answered": false
}'


