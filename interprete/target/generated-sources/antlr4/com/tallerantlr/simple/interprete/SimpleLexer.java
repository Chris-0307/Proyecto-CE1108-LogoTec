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
		SEP=1, EOL=2, PARA=3, FIN=4, VAR=5, PRINTLN=6, SUMA=7, PLUS=8, MINUS=9, 
		MULT=10, AT=11, EQ=12, ASSIGN=13, LBRACK=14, RBRACK=15, COMMA=16, PAR_OPEN=17, 
		PAR_CLOSE=18, SEMICOLON=19, ID=20, NUMBER=21, BOOLEAN=22, COMMENT=23, 
		NEWLINE=24, WS=25, ERROR=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SEP", "EOL", "PARA", "FIN", "VAR", "PRINTLN", "SUMA", "PLUS", "MINUS", 
			"MULT", "AT", "EQ", "ASSIGN", "LBRACK", "RBRACK", "COMMA", "PAR_OPEN", 
			"PAR_CLOSE", "SEMICOLON", "ID_START", "ID_CHAR", "ID", "INVALID_ID", 
			"NUMBER", "BOOLEAN", "COMMENT", "NEWLINE", "WS", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'para'", "'fin'", "'var'", "'println'", "'suma'", 
			"'+'", "'-'", "'*'", "'@'", "'=='", "'='", "'['", "']'", "','", "'('", 
			"')'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEP", "EOL", "PARA", "FIN", "VAR", "PRINTLN", "SUMA", "PLUS", 
			"MINUS", "MULT", "AT", "EQ", "ASSIGN", "LBRACK", "RBRACK", "COMMA", "PAR_OPEN", 
			"PAR_CLOSE", "SEMICOLON", "ID", "NUMBER", "BOOLEAN", "COMMENT", "NEWLINE", 
			"WS", "ERROR"
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
		case 21:
			return ID_sempred((RuleContext)_localctx, predIndex);
		case 22:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u00b5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\7\27{\n\27\f\27\16\27~\13"+
		"\27\3\27\3\27\3\30\3\30\7\30\u0084\n\30\f\30\16\30\u0087\13\30\3\30\3"+
		"\30\3\30\3\30\3\31\6\31\u008e\n\31\r\31\16\31\u008f\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\5\32\u009b\n\32\3\33\3\33\3\33\3\33\7\33\u00a1"+
		"\n\33\f\33\16\33\u00a4\13\33\3\33\3\33\3\34\5\34\u00a9\n\34\3\34\3\34"+
		"\3\35\6\35\u00ae\n\35\r\35\16\35\u00af\3\35\3\35\3\36\3\36\2\2\37\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\2+\2-\26/\2\61\27\63\30\65\31\67\329\33;\34\3\2\7\3"+
		"\2c|\7\2((\62;B\\aac|\3\2\62;\4\2\f\f\17\17\4\2\13\13\"\"\2\u00b9\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5?\3\2\2"+
		"\2\7A\3\2\2\2\tF\3\2\2\2\13J\3\2\2\2\rN\3\2\2\2\17V\3\2\2\2\21[\3\2\2"+
		"\2\23]\3\2\2\2\25_\3\2\2\2\27a\3\2\2\2\31c\3\2\2\2\33f\3\2\2\2\35h\3\2"+
		"\2\2\37j\3\2\2\2!l\3\2\2\2#n\3\2\2\2%p\3\2\2\2\'r\3\2\2\2)t\3\2\2\2+v"+
		"\3\2\2\2-x\3\2\2\2/\u0081\3\2\2\2\61\u008d\3\2\2\2\63\u009a\3\2\2\2\65"+
		"\u009c\3\2\2\2\67\u00a8\3\2\2\29\u00ad\3\2\2\2;\u00b3\3\2\2\2=>\5\'\24"+
		"\2>\4\3\2\2\2?@\5\67\34\2@\6\3\2\2\2AB\7r\2\2BC\7c\2\2CD\7t\2\2DE\7c\2"+
		"\2E\b\3\2\2\2FG\7h\2\2GH\7k\2\2HI\7p\2\2I\n\3\2\2\2JK\7x\2\2KL\7c\2\2"+
		"LM\7t\2\2M\f\3\2\2\2NO\7r\2\2OP\7t\2\2PQ\7k\2\2QR\7p\2\2RS\7v\2\2ST\7"+
		"n\2\2TU\7p\2\2U\16\3\2\2\2VW\7u\2\2WX\7w\2\2XY\7o\2\2YZ\7c\2\2Z\20\3\2"+
		"\2\2[\\\7-\2\2\\\22\3\2\2\2]^\7/\2\2^\24\3\2\2\2_`\7,\2\2`\26\3\2\2\2"+
		"ab\7B\2\2b\30\3\2\2\2cd\7?\2\2de\7?\2\2e\32\3\2\2\2fg\7?\2\2g\34\3\2\2"+
		"\2hi\7]\2\2i\36\3\2\2\2jk\7_\2\2k \3\2\2\2lm\7.\2\2m\"\3\2\2\2no\7*\2"+
		"\2o$\3\2\2\2pq\7+\2\2q&\3\2\2\2rs\7=\2\2s(\3\2\2\2tu\t\2\2\2u*\3\2\2\2"+
		"vw\t\3\2\2w,\3\2\2\2x|\5)\25\2y{\5+\26\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2"+
		"|}\3\2\2\2}\177\3\2\2\2~|\3\2\2\2\177\u0080\6\27\2\2\u0080.\3\2\2\2\u0081"+
		"\u0085\5)\25\2\u0082\u0084\5+\26\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2"+
		"\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0089\6\30\3\2\u0089\u008a\3\2\2\2\u008a\u008b\b"+
		"\30\2\2\u008b\60\3\2\2\2\u008c\u008e\t\4\2\2\u008d\u008c\3\2\2\2\u008e"+
		"\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\62\3\2\2"+
		"\2\u0091\u0092\7v\2\2\u0092\u0093\7t\2\2\u0093\u0094\7w\2\2\u0094\u009b"+
		"\7g\2\2\u0095\u0096\7h\2\2\u0096\u0097\7c\2\2\u0097\u0098\7n\2\2\u0098"+
		"\u0099\7u\2\2\u0099\u009b\7g\2\2\u009a\u0091\3\2\2\2\u009a\u0095\3\2\2"+
		"\2\u009b\64\3\2\2\2\u009c\u009d\7\61\2\2\u009d\u009e\7\61\2\2\u009e\u00a2"+
		"\3\2\2\2\u009f\u00a1\n\5\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a2\3\2"+
		"\2\2\u00a5\u00a6\b\33\3\2\u00a6\66\3\2\2\2\u00a7\u00a9\7\17\2\2\u00a8"+
		"\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\7\f"+
		"\2\2\u00ab8\3\2\2\2\u00ac\u00ae\t\6\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\u00b2\b\35\4\2\u00b2:\3\2\2\2\u00b3\u00b4\13\2\2\2\u00b4<\3\2\2\2\n\2"+
		"|\u0085\u008f\u009a\u00a2\u00a8\u00af\5\t\34\2\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}