pg_dump -h 192.168.1.45 -p 5432 -U cesi -Fc -f "save.bak" "projetcesi"
dropdb -h localhost -p 5432 -U cesi "projetcesi"
createdb -h localhost -p 5432 -U cesi "projetcesi"
pg_restore -h localhost -p 5432 -U cesi  -d "projetcesi" -v "save.bak"
pause