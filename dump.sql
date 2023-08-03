-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           11.1.0-MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Listage de la structure de table pokemon. pokemon
CREATE TABLE IF NOT EXISTS `pokemon` (
  `id` bigint(20) NOT NULL,
  `attack` int(11) DEFAULT NULL,
  `defense` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pc` int(11) DEFAULT NULL,
  `pv` int(11) DEFAULT NULL,
  `type` enum('EAU','FEU','PLANTE') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table pokemon.pokemon : ~9 rows (environ)
INSERT INTO `pokemon` (`id`, `attack`, `defense`, `name`, `pc`, `pv`, `type`) VALUES
	(1, 20, 25, 'Bulbizarre', 200, 20, 'PLANTE'),
	(2, 20, 25, 'Herbizarre', 1500, 20, 'PLANTE'),
	(3, 20, 25, 'Florizarre', 2000, 20, 'PLANTE'),
	(4, 20, 25, 'Salamèche', 200, 20, 'FEU'),
	(5, 20, 25, 'Reptincelle', 1500, 20, 'FEU'),
	(6, 20, 25, 'Dracofeu', 2000, 20, 'FEU'),
	(7, 20, 25, 'Carapuce', 200, 20, 'EAU'),
	(8, 20, 25, 'Carabaffe', 1500, 20, 'EAU'),
	(9, 20, 25, 'Tortank', 2000, 20, 'EAU');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
