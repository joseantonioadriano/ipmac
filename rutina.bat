@echo off
more "db/connectedMACstmp.txt" > "db/connectedMACs.txt"
cd db
del connectedMACstmp.txt
cd ..