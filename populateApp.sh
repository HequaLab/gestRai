#!/bin/bash

echo "---------------------------------"
echo "Popolo il database "


#Popolo Filiali
curl -H "Content-Type: application/json" -X POST -d '{"nome":"Abcd"}' http://localhost:9200/ 

#Popolo Fornitori
curl -H "Content-Type: application/json" -X POST -d '{"note":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"ragioneSociale":"Abcd"}' http://localhost:9200/ 

#Popolo Servizi
curl -H "Content-Type: application/json" -X POST -d '{"descrizione":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"fornitore":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"importo":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"note":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"tipologia":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"codice":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"tipo":"Abcd"}' http://localhost:9200/ 

#Popolo RichiestaNuovoServizio


curl -H "Content-Type: application/json" -X POST -d '{"divisione":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"fornitore":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"nome":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"note":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"ora":"Abcd"}' http://localhost:9200/ 

curl -H "Content-Type: application/json" -X POST -d '{"programmazione":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"stato":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"lotto":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"operatore":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"tipologia":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"matricola":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"produzione":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"luogo":"Abcd"}' http://localhost:9200/ 

#Popolo RichiestaNuovaTipologia
curl -H "Content-Type: application/json" -X POST -d '{"descrizione":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"filiale":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"nome":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"stato":"Abcd"}' http://localhost:9200/ 

#Popolo Luoghi
curl -H "Content-Type: application/json" -X POST -d '{"indirizzo":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"descrizione":"Abcd"}' http://localhost:9200/ 
curl -H "Content-Type: application/json" -X POST -d '{"cap":"Abcd"}' http://localhost:9200/ 

#Popolo Lotti
curl -H "Content-Type: application/json" -X POST -d '{"descrizione":"Abcd"}' http://localhost:9200/ 

#Popolo Matricole
curl -H "Content-Type: application/json" -X POST -d '{"descrizione":"Abcd"}' http://localhost:9200/ 

#Popolo Produzioni
curl -H "Content-Type: application/json" -X POST -d '{"descrizione":"Abcd"}' http://localhost:9200/ 
