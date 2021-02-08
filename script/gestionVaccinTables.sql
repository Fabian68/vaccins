--
-- Base de données : `gestionvaccin`
--

CREATE DATABASE  IF NOT EXISTS `gestionvaccin`;
USE `gestionvaccin`;

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

DROP TABLE IF EXISTS `commandes`;
CREATE TABLE IF NOT EXISTS `commandes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateheure` datetime DEFAULT NULL,
  `nombre` int(11) DEFAULT NULL,
  `produitNb` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_commande` (`produitNb`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseurs`
--

DROP TABLE IF EXISTS `fournisseurs`;
CREATE TABLE IF NOT EXISTS `fournisseurs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `pays` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `code_postal` varchar(5) DEFAULT NULL,
  `telephone` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

DROP TABLE IF EXISTS `produits`;
CREATE TABLE IF NOT EXISTS `produits` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `stock` bigint(20) DEFAULT NULL,
  `fournisseurNb` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Produits` (`fournisseurNb`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;