Az al�bbi mapp�k a helymeghat�roz� rendszerhez k�sz�lt Android alkalmaz�s fejleszt�se sor�n haszn�lt forr�sk�dokat tartalmazz�k.
A "paintball-demo-app" mapp�ban tal�lhat� maga a paintball alkalmaz�s, amely a tag �ltal elk�ld�tt �zeneteket feldolgozza, �s a diplomamunk�ban le�rtak szerint
megjelen�ti egy t�rk�pen a j�t�kos saj�t �s csapatt�rsai poz�ci�j�t. (A jelenleg kirajzolt t�rk�p egy minta.)
A "paintball-demo-server" mapp�ban egy tesztel�shez haszn�lt szerver applik�ci� tal�lhat�, amely a egyr�szt a csapat tagjainak �sszegy�jt�s��rt felel,
m�sr�szt tesztadatokat is k�ld MQTT-n kereszt�l, hogy a kommunik�ci� m�k�d�s�t ellen�rizni lehessen.

Az j�t�k m�k�d�s�hez fel kell programozni a h�l�zatban egy MQTT br�kert, valamint ugyanezen az eszk�z�n el kell ind�tani a szerver alkalmaz�st.
A szerver alkalmaz�s k�dj�ban be kell �ll�tani a br�ker IP-c�m�t porttal egy�tt (default.properties), a mobilalkalmaz�son pedig a szerver IP c�m�t (port n�lk�l) (config.xml)
A mobilalkalmaz�s ezut�n futtathat�, �s tesztelhet�. A szerver k�pes tesztadatokat is szolg�ltatni, j�t�kosokat is szimul�lni, melyeket a szervernek k�ld�tt
http �zenetekkel lehet elind�tani. A pontos paracsok megtal�lhat�ak a szerver alkalmaz�s forr�sk�dj�ban.
Az MQTT t�m�k: positions/redTeam �s positions/blueTeam, ezekre feliratkozva ak�r k�ls� programmal is kiolvashat�ak a csapat adatai.
