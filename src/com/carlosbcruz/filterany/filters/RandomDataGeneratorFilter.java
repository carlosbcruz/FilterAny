/**
 * The program(s) herein may be used  freely
 * for personal use only. It may not be sold, 
 * rented, leased, sublicensed  or  modified 
 * without permission of  the  author.  
 * 
 *           www.carlosbcruz.com
 *           
 * This program is provided "AS IS"  without 
 * warranty of any kind. In no event I  will
 * be liable  for  any  direct  or  indirect 
 * damage.
 */
package com.carlosbcruz.filterany.filters;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

import com.carlosbcruz.filterany.CommandLine;
import com.carlosbcruz.filterany.Example;
import com.carlosbcruz.filterany.FilterAdapter;
import com.carlosbcruz.filterany.FilterControls;
import com.carlosbcruz.filterany.FilterException;
import com.carlosbcruz.filterany.FilterLevel;
import com.carlosbcruz.filterany.FilterParameter;
import com.carlosbcruz.filterany.FilterType;
import com.carlosbcruz.filterany.SpecialBehavior;
import com.carlosbcruz.filterany.Text;
import com.carlosbcruz.filterany.configuration.FilterAnyConfiguration;

/**
 * Generates data to be used to test applications.
 * 
 * 466 Male Portuguese Names. 396 Male English Names. 503 Female Portuguese
 * Names. 521 Female English Names. 2625 Surnames in Portuguese. 716 Surnames in
 * English. 20 Top Levels Domains. 249 Emails country codes.152 Addresses in
 * Portuguese. 500 Addresses in English. 5206 Cities in Portuguese. 513 Cities
 * in English. 600 Zip Codes in English. 27 States in Portuguese. 50 States in
 * English. 202 Countries in Portuguese. 247 Countries in English. 239 Two
 * Letters Country Codes. 239 Three Letters Country Codes. 125 Time zones.
 * 
 * @author Carlos Fernando Bella Cruz - <a
 *         href="mailto:pessoal@carlosbcruz.com">pessoal@carlosbcruz.com</a>
 */
