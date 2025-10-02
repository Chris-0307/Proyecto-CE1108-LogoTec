// Generated from com\tallerantlr\simple\interprete\Simple.g4 by ANTLR 4.9.2
package com.tallerantlr.simple.interprete;

    import java.util.*;
    import com.tallerantlr.simple.interprete.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleParser}.
 */
public interface SimpleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SimpleParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SimpleParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SimpleParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SimpleParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#printlnStmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintlnStmt(SimpleParser.PrintlnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#printlnStmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintlnStmt(SimpleParser.PrintlnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(SimpleParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(SimpleParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void enterVarAssign(SimpleParser.VarAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void exitVarAssign(SimpleParser.VarAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#procCallStmt}.
	 * @param ctx the parse tree
	 */
	void enterProcCallStmt(SimpleParser.ProcCallStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#procCallStmt}.
	 * @param ctx the parse tree
	 */
	void exitProcCallStmt(SimpleParser.ProcCallStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#procedureDef}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDef(SimpleParser.ProcedureDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#procedureDef}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDef(SimpleParser.ProcedureDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#optParams}.
	 * @param ctx the parse tree
	 */
	void enterOptParams(SimpleParser.OptParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#optParams}.
	 * @param ctx the parse tree
	 */
	void exitOptParams(SimpleParser.OptParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SimpleParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SimpleParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#sumaExpr}.
	 * @param ctx the parse tree
	 */
	void enterSumaExpr(SimpleParser.SumaExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#sumaExpr}.
	 * @param ctx the parse tree
	 */
	void exitSumaExpr(SimpleParser.SumaExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#productoExpr}.
	 * @param ctx the parse tree
	 */
	void enterProductoExpr(SimpleParser.ProductoExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#productoExpr}.
	 * @param ctx the parse tree
	 */
	void exitProductoExpr(SimpleParser.ProductoExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#divisionExpr}.
	 * @param ctx the parse tree
	 */
	void enterDivisionExpr(SimpleParser.DivisionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#divisionExpr}.
	 * @param ctx the parse tree
	 */
	void exitDivisionExpr(SimpleParser.DivisionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(SimpleParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(SimpleParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(SimpleParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(SimpleParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(SimpleParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(SimpleParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(SimpleParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(SimpleParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(SimpleParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(SimpleParser.ArgListContext ctx);
}