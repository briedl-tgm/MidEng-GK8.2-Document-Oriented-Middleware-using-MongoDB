## 1. Beantwortung der Fragestellungen

**Vorteile eines NoSQL Repository (MongoDB) gegenüber relationalen DBMS:**

1. **Flexibles Schema:** Dokumente in einer Collection müssen nicht dieselbe Struktur haben (Schema-on-Read).
2. **Skalierbarkeit:** Einfachere horizontale Skalierung durch Sharding über mehrere Server hinweg.
3. **Performance:** Sehr hohe Schreibgeschwindigkeit, da komplexe Integritätsprüfungen (wie Foreign Keys) entfallen.
4. **Datenmodell:** JSON-Strukturen entsprechen oft direkt den Objekten im Programmcode, was das Mapping vereinfacht.

**Nachteile eines NoSQL Repository:**

1. **Fehlende Joins:** Verknüpfungen zwischen Daten müssen meist in der Applikationslogik gelöst werden.
2. **Datenkonsistenz:** Bietet oft nur "Eventual Consistency" statt der strikten ACID-Garantien klassischer SQL-Datenbanken.
3. **Redundanz:** Da Daten oft denormalisiert gespeichert werden, steigt der Speicherbedarf.
4. **Komplexität bei Abfragen:** Komplexe analytische Abfragen sind in SQL oft einfacher zu formulieren.

**Schwierigkeiten bei der Zusammenführung von Daten:**

* Unterschiedliche Datenformate oder Feldnamen aus verschiedenen Quellen müssen vereinheitlicht werden.
* Die Sicherstellung der Datenintegrität ohne Fremdschlüssel-Constraints ist schwierig.

**Arten von NoSQL Datenbanken:**

1. **Dokumentenorientiert:** MongoDB.
2. **Key-Value:** Redis.
3. **Spaltenorientiert:** Apache Cassandra.
4. **Graphdatenbanken:** Neo4j.

**CAP Theorem Definition:**

* **C (Consistency):** Alle Knoten liefern zu jeder Zeit die aktuellsten Daten.
* **A (Availability):** Jede Anfrage wird beantwortet (kein Error).
* **P (Partition Tolerance):** Das System läuft weiter, auch wenn die Kommunikation zwischen Knoten unterbrochen ist.
* *Hinweis:* Man kann nur zwei der drei Eigenschaften gleichzeitig garantieren.

**Spezifische Abfrage-Befehle (Mongo Shell):**

* **Lagerstand eines Produkts über alle Standorte:** `db.productData.aggregate([ { $match: { productID: "00-443175" } }, { $group: { _id: "$productID", total: { $sum: "$productQuantity" } } } ])`.
* **Lagerstand eines Produkts eines bestimmten Standortes:** `db.productData.find({ productID: "00-443175", warehouseID: "1" })`.

---

## 2. Dokumentation der Umsetzung (GK)

### 2.1 Systemarchitektur & Datenstruktur

Die Middleware wurde mit **Spring Boot** und **Spring Data MongoDB** realisiert. Die Daten werden im **JSON-Format** in einer MongoDB gespeichert.

**JSON Beispielstruktur:**

```json
{
  "warehouseID": "1",
  "productID": "00-443175",
  "productName": "Bio Orangensaft Sonne",
  "productCategory": "Getraenk",
  "productQuantity": 2500.0
}

```

Diese Struktur ermöglicht eine kontinuierliche Speicherung und einfache Abfrage nach Kategorien oder Standorten.

### 2.2 Implementierte REST-Schnittstellen

| Methode | Pfad | Beschreibung |
| --- | --- | --- |
| **POST** | `/product` | Fügt ein neues Produkt hinzu |
| **GET** | `/product` | Ruft alle Produkte und deren Bestände ab |
| **GET** | `/warehouse` | Ruft die Bestände aller Lagerstandorte ab |

### 2.3 Nachweis der Anforderungen (Checkliste)

* **Middleware:** Spring Boot Applikation erfolgreich gestartet.
* **MongoDB:** Läuft im Docker-Container auf Port 27017.
* **Datenbestand:** 10 Produkte in 3 Kategorien (Getränk, Lebensmittel, Reinigung) wurden beim Start initialisiert.

---

## 3. Mongo Shell CRUD Operationen

Hier sind die Befehle und erwarteten Ergebnisse für dein Protokoll:

1. **Create (Einfügen):**
`db.productData.insertOne({warehouseID: "1", productID: "GK-100", productName: "Testartikel", productCategory: "Reinigung", productQuantity: 10})`
2. **Read (Finden):**
`db.productData.find({productID: "GK-100"})`
3. **Update (Ändern):**
`db.productData.updateOne({productID: "GK-100"}, {$set: {productQuantity: 50}})`
4. **Delete (Löschen):**
`db.productData.deleteOne({productID: "GK-100"})`
5. **Read All (Übersicht):**
`db.productData.find().pretty()`
