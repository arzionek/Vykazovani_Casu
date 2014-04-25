INSERT INTO `uzivatel` (`uzivatel_id`, `jmeno`, `prijmeni`, `titul_pred`, `titul_za`, `login`, `heslo`) VALUES
(1, 'Vendelín', 'Pokus', 'prof. Ing.', 'Ph.D.', 'admin', 'admin'),
(2, 'Marek', 'Vohradský', 'Bc.', '', 'capo333', 'xxx'),
(3, 'Vít', 'Štěpánek', 'Bc.', '', 'stepanev', 'xxx'),
(4, 'Josef', 'Strolený', '', '', 'jstrolen', 'xxx'),
(5, 'Jaroslav', 'Ullmann', '', '', 'ullmannj', 'xxx'),
(6, 'Roman', 'Mouček', 'Ing.', 'Ph.D.', 'moucek', 'xxx'),
(7, 'Petr', 'Ježek', 'Ing.', 'Ph.D.', 'jezekp', 'xxx');

INSERT INTO `svatek` (`svatek_id`, `datum`, `nazev`, `uzivatel_id`, `kod`) VALUES
(1, '9999-04-01 00:00:00', 'Svátek1', 1, 'sv1'),
(2, '9999-04-02 00:00:00', 'Svátek2', 1, 'sv2'),
(3, '9999-04-03 00:00:00', 'Svátek3', 1, 'sv3'),
(4, '9999-04-04 00:00:00', 'Svátek4', 1, 'sv4'),
(5, '9999-04-05 00:00:00', 'Svátek5', 1, 'sv5'),
(6, '9999-01-01 00:00:00', 'Nový rok', NULL, '000'),
(7, '9999-01-06 00:00:00', 'Tři králové', NULL, '001'),
(8, '9999-05-01 00:00:00', 'Svátek Práce', NULL, '002'),
(9, '9999-05-08 00:00:00', 'Den vítězství', NULL, '003'),
(10, '9999-07-05 00:00:00', 'Cyril a Metoděj', NULL, '004'),
(11, '9999-07-06 00:00:00', 'Mistr Jan Hus', NULL, '005'),
(12, '9999-10-28 00:00:00', 'Den vzniku samostatného Československého státu', NULL, '006'),
(13, '9999-11-02 00:00:00', 'Dušičky', NULL, '007'),
(14, '9999-12-24 00:00:00', 'Štědrý den', NULL, '008'),
(15, '9999-12-25 00:00:00', '1. svátek vánoční', NULL, '009'),
(16, '9999-12-26 00:00:00', '2. svátek vánoční', NULL, '010'),
(17, '9999-11-17 00:00:00', 'Den boje za svobodu', NULL, '011');

INSERT INTO `cinnost` (`cinnost_id`, `uzivatel_id`, `kod`, `nazev`) VALUES
(1, 1, 'cin1', 'Cinnost1'),
(2, 1, 'cin2', 'Cinnost2'),
(3, 1, 'cin3', 'Cinnost3'),
(4, 1, 'cin4', 'Cinnost4'),
(5, 1, 'cin5', 'Cinnost5');

INSERT INTO `pracovni_pomer` (`pracovni_pomer_id`, `kod`, `nazev`, `velikost_uvazku`, `uzivatel_id`) VALUES
(1, 'pom1', 'Poměr1', 200, 1),
(2, 'pom2', 'Poměr2', 160, 1),
(3, 'pom3', 'Poměr3', 180, 1),
(4, 'pom4', 'Poměr4', 50, 1),
(5, 'pom5', 'Poměr5', 100, 1);

INSERT INTO `kalendar_definice` (`kalendar_definice_id`, `kod`, `nazev`, `tag_pracovni_pomer`, `tag_kalendar_cinnost`, `uzivatel_id`) VALUES
(1, 'kDef1', 'KalendarDefinic1', 'tagPomer', 'tagCinnost', 1);

INSERT INTO `kalendar` (`kalendar_id`, `datum_importu`, `kalendar_definice_id`, `uzivatel_id`) VALUES
(1, '2014-04-01 00:00:00', 1, 1);

INSERT INTO `kalendar_cinnost` (`kalendar_cinnost_id`, `datum`, `pocet_hodin`, `cinnost_id`, `pracovni_pomer_id`, `uzivatel_id`, `kalendar_id`, `cas_od`, `cas_do`) VALUES
(1, '2014-04-01 00:00:00', 200, 1, 1, 1, 1, '2014-04-01 00:00:00', '2014-04-30 00:00:00'),
(2, '2014-04-01 00:00:00', 200, 1, 2, 1, 1, '2014-04-01 00:00:00', '2014-04-30 00:00:00'),
(3, '2014-04-01 00:00:00', 200, 5, 4, 1, 1, '2014-04-01 00:00:00', '2014-04-30 00:00:00');
