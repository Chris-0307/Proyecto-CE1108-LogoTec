// Generated from com\tallerantlr\simple\interprete\Simple.g4 by ANTLR 4.9.2
package com.tallerantlr.simple.interprete;

    import java.util.*;
    import com.tallerantlr.simple.interprete.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEP=1, EOL=2, PARA=3, FIN=4, VAR=5, PRINTLN=6, SUMA=7, DIVISION=8, DIV=9, 
		PLUS=10, MINUS=11, MULT=12, AT=13, EQ=14, ASSIGN=15, LBRACK=16, RBRACK=17, 
		COMMA=18, PAR_OPEN=19, PAR_CLOSE=20, SEMICOLON=21, ID=22, NUMBER=23, BOOLEAN=24, 
		COMMENT=25, NEWLINE=26, WS=27, ERROR=28, PERM=29;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_printlnStmt = 2, RULE_varDecl = 3, 
		RULE_varAssign = 4, RULE_procCallStmt = 5, RULE_procedureDef = 6, RULE_optParams = 7, 
		RULE_expression = 8, RULE_sumaExpr = 9, RULE_divisionExpr = 10, RULE_addExpr = 11, 
		RULE_multExpr = 12, RULE_term = 13, RULE_exprList = 14, RULE_argList = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "printlnStmt", "varDecl", "varAssign", "procCallStmt", 
			"procedureDef", "optParams", "expression", "sumaExpr", "divisionExpr", 
			"addExpr", "multExpr", "term", "exprList", "argList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'para'", "'fin'", "'var'", "'println'", "'suma'", 
			"'divisi\u00F3n'", "'/'", "'+'", "'-'", "'*'", "'@'", "'=='", "'='", 
			"'['", "']'", "','", "'('", "')'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEP", "EOL", "PARA", "FIN", "VAR", "PRINTLN", "SUMA", "DIVISION", 
			"DIV", "PLUS", "MINUS", "MULT", "AT", "EQ", "ASSIGN", "LBRACK", "RBRACK", 
			"COMMA", "PAR_OPEN", "PAR_CLOSE", "SEMICOLON", "ID", "NUMBER", "BOOLEAN", 
			"COMMENT", "NEWLINE", "WS", "ERROR", "PERM"
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
	public String getGrammarFileName() { return "Simple.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    Map<String, Object> globals = new HashMap<>();
	    Map<String, ProcedureDef> procTable = new HashMap<>();
	    int varDeclCount = 0;

	    static String sig(String name, int arity) { return name + "#" + arity; }

	public SimpleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<ASTNode> body;
		public ProcedureDefContext p;
		public StatementContext s;
		public ProcedureDefContext p2;
		public StatementContext s2;
		public TerminalNode EOF() { return getToken(SimpleParser.EOF, 0); }
		public List<TerminalNode> EOL() { return getTokens(SimpleParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(SimpleParser.EOL, i);
		}
		public List<TerminalNode> SEP() { return getTokens(SimpleParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(SimpleParser.SEP, i);
		}
		public List<ProcedureDefContext> procedureDef() {
			return getRuleContexts(ProcedureDefContext.class);
		}
		public ProcedureDefContext procedureDef(int i) {
			return getRuleContext(ProcedureDefContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		 ((ProgramContext)_localctx).body =  new ArrayList<>(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(32);
				match(EOL);
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PARA:
				{
				{
				setState(38);
				((ProgramContext)_localctx).p = procedureDef();
				}
				}
				break;
			case VAR:
			case PRINTLN:
			case ID:
				{
				{
				setState(39);
				((ProgramContext)_localctx).s = statement();
				 if (((ProgramContext)_localctx).s.node != null) _localctx.body.add(((ProgramContext)_localctx).s.node); 
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(57);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(45); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(44);
						_la = _input.LA(1);
						if ( !(_la==SEP || _la==EOL) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(47); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SEP || _la==EOL );
					setState(53);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case PARA:
						{
						{
						setState(49);
						((ProgramContext)_localctx).p2 = procedureDef();
						}
						}
						break;
					case VAR:
					case PRINTLN:
					case ID:
						{
						{
						setState(50);
						((ProgramContext)_localctx).s2 = statement();
						 if (((ProgramContext)_localctx).s2.node != null) _localctx.body.add(((ProgramContext)_localctx).s2.node); 
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(59);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP || _la==EOL) {
				{
				{
				setState(60);
				_la = _input.LA(1);
				if ( !(_la==SEP || _la==EOL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			match(EOF);

			        if (varDeclCount == 0) {
			            throw new RuntimeException("Error: el programa debe declarar al menos una variable (regla del enunciado).");
			        }
			        for (ASTNode n : _localctx.body) n.execute(globals);
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public ASTNode node;
		public PrintlnStmtContext printlnStmt;
		public VarDeclContext varDecl;
		public VarAssignContext varAssign;
		public ProcCallStmtContext procCallStmt;
		public PrintlnStmtContext printlnStmt() {
			return getRuleContext(PrintlnStmtContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public VarAssignContext varAssign() {
			return getRuleContext(VarAssignContext.class,0);
		}
		public ProcCallStmtContext procCallStmt() {
			return getRuleContext(ProcCallStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				((StatementContext)_localctx).printlnStmt = printlnStmt();
				 ((StatementContext)_localctx).node =  ((StatementContext)_localctx).printlnStmt.node; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				((StatementContext)_localctx).varDecl = varDecl();
				 ((StatementContext)_localctx).node =  ((StatementContext)_localctx).varDecl.node; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				((StatementContext)_localctx).varAssign = varAssign();
				 ((StatementContext)_localctx).node =  ((StatementContext)_localctx).varAssign.node; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				((StatementContext)_localctx).procCallStmt = procCallStmt();
				 ((StatementContext)_localctx).node =  ((StatementContext)_localctx).procCallStmt.node; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintlnStmtContext extends ParserRuleContext {
		public ASTNode node;
		public ExpressionContext expression;
		public TerminalNode PRINTLN() { return getToken(SimpleParser.PRINTLN, 0); }
		public TerminalNode PAR_OPEN() { return getToken(SimpleParser.PAR_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAR_CLOSE() { return getToken(SimpleParser.PAR_CLOSE, 0); }
		public TerminalNode SEP() { return getToken(SimpleParser.SEP, 0); }
		public PrintlnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printlnStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterPrintlnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitPrintlnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitPrintlnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintlnStmtContext printlnStmt() throws RecognitionException {
		PrintlnStmtContext _localctx = new PrintlnStmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_printlnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(PRINTLN);
			setState(84);
			match(PAR_OPEN);
			setState(85);
			((PrintlnStmtContext)_localctx).expression = expression();
			setState(86);
			match(PAR_CLOSE);
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(87);
				match(SEP);
				}
				break;
			}
			 ((PrintlnStmtContext)_localctx).node =  new Println(((PrintlnStmtContext)_localctx).expression.node); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public ASTNode node;
		public Token id;
		public ExpressionContext e;
		public TerminalNode VAR() { return getToken(SimpleParser.VAR, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(SimpleParser.ASSIGN, 0); }
		public TerminalNode SEP() { return getToken(SimpleParser.SEP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(VAR);
			setState(93);
			((VarDeclContext)_localctx).id = match(ID);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(94);
				match(ASSIGN);
				setState(95);
				((VarDeclContext)_localctx).e = expression();
				}
			}

			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(98);
				match(SEP);
				}
				break;
			}

			            varDeclCount++;
			            if (((VarDeclContext)_localctx).e.node != null) ((VarDeclContext)_localctx).node =  new VarAssign((((VarDeclContext)_localctx).id!=null?((VarDeclContext)_localctx).id.getText():null), ((VarDeclContext)_localctx).e.node);
			            else                 ((VarDeclContext)_localctx).node =  new VarDecl((((VarDeclContext)_localctx).id!=null?((VarDeclContext)_localctx).id.getText():null));
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarAssignContext extends ParserRuleContext {
		public ASTNode node;
		public Token id;
		public ExpressionContext e;
		public TerminalNode ASSIGN() { return getToken(SimpleParser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEP() { return getToken(SimpleParser.SEP, 0); }
		public VarAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterVarAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitVarAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitVarAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarAssignContext varAssign() throws RecognitionException {
		VarAssignContext _localctx = new VarAssignContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			((VarAssignContext)_localctx).id = match(ID);
			setState(104);
			match(ASSIGN);
			setState(105);
			((VarAssignContext)_localctx).e = expression();
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(106);
				match(SEP);
				}
				break;
			}
			 ((VarAssignContext)_localctx).node =  new VarAssign((((VarAssignContext)_localctx).id!=null?((VarAssignContext)_localctx).id.getText():null), ((VarAssignContext)_localctx).e.node); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcCallStmtContext extends ParserRuleContext {
		public ASTNode node;
		public Token id;
		public ArgListContext a;
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode PAR_OPEN() { return getToken(SimpleParser.PAR_OPEN, 0); }
		public TerminalNode PAR_CLOSE() { return getToken(SimpleParser.PAR_CLOSE, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode SEP() { return getToken(SimpleParser.SEP, 0); }
		public ProcCallStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procCallStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterProcCallStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitProcCallStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitProcCallStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcCallStmtContext procCallStmt() throws RecognitionException {
		ProcCallStmtContext _localctx = new ProcCallStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_procCallStmt);

		    java.util.List<ASTNode> argNodes = new java.util.ArrayList<>();
		    int arity = 0;

		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			((ProcCallStmtContext)_localctx).id = match(ID);
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAR_OPEN:
				{
				setState(112);
				match(PAR_OPEN);
				setState(113);
				((ProcCallStmtContext)_localctx).a = argList();
				setState(114);
				match(PAR_CLOSE);

				                argNodes = ((ProcCallStmtContext)_localctx).a.list;
				                arity = argNodes.size();
				            
				}
				break;
			case EOF:
			case SEP:
			case EOL:
			case FIN:
			case VAR:
			case PRINTLN:
			case ID:
				{

				                argNodes = new java.util.ArrayList<>();
				                arity = 0;
				            
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(120);
				match(SEP);
				}
				break;
			}

			            String key = sig((((ProcCallStmtContext)_localctx).id!=null?((ProcCallStmtContext)_localctx).id.getText():null), arity);
			            ProcedureDef def = procTable.get(key);
			            if (def == null) {
			                throw new RuntimeException("Procedimiento no definido: " + (((ProcCallStmtContext)_localctx).id!=null?((ProcCallStmtContext)_localctx).id.getText():null) + " con aridad " + arity);
			            }
			            ((ProcCallStmtContext)_localctx).node =  new ProcedureCall(def, argNodes);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureDefContext extends ParserRuleContext {
		public java.util.List<String> formalParams;
		public Token name;
		public OptParamsContext p;
		public StatementContext s;
		public TerminalNode PARA() { return getToken(SimpleParser.PARA, 0); }
		public TerminalNode FIN() { return getToken(SimpleParser.FIN, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public OptParamsContext optParams() {
			return getRuleContext(OptParamsContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> SEP() { return getTokens(SimpleParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(SimpleParser.SEP, i);
		}
		public List<TerminalNode> EOL() { return getTokens(SimpleParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(SimpleParser.EOL, i);
		}
		public ProcedureDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterProcedureDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitProcedureDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitProcedureDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureDefContext procedureDef() throws RecognitionException {
		ProcedureDefContext _localctx = new ProcedureDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_procedureDef);

		    java.util.List<ASTNode> body = new java.util.ArrayList<ASTNode>();
		    ((ProcedureDefContext)_localctx).formalParams =  new java.util.ArrayList<String>();

		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(PARA);
			setState(126);
			((ProcedureDefContext)_localctx).name = match(ID);
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				{
				setState(127);
				((ProcedureDefContext)_localctx).p = optParams();
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(128);
					match(EOL);
					}
					}
					setState(133);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}

				                ((ProcedureDefContext)_localctx).formalParams =  ((ProcedureDefContext)_localctx).p.ids;
				            
				}
				break;
			case EOL:
			case FIN:
			case VAR:
			case PRINTLN:
			case ID:
				{
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(136);
					match(EOL);
					}
					}
					setState(141);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}

				                ((ProcedureDefContext)_localctx).formalParams =  new java.util.ArrayList<String>();
				            
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VAR) | (1L << PRINTLN) | (1L << ID))) != 0)) {
				{
				{
				setState(145);
				((ProcedureDefContext)_localctx).s = statement();
				 if (((ProcedureDefContext)_localctx).s.node != null) body.add(((ProcedureDefContext)_localctx).s.node); 
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(147);
					match(EOL);
					}
					}
					setState(152);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(158);
			match(FIN);
			setState(162);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(159);
					_la = _input.LA(1);
					if ( !(_la==SEP || _la==EOL) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(164);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}

			            String key = sig((((ProcedureDefContext)_localctx).name!=null?((ProcedureDefContext)_localctx).name.getText():null), _localctx.formalParams.size());
			            if (procTable.containsKey(key)) {
			                throw new RuntimeException("Procedimiento duplicado con la misma firma: " + key);
			            }
			            ProcedureDef def = new ProcedureDef((((ProcedureDefContext)_localctx).name!=null?((ProcedureDefContext)_localctx).name.getText():null), _localctx.formalParams, body, globals, procTable);
			            procTable.put(key, def);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptParamsContext extends ParserRuleContext {
		public java.util.List<String> ids;
		public Token id1;
		public Token idn;
		public TerminalNode LBRACK() { return getToken(SimpleParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(SimpleParser.RBRACK, 0); }
		public List<TerminalNode> ID() { return getTokens(SimpleParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SimpleParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimpleParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimpleParser.COMMA, i);
		}
		public OptParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterOptParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitOptParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitOptParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptParamsContext optParams() throws RecognitionException {
		OptParamsContext _localctx = new OptParamsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_optParams);
		 ((OptParamsContext)_localctx).ids =  new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(LBRACK);
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(168);
				((OptParamsContext)_localctx).id1 = match(ID);
				 _localctx.ids.add((((OptParamsContext)_localctx).id1!=null?((OptParamsContext)_localctx).id1.getText():null)); 
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(170);
					match(COMMA);
					setState(171);
					((OptParamsContext)_localctx).idn = match(ID);
					 _localctx.ids.add((((OptParamsContext)_localctx).idn!=null?((OptParamsContext)_localctx).idn.getText():null)); 
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(180);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ASTNode node;
		public SumaExprContext sumaExpr;
		public DivisionExprContext divisionExpr;
		public AddExprContext a;
		public AddExprContext b;
		public SumaExprContext sumaExpr() {
			return getRuleContext(SumaExprContext.class,0);
		}
		public DivisionExprContext divisionExpr() {
			return getRuleContext(DivisionExprContext.class,0);
		}
		public List<AddExprContext> addExpr() {
			return getRuleContexts(AddExprContext.class);
		}
		public AddExprContext addExpr(int i) {
			return getRuleContext(AddExprContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(SimpleParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(SimpleParser.EQ, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expression);
		int _la;
		try {
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				((ExpressionContext)_localctx).sumaExpr = sumaExpr();
				 ((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).sumaExpr.node; 
				}
				break;
			case DIVISION:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				((ExpressionContext)_localctx).divisionExpr = divisionExpr();
				 ((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).divisionExpr.node; 
				}
				break;
			case PAR_OPEN:
			case ID:
			case NUMBER:
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(188);
				((ExpressionContext)_localctx).a = addExpr();
				 ((ExpressionContext)_localctx).node =  ((ExpressionContext)_localctx).a.node; 
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EQ) {
					{
					{
					setState(190);
					match(EQ);
					setState(191);
					((ExpressionContext)_localctx).b = addExpr();
					 ((ExpressionContext)_localctx).node =  new Equal(_localctx.node, ((ExpressionContext)_localctx).b.node); 
					}
					}
					setState(198);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SumaExprContext extends ParserRuleContext {
		public ASTNode node;
		public ExprListContext exprList;
		public TerminalNode SUMA() { return getToken(SimpleParser.SUMA, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public SumaExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sumaExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterSumaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitSumaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitSumaExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SumaExprContext sumaExpr() throws RecognitionException {
		SumaExprContext _localctx = new SumaExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_sumaExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(SUMA);
			setState(202);
			((SumaExprContext)_localctx).exprList = exprList();
			 ((SumaExprContext)_localctx).node =  new Suma(((SumaExprContext)_localctx).exprList.list); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DivisionExprContext extends ParserRuleContext {
		public ASTNode node;
		public AddExprContext e1;
		public AddExprContext e2;
		public TerminalNode DIVISION() { return getToken(SimpleParser.DIVISION, 0); }
		public List<AddExprContext> addExpr() {
			return getRuleContexts(AddExprContext.class);
		}
		public AddExprContext addExpr(int i) {
			return getRuleContext(AddExprContext.class,i);
		}
		public DivisionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divisionExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterDivisionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitDivisionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitDivisionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DivisionExprContext divisionExpr() throws RecognitionException {
		DivisionExprContext _localctx = new DivisionExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_divisionExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(DIVISION);
			setState(206);
			((DivisionExprContext)_localctx).e1 = addExpr();
			setState(207);
			((DivisionExprContext)_localctx).e2 = addExpr();
			 ((DivisionExprContext)_localctx).node =  new Division(((DivisionExprContext)_localctx).e1.node, ((DivisionExprContext)_localctx).e2.node); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddExprContext extends ParserRuleContext {
		public ASTNode node;
		public MultExprContext t1;
		public MultExprContext t2;
		public MultExprContext t3;
		public List<MultExprContext> multExpr() {
			return getRuleContexts(MultExprContext.class);
		}
		public MultExprContext multExpr(int i) {
			return getRuleContext(MultExprContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(SimpleParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(SimpleParser.PLUS, i);
		}
		public List<TerminalNode> AT() { return getTokens(SimpleParser.AT); }
		public TerminalNode AT(int i) {
			return getToken(SimpleParser.AT, i);
		}
		public AddExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitAddExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitAddExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddExprContext addExpr() throws RecognitionException {
		AddExprContext _localctx = new AddExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_addExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			((AddExprContext)_localctx).t1 = multExpr();
			 ((AddExprContext)_localctx).node =  ((AddExprContext)_localctx).t1.node; 
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==AT) {
				{
				setState(220);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(212);
					match(PLUS);
					setState(213);
					((AddExprContext)_localctx).t2 = multExpr();
					 ((AddExprContext)_localctx).node =  new Addition(_localctx.node, ((AddExprContext)_localctx).t2.node); 
					}
					break;
				case AT:
					{
					setState(216);
					match(AT);
					setState(217);
					((AddExprContext)_localctx).t3 = multExpr();
					 ((AddExprContext)_localctx).node =  new Sumaplicacion(_localctx.node, ((AddExprContext)_localctx).t3.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultExprContext extends ParserRuleContext {
		public ASTNode node;
		public TermContext t1;
		public TermContext t2;
		public TermContext t3;
		public TermContext t4;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(SimpleParser.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(SimpleParser.MULT, i);
		}
		public List<TerminalNode> DIV() { return getTokens(SimpleParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(SimpleParser.DIV, i);
		}
		public List<TerminalNode> PERM() { return getTokens(SimpleParser.PERM); }
		public TerminalNode PERM(int i) {
			return getToken(SimpleParser.PERM, i);
		}
		public MultExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitMultExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitMultExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultExprContext multExpr() throws RecognitionException {
		MultExprContext _localctx = new MultExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_multExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			((MultExprContext)_localctx).t1 = term();
			 ((MultExprContext)_localctx).node =  ((MultExprContext)_localctx).t1.node; 
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DIV) | (1L << MULT) | (1L << PERM))) != 0)) {
				{
				setState(239);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MULT:
					{
					setState(227);
					match(MULT);
					setState(228);
					((MultExprContext)_localctx).t2 = term();
					 ((MultExprContext)_localctx).node =  new Multiplication(_localctx.node, ((MultExprContext)_localctx).t2.node); 
					}
					break;
				case DIV:
					{
					setState(231);
					match(DIV);
					setState(232);
					((MultExprContext)_localctx).t3 = term();
					 ((MultExprContext)_localctx).node =  new Division(_localctx.node, ((MultExprContext)_localctx).t3.node); 
					}
					break;
				case PERM:
					{
					setState(235);
					match(PERM);
					setState(236);
					((MultExprContext)_localctx).t4 = term();
					 ((MultExprContext)_localctx).node =  new Permutation(_localctx.node, ((MultExprContext)_localctx).t4.node); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public ASTNode node;
		public Token NUMBER;
		public Token BOOLEAN;
		public Token ID;
		public ExpressionContext expression;
		public TerminalNode NUMBER() { return getToken(SimpleParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(SimpleParser.BOOLEAN, 0); }
		public TerminalNode ID() { return getToken(SimpleParser.ID, 0); }
		public TerminalNode PAR_OPEN() { return getToken(SimpleParser.PAR_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAR_CLOSE() { return getToken(SimpleParser.PAR_CLOSE, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_term);
		try {
			setState(255);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				((TermContext)_localctx).NUMBER = match(NUMBER);
				 ((TermContext)_localctx).node =  new Constant(Integer.parseInt((((TermContext)_localctx).NUMBER!=null?((TermContext)_localctx).NUMBER.getText():null))); 
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				((TermContext)_localctx).BOOLEAN = match(BOOLEAN);
				 ((TermContext)_localctx).node =  new Constant(Boolean.parseBoolean((((TermContext)_localctx).BOOLEAN!=null?((TermContext)_localctx).BOOLEAN.getText():null))); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				((TermContext)_localctx).ID = match(ID);
				 ((TermContext)_localctx).node =  new VarRef((((TermContext)_localctx).ID!=null?((TermContext)_localctx).ID.getText():null)); 
				}
				break;
			case PAR_OPEN:
				enterOuterAlt(_localctx, 4);
				{
				setState(250);
				match(PAR_OPEN);
				setState(251);
				((TermContext)_localctx).expression = expression();
				setState(252);
				match(PAR_CLOSE);
				 ((TermContext)_localctx).node =  ((TermContext)_localctx).expression.node; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public List<ASTNode> list;
		public AddExprContext e1;
		public AddExprContext e2;
		public List<AddExprContext> addExpr() {
			return getRuleContexts(AddExprContext.class);
		}
		public AddExprContext addExpr(int i) {
			return getRuleContext(AddExprContext.class,i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exprList);
		 ((ExprListContext)_localctx).list =  new ArrayList<>(); 
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			((ExprListContext)_localctx).e1 = addExpr();
			 _localctx.list.add(((ExprListContext)_localctx).e1.node); 
			setState(264);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(259);
					((ExprListContext)_localctx).e2 = addExpr();
					 _localctx.list.add(((ExprListContext)_localctx).e2.node); 
					}
					} 
				}
				setState(266);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public List<ASTNode> list;
		public ExpressionContext e1;
		public ExpressionContext en;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimpleParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimpleParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleListener ) ((SimpleListener)listener).exitArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleVisitor ) return ((SimpleVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_argList);
		 ((ArgListContext)_localctx).list =  new ArrayList<>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			((ArgListContext)_localctx).e1 = expression();
			 _localctx.list.add(((ArgListContext)_localctx).e1.node); 
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(269);
				match(COMMA);
				setState(270);
				((ArgListContext)_localctx).en = expression();
				 _localctx.list.add(((ArgListContext)_localctx).en.node); 
				}
				}
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37\u0119\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2"+
		"$\n\2\f\2\16\2\'\13\2\3\2\3\2\3\2\3\2\5\2-\n\2\3\2\6\2\60\n\2\r\2\16\2"+
		"\61\3\2\3\2\3\2\3\2\5\28\n\2\7\2:\n\2\f\2\16\2=\13\2\3\2\7\2@\n\2\f\2"+
		"\16\2C\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\5\3T\n\3\3\4\3\4\3\4\3\4\3\4\5\4[\n\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5c"+
		"\n\5\3\5\5\5f\n\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6n\n\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7y\n\7\3\7\5\7|\n\7\3\7\3\7\3\b\3\b\3\b\3\b\7\b\u0084"+
		"\n\b\f\b\16\b\u0087\13\b\3\b\3\b\3\b\7\b\u008c\n\b\f\b\16\b\u008f\13\b"+
		"\3\b\5\b\u0092\n\b\3\b\3\b\3\b\7\b\u0097\n\b\f\b\16\b\u009a\13\b\7\b\u009c"+
		"\n\b\f\b\16\b\u009f\13\b\3\b\3\b\7\b\u00a3\n\b\f\b\16\b\u00a6\13\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00b0\n\t\f\t\16\t\u00b3\13\t\5\t\u00b5"+
		"\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00c5"+
		"\n\n\f\n\16\n\u00c8\13\n\5\n\u00ca\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00df\n\r\f\r\16"+
		"\r\u00e2\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\7\16\u00f2\n\16\f\16\16\16\u00f5\13\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0102\n\17\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u0109\n\20\f\20\16\20\u010c\13\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\7\21\u0114\n\21\f\21\16\21\u0117\13\21\3\21\2\2\22\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \2\3\3\2\3\4\2\u012c\2%\3\2\2\2\4S\3\2"+
		"\2\2\6U\3\2\2\2\b^\3\2\2\2\ni\3\2\2\2\fq\3\2\2\2\16\177\3\2\2\2\20\u00a9"+
		"\3\2\2\2\22\u00c9\3\2\2\2\24\u00cb\3\2\2\2\26\u00cf\3\2\2\2\30\u00d4\3"+
		"\2\2\2\32\u00e3\3\2\2\2\34\u0101\3\2\2\2\36\u0103\3\2\2\2 \u010d\3\2\2"+
		"\2\"$\7\4\2\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&,\3\2\2\2\'%\3"+
		"\2\2\2(-\5\16\b\2)*\5\4\3\2*+\b\2\1\2+-\3\2\2\2,(\3\2\2\2,)\3\2\2\2-;"+
		"\3\2\2\2.\60\t\2\2\2/.\3\2\2\2\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2"+
		"\62\67\3\2\2\2\638\5\16\b\2\64\65\5\4\3\2\65\66\b\2\1\2\668\3\2\2\2\67"+
		"\63\3\2\2\2\67\64\3\2\2\28:\3\2\2\29/\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3"+
		"\2\2\2<A\3\2\2\2=;\3\2\2\2>@\t\2\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3"+
		"\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7\2\2\3EF\b\2\1\2F\3\3\2\2\2GH\5\6\4\2HI"+
		"\b\3\1\2IT\3\2\2\2JK\5\b\5\2KL\b\3\1\2LT\3\2\2\2MN\5\n\6\2NO\b\3\1\2O"+
		"T\3\2\2\2PQ\5\f\7\2QR\b\3\1\2RT\3\2\2\2SG\3\2\2\2SJ\3\2\2\2SM\3\2\2\2"+
		"SP\3\2\2\2T\5\3\2\2\2UV\7\b\2\2VW\7\25\2\2WX\5\22\n\2XZ\7\26\2\2Y[\7\3"+
		"\2\2ZY\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\]\b\4\1\2]\7\3\2\2\2^_\7\7\2\2_b"+
		"\7\30\2\2`a\7\21\2\2ac\5\22\n\2b`\3\2\2\2bc\3\2\2\2ce\3\2\2\2df\7\3\2"+
		"\2ed\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\b\5\1\2h\t\3\2\2\2ij\7\30\2\2jk\7\21"+
		"\2\2km\5\22\n\2ln\7\3\2\2ml\3\2\2\2mn\3\2\2\2no\3\2\2\2op\b\6\1\2p\13"+
		"\3\2\2\2qx\7\30\2\2rs\7\25\2\2st\5 \21\2tu\7\26\2\2uv\b\7\1\2vy\3\2\2"+
		"\2wy\b\7\1\2xr\3\2\2\2xw\3\2\2\2y{\3\2\2\2z|\7\3\2\2{z\3\2\2\2{|\3\2\2"+
		"\2|}\3\2\2\2}~\b\7\1\2~\r\3\2\2\2\177\u0080\7\5\2\2\u0080\u0091\7\30\2"+
		"\2\u0081\u0085\5\20\t\2\u0082\u0084\7\4\2\2\u0083\u0082\3\2\2\2\u0084"+
		"\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2"+
		"\2\2\u0087\u0085\3\2\2\2\u0088\u0089\b\b\1\2\u0089\u0092\3\2\2\2\u008a"+
		"\u008c\7\4\2\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090"+
		"\u0092\b\b\1\2\u0091\u0081\3\2\2\2\u0091\u008d\3\2\2\2\u0092\u009d\3\2"+
		"\2\2\u0093\u0094\5\4\3\2\u0094\u0098\b\b\1\2\u0095\u0097\7\4\2\2\u0096"+
		"\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2"+
		"\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u0093\3\2\2\2\u009c"+
		"\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a0\3\2"+
		"\2\2\u009f\u009d\3\2\2\2\u00a0\u00a4\7\6\2\2\u00a1\u00a3\t\2\2\2\u00a2"+
		"\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\b\b\1\2\u00a8"+
		"\17\3\2\2\2\u00a9\u00b4\7\22\2\2\u00aa\u00ab\7\30\2\2\u00ab\u00b1\b\t"+
		"\1\2\u00ac\u00ad\7\24\2\2\u00ad\u00ae\7\30\2\2\u00ae\u00b0\b\t\1\2\u00af"+
		"\u00ac\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00aa\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\7\23\2\2\u00b7\21\3\2\2"+
		"\2\u00b8\u00b9\5\24\13\2\u00b9\u00ba\b\n\1\2\u00ba\u00ca\3\2\2\2\u00bb"+
		"\u00bc\5\26\f\2\u00bc\u00bd\b\n\1\2\u00bd\u00ca\3\2\2\2\u00be\u00bf\5"+
		"\30\r\2\u00bf\u00c6\b\n\1\2\u00c0\u00c1\7\20\2\2\u00c1\u00c2\5\30\r\2"+
		"\u00c2\u00c3\b\n\1\2\u00c3\u00c5\3\2\2\2\u00c4\u00c0\3\2\2\2\u00c5\u00c8"+
		"\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00b8\3\2\2\2\u00c9\u00bb\3\2\2\2\u00c9\u00be\3\2"+
		"\2\2\u00ca\23\3\2\2\2\u00cb\u00cc\7\t\2\2\u00cc\u00cd\5\36\20\2\u00cd"+
		"\u00ce\b\13\1\2\u00ce\25\3\2\2\2\u00cf\u00d0\7\n\2\2\u00d0\u00d1\5\30"+
		"\r\2\u00d1\u00d2\5\30\r\2\u00d2\u00d3\b\f\1\2\u00d3\27\3\2\2\2\u00d4\u00d5"+
		"\5\32\16\2\u00d5\u00e0\b\r\1\2\u00d6\u00d7\7\f\2\2\u00d7\u00d8\5\32\16"+
		"\2\u00d8\u00d9\b\r\1\2\u00d9\u00df\3\2\2\2\u00da\u00db\7\17\2\2\u00db"+
		"\u00dc\5\32\16\2\u00dc\u00dd\b\r\1\2\u00dd\u00df\3\2\2\2\u00de\u00d6\3"+
		"\2\2\2\u00de\u00da\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\31\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4\5\34\17"+
		"\2\u00e4\u00f3\b\16\1\2\u00e5\u00e6\7\16\2\2\u00e6\u00e7\5\34\17\2\u00e7"+
		"\u00e8\b\16\1\2\u00e8\u00f2\3\2\2\2\u00e9\u00ea\7\13\2\2\u00ea\u00eb\5"+
		"\34\17\2\u00eb\u00ec\b\16\1\2\u00ec\u00f2\3\2\2\2\u00ed\u00ee\7\37\2\2"+
		"\u00ee\u00ef\5\34\17\2\u00ef\u00f0\b\16\1\2\u00f0\u00f2\3\2\2\2\u00f1"+
		"\u00e5\3\2\2\2\u00f1\u00e9\3\2\2\2\u00f1\u00ed\3\2\2\2\u00f2\u00f5\3\2"+
		"\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\33\3\2\2\2\u00f5\u00f3"+
		"\3\2\2\2\u00f6\u00f7\7\31\2\2\u00f7\u0102\b\17\1\2\u00f8\u00f9\7\32\2"+
		"\2\u00f9\u0102\b\17\1\2\u00fa\u00fb\7\30\2\2\u00fb\u0102\b\17\1\2\u00fc"+
		"\u00fd\7\25\2\2\u00fd\u00fe\5\22\n\2\u00fe\u00ff\7\26\2\2\u00ff\u0100"+
		"\b\17\1\2\u0100\u0102\3\2\2\2\u0101\u00f6\3\2\2\2\u0101\u00f8\3\2\2\2"+
		"\u0101\u00fa\3\2\2\2\u0101\u00fc\3\2\2\2\u0102\35\3\2\2\2\u0103\u0104"+
		"\5\30\r\2\u0104\u010a\b\20\1\2\u0105\u0106\5\30\r\2\u0106\u0107\b\20\1"+
		"\2\u0107\u0109\3\2\2\2\u0108\u0105\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108"+
		"\3\2\2\2\u010a\u010b\3\2\2\2\u010b\37\3\2\2\2\u010c\u010a\3\2\2\2\u010d"+
		"\u010e\5\22\n\2\u010e\u0115\b\21\1\2\u010f\u0110\7\24\2\2\u0110\u0111"+
		"\5\22\n\2\u0111\u0112\b\21\1\2\u0112\u0114\3\2\2\2\u0113\u010f\3\2\2\2"+
		"\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116!\3"+
		"\2\2\2\u0117\u0115\3\2\2\2 %,\61\67;ASZbemx{\u0085\u008d\u0091\u0098\u009d"+
		"\u00a4\u00b1\u00b4\u00c6\u00c9\u00de\u00e0\u00f1\u00f3\u0101\u010a\u0115";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}