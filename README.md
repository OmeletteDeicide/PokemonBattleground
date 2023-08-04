# Pokemon Battle ground
[Projet] - Application Web Spring
- Créé par Jonathan MARTIN et Simone BARTHE
- Application permettant l'ajout et la création de Pokémons
- Gestion de combats aléatoire entre Pokémons
- Serveur disponible sur TOMCAT connecté avec MariaDB ou MySQL (voir la configuration de connexion)
- CRUD de Pokémons.

## Spécifictées
Chaque Pokémon possède des PV (Points de Vie), des points d'Attaque (Puissance) et des Points de Défense (Résistance), ces 2 paramètres aident à calculer les PC (Points de Combat).
Chaque Pokémon possède un type qui affecte plus ou moins de dégats en fonction d'autres types (voir les règles de typage ci-dessous)
Les Points de Vie, d'attaque et de défense sont générés aléatoirement, les Points de Combats sont eux générés grâce à un calcul.

### Typage : 
1. Les pokémons de types __Eau__ sont faibles face au Pokémons *Plante* et efficace contre les types *Feu*
2. Les pokémons de types __Feu__ sont faibles face au Pokémons *Eau* et efficace contre les types *Plante*
3. Les pokémons de types __Plante__ sont faibles face au Pokémons *Feu* et efficace contre les types *Eau*

## Délais
5 jours de développements avec l'inclusion d'un cahier des charges à rythme de 6 heures / jours.
## Version 1.0, Beta (2.0 en cours de développement ...)
