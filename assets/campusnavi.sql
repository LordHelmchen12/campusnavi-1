SQLite format 3   @                                                                              � ��d	� �                                                                               �6�?tablepersonenpersonenCREATE TABLE personen (_id INTEGER PRIMARY KEY, vorname TEXT, nachname TEXT, titel TEXT, sprechzeit TEXT, zustaendigkeiten TEXT, email TEXT, POI NUMERIC)�
�gtableraeumeraeumeCREATE TABLE raeume (_id INTEGER PRIMARY KEY, name TEXT, nummer NUMERIC, stockwerk NUMERIC, gebaeude NUMERIC)Y	�tablegebaeudegebaeudeCREATE TABLE gebaeude (_id INTEGER PRIMARY KEY, nummer TEXT)j%%�tablefachbereichefachbereicheCREATE TABLE fachbereiche (_id INTEGER PRIMARY KEY, bezeichnung TEXT)�?�atablepoispoisCREATE TABLE pois (gebäude NUMERIC, fachbereich NUMERIC, bewertung NUMERIC, tags TEXT, besonderheit TEXT, _id INTEGER PRIMARY KEY, bezeichnung TEXT, koordinaten NUMERIC)l--�tableandroid_metadataandroid_metadataCREATE TABLE "android_metadata" ("locale" TEXT DEFAULT 'en_US')                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 � ��                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     	en_USen_US                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        