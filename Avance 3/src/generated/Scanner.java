// Generated from C:/Users/Josue/Documents/TEC/Semestre 5/Compiladores/Proyectos/Compiladores-/Avance 2\Scanner.g4 by ANTLR 4.7
package generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Scanner extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PyCOMA=1, COMA=2, ASIGNACION=3, MAYIGUAL=4, MENIGUAL=5, IGUAL=6, MAYOR=7, 
		MENOR=8, DOSPTOS=9, SUM=10, RES=11, MUL=12, DIV=13, PARIZQ=14, PARDER=15, 
		PARCUADIZQ=16, PARCUADDER=17, CORCHETEIZQ=18, CORCHETEDER=19, COMILLADOBLE=20, 
		IF=21, WHILE=22, LET=23, ELSE=24, RETURN=25, TRUE=26, FALSE=27, FN=28, 
		PUTS=29, LEN=30, FIRST=31, LAST=32, REST=33, PUSH=34, INT=35, STRING=36, 
		ID=37, COMENTARIO_LINEA=38, COMENTARIO_MULTILINEA=39, WS=40, LINE_COMMENT=41;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PyCOMA", "COMA", "ASIGNACION", "MAYIGUAL", "MENIGUAL", "IGUAL", "MAYOR", 
		"MENOR", "DOSPTOS", "SUM", "RES", "MUL", "DIV", "PARIZQ", "PARDER", "PARCUADIZQ", 
		"PARCUADDER", "CORCHETEIZQ", "CORCHETEDER", "COMILLADOBLE", "IF", "WHILE", 
		"LET", "ELSE", "RETURN", "TRUE", "FALSE", "FN", "PUTS", "LEN", "FIRST", 
		"LAST", "REST", "PUSH", "INT", "STRING", "ID", "LETTER", "DIGIT", "COMENTARIO_LINEA", 
		"COMENTARIO_MULTILINEA", "WS", "LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'='", "'=>'", "'=<'", "'=='", "'>'", "'<'", "':'", 
		"'+'", "'-'", "'*'", "'/'", "'('", "')'", "'['", "']'", "'{'", "'}'", 
		"'\"'", "'if'", "'while'", "'let'", "'else'", "'return'", "'true'", "'false'", 
		"'fn'", "'puts'", "'len'", "'first'", "'last'", "'rest'", "'push'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PyCOMA", "COMA", "ASIGNACION", "MAYIGUAL", "MENIGUAL", "IGUAL", 
		"MAYOR", "MENOR", "DOSPTOS", "SUM", "RES", "MUL", "DIV", "PARIZQ", "PARDER", 
		"PARCUADIZQ", "PARCUADDER", "CORCHETEIZQ", "CORCHETEDER", "COMILLADOBLE", 
		"IF", "WHILE", "LET", "ELSE", "RETURN", "TRUE", "FALSE", "FN", "PUTS", 
		"LEN", "FIRST", "LAST", "REST", "PUSH", "INT", "STRING", "ID", "COMENTARIO_LINEA", 
		"COMENTARIO_MULTILINEA", "WS", "LINE_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public Scanner(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Scanner.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u0120\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3"+
		"#\3#\3#\3#\3#\3$\3$\3$\7$\u00cd\n$\f$\16$\u00d0\13$\5$\u00d2\n$\3%\3%"+
		"\3%\3%\7%\u00d8\n%\f%\16%\u00db\13%\3%\3%\3&\3&\3&\7&\u00e2\n&\f&\16&"+
		"\u00e5\13&\3\'\3\'\3(\3(\3)\3)\3)\3)\7)\u00ef\n)\f)\16)\u00f2\13)\3)\5"+
		")\u00f5\n)\3)\3)\3)\3)\3*\3*\3*\3*\3*\7*\u0100\n*\f*\16*\u0103\13*\3*"+
		"\3*\3*\3*\3*\3+\6+\u010b\n+\r+\16+\u010c\3+\3+\3,\3,\3,\3,\7,\u0115\n"+
		",\f,\16,\u0118\13,\3,\5,\u011b\n,\3,\3,\3,\3,\4\u00f0\u0101\2-\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M\2O\2Q(S)U*W+\3\2\6\3\2$$\4\2C\\c|\5\2\13\f\17\17\"\"\4\2\f"+
		"\f\17\17\2\u012a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\3Y\3\2\2\2\5[\3\2\2\2\7]\3\2\2\2\t_\3\2\2\2\13b\3\2\2"+
		"\2\re\3\2\2\2\17h\3\2\2\2\21j\3\2\2\2\23l\3\2\2\2\25n\3\2\2\2\27p\3\2"+
		"\2\2\31r\3\2\2\2\33t\3\2\2\2\35v\3\2\2\2\37x\3\2\2\2!z\3\2\2\2#|\3\2\2"+
		"\2%~\3\2\2\2\'\u0080\3\2\2\2)\u0082\3\2\2\2+\u0084\3\2\2\2-\u0087\3\2"+
		"\2\2/\u008d\3\2\2\2\61\u0091\3\2\2\2\63\u0096\3\2\2\2\65\u009d\3\2\2\2"+
		"\67\u00a2\3\2\2\29\u00a8\3\2\2\2;\u00ab\3\2\2\2=\u00b0\3\2\2\2?\u00b4"+
		"\3\2\2\2A\u00ba\3\2\2\2C\u00bf\3\2\2\2E\u00c4\3\2\2\2G\u00d1\3\2\2\2I"+
		"\u00d3\3\2\2\2K\u00de\3\2\2\2M\u00e6\3\2\2\2O\u00e8\3\2\2\2Q\u00ea\3\2"+
		"\2\2S\u00fa\3\2\2\2U\u010a\3\2\2\2W\u0110\3\2\2\2YZ\7=\2\2Z\4\3\2\2\2"+
		"[\\\7.\2\2\\\6\3\2\2\2]^\7?\2\2^\b\3\2\2\2_`\7?\2\2`a\7@\2\2a\n\3\2\2"+
		"\2bc\7?\2\2cd\7>\2\2d\f\3\2\2\2ef\7?\2\2fg\7?\2\2g\16\3\2\2\2hi\7@\2\2"+
		"i\20\3\2\2\2jk\7>\2\2k\22\3\2\2\2lm\7<\2\2m\24\3\2\2\2no\7-\2\2o\26\3"+
		"\2\2\2pq\7/\2\2q\30\3\2\2\2rs\7,\2\2s\32\3\2\2\2tu\7\61\2\2u\34\3\2\2"+
		"\2vw\7*\2\2w\36\3\2\2\2xy\7+\2\2y \3\2\2\2z{\7]\2\2{\"\3\2\2\2|}\7_\2"+
		"\2}$\3\2\2\2~\177\7}\2\2\177&\3\2\2\2\u0080\u0081\7\177\2\2\u0081(\3\2"+
		"\2\2\u0082\u0083\7$\2\2\u0083*\3\2\2\2\u0084\u0085\7k\2\2\u0085\u0086"+
		"\7h\2\2\u0086,\3\2\2\2\u0087\u0088\7y\2\2\u0088\u0089\7j\2\2\u0089\u008a"+
		"\7k\2\2\u008a\u008b\7n\2\2\u008b\u008c\7g\2\2\u008c.\3\2\2\2\u008d\u008e"+
		"\7n\2\2\u008e\u008f\7g\2\2\u008f\u0090\7v\2\2\u0090\60\3\2\2\2\u0091\u0092"+
		"\7g\2\2\u0092\u0093\7n\2\2\u0093\u0094\7u\2\2\u0094\u0095\7g\2\2\u0095"+
		"\62\3\2\2\2\u0096\u0097\7t\2\2\u0097\u0098\7g\2\2\u0098\u0099\7v\2\2\u0099"+
		"\u009a\7w\2\2\u009a\u009b\7t\2\2\u009b\u009c\7p\2\2\u009c\64\3\2\2\2\u009d"+
		"\u009e\7v\2\2\u009e\u009f\7t\2\2\u009f\u00a0\7w\2\2\u00a0\u00a1\7g\2\2"+
		"\u00a1\66\3\2\2\2\u00a2\u00a3\7h\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5\7"+
		"n\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7g\2\2\u00a78\3\2\2\2\u00a8\u00a9"+
		"\7h\2\2\u00a9\u00aa\7p\2\2\u00aa:\3\2\2\2\u00ab\u00ac\7r\2\2\u00ac\u00ad"+
		"\7w\2\2\u00ad\u00ae\7v\2\2\u00ae\u00af\7u\2\2\u00af<\3\2\2\2\u00b0\u00b1"+
		"\7n\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7p\2\2\u00b3>\3\2\2\2\u00b4\u00b5"+
		"\7h\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7t\2\2\u00b7\u00b8\7u\2\2\u00b8"+
		"\u00b9\7v\2\2\u00b9@\3\2\2\2\u00ba\u00bb\7n\2\2\u00bb\u00bc\7c\2\2\u00bc"+
		"\u00bd\7u\2\2\u00bd\u00be\7v\2\2\u00beB\3\2\2\2\u00bf\u00c0\7t\2\2\u00c0"+
		"\u00c1\7g\2\2\u00c1\u00c2\7u\2\2\u00c2\u00c3\7v\2\2\u00c3D\3\2\2\2\u00c4"+
		"\u00c5\7r\2\2\u00c5\u00c6\7w\2\2\u00c6\u00c7\7u\2\2\u00c7\u00c8\7j\2\2"+
		"\u00c8F\3\2\2\2\u00c9\u00d2\7\62\2\2\u00ca\u00ce\4\63;\2\u00cb\u00cd\4"+
		"\62;\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00c9\3\2"+
		"\2\2\u00d1\u00ca\3\2\2\2\u00d2H\3\2\2\2\u00d3\u00d9\7$\2\2\u00d4\u00d5"+
		"\7$\2\2\u00d5\u00d8\7$\2\2\u00d6\u00d8\n\2\2\2\u00d7\u00d4\3\2\2\2\u00d7"+
		"\u00d6\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2"+
		"\2\2\u00da\u00dc\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00dd\7$\2\2\u00dd"+
		"J\3\2\2\2\u00de\u00e3\5M\'\2\u00df\u00e2\5M\'\2\u00e0\u00e2\5O(\2\u00e1"+
		"\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2"+
		"\2\2\u00e3\u00e4\3\2\2\2\u00e4L\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7"+
		"\t\3\2\2\u00e7N\3\2\2\2\u00e8\u00e9\4\62;\2\u00e9P\3\2\2\2\u00ea\u00eb"+
		"\7\61\2\2\u00eb\u00ec\7\61\2\2\u00ec\u00f0\3\2\2\2\u00ed\u00ef\13\2\2"+
		"\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f0\u00ee"+
		"\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f5\7\17\2\2"+
		"\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7"+
		"\7\f\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\b)\2\2\u00f9R\3\2\2\2\u00fa\u00fb"+
		"\7\61\2\2\u00fb\u00fc\7,\2\2\u00fc\u0101\3\2\2\2\u00fd\u0100\5S*\2\u00fe"+
		"\u0100\13\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u00fe\3\2\2\2\u0100\u0103\3"+
		"\2\2\2\u0101\u0102\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0104\3\2\2\2\u0103"+
		"\u0101\3\2\2\2\u0104\u0105\7,\2\2\u0105\u0106\7\61\2\2\u0106\u0107\3\2"+
		"\2\2\u0107\u0108\b*\2\2\u0108T\3\2\2\2\u0109\u010b\t\4\2\2\u010a\u0109"+
		"\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\u010f\b+\2\2\u010fV\3\2\2\2\u0110\u0111\7\61\2\2"+
		"\u0111\u0112\7\61\2\2\u0112\u0116\3\2\2\2\u0113\u0115\n\5\2\2\u0114\u0113"+
		"\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117"+
		"\u011a\3\2\2\2\u0118\u0116\3\2\2\2\u0119\u011b\7\17\2\2\u011a\u0119\3"+
		"\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\7\f\2\2\u011d"+
		"\u011e\3\2\2\2\u011e\u011f\b,\3\2\u011fX\3\2\2\2\20\2\u00ce\u00d1\u00d7"+
		"\u00d9\u00e1\u00e3\u00f0\u00f4\u00ff\u0101\u010c\u0116\u011a\4\b\2\2\2"+
		"\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}