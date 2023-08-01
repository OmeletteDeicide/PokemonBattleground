# Pokemon Battle ground
[Projet] - Application Web Spring
- Créé par Jonathan MARTIN et Simone BARTHE
- Application permettant l'ajout et la création de Pokémons
- Gestion de combats aléatoire entre Pokémons
- Serveur disponible sur TOMCAT connecté avec MariaDB ou MySQL (voir la configuration de connexion)
- CRUD de Pokémons.
## Spécifictées
Chaque Pokémon possède des PV (Points de Vie) et des points d'Attaque (Puissance) et des Points de Défense (Résistance), ces 2 paramètres aident à calculer les PC (Points de Combat).
Chaque Pokémon possède un type qui affecte plus ou moins de dégats en fonction d'autres types

### Typage : 
1. Les Pokémons de types __Normaux__ sont faibles face au Pokémons *Combat* et efficace contre les types *Spèctres*
2. Les pokémons de types __Glace__ sont faibles face au Pokémons *Combat, Feu et Roche* et efficace contre les types Dragon, Plante, Sol et Vol
3. Les pokémons de types __Fée__ sont faibles face au Pokémons *Acier et Poison* et efficace contre les types *Combat, Dragon et Ténèbres*
4. Les pokémons de types __Eau__ sont faibles face au Pokémons *Electrique et Plante* et efficace contre les types *Feu, roche et Sol*
5. Les pokémons de types __Combat__ sont faibles face au Pokémons *Psy et Vol* et efficace contre les types *Glace, Normal et Roche*
6. Les pokémons de types __Acier__ sont faibles face au Pokémons *Combat, Feu et Sol* et efficace contre les types *Glace et Roche*
7. Les pokémons de types __Dragon__ sont faibles face au Pokémons *Dragon et Glace* et efficace contre les types *Dragon*
8. Les pokémons de types __Electrique__ sont faibles face au Pokémons *Sol* et efficace contre les types *Eau et Vol*
9. Les pokémons de types __Feu__ sont faibles face au Pokémons *Eau, Roche et Sol* et efficace contre les types *Glace, Insècte et Plante*
10. Les pokémons de types __Insècte__ sont faibles face au Pokémons *Feu, Poison, Roche et Vol* et efficace contre les types *Plante, Poison, Psy*
11. Les pokémons de types __Plante__ sont faibles face au Pokémons *Feu, Glace, Insècte, Poison et Vol* et efficace contre les types *Eau, Roche et Sol*
12. Les pokémons de types __Psy__ sont faibles face au Pokémons *Insècte* et efficace contre les types *Combat et Poison*
13. Les pokémons de types __Sol__ sont faibles face au Pokémons *Eau, Glace et Plante* et efficace contre les types *Electrique, Feu, Poison et Roche*
14. Les pokémons de types __Ténèbres__ sont faibles face au Pokémons *Combat et Insècte* et efficace contre les types *Psy et Spèctre*
15. Les pokémons de types __Vol__ sont faibles face au Pokémons *Electrique, Glace et Roche* et efficace contre les types *Combat, Insècte et Plante*
16. Les pokémons de types __Spèctre__ sont faibles face au Pokémons *Spèctre* et efficace contre les types *Spèctre*
17. Les pokémons de types __Roche__ sont faibles face au Pokémons *Combat, Eau, Plante, Sol* et efficace contre les types *Feu, Glace, Insècte et Vol*
18. Les pokémons de types __Poison__ sont faibles face au Pokémons *Insècte, Psy et Sol* et efficace contre les types *Insècte et Plante*