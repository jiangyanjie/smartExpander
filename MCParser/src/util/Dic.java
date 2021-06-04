package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;

public class Dic {
    private static HashSet<String> englishDicHashSet = new HashSet<String>();
	private static String grammar = "./englishPCFG.ser.gz";

	private static String[] options = { "-maxLength", "80", "-retainTmpSubcategories" };
    private static LexicalizedParser lp;

	public static String abbrDicFile = "dic/abbrDic.txt";
	public static String computerAbbrDicFile = "dic/computerAbbr.txt";
	public static HashMap<String, String> abbrDicHashMap = new HashMap<String, String>();
	public static HashMap<String, String> computerAbbrDicHashMap = new HashMap<>();


	static {
        // init english dic
        try {
        	FileReader fileReader = new FileReader(Config.englishDicFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                // convert to lower case
                englishDicHashSet.add(temp.toLowerCase());
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

		lp = LexicalizedParser.loadModel(grammar, options);
    }
	static {
		// init abbr dic
		ArrayList<String> file = Util.readFile(abbrDicFile);
		for (String str : file) {
			String[] temp = str.split(":=");
			String key = temp[0];
			String value = temp[1];
			abbrDicHashMap.put(key.toLowerCase(), value.toLowerCase());
		}

		// init computer abbr dic
		ArrayList<String> file2 = Util.readFile(computerAbbrDicFile);
		for (String str : file2) {
			String[] temp = str.split(":=");
			String key = temp[0];
			String value = temp[1];
			computerAbbrDicHashMap.put(key.toLowerCase(), value.toLowerCase());
		}
	}


    public static boolean isInDict(String str){
//		return true;
        if (str.length() == 1) {
            return false;
        }
        if (englishDicHashSet.contains(str.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }


    // wordnet
	static HashSet<String> getStemsWords(String[] words) {
		HashSet<String> result = new HashSet<>();
		for (String word : words) {
			HashSet<String> stems = findStem(word);
			result.addAll(stems);
		}
		return result;
	}

	public static ArrayList<String> removePrepositions(String sentence) {
		ArrayList<String> result = new ArrayList<>();

		TreebankLanguagePack tlp = lp.getOp().langpack();

		Tokenizer<? extends HasWord> toke =	tlp.getTokenizerFactory().getTokenizer(new StringReader(sentence));
		Tree parse = lp.parse(toke.tokenize());
		ArrayList<TaggedWord> tags = parse.taggedYield();
		for (TaggedWord taggedWord : tags) {
			if (taggedWord.tag().equals("IN") || taggedWord.tag().equals("TO") ) {
				continue;
			}
			result.add(taggedWord.word());
		}
		return result;
	}

	// wordnet
	public static HashSet<String> findStem(String word) {
		HashSet<String> result = new HashSet<>();
//		for (POS p1 : POS.values()) {
//			List<String> lstStem=Config.stemmer.findStems(word, p1);
//			result.addAll(lstStem); 
//		}
		result.add((new Stemmer()).stem(word));
		return result;
	}
	
	public static void main(String[] args) {
        System.out.println(removePrepositions("protect you for me"));
        System.out.println(findStem("contextualize"));
		System.out.println(verbIsFirstWord("render"));
	}

	public static boolean verbIsFirstWord(String sentence) {
		TreebankLanguagePack tlp = lp.getOp().langpack();

		Tokenizer<? extends HasWord> toke =	tlp.getTokenizerFactory().getTokenizer(new StringReader(sentence));
		Tree parse = lp.parse(toke.tokenize());
		ArrayList<TaggedWord> tags = parse.taggedYield();
		if (tags.get(0).tag().startsWith("VB")) {
			return true;
		}
		return false;
	}

	public static boolean isInDictToken(String str) {
		if (str.length() == 1) {
            return false;
        }
        if (englishDicHashSet.contains(str.toLowerCase())) {
            return true;
        } else {
            return false;
        }
	}
}
