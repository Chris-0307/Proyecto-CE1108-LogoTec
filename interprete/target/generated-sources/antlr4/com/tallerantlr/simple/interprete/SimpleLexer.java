// Generated from com\tallerantlr\simple\interprete\Simple.g4 by ANTLR 4.9.2
package com.tallerantlr.simple.interprete;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpleLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEP=1, EOL=2, PARA=3, FIN=4, VAR=5, PRINTLN=6, SUMA=7, DIVISION=8, DIV=9, 
		PLUS=10, MINUS=11, AT=12, PRODUCTO=13, MULT=14, EQ=15, ASSIGN=16, LBRACK=17, 
		RBRACK=18, COMMA=19, PAR_OPEN=20, PAR_CLOSE=21, SEMICOLON=22, ID=23, NUMBER=24, 
		BOOLEAN=25, COMMENT=26, NEWLINE=27, WS=28, ERROR=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SEP", "EOL", "PARA", "FIN", "VAR", "PRINTLN", "SUMA", "DIVISION", "DIV", 
			"PLUS", "MINUS", "AT", "PRODUCTO", "MULT", "EQ", "ASSIGN", "LBRACK", 
			"RBRACK", "COMMA", "PAR_OPEN", "PAR_CLOSE", "SEMICOLON", "ID_START", 
			"ID_CHAR", "ID", "INVALID_ID", "NUMBER", "BOOLEAN", "COMMENT", "NEWLINE", 
			"WS", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'para'", "'fin'", "'var'", "'println'", "'suma'", 
			"'divisi\u00F3n'", "'/'", "'+'", "'-'", "'@'", "'producto'", "'*'", "'=='", 
			"'='", "'['", "']'", "','", "'('", "')'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEP", "EOL", "PARA", "FIN", "VAR", "PRINTLN", "SUMA", "DIVISION", 
			"DIV", "PLUS", "MINUS", "AT", "PRODUCTO", "MULT", "EQ", "ASSIGN", "LBRACK", 
			"RBRACK", "COMMA", "PAR_OPEN", "PAR_CLOSE", "SEMICOLON", "ID", "NUMBER", 
			"BOOLEAN", "COMMENT", "NEWLINE", "WS", "ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	    @Override
	    public org.antlr.v4.runtime.Token emit() {
	        if (getType() == ERROR) {
	            throw new RuntimeException("Identificador supera 10 caracteres: " + getText());
	        }
	        return super.emit();
	    }


	public SimpleLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Simple.g4"; }

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

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 24:
			return ID_sempred((RuleContext)_localctx, predIndex);
		case 25:
			return INVALID_ID_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean ID_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return  getText().length() <= 10 ;
		}
		return true;
	}
	private boolean INVALID_ID_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return  getText().length() > 10 ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u00cf\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3"+
		"\31\3\31\3\32\3\32\7\32\u0095\n\32\f\32\16\32\u0098\13\32\3\32\3\32\3"+
		"\33\3\33\7\33\u009e\n\33\f\33\16\33\u00a1\13\33\3\33\3\33\3\33\3\33\3"+
		"\34\6\34\u00a8\n\34\r\34\16\34\u00a9\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\5\35\u00b5\n\35\3\36\3\36\3\36\3\36\7\36\u00bb\n\36\f\36\16"+
		"\36\u00be\13\36\3\36\3\36\3\37\5\37\u00c3\n\37\3\37\3\37\3 \6 \u00c8\n"+
		" \r \16 \u00c9\3 \3 \3!\3!\2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\2"+
		"\61\2\63\31\65\2\67\329\33;\34=\35?\36A\37\3\2\7\3\2c|\7\2((\62;B\\aa"+
		"c|\3\2\62;\4\2\f\f\17\17\4\2\13\13\"\"\2\u00d3\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2"+
		"\2\5E\3\2\2\2\7G\3\2\2\2\tL\3\2\2\2\13P\3\2\2\2\rT\3\2\2\2\17\\\3\2\2"+
		"\2\21a\3\2\2\2\23j\3\2\2\2\25l\3\2\2\2\27n\3\2\2\2\31p\3\2\2\2\33r\3\2"+
		"\2\2\35{\3\2\2\2\37}\3\2\2\2!\u0080\3\2\2\2#\u0082\3\2\2\2%\u0084\3\2"+
		"\2\2\'\u0086\3\2\2\2)\u0088\3\2\2\2+\u008a\3\2\2\2-\u008c\3\2\2\2/\u008e"+
		"\3\2\2\2\61\u0090\3\2\2\2\63\u0092\3\2\2\2\65\u009b\3\2\2\2\67\u00a7\3"+
		"\2\2\29\u00b4\3\2\2\2;\u00b6\3\2\2\2=\u00c2\3\2\2\2?\u00c7\3\2\2\2A\u00cd"+
		"\3\2\2\2CD\5-\27\2D\4\3\2\2\2EF\5=\37\2F\6\3\2\2\2GH\7r\2\2HI\7c\2\2I"+
		"J\7t\2\2JK\7c\2\2K\b\3\2\2\2LM\7h\2\2MN\7k\2\2NO\7p\2\2O\n\3\2\2\2PQ\7"+
		"x\2\2QR\7c\2\2RS\7t\2\2S\f\3\2\2\2TU\7r\2\2UV\7t\2\2VW\7k\2\2WX\7p\2\2"+
		"XY\7v\2\2YZ\7n\2\2Z[\7p\2\2[\16\3\2\2\2\\]\7u\2\2]^\7w\2\2^_\7o\2\2_`"+
		"\7c\2\2`\20\3\2\2\2ab\7f\2\2bc\7k\2\2cd\7x\2\2de\7k\2\2ef\7u\2\2fg\7k"+
		"\2\2gh\7\u00f5\2\2hi\7p\2\2i\22\3\2\2\2jk\7\61\2\2k\24\3\2\2\2lm\7-\2"+
		"\2m\26\3\2\2\2no\7/\2\2o\30\3\2\2\2pq\7B\2\2q\32\3\2\2\2rs\7r\2\2st\7"+
		"t\2\2tu\7q\2\2uv\7f\2\2vw\7w\2\2wx\7e\2\2xy\7v\2\2yz\7q\2\2z\34\3\2\2"+
		"\2{|\7,\2\2|\36\3\2\2\2}~\7?\2\2~\177\7?\2\2\177 \3\2\2\2\u0080\u0081"+
		"\7?\2\2\u0081\"\3\2\2\2\u0082\u0083\7]\2\2\u0083$\3\2\2\2\u0084\u0085"+
		"\7_\2\2\u0085&\3\2\2\2\u0086\u0087\7.\2\2\u0087(\3\2\2\2\u0088\u0089\7"+
		"*\2\2\u0089*\3\2\2\2\u008a\u008b\7+\2\2\u008b,\3\2\2\2\u008c\u008d\7="+
		"\2\2\u008d.\3\2\2\2\u008e\u008f\t\2\2\2\u008f\60\3\2\2\2\u0090\u0091\t"+
		"\3\2\2\u0091\62\3\2\2\2\u0092\u0096\5/\30\2\u0093\u0095\5\61\31\2\u0094"+
		"\u0093\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\u0099\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009a\6\32\2\2\u009a"+
		"\64\3\2\2\2\u009b\u009f\5/\30\2\u009c\u009e\5\61\31\2\u009d\u009c\3\2"+
		"\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a2\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a3\6\33\3\2\u00a3\u00a4\3"+
		"\2\2\2\u00a4\u00a5\b\33\2\2\u00a5\66\3\2\2\2\u00a6\u00a8\t\4\2\2\u00a7"+
		"\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2"+
		"\2\2\u00aa8\3\2\2\2\u00ab\u00ac\7v\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae"+
		"\7w\2\2\u00ae\u00b5\7g\2\2\u00af\u00b0\7h\2\2\u00b0\u00b1\7c\2\2\u00b1"+
		"\u00b2\7n\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b5\7g\2\2\u00b4\u00ab\3\2\2"+
		"\2\u00b4\u00af\3\2\2\2\u00b5:\3\2\2\2\u00b6\u00b7\7\61\2\2\u00b7\u00b8"+
		"\7\61\2\2\u00b8\u00bc\3\2\2\2\u00b9\u00bb\n\5\2\2\u00ba\u00b9\3\2\2\2"+
		"\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf"+
		"\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\b\36\3\2\u00c0<\3\2\2\2\u00c1"+
		"\u00c3\7\17\2\2\u00c2\u00c1\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\3"+
		"\2\2\2\u00c4\u00c5\7\f\2\2\u00c5>\3\2\2\2\u00c6\u00c8\t\6\2\2\u00c7\u00c6"+
		"\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cb\3\2\2\2\u00cb\u00cc\b \4\2\u00cc@\3\2\2\2\u00cd\u00ce\13\2\2\2"+
		"\u00ceB\3\2\2\2\n\2\u0096\u009f\u00a9\u00b4\u00bc\u00c2\u00c9\5\t\37\2"+
		"\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}