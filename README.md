# OOP_UrGame

# Ur mängu reeglid:
  Eesmärk on kõik nuppud lõppu liigutada enne kui seda vastane teeb.
  Mõlemal mängijal on 6 nuppu.
  Valgete nuppudega mängija alustab.
  Visatakse 4 tetraheedroni kujulist täringut, kus kahel tippul neljast on valge märge.
  Vaadatakse mitu valget tippu on näha neljal tärinudl pärast veeretamist. Sellest saab
  silmade arvu 0-4.
  Mänigja valib millise nuppu ta tahab liigutada ning tõstab selle visatud silmade arvu
  kaugusel olevasse ruutu.
  
  Mängija ei saa seda nuppu tõsta kui:
  
    Ruudul on juba ees on sama mängija nupp.
    Ruudul on ees vastas mängija nupp, mis asub boonus ruudu peal.
    Silmade arvu kaugusel ei asu ruutu.(Nupp asub näiteks viimasel ruudul ning mängija veeretab kahe.)
    Nupp on juba mängulaua läbinud.
    
  Kui vastase nupp maandub ruudul, kus on teine nupp ees, liigub ruudul olnud nupp mängulaualt välja algusesse tagasi.
  Kui nupp maandub boonusruudul saab mängija uuesti veeretada täringut.
  Kui ühegi nuppuga ei ole võimalik silmade arvu kaugusele liigutada või kui silmade arv on 0, algab vastase käik.