public class RandomDataGeneratorFilter extends FilterAdapter implements
		SpecialBehavior, Example, CommandLine {

	private static final long serialVersionUID = 1L;

	private static final int NOT_DEFINED = -1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterName()
	 */
	@Override
	public String getFilterName() {

		return Text.get(Text.FILTER_TESTDATAGENERATORFILTER_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getSmallDescription()
	 */
	@Override
	public String getSmallDescription() {

		return Text.get(Text.FILTER_TESTDATAGENERATORFILTER_TOOLTIP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getFilterInstructions()
	 */
	@Override
	public String getFilterInstructions() {

		return Text.get(Text.FILTER_TESTDATAGENERATORFILTER_INSTRUCTIONS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getFilterLevel()
	 */
	@Override
	public FilterLevel getFilterLevel() {

		return FilterLevel.GENERAL_USER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsRequired()
	 */
	@Override
	public FilterControls[] getControls() {

		return new FilterControls[] { FilterControls.INPUT_FIELD_1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsType()
	 */
	@Override
	public FilterType[] getControlsType() {

		return new FilterType[] { FilterType.NUMBER_GENERIC };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#gerControlsRequired()
	 */
	@Override
	public FilterParameter[] gerControlsRequired() {

		return new FilterParameter[] { FilterParameter.OPTIONAL };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.FilterAdapter#getControlsLabels()
	 */
	@Override
	public String[] getControlsLabels() {

		return new String[] { Text
				.get(Text.FILTER_TESTDATAGENERATORFILTER_FIELD_1) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Filter#getControlsToolTipTexts()
	 */
	@Override
	public String[] getControlsToolTipTexts() {

		return new String[] { Text
				.get(Text.FILTER_TESTDATAGENERATORFILTER_FIELD_1_TOOLTIP) };
	}

	/**
	 * Store an element from the mask. It can be command if it follows a
	 * specific format.
	 */
	static class MaskElement implements Serializable {

		private static final long serialVersionUID = 1L;

		// The generator correspondent to the the element.
		Generator generator = null;

		/**
		 * All generators must comply with the Generator interface.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private interface Generator {

			String getContent();
		}

		/**
		 * If an element does not comply with a command format than it is a copy
		 * generator.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class CopyGenerator implements Generator {

			private String content = new String();

			/**
			 * Default constructor.
			 * 
			 * @param content
			 */
			CopyGenerator(String content) {

				this.content = content;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				return this.content;
			}

		}

		/**
		 * Generate a digit from 0 to 9.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class DigitFrom0To9 implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				return String.valueOf(randomGenerator.nextInt(10));
			}
		}

		/**
		 * Generate a lower case vowel.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class LowerCaseVowel implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				char values[] = { 'a', 'e', 'i', 'o', 'u' };

				return String.valueOf(values[randomGenerator
						.nextInt(values.length)]);
			}
		}

		/**
		 * Generate an upper case vowel.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class UpperCaseVowel implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				char values[] = { 'A', 'E', 'I', 'O', 'U' };

				return String.valueOf(values[randomGenerator
						.nextInt(values.length)]);
			}
		}

		/**
		 * Generate a lower case consonant.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class LowerCaseConsonant implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				char values[] = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
						'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x', 'y', 'w',
						'z' };

				return String.valueOf(values[randomGenerator
						.nextInt(values.length)]);
			}
		}

		/**
		 * Generate an upper case consonant.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class UpperCaseConsonant implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				char values[] = { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
						'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'X', 'Y', 'W',
						'Z' };

				return String.valueOf(values[randomGenerator
						.nextInt(values.length)]);
			}
		}

		/**
		 * Generate a lower case letter.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class LowerCaseLetter implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				char values[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
						'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
						'u', 'v', 'x', 'w', 'z' };

				return String.valueOf(values[randomGenerator
						.nextInt(values.length)]);
			}
		}

		/**
		 * Generate an Upper case letter.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class UpperCaseLetter implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				char values[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
						'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
						'U', 'V', 'X', 'W', 'Z' };

				return String.valueOf(values[randomGenerator
						.nextInt(values.length)]);
			}
		}

		/**
		 * Generate a lower case syllable.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class LowerCaseSyllable implements Generator {

			@SuppressWarnings("synthetic-access")
			private Generator first = new LowerCaseConsonant();
			@SuppressWarnings("synthetic-access")
			private Generator second = new LowerCaseVowel();

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				return this.first.getContent() + this.second.getContent();
			}
		}

		/**
		 * Generate an upper case syllable.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class UpperCaseSyllable implements Generator {

			@SuppressWarnings("synthetic-access")
			private Generator first = new UpperCaseConsonant();
			@SuppressWarnings("synthetic-access")
			private Generator second = new UpperCaseVowel();

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				return this.first.getContent() + this.second.getContent();
			}
		}

		/**
		 * Generate a word.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class Word implements Generator {

			@SuppressWarnings("synthetic-access")
			private Generator first = new LowerCaseConsonant();
			@SuppressWarnings("synthetic-access")
			private Generator second = new LowerCaseVowel();

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				int length = randomGenerator.nextInt(3) + 2;

				String word = new String();
				for (int i = 0; i < length; i++) {
					word += this.first.getContent() + this.second.getContent();
				}

				return word;
			}
		}

		/**
		 * Male name in Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class MaleNamePortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "Abner", "Adeildo", "Adeilson", "Adelio",
						"Aderson", "Adilson", "Adriano", "Afonso", "Aires",
						"Airton", "Alan", "Alencar", "Alessandro", "Alex",
						"Alexandel", "Alexandre", "Alexei", "Alexsander",
						"Alfredo", "Allan", "Alvaro", "Alysson", "Amauri",
						"Anderson", "Andreo", "Andrew", "André", "Aneor",
						"Anirban", "Antônio", "Ariel", "Arlen", "Arthur",
						"Artur", "Athos", "Benedetto", "Breno", "Bruno",
						"Caetano", "Caio", "Cairo", "Calisto", "Camilo",
						"Carlo", "Carlos", "Carmo", "Cassiano", "Cauã", "Cauê",
						"Caíque", "Celemente", "Celso", "Cesar", "Christian",
						"Christophe", "Christopher", "Cibele", "Cid", "Cirilo",
						"Ciro", "Clayton", "Clementino", "Clemir", "Clodomiro",
						"Clodovil", "Cláudio", "Cléber", "Clécio", "Clóvis",
						"Conrado", "Constantino", "Cristiano", "Cristóvão",
						"Cássio", "Cândido", "Célio", "César", "Cícero",
						"Daniel", "Danilo", "Davson", "Denis", "Diego",
						"Dilson", "Diogo", "Dioney", "Dirceu", "Djalma",
						"Dominique", "Douglas", "Eder", "Edgar", "Edgard",
						"Edmarcio", "Edmilson", "Edmundo", "Edney", "Edson",
						"Eduardo", "Edvaldo", "Edwins", "Egídio", "Elcio",
						"Eliaquim", "Elinaldo", "Eliseu", "Elison", "Elizeu",
						"Elton", "Elói", "Emanuel", "Emerson", "Emilio",
						"Emílio", "Enio", "Enrico", "Enzo", "Enéias",
						"Epitácio", "Erasmo", "Eric", "Erico", "Erik",
						"Ernesto", "Estevão", "Esthon", "Estéfano", "Estênio",
						"Eugênio", "Eurico", "Eusébio", "Evaldo", "Evandro",
						"Evaristo", "Everaldo", "Everson", "Expedito",
						"Ezequias", "Ezequiel", "Fabiano", "Fabrizio",
						"Fabrício", "Fausto", "Felipe", "Felippe", "Fernando",
						"Filipe", "Filipi", "Fillipo", "Flávio", "Francisco",
						"Frank", "Frederico", "Fábio", "Gabriel", "Gemison",
						"Geraldo", "Gerson", "Giacomo", "Giampiero",
						"Gilberto", "Gilmar", "Giovanni", "Glauber",
						"Gleidson", "Guilherme", "Gunter", "Gustavo",
						"Hamilton", "Haroldo", "Harry", "Heitor", "Helder",
						"Heleno", "Henri", "Henrique", "Heraldo", "Herbert",
						"Hermês", "Hileano", "Hilário", "Homero", "Horácio",
						"Hosokawa", "Hudson", "Hugo", "Humberto", "Husni",
						"Hélio", "Héracles", "Hércules", "Igor", "Innocêncio",
						"Ivan", "Ivanildo", "Jacy", "Jairo", "Jan", "Janio",
						"Jean", "Jefferson", "Jeison", "Joaquim", "Johnnyson",
						"Jonatas", "Jordano", "Jorge", "Jose", "João",
						"Juarez", "Júlio", "Kamal", "Karl", "Karlos", "Kauã",
						"Kauê", "Keiji", "Kelsen", "Kelvin", "Ken", "Kevin",
						"Kirk", "Kiyoshi", "Klaus", "Koji", "Krishnan", "Kurt",
						"Leandro", "Leonardo", "Leonel", "Lidnaldo", "Lucas",
						"Luciano", "Luis", "Luiz", "Lúcio", "Manoel", "Manuel",
						"Marcel", "Marcello", "Marcelo", "Marcio", "Marco",
						"Marcony", "Marcos", "Marcus", "Martin", "Mateus",
						"Matheus", "Matias", "Mauro", "Maurício",
						"Maximiliano", "Melo", "Michel", "Miguel", "Miki",
						"Minoru", "Miro", "Moacir", "Mozart", "Murillo",
						"Murilo", "Márcio", "Mário", "Máximo", "Múcio",
						"Naahman", "Naam", "Naamã", "Nabal", "Nabih", "Nabor",
						"Nabucodonosor", "Nadabe", "Nadal", "Nagibe", "Naim",
						"Naindí", "Naldo", "Nanael", "Naor", "Napoleão",
						"Narceu", "Narciso", "Narcísio", "Nardini", "Nardo",
						"Nascimento", "Natal", "Natalino", "Natalício",
						"Natan", "Natanael", "Nataniel", "Nathan", "Natã",
						"Naum", "Navito", "Nazareno", "Nazariel", "Nazaré",
						"Nazário", "Neandro", "Neemias", "Neftali", "Nei",
						"Neil", "Neilor", "Neimar", "Neleu", "Nelson",
						"Nemestrino", "Nemias", "Nemo", "Neomedes", "Neon",
						"Nereu", "Nero", "Nery", "Nestor", "Neville", "Newton",
						"Ney", "Neófito", "Nicandro", "Nicanor", "Nicholas",
						"Nick", "Nico", "Nicodemus", "Nicol", "Nicola",
						"Nicolau", "Nicolino", "Nicomedes", "Nicácio",
						"Nicéforo", "Niels", "Nigel", "Nikolai", "Nikolas",
						"Nil", "Nildo", "Nilo", "Nilson", "Nilton", "Nimbus",
						"Nino", "Nivaldo", "Noah", "Noel", "Nohouka", "Nolan",
						"Nolana", "Nolepeleko", "Norberto", "Norris", "Norton",
						"Nowak", "Noé", "Noêmio", "Nuno", "Nupeba", "Náder",
						"Násser", "Nélio", "Névio", "Nícolas", "Nílsen",
						"Núncio", "Odete", "Osias", "Osmar", "Osvaldo",
						"Otávio", "Pablo", "Patrick", "Paulo", "Pedro",
						"Piero", "Prudente", "Rafael", "Ramon", "Raul",
						"Renato", "Ricardo", "Rivanor", "Robert", "Roberto",
						"Rodolfo", "Rodrigo", "Rogerio", "Ronaldo", "Ronei",
						"Rubio", "Rômulo", "Sabino", "Said", "Salvador",
						"Salvatore", "Samuel", "Sandro", "Saulo", "Sebastião",
						"Serafim", "Severino", "Sidney", "Silvano", "Silvério",
						"Solano", "Stéphano", "Sérgio", "Sílvio", "Tabajara",
						"Tadeu", "Takeo", "Tancredo", "Tarcisio", "Tarciso",
						"Telmo", "Tenório", "Teobaldo", "Teodoro", "Thales",
						"Thiago", "Thomas", "Théo", "Tiago", "Tibério",
						"Tércio", "Túlio", "Ubirajara", "Ubiratã", "Ulisses",
						"Uriel", "Valmir", "Vando", "Vitor", "Wagner",
						"Waldivino", "Walter", "Weldson", "Wellingtom",
						"Wellington", "Welton", "Wendell", "Wesley", "Wiliam",
						"Willian", "Wilson", "Wladmir", "Xavier", "Xisto",
						"Yago", "Yarle", "Yoshihiro", "Yuri", "Ângelo", "Éder",
						"Édson", "Élder", "Élson", "Émerson", "Érico", "Ênio"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Male name in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class MaleNameEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "Philip", "Silas", "Dustin", "Hammett",
						"Giacomo", "Kasimir", "Colin", "Jared", "Graiden",
						"Mark", "Stone", "Declan", "Barclay", "Adrian",
						"Hamilton", "Dylan", "Zephania", "Griffith", "Quinlan",
						"Vladimir", "Alvin", "Chaney", "Castor", "Matthew",
						"Erasmus", "Stuart", "Dorian", "Drew", "Brendan",
						"Joshua", "Driscoll", "Tate", "Gil", "Lawrence",
						"Edan", "Raphael", "Carlos", "Josiah", "Duncan",
						"Kennedy", "Clark", "Carter", "Dolan", "Charles",
						"Derek", "Lamar", "Channing", "Myles", "Gary", "Bruce",
						"Orlando", "Kenneth", "Elijah", "Guy", "Tyler",
						"Nicholas", "Colby", "Elliott", "Daniel", "Jin",
						"Clayton", "Malcolm", "Bruno", "John", "Hiram",
						"August", "Thor", "Patrick", "Kelly", "Buckminster",
						"Allistair", "Eric", "Omar", "Keefe", "Ignatius",
						"Igor", "Cadman", "Thomas", "Zeus", "Lee", "Donovan",
						"Ronan", "Jonas", "Wyatt", "Arsenio", "Zahir", "Devin",
						"Cedric", "Nasim", "Victor", "Aaron", "Scott", "Yasir",
						"Chaim", "Nissim", "Palmer", "Marsden", "Chase",
						"Todd", "Howard", "Benedict", "Yoshio", "Nero",
						"Denton", "Rahim", "Kenyon", "Bert", "Elmo", "Brady",
						"Ashton", "Blake", "Finn", "Stephen", "Wang", "Isaac",
						"Warren", "Hop", "Theodore", "Deacon", "Garrett",
						"Ray", "Ira", "Rafael", "Christopher", "Arthur",
						"Zeph", "Quentin", "Edward", "Cyrus", "Neil", "Acton",
						"Cullen", "Sylvester", "Burke", "Hedley", "Levi",
						"Caesar", "Beau", "Hunter", "Dennis", "Chandler",
						"Orson", "Michael", "Ethan", "Seth", "Merrill",
						"Alden", "Abbot", "Aladdin", "Demetrius", "Neville",
						"Alfonso", "Talon", "Travis", "Fletcher", "Eagan",
						"Colorado", "Lane", "Nash", "Alexander", "Rashad",
						"Hector", "Randall", "Uriah", "Sawyer", "Len",
						"Harrison", "Drake", "Emmanuel", "Fritz", "Leroy",
						"Xavier", "Macon", "Brett", "Lucian", "Stewart",
						"Armando", "Aquila", "Keegan", "Ahmed", "Ralph",
						"Magee", "Wade", "Lucas", "Cooper", "Kato", "Ross",
						"Marvin", "Steel", "Brody", "Camden", "Tad",
						"Chadwick", "Samson", "James", "Caldwell", "Dominic",
						"Oren", "Logan", "Colt", "Thane", "Coby", "Lance",
						"Ferris", "Preston", "Jameson", "Connor", "William",
						"Ezekiel", "Hu", "Flynn", "Cain", "Barrett", "Jerry",
						"Eaton", "Walker", "Troy", "Ivan", "Addison", "Holmes",
						"Colton", "Ryder", "Davis", "Keith", "Rajah",
						"Abraham", "Darius", "Timothy", "Branden", "Jamal",
						"Gage", "Caleb", "Conan", "Honorato", "Micah",
						"Salvador", "David", "Macaulay", "Clinton", "Jakeem",
						"Kaseem", "Ulysses", "Walter", "Christian", "Abdul",
						"Ezra", "Galvin", "Lev", "Tanek", "Slade", "Gavin",
						"Jeremy", "Thaddeus", "Avram", "Kennan", "Ivor",
						"Jesse", "Dean", "Dillon", "Quamar", "Gannon",
						"Tyrone", "Adam", "Cade", "Brock", "Vernon", "Joel",
						"Lars", "Hakeem", "Carson", "Valentine", "Grady",
						"Graham", "Elvis", "Jacob", "Anthony", "Abel", "Hayes",
						"Plato", "Chester", "Hilel", "Phillip", "Gray",
						"Hyatt", "Brent", "Samuel", "Roth", "Brenden",
						"Ferdinand", "Geoffrey", "Knox", "Tucker", "Kyle",
						"Raymond", "Gabriel", "Oliver", "Ishmael", "Xanthus",
						"Amery", "Forrest", "Baxter", "Vaughan", "George",
						"Armand", "Otto", "Tanner", "Isaiah", "Reed", "Owen",
						"Raja", "Mohammad", "Kibo", "Ryan", "Herrod", "Yuli",
						"Jack", "Trevor", "Dieter", "Allen", "Robert",
						"Malachi", "Jarrod", "Dexter", "Nathaniel", "Amir",
						"Garrison", "Oleg", "Dane", "Xander", "Lester",
						"Felix", "Ali", "Jordan", "Julian", "Damian",
						"Maxwell", "Gregory", "Solomon", "Tobias", "Cameron",
						"Harlan", "Paki", "Hoyt", "Luke", "Boris", "Curran",
						"Mufutau", "Nehru", "Moses", "Paul", "Chancellor",
						"Asher", "Uriel", "Jasper", "Louis", "Emery", "Malik",
						"Jermaine", "Akeem", "Jerome", "Timon", "Arden",
						"Cruz", "Upton", "Simon", "Rudyard", "Byron", "Jason",
						"Aristotle", "Bradley", "Yardley", "Callum", "Nigel",
						"Wesley", "Bevis", "Axel", "Melvin", "Harper",
						"Clarke", "Burton", "Sebastian", "Kadeem", "Hashim",
						"Kevin", "Hasad", "Henry", "Austin", "Nathan", "Reese",
						"Benjamin", "Prescott", "Elton", "Mason", "Francis",
						"Quinn", "Amal" };
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Female name in Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class FemaleNamePortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Abigail", "Acácia", "Ada", "Adalina", "Adamache", "Adelaide",
						"Adele", "Adelia", "Adelina", "Adriana", "Adriane",
						"Adália", "Adélia", "Afra", "Aidê", "Aila", "Akemi",
						"Akiko", "Alana", "Alane", "Alanis", "Alaíde", "Alba",
						"Albina", "Alcina", "Alcione", "Alda", "Aldrin",
						"Alessandra", "Alexandra", "Alexia", "Alice", "Alicia",
						"Aline", "Alois", "Alvina", "Alzira", "Alícia",
						"Amala", "Amanda", "Amina", "Amália", "Amélia",
						"Ana Clara", "Ana Laura", "Ana", "Ananda", "Anastácia",
						"Andrea", "Andreia", "Andressa", "Andréa", "Andréia",
						"Anelisa", "Anete", "Angela", "Angelica", "Angelina",
						"Angelyta", "Angélica", "Anislaine", "Anita", "Ann",
						"Anna", "Anne", "Antonieta", "Antônia", "Aparecida",
						"Araceli", "Araci", "Ariadna", "Ariadne", "Arlete",
						"Arlinda", "Astrid", "Astride", "Barbara", "Bartira",
						"Beatrice", "Beatriz", "Bela", "Belina", "Belisária",
						"Belmira", "Benedita", "Beni", "Benta", "Berenice",
						"Bernadete", "Bernadina", "Berta", "Betina", "Bianca",
						"Bina", "Blanca", "Branca", "Brenda", "Bridget",
						"Brigite", "Bruna", "Bárbara", "Camila", "Camilla",
						"Carolina", "Caroline", "Cinthia", "Claudia",
						"Cristiane", "Cristianne", "Cristina", "Dagmar",
						"Daiana", "Daiane", "Daisy", "Dalva", "Daniela",
						"Daniele", "Danielly", "Daphne", "Darci", "Darcy",
						"Darlene", "Dayane", "Debora", "Deborah", "Deise",
						"Dejanira", "Delfina", "Denise", "Deolinda", "Deovete",
						"Derci", "Diana", "Diane", "Dilma", "Diná", "Dinísia",
						"Dionysia", "Dirce", "Diva", "Dolores", "Domenica",
						"Dominga", "Dora", "Doralice", "Dorothy", "Débora",
						"Désirée", "Ednezer", "Eliane", "Eliazar", "Elizamar",
						"Ely", "Evelin", "Fabiana", "Fabiane", "Fabíola",
						"Fani", "Fanny", "Fantine", "Farah", "Fausta",
						"Felícia", "Fernanda", "Filomena", "Firmina", "Flor",
						"Flora", "Florence", "Florença", "Flávia", "Francesca",
						"Francisca", "Francislaine", "Françoise", "Fábia",
						"Fátima", "Fúlvia", "Gabriela", "Gabrielle", "Galia",
						"Geni", "Georgeta", "Georgete", "Georgina", "Geralda",
						"Geraldine", "Geórgia", "Giorgia", "Giorgina",
						"Giovana", "Giovanna", "Gisela", "Gisele", "Gislaine",
						"Giulia", "Giuliana", "Gladis", "Gladys", "Glaucia",
						"Gleicimara", "Gláucia", "Glória", "Grace", "Graziela",
						"Graça", "Hamílcar", "Helene", "Heyde", "Iasmin",
						"Ieda", "Ilse", "Ilza", "Ingrid", "Inês", "Iolanda",
						"Ione", "Ioná", "Iracema", "Iraci", "Irene", "Isa",
						"Isabel", "Isabela", "Isadora", "Ivana", "Ivani",
						"Ivete", "Ivone", "Iza", "Izadora", "Jacira",
						"Jacqueline", "Jade", "Janaína", "Jandira", "Jane",
						"Janete", "Janice", "Janine", "Janize", "Jaqueline",
						"Jesse", "Joana", "Jocelen", "Josianni", "Josie",
						"Joyce", "Juliana", "Juliet", "Julieta", "Jurema",
						"Jussara", "Jéssica", "Júlia", "Júnia", "Kakashi",
						"Kaloã", "Karina", "Katherine", "Kaíque", "Kátia",
						"Laila", "Larissa", "Larisse", "Laura", "Lavínea",
						"Laís", "Laísa", "Leandra", "Leda", "Leila", "Leslie",
						"Leticia", "Letícia", "Lia", "Lianne", "Lidiane",
						"Ligya", "Lilian", "Liliana", "Liliane", "Linda",
						"Lisandra", "Lohanny", "Lorena", "Loreta", "Louise",
						"Lourdes", "Luana", "Luci", "Luciana", "Luciane",
						"Luciene", "Lucila", "Lucimara", "Lucineide", "Lucy",
						"Lucélia", "Ludmila", "Luise", "Lurdes", "Luzia",
						"Luísa", "Léia", "Lídia", "Lígia", "Lívia", "Lúcia",
						"Marcia", "Maria", "Mariana", "Mariane", "Marlise",
						"Marly", "Marnei", "Melina", "Mercia", "Michelle",
						"Milena", "Milla", "Monica", "Myriam", "Naum",
						"Nayara", "Nei", "Neil", "Odila", "Ofélia", "Olga",
						"Olímpia", "Olívia", "Oma", "Pace", "Padilha", "Paige",
						"Pakelekia", "Paki", "Palakika", "Palakine", "Palani",
						"Palapala", "Palapi", "Pali", "Pallas", "Palma",
						"Palmira", "Paloma", "Pamela", "Pamplona", "Panda",
						"Pandora", "Pane", "Paneki", "Pantéia", "Paola",
						"Paquetá", "Parigot", "Partênope", "Patience",
						"Patrícia", "Patsy", "Paula", "Paulete", "Pauline",
						"Pavlova", "Pavuna", "Peakalika", "Pearl", "Pecúnia",
						"Peddy", "Pedrina", "Peggy", "Pei", "Peike", "Pelena",
						"Pelike", "Penha", "Penélope", "Pepita", "Percy",
						"Perdita", "Peres", "Peresia", "Perez", "Peri",
						"Periclésia", "Perla", "Perpétua", "Perseveranda",
						"Perséia", "Petrônia", "Peônia", "Piatã", "Piedade",
						"Piera", "Pierina", "Pilar", "Pina", "Pirene",
						"Placídia", "Plácida", "Pocema", "Poguira", "Poliana",
						"Policasta", "Policena", "Polikia", "Polixene",
						"Pololena", "Pololika", "Polímela", "Polínea",
						"Pomona", "Pompéia", "Poppy", "Poranga", "Porchat",
						"Porcina", "Potira", "Preciosa", "Prisca", "Priscila",
						"Prudência", "Puleleuha", "Pureza", "Pytia", "Pádua",
						"Pâmela", "Pércia", "Pérola", "Pênia", "Pítane",
						"Públia", "Quitéria", "Rafaela", "Raquel", "Raquele",
						"Raíssa", "Rebeca", "Regina", "Renata", "Rene", "Rita",
						"Roberta", "Rody", "Rosa", "Rosamaria", "Rosana",
						"Rosane", "Rosangela", "Rosani", "Rosália", "Roxana",
						"Ruth", "Sabrina", "Sandra", "Sayane", "Silvana",
						"Silvia", "Simone", "Solange", "Soraya", "Stella",
						"Sueli", "Suliana", "Susana", "Tainah", "Tassia",
						"Tatiana", "Tatiane", "Thais", "Valdete", "Valentina",
						"Valeria", "Valéria", "Vanda", "Vanessa", "Vanusa",
						"Vera", "Veridiana", "Verônica", "Vilma", "Vitória",
						"Vivian", "Viviana", "Viviane", "Vânia", "Yannes",
						"Yorranê", "Yves", "Zabetta", "Zenaide", "Zilda",
						"Zoraide", "Zuleica", "Zulmira", "Zélia", "Ágatha",
						"Áurea", "Ângela", "Íris", "Ísis",

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Female name in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class FemaleNameEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "Nevada", "Teegan", "Venus", "Dominique",
						"India", "Halee", "Ayanna", "Risa", "Abigail",
						"Cameran", "Kyra", "Remedios", "Madison", "Callie",
						"Idola", "Patience", "Ciara", "Hanae", "Fiona",
						"Natalie", "Renee", "Oprah", "Illana", "Priscilla",
						"Karly", "Libby", "Leila", "Barbara", "Ariana",
						"Martha", "Regina", "Yoshi", "Lee", "Aline",
						"Hyacinth", "Rosalyn", "Hillary", "Jolie", "Maia",
						"Macy", "Cameron", "Cara", "Claire", "Erin", "Kirby",
						"Vera", "Julie", "Shoshana", "Daphne", "Carly",
						"Mechelle", "Wanda", "Jana", "Beverly", "Willow",
						"Kiona", "Chanda", "Lois", "Francesca", "Guinevere",
						"Wendy", "Nerea", "Yetta", "Laurel", "Angela", "Cleo",
						"Quynn", "Xantha", "Olga", "Neve", "Jaquelyn",
						"Lillian", "Ulla", "Lucy", "Dai", "Brianna", "Maile",
						"Farrah", "Leigh", "Ima", "Camilla", "Bertha",
						"Kylynn", "Evelyn", "Jamalia", "Amity", "Darrel",
						"Alexis", "Reagan", "MacKenzie", "Holly", "Heidi",
						"Avye", "Sigourney", "Mikayla", "Xandra", "Jennifer",
						"Stella", "Sandra", "Blossom", "Amela", "Tasha",
						"Cassandra", "Cheryl", "Abra", "Josephine", "Paula",
						"Nell", "Lani", "Joy", "Ocean", "Aurelia", "Nyssa",
						"Hilda", "Cecilia", "Quintessa", "Daria", "Sasha",
						"Miriam", "Noelani", "Daryl", "Chiquita", "Meghan",
						"Dawn", "Laura", "Catherine", "Ivana", "Shelby",
						"Leilani", "Kirsten", "Hedy", "Alfreda", "Deborah",
						"Nora", "Winifred", "Geraldine", "Jade", "Gillian",
						"Kameko", "Chava", "Lael", "Galena", "Tanya", "Cora",
						"Adele", "Jayme", "Jeanette", "Jaden", "McKenzie",
						"Madeline", "Serena", "Jillian", "Charde", "Amber",
						"Inez", "Vanna", "Blythe", "Doris", "Delilah",
						"Kristen", "Odessa", "Bo", "Alana", "Maggie",
						"Germane", "Alexandra", "Amena", "Piper", "Dahlia",
						"Cheyenne", "Maxine", "Rebecca", "Justine", "Yael",
						"Debra", "Chelsea", "Camille", "Eugenia", "Jocelyn",
						"Molly", "Quon", "Adara", "Zoe", "Freya", "Lila",
						"Nadine", "Orli", "Zephr", "Portia", "Melyssa",
						"Penelope", "Rhona", "Velma", "Aspen", "Lacy", "Naida",
						"Nola", "Kimberly", "TaShya", "Moana", "Jorden",
						"Erica", "Allegra", "Yvette", "Yeo", "Deirdre",
						"Jessamine", "Alice", "Ifeoma", "Calista", "Heather",
						"Rylee", "Dora", "Angelica", "Gwendolyn", "Kim",
						"Ruby", "Regan", "Quemby", "Odette", "Haley", "Alika",
						"Sonya", "Destiny", "Karen", "Lillith", "Gretchen",
						"Hilary", "Veda", "Maisie", "Justina", "Keely",
						"Astra", "Juliet", "Whoopi", "Harriet", "Kai", "Rose",
						"Belle", "Christen", "Jasmine", "Irene", "Ria",
						"Anastasia", "Britanney", "Isadora", "Shafira",
						"Eleanor", "Sara", "Raya", "Ginger", "Roanna", "Myra",
						"Elaine", "Victoria", "Anika", "Halla", "Dacey",
						"Lacey", "Kylie", "Georgia", "Ori", "Olympia",
						"Yvonne", "Octavia", "Jaime", "Kimberley", "Courtney",
						"Emerald", "Sarah", "Gemma", "Quinn", "Xyla", "Bell",
						"Orla", "Kerry", "Jolene", "Nina", "Fleur", "Tara",
						"Joan", "Echo", "Candice", "Miranda", "MacKensie",
						"Kevyn", "Adena", "Sharon", "Athena", "Zenaida",
						"Aileen", "Shay", "Mia", "Aretha", "Minerva", "Tamara",
						"Kirestin", "Brielle", "Jada", "Dana", "Sacha",
						"Nelle", "Germaine", "Joelle", "Liberty", "Janna",
						"Fatima", "Karyn", "Savannah", "Mona", "Gail",
						"Stacey", "Vielka", "Mollie", "Ora", "Alyssa",
						"Kelsey", "Rhea", "Shannon", "Azalia", "Madaline",
						"Ingrid", "Noel", "Hollee", "Lacota", "Ainsley",
						"Brittany", "Eliana", "Mariam", "Lilah", "Lana",
						"Frances", "Hadley", "Carolyn", "Vivian", "Vivien",
						"Alisa", "Rebekah", "Rinah", "Jenna", "Cailin",
						"Cathleen", "Inga", "Emma", "Lesley", "Genevieve",
						"Indigo", "Chloe", "Summer", "Karleigh", "Chastity",
						"Cally", "Kiayada", "Flavia", "Elizabeth", "Nichole",
						"Kathleen", "Caryn", "Amy", "Cherokee", "Marcia",
						"Kay", "May", "Fay", "Latifah", "Faith", "Ann",
						"Colleen", "Whitney", "Phyllis", "Cynthia", "Shelley",
						"Tashya", "Adria", "Pamela", "Lisandra", "Candace",
						"Martina", "Sade", "Lynn", "Cassady", "Hannah",
						"Patricia", "Jena", "Rachel", "Tallulah", "Karina",
						"Ivy", "Desiree", "Yoko", "Marny", "Imani", "Emily",
						"Bree", "Bethany", "Kalia", "Xaviera", "Illiana",
						"Kyla", "Ina", "Ila", "Ramona", "Suki", "Fredericka",
						"Mallory", "Lydia", "Lysandra", "Margaret", "Dorothy",
						"Medge", "Hayfa", "Aiko", "Hedwig", "Gay", "Deanna",
						"Althea", "Serina", "Jessica", "Indira", "Quyn",
						"Breanna", "Pearl", "Petra", "Selma", "Hope", "Sylvia",
						"Jael", "Madeson", "Britanni", "Quail", "Kelly",
						"Brenda", "Willa", "Roary", "Melanie", "Tana", "Nita",
						"Melissa", "Haviva", "Beatrice", "Iris", "Aimee",
						"Aphrodite", "Keelie", "Jane", "Shana", "Alexa",
						"Maris", "Nicole", "Wynne", "Urielle", "Yuri",
						"Hermione", "Lareina", "Maryam", "Kaitlin", "Anjolie",
						"Aurora", "Kiara", "Zelda", "acqueline", "Charity",
						"Denise", "Stacy", "Carol", "Madonna", "Zenia",
						"Katell", "Teagan", "Charissa", "Bianca", "Mari",
						"Autumn", "Meredith", "Sonia", "Iliana", "Unity",
						"Darryl", "Rowan", "Michelle", "Audrey", "Brynn",
						"Hiroko", "Sierra", "Morgan", "Maya", "Clementine",
						"Eve", "Shaine", "Tatyana", "Scarlet", "Rina", "Kaye",
						"Montana", "Hanna", "September", "Sybill", "Brynne",
						"Kessie", "Diana", "April", "Leah", "Sydnee", "Lunea",
						"Sheila", "Naomi", "Kelsie", "Giselle", "Jemima",
						"Rama", "Zorita", "Melinda", "Susan", "Ashely",
						"Hedda", "Paloma", "Olivia", "Lenore", "Melodie",
						"Cassidy", "Grace" };

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * No gender name in Portuguese
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class NoGenderNamePortuguese implements Generator {

			@SuppressWarnings("synthetic-access")
			private Generator generators[] = new Generator[] {
					new MaleNamePortuguese(), new FemaleNamePortuguese() };

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				int generatorSelected = randomGenerator.nextInt(2);

				return this.generators[generatorSelected].getContent();
			}
		}

		/**
		 * No gender name in English
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class NoGenderNameEnglish implements Generator {

			@SuppressWarnings("synthetic-access")
			private Generator generators[] = new Generator[] {
					new MaleNameEnglish(), new FemaleNameEnglish() };

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				int generatorSelected = randomGenerator.nextInt(2);

				return this.generators[generatorSelected].getContent();
			}
		}

		/**
		 * Surname name in Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class SurnameInPortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "Abdala", "Abeck", "Abreu", "Acosta",
						"Adams", "Agne", "Agnes", "Agostini", "Aguiar",
						"Alberton", "Albiero", "Albring", "Alff", "Aliatti",
						"Allebrand", "Alles", "Almansa", "Almeida",
						"Altenhofen", "Altenhoven", "Altermann", "Altmaier",
						"Altmayer", "Altmeyer", "Alvarenga", "Alves", "Amadeu",
						"Amaral", "Américo", "Ames", "Amorim", "Anay",
						"Andrade", "Andreoli", "Andres", "Andrioli",
						"Andschau", "Angeli", "Annes", "Anschau", "Antes",
						"Anth", "Anton", "Antoniazzi", "Antunes", "Appel",
						"Araujo", "Arcaro", "Arend", "Arenhardt", "Arenhart",
						"Aretz", "Argenta", "Ariga", "Arnhold", "Arnholf",
						"Arnold", "Arnt", "Arthur", "Arthus", "Assis",
						"Assmann", "Assunção", "Augusto", "Auler", "Avila",
						"Avozani", "Azambuja", "Azevedo", "Bach", "Bacht",
						"Back", "Backendorf", "Backert", "Backes", "Baehr",
						"Bahn", "Bald", "Baldini", "Balensiefer", "Balestrem",
						"Ballus", "Bandeira", "Barbacori", "Barbara",
						"Barcellos", "Barckert", "Barden", "Bardt", "Baron",
						"Barreira", "Barreto", "Barros", "Barth", "Bartz",
						"Bartzen", "Barz", "Barzen", "Basch", "Basso",
						"Bastian", "Batista", "Batisti", "Battisti", "Bau",
						"Bauer", "Baumbach", "Baumgartem", "Baumgarten",
						"Baunbach", "Baunmgaertner", "Bays", "Bazanella",
						"Beal", "Beckendorf", "Becker", "Bedhung", "Behm",
						"Behnen", "Behrenz", "Beidaski", "Beilke", "Belem",
						"Belini", "Bello", "Bellon", "Belloni", "Bellony",
						"Beltrame", "Beluski", "Bemen", "Bencke", "Bender",
						"Bendlin", "Benedetti", "Benke", "Bentz", "Benvenuti",
						"Benzen", "Beppler", "Bergamini", "Berger", "Bergesch",
						"Bergi", "Bergmann", "Bernard", "Bernardi",
						"Bernhardi", "Bernini", "Beronek", "Berres", "Bersch",
						"Berté", "Berti", "Bertran", "Bervanger", "Bervian",
						"Bervig", "Berwanger", "Betemps", "Bettio", "Beumer",
						"Beuren", "Beurens", "Bezerra", "Bhil", "Bicudo",
						"Bidel", "Bieber", "Biefielasbi", "Bieger", "Biesdorf",
						"Bildhauer", "Bilhão", "Bimmer", "Binotto", "Bins",
						"Binsfeld", "Bionart", "Birck", "Birk", "Bischof",
						"Bischoff", "Bisi", "Bisolo", "Bitdinger",
						"Bitdingher", "Bitsch", "Bittenbender", "Bittencourt",
						"Bizze", "Blasi", "Blatt", "Blatten", "Blau", "Blaut",
						"Blauth", "Bleser", "Bley", "Bluemer", "Blum", "Blume",
						"Blümling", "Bochmm", "Bochnia", "Bock", "Bodner",
						"Boeni", "Boff", "Bogo", "Bogorni", "Bogorny", "Bohn",
						"Bohnen", "Bohnenberger", "Bohra", "Bohrer", "Boika",
						"Boldrini", "Bolkenhagen", "Boloni", "Bolsan", "Bolsi",
						"Bolzom", "Bona", "Bondan", "Borba", "Bordignon",
						"Bordim", "Borger", "Borges", "Borghetti", "Borini",
						"Born", "Bortolini", "Bortolotto", "Bortotuzzi",
						"Borttot", "Bosembecker", "Bossan", "Botevine", "Both",
						"Boufleuer", "Bourcheit", "Bourscheid", "Bozzetto",
						"Braescher", "Braga", "Brancalone", "Brand",
						"Brandalise", "Brandelero", "Branderburger", "Brandt",
						"Bratz", "Braun", "Breit", "Breitbach", "Bremm",
						"Bressler", "Breuer", "Briçologo", "Brignoni", "Brill",
						"Brisch", "Britz", "Brix", "Brixius", "Brochier",
						"Brocinslei", "Brod", "Brogliato", "Broza",
						"Bruckeimer", "Bruismann", "Brum", "Brustolin",
						"Bruxel", "Bubans", "Buch", "Buchmann", "Buelke",
						"Bueno", "Bugs", "Bule", "Burg", "Burger", "Buss",
						"Butzen", "Butzkies", "Buus", "Bügelmeier", "Bün",
						"Bündtges", "Büttdinger", "Bäck", "Böck", "Bönmann",
						"Cabanellos", "Caberlon", "Cabral", "Cabrera",
						"Caderno", "Cagol", "Caio", "Calazans", "Calcagno",
						"Caldartt", "Caldierro", "Calegaro", "Campos", "Canan",
						"Candalialta", "Caneressig", "Canova", "Canto",
						"Capeletti", "Capella", "Capellet", "Carazzo",
						"Carbone", "Cardinal", "Cardoso", "Caré", "Cargnin",
						"Carneiro", "Carnin", "Caron", "Carrad", "Carradt",
						"Carvalho", "Casagrandi", "Casarotto", "Casgnin",
						"Castanho", "Castilho", "Castilhos", "Castro",
						"Catharina", "Cavalcanti", "Cavalheiro", "Cavalli",
						"Caye", "Cecatto", "Cecchin", "Cechet", "Ceolato",
						"Ceres", "Cerutti", "Chapóis", "Chardong", "Chassot",
						"Chaves", "Cheveste", "Chiarelloto", "Chisler",
						"Christ", "Christensen", "Cidade", "Ciulla",
						"Clasmann", "Clering", "Clos", "Closs", "Có", "Coelho",
						"Coimbra", "Colavin", "Colle", "Collet", "Colpo",
						"Comis", "Conceição", "Conrad", "Conti", "Cordeiro",
						"Cordenurisé", "Corneau", "Cornelli", "Coronel",
						"Correa", "Corso", "Costa", "Costacurta", "Costela",
						"Couto", "Cremer", "Creutz", "Cristmann", "Cruz",
						"Cunha", "Cyrino", "Czernichoviski", "Cölsch",
						"Cölsen", "Dahm", "Dahmer", "Dal", "Dalagustin",
						"Dalara", "Dalastra", "Dalci", "Dalcin", "Dall",
						"Dall'agnol", "Dallastra", "Dalle", "Dalmolin",
						"Dambacher", "Damm", "Damo", "Danda", "Dantas",
						"Dapper", "Dariz", "Daromch", "Daufenbach", "David",
						"De-lai", "Debom", "Decker", "Deimling", "Delazeri",
						"Delwing", "Demaman", "Denga", "Dente", "Deon",
						"Deotti", "Dequi", "Dequigiovanni", "Dertzbacher",
						"Desordi", "Dessoy", "Dewes", "Dias", "Diba", "Dick",
						"Didio", "Didios", "Diedrich", "Diehl", "Diel",
						"Dieler", "Dierins", "Diesel", "Dietrich",
						"Diggelmann", "Dihel", "Dihl", "Dihler", "Dill",
						"Dillenburg", "Dillmann", "Dilly", "Dirings",
						"Dittrich", "Dockhorn", "Doico", "Doillier", "Donadel",
						"Donicht", "Dorfei", "Dorfer", "Dorfey", "Dorneles",
						"Dornelles", "Drebes", "Drehmer", "Drei", "Dresch",
						"Drescher", "Dressler", "Drews", "Drhemer", "Dries",
						"Drump", "Duarte", "Dullius", "Dupond", "Dupont",
						"Durante", "Dutra", "Dämgen", "Däum", "Dörsbach",
						"Eberharth", "Ebert", "Ebling", "Echer", "Eckert",
						"Eckstein", "Ehle", "Eickhoff", "Eidelwein", "Eidt",
						"Eifler", "Eisenhat", "Eisenhut", "Ejiri", "Eli",
						"Elkoz", "Ellmanger", "Elsenbach", "Ely", "Endler",
						"Engel", "Engerhoff", "Engster", "Eninger",
						"Enzuceiler", "Erhart", "Erkert", "Erlart", "Ertel",
						"Ertz", "Esau", "Escarcell", "Escher", "Espindola",
						"Essig", "Esswein", "Estefani", "Ewald", "Faber",
						"Facchi", "Facchini", "Fachim", "Fachin", "Fachini",
						"Fagundes", "Fallas", "Fank", "Fão", "Faoro", "Faria",
						"Farias", "Farina", "Fasbender", "Faustini",
						"Faustino", "Federhen", "Feiden", "Feil", "Feiten",
						"Feldens", "Feldes", "Feldkürch", "Feldmann",
						"Feliciolli", "Felimberti", "Fell", "Fellens",
						"Felter", "Fenoi", "Ferigo", "Ferla", "Fernes",
						"Ferrão", "Ferrari", "Ferreira", "Ferronato", "Fey",
						"Feyerherd", "Ffoik", "Fiel", "Figueiro", "Filzem",
						"Filzen", "Finger", "Fink", "Finkler", "Fischer",
						"Flach", "Fleck", "Fleuthe", "Flôres", "Florian",
						"Flukseder", "Foisin", "Follmann", "Folmann",
						"Folvais", "Fonseca", "Fontana", "Fontes", "Fontin",
						"Formighieri", "Forster", "Forstner", "Fortes",
						"Fortunato", "Foschera", "Franchini", "Francken",
						"Franco", "Frank", "Franken", "Frantz", "Franz",
						"Franzen", "Frau", "Frederes", "Fredo", "Fredrich",
						"Frei", "Freiberger", "Freire", "Freitag", "Freitas",
						"Frey", "Fricker", "Friederich", "Friedrich", "Fries",
						"Fritsch", "Fritz", "Fritzen", "Friz", "Froezer",
						"Froney", "Fryder", "Fröhlich", "Fröner", "Fuchs",
						"Fucks", "Funai", "Funk", "Furtado", "Fusiger",
						"Fusinger", "Fussinger", "Führ", "Führmeister",
						"Fülber", "Fyling", "Gabbi", "Gabriel", "Gabrielli",
						"Gaersting", "Galando", "Galdera", "Galetto",
						"Galiano", "Gallas", "Galle", "Ganzer", "Garbini",
						"Garcia", "Gargnin", "Garibotti", "Gariz", "Gassen",
						"Gassner", "Gauer", "Gazzoni", "Gebert", "Gehlen",
						"Geiger", "Geller", "Gellner", "Gemerich", "Genehr",
						"Gerhadt", "Gerhardt", "Gerher", "Gerhod", "Gerloff",
						"Germano", "Geszner", "Geulette", "Giacomelli",
						"Giacometi", "Gianela", "Gibbert", "Gibert", "Giehl",
						"Gielzmann", "Gilbert", "Giongo", "Giovanella",
						"Giuliani", "Glabb", "Glaeser", "Glassmann", "Glinka",
						"Gobbi", "Godoy", "Gogen", "Goldschmidt",
						"Goldschmitt", "Golfetto", "Goltz", "Gomes",
						"Gonçalves", "Gonchoroski", "Gonzalez", "Gonzatti",
						"Goreis", "Gorgen", "Gosler", "Gossler", "Gotchot",
						"Gouvea", "Grackhecke", "Graebin", "Graeff",
						"Grafender", "Grahl", "Grance", "Grando", "Graner",
						"Grasel", "Grebos", "Gregori", "Gregory", "Gregoy",
						"Grenler", "Griebel", "Griebeler", "Griebler",
						"Grifanti", "Grimm", "Gringes", "Groff", "Grohmann",
						"Groman", "Gronistki", "Gross", "Grub", "Grümer",
						"Grütmacher", "Grützmacher", "Gräf", "Gräff", "Gueler",
						"Guerra", "Guerreiro", "Guidotti", "Guies",
						"Guimarães", "Gusmão", "Gut", "Guth", "Gutieres",
						"Gutterres", "Gwiggner", "Gölzer", "Gören", "Görgen",
						"Göttems", "Haas", "Habitzreiter", "Hacbardt",
						"Hackbart", "Hackbarth", "Haefliger", "Haenssgen",
						"Hagedorn", "Hahn", "Halbech", "Halberstadt",
						"Halmenschlager", "Halmenschlanger", "Hambrücken",
						"Hamerchlanger", "Hammerschmitt", "Hammes",
						"Hammester", "Hanauer", "Hann", "Hans", "Hansen",
						"Hantt", "Hanuer", "Hart", "Hartamnn", "Hartmann",
						"Harttmem", "Hartwig", "Hass", "Hassmann",
						"Hasstenteufel", "Hastenplug", "Haubert", "Haupenthal",
						"Hauppentahl", "Haupt", "Hauschild", "Hebel",
						"Heberle", "Heck", "Heckler", "Heil", "Hein", "Heine",
						"Heineck", "Heinen", "Heinrich", "Heinrichs", "Heintz",
						"Heinz", "Heinze", "Heisler", "Hekler", "Hemrichs",
						"Hemsing", "Hendges", "Henemann", "Henkes", "Henn",
						"Hennicka", "Hennika", "Henrich", "Hensel", "Hentz",
						"Henz", "Heofel", "Hepp", "Herbele", "Herber",
						"Herich", "Herkert", "Herman", "Hermann", "Hermes",
						"Herrmann", "Hertes", "Herzer", "Hess", "Hickmann",
						"Hidalgo", "Hilgert", "Hillesheim", "Hindersmann",
						"Hinterholz", "Hirsch", "Hister", "Hober", "Hobuss",
						"Hochscheidt", "Hochsheid", "Hofer", "Hoff",
						"Hoffmann", "Hoffmeister", "Hoffstetter",
						"Hoffstätter", "Hofmeister", "Hofstetter", "Hohn",
						"Holdefer", "Holstetter", "Holten", "Holz",
						"Holzschuh", "Horbach", "Horn", "Horster", "Horz",
						"Hostele", "Hostetter", "Howmerschmitt", "Hubert",
						"Hulten", "Hunger", "Hunhoff", "Hunning", "Huppes",
						"Hübling", "Hübner", "Hülhesheim", "Hüpp", "Hähn",
						"Hänsel", "Hässler", "Häussler", "Höchst", "Hölcher",
						"Hölten", "Hölzen", "Iawall", "Ihme", "Ilgenfritz",
						"Illenfeld", "Imig", "Inacio", "Irber", "Jacob",
						"Jacobbi", "Jacobi", "Jacobs", "Jacoby", "Jado",
						"Jaeger", "Jager", "Jagmiu", "Jahn", "Jais",
						"Jalowitzke", "Jantsch", "Jappe", "Jardim", "Jaworski",
						"Jeckel", "Jerke", "Joahann", "Job", "Johann", "John",
						"Johner", "Joner", "Jordan", "Jorge", "Jost", "Jotz",
						"Juchem", "Jucksk", "Juljano", "Jung", "Jungbbet",
						"Jungblut", "Junges", "Just", "Justen", "Juver",
						"Jäger", "Kaefer", "Kahler", "Kaiser", "Kalb",
						"Kamphorst", "Kappaun", "Kappel", "Kappes", "Kappler",
						"Karkow", "Karling", "Karvatte", "Kaskow", "Kaspary",
						"Kasper", "Kauffmann", "Kaufmann", "Kaus", "Kayser",
						"Keiser", "Kehl", "Kehnlein", "Keiber", "Keil", "Kein",
						"Keler", "Keller", "Kelm", "Kelsch", "Kemmer", "Kemp",
						"Kemper", "Kempf", "Kenh", "Kepplinger", "Kerber",
						"Kerkhoff", "Kerkof", "Kerkoff", "Kern", "Kerpen",
						"Kerz", "Kessler", "Ketszer", "Kettel", "Ketzer",
						"Kever", "Kick", "Kidichimo", "Kilian", "Killing",
						"Kilser", "Kindges", "Kinsel", "Kintschner", "Kinzel",
						"Kipper", "Kippert", "Kirch", "Kirchiam", "Kirich",
						"Kirsch", "Kirst", "Kisch", "Kisner", "Kist", "Klab",
						"Klaf", "Klauch", "Klauck", "Klaus", "Klecsazeck",
						"Klein", "Kleinübing", "Klering", "Kliemun", "Klingel",
						"Klingels", "Klipel", "Klippel", "Klock", "Kloeckner",
						"Klopp", "Klos", "Klung", "Klunk", "Klunzener",
						"Knapp", "Knecht", "Kneip", "Knickel", "Knieling",
						"Knippel", "Knob", "Knolo", "Knop", "Knopp", "Knorst",
						"Kober", "Koch", "Kochan", "Kochem", "Kochhann",
						"Koffmann", "Kohls", "Kohlund", "Kohlung", "Koldehoff",
						"Koling", "Kollet", "Kollimg", "Kolling", "Konrath",
						"Konzen", "Koop", "Kopp", "Korb", "Korier", "Korkhoff",
						"Korn", "Korneau", "Korte", "Kortuys", "Kortz",
						"Kosmann", "Koth", "Kotz", "Krabbe", "Kraemer",
						"Kramer", "Krantz", "Kratz", "Kraus", "Kreem", "Krein",
						"Kreisich", "Kremer", "Kremmer", "Kreutz", "Kreuz",
						"Kreuzbach", "Kreuzberg", "Krewer", "Krindges",
						"Kringes", "Krinkhes", "Kritzmann", "Kroeff", "Krolow",
						"Krombauer", "Kromnauer", "Kronbouer", "Krone",
						"Kronistki", "Kronitski", "Kroth", "Krug",
						"Krummenauer", "Krüger", "Krützke", "Krämer",
						"Krämeren", "Kröss", "Kubiak", "Kuhn", "Kulack",
						"Kullack", "Kullmann", "Kummer", "Kunbauer", "Kunrath",
						"Kunz", "Kunzler", "Kuritza", "Kurmann", "Külzer",
						"Kürch", "Käefer", "Käfer", "Kämpfer", "Köller",
						"Kölzer", "König", "Köpp", "Körbes", "Lachesk", "Lage",
						"Lahm", "Lamb", "Lamp", "Lana", "Lang", "Lange",
						"Langer", "Langner", "Lanius", "Larré", "Lasarotto",
						"Lau", "Lauderbach", "Lauer", "Lauermann", "Lauxen",
						"Lava", "Lavisch", "Lawal", "Lay", "Lazari",
						"Lazarotto", "Lazzari", "Leal", "Leandro", "Leão",
						"Ledur", "Lehnen", "Lehnhard", "Leichtveiss",
						"Leidens", "Leismann", "Leitão", "Leite", "Leitzke",
						"Lelling", "Lemes", "Lemos", "Lenhard", "Lenhardt",
						"Lenhart", "Lensk", "Lenverenz", "Lenz", "Leokowitz",
						"Lermen", "Lessa", "Lethim", "Lettur", "Leuckert",
						"Liberalesso", "Liberolesso", "Lichantre", "Licks",
						"Liell", "Liesenfeld", "Lieske", "Lima", "Limberger",
						"Limberguer", "Linck", "Linden", "Link", "Lippert",
						"Lisboa", "Loch", "Loebens", "Loeblein", "Loef",
						"Loewe", "Loft", "Longo", "Lopes", "Lorenz",
						"Lorenzzi", "Loro", "Lorscheider", "Lorscheiter",
						"Losekann", "Lourenço", "Lucas", "Lucini", "Ludvig",
						"Ludwig", "Luft", "Luiz", "Lunkes", "Lupatini", "Lutz",
						"Luvier", "Lux", "Luz", "Lüdke", "Lütz", "Lösch",
						"Macedo", "Machado", "Machry", "Maciel", "Macira",
						"Maders", "Madsen", "Maenhart", "Mafazioli", "Maganha",
						"Magni", "Mahl", "Mahoski", "Mai", "Maia", "Maicá",
						"Maier", "Mainele", "Mainhertz", "Majolo", "Maju",
						"Malacarne", "Maldaner", "Malke", "Mallmann",
						"Manfroi", "Manfroid", "Mânica", "Mank", "Mans",
						"Marangon", "Marcello", "Márcilio", "Marcilli",
						"Marcolla", "Marcon", "Marconcini", "Marello",
						"Marins", "Markwarth", "Marmit", "Marmita", "Marmitt",
						"Marquart", "Marques", "Marquez", "Marschalk",
						"Martin", "Martinelli", "Martinez", "Martini",
						"Martins", "Martoso", "Marx", "Mascarenhas", "Masiero",
						"Masochi", "Massig", "Massing", "Massirer", "Massom",
						"Masterlich", "Masz", "Matana", "Matens", "Mathias",
						"Matias", "Matielo", "Matos", "Mattana", "Matte",
						"Matter", "Mattes", "Mattge", "Matthes", "Mattje",
						"Mattjie", "Matto", "Mattos", "Matulle", "Matutte",
						"Mauer", "Maurer", "May", "Mayca", "Mayer", "Mays",
						"Mazardo", "Mazzotti", "Medina", "Medink", "Mees",
						"Meier", "Meinert", "Meinertz", "Meinerz", "Meirelles",
						"Meisterlin", "Mello", "Melo", "Melz", "Mendel",
						"Mendes", "Mendonça", "Mendzel", "Meneghetti",
						"Meneghini", "Meneguissi", "Menezes", "Meng",
						"Mentges", "Mentz", "Merckel", "Meregali", "Mergener",
						"Mertins", "Mesomo", "Mesquita", "Messer", "Metzem",
						"Metzen", "Metzke", "Meurer", "Meurers", "Meyer",
						"Michaely", "Michels", "Mickler", "Milanesi", "Milani",
						"Milbradt", "Milbrandt", "Milech", "Milek", "Milozak",
						"Minsky", "Miorando", "Miot", "Miranda", "Misome",
						"Mitri", "Mocelin", "Mocellin", "Modesto", "Moecke",
						"Moehlecke", "Moesch", "Mohr", "Mohrr", "Moleri",
						"Molina", "Mombach", "Monbach", "Monreal", "Montagna",
						"Monteiro", "Moraes", "Morais", "Morcelli", "Moreira",
						"Morello", "Moritz", "Morsch", "Morschbächer",
						"Mossmann", "Mossolini", "Motta", "Moura", "Mucksfell",
						"Mueller", "Muhamed", "Muler", "Munchen", "Mühl",
						"Müller", "Müllich", "München", "Mützel", "Mörs",
						"Möts", "Naldony", "Nardi", "Naschald", "Nascimento",
						"Nayssiger", "Neckel", "Nedel", "Nedochetko", "Neef",
						"Neitzke", "Nengelken", "Netto", "Neukamp", "Neumann",
						"Neumeyer", "Neuwald", "Neves", "Newmann", "Nezzi",
						"Nick", "Nicolana", "Nied", "Nieder", "Niedermayer",
						"Nielsen", "Niendecker", "Nienov", "Nienow", "Nilles",
						"Nilson", "Ninov", "Nitzchke", "Nizolli", "Nodari",
						"Nogueira", "Nonnemacher", "Nos", "Noss", "Nozari",
						"Nue", "Nunemacher", "Nunes", "Nöremberg", "Obadoski",
						"Oberger", "Obermaier", "Odilia", "Ody", "Olbermann",
						"Oliveira", "Onzi", "Opermann", "Oppermann", "Orbeu",
						"Oreste", "Oriques", "Orlandin", "Ortha", "Ortis",
						"Ortiz", "Osmari", "Osorio", "Ost", "Ostien", "Otaki",
						"Oth", "Ott", "Otto", "Ovedo", "Pack", "Padilha",
						"Pagliarin", "Paiva", "Panegali", "Panow", "Panta",
						"Paoli", "Parodi", "Patzlaff", "Paul", "Paula",
						"Pauli", "Paz", "Pazze", "Peçanha", "Pedralli",
						"Pedron", "Pedroso", "Pegoraro", "Peixoto", "Pelegrin",
						"Pelents", "Pelisser", "Pelisson", "Pelizzer",
						"Pellenz", "Pentz", "Penz", "Pereira", "Peres",
						"Pereyra", "Perius", "Perrios", "Perrotti", "Persch",
						"Peruzzo", "Peter", "Peteres", "Peters", "Peterson",
						"Petkof", "Petri", "Petrussi", "Petry", "Petter",
						"Petzold", "Pfeifer", "Philippsen", "Philipsen",
						"Pichler", "Pichtler", "Picini", "Piffen", "Pilau",
						"Piloni", "Pilz", "Pinheiro", "Pinto", "Pio", "Pioli",
						"Piovezan", "Pírius", "Piroth", "Pittol", "Pizzatto",
						"Plein", "Plentz", "Plestch", "Pletch", "Pletseh",
						"Pocahy", "Pocaí", "Pockorny", "Poersch", "Pohl",
						"Pohren", "Polachin", "Poletto", "Porcheid",
						"Porfirio", "Portela", "Porto", "Portz", "Posole",
						"Possibon", "Post", "Postay", "Potença", "Pozzobon",
						"Prade", "Prado", "Prass", "Praxedes", "Prediger",
						"Preis", "Preisler", "Preseziniak", "Prestes",
						"Presznuk", "Pretzel", "Pretzold", "Preuss",
						"Preussler", "Probeck", "Proença", "Protzen", "Puff",
						"Puhl", "Puiati", "Pulz", "Pütz", "Pösing", "Queiroz",
						"Quevedo", "Rabuske", "Rabuski", "Racht", "Raddetz",
						"Radke", "Raimann", "Rambo", "Ramires", "Ramos",
						"Rampazzo", "Rangel", "Ranzi", "Rasch", "Rauber",
						"Rauch", "Raupt", "Raya", "Reali", "Rech",
						"Recktenwald", "Reckziegel", "Redü", "Regner",
						"Rehbein", "Reichert", "Reichhart", "Reichow", "Reil",
						"Reinbüchler", "Reindl", "Reineld", "Reinelt",
						"Reinher", "Reis", "Reisner", "Reistörfer", "Reiter",
						"Reitzke", "Remier", "Remmel", "Remonti", "Rempel",
						"Renner", "Renz", "Repke", "Reser", "Retz", "Retzmann",
						"Reus", "Reuter", "Rezzadori", "Rhoden", "Ribeiro",
						"Richardt", "Richter", "Riechel", "Ried", "Riedmann",
						"Ries", "Rieth", "Rifel", "Riffel", "Rigo", "Rigoli",
						"Rinka", "Rinke", "Ripp", "Rissi", "Ritt", "Ritter",
						"Rivelini", "Roberti", "Roberto", "Rocha",
						"Rockembach", "Rockenbach", "Rockenback", "Roden",
						"Rodenberger", "Rodermann", "Rodhe", "Rodhen",
						"Rodiske", "Rodrigues", "Rodriguez", "Rohde",
						"Rohenkohl", "Roher", "Rohr", "Rojas", "Rolf", "Rolim",
						"Roll", "Roller", "Romeiro", "Ronson", "Roos", "Ros",
						"Rosa", "Rosenbach", "Rosenstengel", "Rosin",
						"Rossetto", "Rossi", "Rothenbucher", "Rothenbücher",
						"Roweder", "Royer", "Rozek", "Ruara", "Ruberti",
						"Ruppel", "Ruppenthal", "Rusch", "Rybicki", "Rübinich",
						"Rüchel", "Rüdell", "Rüvenich", "Röhsing", "Röser",
						"Rössler", "Sá", "Saibet", "Salamo", "Sales", "Salet",
						"Salgueiro", "Salim", "Saling", "Salm", "Sander",
						"Sangalli", "Santi", "Santos", "Sarraf", "Sartoretto",
						"Sartori", "Sartório", "Sass", "Sattler", "Sauer",
						"Saueressig", "Saurem", "Sausen", "Savi", "Scaider",
						"Scapin", "Scariot", "Scatolem", "Schaab", "Schaak",
						"Schabarum", "Schaedler", "Schaefer", "Schaeffer",
						"Schalemberger", "Schalfer", "Schallemberger",
						"Schardong", "Scharf", "Scharnoski", "Schauren",
						"Scheck", "Scheeren", "Scheibe", "Scheibel",
						"Scheibler", "Scheich", "Scheid", "Scheidler",
						"Schein", "Scheld", "Schell", "Scherer", "Scherner",
						"Scherr", "Schesner", "Scheuermann", "Schewa",
						"Schiefer", "Schiel", "Schier", "Schimanki", "Schimdt",
						"Schinen", "Schleich", "Schleicher", "Schleif",
						"Schlindvein", "Schlindwein", "Schmaedecker",
						"Schmall", "Schmidt", "Schmitt", "Schmitz", "Schmn",
						"Schmoll", "Schmädeke", "Schnapp", "Schneider",
						"Schnmidt", "Schnoengber", "Schnornberger", "Schnorr",
						"Schoenhals", "Schoessler", "Schoffen", "Scholl",
						"Scholze", "Schommer", "Schons", "Schorr", "Schossler",
						"Schoveng", "Schramm", "Schran", "Schreiner",
						"Schtreich", "Schuch", "Schuck", "Schuer", "Schuh",
						"Schuk", "Schul", "Schulten", "Schulz", "Schumacher",
						"Schuster", "Schvatzer", "Schwaab", "Schwahn",
						"Schwan", "Schwantes", "Schwartz", "Schwarzbold",
						"Schweitzer", "Schwendler", "Schwengber", "Schwert",
						"Schwertner", "Schwertz", "Schwickert", "Schwärz",
						"Schültz", "Schümer", "Schütz", "Schüzler",
						"Schzengber", "Schäefer", "Schäfer", "Schäffer",
						"Schölz", "Schönhals", "Schönkein", "Schöns",
						"Schöntag", "Schössler", "Sebastiane", "Sebastiany",
						"Sebastyany", "Sebben", "Sedrikoski", "Seewald",
						"Seffeu", "Segatto", "Sehnem", "Seibel", "Seibert",
						"Seibt", "Seidel", "Seidl", "Seimetz", "Selbach",
						"Selben", "Selig", "Sell", "Selzler", "Sembay",
						"Senartovicz", "Senden", "Senna", "Sequi", "Serafini",
						"Serdan", "Serppe", "Sertóri", "Servazy", "Serwasi",
						"Seus", "Severo", "Sheeren", "Shinen", "Siebeneichler",
						"Signor", "Signorini", "Silva", "Silveira",
						"Silvestrin", "Simão", "Simmelink", "Simmern", "Simon",
						"Simonis", "Simplício", "Simsen", "Siqueira",
						"Siveres", "Siveris", "Sliva", "Slongo", "Smaniotto",
						"Smith", "Smitz", "Soares", "Soeth", "Solivo",
						"Sommer", "Sonalle", "Sorayre", "Sot", "Sott", "Sousa",
						"Souto", "Souza", "Sova", "Spaniol", "Sparrenberger",
						"Specht", "Sperafico", "Spieckermann", "Spier",
						"Spies", "Spitalieri", "Spohn", "Spohr", "Spöhr",
						"Staeler", "Stahl", "Stam", "Stamm", "Staniecki",
						"Starich", "Staudt", "Steckel", "Stefanello",
						"Steffen", "Steffens", "Steffer", "Steffler",
						"Steigleder", "Stein", "Steinmetz", "Steinmtz",
						"Stephan", "Sterz", "Stocker", "Stockman", "Stoffel",
						"Stoll", "Strack", "Strass", "Straus", "Strauss",
						"Streck", "Strehl", "Streit", "Strider", "Strieder",
						"Stroher", "Strom", "Strümer", "Ströher", "Studer",
						"Stuelp", "Stukzinski", "Stumm", "Sturm", "Stülp",
						"Stümer", "Stürmer", "Stöelben", "Stölben", "Sulzbach",
						"Suptitz", "Susen", "Suski", "Svoboda", "Swarowsky",
						"Sänger", "Tacchele", "Tagleber", "Taglieber",
						"Takeyuti", "Talavera", "Tâmbara", "Tambke", "Tamimi",
						"Tamioso", "Tamiozzo", "Tarouco", "Tasca", "Tastch",
						"Taufer", "Tavares", "Tavela", "Tecatten", "Teixeira",
						"Teka", "Teloecken", "Teloeken", "Telöken", "Tencaten",
						"Tenojgaten", "Tenroller", "Teres", "Teresa",
						"Terhorst", "Terhost", "Tessmer", "Thais", "Thal",
						"Theisen", "Theobald", "Thess", "Thewe", "Thewes",
						"Thiel", "Thiell", "Thielmann", "Thiesen", "Thoma",
						"Thomas", "Thomaz", "Thomé", "Thonnigs", "Thum",
						"Tiemamm", "Tietze", "Tilivitz", "Tillmann", "Timm",
						"Tirelli", "Tischer", "Toderke", "Toebes", "Toffano",
						"Toigo", "Toillier", "Tomm", "Tonet", "Tonett",
						"Tonolli", "Tormen", "Tormes", "Toss", "Tossi",
						"Tozzeto", "Trami", "Trebien", "Treiber", "Treichel",
						"Treier", "Trejes", "Trennenpohl", "Tres", "Tresch",
						"Tressoldi", "Triacca", "Trisoldi", "Tritsch",
						"Träsel", "Tuclhinovicz", "Tullios", "Turati",
						"Turatti", "Ulmann", "Ulrich", "Ulsenheimer", "Unser",
						"Urmann", "Urnau", "Ustraus", "Utzich", "Vaccari",
						"Valerius", "Valgarengue", "Vanger", "Vargas",
						"Vasques", "Vater", "Veit", "Velho", "Veltens",
						"Velter", "Venning", "Ventura", "Venturini",
						"Verkoyen", "Vermuth", "Verner", "Vettorello",
						"Vezaro", "Viana", "Vieira", "Vielmond", "Vigna",
						"Vilgen", "Villa", "Villarinho", "Vitecker",
						"Vitorassi", "Vocht", "Vogel", "Vogt", "Voking",
						"Volken", "Volkmer", "Volkweis", "Vollmann", "Volz",
						"Vorga", "Voss", "Voszyl", "Wagner", "Wailand",
						"Waldgrün", "Walter", "Wames", "Wammes", "Wander",
						"Warken", "Waschleuger", "Wassmuth", "Wazur", "Webber",
						"Weber", "Webers", "Webler", "Weiand", "Weiland",
						"Weiler", "Weirich", "Weirichi", "Weise", "Weiser",
						"Weiss", "Weissmann", "Weith", "Weizenmann", "Welchem",
						"Welchen", "Wele", "Welhen", "Welter", "Weltes",
						"Wenchenfelder", "Wendling", "Wendt", "Wener", "Wentz",
						"Werberich", "Werlang", "Werlank", "Werle", "Wermann",
						"Wermingof", "Wermuth", "Werne", "Werner",
						"Weschenfelder", "Weter", "Wetral", "Weyh", "Wickbold",
						"Wickert", "Widawer", "Wiedercker", "Wiederkher",
						"Wienandg", "Wierschem", "Wies", "Wiest", "Wildner",
						"Wilhelm", "Wilhem", "Wilhems", "Wilke", "Willalba",
						"Willberth", "Wille", "Willers", "Willes", "Willig",
						"Willinborg", "Willwerth", "Wiltgen", "Wiltzen",
						"Winck", "Windheuser", "Wingert", "Wink", "Winter",
						"Wirsch", "Wirth", "Witke", "Wittke", "Wognag",
						"Woizicki", "Wojcicki", "Wojzicki", "Wolf", "Wolfardt",
						"Wolfart", "Wolfarth", "Wolfrart", "Wollmann",
						"Wollmeister", "Worm", "Worst", "Woycishowski",
						"Wuitschick", "Wust", "Wäschenfelder", "Wöhler",
						"Xavier", "Yung", "Zafalon", "Zahrt", "Zalandros",
						"Zambarda", "Zambiasi", "Zambon", "Zanatta",
						"Zanchettin", "Zandoná", "Zandonai", "Zanella", "Zang",
						"Zanotteli", "Zaro", "Zart", "Zarth", "Zavaris",
						"Zeleda", "Zenkner", "Zerwes", "Zignani", "Zilke",
						"Zilles", "Zilmer", "Zima", "Zimmer", "Zimmermann",
						"Zingheim", "Zirmer", "Zitterman", "Zoaritz", "Zonin",
						"Zorzi", "Zuniga", "Zvirtes", "Zvirtez", "Abramides",
						"Afeitos", "Alanis", "Alberio", "Alcalay", "Almada",
						"Amadei", "Amancio", "Amarante", "Amorosino", "Angelo",
						"Anjos", "Anselmo", "Aparicio", "Apis", "Apoliano",
						"Apolinario", "Aquino", "Arancibia", "Arantes",
						"Arendt", "Arevalo", "Armando", "Avendanha", "Bacelo",
						"Bairao", "Baptista", "Baraquet", "Barata", "Barbosa",
						"Barioni", "Barreta", "Barroso", "Basetto", "Bassi",
						"Basu", "Bechuetti", "Belgine", "Belucci", "Bem",
						"Bernardeli", "Bernardes", "Bertanha", "Betencourt",
						"Biagiotti", "Bianchi", "Bignotto", "Birnfeld",
						"Bittar", "Bitton", "Blanco", "Blandy", "Bobadilha",
						"Bocian", "Bones", "Bosco", "Boullosa", "Boyde",
						"Brayer", "Braz", "Brito", "Bucaleto", "Burock",
						"Cafe", "Caldas", "Caldeira", "Calderan",
						"Calegaretti", "Calixto", "Camara", "Camargo",
						"Camargos", "Camillo", "Campagna", "Campolina",
						"Campovilla", "Canario", "Cantanhede", "Capovilla",
						"Cardin", "Carolino", "Catani", "Cavaleri", "Ceccato",
						"Ceccon", "Ceolin", "Cerdeira", "Chiarelli",
						"Chicherchio", "Cipolla", "Claude", "Claudino",
						"Clemente", "Coca", "Consolini", "Correia", "Coutinho",
						"Crisanto", "Cristia", "Da", "Dafonseca", "Dagostini",
						"Dainez", "Danelli", "Daniel", "Das", "De", "Debiagi",
						"Debs", "Defavari", "Degasperi", "Delayti", "Dellega",
						"Deluca", "Diniz", "Do", "Domingos", "Donati", "Dos",
						"Drummond", "Drumond", "Duck", "Duque", "Eskopinski",
						"Esmiralha", "Espirito", "Eyras", "Fabiano", "Fabri",
						"Fadini", "Falcao", "Fatima", "Favero", "Felipo",
						"Fernandes", "Ferracini", "Figueiredo", "Filho",
						"Fiorini", "Flores", "Fogaca", "Fontoura", "Foresti",
						"Franca", "Francisco", "Francoti", "Fregolente",
						"Froehlich", "Fujii", "Fukugauti", "Furlaneto",
						"Fuzinato", "Gabler", "Galdino", "Galvani", "Gandra",
						"Gappo", "Garzon", "Gasiglia", "Georges", "Gesmundo",
						"Giacometti", "Gialdi", "Gil", "Giordana", "Giovanni",
						"Giraldi", "Girdwood", "Giuseppe", "Godoi",
						"Godolphim", "Gonalves", "Goncalves", "Gondo",
						"Gonzalespmacedo", "Graf", "Granzotto", "Guajardo",
						"Guedes", "Guimaraes", "Gusmao", "Gusso", "Gusson",
						"Hamilton", "Hashimoto", "Henrique", "Hid", "Husni",
						"Iani", "Innecco", "Intaschi", "Itami", "Ito",
						"Jacomel", "Jambwisch", "Jaqueira", "Jesus", "Junior",
						"Junqueira", "Kamioji", "Kater", "Kawakami",
						"Kazitoris", "Kerr", "Klaskala", "Koike", "Kruger",
						"Kuratomi", "Lacerda", "Lafaurie", "Lazarin",
						"Lazarini", "Leao", "Leitao", "Leoncy", "Leouhart",
						"Limeira", "Linhares", "Loregian", "Loreto",
						"Lourenco", "Lucca", "Lucena", "Lucia", "Lustosa",
						"Magalhaes", "Mantovani", "Maran", "Marcelino",
						"Marco", "Mariano", "Marinho", "Marion", "Marra",
						"Marson", "Martines", "Marzola", "Massunaga",
						"Mayrink", "Medeiros", "Meira", "Menendes", "Menon",
						"Messias", "Migon", "Miguel", "Milanez", "Minervini",
						"Miyagi", "Molteni", "Monaco", "Montilha", "Moribe",
						"Moro", "Mota", "Muhana", "Muhlen", "Murr", "Nallo",
						"Nardo", "Navarro", "Neto", "Nobrega", "Nori", "Norte",
						"Novello", "Ogawa", "Okubo", "Ortega", "Pache",
						"Pacheco", "Padula", "Paes", "Paganelo", "Parente",
						"Parisotto", "Parreira", "Pavarin", "Pedrosa", "Pena",
						"Pentian", "Perini", "Periotto", "Perlatti", "Perusso",
						"Pettersen", "Piantoni", "Piccirilo", "Pires", "Piske",
						"Polla", "Ponteado", "Pontes", "Poppe", "Pozza",
						"Prada", "Processi", "Pudenzi", "Quintella",
						"Raghiante", "Raimondi", "Ramalho", "Raposo",
						"Reinehr", "Retamal", "Rezende", "Ricci", "Rivera",
						"Roda", "Romero", "Rua", "Rubio", "Ruffato", "Rustice",
						"Ruys", "Sa", "Saldanha", "Salem", "Salloume",
						"Salomao", "Salviato", "Samra", "Sanches", "Sanchez",
						"Santana", "Santis", "Santo", "Saragiotto", "Sardinha",
						"Sartoris", "Sato", "Savoi", "Scapinello", "Scarpato",
						"Scavasin", "Scher", "Schmol", "Schroeder", "Schuler",
						"Scriven", "Seabra", "Segundo", "Serrano", "Sibinel",
						"Simioni", "Sobrinho", "Spagnolo", "Szareski",
						"Takeda", "Teles", "Terra", "Thomaziello", "Toledo",
						"Tonon", "Torquato", "Tosin", "Tota", "Trevisan",
						"Trinca", "Troncha", "Uyeda", "Vale", "Valente",
						"Valiati", "Valle", "Vasconcelos", "Vaz", "Vazquez",
						"Velloso", "Venezian", "Vianna", "Viero", "Vigato",
						"Vilela", "Virgens", "Vizone", "Vlasman", "Volpato",
						"Von", "Wadamori", "Yada", "Yamada", "Yashoshima",
						"Zalochi", "Zanetti", "Zani", "Zannini", "Zapater",
						"Zechel" };
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Surname name in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class SurnameInEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "Lee", "Chaney", "Dalton", "Leach",
						"Graham", "Hines", "Spencer", "Nieves", "Roach",
						"Vance", "Hartman", "Matthews", "Harrell", "Espinoza",
						"Peck", "Bonner", "Lindsay", "Hampton", "Sharp",
						"Carr", "Moody", "Mason", "Calhoun", "Frank", "Romero",
						"Wiggins", "Wilkins", "Rosa", "Rivas", "Alford",
						"Woodward", "Foreman", "Christian", "Craig", "Sears",
						"Leon", "Molina", "Osborne", "Cook", "Hendricks",
						"Meadows", "Mcgee", "Winters", "Acosta", "Boone",
						"Haynes", "Blackwell", "Vargas", "Lynch", "Patton",
						"Kane", "Odonnell", "Anthony", "Farmer", "Mosley",
						"Workman", "Bishop", "Hinton", "Zimmerman", "Bender",
						"Pickett", "England", "Mack", "Parker", "Thornton",
						"Dotson", "Tillman", "Solomon", "Kent", "Finley",
						"Sparks", "Greene", "Witt", "Washington", "Rhodes",
						"Beck", "Frederick", "Richards", "Goodman", "Yang",
						"Rodgers", "Morrison", "Rivers", "Best", "Rivera",
						"Ross", "Waters", "Baker", "Gonzalez", "Moses",
						"Vinson", "Mcintyre", "Wall", "Whitney", "Mullen",
						"Potts", "Walsh", "Walker", "Schmidt", "Adkins",
						"Quinn", "Cabrera", "Duncan", "Gamble", "Hardy",
						"English", "Lara", "Wiley", "Dean", "Cervantes",
						"James", "Reyes", "Fleming", "Bowers", "Brewer",
						"Andrews", "Compton", "Brooks", "Conrad", "Barnett",
						"Brady", "Singleton", "Banks", "Rocha", "Mcintosh",
						"Barnes", "Woodard", "Rodriguez", "Baxter", "Harrison",
						"Church", "Gallagher", "Valdez", "Carey", "Lynn",
						"Blackburn", "Melendez", "Grimes", "Park", "Hensley",
						"Haney", "Wilkinson", "Mcknight", "Kaufman", "Bright",
						"Rowland", "Leblanc", "Morton", "Fowler", "Delacruz",
						"Holden", "Wheeler", "Miller", "Dunn", "Obrien",
						"Moss", "Martin", "Benson", "Moore", "Nunez", "Soto",
						"Hamilton", "Estes", "Stevenson", "Huffman", "Norris",
						"Head", "Mullins", "Galloway", "Pate", "Swanson",
						"Raymond", "Mcmahon", "Castro", "Pena", "Deleon",
						"Hess", "Hunter", "Berry", "Montoya", "Hopper",
						"Hutchinson", "Burnett", "Farley", "Kim", "Nguyen",
						"Slater", "Jenkins", "Logan", "Franco", "Carrillo",
						"Warner", "Whitaker", "Jarvis", "Sims", "Davis",
						"Aguirre", "Walton", "Kelly", "Hays", "Hebert",
						"Robles", "Fulton", "Butler", "Davidson", "Hayden",
						"Glover", "Browning", "Silva", "Parrish", "Gilmore",
						"Owens", "Clayton", "Suarez", "Garcia", "Scott",
						"Whitfield", "Roth", "Johnson", "Sanders", "Austin",
						"Powell", "Saunders", "Whitley", "Dodson", "Gross",
						"Carroll", "Webster", "Hale", "Powers", "Dyer",
						"Peters", "Marshall", "Luna", "Ferguson", "Durham",
						"Phillips", "Chang", "Edwards", "Wright", "Dawson",
						"Barr", "Stephens", "Craft", "Shelton", "Holmes",
						"Cote", "Black", "Shepard", "Gordon", "Ayala",
						"Palmer", "Pitts", "Rich", "Howell", "Mcmillan",
						"Anderson", "Castillo", "Cline", "Ewing", "Stuart",
						"Goff", "Lloyd", "Gilbert", "Richardson", "Salazar",
						"Salas", "Nichols", "Kelley", "Huber", "Riddle",
						"Pace", "Lawson", "Lyons", "Robertson", "Fry",
						"Dejesus", "Vega", "Cherry", "Donovan", "Bond", "Good",
						"Larson", "Pacheco", "Thompson", "Barber", "Sellers",
						"Oconnor", "Short", "Mooney", "Gibbs", "Fox", "Shaw",
						"Odom", "Maynard", "Wooten", "Gould", "Parks", "Mccoy",
						"Hoffman", "Shannon", "Patrick", "Noel", "Hester",
						"Mathis", "Lambert", "Dorsey", "Doyle", "Graves",
						"Summers", "Mccray", "Curtis", "Colon", "Herring",
						"Elliott", "Walter", "Griffith", "Warren", "Ratliff",
						"Steele", "Hughes", "Conley", "Hull", "Booker",
						"Merritt", "Fitzgerald", "Hyde", "Strickland",
						"Bentley", "Ray", "Contreras", "Harding", "Mcguire",
						"Puckett", "Mendoza", "Leonard", "Phelps", "Watson",
						"Ramsey", "Albert", "Herrera", "Miranda", "Bird",
						"Avery", "Robbins", "Mcbride", "Mejia", "Townsend",
						"Mendez", "Caldwell", "Buckley", "Cross", "Stewart",
						"Poole", "Keller", "Rutledge", "Jacobs", "Marks",
						"White", "Sandoval", "Vazquez", "Underwood", "Marquez",
						"Garner", "Combs", "Acevedo", "Cunningham", "Hansen",
						"Lancaster", "Wynn", "Byrd", "Pruitt", "Velez",
						"Baldwin", "Love", "Brock", "Reeves", "Barrera",
						"Sharpe", "Kinney", "Lester", "Moran", "Wilder",
						"Bradley", "Landry", "Cameron", "Ramirez", "Fletcher",
						"Daniels", "Floyd", "Johnston", "Richard", "Hurley",
						"Mcclure", "Lawrence", "Kirby", "Hickman", "Newman",
						"Carney", "Clemons", "Burris", "Bradford", "Nash",
						"Morin", "Herman", "Guy", "Bartlett", "Morse",
						"Gilliam", "Russell", "Patterson", "Clay", "Francis",
						"Cooper", "Gillespie", "Collins", "Sloan", "Ball",
						"Beasley", "Ramos", "Page", "Chase", "Burks",
						"Stafford", "Salinas", "Williams", "Byers", "Wise",
						"Hawkins", "Alston", "Bernard", "Sweeney", "Wilkerson",
						"Cummings", "Reese", "Riggs", "Coleman", "Hogan",
						"Finch", "Reed", "Branch", "Dunlap", "Fernandez",
						"Chandler", "Solis", "Cardenas", "Robinson", "Cash",
						"Sutton", "Daugherty", "Pratt", "Dennis", "Lang",
						"Irwin", "Terrell", "Morris", "Wyatt", "Webb", "Boyd",
						"Tucker", "Delgado", "Burt", "Stanton", "Pierce",
						"Mcconnell", "Carver", "Mathews", "Michael", "Stevens",
						"George", "Ballard", "Roy", "Olson", "Golden", "Duke",
						"Burton", "Mcdowell", "Ashley", "Franks", "Mclean",
						"Holland", "Crawford", "Travis", "Foley", "Massey",
						"Fields", "Hatfield", "Ellis", "Medina", "Noble",
						"Jones", "Williamson", "Cole", "Buchanan",
						"Mccullough", "Case", "Conner", "Melton", "Morrow",
						"Santos", "Macdonald", "Bennett", "Douglas", "Stone",
						"Preston", "Gray", "Mcdonald", "Lott", "Mccarthy",
						"Glenn", "Snyder", "Chan", "Chapman", "Torres",
						"Sullivan", "Crosby", "Rush", "Ruiz", "Rosario",
						"Kirk", "Forbes", "Dickson", "Barlow", "Bryant",
						"Savage", "Hancock", "Petty", "Tran", "Bray", "Clarke",
						"Hammond", "Miles", "Diaz", "Harris", "David",
						"Arnold", "Howe", "Justice", "Everett", "Zamora",
						"Clements", "West", "Porter", "Shaffer", "Reilly",
						"Humphrey", "Young", "Oliver", "Mccarty", "Perkins",
						"Stanley", "Griffin", "Cotton", "Wilcox", "Cantu",
						"Bailey", "Nixon", "Rice", "Kerr", "Cooke", "Fisher",
						"Rasmussen", "Berger", "Weiss", "Meyer", "Wolfe",
						"Beard", "Green", "Mayo", "Estrada", "Thomas", "Wade",
						"Long", "Pugh", "Dillard", "Hooper", "Kline",
						"Pennington", "Talley", "Blair", "Mcfadden", "Howard",
						"Callahan", "Goodwin", "Berg", "Bean", "Guthrie",
						"Flores", "Potter", "Trevino", "Clark", "Shields",
						"Welch", "Vang", "Koch", "Jennings", "Figueroa",
						"Hall", "Randolph", "Cleveland", "Payne", "Hicks",
						"Gomez", "Dale", "Harper", "Guzman", "Gutierrez",
						"Peterson", "Cortez", "Gill", "Freeman", "Beach",
						"Jefferson", "Ortiz", "Duffy", "Pope", "Wood",
						"Abbott", "Barker", "Henry", "French", "Gregory",
						"Vincent", "Buckner", "Bruce", "Bush", "Kidd", "Greer",
						"Mitchell", "Hahn", "Dickerson", "Simmons", "Faulkner",
						"Malone", "Russo", "Chen", "Hanson", "Coffey",
						"Stephenson", "Taylor", "Joyce", "Tate", "Lowery",
						"Britt", "Snow", "Woods", "Wilson", "Sawyer", "Hart",
						"Erickson", "Campbell", "Crane", "Sargent", "Vaughn",
						"Harrington", "Randall", "Jensen", "Casey", "Weeks",
						"Cooley", "Sweet", "Mcgowan", "Barton", "Mueller",
						"Wells", "Carter", "Morgan", "Middleton", "Mcclain",
						"Valentine", "Benton", "Knox", "Sampson", "Vaughan",
						"Prince", "Norman", "Adams", "Franklin", "Murray",
						"Ryan", "Sheppard", "Mckenzie", "Hill", "Fuentes",
						"Tyler", "Gallegos", "Kemp", "Cruz", "Glass", "Mann",
						"Armstrong", "Sexton", "Riley", "Decker", "Mayer",
						"Garza", "Levy", "Velazquez", "Terry", "Blankenship",
						"Parsons", "Christensen", "Monroe", "Kirkland",
						"Garrison", "Keith", "Stein", "Stout", "Larsen",
						"Barrett", "Ochoa", "Hardin", "Watkins", "Day",
						"Trujillo", "Hood"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Generate valid email characters.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class EmailCharacters implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				char values[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
						'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
						'U', 'V', 'X', 'W', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
						'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
						'r', 's', 't', 'u', 'v', 'x', 'w', 'z', '0', '1', '2',
						'3', '4', '5', '6', '7', '8', '9', '0', '.', '_', '-' };

				return String.valueOf(values[randomGenerator
						.nextInt(values.length)]);
			}
		}

		/**
		 * Generate an email name.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class EmailName implements Generator {

			@SuppressWarnings("synthetic-access")
			private Generator characters = new EmailCharacters();
			private int moreThen;
			private int lessThen;

			/**
			 * Constructor.
			 * 
			 * @param moreThen
			 *            More than a minimum of characters.
			 * @param lessThen
			 *            Less then a maximum of characters.
			 */
			EmailName(int moreThen, int lessThen) {

				this.moreThen = moreThen;
				this.lessThen = lessThen;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				int lengthName = this.moreThen
						+ randomGenerator.nextInt(this.lessThen - this.moreThen
								- 1) + 1;

				String word = new String();
				for (int i = 0; i < lengthName; i++) {
					word += this.characters.getContent();
				}

				return word;
			}
		}

		/**
		 * Top level domains for emails.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class TopLevelDomains implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "aero", "asia", "biz", "cat", "com",
						"coop", "edu", "gov", "info", "int", "jobs", "mil",
						"mobi", "museum", "name", "net", "org", "pro", "tel",
						"travel" };

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Email country codes.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class EmailCountryCode implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "ac", "ad", "ae", "af", "ag", "ai", "al",
						"am", "an", "ao", "aq", "ar", "as", "at", "au", "aw",
						"ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh",
						"bi", "bj", "bm", "bn", "bo", "br", "bs", "bt", "bv",
						"bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch",
						"ci", "ck", "cl", "cm", "cn", "co", "cr", "cs", "cu",
						"cv", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do",
						"dz", "ec", "ee", "eg", "er", "es", "et", "eu", "fi",
						"fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge",
						"gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq",
						"gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn",
						"hr", "ht", "hu", "id", "ie", "North", "il", "im",
						"in", "io", "iq", "ir", "is", "it", "je", "jm", "jo",
						"jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr",
						"kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr",
						"ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me",
						"mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq",
						"mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz",
						"na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np",
						"nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph",
						"pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py",
						"qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc",
						"sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm",
						"sn", "so", "sr", "st", "su", "sv", "sy", "sz", "tc",
						"td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn",
						"to", "tp", "tr", "tt", "tv", "tw", "tz", "ua", "ug",
						"uk", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi",
						"vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw" };

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Address Portuguese
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class AddressPortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"R Leosino Oliveira, 967", "R Gravata, 77 - Lj 46",
						"R Pedro Ii, 11", "Av Graças, 147 - Lj 1",
						"Av México, 23", "R Emílio Ribas, 45",
						"Av José Mariano, 124", "R Curussa, 305 - Lj 305",
						"Av Sycaba, 19", "Av Nova York, 650 - Sl 1",
						"R Olga Assunpção, 534", "R Palmeiras, 141",
						"R Graças, 581", "Av Camanducaia, 409 - Lj 08",
						"Av Antônio Rodrigues Campos, 136 - Lj",
						"R Salvador, 4", "Av Nova York, 650",
						"Av Laranjeiras, 117 - Lj C", "R Camélia, 867",
						"R José Sales Pinto, 16 - Lj 01",
						"Av Campos Ourique, 2032", "R Rio Japurá, 335",
						"R Amim Fares Debian, 170", "R Pernambuco, 221 - Lj 6",
						"R Águas Marinhas, 263 - Lj 01",
						"Av Bandeirantes, 159", "R Contagem, 359",
						"Av Acácias, 15", "Av Campos Ourique, 1710",
						"Av Belo Horizonte, 1513", "Av Belo Horizonte, 917",
						"Av Sycaba, 1234", "R Simonesia, 240",
						"R Antônio Franco Amaral, 56 - Lj A", "R Dois, 17",
						"R Patrocínio, 375", "Av Caetano, 120",
						"R Dois, 17 - Lj", "Al Damazo Rezende, 281",
						"R Gravata, 77 - Lj 43",
						"Av Campos Ourique, 1266 - Lj", "R Um, 206",
						"Av Nova York, 719 - Fr", "Av Camanducaia, 441",
						"Av Edméia Matos Lazzarotti, 695", "R Vinte E Dois, 0",
						"Av Amazonas, 1112", "R Quatro, 0",
						"Av Artur Trindade, 547", "Av Caetano, 337",
						"R Violetas, 425", "R Santa Cruz, 781 - Lj 1",
						"Av Carmo, 213", "Av Valadares, 229 - Lj A",
						"Av Valadares, 88 - Sl 204",
						"R Rio Janeiro, 182 - Lj 3", "R Pará Minas, 315",
						"Av Carmo, 38 - Sl 203", "R Graças, 470 - A",
						"R Inconfidência, 468 - Sl 101",
						"Av Amazonas, 838 - Sl 108",
						"R Inconfidência, 402 - Sl 1",
						"Av Belo Horizonte, 272", "R Santa Cruz, 797 - Lj 02",
						"Av Valadares, 88 - Sl 202",
						"Av Juscelino Kubitschek, 140 - Sl 805",
						"Av Amazonas, 1157 - Ap 02",
						"R Santa Cruz, 612 - Sl 504",
						"R Josefina Bento Costa, 215",
						"Av Edméia Matos Lazzarotti, 832", "Av Amazonas, 1151",
						"R Santa Cruz, 689 - Sl 04",
						"R Nicolau Alves Melo, 161 - Sl 101", "R Caxias, 128",
						"Av Juscelino Kubitschek, 140 - Sl 103", "R Dois, 13",
						"R Antônio Quirino Silva, 170 - Lj 2", "R Brasil, 78",
						"R Mira, 16", "Av José Mariano, 474 - An 2",
						"Av Laranjeiras, 458", "R Amazonas, 21 - Ca A",
						"R Amazonas, 21",
						"Av Antônio Rodrigues Campos, 203 - Lj 9",
						"R Palmeiras, 105", "Av Rio Madeira, 152",
						"R Graças, 671", "R Campo Formoso, 278",
						"R Afonso Henrique, 737", "Av Nova York, 1214",
						"Av Edméia Matos Lazzarotti, 1441 - Lj 04",
						"Av Edméia Matos Lazzarotti, 1441 - Lj 4",
						"Rua Julia Esteves, 140",
						"Estr. União Indústria, 3441", "Rua André Rovay, 332",
						"Av. Brasil, 809", "Rua Bernardo Mascarenhas, 1347",
						"Rua Justo Azambuja, 365", "Rua Alagoas, 760",
						"Rua Vitoriano Borges, 387",
						"Av. Barão Do Rio Branco, 4506",
						"Rua Amaral Santos, 186",
						"Rua Antônio Soterado Almeida, 536",
						"Rua Leopoldo Teixeira, 24", "Rua União De Vitória",
						"Av. Senador Salbado Filho, 7100",
						"Rua Floriano Peixoto, 1967",
						"Av. Paulo De Frontin, 568",
						"Rua José Pimenta De Oliveira, 750",
						"Av. Mons. Eduardo, 701", "Rua Manoel Sbaile, 51",
						"Rua Pio Xii, 236", "Rua Lourenço Costa, 102",
						"Rua Dora Maria Emílio N. F. Silva, 92",
						"Av. Dom Antônio Brandão, 559",
						"Av. Pualo De Frontin, 568", "Caixa Postal 21",
						"Praça Dr. Rooservelt Cury, 02",
						"Praça João Ribeiro, 84", "Rua Prof. Paulo Lpes, 122",
						"Av. Amazonas, 6825", "Av. José Munia, 7470",
						"Rua França Pinto, 55 Ap. 51", "Olegário Maciel, 2410",
						"Rua Vicente Cardoso, 1591",
						"Ria Mep Açves <artoms. 3176",
						"Rua Siqueira Campos, 913",
						"Rua Dr. Cristiano Otoni, 867",
						"Av. Barão Do Rio Branco, 4516",
						"Rua Vito Abatepaulo, 51 Apto. 13a",
						"Rua Caetano Belincanta, 777 Apto. 101",
						"Rua Dr. Mário Vicente, 1108",
						"Seminário N. Sra. Assunção",
						"Rua Guilherme Bramer, 84", "Rua Belém Do Pará, 566",
						"Ra Soldado Abílio Dos Santos, 47",
						"Rua Nove De Julho, N. 102 Casa 01",
						"Pça Sta Ana, 420", "Octávio Malvaccini, 39",
						"Rua Santa Erotildes, N. 483", "Rua Pe. Leon, 230",
						"Av. Cardoso Saraiva, Apto. 201 N. 726",
						"Av. Gov. Parigot De Souza, 491 Apto. 302",
						"Rua Serra Leoa, 360", "Rua Xavier De Almeida, 868",
						"Tv. Benjamim Constant 1364 Ap 54",
						"Pça Cesário Alvim,271",
						"Av. Leovigildo Figueiras, 270",
						"Av. Cordese Da Silva", "Rua Prfº Gerson Pinto,281",
						"Rua Pinheiro Machado,2011 S",
						"Av. Maria Benedita De Mello Lincoln, 312"

				};

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Address English
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class AddressEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "P.O. Box 202, 2156 Tempor Street",
						"P.O. Box 460, 7499 Sodales Rd.",
						"Ap #265-773 Consequat Ave",
						"6888 Pellentesque Street", "Ap #292-1580 Vel Street",
						"P.O. Box 474, 6852 Quisque St.",
						"Ap #343-2289 Massa St.",
						"P.O. Box 312, 6507 Parturient Rd.",
						"1382 Aliquet Avenue",
						"P.O. Box 473, 2670 Condimentum. Avenue",
						"361-8202 Gravida St.", "7194 Nec Road",
						"556-2875 Aenean Street", "Ap #680-2641 Sed Av.",
						"9316 Cum Av.", "P.O. Box 627, 6249 In, Ave",
						"2824 Luctus St.", "Ap #927-221 Enim. Road",
						"Ap #541-133 Integer Ave", "183-7782 Vivamus Avenue",
						"P.O. Box 371, 6223 Adipiscing Ave",
						"646-2996 Posuere Street",
						"Ap #162-1180 Turpis Street", "452-994 Varius St.",
						"1981 Convallis, Road", "898-1394 Risus. Av.",
						"1561 Massa. Rd.", "6359 Rhoncus. St.",
						"P.O. Box 767, 3243 Nulla. Street",
						"619-1271 Dui. Street", "Ap #683-4921 Malesuada. St.",
						"Ap #817-3457 Id Rd.", "464-3535 Enim Ave",
						"P.O. Box 642, 3252 Varius. Rd.",
						"Ap #922-7961 Lectus. St.",
						"P.O. Box 344, 9591 Lorem, Road", "7178 Pede, Av.",
						"Ap #973-1573 Orci. Rd.",
						"P.O. Box 194, 7816 Pellentesque Street",
						"P.O. Box 190, 4993 Nunc Rd.",
						"P.O. Box 838, 9863 Tristique Road", "4705 Odio. Rd.",
						"915-9731 Nisi St.", "892-2520 Sem Rd.",
						"Ap #758-1807 Facilisis St.",
						"Ap #299-6794 Fermentum Rd.", "316-8863 Mi Ave",
						"8814 Nostra, Street", "Ap #316-8591 Risus. Road",
						"198-6679 Sagittis St.", "P.O. Box 258, 284 Ut Road",
						"3350 Eu Road", "P.O. Box 901, 7292 Turpis St.",
						"Ap #743-3633 Nulla. St.", "112-1848 Id, Rd.",
						"Ap #917-1467 Vivamus Av.", "214-6625 Aliquet Rd.",
						"827-9348 Accumsan St.", "8114 Nonummy Av.",
						"907-2900 Facilisis Ave", "5725 Auctor Street",
						"Ap #463-7515 Pretium Av.", "1908 Eget Street",
						"589-6355 Lorem, St.", "450-8272 Auctor St.",
						"Ap #175-150 Eget, Road", "880-2441 Commodo Avenue",
						"379 Vitae Road", "5934 Mauris Road", "5502 Ante Rd.",
						"P.O. Box 521, 5415 Lorem St.", "9361 Cras Avenue",
						"Ap #378-2726 Ante Street",
						"Ap #398-5331 Vitae Street", "759-9472 Euismod Ave",
						"Ap #535-1824 Sed Rd.", "P.O. Box 450, 1279 A, St.",
						"P.O. Box 447, 8544 Orci. Avenue",
						"P.O. Box 761, 9226 Sodales. Rd.",
						"P.O. Box 938, 5353 Nulla St.", "294-3748 Ac Rd.",
						"7821 Mollis St.", "P.O. Box 597, 723 Mus. St.",
						"Ap #464-9020 Amet Ave",
						"P.O. Box 586, 3769 Ac Street",
						"Ap #100-6789 Mattis. Rd.", "385-8296 Montes, Rd.",
						"P.O. Box 705, 5682 Sapien. Ave",
						"Ap #945-7511 Ac St.", "298-8237 Dis Road",
						"P.O. Box 448, 6919 Gravida St.",
						"P.O. Box 279, 1961 Cras St.",
						"Ap #319-2050 Ultrices, Av.", "6223 Curabitur Av.",
						"772-723 Turpis Rd.", "Ap #844-9172 Massa. Rd.",
						"P.O. Box 200, 7314 A, Rd.",
						"P.O. Box 831, 9522 Elit Av.", "2654 Volutpat. St.",
						"339-9020 Congue St.", "Ap #110-6959 Massa St.",
						"Ap #797-9629 Pellentesque Rd.", "5993 Purus, Rd.",
						"876-9539 Molestie Avenue",
						"349-7411 Parturient Avenue",
						"P.O. Box 939, 1649 Ornare St.", "5666 Blandit St.",
						"313-4149 Justo. Av.", "912-5133 In St.",
						"773 Enim. Rd.", "P.O. Box 550, 7486 Ac Av.",
						"903-9243 Dapibus Ave", "P.O. Box 301, 8318 Sem Av.",
						"811-509 Velit Av.", "6271 Non St.",
						"P.O. Box 753, 3536 Hendrerit. Av.",
						"P.O. Box 965, 8169 Vulputate Av.", "501-2001 Sit Ave",
						"556 Suscipit Road", "3859 Cras St.",
						"P.O. Box 923, 8640 Volutpat. Street",
						"947-7141 Vivamus Av.",
						"P.O. Box 759, 4660 Aliquet, Ave",
						"905-4080 Primis Ave", "Ap #841-7148 In Road",
						"P.O. Box 590, 7490 Purus. Av.", "7153 Eleifend Road",
						"P.O. Box 275, 3527 Vel Av.", "6944 Magna. Avenue",
						"P.O. Box 271, 3980 Arcu. Ave", "409 Mauris St.",
						"1481 Massa. Road", "451-4773 Habitant Ave",
						"600-1491 Non Av.", "708-6666 Nascetur Rd.",
						"284-3442 Proin Rd.", "Ap #941-4862 Sit Road",
						"9126 Proin Avenue", "5512 Mi St.",
						"P.O. Box 274, 9556 Aliquam St.",
						"P.O. Box 836, 3954 Felis. Ave",
						"P.O. Box 704, 1435 Dictum Rd.",
						"922-2789 Sollicitudin Av.", "7284 Lobortis Av.",
						"Ap #671-7123 At Rd.", "P.O. Box 587, 2087 Nulla St.",
						"Ap #103-5972 Sed Rd.",
						"P.O. Box 101, 4510 Amet, Road", "5760 Rhoncus. St.",
						"8136 Proin Ave", "Ap #486-2647 Cursus, Rd.",
						"5742 Phasellus St.", "P.O. Box 502, 2866 Velit. St.",
						"282-6473 Proin St.", "Ap #209-9230 Cum Street",
						"453-7431 Sit Avenue", "940-7447 Ac St.",
						"8503 Consectetuer St.",
						"P.O. Box 265, 6034 Purus Rd.",
						"P.O. Box 710, 7487 Eleifend Rd.",
						"674-9682 Cras Street", "5219 Non, Rd.",
						"453-3549 Accumsan St.",
						"P.O. Box 457, 5216 Bibendum Street",
						"Ap #545-3482 Lacus. Street", "3283 Sem St.",
						"P.O. Box 744, 8848 Eros Rd.", "425 Tempor, Road",
						"Ap #973-7491 Tortor Rd.",
						"P.O. Box 478, 6918 Elit Road",
						"P.O. Box 274, 742 Nulla Av.",
						"764-7851 Vulputate, Ave",
						"P.O. Box 866, 1363 Libero St.", "Ap #240-5914 Eu Ave",
						"P.O. Box 280, 518 Aenean St.", "128-4340 Posuere St.",
						"P.O. Box 369, 4164 Ipsum St.",
						"P.O. Box 746, 9185 Diam. St.", "9688 Ultricies St.",
						"6000 Nam St.", "P.O. Box 435, 9990 Donec Avenue",
						"P.O. Box 378, 5846 Urna. Rd.",
						"P.O. Box 586, 5631 Luctus Road", "5715 Id Av.",
						"943-9508 A, St.", "1627 Iaculis Avenue",
						"7446 Malesuada Ave",
						"P.O. Box 654, 3139 Pretium Avenue",
						"Ap #341-1123 Auctor Rd.",
						"Ap #565-5371 Integer Street",
						"P.O. Box 551, 5595 Molestie Rd.",
						"Ap #711-3594 Quisque Road", "202-5877 Est. Av.",
						"Ap #306-3529 Aliquam Ave",
						"P.O. Box 497, 1931 Felis Avenue",
						"775-9181 Cursus Road", "184-7380 Enim Street",
						"P.O. Box 518, 1664 Sem Rd.", "9006 Enim. Av.",
						"P.O. Box 760, 1010 Adipiscing Rd.", "805-3617 Ac Av.",
						"6985 Vel Av.", "Ap #597-2495 Ligula Rd.",
						"4803 Malesuada Rd.", "475-5319 Magna St.",
						"193 Ornare, St.", "207-5820 Dui Street",
						"564-7740 Eu Rd.", "818-8756 Ligula. Street",
						"P.O. Box 936, 259 Tincidunt Road",
						"312-6957 Nisi. Road", "1047 Lectus Ave",
						"810-2750 Sed Avenue", "105-7315 Orci, Road",
						"2350 Dolor, Av.", "P.O. Box 392, 965 Posuere Ave",
						"P.O. Box 374, 6617 Non Street",
						"536-3118 Consectetuer, Rd.", "3314 Arcu Rd.",
						"Ap #396-194 Libero. Rd.", "Ap #970-1228 Etiam Road",
						"471-3030 Sociis St.", "Ap #944-6071 Dignissim Rd.",
						"P.O. Box 424, 9480 Ut Rd.", "329-5240 Vel Rd.",
						"P.O. Box 278, 4854 Justo St.", "9107 Aliquet St.",
						"Ap #442-2967 Nulla Avenue", "5029 Libero Ave",
						"P.O. Box 108, 5134 Nulla Avenue",
						"967-455 Libero Road", "Ap #408-3762 Pede St.",
						"8895 Nam Rd.", "9460 Proin Rd.",
						"897-3886 Lectus. Road", "2008 Consectetuer Av.",
						"P.O. Box 352, 2789 Donec Avenue",
						"P.O. Box 647, 4788 Porttitor Ave", "8267 Auctor St.",
						"Ap #792-1242 Risus Road",
						"P.O. Box 944, 9520 Velit Rd.",
						"450-6878 Porttitor Av.", "276-7865 Sed Rd.",
						"526-7065 Blandit Rd.",
						"P.O. Box 216, 7658 Libero. Avenue",
						"P.O. Box 693, 7816 Dolor. St.",
						"366-9396 Mollis. St.", "6361 Eu Ave", "6571 A Rd.",
						"678-5160 Erat, St.", "P.O. Box 785, 734 Vitae St.",
						"3635 Urna. Avenue", "P.O. Box 712, 1899 Amet Avenue",
						"960-3324 Gravida St.", "2393 Gravida. Ave",
						"821 Purus. Av.", "Ap #423-1275 Et Street",
						"8968 Lorem Rd.", "Ap #374-6751 Feugiat Ave",
						"866-3446 Ultricies Street",
						"734-4945 Maecenas Street", "Ap #263-4743 At Rd.",
						"936-9699 Amet Street", "2591 Penatibus St.",
						"P.O. Box 729, 4263 Mattis Ave",
						"P.O. Box 842, 7263 Et, Road",
						"P.O. Box 345, 4629 Non St.", "525-4881 Risus, Ave",
						"P.O. Box 916, 2667 Suspendisse Avenue", "771 A, Av.",
						"3811 Penatibus St.", "8930 Scelerisque Ave",
						"Ap #811-8309 Malesuada Av.",
						"P.O. Box 404, 7056 A Avenue",
						"P.O. Box 411, 4673 Convallis Av.",
						"438-4605 Sed Road", "Ap #930-5062 Posuere Street",
						"2229 Dis Street", "981-5033 Nonummy Av.",
						"969-9115 Neque. Av.", "5320 Nulla. Ave",
						"9618 Quam. St.", "110-6073 Dictum Road",
						"Ap #236-634 Eget Rd.", "925-8236 Felis St.",
						"661-7093 Turpis Avenue", "Ap #475-2658 In Rd.",
						"P.O. Box 560, 9348 Fringilla Avenue",
						"461-892 Senectus St.", "Ap #479-2133 Morbi St.",
						"P.O. Box 943, 1723 Lorem, Street",
						"P.O. Box 449, 1209 Turpis Rd.",
						"Ap #446-5105 Lorem St.", "156-2952 Pharetra Ave",
						"709-5340 Velit. St.", "Ap #146-9948 Diam Rd.",
						"P.O. Box 130, 6610 Libero Ave",
						"P.O. Box 519, 4962 Justo St.",
						"Ap #963-6230 Luctus Street",
						"581-9831 Interdum Avenue", "119-1949 Proin Avenue",
						"2675 Urna. Av.", "1813 Elementum St.",
						"P.O. Box 986, 3154 Tincidunt Av.",
						"P.O. Box 344, 2282 Fringilla Rd.", "1684 Libero St.",
						"506-7464 Orci, Ave", "6260 Consequat Ave",
						"430-5871 Cursus Rd.", "3656 Pede. Road",
						"528-5223 Consequat Avenue", "438 In Rd.",
						"P.O. Box 174, 4083 Tincidunt, Avenue",
						"5265 Placerat Rd.", "659-6962 Nec Rd.",
						"P.O. Box 471, 7390 Feugiat St.",
						"P.O. Box 616, 1692 A St.", "Ap #328-6858 Odio St.",
						"Ap #483-2960 Dui. Street",
						"P.O. Box 326, 6852 Auctor Av.",
						"888-8744 Interdum Street", "316 Pharetra Ave",
						"359-8035 Consequat Ave",
						"P.O. Box 148, 1364 Adipiscing Rd.",
						"Ap #804-3478 Urna, Ave",
						"P.O. Box 865, 1477 Etiam St.",
						"Ap #579-8026 Mauris. Rd.", "3163 In Road",
						"173-2211 Dapibus Av.", "P.O. Box 249, 1330 Nam Av.",
						"Ap #105-8907 Vel Street", "4804 Semper Road",
						"Ap #691-3004 Ligula. Ave",
						"P.O. Box 951, 5994 Velit St.",
						"6444 Scelerisque Street", "4067 Dignissim St.",
						"Ap #152-3470 Fringilla, St.", "776-903 Nulla Ave",
						"Ap #162-4293 Lobortis Rd.", "Ap #561-9177 Congue Ave",
						"Ap #700-7162 Purus Rd.", "581-1709 Molestie Street",
						"P.O. Box 428, 5322 Mi Rd.",
						"P.O. Box 927, 9778 Non Street",
						"Ap #858-6864 Molestie Rd.", "802-5315 Tristique St.",
						"8410 Et Rd.", "610-1047 Class Ave",
						"3325 Scelerisque Ave", "5416 A, Street",
						"516-3525 Egestas St.",
						"P.O. Box 778, 1746 Aliquet St.", "7328 In St.",
						"Ap #397-7995 Ac Rd.", "558-5148 Cras Rd.",
						"679 Arcu. Road", "Ap #232-7507 Blandit Ave",
						"P.O. Box 210, 7152 Libero Rd.", "4920 Molestie Av.",
						"P.O. Box 578, 3704 At, Av.",
						"P.O. Box 411, 5203 Mi Av.",
						"P.O. Box 659, 2663 Euismod Av.", "5549 Nulla Ave",
						"395-6803 Tempus St.", "709-3347 Mauris Rd.",
						"184-3868 Ut St.", "786-6235 Sit Rd.",
						"P.O. Box 563, 3107 Pede Street",
						"P.O. Box 504, 6976 Tristique Ave",
						"240 Dignissim Ave", "554-8303 Aliquam Road",
						"P.O. Box 322, 2099 Nam Av.",
						"P.O. Box 683, 9418 Tempor Rd.",
						"Ap #229-4210 Cursus Street",
						"P.O. Box 900, 6162 Ullamcorper Rd.",
						"422-3528 Id Rd.", "7518 Velit Road", "7534 Ac Rd.",
						"9027 Quis St.", "Ap #685-9212 Pretium St.",
						"P.O. Box 311, 3726 Ut Street", "211-607 Sit Road",
						"267-7957 Sagittis Rd.",
						"Ap #765-5900 Eleifend Avenue", "511-4489 Ligula. Rd.",
						"774-8177 Mauris St.", "P.O. Box 534, 8049 Dolor Rd.",
						"P.O. Box 719, 1269 Risus Avenue",
						"Ap #713-7180 In Rd.", "Ap #915-2510 Diam. Rd.",
						"Ap #612-7418 Faucibus. Street", "6792 Ante Ave",
						"P.O. Box 177, 7896 Penatibus Ave",
						"P.O. Box 615, 1191 Rutrum Avenue",
						"494-2680 Enim. Ave", "Ap #612-8416 Et St.",
						"P.O. Box 831, 5507 Sed Av.", "Ap #505-4624 Mus. Rd.",
						"P.O. Box 545, 638 Lacus. Avenue", "1939 Et St.",
						"P.O. Box 403, 4420 Magnis St.",
						"Ap #717-2295 Vulputate Rd.", "8837 Fusce Rd.",
						"389-1255 Iaculis Rd.", "Ap #596-7429 Eros Ave",
						"7198 Dui, St.", "7682 Dolor Ave",
						"P.O. Box 305, 5352 Mollis Avenue",
						"P.O. Box 930, 8015 Lobortis, Ave",
						"Ap #443-2466 Metus Ave", "336-4797 Sed Ave",
						"P.O. Box 712, 8632 Velit Av.", "7501 Nec Road",
						"Ap #368-8520 Amet Ave", "Ap #547-552 Lorem Av.",
						"1564 Sodales Street", "180-8730 Neque St.",
						"704-1759 Metus Road", "P.O. Box 613, 7537 Et Road",
						"P.O. Box 782, 8591 Arcu Street",
						"Ap #262-1689 Rutrum Rd.", "Ap #476-238 Donec Street",
						"P.O. Box 252, 4912 Ipsum Road",
						"4073 Accumsan Avenue", "9377 In, St.",
						"Ap #142-8529 Erat Rd.", "Ap #182-4208 Dui. Av.",
						"Ap #510-5023 Odio Street", "Ap #992-2790 Aenean Av.",
						"7521 Ultrices. St.", "P.O. Box 690, 6065 Neque. Rd.",
						"Ap #681-2888 Eget Rd.", "P.O. Box 541, 2151 A Rd.",
						"Ap #446-4863 Odio. Avenue", "Ap #885-3996 Libero Av.",
						"P.O. Box 858, 9037 Ridiculus St.",
						"989-5758 Iaculis, Avenue",
						"P.O. Box 765, 7511 Nunc St.",
						"P.O. Box 521, 9163 Sit Road", "9955 Interdum. Rd.",
						"8512 Mi. Street", "P.O. Box 707, 217 Laoreet Av.",
						"1776 Mus. Rd.", "P.O. Box 104, 5202 Varius Road",
						"107-6204 Aptent St.",
						"P.O. Box 636, 7120 Parturient Street",
						"Ap #671-7218 Vestibulum Road",
						"Ap #608-7308 Ullamcorper, Rd.",
						"P.O. Box 915, 4967 Scelerisque Rd.",
						"3931 Luctus Road", "Ap #563-6792 Lacinia Road",
						"920-2244 Bibendum St.", "3749 Fusce Rd.",
						"P.O. Box 121, 141 Sociis Rd.",
						"Ap #268-1743 Lacinia Rd.", "Ap #118-4379 Dui, St.",
						"9841 Mauris Road", "Ap #740-4282 Accumsan Street",
						"Ap #971-2506 Sapien, Rd.", "Ap #138-4302 Proin Road",
						"P.O. Box 262, 7814 Imperdiet St.",
						"Ap #554-145 Eleifend. Road",
						"Ap #100-7165 Dictum Avenue",
						"P.O. Box 290, 7691 Nec St.", "159-2040 Massa. Street",
						"Ap #280-6362 Mauris Rd.", "Ap #263-9738 Vel, Av.",
						"126-1069 Elementum, Rd.",
						"P.O. Box 903, 6262 Sociis Rd.",
						"Ap #778-6597 Consequat Rd.", "8358 Adipiscing St.",
						"853-6170 Lorem St.", "Ap #514-1581 Vestibulum Rd.",
						"Ap #410-7566 Nec Road", "P.O. Box 334, 4834 At, Road",
						"P.O. Box 474, 5652 Risus Av.",
						"P.O. Box 245, 9575 Non, St.",
						"P.O. Box 375, 2540 Sagittis Road",
						"Ap #299-4399 Aenean Avenue", "4799 Eros. Avenue",
						"P.O. Box 785, 7045 Donec Rd.",
						"Ap #330-4725 Urna. Street", "246-8248 Nec Av.",
						"Ap #116-1659 Justo. St.", "9440 Mauris Street",
						"862-125 Vestibulum St.", "881-4407 At, Street",
						"3307 Odio. Rd.", "3400 Nec, St.",
						"735-8491 Adipiscing, Street", "7761 At Road",
						"Ap #353-1200 Libero St.", "6178 Massa Rd.",
						"7755 Erat, Street", "571-3493 Dignissim St.",
						"P.O. Box 927, 4772 Et, Rd.",
						"P.O. Box 298, 421 Integer Road", "8851 Luctus. St.",
						"P.O. Box 394, 9998 Eget Road",
						"Ap #756-5705 Felis Street"

				};

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Cities in Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class CitiesPortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "Abaetetuba", "Abel Figueiredo", "Acara",
						"Afua", "Agua Azul Do Norte", "Alenquer", "Almeirim",
						"Altamira", "Anajas", "Ananindeua", "Anapu",
						"Augusto Correa", "Aurora Do Para", "Aveiro", "Bagre",
						"Baiao", "Bannach", "Barcarena", "Belem", "Belterra",
						"Benevides", "Bom Jesus Do Tocantins", "Bonito",
						"Braganca", "Brasil Novo", "Brejo Grande Do Araguaia",
						"Breu Branco", "Breves", "Bujaru",
						"Cachoeira Do Arari", "Cachoeira Do Piria", "Cameta",
						"Canaa Dos Carajas", "Capanema", "Capitao Poco",
						"Castanhal", "Chaves", "Colares",
						"Conceicao Do Araguaia", "Concordia Do Para",
						"Cumaru Do Norte", "Curionopolis", "Curralinho",
						"Curua", "Curuca", "Dom Eliseu",
						"Eldorado Dos Carajas", "Faro", "Floresta Do Araguaia",
						"Garrafao Do Norte", "Goianesia Do Para", "Gurupa",
						"Igarape-acu", "Igarape-miri", "Inhangapi",
						"Ipixuna Do Para", "Irituia", "Itaituba", "Itupiranga",
						"Jacareacanga", "Jacunda", "Juruti",
						"Limoeiro Do Ajuru", "Mae Do Rio", "Magalhaes Barata",
						"Maraba", "Maracana", "Marapanim", "Marituba",
						"Medicilandia", "Melgaco", "Mocajuba", "Moju",
						"Monte Alegre", "Muana", "Nova Esperanca Do Piria",
						"Nova Ipixuna", "Nova Timboteua", "Novo Progresso",
						"Novo Repartimento", "Obidos", "Oeiras Do Para",
						"Oriximina", "Ourem", "Ourilandia Do Norte", "Pacaja",
						"Palestina Do Para", "Paragominas", "Parauapebas",
						"Pau D'arco", "Peixe-boi", "Picarra", "Placas",
						"Ponta De Pedras", "Portel", "Porto De Moz", "Prainha",
						"Primavera", "Quatipuru", "Redencao", "Rio Maria",
						"Rondon Do Para", "Ruropolis", "Salinopolis",
						"Salvaterra", "Santa Barbara Do Para",
						"Santa Cruz Do Arari", "Santa Isabel Do Para",
						"Santa Luzia Do Para", "Santa Maria Das Barreiras",
						"Santa Maria Do Para", "Santana Do Araguaia",
						"Santarem", "Santarem Novo", "Santo Antonio Do Taua",
						"Sao Caetano De Odivelas", "Sao Domingos Do Araguaia",
						"Sao Domingos Do Capim", "Sao Felix Do Xingu",
						"Sao Francisco Do Para", "Sao Geraldo Do Araguaia",
						"Sao Joao Da Ponta", "Sao Joao De Pirabas",
						"Sao Joao Do Araguaia", "Sao Miguel Do Guama",
						"Sao Sebastiao Da Boa Vista", "Sapucaia",
						"Senador Jose Porfirio", "Soure", "Tailandia",
						"Terra Alta", "Terra Santa", "Tome-acu", "Tracuateua",
						"Trairao", "Tucuma", "Tucurui", "Ulianopolis",
						"Uruara", "Vigia", "Viseu", "Vitoria Do Xingu",
						"Xinguara", "Agua Branca", "Aguiar", "Alagoa Grande",
						"Alagoa Nova", "Alagoinha", "Alcantil",
						"Algodao De Jandaira", "Alhandra", "Amparo",
						"Aparecida", "Aracagi", "Arara", "Araruna", "Areia",
						"Areia De Baraunas", "Areial", "Aroeiras", "Assuncao",
						"Baia Da Traicao", "Bananeiras", "Barauna",
						"Barra De Santa Rosa", "Barra De Santana",
						"Barra De Sao Miguel", "Bayeux",
						"Belem Do Brejo Do Cruz", "Bernardino Batista",
						"Boa Ventura", "Bom Jesus", "Bom Sucesso",
						"Bonito De Santa Fe", "Boqueirao", "Borborema",
						"Brejo Do Cruz", "Brejo Dos Santos", "Caapora",
						"Cabaceiras", "Cabedelo", "Cachoeira Dos Indios",
						"Cacimba De Areia", "Cacimba De Dentro", "Cacimbas",
						"Caicara", "Cajazeiras", "Cajazeirinhas",
						"Caldas Brandao", "Camalau", "Campina Grande",
						"Campo De Santana", "Capim", "Caraubas",
						"Carrapateira", "Casserengue", "Catingueira",
						"Catole Do Rocha", "Caturite", "Conceicao", "Condado",
						"Conde", "Congo", "Coremas", "Coxixola",
						"Cruz Do Espirito Santo", "Cubati", "Cuite",
						"Cuite De Mamanguape", "Cuitegi", "Curral De Cima",
						"Curral Velho", "Damiao", "Desterro", "Diamante",
						"Dona Ines", "Duas Estradas", "Emas", "Esperanca",
						"Fagundes", "Frei Martinho", "Gado Bravo", "Guarabira",
						"Gurinhem", "Gurjao", "Ibiara", "Igaracy", "Imaculada",
						"Inga", "Itabaiana", "Itaporanga", "Itapororoca",
						"Itatuba", "Jacarau", "Jerico", "Joao Pessoa",
						"Juarez Tavora", "Juazeirinho", "Junco Do Serido",
						"Juripiranga", "Juru", "Lagoa", "Lagoa De Dentro",
						"Lagoa Seca", "Lastro", "Livramento", "Logradouro",
						"Lucena", "Mae D'agua", "Malta", "Mamanguape",
						"Manaira", "Marcacao", "Mari", "Marizopolis",
						"Massaranduba", "Mataraca", "Matinhas", "Mato Grosso",
						"Matureia", "Mogeiro", "Montadas", "Monte Horebe",
						"Monteiro", "Mulungu", "Natuba", "Nazarezinho",
						"Nova Floresta", "Nova Olinda", "Nova Palmeira",
						"Olho D'agua", "Olivedos", "Ouro Velho", "Parari",
						"Passagem", "Patos", "Paulista", "Pedra Branca",
						"Pedra Lavrada", "Pedras De Fogo", "Pedro Regis",
						"Pianco", "Picui", "Pilar", "Piloes", "Piloezinhos",
						"Pirpirituba", "Pitimbu", "Pocinhos", "Poco Dantas",
						"Poco De Jose De Moura", "Pombal", "Prata",
						"Princesa Isabel", "Puxinana", "Queimadas", "Quixaba",
						"Remigio", "Riachao", "Riachao Do Bacamarte",
						"Riachao Do Poco", "Riacho De Santo Antonio",
						"Riacho Dos Cavalos", "Rio Tinto", "Salgadinho",
						"Salgado De Sao Felix", "Santa Cecilia", "Santa Cruz",
						"Santa Helena", "Santa Ines", "Santa Luzia",
						"Santa Rita", "Santa Teresinha",
						"Santana De Mangueira", "Santana Dos Garrotes",
						"Santo Andre", "Sao Bentinho", "Sao Bento",
						"Sao Domingos De Pombal", "Sao Domingos Do Cariri",
						"Sao Francisco", "Sao Joao Do Cariri",
						"Sao Joao Do Rio Do Peixe", "Sao Joao Do Tigre",
						"Sao Jose Da Lagoa Tapada", "Sao Jose De Caiana",
						"Sao Jose De Espinharas", "Sao Jose De Piranhas",
						"Sao Jose De Princesa", "Sao Jose Do Bonfim",
						"Sao Jose Do Brejo Do Cruz", "Sao Jose Do Sabugi",
						"Sao Jose Dos Cordeiros", "Sao Jose Dos Ramos",
						"Sao Mamede", "Sao Miguel De Taipu",
						"Sao Sebastiao De Lagoa De Roca",
						"Sao Sebastiao Do Umbuzeiro", "Sape", "Serido",
						"Serra Branca", "Serra Da Raiz", "Serra Grande",
						"Serra Redonda", "Serraria", "Sertaozinho", "Sobrado",
						"Solanea", "Soledade", "Sossego", "Sousa", "Sume",
						"Taperoa", "Tavares", "Teixeira", "Tenorio", "Triunfo",
						"Uirauna", "Umbuzeiro", "Varzea", "Vieiropolis",
						"Vista Serrana", "Zabele", "Adrianopolis",
						"Agudos Do Sul", "Almirante Tamandare",
						"Altamira Do Parana", "Alto Parana", "Alto Piquiri",
						"Altonia", "Alvorada Do Sul", "Amapora", "Ampere",
						"Anahy", "Andira", "Angulo", "Antonina",
						"Antonio Olinto", "Apucarana", "Arapongas", "Arapoti",
						"Arapua", "Araucaria", "Ariranha Do Ivai", "Assai",
						"Assis Chateaubriand", "Astorga", "Atalaia",
						"Balsa Nova", "Bandeirantes", "Barbosa Ferraz",
						"Barra Do Jacare", "Bela Vista Da Caroba",
						"Bela Vista Do Paraiso", "Bituruna", "Boa Esperanca",
						"Boa Esperanca Do Iguacu", "Boa Ventura De Sao Roque",
						"Boa Vista Da Aparecida", "Bocaiuva Do Sul",
						"Bom Jesus Do Sul", "Bom Sucesso Do Sul",
						"Borrazopolis", "Braganey", "Brasilandia Do Sul",
						"Cafeara", "Cafelandia", "Cafezal Do Sul",
						"California", "Cambara", "Cambe", "Cambira",
						"Campina Da Lagoa", "Campina Do Simao",
						"Campina Grande Do Sul", "Campo Bonito",
						"Campo Do Tenente", "Campo Largo", "Campo Magro",
						"Campo Mourao", "Candido De Abreu", "Candoi",
						"Cantagalo", "Capitao Leonidas Marques", "Carambei",
						"Carlopolis", "Cascavel", "Castro", "Catanduvas",
						"Centenario Do Sul", "Cerro Azul", "Ceu Azul",
						"Chopinzinho", "Cianorte", "Cidade Gaucha",
						"Clevelandia", "Colombo", "Colorado", "Congonhinhas",
						"Conselheiro Mairinck", "Contenda", "Corbelia",
						"Cornelio Procopio", "Coronel Domingos Soares",
						"Coronel Vivida", "Corumbatai Do Sul", "Cruz Machado",
						"Cruzeiro Do Iguacu", "Cruzeiro Do Oeste",
						"Cruzeiro Do Sul", "Cruzmaltina", "Curitiba",
						"Curiuva", "Diamante Do Norte", "Diamante Do Sul",
						"Diamante D'oeste", "Dois Vizinhos", "Douradina",
						"Doutor Camargo", "Doutor Ulysses", "Eneas Marques",
						"Engenheiro Beltrao", "Entre Rios Do Oeste",
						"Esperanca Nova", "Espigao Alto Do Iguacu", "Farol",
						"Faxinal", "Fazenda Rio Grande", "Fenix",
						"Fernandes Pinheiro", "Figueira",
						"Flor Da Serra Do Sul", "Florai", "Floresta",
						"Florestopolis", "Florida", "Formosa Do Oeste",
						"Foz Do Iguacu", "Foz Do Jordao", "Francisco Alves",
						"Francisco Beltrao", "General Carneiro",
						"Godoy Moreira", "Goioere", "Goioxim", "Grandes Rios",
						"Guaira", "Guairaca", "Guamiranga", "Guapirama",
						"Guaporema", "Guaraci", "Guaraniacu", "Guarapuava",
						"Guaraquecaba", "Guaratuba", "Honorio Serpa", "Ibaiti",
						"Ibema", "Ibipora", "Icaraima", "Iguaracu", "Iguatu",
						"Imbau", "Imbituva", "Inacio Martins", "Inaja",
						"Indianopolis", "Ipiranga", "Ipora",
						"Iracema Do Oeste", "Irati", "Iretama", "Itaguaje",
						"Itaipulandia", "Itambaraca", "Itambe",
						"Itapejara D'oeste", "Itaperucu", "Itauna Do Sul",
						"Ivai", "Ivaipora", "Ivate", "Ivatuba", "Jaboti",
						"Jacarezinho", "Jaguapita", "Jaguariaiva",
						"Jandaia Do Sul", "Janiopolis", "Japira", "Japura",
						"Jardim Alegre", "Jardim Olinda", "Jataizinho",
						"Jesuitas", "Joaquim Tavora", "Jundiai Do Sul",
						"Juranda", "Jussara", "Kalore", "Lapa", "Laranjal",
						"Laranjeiras Do Sul", "Leopolis", "Lidianopolis",
						"Lindoeste", "Loanda", "Lobato", "Londrina",
						"Luiziana", "Lunardelli", "Lupionopolis", "Mallet",
						"Mambore", "Mandaguacu", "Mandaguari", "Mandirituba",
						"Manfrinopolis", "Mangueirinha", "Manoel Ribas",
						"Marechal Candido Rondon", "Maria Helena", "Marialva",
						"Marilandia Do Sul", "Marilena", "Mariluz", "Maringa",
						"Mariopolis", "Maripa", "Marmeleiro", "Marquinho",
						"Marumbi", "Matelandia", "Matinhos", "Mato Rico",
						"Maua Da Serra", "Medianeira", "Mercedes", "Mirador",
						"Miraselva", "Missal", "Moreira Sales", "Morretes",
						"Munhoz De Melo", "Nossa Senhora Das Gracas",
						"Nova Alianca Do Ivai", "Nova America Da Colina",
						"Nova Aurora", "Nova Cantu", "Nova Esperanca",
						"Nova Esperanca Do Sudoeste", "Nova Fatima",
						"Nova Laranjeiras", "Nova Londrina", "Nova Olimpia",
						"Nova Prata Do Iguacu", "Nova Santa Barbara",
						"Nova Santa Rosa", "Nova Tebas", "Novo Itacolomi",
						"Ortigueira", "Ourizona", "Ouro Verde Do Oeste",
						"Paicandu", "Palmas", "Palmeira", "Palmital",
						"Palotina", "Paraiso Do Norte", "Paranacity",
						"Paranagua", "Paranapoema", "Paranavai",
						"Pato Bragado", "Pato Branco", "Paula Freitas",
						"Paulo Frontin", "Peabiru", "Perobal", "Perola",
						"Perola D'oeste", "Pien", "Pinhais",
						"Pinhal De Sao Bento", "Pinhalao", "Pinhao",
						"Pirai Do Sul", "Piraquara", "Pitanga", "Pitangueiras",
						"Planaltina Do Parana", "Planalto", "Ponta Grossa",
						"Pontal Do Parana", "Porecatu", "Porto Amazonas",
						"Porto Barreiro", "Porto Rico", "Porto Vitoria",
						"Prado Ferreira", "Pranchita",
						"Presidente Castelo Branco", "Primeiro De Maio",
						"Prudentopolis", "Quarto Centenario", "Quatigua",
						"Quatro Barras", "Quatro Pontes", "Quedas Do Iguacu",
						"Querencia Do Norte", "Quinta Do Sol", "Quitandinha",
						"Ramilandia", "Rancho Alegre", "Rancho Alegre D'oeste",
						"Realeza", "Reboucas", "Renascenca", "Reserva",
						"Reserva Do Iguacu", "Ribeirao Claro",
						"Ribeirao Do Pinhal", "Rio Azul", "Rio Bom",
						"Rio Bonito Do Iguacu", "Rio Branco Do Ivai",
						"Rio Branco Do Sul", "Rio Negro", "Rolandia",
						"Roncador", "Rondon", "Rosario Do Ivai", "Sabaudia",
						"Salgado Filho", "Salto Do Itarare", "Salto Do Lontra",
						"Santa Amelia", "Santa Cecilia Do Pavao",
						"Santa Cruz De Monte Castelo", "Santa Fe",
						"Santa Isabel Do Ivai", "Santa Izabel Do Oeste",
						"Santa Lucia", "Santa Maria Do Oeste", "Santa Mariana",
						"Santa Monica", "Santa Tereza Do Oeste",
						"Santa Terezinha De Itaipu", "Santana Do Itarare",
						"Santo Antonio Da Platina", "Santo Antonio Do Caiua",
						"Santo Antonio Do Paraiso",
						"Santo Antonio Do Sudoeste", "Santo Inacio",
						"Sao Carlos Do Ivai", "Sao Jeronimo Da Serra",
						"Sao Joao", "Sao Joao Do Caiua", "Sao Joao Do Ivai",
						"Sao Joao Do Triunfo", "Sao Jorge Do Ivai",
						"Sao Jorge Do Patrocinio", "Sao Jorge D'oeste",
						"Sao Jose Da Boa Vista", "Sao Jose Das Palmeiras",
						"Sao Jose Dos Pinhais", "Sao Manoel Do Parana",
						"Sao Mateus Do Sul", "Sao Miguel Do Iguacu",
						"Sao Pedro Do Iguacu", "Sao Pedro Do Ivai",
						"Sao Pedro Do Parana", "Sao Sebastiao Da Amoreira",
						"Sao Tome", "Sapopema", "Sarandi", "Saudade Do Iguacu",
						"Senges", "Serranopolis Do Iguacu", "Sertaneja",
						"Sertanopolis", "Siqueira Campos", "Sulina",
						"Tamarana", "Tamboara", "Tapejara", "Tapira",
						"Teixeira Soares", "Telemaco Borba", "Terra Boa",
						"Terra Rica", "Terra Roxa", "Tibagi", "Tijucas Do Sul",
						"Toledo", "Tomazina", "Tres Barras Do Parana",
						"Tunas Do Parana", "Tuneiras Do Oeste", "Tupassi",
						"Turvo", "Ubirata", "Umuarama", "Uniao Da Vitoria",
						"Uniflor", "Urai", "Ventania", "Vera Cruz Do Oeste",
						"Vere", "Vila Alta", "Virmond", "Vitorino",
						"Wenceslau Braz", "Xambre", "Abreu E Lima",
						"Afogados Da Ingazeira", "Afranio", "Agrestina",
						"Agua Preta", "Aguas Belas", "Alianca", "Altinho",
						"Amaraji", "Angelim", "Aracoiaba", "Araripina",
						"Arcoverde", "Barra De Guabiraba", "Barreiros",
						"Belem De Maria", "Belem De Sao Francisco",
						"Belo Jardim", "Betania", "Bezerros", "Bodoco",
						"Bom Conselho", "Bom Jardim", "Brejao", "Brejinho",
						"Brejo Da Madre De Deus", "Buenos Aires", "Buique",
						"Cabo De Santo Agostinho", "Cabrobo", "Cachoeirinha",
						"Caetes", "Calcado", "Calumbi", "Camaragibe",
						"Camocim De Sao Felix", "Camutanga", "Canhotinho",
						"Capoeiras", "Carnaiba", "Carnaubeira Da Penha",
						"Carpina", "Caruaru", "Casinhas", "Catende", "Cedro",
						"Cha Grande", "Correntes", "Cortes", "Cumaru",
						"Cupira", "Custodia", "Dormentes", "Escada", "Exu",
						"Feira Nova", "Fernando De Noronha", "Ferreiros",
						"Flores", "Frei Miguelinho", "Gameleira", "Garanhuns",
						"Gloria Do Goita", "Goiana", "Granito", "Gravata",
						"Iati", "Ibimirim", "Ibirajuba", "Igarassu",
						"Iguaraci", "Ilha De Itamaraca", "Ingazeira",
						"Ipojuca", "Ipubi", "Itacuruba", "Itaiba", "Itapetim",
						"Itapissuma", "Itaquitinga", "Jaboatao Dos Guararapes",
						"Jaqueira", "Jatauba", "Jatoba", "Joao Alfredo",
						"Joaquim Nabuco", "Jucati", "Jupi", "Jurema",
						"Lagoa Do Carro", "Lagoa Do Itaenga", "Lagoa Do Ouro",
						"Lagoa Dos Gatos", "Lagoa Grande", "Lajedo",
						"Limoeiro", "Macaparana", "Machados", "Manari",
						"Maraial", "Mirandiba", "Moreilandia", "Moreno",
						"Nazare Da Mata", "Olinda", "Orobo", "Oroco",
						"Ouricuri", "Palmares", "Palmeirina", "Panelas",
						"Paranatama", "Parnamirim", "Passira", "Paudalho",
						"Pedra", "Pesqueira", "Petrolandia", "Petrolina",
						"Pocao", "Pombos", "Quipapa", "Recife",
						"Riacho Das Almas", "Ribeirao", "Rio Formoso", "Saire",
						"Salgueiro", "Saloa", "Sanharo",
						"Santa Cruz Da Baixa Verde",
						"Santa Cruz Do Capibaribe", "Santa Filomena",
						"Santa Maria Da Boa Vista", "Santa Maria Do Cambuca",
						"Santa Terezinha", "Sao Benedito Do Sul",
						"Sao Bento Do Una", "Sao Caitano",
						"Sao Joaquim Do Monte", "Sao Jose Da Coroa Grande",
						"Sao Jose Do Belmonte", "Sao Jose Do Egito",
						"Sao Lourenco Da Mata", "Sao Vicente Ferrer",
						"Serra Talhada", "Serrita", "Sertania", "Sirinhaem",
						"Solidao", "Surubim", "Tabira", "Tacaimbo", "Tacaratu",
						"Tamandare", "Taquaritinga Do Norte", "Terezinha",
						"Terra Nova", "Timbauba", "Toritama", "Tracunhaem",
						"Trindade", "Tupanatinga", "Tuparetama", "Venturosa",
						"Verdejante", "Vertente Do Lerio", "Vertentes",
						"Vicencia", "Vitoria De Santo Antao", "Xexeu", "Acaua",
						"Agricolandia", "Alagoinha Do Piaui",
						"Alegrete Do Piaui", "Alto Longa", "Altos",
						"Alvorada Do Gurgueia", "Amarante", "Angical Do Piaui",
						"Anisio De Abreu", "Antonio Almeida", "Aroazes",
						"Arraial", "Assuncao Do Piaui", "Avelino Lopes",
						"Baixa Grande Do Ribeiro", "Barra D'alcantara",
						"Barras", "Barreiras Do Piaui", "Barro Duro",
						"Batalha", "Bela Vista Do Piaui", "Belem Do Piaui",
						"Beneditinos", "Bertolinia", "Betania Do Piaui",
						"Boa Hora", "Bocaina", "Bom Principio Do Piaui",
						"Bonfim Do Piaui", "Boqueirao Do Piaui", "Brasileira",
						"Brejo Do Piaui", "Buriti Dos Lopes",
						"Buriti Dos Montes", "Cabeceiras Do Piaui",
						"Cajazeiras Do Piaui", "Cajueiro Da Praia",
						"Caldeirao Grande Do Piaui", "Campinas Do Piaui",
						"Campo Alegre Do Fidalgo", "Campo Grande Do Piaui",
						"Campo Largo Do Piaui", "Campo Maior", "Canavieira",
						"Canto Do Buriti", "Capitao De Campos",
						"Capitao Gervasio Oliveira", "Caracol",
						"Caraubas Do Piaui", "Caridade Do Piaui",
						"Castelo Do Piaui", "Caxingo", "Cocal",
						"Cocal De Telha", "Cocal Dos Alves", "Coivaras",
						"Colonia Do Gurgueia", "Colonia Do Piaui",
						"Conceicao Do Caninde", "Coronel Jose Dias",
						"Corrente", "Cristalandia Do Piaui", "Cristino Castro",
						"Curimata", "Currais", "Curral Novo Do Piaui",
						"Curralinhos", "Demerval Lobao", "Dirceu Arcoverde",
						"Dom Expedito Lopes", "Dom Inocencio",
						"Domingos Mourao", "Elesbao Veloso", "Eliseu Martins",
						"Esperantina", "Fartura Do Piaui", "Flores Do Piaui",
						"Floresta Do Piaui", "Floriano", "Francinopolis",
						"Francisco Ayres", "Francisco Macedo",
						"Francisco Santos", "Fronteiras", "Geminiano",
						"Gilbues", "Guadalupe", "Guaribas", "Hugo Napoleao",
						"Inhuma", "Ipiranga Do Piaui", "Isaias Coelho",
						"Itainopolis", "Itaueira", "Jacobina Do Piaui",
						"Jaicos", "Jardim Do Mulato", "Jatoba Do Piaui",
						"Jerumenha", "Joao Costa", "Joaquim Pires",
						"Joca Marques", "Jose De Freitas", "Juazeiro Do Piaui",
						"Julio Borges", "Lagoa Alegre",
						"Lagoa De Sao Francisco", "Lagoa Do Barro Do Piaui",
						"Lagoa Do Piaui", "Lagoa Do Sitio",
						"Lagoinha Do Piaui", "Landri Sales", "Luis Correia",
						"Luzilandia", "Madeiro", "Manoel Emidio",
						"Marcolandia", "Marcos Parente", "Massape Do Piaui",
						"Matias Olimpio", "Miguel Alves", "Miguel Leao",
						"Milton Brandao", "Monsenhor Gil",
						"Monsenhor Hipolito", "Monte Alegre Do Piaui",
						"Morro Cabeca No Tempo", "Morro Do Chapeu Do Piaui",
						"Murici Dos Portelas", "Nazare Do Piaui",
						"Nossa Senhora De Nazare",
						"Nossa Senhora Dos Remedios", "Nova Santa Rita",
						"Novo Oriente Do Piaui", "Novo Santo Antonio",
						"Oeiras", "Olho D'agua Do Piaui", "Padre Marcos",
						"Paes Landim", "Pajeu Do Piaui", "Palmeira Do Piaui",
						"Palmeirais", "Paqueta", "Parnagua", "Parnaiba",
						"Passagem Franca Do Piaui", "Patos Do Piaui",
						"Pau D'arco Do Piaui", "Paulistana", "Pavussu",
						"Pedro Ii", "Pedro Laurentino", "Picos", "Pimenteiras",
						"Pio Ix", "Piracuruca", "Piripiri", "Porto",
						"Porto Alegre Do Piaui", "Prata Do Piaui",
						"Queimada Nova", "Redencao Do Gurgueia", "Regeneracao",
						"Riacho Frio", "Ribeira Do Piaui", "Ribeiro Goncalves",
						"Rio Grande Do Piaui", "Santa Cruz Do Piaui",
						"Santa Cruz Dos Milagres", "Santa Luz",
						"Santa Rosa Do Piaui", "Santana Do Piaui",
						"Santo Antonio De Lisboa",
						"Santo Antonio Dos Milagres", "Santo Inacio Do Piaui",
						"Sao Braz Do Piaui", "Sao Felix Do Piaui",
						"Sao Francisco De Assis Do Piaui",
						"Sao Francisco Do Piaui", "Sao Goncalo Do Gurgueia",
						"Sao Goncalo Do Piaui", "Sao Joao Da Canabrava",
						"Sao Joao Da Fronteira", "Sao Joao Da Serra",
						"Sao Joao Da Varjota", "Sao Joao Do Arraial",
						"Sao Joao Do Piaui", "Sao Jose Do Divino",
						"Sao Jose Do Peixe", "Sao Jose Do Piaui", "Sao Juliao",
						"Sao Lourenco Do Piaui", "Sao Luis Do Piaui",
						"Sao Miguel Da Baixa Grande", "Sao Miguel Do Fidalgo",
						"Sao Miguel Do Tapuio", "Sao Pedro Do Piaui",
						"Sao Raimundo Nonato", "Sebastiao Barros",
						"Sebastiao Leal", "Sigefredo Pacheco", "Simoes",
						"Simplicio Mendes", "Socorro Do Piaui", "Sussuapara",
						"Tamboril Do Piaui", "Tanque Do Piaui", "Teresina",
						"Uniao", "Urucui", "Valenca Do Piaui", "Varzea Branca",
						"Varzea Grande", "Vera Mendes", "Vila Nova Do Piaui",
						"Wall Ferraz", "Angra Dos Reis", "Aperibe", "Araruama",
						"Areal", "Armacao Dos Buzios", "Arraial Do Cabo",
						"Barra Do Pirai", "Barra Mansa", "Belford Roxo",
						"Bom Jesus Do Itabapoana", "Cabo Frio",
						"Cachoeiras De Macacu", "Cambuci",
						"Campos Dos Goytacazes", "Carapebus",
						"Cardoso Moreira", "Carmo", "Casimiro De Abreu",
						"Comendador Levy Gasparian", "Conceicao De Macabu",
						"Cordeiro", "Duas Barras", "Duque De Caxias",
						"Engenheiro Paulo De Frontin", "Guapimirim",
						"Iguaba Grande", "Itaborai", "Itaguai", "Italva",
						"Itaocara", "Itaperuna", "Itatiaia", "Japeri",
						"Laje Do Muriae", "Macae", "Macuco", "Mage",
						"Mangaratiba", "Marica", "Mendes", "Mesquita",
						"Miguel Pereira", "Miracema", "Natividade",
						"Nilopolis", "Niteroi", "Nova Friburgo", "Nova Iguacu",
						"Paracambi", "Paraiba Do Sul", "Parati",
						"Paty Do Alferes", "Petropolis", "Pinheiral", "Pirai",
						"Porciuncula", "Porto Real", "Quatis", "Queimados",
						"Quissama", "Resende", "Rio Bonito", "Rio Claro",
						"Rio Das Flores", "Rio Das Ostras", "Rio De Janeiro",
						"Santa Maria Madalena", "Santo Antonio De Padua",
						"Sao Fidelis", "Sao Francisco De Itabapoana",
						"Sao Goncalo", "Sao Joao Da Barra",
						"Sao Joao De Meriti", "Sao Jose De Uba",
						"Sao Jose Do Vale Do Rio Preto", "Sao Pedro Da Aldeia",
						"Sao Sebastiao Do Alto", "Saquarema", "Seropedica",
						"Silva Jardim", "Sumidouro", "Tangua", "Teresopolis",
						"Trajano De Morais", "Tres Rios", "Valenca",
						"Varre-sai", "Vassouras", "Volta Redonda", "Acari",
						"Acu", "Afonso Bezerra", "Agua Nova", "Alexandria",
						"Almino Afonso", "Alto Do Rodrigues", "Angicos",
						"Antonio Martins", "Apodi", "Areia Branca", "Ares",
						"Augusto Severo", "Baia Formosa", "Barcelona",
						"Bento Fernandes", "Bodo", "Caicara Do Norte",
						"Caicara Do Rio Do Vento", "Caico", "Campo Redondo",
						"Canguaretama", "Carnauba Dos Dantas", "Carnaubais",
						"Ceara-mirim", "Cerro Cora", "Coronel Ezequiel",
						"Coronel Joao Pessoa", "Cruzeta", "Currais Novos",
						"Doutor Severiano", "Encanto", "Equador",
						"Espirito Santo", "Extremoz", "Felipe Guerra",
						"Fernando Pedroza", "Florania", "Francisco Dantas",
						"Frutuoso Gomes", "Galinhos", "Goianinha",
						"Governador Dix-sept Rosado", "Grossos", "Guamare",
						"Ielmo Marinho", "Ipanguacu", "Ipueira", "Itaja",
						"Itau", "Jacana", "Jandaira", "Janduis",
						"Januario Cicco", "Japi", "Jardim De Angicos",
						"Jardim De Piranhas", "Jardim Do Serido",
						"Joao Camara", "Joao Dias", "Jose Da Penha",
						"Jucurutu", "Jundia", "Lagoa D'anta",
						"Lagoa De Pedras", "Lagoa De Velhos", "Lagoa Nova",
						"Lagoa Salgada", "Lajes", "Lajes Pintadas", "Lucrecia",
						"Luis Gomes", "Macaiba", "Macau", "Major Sales",
						"Marcelino Vieira", "Martins", "Maxaranguape",
						"Messias Targino", "Montanhas", "Monte Das Gameleiras",
						"Mossoro", "Natal", "Nisia Floresta", "Nova Cruz",
						"Olho-d'agua Do Borges", "Ouro Branco", "Parana",
						"Parau", "Parazinho", "Parelhas", "Passa E Fica",
						"Patu", "Pau Dos Ferros", "Pedra Grande",
						"Pedra Preta", "Pedro Avelino", "Pedro Velho",
						"Pendencias", "Poco Branco", "Portalegre",
						"Porto Do Mangue", "Presidente Juscelino", "Pureza",
						"Rafael Fernandes", "Rafael Godeiro", "Riacho Da Cruz",
						"Riacho De Santana", "Riachuelo", "Rio Do Fogo",
						"Rodolfo Fernandes", "Ruy Barbosa", "Santa Maria",
						"Santana Do Matos", "Santana Do Serido",
						"Santo Antonio", "Sao Bento Do Norte",
						"Sao Bento Do Trairi", "Sao Fernando",
						"Sao Francisco Do Oeste", "Sao Goncalo Do Amarante",
						"Sao Joao Do Sabugi", "Sao Jose De Mipibu",
						"Sao Jose Do Campestre", "Sao Jose Do Serido",
						"Sao Miguel", "Sao Miguel De Touros",
						"Sao Paulo Do Potengi", "Sao Pedro", "Sao Rafael",
						"Sao Vicente", "Senador Eloi De Souza",
						"Senador Georgino Avelino", "Serra De Sao Bento",
						"Serra Do Mel", "Serra Negra Do Norte", "Serrinha",
						"Serrinha Dos Pintos", "Severiano Melo", "Sitio Novo",
						"Taboleiro Grande", "Taipu", "Tangara",
						"Tenente Ananias", "Tenente Laurentino Cruz", "Tibau",
						"Tibau Do Sul", "Timbauba Dos Batistas", "Touros",
						"Triunfo Potiguar", "Umarizal", "Upanema", "Venha-ver",
						"Vera Cruz", "Vicosa", "Vila Flor", "Acegua",
						"Agua Santa", "Agudo", "Ajuricaba", "Alecrim",
						"Alegrete", "Alegria", "Alpestre", "Alto Alegre",
						"Alto Feliz", "Alvorada", "Amaral Ferrador",
						"Ametista Do Sul", "Andre Da Rocha", "Anta Gorda",
						"Antonio Prado", "Arambare", "Ararica", "Aratiba",
						"Arroio Do Meio", "Arroio Do Padre", "Arroio Do Sal",
						"Arroio Do Tigre", "Arroio Dos Ratos", "Arroio Grande",
						"Arvorezinha", "Augusto Pestana", "Aurea", "Bage",
						"Balneario Pinhal", "Barao", "Barao De Cotegipe",
						"Barao Do Triunfo", "Barra Do Quarai",
						"Barra Do Ribeiro", "Barra Funda", "Barracao",
						"Barros Cassal", "Bento Goncalves",
						"Boa Vista Das Missoes", "Boa Vista Do Burica",
						"Boa Vista Do Cadeado", "Boa Vista Do Sul",
						"Bom Principio", "Bom Progresso", "Bom Retiro Do Sul",
						"Boqueirao Do Leao", "Bossoroca", "Braga", "Brochier",
						"Butia", "Cacapava Do Sul", "Cacequi",
						"Cachoeira Do Sul", "Cacique Doble", "Caibate",
						"Camaqua", "Camargo", "Cambara Do Sul",
						"Campestre Da Serra", "Campina Das Missoes",
						"Campinas Do Sul", "Campo Bom", "Campo Novo",
						"Campos Borges", "Candelaria", "Candido Godoi",
						"Candiota", "Canela", "Cangucu", "Canoas",
						"Capao Bonito Do Sul", "Capao Da Canoa",
						"Capao Do Cipo", "Capao Do Leao", "Capela De Santana",
						"Capitao", "Capivari Do Sul", "Caraa", "Carazinho",
						"Carlos Barbosa", "Casca", "Caseiros", "Catuipe",
						"Caxias Do Sul", "Cerrito", "Cerro Branco",
						"Cerro Grande", "Cerro Grande Do Sul", "Cerro Largo",
						"Chapada", "Charqueadas", "Charrua", "Chiapetta",
						"Chui", "Cidreira", "Ciriaco", "Colinas", "Condor",
						"Constantina", "Coqueiro Baixo", "Coronel Barros",
						"Coronel Bicaco", "Coronel Pilar", "Cotipora",
						"Coxilha", "Crissiumal", "Cristal", "Cruz Alta",
						"David Canabarro", "Dezesseis De Novembro",
						"Dois Irmaos", "Dois Irmaos Das Missoes",
						"Dois Lajeados", "Dom Feliciano", "Dom Pedrito",
						"Dom Pedro De Alcantara", "Dona Francisca",
						"Doutor Mauricio Cardoso", "Eldorado Do Sul",
						"Encantado", "Encruzilhada Do Sul", "Engenho Velho",
						"Entre Rios Do Sul", "Entre-ijuis", "Erebango",
						"Erechim", "Ernestina", "Erval Grande", "Erval Seco",
						"Esmeralda", "Espumoso", "Estacao", "Estancia Velha",
						"Esteio", "Estrela", "Eugenio De Castro",
						"Fagundes Varela", "Farroupilha", "Faxinal Do Soturno",
						"Faxinalzinho", "Fazenda Vilanova", "Feliz",
						"Flores Da Cunha", "Fontoura Xavier", "Formigueiro",
						"Fortaleza Dos Valos", "Frederico Westphalen",
						"Garibaldi", "Gaurama", "General Camara",
						"Getulio Vargas", "Girua", "Glorinha", "Gramado",
						"Gravatai", "Guabiju", "Guaiba", "Guapore",
						"Guarani Das Missoes", "Harmonia", "Herval",
						"Horizontina", "Hulha Negra", "Humaita", "Ibarama",
						"Ibiaca", "Ibiraiaras", "Ibirapuita", "Ibiruba",
						"Igrejinha", "Ijui", "Ilopolis", "Imbe", "Imigrante",
						"Independencia", "Inhacora", "Ipe", "Ipiranga Do Sul",
						"Irai", "Itaara", "Itacurubi", "Itaqui", "Itati",
						"Itatiba Do Sul", "Ivora", "Ivoti", "Jaboticaba",
						"Jacuizinho", "Jacutinga", "Jaguarao", "Jaguari",
						"Jaquirana", "Jari", "Joia", "Julio De Castilhos",
						"Lagoa Dos Tres Cantos", "Lagoa Vermelha", "Lagoao",
						"Lajeado", "Lavras Do Sul", "Liberato Salzano",
						"Lindolfo Collor", "Linha Nova", "Macambara",
						"Machadinho", "Manoel Viana", "Maquine", "Marau",
						"Marcelino Ramos", "Mariana Pimentel", "Mariano Moro",
						"Marques De Souza", "Mata", "Mato Castelhano",
						"Mato Leitao", "Maximiliano De Almeida",
						"Minas Do Leao", "Miraguai", "Montauri",
						"Monte Alegre Dos Campos", "Monte Belo Do Sul",
						"Montenegro", "Mormaco", "Morrinhos Do Sul",
						"Morro Redondo", "Morro Reuter", "Mostardas", "Mucum",
						"Muliterno", "Nao-me-toque", "Nonoai", "Nova Alvorada",
						"Nova Araca", "Nova Bassano", "Nova Boa Vista",
						"Nova Brescia", "Nova Esperanca Do Sul", "Nova Hartz",
						"Nova Padua", "Nova Palma", "Nova Petropolis",
						"Nova Prata", "Nova Roma Do Sul", "Novo Barreiro",
						"Novo Hamburgo", "Novo Machado", "Novo Tiradentes",
						"Osorio", "Paim Filho", "Palmares Do Sul",
						"Palmeira Das Missoes", "Palmitinho", "Panambi",
						"Pantano Grande", "Parai", "Paraiso Do Sul",
						"Pareci Novo", "Parobe", "Passo Do Sobrado",
						"Passo Fundo", "Paulo Bento", "Paverama",
						"Pedras Altas", "Pedro Osorio", "Pejucara", "Pelotas",
						"Picada Cafe", "Pinhal", "Pinhal Da Serra",
						"Pinhal Grande", "Pinheirinho Do Vale",
						"Pinheiro Machado", "Pirapo", "Piratini",
						"Poco Das Antas", "Ponte Preta", "Portao",
						"Porto Alegre", "Porto Lucena", "Porto Maua",
						"Porto Xavier", "Pouso Novo", "Presidente Lucena",
						"Progresso", "Protasio Alves", "Putinga", "Quarai",
						"Quatro Irmaos", "Quevedos", "Quinze De Novembro",
						"Redentora", "Relvado", "Restinga Seca", "Rio Grande",
						"Rio Pardo", "Riozinho", "Roca Sales", "Rodeio Bonito",
						"Rolante", "Ronda Alta", "Rondinha", "Roque Gonzales",
						"Rosario Do Sul", "Saldanha Marinho", "Salto Do Jacui",
						"Salvador Das Missoes", "Salvador Do Sul", "Sananduva",
						"Santa Barbara Do Sul", "Santa Clara Do Sul",
						"Santa Cruz Do Sul", "Santa Margarida Do Sul",
						"Santa Maria Do Herval", "Santa Rosa", "Santa Tereza",
						"Santa Vitoria Do Palmar", "Santana Da Boa Vista",
						"Santana Do Livramento", "Santiago", "Santo Angelo",
						"Santo Antonio Da Patrulha",
						"Santo Antonio Das Missoes", "Santo Antonio Do Palma",
						"Santo Antonio Do Planalto", "Santo Augusto",
						"Santo Cristo", "Santo Expedito Do Sul", "Sao Borja",
						"Sao Domingos Do Sul", "Sao Francisco De Assis",
						"Sao Francisco De Paula", "Sao Gabriel",
						"Sao Jeronimo", "Sao Joao Da Urtiga",
						"Sao Joao Do Polesine", "Sao Jorge",
						"Sao Jose Das Missoes", "Sao Jose Do Herval",
						"Sao Jose Do Hortencio", "Sao Jose Do Inhacora",
						"Sao Jose Do Norte", "Sao Jose Do Ouro",
						"Sao Jose Dos Ausentes", "Sao Leopoldo",
						"Sao Lourenco Do Sul", "Sao Luiz Gonzaga",
						"Sao Marcos", "Sao Martinho", "Sao Martinho Da Serra",
						"Sao Miguel Das Missoes", "Sao Nicolau",
						"Sao Paulo Das Missoes", "Sao Pedro Das Missoes",
						"Sao Pedro Do Butia", "Sao Pedro Do Sul",
						"Sao Sebastiao Do Cai", "Sao Sepe", "Sao Valentim",
						"Sao Valentim Do Sul", "Sao Vendelino",
						"Sao Vicente Do Sul", "Sapiranga", "Sapucaia Do Sul",
						"Seberi", "Sede Nova", "Segredo", "Selbach",
						"Senador Salgado Filho", "Sentinela Do Sul",
						"Serafina Correa", "Serio", "Sertao", "Sertao Santana",
						"Severiano De Almeida", "Silveira Martins", "Sinimbu",
						"Sobradinho", "Tabai", "Tapera", "Tapes", "Taquara",
						"Taquari", "Taquarucu Do Sul", "Tenente Portela",
						"Terra De Areia", "Teutonia", "Tio Hugo", "Toropi",
						"Torres", "Tramandai", "Travesseiro", "Tres Arroios",
						"Tres Cachoeiras", "Tres Coroas", "Tres De Maio",
						"Tres Forquilhas", "Tres Palmeiras", "Tres Passos",
						"Trindade Do Sul", "Tucunduva", "Tunas", "Tupancireta",
						"Tupandi", "Tuparendi", "Turucu", "Uniao Da Serra",
						"Uruguaiana", "Vacaria", "Vale Do Sol", "Vale Real",
						"Vale Verde", "Vanini", "Venancio Aires",
						"Veranopolis", "Vespasiano Correa", "Viadutos",
						"Viamao", "Vicente Dutra", "Victor Graeff",
						"Vila Flores", "Vila Langaro", "Vila Maria",
						"Vila Nova Do Sul", "Vista Alegre",
						"Vista Alegre Do Prata", "Vista Gaucha", "Xangri-la",
						"Alta Floresta D'oeste", "Alto Alegre Dos Parecis",
						"Alto Paraiso", "Alvorada D'oeste", "Ariquemes",
						"Buritis", "Cabixi", "Cacaulandia", "Cacoal",
						"Campo Novo De Rondonia", "Candeias Do Jamari",
						"Castanheiras", "Cerejeiras", "Chupinguaia",
						"Colorado Do Oeste", "Corumbiara", "Costa Marques",
						"Cujubim", "Espigao D'oeste",
						"Governador Jorge Teixeira", "Guajara-mirim",
						"Itapua Do Oeste", "Jaru", "Ji-parana",
						"Machadinho D'oeste", "Ministro Andreazza",
						"Mirante Da Serra", "Monte Negro",
						"Nova Brasilandia D'oeste", "Nova Mamore",
						"Nova Uniao", "Novo Horizonte Do Oeste",
						"Ouro Preto Do Oeste", "Parecis", "Pimenta Bueno",
						"Pimenteiras Do Oeste", "Porto Velho",
						"Presidente Medici", "Primavera De Rondonia",
						"Rio Crespo", "Rolim De Moura", "Santa Luzia D'oeste",
						"Sao Felipe D'oeste", "Sao Francisco Do Guapore",
						"Sao Miguel Do Guapore", "Seringueiras",
						"Teixeiropolis", "Theobroma", "Urupa", "Vale Do Anari",
						"Vale Do Paraiso", "Vilhena", "Amajari", "Boa Vista",
						"Bonfim", "Canta", "Caracarai", "Caroebe", "Iracema",
						"Mucajai", "Normandia", "Pacaraima", "Rorainopolis",
						"Sao Joao Da Baliza", "Sao Luiz", "Uiramuta",
						"Abdon Batista", "Abelardo Luz", "Agrolandia",
						"Agronomica", "Agua Doce", "Aguas De Chapeco",
						"Aguas Frias", "Aguas Mornas", "Alfredo Wagner",
						"Alto Bela Vista", "Anchieta", "Angelina",
						"Anita Garibaldi", "Anitapolis", "Antonio Carlos",
						"Apiuna", "Arabuta", "Araquari", "Ararangua",
						"Armazem", "Arroio Trinta", "Arvoredo", "Ascurra",
						"Atalanta", "Aurora", "Balneario Arroio Do Silva",
						"Balneario Barra Do Sul", "Balneario Camboriu",
						"Balneario Gaivota", "Bandeirante", "Barra Bonita",
						"Barra Velha", "Bela Vista Do Toldo", "Belmonte",
						"Benedito Novo", "Biguacu", "Blumenau",
						"Bocaina Do Sul", "Bom Jardim Da Serra",
						"Bom Jesus Do Oeste", "Bom Retiro", "Bombinhas",
						"Botuvera", "Braco Do Norte", "Braco Do Trombudo",
						"Brunopolis", "Brusque", "Cacador", "Caibi", "Calmon",
						"Camboriu", "Campo Alegre", "Campo Belo Do Sul",
						"Campo Ere", "Campos Novos", "Canelinha", "Canoinhas",
						"Capao Alto", "Capinzal", "Capivari De Baixo",
						"Caxambu Do Sul", "Celso Ramos", "Cerro Negro",
						"Chapadao Do Lageado", "Chapeco", "Cocal Do Sul",
						"Concordia", "Cordilheira Alta", "Coronel Freitas",
						"Coronel Martins", "Correia Pinto", "Corupa",
						"Criciuma", "Cunha Pora", "Cunhatai", "Curitibanos",
						"Descanso", "Dionisio Cerqueira", "Dona Emma",
						"Doutor Pedrinho", "Entre Rios", "Ermo", "Erval Velho",
						"Faxinal Dos Guedes", "Flor Do Sertao",
						"Florianopolis", "Formosa Do Sul", "Forquilhinha",
						"Fraiburgo", "Frei Rogerio", "Galvao", "Garopaba",
						"Garuva", "Gaspar", "Governador Celso Ramos",
						"Grao Para", "Gravatal", "Guabiruba", "Guaraciaba",
						"Guaramirim", "Guaruja Do Sul", "Guatambu",
						"Herval D'oeste", "Ibiam", "Ibicare", "Ibirama",
						"Icara", "Ilhota", "Imarui", "Imbituba", "Imbuia",
						"Indaial", "Iomere", "Ipira", "Ipora Do Oeste",
						"Ipuacu", "Ipumirim", "Iraceminha", "Irani",
						"Irineopolis", "Ita", "Itaiopolis", "Itajai",
						"Itapema", "Itapiranga", "Itapoa", "Ituporanga",
						"Jabora", "Jacinto Machado", "Jaguaruna",
						"Jaragua Do Sul", "Jardinopolis", "Joacaba",
						"Joinville", "Jose Boiteux", "Jupia", "Lacerdopolis",
						"Lages", "Laguna", "Lajeado Grande", "Laurentino",
						"Lauro Muller", "Lebon Regis", "Leoberto Leal",
						"Lindoia Do Sul", "Lontras", "Luiz Alves", "Luzerna",
						"Macieira", "Mafra", "Major Gercino", "Major Vieira",
						"Maracaja", "Maravilha", "Marema", "Matos Costa",
						"Meleiro", "Mirim Doce", "Modelo", "Mondai",
						"Monte Carlo", "Monte Castelo", "Morro Da Fumaca",
						"Morro Grande", "Navegantes", "Nova Erechim",
						"Nova Itaberaba", "Nova Trento", "Nova Veneza",
						"Novo Horizonte", "Orleans", "Otacilio Costa",
						"Ouro Verde", "Paial", "Painel", "Palhoca",
						"Palma Sola", "Palmitos", "Papanduva", "Paraiso",
						"Passo De Torres", "Passos Maia", "Paulo Lopes",
						"Pedras Grandes", "Penha", "Peritiba", "Picarras",
						"Pinhalzinho", "Pinheiro Preto", "Piratuba",
						"Planalto Alegre", "Pomerode", "Ponte Alta",
						"Ponte Alta Do Norte", "Ponte Serrada", "Porto Belo",
						"Porto Uniao", "Pouso Redondo", "Praia Grande",
						"Presidente Getulio", "Presidente Nereu", "Princesa",
						"Quilombo", "Rancho Queimado", "Rio Das Antas",
						"Rio Do Campo", "Rio Do Oeste", "Rio Do Sul",
						"Rio Dos Cedros", "Rio Fortuna", "Rio Negrinho",
						"Rio Rufino", "Riqueza", "Rodeio", "Romelandia",
						"Salete", "Saltinho", "Salto Veloso", "Sangao",
						"Santa Rosa De Lima", "Santa Rosa Do Sul",
						"Santa Terezinha Do Progresso", "Santiago Do Sul",
						"Santo Amaro Da Imperatriz", "Sao Bento Do Sul",
						"Sao Bernardino", "Sao Bonifacio", "Sao Carlos",
						"Sao Cristovao Do Sul", "Sao Domingos",
						"Sao Francisco Do Sul", "Sao Joao Batista",
						"Sao Joao Do Itaperiu", "Sao Joao Do Oeste",
						"Sao Joao Do Sul", "Sao Joaquim", "Sao Jose",
						"Sao Jose Do Cedro", "Sao Jose Do Cerrito",
						"Sao Lourenco Do Oeste", "Sao Ludgero",
						"Sao Miguel Da Boa Vista", "Sao Miguel Do Oeste",
						"Sao Pedro De Alcantara", "Saudades", "Schroeder",
						"Seara", "Serra Alta", "Sideropolis", "Sombrio",
						"Sul Brasil", "Taio", "Tigrinhos", "Tijucas",
						"Timbe Do Sul", "Timbo", "Timbo Grande", "Tres Barras",
						"Treviso", "Treze De Maio", "Treze Tilias",
						"Trombudo Central", "Tubarao", "Tunapolis",
						"Uniao Do Oeste", "Urubici", "Urupema", "Urussanga",
						"Vargeao", "Vargem", "Vargem Bonita", "Vidal Ramos",
						"Videira", "Vitor Meireles", "Witmarsum", "Xanxere",
						"Xavantina", "Xaxim", "Zortea", "Adamantina", "Adolfo",
						"Aguai", "Aguas Da Prata", "Aguas De Lindoia",
						"Aguas De Santa Barbara", "Aguas De Sao Pedro",
						"Agudos", "Alambari", "Alfredo Marcondes", "Altair",
						"Altinopolis", "Aluminio", "Alvares Florence",
						"Alvares Machado", "Alvaro De Carvalho", "Alvinlandia",
						"Americana", "Americo Brasiliense",
						"Americo De Campos", "Analandia", "Andradina",
						"Angatuba", "Anhembi", "Anhumas", "Aparecida D'oeste",
						"Apiai", "Aracariguama", "Aracatuba",
						"Aracoiaba Da Serra", "Aramina", "Arandu", "Arapei",
						"Araraquara", "Araras", "Arco-iris", "Arealva",
						"Areias", "Areiopolis", "Ariranha", "Artur Nogueira",
						"Aruja", "Aspasia", "Assis", "Atibaia", "Auriflama",
						"Avai", "Avanhandava", "Avare", "Bady Bassitt",
						"Balbinos", "Balsamo", "Bananal", "Barao De Antonina",
						"Barbosa", "Bariri", "Barra Do Chapeu",
						"Barra Do Turvo", "Barretos", "Barrinha", "Barueri",
						"Bastos", "Batatais", "Bauru", "Bebedouro",
						"Bento De Abreu", "Bernardino De Campos", "Bertioga",
						"Bilac", "Birigui", "Biritiba-mirim",
						"Boa Esperanca Do Sul", "Bofete", "Boituva",
						"Bom Jesus Dos Perdoes", "Bom Sucesso De Itarare",
						"Bora", "Boraceia", "Borebi", "Botucatu",
						"Braganca Paulista", "Brauna", "Brejo Alegre",
						"Brodowski", "Brotas", "Buri", "Buritama", "Buritizal",
						"Cabralia Paulista", "Cabreuva", "Cacapava",
						"Cachoeira Paulista", "Caconde", "Caiabu", "Caieiras",
						"Caiua", "Cajamar", "Cajati", "Cajobi", "Cajuru",
						"Campina Do Monte Alegre", "Campinas",
						"Campo Limpo Paulista", "Campos Do Jordao",
						"Campos Novos Paulista", "Cananeia", "Canas",
						"Candido Mota", "Candido Rodrigues", "Canitar",
						"Capao Bonito", "Capela Do Alto", "Capivari",
						"Caraguatatuba", "Carapicuiba", "Cardoso",
						"Casa Branca", "Cassia Dos Coqueiros", "Castilho",
						"Catanduva", "Catigua", "Cedral", "Cerqueira Cesar",
						"Cerquilho", "Cesario Lange", "Charqueada",
						"Chavantes", "Clementina", "Colina", "Colombia",
						"Conchal", "Conchas", "Cordeiropolis", "Coroados",
						"Coronel Macedo", "Corumbatai", "Cosmopolis",
						"Cosmorama", "Cotia", "Cravinhos", "Cristais Paulista",
						"Cruzalia", "Cruzeiro", "Cubatao", "Cunha",
						"Descalvado", "Diadema", "Dirce Reis", "Divinolandia",
						"Dobrada", "Dois Corregos", "Dolcinopolis", "Dourado",
						"Dracena", "Duartina", "Dumont", "Echapora",
						"Eldorado", "Elias Fausto", "Elisiario", "Embauba",
						"Embu", "Embu-guacu", "Emilianopolis",
						"Engenheiro Coelho", "Espirito Santo Do Pinhal",
						"Espirito Santo Do Turvo", "Estiva Gerbi",
						"Estrela Do Norte", "Estrela D'oeste",
						"Euclides Da Cunha Paulista", "Fartura",
						"Fernando Prestes", "Fernandopolis", "Fernao",
						"Ferraz De Vasconcelos", "Flora Rica", "Floreal",
						"Florida Paulista", "Florinia", "Franca",
						"Francisco Morato", "Franco Da Rocha",
						"Gabriel Monteiro", "Galia", "Garca", "Gastao Vidigal",
						"Gaviao Peixoto", "General Salgado", "Getulina",
						"Glicerio", "Guaicara", "Guaimbe", "Guapiacu",
						"Guapiara", "Guara", "Guaracai", "Guarani D'oeste",
						"Guaranta", "Guararapes", "Guararema", "Guaratingueta",
						"Guarei", "Guariba", "Guaruja", "Guarulhos",
						"Guatapara", "Guzolandia", "Herculandia", "Holambra",
						"Hortolandia", "Iacanga", "Iacri", "Iaras", "Ibate",
						"Ibira", "Ibirarema", "Ibitinga", "Ibiuna", "Icem",
						"Iepe", "Igaracu Do Tiete", "Igarapava", "Igarata",
						"Iguape", "Ilha Comprida", "Ilha Solteira", "Ilhabela",
						"Indaiatuba", "Indiana", "Indiapora",
						"Inubia Paulista", "Ipaussu", "Ipero", "Ipeuna",
						"Ipigua", "Iporanga", "Ipua", "Iracemapolis", "Irapua",
						"Irapuru", "Itabera", "Itai", "Itajobi", "Itaju",
						"Itanhaem", "Itaoca", "Itapecerica Da Serra",
						"Itapetininga", "Itapeva", "Itapevi", "Itapira",
						"Itapirapua Paulista", "Itapolis", "Itapui", "Itapura",
						"Itaquaquecetuba", "Itarare", "Itariri", "Itatiba",
						"Itatinga", "Itirapina", "Itirapua", "Itobi", "Itu",
						"Itupeva", "Ituverava", "Jaborandi", "Jaboticabal",
						"Jacarei", "Jaci", "Jacupiranga", "Jaguariuna",
						"Jales", "Jambeiro", "Jandira", "Jarinu", "Jau",
						"Jeriquara", "Joanopolis", "Joao Ramalho",
						"Jose Bonifacio", "Julio Mesquita", "Jumirim",
						"Jundiai", "Junqueiropolis", "Juquia", "Juquitiba",
						"Lagoinha", "Laranjal Paulista", "Lavinia",
						"Lavrinhas", "Leme", "Lencois Paulista", "Limeira",
						"Lindoia", "Lins", "Lorena", "Lourdes", "Louveira",
						"Lucelia", "Lucianopolis", "Luis Antonio", "Luiziania",
						"Lupercio", "Lutecia", "Macatuba", "Macaubal",
						"Macedonia", "Magda", "Mairinque", "Mairipora",
						"Manduri", "Maraba Paulista", "Maracai", "Marapoama",
						"Mariapolis", "Marilia", "Marinopolis", "Martinopolis",
						"Matao", "Maua", "Mendonca", "Meridiano", "Mesopolis",
						"Miguelopolis", "Mineiros Do Tiete", "Mira Estrela",
						"Miracatu", "Mirandopolis", "Mirante Do Paranapanema",
						"Mirassol", "Mirassolandia", "Mococa", "Mogi Guacu",
						"Mogi Das Cruzes", "Mogi-mirim", "Mombuca", "Moncoes",
						"Mongagua", "Monte Alegre Do Sul", "Monte Alto",
						"Monte Aprazivel", "Monte Azul Paulista", "Monte Mor",
						"Monteiro Lobato", "Morro Agudo", "Morungaba",
						"Motuca", "Murutinga Do Sul", "Nantes", "Narandiba",
						"Natividade Da Serra", "Nazare Paulista",
						"Neves Paulista", "Nhandeara", "Nipoa", "Nova Alianca",
						"Nova Campina", "Nova Canaa Paulista", "Nova Castilho",
						"Nova Europa", "Nova Granada", "Nova Guataporanga",
						"Nova Independencia", "Nova Luzitania", "Nova Odessa",
						"Novais", "Nuporanga", "Ocaucu", "Oleo", "Olimpia",
						"Onda Verde", "Oriente", "Orindiuva", "Orlandia",
						"Osasco", "Oscar Bressane", "Osvaldo Cruz", "Ourinhos",
						"Ouroeste", "Pacaembu", "Palestina",
						"Palmares Paulista", "Palmeira D'oeste", "Panorama",
						"Paraguacu Paulista", "Paraibuna", "Paranapanema",
						"Paranapua", "Parapua", "Pardinho", "Pariquera-acu",
						"Parisi", "Patrocinio Paulista", "Pauliceia",
						"Paulinia", "Paulistania", "Paulo De Faria",
						"Pederneiras", "Pedra Bela", "Pedranopolis",
						"Pedregulho", "Pedreira", "Pedrinhas Paulista",
						"Pedro De Toledo", "Penapolis", "Pereira Barreto",
						"Pereiras", "Peruibe", "Piacatu", "Piedade",
						"Pilar Do Sul", "Pindamonhangaba", "Pindorama",
						"Piquerobi", "Piquete", "Piracaia", "Piracicaba",
						"Piraju", "Pirajui", "Pirangi",
						"Pirapora Do Bom Jesus", "Pirapozinho", "Pirassununga",
						"Piratininga", "Platina", "Poa", "Poloni", "Pompeia",
						"Pongai", "Pontal", "Pontalinda", "Pontes Gestal",
						"Populina", "Porangaba", "Porto Feliz",
						"Porto Ferreira", "Potim", "Potirendaba", "Pracinha",
						"Pradopolis", "Pratania", "Presidente Alves",
						"Presidente Bernardes", "Presidente Epitacio",
						"Presidente Prudente", "Presidente Venceslau",
						"Promissao", "Quadra", "Quata", "Queiroz", "Queluz",
						"Quintana", "Rafard", "Rancharia", "Redencao Da Serra",
						"Regente Feijo", "Reginopolis", "Registro", "Restinga",
						"Ribeira", "Ribeirao Bonito", "Ribeirao Branco",
						"Ribeirao Corrente", "Ribeirao Do Sul",
						"Ribeirao Dos Indios", "Ribeirao Grande",
						"Ribeirao Pires", "Ribeirao Preto", "Rifaina",
						"Rincao", "Rinopolis", "Rio Das Pedras",
						"Rio Grande Da Serra", "Riolandia", "Riversul",
						"Rosana", "Roseira", "Rubiacea", "Rubineia", "Sabino",
						"Sagres", "Sales", "Sales Oliveira", "Salesopolis",
						"Salmourao", "Salto", "Salto De Pirapora",
						"Salto Grande", "Sandovalina", "Santa Adelia",
						"Santa Albertina", "Santa Barbara D'oeste",
						"Santa Branca", "Santa Clara D'oeste",
						"Santa Cruz Da Conceicao", "Santa Cruz Da Esperanca",
						"Santa Cruz Das Palmeiras", "Santa Cruz Do Rio Pardo",
						"Santa Ernestina", "Santa Fe Do Sul",
						"Santa Gertrudes", "Santa Isabel",
						"Santa Maria Da Serra", "Santa Mercedes",
						"Santa Rita Do Passa Quatro", "Santa Rita D'oeste",
						"Santa Rosa De Viterbo", "Santa Salete",
						"Santana Da Ponte Pensa", "Santana De Parnaiba",
						"Santo Anastacio", "Santo Antonio Da Alegria",
						"Santo Antonio De Posse", "Santo Antonio Do Aracangua",
						"Santo Antonio Do Jardim", "Santo Antonio Do Pinhal",
						"Santo Expedito", "Santopolis Do Aguapei", "Santos",
						"Sao Bento Do Sapucai", "Sao Bernardo Do Campo",
						"Sao Caetano Do Sul", "Sao Joao Da Boa Vista",
						"Sao Joao Das Duas Pontes", "Sao Joao De Iracema",
						"Sao Joao Do Pau D'alho", "Sao Joaquim Da Barra",
						"Sao Jose Da Bela Vista", "Sao Jose Do Barreiro",
						"Sao Jose Do Rio Pardo", "Sao Jose Do Rio Preto",
						"Sao Jose Dos Campos", "Sao Lourenco Da Serra",
						"Sao Luis Do Paraitinga", "Sao Manuel",
						"Sao Miguel Arcanjo", "Sao Paulo",
						"Sao Pedro Do Turvo", "Sao Roque", "Sao Sebastiao",
						"Sao Sebastiao Da Grama", "Sao Simao", "Sarapui",
						"Sarutaia", "Sebastianopolis Do Sul", "Serra Azul",
						"Serra Negra", "Serrana", "Sete Barras", "Severinia",
						"Silveiras", "Socorro", "Sorocaba", "Sud Mennucci",
						"Sumare", "Suzanapolis", "Suzano", "Tabapua",
						"Tabatinga", "Taboao Da Serra", "Taciba", "Taguai",
						"Taiacu", "Taiuva", "Tambau", "Tanabi", "Tapirai",
						"Tapiratiba", "Taquaral", "Taquaritinga",
						"Taquarituba", "Taquarivai", "Tarabai", "Taruma",
						"Tatui", "Taubate", "Tejupa", "Teodoro Sampaio",
						"Tiete", "Timburi", "Torre De Pedra", "Torrinha",
						"Trabiju", "Tremembe", "Tres Fronteiras", "Tuiuti",
						"Tupa", "Tupi Paulista", "Turiuba", "Turmalina",
						"Ubarana", "Ubatuba", "Ubirajara", "Uchoa",
						"Uniao Paulista", "Urania", "Uru", "Urupes",
						"Valentim Gentil", "Valinhos", "Valparaiso",
						"Vargem Grande Do Sul", "Vargem Grande Paulista",
						"Varzea Paulista", "Vinhedo", "Viradouro",
						"Vista Alegre Do Alto", "Vitoria Brasil", "Votorantim",
						"Votuporanga", "Zacarias", "Amparo De Sao Francisco",
						"Aquidaba", "Aracaju", "Araua", "Barra Dos Coqueiros",
						"Boquim", "Brejo Grande", "Campo Do Brito", "Canhoba",
						"Caninde De Sao Francisco", "Capela", "Carira",
						"Carmopolis", "Cedro De Sao Joao", "Cristinapolis",
						"Cumbe", "Divina Pastora", "Estancia", "Frei Paulo",
						"Gararu", "General Maynard", "Gracho Cardoso",
						"Ilha Das Flores", "Indiaroba", "Itabaianinha",
						"Itabi", "Itaporanga D'ajuda", "Japaratuba", "Japoata",
						"Lagarto", "Laranjeiras", "Malhada Dos Bois",
						"Malhador", "Maruim", "Moita Bonita",
						"Monte Alegre De Sergipe", "Muribeca", "Neopolis",
						"Nossa Senhora Aparecida", "Nossa Senhora Da Gloria",
						"Nossa Senhora Das Dores", "Nossa Senhora De Lourdes",
						"Nossa Senhora Do Socorro", "Pacatuba", "Pedra Mole",
						"Pedrinhas", "Pirambu", "Poco Redondo", "Poco Verde",
						"Porto Da Folha", "Propria", "Riachao Do Dantas",
						"Ribeiropolis", "Rosario Do Catete", "Salgado",
						"Santa Luzia Do Itanhy", "Santana Do Sao Francisco",
						"Santo Amaro Das Brotas", "Sao Cristovao",
						"Sao Miguel Do Aleixo", "Simao Dias", "Siriri",
						"Telha", "Tobias Barreto", "Tomar Do Geru", "Umbauba",
						"Abreulandia", "Aguiarnopolis", "Alianca Do Tocantins",
						"Almas", "Ananas", "Angico", "Aparecida Do Rio Negro",
						"Aragominas", "Araguacema", "Araguacu", "Araguaina",
						"Araguana", "Araguatins", "Arapoema", "Arraias",
						"Augustinopolis", "Aurora Do Tocantins",
						"Axixa Do Tocantins", "Babaculandia",
						"Bandeirantes Do Tocantins", "Barra Do Ouro",
						"Barrolandia", "Bernardo Sayao",
						"Brasilandia Do Tocantins", "Brejinho De Nazare",
						"Buriti Do Tocantins", "Campos Lindos",
						"Cariri Do Tocantins", "Carmolandia",
						"Carrasco Bonito", "Caseara", "Centenario",
						"Chapada Da Natividade", "Chapada De Areia",
						"Colinas Do Tocantins", "Colmeia", "Combinado",
						"Conceicao Do Tocantins", "Couto De Magalhaes",
						"Cristalandia", "Crixas Do Tocantins", "Darcinopolis",
						"Dianopolis", "Divinopolis Do Tocantins",
						"Dois Irmaos Do Tocantins", "Duere", "Fatima",
						"Figueiropolis", "Filadelfia", "Formoso Do Araguaia",
						"Fortaleza Do Tabocao", "Goianorte", "Goiatins",
						"Guarai", "Gurupi", "Ipueiras", "Itacaja",
						"Itaguatins", "Itapiratins", "Itapora Do Tocantins",
						"Jau Do Tocantins", "Juarina", "Lagoa Da Confusao",
						"Lagoa Do Tocantins", "Lavandeira", "Lizarda",
						"Luzinopolis", "Marianopolis Do Tocantins", "Mateiros",
						"Maurilandia Do Tocantins", "Miracema Do Tocantins",
						"Miranorte", "Monte Do Carmo", "Muricilandia",
						"Nazare", "Nova Rosalandia", "Novo Acordo",
						"Novo Alegre", "Novo Jardim", "Palmeirante",
						"Palmeiras Do Tocantins", "Palmeiropolis",
						"Paraiso Do Tocantins", "Pedro Afonso", "Peixe",
						"Pequizeiro", "Pindorama Do Tocantins", "Piraque",
						"Pium", "Ponte Alta Do Bom Jesus",
						"Ponte Alta Do Tocantins", "Porto Alegre Do Tocantins",
						"Porto Nacional", "Praia Norte", "Presidente Kennedy",
						"Pugmil", "Recursolandia", "Riachinho",
						"Rio Da Conceicao", "Rio Dos Bois", "Rio Sono",
						"Sampaio", "Sandolandia", "Santa Fe Do Araguaia",
						"Santa Maria Do Tocantins", "Santa Rita Do Tocantins",
						"Santa Rosa Do Tocantins", "Santa Tereza Do Tocantins",
						"Santa Terezinha Do Tocantins",
						"Sao Bento Do Tocantins", "Sao Felix Do Tocantins",
						"Sao Miguel Do Tocantins", "Sao Salvador Do Tocantins",
						"Sao Sebastiao Do Tocantins",
						"Sao Valerio Da Natividade", "Silvanopolis",
						"Sitio Novo Do Tocantins", "Sucupira", "Taguatinga",
						"Talisma", "Tocantinia", "Tocantinopolis", "Tupirama",
						"Tupiratins", "Wanderlandia", "Xambioa", "Acrelandia",
						"Assis Brasil", "Brasileia", "Bujari", "Capixaba",
						"Epitaciolandia", "Feijo", "Jordao", "Mancio Lima",
						"Manoel Urbano", "Marechal Thaumaturgo",
						"Placido De Castro", "Porto Acre", "Porto Walter",
						"Rio Branco", "Rodrigues Alves", "Santa Rosa Do Purus",
						"Sena Madureira", "Senador Guiomard", "Tarauaca",
						"Xapuri", "Abadia Dos Dourados", "Abaete",
						"Abre Campo", "Acaiaca", "Acucena", "Agua Boa",
						"Agua Comprida", "Aguanil", "Aguas Formosas",
						"Aguas Vermelhas", "Aimores", "Aiuruoca", "Alagoa",
						"Albertina", "Alem Paraiba", "Alfenas",
						"Alfredo Vasconcelos", "Almenara", "Alpercata",
						"Alpinopolis", "Alterosa", "Alto Caparao",
						"Alto Jequitiba", "Alto Rio Doce", "Alvarenga",
						"Alvinopolis", "Alvorada De Minas", "Amparo Do Serra",
						"Andradas", "Andrelandia", "Antonio Dias",
						"Antonio Prado De Minas", "Aracai", "Aracitaba",
						"Aracuai", "Araguari", "Arantina", "Araponga",
						"Arapora", "Araujos", "Araxa", "Arceburgo", "Arcos",
						"Areado", "Argirita", "Aricanduva", "Arinos",
						"Astolfo Dutra", "Ataleia", "Augusto De Lima",
						"Baependi", "Baldim", "Bambui", "Bandeira",
						"Bandeira Do Sul", "Barao De Cocais",
						"Barao De Monte Alto", "Barbacena", "Barra Longa",
						"Barroso", "Bela Vista De Minas", "Belmiro Braga",
						"Belo Horizonte", "Belo Oriente", "Belo Vale",
						"Berilo", "Berizal", "Bertopolis", "Betim",
						"Bias Fortes", "Bicas", "Biquinhas",
						"Bocaina De Minas", "Bocaiuva", "Bom Despacho",
						"Bom Jardim De Minas", "Bom Jesus Da Penha",
						"Bom Jesus Do Amparo", "Bom Jesus Do Galho",
						"Bom Repouso", "Bonfinopolis De Minas",
						"Bonito De Minas", "Borda Da Mata", "Botelhos",
						"Botumirim", "Bras Pires", "Brasilandia De Minas",
						"Brasilia De Minas", "Brasopolis", "Braunas",
						"Brumadinho", "Bueno Brandao", "Buenopolis", "Bugre",
						"Buritizeiro", "Cabeceira Grande", "Cabo Verde",
						"Cachoeira Da Prata", "Cachoeira De Minas",
						"Cachoeira De Pajeu", "Cachoeira Dourada",
						"Caetanopolis", "Caete", "Caiana", "Cajuri", "Caldas",
						"Camacho", "Camanducaia", "Cambui", "Cambuquira",
						"Campanario", "Campanha", "Campestre", "Campina Verde",
						"Campo Azul", "Campo Belo", "Campo Do Meio",
						"Campo Florido", "Campos Altos", "Campos Gerais",
						"Cana Verde", "Canaa", "Canapolis", "Candeias",
						"Caparao", "Capela Nova", "Capelinha", "Capetinga",
						"Capim Branco", "Capinopolis", "Capitao Andrade",
						"Capitao Eneas", "Capitolio", "Caputira", "Carai",
						"Caranaiba", "Carandai", "Carangola", "Caratinga",
						"Carbonita", "Careacu", "Carlos Chagas", "Carmesia",
						"Carmo Da Cachoeira", "Carmo Da Mata",
						"Carmo De Minas", "Carmo Do Cajuru",
						"Carmo Do Paranaiba", "Carmo Do Rio Claro",
						"Carmopolis De Minas", "Carneirinho", "Carrancas",
						"Carvalhopolis", "Carvalhos", "Casa Grande",
						"Cascalho Rico", "Cassia", "Cataguases", "Catas Altas",
						"Catas Altas Da Noruega", "Catuji", "Catuti",
						"Caxambu", "Cedro Do Abaete", "Central De Minas",
						"Centralina", "Chacara", "Chale", "Chapada Do Norte",
						"Chapada Gaucha", "Chiador", "Cipotanea", "Claraval",
						"Claro Dos Pocoes", "Claudio", "Coimbra", "Coluna",
						"Comendador Gomes", "Comercinho",
						"Conceicao Da Aparecida",
						"Conceicao Da Barra De Minas", "Conceicao Das Alagoas",
						"Conceicao Das Pedras", "Conceicao De Ipanema",
						"Conceicao Do Mato Dentro", "Conceicao Do Para",
						"Conceicao Do Rio Verde", "Conceicao Dos Ouros",
						"Conego Marinho", "Confins", "Congonhal", "Congonhas",
						"Congonhas Do Norte", "Conquista",
						"Conselheiro Lafaiete", "Conselheiro Pena",
						"Consolacao", "Contagem", "Coqueiral",
						"Coracao De Jesus", "Cordisburgo", "Cordislandia",
						"Corinto", "Coroaci", "Coromandel",
						"Coronel Fabriciano", "Coronel Murta",
						"Coronel Pacheco", "Coronel Xavier Chaves",
						"Corrego Danta", "Corrego Do Bom Jesus",
						"Corrego Fundo", "Corrego Novo",
						"Couto De Magalhaes De Minas", "Crisolita", "Cristais",
						"Cristalia", "Cristiano Otoni", "Cristina",
						"Crucilandia", "Cruzeiro Da Fortaleza", "Cruzilia",
						"Cuparaque", "Curral De Dentro", "Curvelo", "Datas",
						"Delfim Moreira", "Delfinopolis", "Delta",
						"Descoberto", "Desterro De Entre Rios",
						"Desterro Do Melo", "Diamantina",
						"Diogo De Vasconcelos", "Dionisio", "Divinesia",
						"Divino", "Divino Das Laranjeiras",
						"Divinolandia De Minas", "Divinopolis",
						"Divisa Alegre", "Divisa Nova", "Divisopolis",
						"Dom Bosco", "Dom Cavati", "Dom Joaquim",
						"Dom Silverio", "Dom Vicoso", "Dona Eusebia",
						"Dores De Campos", "Dores De Guanhaes",
						"Dores Do Indaia", "Dores Do Turvo", "Doresopolis",
						"Douradoquara", "Durande", "Eloi Mendes",
						"Engenheiro Caldas", "Engenheiro Navarro",
						"Entre Folhas", "Entre Rios De Minas", "Ervalia",
						"Esmeraldas", "Espera Feliz", "Espinosa",
						"Espirito Santo Do Dourado", "Estiva", "Estrela Dalva",
						"Estrela Do Indaia", "Estrela Do Sul", "Eugenopolis",
						"Ewbank Da Camara", "Extrema", "Fama", "Faria Lemos",
						"Felicio Dos Santos", "Felisburgo", "Felixlandia",
						"Fernandes Tourinho", "Ferros", "Fervedouro",
						"Florestal", "Formiga", "Formoso",
						"Fortaleza De Minas", "Fortuna De Minas",
						"Francisco Badaro", "Francisco Dumont", "Francisco Sa",
						"Franciscopolis", "Frei Gaspar", "Frei Inocencio",
						"Frei Lagonegro", "Fronteira", "Fronteira Dos Vales",
						"Fruta De Leite", "Frutal", "Funilandia", "Galileia",
						"Gameleiras", "Glaucilandia", "Goiabeira", "Goncalves",
						"Gonzaga", "Gouveia", "Governador Valadares",
						"Grao Mogol", "Grupiara", "Guanhaes", "Guape",
						"Guaraciama", "Guaranesia", "Guarani", "Guarara",
						"Guarda-mor", "Guaxupe", "Guidoval", "Guimarania",
						"Guiricema", "Gurinhata", "Heliodora", "Iapu",
						"Ibertioga", "Ibia", "Ibiai", "Ibiracatu", "Ibiraci",
						"Ibirite", "Ibitiura De Minas", "Ibituruna",
						"Icarai De Minas", "Igarape", "Igaratinga", "Iguatama",
						"Ijaci", "Ilicinea", "Imbe De Minas", "Inconfidentes",
						"Indaiabira", "Ingai", "Inhapim", "Inhauma",
						"Inimutaba", "Ipaba", "Ipanema", "Ipatinga", "Ipiacu",
						"Ipuiuna", "Irai De Minas", "Itabira",
						"Itabirinha De Mantena", "Itabirito", "Itacambira",
						"Itacarambi", "Itaguara", "Itaipe", "Itajuba",
						"Itamarandiba", "Itamarati De Minas", "Itambacuri",
						"Itambe Do Mato Dentro", "Itamogi", "Itamonte",
						"Itanhandu", "Itanhomi", "Itaobim", "Itapagipe",
						"Itapecerica", "Itatiaiucu", "Itau De Minas", "Itauna",
						"Itaverava", "Itinga", "Itueta", "Ituiutaba",
						"Itumirim", "Iturama", "Itutinga", "Jaboticatubas",
						"Jacinto", "Jacui", "Jaguaracu", "Jaiba", "Jampruca",
						"Janauba", "Januaria", "Japaraiba", "Japonvar",
						"Jeceaba", "Jenipapo De Minas", "Jequeri", "Jequitai",
						"Jequitiba", "Jequitinhonha", "Jesuania", "Joaima",
						"Joanesia", "Joao Monlevade", "Joao Pinheiro",
						"Joaquim Felicio", "Jordania",
						"Jose Goncalves De Minas", "Jose Raydan",
						"Josenopolis", "Juatuba", "Juiz De Fora", "Juramento",
						"Juruaia", "Juvenilia", "Ladainha", "Lagamar",
						"Lagoa Da Prata", "Lagoa Dos Patos", "Lagoa Dourada",
						"Lagoa Formosa", "Lagoa Santa", "Lajinha", "Lambari",
						"Lamim", "Lassance", "Lavras", "Leandro Ferreira",
						"Leme Do Prado", "Leopoldina", "Liberdade",
						"Lima Duarte", "Limeira Do Oeste", "Lontra",
						"Luisburgo", "Luislandia", "Luminarias", "Luz",
						"Machacalis", "Machado", "Madre De Deus De Minas",
						"Malacacheta", "Mamonas", "Manga", "Manhuacu",
						"Manhumirim", "Mantena", "Mar De Espanha",
						"Maravilhas", "Maria Da Fe", "Mariana", "Marilac",
						"Mario Campos", "Maripa De Minas", "Marlieria",
						"Marmelopolis", "Martinho Campos", "Martins Soares",
						"Mata Verde", "Materlandia", "Mateus Leme",
						"Matias Barbosa", "Matias Cardoso", "Matipo",
						"Mato Verde", "Matozinhos", "Matutina", "Medeiros",
						"Medina", "Mendes Pimentel", "Merces", "Minas Novas",
						"Minduri", "Mirabela", "Miradouro", "Mirai",
						"Miravania", "Moeda", "Moema", "Monjolos",
						"Monsenhor Paulo", "Montalvania",
						"Monte Alegre De Minas", "Monte Azul", "Monte Belo",
						"Monte Carmelo", "Monte Formoso",
						"Monte Santo De Minas", "Monte Siao", "Montes Claros",
						"Montezuma", "Morada Nova De Minas", "Morro Da Garca",
						"Morro Do Pilar", "Munhoz", "Muriae", "Mutum",
						"Muzambinho", "Nacip Raydan", "Nanuque", "Naque",
						"Natalandia", "Natercia", "Nazareno", "Nepomuceno",
						"Ninheira", "Nova Belem", "Nova Era", "Nova Lima",
						"Nova Modica", "Nova Ponte", "Nova Porteirinha",
						"Nova Resende", "Nova Serrana", "Novo Cruzeiro",
						"Novo Oriente De Minas", "Novorizonte", "Olaria",
						"Olhos-d'agua", "Olimpio Noronha", "Oliveira",
						"Oliveira Fortes", "Onca De Pitangui", "Oratorios",
						"Orizania", "Ouro Fino", "Ouro Preto",
						"Ouro Verde De Minas", "Padre Carvalho",
						"Padre Paraiso", "Pai Pedro", "Paineiras", "Pains",
						"Paiva", "Palma", "Palmopolis", "Papagaios",
						"Para De Minas", "Paracatu", "Paraguacu",
						"Paraisopolis", "Paraopeba", "Passa Quatro",
						"Passa Tempo", "Passabem", "Passa-vinte", "Passos",
						"Patis", "Patos De Minas", "Patrocinio",
						"Patrocinio Do Muriae", "Paula Candido", "Paulistas",
						"Pavao", "Pecanha", "Pedra Azul", "Pedra Bonita",
						"Pedra Do Anta", "Pedra Do Indaia", "Pedra Dourada",
						"Pedralva", "Pedras De Maria Da Cruz", "Pedrinopolis",
						"Pedro Leopoldo", "Pedro Teixeira", "Pequeri", "Pequi",
						"Perdigao", "Perdizes", "Perdoes", "Periquito",
						"Pescador", "Piau", "Piedade De Caratinga",
						"Piedade De Ponte Nova", "Piedade Do Rio Grande",
						"Piedade Dos Gerais", "Pimenta", "Pingo-d'agua",
						"Pintopolis", "Piracema", "Pirajuba", "Piranga",
						"Pirangucu", "Piranguinho", "Pirapetinga", "Pirapora",
						"Pirauba", "Pitangui", "Piumhi", "Planura",
						"Poco Fundo", "Pocos De Caldas", "Pocrane", "Pompeu",
						"Ponte Nova", "Ponto Chique", "Ponto Dos Volantes",
						"Porteirinha", "Porto Firme", "Pote", "Pouso Alegre",
						"Pouso Alto", "Prados", "Pratapolis", "Pratinha",
						"Presidente Kubitschek", "Presidente Olegario",
						"Prudente De Morais", "Quartel Geral", "Queluzito",
						"Raposos", "Raul Soares", "Recreio", "Reduto",
						"Resende Costa", "Resplendor", "Ressaquinha",
						"Riacho Dos Machados", "Ribeirao Das Neves",
						"Ribeirao Vermelho", "Rio Acima", "Rio Casca",
						"Rio Do Prado", "Rio Doce", "Rio Espera", "Rio Manso",
						"Rio Novo", "Rio Paranaiba", "Rio Pardo De Minas",
						"Rio Piracicaba", "Rio Pomba", "Rio Preto",
						"Rio Vermelho", "Ritapolis", "Rochedo De Minas",
						"Rodeiro", "Romaria", "Rosario Da Limeira", "Rubelita",
						"Rubim", "Sabara", "Sabinopolis", "Sacramento",
						"Salinas", "Salto Da Divisa", "Santa Barbara",
						"Santa Barbara Do Leste",
						"Santa Barbara Do Monte Verde",
						"Santa Barbara Do Tugurio", "Santa Cruz De Salinas",
						"Santa Cruz Do Escalvado", "Santa Efigenia De Minas",
						"Santa Fe De Minas", "Santa Helena De Minas",
						"Santa Juliana", "Santa Margarida",
						"Santa Maria De Itabira", "Santa Maria Do Salto",
						"Santa Maria Do Suacui", "Santa Rita De Caldas",
						"Santa Rita De Ibitipoca", "Santa Rita De Jacutinga",
						"Santa Rita De Minas", "Santa Rita Do Itueto",
						"Santa Rita Do Sapucai", "Santa Rosa Da Serra",
						"Santa Vitoria", "Santana Da Vargem",
						"Santana De Cataguases", "Santana De Pirapama",
						"Santana Do Deserto", "Santana Do Garambeu",
						"Santana Do Jacare", "Santana Do Manhuacu",
						"Santana Do Paraiso", "Santana Do Riacho",
						"Santana Dos Montes", "Santo Antonio Do Amparo",
						"Santo Antonio Do Aventureiro",
						"Santo Antonio Do Grama", "Santo Antonio Do Itambe",
						"Santo Antonio Do Jacinto", "Santo Antonio Do Monte",
						"Santo Antonio Do Retiro",
						"Santo Antonio Do Rio Abaixo", "Santo Hipolito",
						"Santos Dumont", "Sao Bento Abade",
						"Sao Bras Do Suacui", "Sao Domingos Das Dores",
						"Sao Domingos Do Prata", "Sao Felix De Minas",
						"Sao Francisco De Sales", "Sao Francisco Do Gloria",
						"Sao Geraldo", "Sao Geraldo Da Piedade",
						"Sao Geraldo Do Baixio", "Sao Goncalo Do Abaete",
						"Sao Goncalo Do Para", "Sao Goncalo Do Rio Abaixo",
						"Sao Goncalo Do Rio Preto", "Sao Goncalo Do Sapucai",
						"Sao Gotardo", "Sao Joao Batista Do Gloria",
						"Sao Joao Da Lagoa", "Sao Joao Da Mata",
						"Sao Joao Da Ponte", "Sao Joao Das Missoes",
						"Sao Joao Del Rei", "Sao Joao Do Manhuacu",
						"Sao Joao Do Manteninha", "Sao Joao Do Oriente",
						"Sao Joao Do Pacui", "Sao Joao Do Paraiso",
						"Sao Joao Evangelista", "Sao Joao Nepomuceno",
						"Sao Joaquim De Bicas", "Sao Jose Da Barra",
						"Sao Jose Da Lapa", "Sao Jose Da Safira",
						"Sao Jose Da Varginha", "Sao Jose Do Alegre",
						"Sao Jose Do Goiabal", "Sao Jose Do Jacuri",
						"Sao Jose Do Mantimento", "Sao Lourenco",
						"Sao Miguel Do Anta", "Sao Pedro Da Uniao",
						"Sao Pedro Do Suacui", "Sao Pedro Dos Ferros",
						"Sao Romao", "Sao Roque De Minas",
						"Sao Sebastiao Da Bela Vista",
						"Sao Sebastiao Da Vargem Alegre",
						"Sao Sebastiao Do Anta", "Sao Sebastiao Do Maranhao",
						"Sao Sebastiao Do Oeste", "Sao Sebastiao Do Paraiso",
						"Sao Sebastiao Do Rio Preto",
						"Sao Sebastiao Do Rio Verde", "Sao Thome Das Letras",
						"Sao Tiago", "Sao Tomas De Aquino",
						"Sao Vicente De Minas", "Sapucai-mirim", "Sardoa",
						"Sarzedo", "Sem-peixe", "Senador Amaral",
						"Senador Cortes", "Senador Firmino",
						"Senador Jose Bento", "Senador Modestino Goncalves",
						"Senhora De Oliveira", "Senhora Do Porto",
						"Senhora Dos Remedios", "Sericita", "Seritinga",
						"Serra Azul De Minas", "Serra Da Saudade",
						"Serra Do Salitre", "Serra Dos Aimores", "Serrania",
						"Serranopolis De Minas", "Serranos", "Serro",
						"Sete Lagoas", "Setubinha", "Silveirania",
						"Silvianopolis", "Simao Pereira", "Simonesia",
						"Sobralia", "Soledade De Minas", "Tabuleiro",
						"Taiobeiras", "Taparuba", "Taquaracu De Minas",
						"Tarumirim", "Teixeiras", "Teofilo Otoni", "Timoteo",
						"Tiradentes", "Tiros", "Tocantins", "Tocos Do Moji",
						"Tombos", "Tres Coracoes", "Tres Marias",
						"Tres Pontas", "Tumiritinga", "Tupaciguara",
						"Turvolandia", "Uba", "Ubai", "Ubaporanga", "Uberaba",
						"Uberlandia", "Umburatiba", "Unai", "Uniao De Minas",
						"Uruana De Minas", "Urucania", "Urucuia",
						"Vargem Alegre", "Vargem Grande Do Rio Pardo",
						"Varginha", "Varjao De Minas", "Varzea Da Palma",
						"Varzelandia", "Vazante", "Verdelandia", "Veredinha",
						"Verissimo", "Vermelho Novo", "Vespasiano", "Vieiras",
						"Virgem Da Lapa", "Virginia", "Virginopolis",
						"Virgolandia", "Visconde Do Rio Branco",
						"Volta Grande", "Agua Clara", "Alcinopolis", "Amambai",
						"Anastacio", "Anaurilandia", "Angelica",
						"Antonio Joao", "Aparecida Do Taboado", "Aquidauana",
						"Aral Moreira", "Bataguassu", "Bataypora",
						"Bela Vista", "Bodoquena", "Brasilandia", "Caarapo",
						"Camapua", "Campo Grande", "Cassilandia",
						"Chapadao Do Sul", "Corguinho", "Coronel Sapucaia",
						"Corumba", "Costa Rica", "Coxim", "Deodapolis",
						"Dois Irmaos Do Buriti", "Dourados", "Fatima Do Sul",
						"Gloria De Dourados", "Guia Lopes Da Laguna",
						"Iguatemi", "Inocencia", "Itapora", "Itaquirai",
						"Ivinhema", "Japora", "Jaraguari", "Jardim", "Jatei",
						"Juti", "Ladario", "Laguna Carapa", "Maracaju",
						"Miranda", "Mundo Novo", "Navirai", "Nioaque",
						"Nova Alvorada Do Sul", "Nova Andradina",
						"Novo Horizonte Do Sul", "Paranaiba", "Paranhos",
						"Pedro Gomes", "Ponta Pora", "Porto Murtinho",
						"Ribas Do Rio Pardo", "Rio Brilhante",
						"Rio Verde De Mato Grosso", "Rochedo",
						"Santa Rita Do Pardo", "Sao Gabriel Do Oeste",
						"Selviria", "Sete Quedas", "Sidrolandia", "Sonora",
						"Tacuru", "Taquarussu", "Terenos", "Tres Lagoas",
						"Vicentina", "Acorizal", "Alta Floresta",
						"Alto Araguaia", "Alto Boa Vista", "Alto Garcas",
						"Alto Paraguai", "Alto Taquari", "Apiacas",
						"Araguaiana", "Araguainha", "Araputanga", "Arenapolis",
						"Aripuana", "Barao De Melgaco", "Barra Do Bugres",
						"Barra Do Garcas", "Bom Jesus Do Araguaia",
						"Brasnorte", "Caceres", "Campinapolis",
						"Campo Novo Do Parecis", "Campo Verde",
						"Campos De Julio", "Canarana", "Carlinda",
						"Castanheira", "Chapada Dos Guimaraes", "Claudia",
						"Cocalinho", "Colider", "Colniza", "Comodoro",
						"Confresa", "Conquista D'oeste", "Cotriguacu",
						"Cuiaba", "Curvelandia", "Denise", "Diamantino",
						"Dom Aquino", "Feliz Natal", "Figueiropolis D'oeste",
						"Gaucha Do Norte", "Gloria D'oeste",
						"Guaranta Do Norte", "Guiratinga", "Indiavai",
						"Itauba", "Itiquira", "Jaciara", "Jangada", "Jauru",
						"Juara", "Juina", "Juruena", "Juscimeira",
						"Lambari D'oeste", "Lucas Do Rio Verde", "Luciara",
						"Marcelandia", "Matupa", "Mirassol D'oeste", "Nobres",
						"Nortelandia", "Nossa Senhora Do Livramento",
						"Nova Bandeirantes", "Nova Brasilandia",
						"Nova Canaa Do Norte", "Nova Guarita", "Nova Lacerda",
						"Nova Marilandia", "Nova Maringa", "Nova Monte Verde",
						"Nova Mutum", "Nova Santa Helena", "Nova Ubirata",
						"Nova Xavantina", "Novo Horizonte Do Norte",
						"Novo Mundo", "Novo Sao Joaquim", "Paranaita",
						"Paranatinga", "Peixoto De Azevedo",
						"Planalto Da Serra", "Pocone", "Pontal Do Araguaia",
						"Ponte Branca", "Pontes E Lacerda",
						"Porto Alegre Do Norte", "Porto Dos Gauchos",
						"Porto Esperidiao", "Porto Estrela", "Poxoreo",
						"Primavera Do Leste", "Querencia",
						"Reserva Do Cabacal", "Ribeirao Cascalheira",
						"Ribeiraozinho", "Rondolandia", "Rondonopolis",
						"Rosario Oeste", "Salto Do Ceu", "Santa Carmem",
						"Santa Cruz Do Xingu", "Santa Rita Do Trivelato",
						"Santo Afonso", "Santo Antonio Do Leste",
						"Santo Antonio Do Leverger", "Sao Felix Do Araguaia",
						"Sao Jose Do Povo", "Sao Jose Do Rio Claro",
						"Sao Jose Do Xingu", "Sao Jose Dos Quatro Marcos",
						"Sao Pedro Da Cipa", "Sapezal", "Serra Nova Dourada",
						"Sinop", "Sorriso", "Tabapora", "Tangara Da Serra",
						"Tapurah", "Terra Nova Do Norte", "Tesouro",
						"Torixoreu", "Uniao Do Sul", "Vale De Sao Domingos",
						"Vera", "Vila Bela Da Santissima Trindade",
						"Vila Rica", "Acailandia", "Afonso Cunha",
						"Agua Doce Do Maranhao", "Alcantara", "Aldeias Altas",
						"Altamira Do Maranhao", "Alto Alegre Do Maranhao",
						"Alto Alegre Do Pindare", "Alto Parnaiba",
						"Amapa Do Maranhao", "Amarante Do Maranhao",
						"Anajatuba", "Anapurus", "Apicum-acu", "Araioses",
						"Arame", "Arari", "Axixa", "Bacabal", "Bacabeira",
						"Bacuri", "Bacurituba", "Balsas", "Barra Do Corda",
						"Barreirinhas", "Bela Vista Do Maranhao", "Belagua",
						"Benedito Leite", "Bequimao", "Bernardo Do Mearim",
						"Boa Vista Do Gurupi", "Bom Jesus Das Selvas",
						"Bom Lugar", "Brejo", "Brejo De Areia", "Buriti",
						"Buriti Bravo", "Buriticupu", "Buritirana",
						"Cachoeira Grande", "Cajapio", "Cajari",
						"Campestre Do Maranhao", "Candido Mendes",
						"Cantanhede", "Capinzal Do Norte", "Carolina",
						"Carutapera", "Caxias", "Central Do Maranhao",
						"Centro Do Guilherme", "Centro Novo Do Maranhao",
						"Chapadinha", "Cidelandia", "Codo", "Coelho Neto",
						"Conceicao Do Lago-acu", "Coroata", "Cururupu",
						"Davinopolis", "Dom Pedro", "Duque Bacelar",
						"Esperantinopolis", "Estreito",
						"Feira Nova Do Maranhao", "Fernando Falcao",
						"Formosa Da Serra Negra", "Fortaleza Dos Nogueiras",
						"Fortuna", "Godofredo Viana", "Goncalves Dias",
						"Governador Archer", "Governador Edison Lobao",
						"Governador Eugenio Barros", "Governador Luiz Rocha",
						"Governador Newton Bello", "Governador Nunes Freire",
						"Graca Aranha", "Grajau", "Guimaraes",
						"Humberto De Campos", "Icatu", "Igarape Do Meio",
						"Igarape Grande", "Imperatriz", "Itaipava Do Grajau",
						"Itapecuru Mirim", "Itinga Do Maranhao",
						"Jenipapo Dos Vieiras", "Joao Lisboa", "Joselandia",
						"Junco Do Maranhao", "Lago Da Pedra", "Lago Do Junco",
						"Lago Dos Rodrigues", "Lago Verde", "Lagoa Do Mato",
						"Lagoa Grande Do Maranhao", "Lajeado Novo",
						"Lima Campos", "Loreto", "Luis Domingues",
						"Magalhaes De Almeida", "Maracacume", "Maraja Do Sena",
						"Maranhaozinho", "Mata Roma", "Matinha", "Matoes",
						"Matoes Do Norte", "Milagres Do Maranhao",
						"Miranda Do Norte", "Mirinzal", "Moncao",
						"Montes Altos", "Morros", "Nina Rodrigues",
						"Nova Colinas", "Nova Iorque",
						"Nova Olinda Do Maranhao", "Olho D'agua Das Cunhas",
						"Olinda Nova Do Maranhao", "Palmeirandia", "Paraibano",
						"Parnarama", "Passagem Franca", "Pastos Bons",
						"Paulino Neves", "Paulo Ramos", "Pedreiras",
						"Pedro Do Rosario", "Penalva", "Peri Mirim",
						"Peritoro", "Pindare-mirim", "Pinheiro", "Pio Xii",
						"Pirapemas", "Pocao De Pedras", "Porto Franco",
						"Porto Rico Do Maranhao", "Presidente Dutra",
						"Presidente Sarney", "Presidente Vargas",
						"Primeira Cruz", "Raposa", "Ribamar Fiquene",
						"Rosario", "Sambaiba", "Santa Filomena Do Maranhao",
						"Santa Luzia Do Parua", "Santa Quiteria Do Maranhao",
						"Santana Do Maranhao", "Santo Amaro Do Maranhao",
						"Santo Antonio Dos Lopes", "Sao Benedito Do Rio Preto",
						"Sao Bernardo", "Sao Domingos Do Azeitao",
						"Sao Domingos Do Maranhao", "Sao Felix De Balsas",
						"Sao Francisco Do Brejao", "Sao Francisco Do Maranhao",
						"Sao Joao Do Caru", "Sao Joao Do Soter",
						"Sao Joao Dos Patos", "Sao Jose De Ribamar",
						"Sao Jose Dos Basilios", "Sao Luis",
						"Sao Luis Gonzaga Do Maranhao",
						"Sao Mateus Do Maranhao", "Sao Pedro Da Agua Branca",
						"Sao Pedro Dos Crentes",
						"Sao Raimundo Das Mangabeiras",
						"Sao Raimundo Do Doca Bezerra", "Sao Roberto",
						"Satubinha", "Senador Alexandre Costa",
						"Senador La Rocque", "Serrano Do Maranhao",
						"Sucupira Do Norte", "Sucupira Do Riachao",
						"Tasso Fragoso", "Timbiras", "Timon",
						"Trizidela Do Vale", "Tufilandia", "Tuntum", "Turiacu",
						"Turilandia", "Tutoia", "Urbano Santos",
						"Vargem Grande", "Viana", "Vila Nova Dos Martirios",
						"Vitoria Do Mearim", "Vitorino Freire", "Ze Doca",
						"Abadia De Goias", "Abadiania", "Acreuna", "Adelandia",
						"Agua Fria De Goias", "Agua Limpa",
						"Aguas Lindas De Goias", "Alexania", "Aloandia",
						"Alto Horizonte", "Alto Paraiso De Goias",
						"Alvorada Do Norte", "Amaralina",
						"Americano Do Brasil", "Amorinopolis", "Anapolis",
						"Anhanguera", "Anicuns", "Aparecida De Goiania",
						"Aparecida Do Rio Doce", "Apore", "Aracu", "Aragarcas",
						"Aragoiania", "Araguapaz", "Arenopolis", "Aruana",
						"Aurilandia", "Avelinopolis", "Barro Alto",
						"Bela Vista De Goias", "Bom Jardim De Goias",
						"Bom Jesus De Goias", "Bonfinopolis", "Bonopolis",
						"Brazabrantes", "Britania", "Buriti Alegre",
						"Buriti De Goias", "Buritinopolis", "Cabeceiras",
						"Cachoeira Alta", "Cachoeira De Goias", "Cacu",
						"Caiaponia", "Caldas Novas", "Caldazinha",
						"Campestre De Goias", "Campinacu", "Campinorte",
						"Campo Alegre De Goias", "Campo Limpo De Goias",
						"Campos Belos", "Campos Verdes", "Carmo Do Rio Verde",
						"Castelandia", "Catalao", "Caturai", "Cavalcante",
						"Ceres", "Cezarina", "Chapadao Do Ceu",
						"Cidade Ocidental", "Cocalzinho De Goias",
						"Colinas Do Sul", "Corrego Do Ouro",
						"Corumba De Goias", "Corumbaiba", "Cristalina",
						"Cristianopolis", "Crixas", "Crominia", "Cumari",
						"Damianopolis", "Damolandia", "Diorama",
						"Divinopolis De Goias", "Doverlandia", "Edealina",
						"Edeia", "Faina", "Fazenda Nova", "Firminopolis",
						"Flores De Goias", "Formosa", "Gameleira De Goias",
						"Goianapolis", "Goiandira", "Goianesia", "Goiania",
						"Goianira", "Goias", "Goiatuba", "Gouvelandia",
						"Guapo", "Guaraita", "Guarani De Goias", "Guarinos",
						"Heitorai", "Hidrolandia", "Hidrolina", "Iaciara",
						"Inaciolandia", "Indiara", "Inhumas", "Ipameri",
						"Ipiranga De Goias", "Israelandia", "Itaberai",
						"Itaguari", "Itaguaru", "Itapaci", "Itapirapua",
						"Itapuranga", "Itaruma", "Itaucu", "Itumbiara",
						"Ivolandia", "Jandaia", "Jaragua", "Jatai", "Jaupaci",
						"Jesupolis", "Joviania", "Leopoldo De Bulhoes",
						"Luziania", "Mairipotaba", "Mambai", "Mara Rosa",
						"Marzagao", "Matrincha", "Maurilandia",
						"Mimoso De Goias", "Minacu", "Mineiros", "Moipora",
						"Monte Alegre De Goias", "Montes Claros De Goias",
						"Montividiu", "Montividiu Do Norte", "Morrinhos",
						"Morro Agudo De Goias", "Mossamedes", "Mozarlandia",
						"Mutunopolis", "Nazario", "Neropolis", "Niquelandia",
						"Nova America", "Nova Crixas", "Nova Gloria",
						"Nova Iguacu De Goias", "Nova Roma", "Novo Brasil",
						"Novo Gama", "Novo Planalto", "Orizona",
						"Ouro Verde De Goias", "Ouvidor", "Padre Bernardo",
						"Palestina De Goias", "Palmeiras De Goias", "Palmelo",
						"Palminopolis", "Panama", "Paranaiguara", "Parauna",
						"Perolandia", "Petrolina De Goias", "Pilar De Goias",
						"Piracanjuba", "Piranhas", "Pirenopolis",
						"Pires Do Rio", "Planaltina", "Pontalina", "Porangatu",
						"Porteirao", "Portelandia", "Posse", "Professor Jamil",
						"Quirinopolis", "Rialma", "Rianapolis", "Rio Quente",
						"Rio Verde", "Rubiataba", "Sanclerlandia",
						"Santa Barbara De Goias", "Santa Cruz De Goias",
						"Santa Fe De Goias", "Santa Helena De Goias",
						"Santa Rita Do Araguaia", "Santa Rita Do Novo Destino",
						"Santa Rosa De Goias", "Santa Tereza De Goias",
						"Santa Terezinha De Goias", "Santo Antonio Da Barra",
						"Santo Antonio De Goias",
						"Santo Antonio Do Descoberto",
						"Sao Francisco De Goias", "Sao Joao Da Parauna",
						"Sao Joao D'alianca", "Sao Luis De Montes Belos",
						"Sao Luiz Do Norte", "Sao Miguel Do Araguaia",
						"Sao Miguel Do Passa Quatro", "Sao Patricio",
						"Senador Canedo", "Serranopolis", "Silvania",
						"Simolandia", "Sitio D'abadia", "Taquaral De Goias",
						"Teresina De Goias", "Terezopolis De Goias",
						"Tres Ranchos", "Trombas", "Turvania", "Turvelandia",
						"Uirapuru", "Uruacu", "Uruana", "Urutai",
						"Valparaiso De Goias", "Varjao", "Vianopolis",
						"Vicentinopolis", "Vila Boa", "Vila Propicio",
						"Afonso Claudio", "Agua Doce Do Norte", "Aguia Branca",
						"Alegre", "Alfredo Chaves", "Alto Rio Novo", "Apiaca",
						"Aracruz", "Atilio Vivacqua", "Baixo Guandu",
						"Barra De Sao Francisco", "Bom Jesus Do Norte",
						"Brejetuba", "Cachoeiro De Itapemirim", "Cariacica",
						"Castelo", "Colatina", "Conceicao Da Barra",
						"Conceicao Do Castelo", "Divino De Sao Lourenco",
						"Domingos Martins", "Dores Do Rio Preto", "Ecoporanga",
						"Fundao", "Governador Lindenberg", "Guacui",
						"Guarapari", "Ibatiba", "Ibiracu", "Ibitirama",
						"Iconha", "Irupi", "Itaguacu", "Itapemirim", "Itarana",
						"Iuna", "Jaguare", "Jeronimo Monteiro", "Joao Neiva",
						"Laranja Da Terra", "Linhares", "Mantenopolis",
						"Marataizes", "Marechal Floriano", "Marilandia",
						"Mimoso Do Sul", "Montanha", "Mucurici",
						"Muniz Freire", "Muqui", "Nova Venecia", "Pancas",
						"Pedro Canario", "Pinheiros", "Piuma", "Ponto Belo",
						"Rio Bananal", "Rio Novo Do Sul", "Santa Leopoldina",
						"Santa Maria De Jetiba", "Santa Teresa",
						"Sao Domingos Do Norte", "Sao Gabriel Da Palha",
						"Sao Jose Do Calcado", "Sao Mateus",
						"Sao Roque Do Canaa", "Serra", "Sooretama",
						"Vargem Alta", "Venda Nova Do Imigrante", "Vila Pavao",
						"Vila Valerio", "Vila Velha", "Vitoria", "Brasilia",
						"Abaiara", "Acarape", "Acarau", "Acopiara", "Aiuaba",
						"Alcantaras", "Altaneira", "Alto Santo", "Amontada",
						"Antonina Do Norte", "Apuiares", "Aquiraz", "Aracati",
						"Ararenda", "Araripe", "Aratuba", "Arneiroz", "Assare",
						"Baixio", "Banabuiu", "Barbalha", "Barreira", "Barro",
						"Barroquinha", "Baturite", "Beberibe", "Bela Cruz",
						"Boa Viagem", "Brejo Santo", "Camocim", "Campos Sales",
						"Caninde", "Capistrano", "Caridade", "Carire",
						"Caririacu", "Carius", "Carnaubal", "Catarina",
						"Catunda", "Caucaia", "Chaval", "Choro", "Chorozinho",
						"Coreau", "Crateus", "Crato", "Croata", "Cruz",
						"Deputado Irapuan Pinheiro", "Erere", "Eusebio",
						"Farias Brito", "Forquilha", "Fortaleza", "Fortim",
						"Frecheirinha", "General Sampaio", "Graca", "Granja",
						"Granjeiro", "Groairas", "Guaiuba",
						"Guaraciaba Do Norte", "Guaramiranga", "Horizonte",
						"Ibaretama", "Ibiapina", "Ibicuitinga", "Icapui",
						"Ico", "Ipaporanga", "Ipaumirim", "Ipu", "Iraucuba",
						"Itaicaba", "Itaitinga", "Itapage", "Itapipoca",
						"Itapiuna", "Itarema", "Itatira", "Jaguaretama",
						"Jaguaribara", "Jaguaribe", "Jaguaruana", "Jati",
						"Jijoca De Jericoacoara", "Juazeiro Do Norte", "Jucas",
						"Lavras Da Mangabeira", "Limoeiro Do Norte",
						"Madalena", "Maracanau", "Maranguape", "Marco",
						"Martinopole", "Massape", "Mauriti", "Meruoca",
						"Milagres", "Milha", "Miraima", "Missao Velha",
						"Mombaca", "Monsenhor Tabosa", "Morada Nova",
						"Moraujo", "Mucambo", "Nova Russas", "Novo Oriente",
						"Ocara", "Oros", "Pacajus", "Pacoti", "Pacuja",
						"Palhano", "Palmacia", "Paracuru", "Paraipaba",
						"Parambu", "Paramoti", "Penaforte", "Pentecoste",
						"Pereiro", "Pindoretama", "Piquet Carneiro",
						"Pires Ferreira", "Poranga", "Porteiras", "Potengi",
						"Potiretama", "Quiterianopolis", "Quixada", "Quixelo",
						"Quixeramobim", "Quixere", "Reriutaba", "Russas",
						"Saboeiro", "Salitre", "Santa Quiteria",
						"Santana Do Acarau", "Santana Do Cariri",
						"Sao Benedito", "Sao Joao Do Jaguaribe",
						"Sao Luis Do Curu", "Senador Pompeu", "Senador Sa",
						"Sobral", "Solonopole", "Tabuleiro Do Norte",
						"Tamboril", "Tarrafas", "Taua", "Tejucuoca", "Tiangua",
						"Trairi", "Tururu", "Ubajara", "Umari", "Umirim",
						"Uruburetama", "Uruoca", "Varjota", "Varzea Alegre",
						"Vicosa Do Ceara", "Abaira", "Abare", "Acajutiba",
						"Adustina", "Agua Fria", "Aiquara", "Alagoinhas",
						"Alcobaca", "Almadina", "Amargosa", "Amelia Rodrigues",
						"America Dourada", "Anage", "Andarai", "Andorinha",
						"Angical", "Anguera", "Antas", "Antonio Cardoso",
						"Antonio Goncalves", "Apora", "Apuarema", "Aracas",
						"Aracatu", "Araci", "Aramari", "Arataca", "Aratuipe",
						"Aurelino Leal", "Baianopolis", "Baixa Grande",
						"Banzae", "Barra", "Barra Da Estiva", "Barra Do Choca",
						"Barra Do Mendes", "Barra Do Rocha", "Barreiras",
						"Barrocas", "Belo Campo", "Biritinga", "Boa Nova",
						"Boa Vista Do Tupim", "Bom Jesus Da Lapa",
						"Bom Jesus Da Serra", "Boninal", "Boquira", "Botupora",
						"Brejoes", "Brejolandia", "Brotas De Macaubas",
						"Brumado", "Buerarema", "Caatiba",
						"Cabaceiras Do Paraguacu", "Cachoeira", "Cacule",
						"Caem", "Caetanos", "Caetite", "Cafarnaum", "Cairu",
						"Caldeirao Grande", "Camacan", "Camacari", "Camamu",
						"Campo Alegre De Lourdes", "Campo Formoso",
						"Canavieiras", "Candeal", "Candiba", "Candido Sales",
						"Cansancao", "Canudos", "Capela Do Alto Alegre",
						"Capim Grosso", "Caraibas", "Caravelas",
						"Cardeal Da Silva", "Carinhanha", "Casa Nova",
						"Castro Alves", "Catolandia", "Catu", "Caturama",
						"Central", "Chorrocho", "Cicero Dantas", "Cipo",
						"Coaraci", "Cocos", "Conceicao Da Feira",
						"Conceicao Do Almeida", "Conceicao Do Coite",
						"Conceicao Do Jacuipe", "Condeuba",
						"Contendas Do Sincora", "Coracao De Maria",
						"Cordeiros", "Coribe", "Coronel Joao Sa", "Correntina",
						"Cotegipe", "Cravolandia", "Crisopolis", "Cristopolis",
						"Cruz Das Almas", "Curaca", "Dario Meira",
						"Dias D'avila", "Dom Basilio", "Dom Macedo Costa",
						"Elisio Medrado", "Encruzilhada", "Erico Cardoso",
						"Esplanada", "Euclides Da Cunha", "Eunapolis",
						"Feira Da Mata", "Feira De Santana", "Firmino Alves",
						"Floresta Azul", "Formosa Do Rio Preto", "Gandu",
						"Gaviao", "Gentio Do Ouro", "Gloria", "Gongogi",
						"Governador Lomanto Junior", "Governador Mangabeira",
						"Guajeru", "Guanambi", "Guaratinga", "Heliopolis",
						"Iacu", "Ibiassuce", "Ibicarai", "Ibicoara", "Ibicui",
						"Ibipeba", "Ibipitanga", "Ibiquera", "Ibirapitanga",
						"Ibirapua", "Ibirataia", "Ibitiara", "Ibitita",
						"Ibotirama", "Ichu", "Igapora", "Igrapiuna", "Iguai",
						"Ilheus", "Inhambupe", "Ipecaeta", "Ipiau", "Ipupiara",
						"Irajuba", "Iramaia", "Iraquara", "Irara", "Irece",
						"Itabela", "Itaberaba", "Itabuna", "Itacare", "Itaete",
						"Itagi", "Itagiba", "Itagimirim", "Itaguacu Da Bahia",
						"Itaju Do Colonia", "Itajuipe", "Itamaraju", "Itamari",
						"Itanagra", "Itanhem", "Itaparica", "Itape", "Itapebi",
						"Itapetinga", "Itapicuru", "Itapitanga", "Itaquara",
						"Itarantim", "Itatim", "Itirucu", "Itiuba", "Itororo",
						"Ituacu", "Itubera", "Iuiu", "Jacaraci", "Jacobina",
						"Jaguaquara", "Jaguarari", "Jaguaripe", "Jequie",
						"Jeremoabo", "Jiquirica", "Jitauna", "Joao Dourado",
						"Juazeiro", "Jucurucu", "Jussari", "Jussiape",
						"Lafaiete Coutinho", "Lagoa Real", "Laje", "Lajedao",
						"Lajedinho", "Lajedo Do Tabocal", "Lamarao", "Lapao",
						"Lauro De Freitas", "Lencois", "Licinio De Almeida",
						"Livramento De Nossa Senhora",
						"Luis Eduardo Magalhaes", "Macajuba", "Macarani",
						"Macaubas", "Macurure", "Madre De Deus", "Maetinga",
						"Maiquinique", "Mairi", "Malhada", "Malhada De Pedras",
						"Manoel Vitorino", "Mansidao", "Maracas", "Maragogipe",
						"Marcionilio Souza", "Mascote", "Mata De Sao Joao",
						"Matina", "Medeiros Neto", "Miguel Calmon",
						"Mirangaba", "Mirante", "Monte Santo", "Morpara",
						"Morro Do Chapeu", "Mortugaba", "Mucuge", "Mucuri",
						"Mulungu Do Morro", "Muniz Ferreira",
						"Muquem De Sao Francisco", "Muritiba", "Mutuipe",
						"Nilo Pecanha", "Nordestina", "Nova Canaa",
						"Nova Ibia", "Nova Itarana", "Nova Redencao",
						"Nova Soure", "Nova Vicosa", "Novo Triunfo",
						"Olindina", "Oliveira Dos Brejinhos", "Ouricangas",
						"Ourolandia", "Palmas De Monte Alto", "Palmeiras",
						"Paramirim", "Paratinga", "Paripiranga", "Pau Brasil",
						"Paulo Afonso", "Pe De Serra", "Pedrao",
						"Pedro Alexandre", "Piata", "Pilao Arcado", "Pindai",
						"Pindobacu", "Pintadas", "Pirai Do Norte", "Piripa",
						"Piritiba", "Planaltino", "Pocoes", "Pojuca",
						"Ponto Novo", "Porto Seguro", "Potiragua", "Prado",
						"Presidente Janio Quadros",
						"Presidente Tancredo Neves", "Quijingue", "Quixabeira",
						"Rafael Jambeiro", "Remanso", "Retirolandia",
						"Riachao Das Neves", "Riachao Do Jacuipe",
						"Ribeira Do Amparo", "Ribeira Do Pombal",
						"Ribeirao Do Largo", "Rio De Contas", "Rio Do Antonio",
						"Rio Do Pires", "Rio Real", "Rodelas",
						"Salinas Da Margarida", "Salvador", "Santa Brigida",
						"Santa Cruz Cabralia", "Santa Cruz Da Vitoria",
						"Santa Maria Da Vitoria", "Santa Rita De Cassia",
						"Santaluz", "Santana", "Santanopolis", "Santo Amaro",
						"Santo Antonio De Jesus", "Santo Estevao",
						"Sao Desiderio", "Sao Felipe", "Sao Felix",
						"Sao Francisco Do Conde", "Sao Goncalo Dos Campos",
						"Sao Jose Da Vitoria", "Sao Jose Do Jacuipe",
						"Sao Miguel Das Matas", "Sao Sebastiao Do Passe",
						"Sapeacu", "Satiro Dias", "Saubara", "Saude", "Seabra",
						"Sebastiao Laranjeiras", "Senhor Do Bonfim",
						"Sento Se", "Serra Do Ramalho", "Serra Dourada",
						"Serra Preta", "Serrolandia", "Simoes Filho",
						"Sitio Do Mato", "Sitio Do Quinto", "Souto Soares",
						"Tabocas Do Brejo Velho", "Tanhacu", "Tanque Novo",
						"Tanquinho", "Tapiramuta", "Teixeira De Freitas",
						"Teofilandia", "Teolandia", "Tremedal", "Tucano",
						"Uaua", "Ubaira", "Ubaitaba", "Ubata", "Uibai",
						"Umburanas", "Una", "Urandi", "Urucuca", "Utinga",
						"Valente", "Varzea Da Roca", "Varzea Do Poco",
						"Varzea Nova", "Varzedo", "Vereda",
						"Vitoria Da Conquista", "Wagner", "Wanderley",
						"Wenceslau Guimaraes", "Xique-xique", "Alvaraes",
						"Amatura", "Anama", "Anori", "Apui",
						"Atalaia Do Norte", "Autazes", "Barcelos",
						"Barreirinha", "Benjamin Constant", "Beruri",
						"Boa Vista Do Ramos", "Boca Do Acre", "Borba",
						"Caapiranga", "Canutama", "Carauari", "Careiro",
						"Careiro Da Varzea", "Coari", "Codajas", "Eirunepe",
						"Envira", "Fonte Boa", "Guajara", "Ipixuna",
						"Iranduba", "Itacoatiara", "Itamarati", "Jurua",
						"Jutai", "Labrea", "Manacapuru", "Manaquiri", "Manaus",
						"Manicore", "Maraa", "Maues", "Nhamunda",
						"Nova Olinda Do Norte", "Novo Airao", "Novo Aripuana",
						"Parintins", "Pauini", "Presidente Figueiredo",
						"Rio Preto Da Eva", "Santa Isabel Do Rio Negro",
						"Santo Antonio Do Ica", "Sao Gabriel Da Cachoeira",
						"Sao Paulo De Olivenca", "Sao Sebastiao Do Uatuma",
						"Silves", "Tapaua", "Tefe", "Tonantins", "Uarini",
						"Urucara", "Urucurituba", "Amapa", "Calcoene",
						"Cutias", "Ferreira Gomes", "Itaubal",
						"Laranjal Do Jari", "Macapa", "Mazagao", "Oiapoque",
						"Pedra Branca Do Amapari", "Porto Grande", "Pracuuba",
						"Serra Do Navio", "Tartarugalzinho", "Vitoria Do Jari",
						"Anadia", "Arapiraca", "Barra De Santo Antonio",
						"Belo Monte", "Boca Da Mata", "Branquinha",
						"Cacimbinhas", "Cajueiro", "Canapi", "Carneiros",
						"Cha Preta", "Coite Do Noia", "Colonia Leopoldina",
						"Coqueiro Seco", "Coruripe", "Craibas",
						"Delmiro Gouveia", "Dois Riachos",
						"Estrela De Alagoas", "Feira Grande", "Feliz Deserto",
						"Flexeiras", "Girau Do Ponciano", "Ibateguara",
						"Igaci", "Igreja Nova", "Inhapi", "Jacare Dos Homens",
						"Jacuipe", "Japaratinga", "Jaramataia",
						"Jequia Da Praia", "Joaquim Gomes", "Junqueiro",
						"Lagoa Da Canoa", "Limoeiro De Anadia", "Maceio",
						"Major Isidoro", "Mar Vermelho", "Maragogi",
						"Marechal Deodoro", "Maribondo", "Mata Grande",
						"Matriz De Camaragibe", "Messias", "Minador Do Negrao",
						"Monteiropolis", "Murici", "Novo Lino",
						"Olho D'agua Das Flores", "Olho D'agua Do Casado",
						"Olho D'agua Grande", "Olivenca",
						"Palmeira Dos Indios", "Pao De Acucar", "Pariconha",
						"Passo De Camaragibe", "Paulo Jacinto", "Penedo",
						"Piacabucu", "Pindoba", "Poco Das Trincheiras",
						"Porto Calvo", "Porto De Pedras",
						"Porto Real Do Colegio", "Quebrangulo", "Rio Largo",
						"Roteiro", "Santa Luzia Do Norte", "Santana Do Mundau",
						"Sao Bras", "Sao Jose Da Laje", "Sao Jose Da Tapera",
						"Sao Luis Do Quitunde", "Sao Miguel Dos Campos",
						"Sao Miguel Dos Milagres", "Satuba",
						"Senador Rui Palmeira", "Tanque D'arca", "Taquarana",
						"Teotonio Vilela", "Traipu", "Uniao Dos Palmares"

				};

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Cities in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class CitiesEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Santa Fe", "Helena", "Turlock", "Huntington Park",
						"Gold Beach", "Webster Groves", "Roseville", "Dallas",
						"Eugene", "Berlin", "Beaumont", "Manchester", "Homer",
						"Pass Christian", "Daly City", "Los Angeles",
						"Eden Prairie", "Santa Fe Springs", "Woodruff",
						"Salem", "Sister Bay", "Louisville", "Norfolk",
						"North Chicago", "Tuscaloosa", "Lubbock", "Norton",
						"Manitowoc", "Slidell", "Grand Rapids", "Valparaiso",
						"Norwich", "Pulaski", "Shamokin", "Baltimore",
						"Beckley", "Saint Joseph", "Scranton", "Fairfield",
						"Baldwin Park", "Trenton", "Green River", "Monterey",
						"Fernley", "Ashland", "San Juan Capistrano",
						"Santa Clara", "Nanticoke", "San Dimas", "Bellingham",
						"Kansas City", "Forrest City", "Plymouth",
						"Crown Point", "Dearborn", "Pueblo", "Reading",
						"Baytown", "Oneonta", "Port Jervis", "Santa Ana",
						"Laughlin", "Roswell", "Sunbury", "Egg Harbor",
						"Morgan City", "Chelsea", "Frederiksted", "Laurel",
						"Long Beach", "Thomasville", "Salinas", "Meadville",
						"Passaic", "Moraga", "Sioux Falls", "Fallon",
						"Hattiesburg", "Urbana", "Pullman", "Palm Springs",
						"New York", "Santa Rosa", "Temecula", "South El Monte",
						"Marshfield", "New Rochelle", "Uniontown",
						"New Albany", "Fairfax", "Newport",
						"International Falls", "Rutland", "Honolulu",
						"Areceibo", "Lakeland", "Cincinnati", "Dayton",
						"Mandan", "Chicopee", "Albany", "Durant", "Huntsville",
						"Auburn", "Cedarburg", "North Adams", "Frederick",
						"Loudon", "Danbury", "Elsmere", "Camden",
						"Fort Collins", "Commerce", "Manassas Park",
						"Sioux City", "Claremont", "Cudahy", "Rock Springs",
						"Durham", "Greensburg", "Ventura", "Malibu",
						"Cedar Rapids", "Easton", "Pawtucket",
						"Charlottesville", "Lock Haven", "Bloomington",
						"Corpus Christi", "Idaho Springs", "Tulsa",
						"Grand Island", "La Habra Heights", "Coatesville",
						"Woburn", "Saginaw", "Peoria", "Corry", "Atwater",
						"Sedalia", "Livonia", "Dickinson", "Great Falls",
						"Gloversville", "Idabel", "Miami Beach", "Boulder",
						"Pembroke Pines", "Santa Monica", "Kettering",
						"New Brunswick", "Marshall", "Williston", "Rockville",
						"San Gabriel", "Orangeburg", "Utica", "Fitchburg",
						"Rockford", "Brookfield", "Placentia", "Opelousas",
						"La CaÃ±ada Flintridge", "Chino Hills", "Somerville",
						"Warner Robins", "North Platte", "Wisconsin Dells",
						"Gulfport", "Elko", "Moorhead", "Bend", "Asheville",
						"Victoria", "New Britain", "Alexandria",
						"Huntington Beach", "Palmdale", "Nacogdoches",
						"Pico Rivera", "Joliet", "Port Huron", "Huntington",
						"Boise", "City of Industry", "Minnetonka", "Warren",
						"Lomita", "Lake Charles", "Eau Claire", "Lawrence",
						"Sierra Madre", "Hudson", "McKeesport", "Texas City",
						"Gardner", "Saratoga Springs", "Laguna Niguel",
						"The Dalles", "Milwaukee", "Littleton", "Lynn",
						"East St. Louis", "Connellsville", "Spokane Valley",
						"Nashua", "Akron", "Prescott", "Rawlins", "Monroe",
						"Ontario", "Bethlehem", "Carbondale", "Needham",
						"Mobile", "Whittier", "Albuquerque", "Eufaula",
						"Torrington", "Naperville", "Garden Grove", "Hartland",
						"DuBois", "Cypress", "Tallahassee", "Hopkinsville",
						"Baton Rouge", "New Madrid", "Mount Vernon",
						"Darlington", "Lansing", "Detroit", "Waterloo",
						"Narragansett", "Missoula", "Clovis", "La Mirada",
						"Compton", "Redding", "Birmingham", "Goose Creek",
						"Medford", "Laguna Woods", "Kingston", "Blacksburg",
						"Gatlinburg", "Harrisburg", "Fullerton", "Mentor",
						"South Portland", "Memphis", "Layton", "Beaver Falls",
						"North Las Vegas", "Dunkirk", "Sierra Vista",
						"Seaford", "Pocatello", "Kent", "Rosemead", "Bozeman",
						"San Jose", "Fredericksburg", "Midwest City",
						"Hayward", "Bell Gardens", "Rye", "North Pole",
						"Decatur", "Norman", "Williamsburg", "Murfreesboro",
						"West Hartford", "Malden", "Kearns", "Little Rock",
						"Allentown", "Springfield", "Brockton", "Charlotte",
						"Bowling Green", "Wausau", "Lawrenceville", "Woodward",
						"Effingham", "Eatontown", "Biloxi", "Bandera", "Ogden",
						"Johnson City", "Miami", "Cortland", "College Park",
						"Jefferson City", "Clarksburg", "Anaconda",
						"Saint Albans", "Pierre", "Ephraim", "Wilmington",
						"Cambridge", "Des Moines", "Atlantic City",
						"El Cerrito", "Clarksville", "GuÃ¡nica", "Billings",
						"Sheboygan", "San Diego", "Highland Park", "Worland",
						"Tyler", "Moscow", "Radford", "Rome", "Jonesboro",
						"Lockport", "Torrance", "Saipan", "Clemson",
						"Superior", "Agoura Hills", "Agawam", "Carrollton",
						"Saint Paul", "Bremerton", "Atlanta", "Coral Springs",
						"Laramie", "Hastings", "Corvallis", "Newburgh",
						"Apple Valley", "Aliso Viejo", "Watertown", "Lynwood",
						"Hutchinson", "Flint", "Moreno Valley", "Dixon",
						"Council Bluffs", "Moultrie", "Dover", "Hannibal",
						"Macomb", "McAllen", "Abilene", "Wheaton", "Vacaville",
						"Brownsville", "Woonsocket", "Plantation", "Belpre",
						"Terre Haute", "Bradbury", "Fairmont", "Dothan",
						"Vermillion", "Seal Beach", "Cheyenne", "Fort Wayne",
						"Indio", "Hornell", "Norwalk", "Reno", "Chula Vista",
						"Columbus", "Chattanooga", "Alamogordo", "Ruston",
						"Gainesville", "Rochester", "Annapolis", "Franklin",
						"Redondo Beach", "Danville", "Brookings", "Wilson",
						"Cedar City", "Sandy", "Fort Dodge", "Madison",
						"Pittsburgh", "Cicero", "Yonkers", "Kenner",
						"Glendora", "Rancho Cordova", "Walla Walla",
						"Lynchburg", "Branson", "Yakima", "Stanton",
						"Fairbanks", "Maywood", "Concord", "Coos Bay",
						"Walnut", "Mesa", "St. Petersburg", "Austin", "Vernon",
						"Miami Gardens", "Valdosta", "Aguadilla", "Merizo",
						"Boulder Junction", "Bay City", "Juneau",
						"Monterey Park", "Vineland", "Mission Viejo",
						"Cumberland", "Bristol", "Farmer City",
						"Fort Lauderdale", "Port St. Lucie", "San Marino",
						"Enfield", "Galesburg", "Wheeling", "Hartford",
						"Titusville", "Peekskill", "Saukville", "Philadelphia",
						"Casper", "Union City", "Jordan Valley",
						"Grass Valley", "San Angelo", "Thousand Oaks",
						"East Rutherford", "La Puente", "Omaha", "Belleville",
						"Zanesville", "Latrobe", "Sturgis", "Easthampton",
						"Rock Island", "Deadwood", "Texarkana", "Brown Deer",
						"Monrovia", "Centennial", "Villa Park", "Falls Church",
						"Lebanon", "Niagara Falls", "Bossier City",
						"Westlake Village", "Steubenville", "Aspen",
						"Sacramento", "Ansonia", "Brea", "Boston", "Olympia",
						"Hampton", "Minot", "Santa Clarita", "Blythe",
						"Cape Girardeau", "Meriden", "Gallup", "Statesboro",
						"Lewiston", "Salisbury", "Mount Pleasant", "Revere",
						"Buffalo", "Knoxville", "Santa Cruz", "Christiansted",
						"Kemmerer", "Marietta", "Lake Forest", "East Hartford",
						"Grambling", "West Sacramento", "Sun Valley",
						"Virginia Beach", "Duncan", "San Luis Obispo",
						"Lancaster", "Lafayette", "Taunton", "Dalton", "Yuma",
						"Peabody", "Barre", "Vancouver", "West Warwick",
						"Kalamazoo", "Clairton", "Everett", "Downey", "Newton",
						"Kennewick", "Sutter Creek", "Duluth", "Greensboro",
						"Marlborough", "Palos Verdes Estates", "Cairo",
						"Richmond", "Thibodaux", "Toledo", "Montebello",
						"Portsmouth", "West Covina", "Bandon"

				};

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Zip Codes in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class ZipCodesEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Y2C 1X4", "22623", "D7Q 2L9", "69526", "32835", "56841",
						"63297", "04238", "A5E 6S7", "B1D 4P9", "27434",
						"T7B 5J1", "54874", "Z1A 7I0", "45444", "29970",
						"41182", "K7X 2F2", "G3X 6I7", "V2N 3U7", "T5I 5Y4",
						"76713", "35848", "K4K 2M2", "A5B 8V8", "D3F 5Y9",
						"R1P 4K1", "X9H 3K6", "X9T 9L8", "26939", "Y8D 2O1",
						"95814", "38202", "Z1B 4W7", "57049", "I1E 8G8",
						"87835", "D8J 4Z3", "B8J 8M8", "53556", "75862",
						"31094", "U8P 7F0", "94643", "A6T 6G3", "U2Y 1W2",
						"W3T 1A5", "77757", "F3L 4H0", "W4N 2C6", "E5L 4K6",
						"L7T 1M5", "03825", "S8K 9T7", "Y8B 1A6", "40164",
						"44392", "I2R 9M3", "82566", "M7O 5I0", "32570",
						"23957", "R4E 2U9", "B5R 7Y1", "N6U 6O1", "19812",
						"C7A 4L4", "52462", "29943", "A2A 6H7", "16839",
						"19253", "15377", "C7G 5C6", "26154", "71610", "29882",
						"97075", "W3O 4K2", "69023", "95505", "I5Z 2U6",
						"O7X 2B7", "P2V 8C4", "16196", "46250", "51522",
						"Z2F 1Z6", "14429", "J6K 3H1", "93391", "16147",
						"P9D 6P7", "02994", "Q9Q 9A3", "37945", "C7M 8W0",
						"96374", "Z4J 5T0", "J9J 7X7", "W6H 6B6", "02104",
						"Q2X 8N8", "45977", "91594", "Q7Y 7K4", "66819",
						"G8H 7Q0", "E1X 9V8", "I8N 7M2", "16093", "74627",
						"N5D 6Q2", "51441", "27479", "Y7D 9B8", "55929",
						"S2A 4K6", "50377", "51352", "F4E 3F8", "78062",
						"82128", "V9M 6Q0", "V5Q 1T8", "Y1O 8W2", "99877",
						"X1Z 1V8", "L5O 6I0", "J7Z 7S0", "N9O 2W5", "62153",
						"O2Z 1P5", "18632", "I9D 4F6", "T8G 3U8", "83899",
						"99546", "32056", "L8V 7M1", "H4R 1H2", "60960",
						"56323", "44218", "97349", "73485", "72768", "54596",
						"61383", "M3Z 8T9", "42028", "00479", "I4U 3C6",
						"20653", "V3Y 5G5", "L5I 3F3", "R1I 9K7", "68440",
						"D6V 3L1", "A4T 1W2", "D5S 5E7", "11588", "H3N 8L1",
						"75795", "S6A 9Z1", "R6R 5M8", "06449", "97114",
						"71823", "93585", "O2R 1R4", "71021", "29033", "05738",
						"H3U 2T7", "94759", "I1L 6G2", "13152", "B9O 9G8",
						"A9W 8R8", "K4F 7T3", "I6N 1U8", "H1K 4A9", "H8K 3T2",
						"L1J 8M5", "29854", "Q3F 8O2", "X3G 2P0", "87802",
						"30397", "85139", "51614", "07123", "T3Z 6H2",
						"R3V 6A4", "O6V 5I4", "95186", "08226", "21746",
						"V5B 8Y5", "N4X 8V5", "46705", "97052", "T1V 4N6",
						"54818", "O7G 4H0", "97845", "26359", "P7O 1J4",
						"83867", "J1T 6B5", "84042", "A6W 9U4", "35082",
						"10493", "91506", "X1F 3K5", "B4H 8W9", "K1Z 6W1",
						"31020", "T1V 8Z9", "H3Q 6J0", "18832", "39756",
						"Q6I 7U0", "69850", "V7S 2T6", "Q5S 9K0", "15422",
						"P9X 7U5", "50768", "K1G 1L8", "18926", "U2K 1G0",
						"17774", "T2A 6R4", "84170", "O9O 3C3", "M4R 5I8",
						"B5P 4H7", "Y5V 6J2", "12162", "93038", "Z4S 8O7",
						"E2Q 9O5", "J2T 6A8", "15285", "88428", "96379",
						"86531", "74634", "36154", "03456", "J6Y 7H9",
						"D6U 5T7", "E4R 6A9", "D5S 8O0", "C7P 5H5", "P5L 7B2",
						"U9N 9K1", "41756", "J2O 6H2", "T6T 9D2", "W3Q 1B6",
						"00854", "30573", "30062", "10722", "D5S 3Q3", "63618",
						"58222", "Q3C 3M8", "N6R 7S8", "K1X 7X5", "P7X 3G7",
						"O9L 2E5", "R4U 2B4", "72213", "W3S 9W4", "17552",
						"04658", "Z7N 1D4", "46988", "05790", "07160", "64323",
						"10221", "96394", "S5T 4M7", "54114", "89002", "23944",
						"40935", "B1W 5M6", "A1O 2P3", "O4B 8D2", "C7Q 7E7",
						"52752", "07257", "C9Y 4Z4", "03924", "A2T 2V4",
						"02338", "B2Y 8N0", "44793", "32082", "Y1F 7L9",
						"62156", "R6A 2R7", "15642", "73703", "05455",
						"E8M 7M6", "D7P 8N2", "09439", "Q7E 9K2", "87119",
						"66330", "06359", "P8S 9U8", "40582", "S1G 9A1",
						"65870", "33536", "F4B 1H6", "I2J 6J0", "69685",
						"J4B 5J4", "87500", "00175", "98139", "99474", "18852",
						"K2D 9E1", "21096", "95724", "E4Y 9W7", "T6Q 1Z0",
						"C9J 3W6", "X6D 5O6", "U2S 9O6", "34910", "10631",
						"63438", "X9W 5R0", "V8F 4L1", "80996", "Y3J 1Y0",
						"J3H 7G2", "79797", "11928", "37708", "31731",
						"S5M 5T7", "M6V 4K3", "52830", "D8I 9M7", "F1H 4T8",
						"15520", "C5Q 4B1", "95716", "46017", "F1R 9W5",
						"L2L 5J5", "09016", "74030", "01774", "71966",
						"B8N 6E0", "10536", "C1P 5D1", "28278", "V7Q 5V6",
						"J3L 4U6", "I1C 8D2", "P4O 2Q4", "J3J 2B0", "97929",
						"R7B 2C0", "X2L 3U8", "94328", "60667", "37831",
						"02612", "04332", "H2P 6L4", "C5W 7F4", "Y3K 1V4",
						"L6O 4Q0", "03824", "X9V 1B3", "85484", "35577",
						"I4J 9J0", "68212", "C1G 2J2", "86834", "U9X 6Z2",
						"32232", "15136", "M9K 8E3", "82023", "23415",
						"N8Q 8A5", "N7K 2Y8", "I7I 7L2", "44337", "79104",
						"72932", "06795", "33297", "X3Y 2O6", "25939", "12945",
						"M2G 4U1", "03788", "07864", "99629", "31586",
						"H8I 7Q4", "S8F 6S8", "57294", "X1L 8R8", "26639",
						"S7D 2F8", "L4U 3A2", "H2L 5C5", "91784", "M8L 9N1",
						"S4Y 7U3", "03816", "O1D 1Z0", "T9T 2L5", "41748",
						"L6Y 7D0", "Y2T 9S8", "V8E 1P1", "75449", "24438",
						"I6D 4S0", "M1F 3F0", "J2S 5Y4", "39991", "I5F 4W8",
						"F5O 7Z6", "28932", "C7A 6R3", "53696", "D1B 9N2",
						"B9L 7W7", "81782", "X8C 3H6", "96317", "F7V 5D8",
						"P6I 3E1", "Y1Q 4Y4", "27286", "18640", "I5L 2Z0",
						"33047", "44348", "66042", "60511", "46266", "M9R 5K4",
						"X9V 3U7", "37466", "T2G 9Z7", "23258", "E4Q 6W5",
						"78347", "11382", "A5B 1F6", "P8U 5B9", "66721",
						"A5V 7F0", "68278", "89041", "39049", "15636",
						"I2W 2K8", "A7I 8P1", "51167", "R8X 6R0", "I4V 4R0",
						"V4B 9L7", "G4J 9X4", "Q2M 3Z3", "64997", "34752",
						"P2R 4J9", "77628", "K3P 4S4", "C4Z 8F3", "A8O 3L7",
						"70758", "20454", "17457", "07320", "29243", "31016",
						"79815", "X6A 3G7", "25156", "75128", "Q1B 8R9",
						"67710", "80908", "34980", "65894", "54931", "97669",
						"37728", "92990", "S7P 6R0", "25566", "O5C 3K7",
						"15890", "10720", "B7C 7T6", "22696", "K7L 8O0",
						"08916", "F5K 3Y4", "01771", "97959", "J4C 9M0",
						"I9E 1C3", "M7L 9R4", "K2G 4D0", "S1Q 1L7", "X8A 3T1",
						"V6T 3H2", "Z7H 4X5", "05176", "80489", "X3A 1N8",
						"21944", "17302", "D8E 9M9", "54656", "U7P 3P2",
						"72021", "Y4J 2N5", "S4O 6E1", "U6T 3L4", "70138",
						"L1L 7A9", "G4Q 3D0", "72980", "52001", "12105",
						"10319", "K4C 7N1", "23513", "R7E 7V9", "11977",
						"T1H 7O6", "Q4Q 7Z9", "M1N 6R2", "40721", "28626",
						"53324", "60760", "10376", "50633", "25698", "R5S 1S1",
						"32602", "22999", "50184", "10609", "S4S 7C8", "41354",
						"77876", "H7N 2F5", "H2G 2E9", "86673", "X7R 3O4",
						"K2S 7L4", "P7V 8Z9", "E2B 7B7", "V1P 7T2", "I7C 7W7",
						"32799", "79581", "98863", "79441", "88557", "C1R 8A6",
						"T6C 6R8", "57184", "78493", "W9V 6I9", "97762",
						"E4B 8F5", "X7I 3R1", "C1M 4Y4", "O7B 3T8", "J1M 5L5"

				};

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * State names in Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class StatesPortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará",
						"Distrito Federal", "Espírito Santo", "Goiás",
						"Maranhão", "Mato Grosso", "Mato Grosso do Sul",
						"Minas Gerais", "Pará", "Paraíba", "Paraná",
						"Pernambuco", "Piauí", "Rio de Janeiro",
						"Rio Grande do Norte", "Rio Grande do Sul", "Rondônia",
						"Roraima", "Santa Catarina", "São Paulo", "Sergipe",
						"Tocantins"

				};

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * State names in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class StatesEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Alabama", "Alaska", "Arizona", "Arkansas", "California",
						"Colorado", "Connecticut", "Delaware", "Florida",
						"Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
						"Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",
						"Maryland", "Massachusetts", "Michigan", "Minnesota",
						"Mississippi", "Missouri", "Montana", "Nebraska",
						"Nevada", "New Hampshire", "New Jersey", "New Mexico",
						"New York", "North Carolina", "North Dakota", "Ohio",
						"Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
						"South Carolina", "South Dakota", "Tennessee", "Texas",
						"Utah", "Vermont", "Virginia", "Washington",
						"West Virginia", "Wisconsin", "Wyoming"

				};

				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Contry names in Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class CountryNamesInPortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Abecásia", "Afeganistão", "África do Sul", "Albânia",
						"Alemanha", "Andorra", "Angola", "Antígua e Barbuda",
						"Arábia Saudita", "Argélia", "Argentina", "Arménia",
						"Austrália", "Áustria", "Azerbaijão", "Bahamas",
						"Bahrein", "Bangladesh", "Barbados", "Bélgica",
						"Belize", "Benim", "Bielorrússia", "Bolívia",
						"Bósnia e Herzegovina", "Botsuana", "Brasil", "Brunei",
						"Bulgária", "Burkina Faso", "Burundi", "Butão",
						"Cabo Verde", "Camarões", "Camboja", "Canadá",
						"Cazaquistão", "República Centro", "Chade",
						"República Checa", "Chile", "China", "Chipre",
						"Chipre do Norte", "Colômbia", "Comores",
						"República Democrática do Congo", "República do Congo",
						"Coreia do Norte", "Coreia do Sul", "Costa do Marfim",
						"Costa Rica", "Croácia", "Cuba", "Dinamarca",
						"Djibuti", "Dominica", "República Dominicana",
						"El Salvador", "Egipto", "Emirados Árabes Unidos",
						"Equador", "Eritreia", "Eslováquia", "Eslovénia",
						"Espanha", "Estados Unidos", "Estónia", "Etiópia",
						"Fiji", "Filipinas", "Finlândia", "França", "Gabão",
						"Gâmbia", "Gana", "Geórgia", "Granada", "Grécia",
						"Guatemala", "Guiana", "Guiné", "Guiné",
						"Guiné Equatorial", "Haiti", "Honduras", "Hungria",
						"Iémen", "Índia", "Indonésia", "Iraque", "Irlanda",
						"Islândia", "Israel", "Itália", "Jamaica", "Japão",
						"Jordânia", "Kiribati", "Kosovo", "Kuwait", "Laos",
						"Lesoto", "Letónia", "Líbano", "Libéria", "Líbia",
						"Liechtenstein", "Lituânia", "Luxemburgo", "Macedónia",
						"Madagáscar", "Malásia", "Malaui", "Maldivas", "Mali",
						"Malta", "Ilhas Marshall", "Marrocos", "Maurícia",
						"Mauritânia", "México", "Mianmar", "Micronésia",
						"Moçambique", "Moldávia", "Mónaco", "Mongólia",
						"Montenegro", "Nagorno", "Namíbia", "Nauru", "Nepal",
						"Nicarágua", "Níger", "Nigéria", "Noruega",
						"Nova Zelândia", "Omã", "Ossétia do Sul",
						"Países Baixos", "Palau", "Palestina", "Panamá",
						"Papua", "Paquistão", "Paraguai", "Peru", "Polónia",
						"Portugal", "Qatar", "Quénia", "Quirguistão",
						"Reino Unido", "Roménia", "Ruanda", "Rússia",
						"Ilhas Salomão", "Saara Ocidental", "Samoa",
						"Santa Lúcia", "São Cristóvão e Neves", "São Marinho",
						"São Tomé e Príncipe", "São Vicente e Granadinas",
						"Seicheles", "Senegal", "Sri Lanca", "Serra Leoa",
						"Sérvia", "Singapura", "íria", "Somália",
						"Somalilândia", "Suazilândia", "Sudão", "Suécia",
						"Suíça", "Suriname", "Tailândia", "Taiwan",
						"Tajiquistão", "Tanzânia", "Timor", "Togo", "Tonga",
						"Transnístria", "Trindade e Tobago", "Tunísia",
						"Turquemenistão", "Turquia", "Tuvalu", "Ucrânia",
						"Uganda", "Uruguai", "Uzbequistão", "Vanuatu",
						"Cidade do Vaticano", "Venezuela", "Vietname",
						"Zâmbia", "Zimbabué"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Contry names in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class CountryNamesInEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "Afghanistan", "Albania", "Algeria",
						"American Samoa", "Andorra", "Angola", "Anguilla",
						"Antarctica", "Antigua And Barbuda", "Argentina",
						"Armenia", "Aruba", "Australia", "Austria",
						"Azerbaijan", "Bahamas", "Bahrain", "Bangladesh",
						"Barbados", "Belarus", "Belgium", "Belize", "Benin",
						"Bermuda", "Bhutan", "Bolivia",
						"Bosnia And Herzegowina", "Botswana", "Bouvet Island",
						"Brazil", "British Indian Ocean Territory",
						"Brunei Darussalam", "Bulgaria", "Burkina Faso",
						"Burundi", "Cambodia", "Cameroon", "Canada",
						"Cape Verde", "Cayman Islands",
						"Central African Republic", "Chad", "Chile", "China",
						"Christmas Island", "Cocos Islands", "Colombia",
						"Comoros", "Congo", "Congo, The Drc", "Cook Islands",
						"Costa Rica", "Cote D'ivoire", "Croatia", "Cuba",
						"Cyprus", "Czech Republic", "Denmark", "Djibouti",
						"Dominica", "Dominican Republic", "East Timor",
						"Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
						"Eritrea", "Estonia", "Ethiopia", "Falkland Islands",
						"Faroe Islands", "Fiji", "Finland", "France",
						"France, Metropolitan", "French Guiana",
						"French Polynesia", "French Southern Territories",
						"Gabon", "Gambia", "Georgia", "Germany", "Ghana",
						"Gibraltar", "Greece", "Greenland", "Grenada",
						"Guadeloupe", "Guam", "Guatemala", "Guinea",
						"Guinea-bissau", "Guyana", "Haiti",
						"Heard And Mc Donald Islands", "Holy See", "Honduras",
						"Hong Kong", "Hungary", "Iceland", "India",
						"Indonesia", "Iran", "Iraq", "Ireland", "Israel",
						"Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan",
						"Kenya", "Kiribati", "Korea, D.p.r.o.",
						"Korea, Republic Of", "Kuwait", "Kyrgyzstan", "Laos",
						"Latvia", "Lebanon", "Lesotho", "Liberia",
						"Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania",
						"Luxembourg", "Macau", "Macedonia", "Madagascar",
						"Malawi", "Malaysia", "Maldives", "Mali", "Malta",
						"Marshall Islands", "Martinique", "Mauritania",
						"Mauritius", "Mayotte", "Mexico",
						"Micronesia, Federated States Of",
						"Moldova, Republic Of", "Monaco", "Mongolia",
						"Montserrat", "Morocco", "Mozambique", "Myanmar",
						"Namibia", "Nauru", "Nepal", "Netherlands",
						"Netherlands Antilles", "New Caledonia", "New Zealand",
						"Nicaragua", "Niger", "Nigeria", "Niue",
						"Norfolk Island", "Northern Mariana Islands", "Norway",
						"Oman", "Pakistan", "Palau", "Panama",
						"Papua New Guinea", "Paraguay", "Peru", "Philippines",
						"Pitcairn", "Poland", "Portugal", "Puerto Rico",
						"Qatar", "Reunion", "Romania", "Russian Federation",
						"Rwanda", "Saint Kitts And Nevis", "Saint Lucia",
						"Saint Vincent And The Grenadines", "Samoa",
						"San Marino", "Sao Tome And Principe", "Saudi Arabia",
						"Senegal", "Seychelles", "Sierra Leone", "Singapore",
						"Slovakia", "Slovenia", "Solomon Islands", "Somalia",
						"South Africa", "South Georgia And South S.s.",
						"Spain", "Sri Lanka", "St. Helena",
						"St. Pierre And Miquelon", "Sudan", "Suriname",
						"Svalbard And Jan Mayen Islands", "Swaziland",
						"Sweden", "Switzerland", "Syrian Arab Republic",
						"Taiwan, Province Of China", "Tajikistan",
						"Tanzania, United Republic Of", "Thailand", "Togo",
						"Tokelau", "Tonga", "Trinidad And Tobago", "Tunisia",
						"Turkey", "Turkmenistan", "Turks And Caicos Islands",
						"Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
						"United Kingdom", "United States",
						"U.s. Minor Islands", "Uruguay", "Uzbekistan",
						"Vanuatu", "Venezuela", "Viet Nam", "Virgin Islands",
						"Virgin Islands", "Wallis And Futuna Islands",
						"Western Sahara", "Yemen", "Yugoslavia", "Zambia",
						"Zimbabwe "

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Two letters country codes.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class TwoLettersCountryCodes implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "AF", "AL", "DZ", "AS", "AD", "AO", "AI",
						"AQ", "AG", "AR", "AM", "AW", "AU", "AT", "AZ", "BS",
						"BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT",
						"BO", "BA", "BW", "BV", "BR", "IO", "BN", "BG", "BF",
						"BI", "KH", "CM", "CA", "CV", "KY", "CF", "TD", "CL",
						"CN", "CX", "CC", "CO", "KM", "CG", "CD", "CK", "CR",
						"CI", "HR", "CU", "CY", "CZ", "DK", "DJ", "DM", "DO",
						"TP", "EC", "EG", "SV", "GQ", "ER", "EE", "ET", "FK",
						"FO", "FJ", "FI", "FR", "FX", "GF", "PF", "TF", "GA",
						"GM", "GE", "DE", "GH", "GI", "GR", "GL", "GD", "GP",
						"GU", "GT", "GN", "GW", "GY", "HT", "HM", "VA", "HN",
						"HK", "HU", "IS", "IN", "ID", "IR", "IQ", "IE", "IL",
						"IT", "JM", "JP", "JO", "KZ", "KE", "KI", "KP", "KR",
						"KW", "KG", "LA", "LV", "LB", "LS", "LR", "LY", "LI",
						"LT", "LU", "MO", "MK", "MG", "MW", "MY", "MV", "ML",
						"MT", "MH", "MQ", "MR", "MU", "YT", "MX", "FM", "MD",
						"MC", "MN", "MS", "MA", "MZ", "MM", "NA", "NR", "NP",
						"NL", "AN", "NC", "NZ", "NI", "NE", "NG", "NU", "NF",
						"MP", "NO", "OM", "PK", "PW", "PA", "PG", "PY", "PE",
						"PH", "PN", "PL", "PT", "PR", "QA", "RE", "RO", "RU",
						"RW", "KN", "LC", "VC", "WS", "SM", "ST", "SA", "SN",
						"SC", "SL", "SG", "SK", "SI", "SB", "SO", "ZA", "GS",
						"ES", "LK", "SH", "PM", "SD", "SR", "SJ", "SZ", "SE",
						"CH", "SY", "TW", "TJ", "TZ", "TH", "TG", "TK", "TO",
						"TT", "TN", "TR", "TM", "TC", "TV", "UG", "UA", "AE",
						"GB", "US", "UM", "UY", "UZ", "VU", "VE", "VN", "VG",
						"VI", "WF", "EH", "YE", "YU", "ZM", "ZW"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Three letters country codes.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class ThreeLettersCountryCodes implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = { "AFG", "ALB", "DZA", "ASM", "AND", "AGO",
						"AIA", "ATA", "ATG", "ARG", "ARM", "ABW", "AUS", "AUT",
						"AZE", "BHS", "BHR", "BGD", "BRB", "BLR", "BEL", "BLZ",
						"BEN", "BMU", "BTN", "BOL", "BIH", "BWA", "BVT", "BRA",
						"IOT", "BRN", "BGR", "BFA", "BDI", "KHM", "CMR", "CAN",
						"CPV", "CYM", "CAF", "TCD", "CHL", "CHN", "CXR", "CCK",
						"COL", "COM", "COG", "COD", "COK", "CRI", "CIV", "HRV",
						"CUB", "CYP", "CZE", "DNK", "DJI", "DMA", "DOM", "TMP",
						"ECU", "EGY", "SLV", "GNQ", "ERI", "EST", "ETH", "FLK",
						"FRO", "FJI", "FIN", "FRA", "FXX", "GUF", "PYF", "ATF",
						"GAB", "GMB", "GEO", "DEU", "GHA", "GIB", "GRC", "GRL",
						"GRD", "GLP", "GUM", "GTM", "GIN", "GNB", "GUY", "HTI",
						"HMD", "VAT", "HND", "HKG", "HUN", "ISL", "IND", "IDN",
						"IRN", "IRQ", "IRL", "ISR", "ITA", "JAM", "JPN", "JOR",
						"KAZ", "KEN", "KIR", "PRK", "KOR", "KWT", "KGZ", "LAO",
						"LVA", "LBN", "LSO", "LBR", "LBY", "LIE", "LTU", "LUX",
						"MAC", "MKD", "MDG", "MWI", "MYS", "MDV", "MLI", "MLT",
						"MHL", "MTQ", "MRT", "MUS", "MYT", "MEX", "FSM", "MDA",
						"MCO", "MNG", "MSR", "MAR", "MOZ", "MMR", "NAM", "NRU",
						"NPL", "NLD", "ANT", "NCL", "NZL", "NIC", "NER", "NGA",
						"NIU", "NFK", "MNP", "NOR", "OMN", "PAK", "PLW", "PAN",
						"PNG", "PRY", "PER", "PHL", "PCN", "POL", "PRT", "PRI",
						"QAT", "REU", "ROM", "RUS", "RWA", "KNA", "LCA", "VCT",
						"WSM", "SMR", "STP", "SAU", "SEN", "SYC", "SLE", "SGP",
						"SVK", "SVN", "SLB", "SOM", "ZAF", "SGS", "ESP", "LKA",
						"SHN", "SPM", "SDN", "SUR", "SJM", "SWZ", "SWE", "CHE",
						"SYR", "TWN", "TJK", "TZA", "THA", "TGO", "TKL", "TON",
						"TTO", "TUN", "TUR", "TKM", "TCA", "TUV", "UGA", "UKR",
						"ARE", "GBR", "USA", "UMI", "URY", "UZB", "VUT", "VEN",
						"VNM", "VGB", "VIR", "WLF", "ESH", "YEM", "YUG", "ZMB",
						"ZWE"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Three letters month in Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class ThreeLettersMonthInPortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set",
						"Out", "Nov", "Dez"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Three letters month in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class ThreeLettersMonthInEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Jan", "Feb", "Mar", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep",
						"Oct", "Nov", "Dec"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * One of the provided options.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class SetOfValues implements Generator {

			String values[];

			/**
			 * Constructor.
			 * 
			 * @param content
			 *            Options separeted by "|".
			 */
			@SuppressWarnings("synthetic-access")
			SetOfValues(String content) {

				this.values = RandomDataGeneratorFilter.getValues(content);

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				return this.values[randomGenerator.nextInt(this.values.length)];
			}
		}

		/**
		 * Generate a number between an interval.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class NumberBetweenInterval implements Generator {

			private int moreThen;
			private int lessThen;

			/**
			 * Constructor.
			 * 
			 * @param moreThen
			 *            More than a minimum of characters.
			 * @param lessThen
			 *            Less then a maximum of characters.
			 */
			NumberBetweenInterval(int moreThen, int lessThen) {

				this.moreThen = moreThen;
				this.lessThen = lessThen;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				int generatedNumber = this.moreThen
						+ randomGenerator.nextInt(this.lessThen - this.moreThen
								- 1) + 1;

				return String.valueOf(generatedNumber);
			}
		}

		/**
		 * Generate a number between an interval filled with zeroes.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class NumberFilledWithZeroesBetweenInterval implements
				Generator {

			private int moreThen;
			private int lessThen;

			/**
			 * Constructor.
			 * 
			 * @param moreThen
			 *            More than a minimum of characters.
			 * @param lessThen
			 *            Less then a maximum of characters.
			 */
			NumberFilledWithZeroesBetweenInterval(int moreThen, int lessThen) {

				this.moreThen = moreThen;
				this.lessThen = lessThen;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				int lessThenDigits = String.valueOf(this.lessThen - 1).length();

				int generatedNumber = this.moreThen
						+ randomGenerator.nextInt(this.lessThen - this.moreThen
								- 1) + 1;

				int generatedNumberDigits = String.valueOf(generatedNumber)
						.length();

				int zeroes = lessThenDigits - generatedNumberDigits;

				String zeroesPrefix = new String();
				for (int i = 0; i < zeroes; i++) {
					zeroesPrefix += "0"; //$NON-NLS-1$
				}

				return zeroesPrefix + String.valueOf(generatedNumber);
			}
		}

		/**
		 * A timezone.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class Timezone implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"ACDT", "ACST", "ACT", "ADT", "AEDT", "AEST", "AFT", "AKDT",
						"AKST", "AMST", "AMT", "ART", "AST", "AST", "AST",
						"AST", "AWDT", "AWST", "AZOST", "AZT", "BDT", "BIOT",
						"BIT", "BOT", "BRT", "BST", "BST", "BTT", "CAT", "CCT",
						"CDT", "CEDT", "CEST", "CET", "CHAST", "CIST", "CKT",
						"CLST", "CLT", "COST", "COT", "CST", "CST", "CVT",
						"CXT", "ChST", "DST", "DFT", "EAST", "EAT", "ECT",
						"ECT", "EDT", "EEDT", "EEST", "EET", "EST", "FJT",
						"FKST", "FKT", "GALT", "GET", "GFT", "GILT", "GIT",
						"GMT", "GST", "GYT", "HADT", "HAST", "HKT", "HMT",
						"HST", "IRKT", "IRST", "IST", "IST", "IST", "JST",
						"KRAT", "KST", "LHST", "LINT", "MAGT", "MDT", "MIT",
						"MSD", "MSK", "MST", "MST", "MST", "MUT", "NDT", "NFT",
						"NPT", "NST", "NT", "OMST", "PDT", "PETT", "PHOT",
						"PKT", "PST", "PST", "RET", "SAMT", "SAST", "SBT",
						"SCT", "SLT", "SST", "SST", "TAHT", "THA", "UTC",
						"UYST", "UYT", "VET", "VLAT", "WAT", "WEDT", "WEST",
						"WET", "YAKT", "YEKT"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * A month in Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class MonthInPortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
						"Julho", "Agosto", "Setembro", "Outubro", "Novembro",
						"Dezembro"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * A month in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class MonthInEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"January", "February", "March", "April", "May", "June", "July",
						"August", "September", "October", "November",
						"December"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * A day of week in Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class DayOfWeekInPortuguese implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado",
						"Domingo"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * A day of week in English.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class DayOfWeekInEnglish implements Generator {

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("nls")
			@Override
			public String getContent() {

				Random randomGenerator = new Random();

				String values[] = {

				"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
						"Saturday", "Sunday"

				};
				return values[randomGenerator.nextInt(values.length)];
			}
		}

		/**
		 * Generate an auto increment number.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class AutoIncrement implements Generator {

			private int initial = 1;
			private int startWith = 1;
			private int endWith = Integer.MAX_VALUE - 10;

			/**
			 * Constructor.
			 */
			AutoIncrement() {

				this.initial = this.startWith;
			}

			/**
			 * Constructor.
			 */
			AutoIncrement(int startWith, int endWith) {

				this.startWith = startWith;
				this.endWith = endWith;
				this.initial = startWith;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@Override
			public String getContent() {

				String value = String.valueOf(this.initial++);

				if (this.initial > this.endWith) {
					this.initial = this.startWith;
				}

				return value;

			}
		}

		/**
		 * A date using format for Portuguese.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class DateFormatPortuguese implements Generator {

			DateFormat dateFormat;

			/**
			 * Constructor.
			 * 
			 * @param format
			 */
			DateFormatPortuguese(String format) {

				this.dateFormat = new SimpleDateFormat(format, new Locale(
						"pt", "BR")); //$NON-NLS-1$ //$NON-NLS-2$

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public String getContent() {

				return this.dateFormat.format(new Date(
						RandomDataGeneratorFilter.getRandomDate()));
			}
		}

		/**
		 * A date using format in English
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class DateFormatEnglish implements Generator {

			DateFormat dateFormat;

			/**
			 * Constructor.
			 * 
			 * @param format
			 */
			DateFormatEnglish(String format) {

				this.dateFormat = new SimpleDateFormat(format, new Locale(
						"en", "US")); //$NON-NLS-1$//$NON-NLS-2$

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public String getContent() {

				return this.dateFormat.format(new Date(
						RandomDataGeneratorFilter.getRandomDate()));
			}
		}

		/**
		 * Milliseconds after January 1, 1970 00:00:00 GMT.
		 * 
		 * @author Carlos Fernando Bella Cruz - <a
		 *         href="mailto:pessoal@carlosbcruz.com"
		 *         >pessoal@carlosbcruz.com</a>
		 */
		private class MillisecondsAfter1970 implements Generator {

			/**
			 * Constructor.
			 * 
			 * @param format
			 */
			MillisecondsAfter1970() {

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @seecom.carlosbcruz.filterany.filters.RandomDataGeneratorFilter.
			 * MaskElement .Generator#getContent()
			 */
			@SuppressWarnings("synthetic-access")
			@Override
			public String getContent() {

				return String
						.valueOf(RandomDataGeneratorFilter.getRandomDate());
			}
		}

		/**
		 * Get a generator based on a content.
		 * 
		 * @param content
		 *            The content.
		 * @return The generator correspondent to the token.
		 */
		@SuppressWarnings({ "synthetic-access", "nls" })
		private Generator getGenerator(String content) {

			// A command shoud not be empty.
			if ("".equals(content)) { //$NON-NLS-1$
				return new CopyGenerator(content);
			}

			// A command should have at least 1 letter.
			if (content.length() < 4) {
				return new CopyGenerator(content);
			}

			// A command should start by "${" and end by "}"
			String command = new String();
			if (content.charAt(0) == '$' && content.charAt(1) == '{'
					&& content.charAt(content.length() - 1) == '}') {
				command = content.substring(2, content.length() - 1);
			} else {
				return new CopyGenerator(content);
			}

			// See if the command has parameters minimum and maximum.
			int moreThenIndex = command.indexOf('>');
			int lessThenIndex = command.indexOf('<');
			boolean hasMaximumAndMinimum = false;
			int moreThen = 0;
			int lessThen = 0;
			if (moreThenIndex != -1 && lessThenIndex != -1
					&& moreThenIndex < lessThenIndex) {
				// Try to retrieve maximum and minimum.
				String commandParameter = command.substring(0, moreThenIndex);
				String moreThenParameter = command.substring(moreThenIndex + 1,
						lessThenIndex);
				String lessThenParameter = command.substring(lessThenIndex + 1);

				// Try to get the more then value.
				boolean validParameters = true;
				try {
					moreThen = Integer.parseInt(moreThenParameter);
				} catch (NumberFormatException exeption) {
					validParameters = false;
				}

				// Try to get the less then value.
				if (validParameters) {
					try {
						lessThen = Integer.parseInt(lessThenParameter);
					} catch (NumberFormatException exeption) {
						validParameters = false;
					}
				}

				// See if there are at lest two possibilities.
				if (validParameters) {
					if (moreThen >= lessThen - 2) {
						validParameters = false;
					}
				}

				// If all requisites are ok than apply the values.
				if (validParameters) {

					// Accept parameters.
					command = commandParameter;
					hasMaximumAndMinimum = true;
				}
			}

			// A number.
			if ("d".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new DigitFrom0To9();
			}

			// A lower case vowel.
			if ("a".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new LowerCaseVowel();
			}

			// An upper case vowel.
			if ("A".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new UpperCaseVowel();
			}

			// A lower case consonant.
			if ("c".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new LowerCaseConsonant();
			}

			// An upper case consonant.
			if ("C".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new UpperCaseConsonant();
			}

			// A lower case letter.
			if ("l".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new LowerCaseLetter();
			}

			// An upper case letter.
			if ("L".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new UpperCaseLetter();
			}

			// A lower case syllable.
			if ("s".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new LowerCaseSyllable();
			}

			// A upper case syllable.
			if ("S".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new UpperCaseSyllable();
			}

			// A no meaning word.
			if ("w".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new Word();
			}

			// A male name in Portuguese.
			if ("m_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new MaleNamePortuguese();
			}

			// A male name in English.
			if ("m_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new MaleNameEnglish();
			}

			// A female name in Portuguese.
			if ("f_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new FemaleNamePortuguese();
			}

			// A female name in English.
			if ("f_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new FemaleNameEnglish();
			}

			// A no gender name in Portuguese.
			if ("g_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new NoGenderNamePortuguese();
			}

			// A no gender name in English.
			if ("g_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new NoGenderNameEnglish();
			}

			// A surname in Portuguese.
			if ("s_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new SurnameInPortuguese();
			}

			// A surname in English.
			if ("s_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new SurnameInEnglish();
			}

			// Only the name part of the email (Short version, maximum of 20
			// characters).
			if ("e".equals(command) && hasMaximumAndMinimum) {

				// It should have a minimum of 5 characters.
				if (moreThen > 4) {

					return new EmailName(moreThen, lessThen);
				}

				return new CopyGenerator(content);
			}

			// A top level domain for email.
			if ("et".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new TopLevelDomains();
			}

			// A country level for email.
			if ("ec".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new EmailCountryCode();
			}

			// An address in Portuguese.
			if ("a_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new AddressPortuguese();
			}

			// An address in English.
			if ("a_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new AddressEnglish();
			}

			// City names in Portuguese.
			if ("ct_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new CitiesPortuguese();
			}

			// City names in English.
			if ("ct_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new CitiesEnglish();
			}

			// Zip codes in English.
			if ("z_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new ZipCodesEnglish();
			}

			// State names in Portuguese.
			if ("st_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new StatesPortuguese();
			}

			// State names in English.
			if ("st_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new StatesEnglish();
			}

			// Country names in Portuguese.
			if ("cn_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new CountryNamesInPortuguese();
			}

			// Country names in English.
			if ("cn_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new CountryNamesInEnglish();
			}

			// Two letters country codes.
			if ("c2".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new TwoLettersCountryCodes();
			}

			// Three letters country codes.
			if ("c3".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new ThreeLettersCountryCodes();
			}

			// A set of possible values.
			if ('!' == command.charAt(0)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new SetOfValues(command.substring(1));
			}

			// Three letters month in Portuguese.
			if ("M_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new ThreeLettersMonthInPortuguese();
			}

			// Three letters month in English.
			if ("M_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new ThreeLettersMonthInEnglish();
			}

			// A number between an interval
			if ("n".equals(command) && hasMaximumAndMinimum) {

				return new NumberBetweenInterval(moreThen, lessThen);
			}

			// A timezone.
			if ("t".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new Timezone();
			}

			// Month in Portuguese.
			if ("Mth_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new MonthInPortuguese();
			}

			// Month in English.
			if ("Mth_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new MonthInEnglish();
			}

			// Day of the week in Portuguese.
			if ("w_pt".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new DayOfWeekInPortuguese();
			}

			// Day of the week in English.
			if ("w_en".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new DayOfWeekInEnglish();
			}

			// A number between an interval prefixed with zeroes.
			if ("N".equals(command) && hasMaximumAndMinimum) {

				return new NumberFilledWithZeroesBetweenInterval(moreThen,
						lessThen);
			}

			// An auto increment number.
			if ("i".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new AutoIncrement(moreThen, lessThen);
				}
				return new AutoIncrement();
			}

			// A date on format in Portuguese.
			if ('T' == command.charAt(0)) {

				Generator returnGenerator = null;

				try {
					returnGenerator = new DateFormatPortuguese(
							command.substring(1));
				} catch (Throwable throwable) {
					returnGenerator = new CopyGenerator(content);
				}

				return returnGenerator;
			}

			// A date on format in English.
			if ('t' == command.charAt(0)) {

				Generator returnGenerator = null;

				try {
					returnGenerator = new DateFormatEnglish(
							command.substring(1));
				} catch (Throwable throwable) {
					returnGenerator = new CopyGenerator(content);
				}

				return returnGenerator;
			}

			// Misisseconds since 1970.
			if ("ms".equals(command)) {
				if (hasMaximumAndMinimum) {
					return new CopyGenerator(content);
				}
				return new MillisecondsAfter1970();
			}

			return new CopyGenerator(content);

		}

		/**
		 * @param String
		 *            content.
		 */
		public MaskElement(String content) {

			super();

			this.generator = getGenerator(content);
		}

		/**
		 * Provide: The content of the Element.
		 * 
		 * @return content The content of the Element.
		 */
		public String getContent() {

			return this.generator.getContent();
		}
	}

	/**
	 * Provide a random date between 2006 to 2014.
	 * 
	 * @return A date.
	 */
	private static long getRandomDate() {

		long initialDate = 1298162869321L - (1000L * 60L * 60L * 24L * 30L
				* 12L * 5L);

		long oneSecond = 1000L;

		Random randomGenerator = new Random();
		long randomHours = randomGenerator.nextInt(24 * 30 * 12 * 10 * 60 * 60);

		return initialDate + (oneSecond * randomHours);
	}

	/**
	 * Split a content into elements separeted by "|" symbol.
	 * 
	 * @param content
	 *            A content separated by the "|" symbol.
	 */
	private static String[] getValues(String content) {

		ArrayList<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(content, "|"); //$NON-NLS-1$
		while (tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}

		String values[] = new String[tokens.size()];

		values = tokens.toArray(values);

		return values;
	}

	/**
	 * Split the mask based on the "$" symbol.
	 * 
	 * @param mask
	 *            The original mask.
	 * @return The original mask split based on "$" symbol.
	 */
	private static ArrayList<String> getSplits(String mask) {

		ArrayList<String> splits = new ArrayList<>();
		String split = new String();
		for (char character : mask.toCharArray()) {

			// Get the split
			if (character == '$') {

				// Add a split if it has characters.
				if (!"".equals(split)) { //$NON-NLS-1$
					splits.add(split);
				}
				split = String.valueOf(character);

			} else {

				// Append to the split.
				split += character;
			}
		}
		splits.add(split);

		return splits;
	}

	/**
	 * Refine the splits into possible tokens.
	 * 
	 * @param splits
	 *            The splits based on the "$" symbol.
	 * @return The refined list into possible tokens.
	 */
	private static ArrayList<String> getPossibleTokens(ArrayList<String> splits) {

		// Identify possible tokens
		ArrayList<String> possibleTokens = new ArrayList<>();
		for (String possibleToken : splits) {

			String split = new String();
			for (char character : possibleToken.toCharArray()) {

				// Get the split
				if (character == '}') {

					split += character;
					possibleTokens.add(split);

					split = new String();

				} else {

					// Append to the split.
					split += character;
				}
			}
			possibleTokens.add(split);
		}

		return possibleTokens;
	}

	/**
	 * Split the mask into elements that can be commands and simple contents.
	 * 
	 * @param mask
	 *            The original mask.
	 * @return The split into elements that can be commands and simple contents.
	 */
	public static MaskElement[] getMaskElements(String mask) {

		// There is no mask.
		if ("".equals(mask.toString())) { //$NON-NLS-1$
			return new MaskElement[0];
		}

		// See if there are tokens specification.

		// Split the mask based on "$" symbol.
		ArrayList<String> splits = getSplits(mask);
		// Refine the splits into possible tokens.
		ArrayList<String> possibleTokens = getPossibleTokens(splits);

		// Generate mask elements.
		MaskElement[] masks = new MaskElement[possibleTokens.size()];
		for (int i = 0; i < possibleTokens.size(); i++) {
			masks[i] = new MaskElement(possibleTokens.get(i));
		}

		return masks;
	}

	/**
	 * Generate the test data based on the mask provided and repeated the number
	 * of times requested.
	 * 
	 * @param mask
	 *            The mask to be generated.
	 * @param numberOfRepetitions
	 *            The number of times the mask will be used to generate data.
	 * @return The test data.
	 */
	public static StringBuffer generateTestData(String mask,
			int numberOfRepetitions) {

		MaskElement[] masks = getMaskElements(mask);

		StringBuffer outputBuffer = new StringBuffer();

		for (int i = 0; i < numberOfRepetitions; i++) {
			for (MaskElement element : masks) {
				outputBuffer.append(element.getContent());
			}
			outputBuffer.append("\n"); //$NON-NLS-1$
		}

		return outputBuffer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carlosbcruz.filterany.FilterAdapter#filter(java.lang.StringBuffer,
	 * java.lang.StringBuffer)
	 */
	@Override
	protected StringBuffer filter(StringBuffer text, StringBuffer auxiliarText)
			throws FilterException {

		// If it is running on demonstration version.
		if (FilterAnyConfiguration.isRunningADemonstrationVersion()) {
			return new StringBuffer(Text.get(Text.FILTER_DEMONSTRATION_TEXT));
		}

		int numberOfRepetitions = NOT_DEFINED;

		// Retrieve the number of repetitions.
		String numberOfRepetitionsParameter = getField1().trim();
		if ("".equals(numberOfRepetitionsParameter)) { //$NON-NLS-1$

			numberOfRepetitions = 50;

		} else {

			try {
				numberOfRepetitions = Integer
						.parseInt(numberOfRepetitionsParameter);
			} catch (NumberFormatException e) {
				throw new FilterException(Text.get(
						Text.FILTER_TESTDATAGENERATORFILTER_EXCEPTION_1,
						numberOfRepetitionsParameter));
			}
			if (numberOfRepetitions <= 0) {
				throw new FilterException(Text.get(
						Text.FILTER_TESTDATAGENERATORFILTER_EXCEPTION_2,
						numberOfRepetitionsParameter));
			}
		}

		return generateTestData(text.toString(), numberOfRepetitions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getAuxiliarInputExample()
	 */
	@Override
	public String getAuxiliarInputExample() {

		return new String();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getMainInputExample()
	 */
	@Override
	public String getMainInputExample() {

		return Text.get(Text.FILTER_TESTDATAGENERATORFILTER_EXAMPLE_MAIN_INPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.Example#getOutputExample()
	 */
	@Override
	public String getOutputExample() {

		return Text.get(Text.FILTER_TESTDATAGENERATORFILTER_EXAMPLE_OUTPUT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.SpecialBehavior#getSpecialBehavior()
	 */
	@Override
	public Behavior[] getSpecialBehavior() {

		return new SpecialBehavior.Behavior[] {
				SpecialBehavior.Behavior.WORK_ONLY_ON_TEXT,
				Behavior.ACCEPT_AUTOMATIC_BEHAVIOR,
				FilterAnyConfiguration.isRunningADemonstrationVersion() ? Behavior.DEMONSTRATION_ICON
						: Behavior.NORMAL_ICON };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.CommandLine#getCommandName()
	 */
	@Override
	public String getCommandName() {

		return "Rand"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.carlosbcruz.filterany.CommandLine#getHelpDescription()
	 */
	@Override
	public String getHelpDescription() {

		return Text.get(Text.FILTER_TESTDATAGENERATORFILTER_COMMAND_LINE_HELP);
	}
}
