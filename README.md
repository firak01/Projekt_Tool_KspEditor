# Projekt_Tool_KspEditor
Ansammlung kleiner Tools, um ein SaveFile von "Kerbal Space Program" zu bearbeiten.
(KSP Version 1.5.1)

Ziel ist es an ein bestehendes Fahrzeug eine neue "Struktur", bestehend aus meheren neuen PARTS, anzuhängen.

Also:
1) Ausgangsstruktur erstellen.
Struktur in KSP bauen und in ein "Dummy" Fahrzeug integrieren.
Dieses "Dummy" Fahrzeug starten.
Aus dem Spielstand das neue "Dummy" Fahrzeug holen
und in eine Datei exportieren.

2) Ausgangsstruktur ermitteln und anpassen
Aus der Datei mit dem "Dummy" Fahrzeug:
Zeilen mit der Struktur daraus extrahieren und in eine eigenen Datei wegspeichern.

3) Zielfahrzeug ermitteln
Aus dem Spielstand das eigentliche Zielfahrzeug holen 
und in eine Datei exportieren

4) Struktur anpassen
Diese Struktur muss dann für das Hinzufügen an ein bestehendes Fahrzeug überarbeitet werden:
a) Perent Indizierung:
- Hänge Sie immer an das Ende (wg. der "dynamischen Inidizierung" der PARTS eines VESSELS)
- Erstes PART der Struktur muss als Parent den "dynamischen Index" des "Aufhänger - PARTS" bekommen. Ermittle das Aufhängerpart (nimm dazu eine im VESSEL vorhandene PART Kombination) und suche in einem Tool wie "KLM" danach.
- Nachfolgende PARTS der Struktur müssen dann als Parent den "dynamischen Index" des ersten PART der Struktur bekommen, usw.

b) Anhängen an das Aufhängerpart
attN Wert sowohl im Aufhängerpart als auch in der neuen Struktur passend machen

c) Korrektes Positionieren der Teile des neuen Struktur
Die bisherige Position der neuen Struktur wird nicht stimmen.
Positionen sind in einem VESSEL immer relativ zueineander.
Orientiere dich an dem Aufhängerpart und den dort vorhandenen Werten. 
Das ist Ausprobieren.

Die Tools sollen mit der Ermittlung einen "Offset" Wertes alle PARTS der Struktur anpassen.
Sichere solch eine Struktur in einer Datei. Möglichst nach jedem Arbeitsschritt

d) Korrektes Drehen der Teile der neuen Struktur
s. Positionieren.



