# NicoBoy

Game-Boy-Emulator für Android (Game Boy, Game Boy Color, Game Boy Advance, DS Beta) mit Controller-Support und Neigungssteuerung.

## APK bauen
1. Projekt auf GitHub hochladen.
2. Unter "Actions" läuft "Build NicoBoy APK" automatisch.
3. Artifact "NicoBoy-APK" herunterladen und installieren.

## Neigungssteuerung & Vibration
Einstellungen -> Touch-Steuerung:
- "Neigung steuert Steuerkreuz": Handy neigen = Steuerkreuz (Empfindlichkeit, Umkehren links/rechts und oben/unten, Neu-Kalibrieren).
- Spiele mit echtem Bewegungssensor (MBC7, z. B. Kirby Tilt 'n' Tumble) nutzen den Handy-Sensor automatisch (analog). Die Kalibrierung passiert beim Laden des Spiels; die Umkehr-Einstellungen gelten auch hier.
- "Vibration bei Tastendruck": kurzes Feedback bei A/B/Start/Select/Steuerkreuz/L/R.

## Startanimation
Beim Start faellt das NicoBoy-Logo ein (Tippen ueberspringt).

## Basis / Lizenz
NicoBoy basiert auf dem Open-Source-Emulator SkyEmu von Skyler "Sky" Saleh (MIT-Lizenz, siehe LICENSE und ACKNOWLEDGMENTS.md).
