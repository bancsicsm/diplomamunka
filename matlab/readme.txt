Ez a mappa tartalmazza az algoritmus fejleszt�se sor�n �rt k�l�nb�z� matlab scripteket �s f�ggv�nyeket.
A data mapp�k a tesztm�r�seken r�gz�tett id�b�lyegeket �s a m�r�si elrendez�s koordin�t�it tartalmazz�k.
Az uwb_mat_lib mapp�ban a sz�mol�sok sor�n haszn�lt seg�df�ggv�nyek tal�lhat�k.
A do_tdoa.m a f� script, ezt haszn�ltam az algoritmus fejleszt�s�hez.
A do_tdoa_live.m f�jlban az algoritmust �gy alak�tottam �t, hogy a m�r�sek m�trixba rendez�se �s a poz�ci� sz�m�t�s egyszerre fusson minden tranzakci�n�l (t�mogassa a real time alkalmaz�st)
A tdoa_as_func.m-ben az ut�bbi script lett �talak�tva f�ggv�nny�, mivel a k�d C nyelvre val� export�l�s�t csak f�ggv�nyeken tudja v�grehajtani a Matlabba �p�tett ford�t�. 
A f�ggv�ny bemenete a feldolgozand� adatf�jl, illete a m�r�sben haszn�lt anchorok koordin�t�it tartalmaz� csv f�jl el�r�si �tvonala.

