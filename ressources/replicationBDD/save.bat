pg_dump -h 127.0.0.1 -p 5432 -U cesi -Fc -f "save.bak" "projetcesi"
dropdb -h localhost -p 5432 -U cesi "projetcesi2"
createdb -h localhost -p 5432 -U cesi "projetcesi2"
pg_restore -h localhost -p 5432 -U cesi  -d "projetcesi2" -v "save.bak"
pause