Se citeste din versiunea cleaned a fisierului csv si am adaugat biblioteca opencsv 5.3.
Clasa Currency: contine campurile name, symbol si parityToEur, constructorul si getterii, metoda updateParity si tratarea exceptiilor.
Clasa Discount: contine campurile si am facut enum-ul in interiorul acestei clase, constructorul si getterii si tratarea exceptiilor.
Clasa Main: listcurrencies afiseaza currency urile din store
getstorecurrency ia currency ul curent de pe store
addcurrency adauga un currency in store cu campurile nume si simbol si paritate
listproducts afiseaza produsele de pe store
showproduct afiseaza informatii despre produs dupa uniq_id
listmanufacturers afiseaza producatorii de pe store
listproductsbymanufacturarer afiseaza produsele de pe store care au manufacturer ca parametru
listdiscounts afiseaza discount-urile de pe store
addiscount adauga un discount pe store, cu campurile nume, valoare si tip
listdiscounts afiseaza discount-urile de pe store
calculatetotal afla totalul produselor ca parametru, in moneda curenta de pe store si discount-ul aplicat daca este cazul
clasa Manufacturer: campurile name si countProducts cu constructorul si getterii.
clasa Product: campurile corespunzatoare si getterii si setterii si Builderul cu setterii si tratarea exceptiilor
clasa Store: campurile aferente si metodele
readCSV citește din fișierul CSV
saveCSV scrie informatii despre produse într-un fișier CSV
addProduct throws DuplicateProductException adaugă un produs în array-ul de produse
addManufacturer throws DuplicateManufacturerException adaugă un producător în array-ul de producători
loadStore throws IOException încarcă o salvare mai veche din store
saveStore throws IOException salvează un state (o salvare) mai nouă a store-ului
Currency createCurrency creează un currency ce va putea fi ulterior folosit pentru a actualiza prețurile produselor
changeCurrency throws CurrencyNotFoundException schimbă monedă magazinului; odată apelat, trebuie recalculate toate prețurile produselor
Discount createDiscount creează un discount ce va putea fi aplicat asupra produselor
applyDiscount throws DiscountNotFoundException, NegativePriceException - aplică discountul discount asupra tuturor produselor din magazin
Product[] getProductsByManufacturer întoarce doar acele produse care aparțin producătorului manufacturer
calculateTota calculează costul total al produselor date ca parametru (se consideră că se dorește calcul pentru întreaga cantitate)

