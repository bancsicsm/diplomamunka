Az alábbi mappák a helymeghatározó rendszerhez készült Android alkalmazás fejlesztése során használt forráskódokat tartalmazzák.
A "paintball-demo-app" mappában található maga a paintball alkalmazás, amely a tag által elküldött üzeneteket feldolgozza, és a diplomamunkában leírtak szerint
megjeleníti egy térképen a játékos saját és csapattársai pozícióját. (A jelenleg kirajzolt térkép egy minta.)
A "paintball-demo-server" mappában egy teszteléshez használt szerver applikáció található, amely a egyrészt a csapat tagjainak összegyûjtéséért felel,
másrészt tesztadatokat is küld MQTT-n keresztül, hogy a kommunikáció mûködését ellenõrizni lehessen.

Az játék mûködéséhez fel kell programozni a hálózatban egy MQTT brókert, valamint ugyanezen az eszközön el kell indítani a szerver alkalmazást.
A szerver alkalmazás kódjában be kell állítani a bróker IP-címét porttal együtt (default.properties), a mobilalkalmazáson pedig a szerver IP címét (port nélkül) (config.xml)
A mobilalkalmazás ezután futtatható, és tesztelhetõ. A szerver képes tesztadatokat is szolgáltatni, játékosokat is szimulálni, melyeket a szervernek küldött
http üzenetekkel lehet elindítani. A pontos paracsok megtalálhatóak a szerver alkalmazás forráskódjában.
Az MQTT témák: positions/redTeam és positions/blueTeam, ezekre feliratkozva akár külsõ programmal is kiolvashatóak a csapat adatai.
