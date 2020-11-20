Ez a mappa tartalmazza az algoritmus fejlesztése során írt különbözõ matlab scripteket és függvényeket.
A data mappák a tesztméréseken rögzített idõbélyegeket és a mérési elrendezés koordinátáit tartalmazzák.
Az uwb_mat_lib mappában a számolások során használt segédfüggvények találhatók.
A do_tdoa.m a fõ script, ezt használtam az algoritmus fejlesztéséhez.
A do_tdoa_live.m fájlban az algoritmust úgy alakítottam át, hogy a mérések mátrixba rendezése és a pozíció számítás egyszerre fusson minden tranzakciónál (támogassa a real time alkalmazást)
A tdoa_as_func.m-ben az utóbbi script lett átalakítva függvénnyé, mivel a kód C nyelvre való exportálását csak függvényeken tudja végrehajtani a Matlabba épített fordító. 
A függvény bemenete a feldolgozandó adatfájl, illete a mérésben használt anchorok koordinátáit tartalmazó csv fájl elérési útvonala.

