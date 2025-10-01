// Generated from Simple.g4 by ANTLR 4.4
package com.tallerantlr.simple.interprete;

    import java.util.*;
    import com.tallerantlr.simple.interprete.ast.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleParser}.
 */
public interface SimpleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull SimpleParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull SimpleParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull SimpleParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull SimpleParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull SimpleParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull SimpleParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#optParams}.
	 * @param ctx the parse tree
	 */
	void enterOptParams(@NotNull SimpleParser.OptParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#optParams}.
	 * @param ctx the parse tree
	 */
	void exitOptParams(@NotNull SimpleParser.OptParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(@NotNull SimpleParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(@NotNull SimpleParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void enterVarAssign(@NotNull SimpleParser.VarAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void exitVarAssign(@NotNull SimpleParser.VarAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#procCallStmt}.
	 * @param ctx the parse tree
	 */
	void enterProcCallStmt(@NotNull SimpleParser.ProcCallStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#procCallStmt}.
	 * @param ctx the parse tree
	 */
	void exitProcCallStmt(@NotNull SimpleParser.ProcCallStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#procedureDef}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDef(@NotNull SimpleParser.ProcedureDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#procedureDef}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDef(@NotNull SimpleParser.ProcedureDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(@NotNull SimpleParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(@NotNull SimpleParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull SimpleParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull SimpleParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull SimpleParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull SimpleParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(@NotNull SimpleParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(@NotNull SimpleParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#printlnStmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintlnStmt(@NotNull SimpleParser.PrintlnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#printlnStmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintlnStmt(@NotNull SimpleParser.PrintlnStmtContext ctx);
}