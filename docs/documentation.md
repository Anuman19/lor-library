# ***LoR Library***

## Inhalt

- [***LoR Library***](#lor-library)
  - [Inhalt](#inhalt)
- [1 Abstract (Kurzbeschreibung)](#1-abstract-kurzbeschreibung)
- [2 Konkurrenzanalyse](#2-konkurrenzanalyse)
- [3 User Storys](#3-user-storys)
- [4 Mockups und App Struktur](#4-mockups-und-app-struktur)
- [5 Technische Realisierung](#5-technische-realisierung)
  - [Einlesen der JSON Datei](#einlesen-der-json-datei)
  - [Daten in Liste darstellen](#daten-in-liste-darstellen)
- [6 Fazit](#6-fazit)

# 1 Abstract (Kurzbeschreibung)

In unserem Projekt dreht es sich um das Kartenspiel "Legends of Runeterra".
Die App bietet dazu eine Begleitfunktion und soll dem Nutzer ermöglichen sich zu den Karten zu informieren.
Auf der Hauptseite wird der Nutzer von jeweils fünf zufällig ausgewählten Karten begrüsst, welche dem User direkt die Möglichkeit geben mit der App zu interagieren.
Dazu gibt es eine Übersichtsseite auf der alle Karten angezeigt werden und per drauftippen eine Detailsicht erscheint.

# 2 Konkurrenzanalyse

Beim Betrachten anderer Apps ist uns aufgefallen, dass diese häufig etwas unübersichtlich und überladen wirken. 
Deshalb war es für uns wichtig ein schlichtes und übersichtliches Design zu bewahren.

# 3 User Storys

Als Benutzer möchte ich auf der Hauptseite mit einer Auswahl an Karten begrüsst werden, um direkt mit den Karten in Berührung zu kommen.

Als Benutzer möchte ich auf eine Karte tippen um eine Detaillansicht zu erhalten.

Als Benutzer möchte ich Karten als Favoriten markieren können, um mir diese zu merken.

Als Benutzer möchte ich alle Karten, welche ich als Favorit markiert habe, auf einer separaten Seite anschauen können.

Als Benutzer möchte ich eine Übersichtseite welche alle Karten darstellt.

Als Benutzer möchte ich nach bestimmten Karten per Name suchen. 

Als Benutzer möchte ich ein Deck erstellen welches den Spielregeln entspricht.

Als Benutzer möchte ich Karten dem Deck hinzufügen.

# 4 Mockups und App Struktur

***1. Startactivity***  
Die Startactivity bietet dem User die Möglichkeit direkt in Kontakt mit den Karten zu gelangen.
Jedes mal wenn die Startseite geladen wird, werden 5 zufällig ausgewählte Karten angezeigt.
Diese Karten können angetippt werden um auf die Detailsicht der jeweiligen Karte zu gelangen.
Ausserdem kann über das Burger Menu oben-rechts auf die Übersichtsseite navigiert werden.

***2. Übersicht***  
Auf der Übersichtsseite werden alle Karten in einer Liste angezeigt.
Umd die Performance zu gewährleisten werden immer nur fünf Karten auf einmal geladen und erst bei weiterem scrollen die nächsten fünf.
Auch hier kann durch antippen der Karten auf die Detailseite gewechselt werden und über das Burgermenu kann man auch auf die Hauptseite zurück.

***3. Card Details***   
Die Detailseite wird geöffnet wenn der User auf eine der Karten tippt.
Auf dieser Seite wird das Bild der Karte und ein Banner angezeigt.
Zusätzlich gibt es noch ein paar Extrainformationen welche man anschauen kann.

# 5 Technische Realisierung

Unsere komplexe Komponente umfasst das Umwandeln eines JSON Dokuments und das anschliessende Darstellen der der Daten in einer benutzerdefinierten Liste.


## Einlesen der JSON Datei
Für das Einlesen der Datei nutzen wir einen Inputstream, welcher die Datei über ein byte Array in einen String abspeichert.
Der String wird anschliessend mit GSON in ein Array von Card Objekten umgewandelt.
Das Card Objekt ist hierbei eine selbst erstellte Klasse und beinhaltet alle Informationen, welche eine Karte enthält.

## Daten in Liste darstellen

Für die Liste haben wir eine RecycleView implementiert. Dadurch konnten wir einen eigenen RecycleViewAdapter entwerfen wo die Datendarstellung geschieht. Die Entscheidung fiel auf RecycleView und nicht auf ListView oder GridLayout weil wir dadurch die CardView Komponente am besten umsetzten konnten. Diese wird gebraucht um unser Kartenmodell darzustellen. Die RecycleView verspricht Performance und Kontrolle über die Daten und deren Verarbeitung. Hierbei wurde noch eine ScrollView mit ScrollListener implementiert. Dadurch werden beim Scrollen jeweils fünf weitere Karten geladen und dargestellt. Mit dieser Variante können wir bessere Performance und User Experience generieren.

# 6 Fazit

Das Projekt lief von Anfang an etwas holprig, da wir teilweise noch die Grundlagen von Android Studio verinnerlichen mussten.
Als wir dann beide am Projekt arbeiten konnte lief es gut und wir konnten recht schnell unsere JSON Datei auslesen und die Daten via GSON zu Java Objekten umwandeln.
Auch die Darstellung dieser Karten in einer Liste ging sehr gut.
Doch als wir uns dann mit der Darstellung der Bilder auseinandergesetzt haben, stiessen wir auf die ersten grösseren Probleme.
Da wir unsere Bilder nicht lokal gespeichert haben mussten wir diese per URL jeweils herunterladen, was in der ersten Version der Übersichtsseite zu Performance Problemen geführt hat.

Im Verlauf des Projektes wurde uns zunehmend bewusst, dass für die Umsetzung aller Funktionen, die wir uns vorgenommen haben, keine Zeit ist.
Wir hatten aufgrund von einem Projekt der FFHS und von Bewerbungsgesprächen am BIT deutlich weniger Zeit als ursprünglich geplant.
Wir konnten aufgrund der fehlenden Zeit leider nur wenige Funktionalitäten, die wir uns vorgenommen haben, umsetzen.
Wir hätten gerne noch Funktionen wie das Favorisieren oder das erstellen von Decks umgesetzt.


