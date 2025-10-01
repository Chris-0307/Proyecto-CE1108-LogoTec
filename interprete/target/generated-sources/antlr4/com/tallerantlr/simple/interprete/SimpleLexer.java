// Generated from Simple.g4 by ANTLR 4.4
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
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEP=1, EOL=2, PARA=3, FIN=4, VAR=5, PRINTLN=6, PLUS=7, MINUS=8, MULT=9, 
		DIV=10, AT=11, EQ=12, ASSIGN=13, LBRACK=14, RBRACK=15, COMMA=16, PAR_OPEN=17, 
		PAR_CLOSE=18, SEMICOLON=19, ID=20, NUMBER=21, BOOLEAN=22, COMMENT=23, 
		NEWLINE=24, WS=25, ERROR=26;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'"
	};
	public static final String[] ruleNames = {
		"SEP", "EOL", "PARA", "FIN", "VAR", "PRINTLN", "PLUS", "MINUS", "MULT", 
		"DIV", "AT", "EQ", "ASSIGN", "LBRACK", "RBRACK", "COMMA", "PAR_OPEN", 
		"PAR_CLOSE", "SEMICOLON", "ID_START", "ID_CHAR", "ID", "INVALID_ID", "NUMBER", 
		"BOOLEAN", "COMMENT", "NEWLINE", "WS", "ERROR"
	};


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
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 21: return ID_sempred((RuleContext)_localctx, predIndex);
		case 22: return INVALID_ID_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean INVALID_ID_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return  getText().length() > 10 ;
		}
		return true;
	}
	private boolean ID_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return  getText().length() <= 10 ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\34\u00b2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\7\27x\n\27\f\27\16\27{\13\27\3\27\3\27"+
		"\3\30\3\30\7\30\u0081\n\30\f\30\16\30\u0084\13\30\3\30\3\30\3\30\3\30"+
		"\3\31\6\31\u008b\n\31\r\31\16\31\u008c\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\5\32\u0098\n\32\3\33\3\33\3\33\3\33\7\33\u009e\n\33\f\33"+
		"\16\33\u00a1\13\33\3\33\3\33\3\34\5\34\u00a6\n\34\3\34\3\34\3\35\6\35"+
		"\u00ab\n\35\r\35\16\35\u00ac\3\35\3\35\3\36\3\36\2\2\37\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\2+\2-\26/\2\61\27\63\30\65\31\67\329\33;\34\3\2\7\3\2c|\7\2"+
		"((\62;B\\aac|\3\2\62;\4\2\f\f\17\17\4\2\13\13\"\"\u00b6\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5?\3\2\2\2\7A\3\2\2"+
		"\2\tF\3\2\2\2\13J\3\2\2\2\rN\3\2\2\2\17V\3\2\2\2\21X\3\2\2\2\23Z\3\2\2"+
		"\2\25\\\3\2\2\2\27^\3\2\2\2\31`\3\2\2\2\33c\3\2\2\2\35e\3\2\2\2\37g\3"+
		"\2\2\2!i\3\2\2\2#k\3\2\2\2%m\3\2\2\2\'o\3\2\2\2)q\3\2\2\2+s\3\2\2\2-u"+
		"\3\2\2\2/~\3\2\2\2\61\u008a\3\2\2\2\63\u0097\3\2\2\2\65\u0099\3\2\2\2"+
		"\67\u00a5\3\2\2\29\u00aa\3\2\2\2;\u00b0\3\2\2\2=>\5\'\24\2>\4\3\2\2\2"+
		"?@\5\67\34\2@\6\3\2\2\2AB\7r\2\2BC\7c\2\2CD\7t\2\2DE\7c\2\2E\b\3\2\2\2"+
		"FG\7h\2\2GH\7k\2\2HI\7p\2\2I\n\3\2\2\2JK\7x\2\2KL\7c\2\2LM\7t\2\2M\f\3"+
		"\2\2\2NO\7r\2\2OP\7t\2\2PQ\7k\2\2QR\7p\2\2RS\7v\2\2ST\7n\2\2TU\7p\2\2"+
		"U\16\3\2\2\2VW\7-\2\2W\20\3\2\2\2XY\7/\2\2Y\22\3\2\2\2Z[\7,\2\2[\24\3"+
		"\2\2\2\\]\7\61\2\2]\26\3\2\2\2^_\7B\2\2_\30\3\2\2\2`a\7?\2\2ab\7?\2\2"+
		"b\32\3\2\2\2cd\7?\2\2d\34\3\2\2\2ef\7]\2\2f\36\3\2\2\2gh\7_\2\2h \3\2"+
		"\2\2ij\7.\2\2j\"\3\2\2\2kl\7*\2\2l$\3\2\2\2mn\7+\2\2n&\3\2\2\2op\7=\2"+
		"\2p(\3\2\2\2qr\t\2\2\2r*\3\2\2\2st\t\3\2\2t,\3\2\2\2uy\5)\25\2vx\5+\26"+
		"\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\6\27"+
		"\2\2}.\3\2\2\2~\u0082\5)\25\2\177\u0081\5+\26\2\u0080\177\3\2\2\2\u0081"+
		"\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2"+
		"\2\2\u0084\u0082\3\2\2\2\u0085\u0086\6\30\3\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0088\b\30\2\2\u0088\60\3\2\2\2\u0089\u008b\t\4\2\2\u008a\u0089\3\2\2"+
		"\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\62"+
		"\3\2\2\2\u008e\u008f\7v\2\2\u008f\u0090\7t\2\2\u0090\u0091\7w\2\2\u0091"+
		"\u0098\7g\2\2\u0092\u0093\7h\2\2\u0093\u0094\7c\2\2\u0094\u0095\7n\2\2"+
		"\u0095\u0096\7u\2\2\u0096\u0098\7g\2\2\u0097\u008e\3\2\2\2\u0097\u0092"+
		"\3\2\2\2\u0098\64\3\2\2\2\u0099\u009a\7\61\2\2\u009a\u009b\7\61\2\2\u009b"+
		"\u009f\3\2\2\2\u009c\u009e\n\5\2\2\u009d\u009c\3\2\2\2\u009e\u00a1\3\2"+
		"\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a2\u00a3\b\33\3\2\u00a3\66\3\2\2\2\u00a4\u00a6\7\17"+
		"\2\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00a8\7\f\2\2\u00a88\3\2\2\2\u00a9\u00ab\t\6\2\2\u00aa\u00a9\3\2\2\2"+
		"\u00ab\u00ac\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae\u00af\b\35\4\2\u00af:\3\2\2\2\u00b0\u00b1\13\2\2\2\u00b1"+
		"<\3\2\2\2\n\2y\u0082\u008c\u0097\u009f\u00a5\u00ac\5\t\34\2\2\3\2\b\2"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}