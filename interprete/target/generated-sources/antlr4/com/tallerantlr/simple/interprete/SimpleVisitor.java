// Generated from com\tallerantlr\simple\interprete\Simple.g4 by ANTLR 4.9.2
package com.tallerantlr.simple.interprete;

    import java.util.*;
    import com.tallerantlr.simple.interprete.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimpleParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SimpleParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SimpleParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#printlnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintlnStmt(SimpleParser.PrintlnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(SimpleParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#varAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssign(SimpleParser.VarAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#procCallStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcCallStmt(SimpleParser.ProcCallStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#procedureDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureDef(SimpleParser.ProcedureDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#optParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptParams(SimpleParser.OptParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SimpleParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#sumaExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumaExpr(SimpleParser.SumaExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#addExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(SimpleParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#multExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(SimpleParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(SimpleParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(SimpleParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(SimpleParser.ArgListContext ctx);
}