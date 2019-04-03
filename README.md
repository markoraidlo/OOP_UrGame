# OOP_UrGame

# Ur mängu reeglid:
  Eesmärk on kõik nupud liigutada lõppu enne, kui seda teeb vastane.
  Mõlemal mängijal on 6 nuppu.
  Valgete nuppudega mängija alustab.
  Visatakse 4 tetraheedroni kujulist täringut, kus kahel tipul neljast on valge märge.
  Vaadatakse mitut valget tippu on neljal täringul pärast veeretamist näha. Sellest tuleneb silmade arv 0st kuni 4ni.
  Mänigja valib, millist nuppu ta tahab liigutada ning tõstab selle visatud silmade arvu kaugusel olevasse ruutu.
  
  Mängija ei saa seda nuppu tõsta kui:
  
    *Ruudul on juba ees sama mängija nupp.
    *Ruudul on ees vastasmängija nupp, mis asub boonusruudu peal.
    *Silmadearvu kaugusel ei asu ruutu (Nupp asub näiteks viimasel ruudul ning mängija veeretab kahe.)
    *Nupp on juba mängulaua läbinud.
    
  Kui vastase nupp maandub ruudul, kus on teine nupp ees, liigub ruudul olnud nupp mängulaualt algusesse tagasi.
  Kui nupp maandub boonusruudul saab mängija uuesti täringut veeretada.
  Kui ühegi nupuga ei ole võimalik silmade arvu kaugusele liigutada või kui silmade arv on 0, algab vastase käik.
