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
	 * Visit a parse tree produced by {@link SimpleParser#repiteStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepiteStmt(SimpleParser.RepiteStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#ejecutaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEjecutaStmt(SimpleParser.EjecutaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#esperaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEsperaStmt(SimpleParser.EsperaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#centroStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCentroStmt(SimpleParser.CentroStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#ponColorLapizStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonColorLapizStmt(SimpleParser.PonColorLapizStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#subeLapizStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubeLapizStmt(SimpleParser.SubeLapizStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#bajaLapizStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBajaLapizStmt(SimpleParser.BajaLapizStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#ponYStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonYStmt(SimpleParser.PonYStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#ponXStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonXStmt(SimpleParser.PonXStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#rumboStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRumboStmt(SimpleParser.RumboStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#ponRumboStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonRumboStmt(SimpleParser.PonRumboStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#ponPosStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonPosStmt(SimpleParser.PonPosStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#ocultaTortugaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOcultaTortugaStmt(SimpleParser.OcultaTortugaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#giraIzquierdaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGiraIzquierdaStmt(SimpleParser.GiraIzquierdaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#giraDerechaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGiraDerechaStmt(SimpleParser.GiraDerechaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#retrocedeStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetrocedeStmt(SimpleParser.RetrocedeStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#incStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncStmt(SimpleParser.IncStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#avanzaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAvanzaStmt(SimpleParser.AvanzaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#inicStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicStmt(SimpleParser.InicStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#hazStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHazStmt(SimpleParser.HazStmtContext ctx);
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
	 * Visit a parse tree produced by {@link SimpleParser#siStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSiStmt(SimpleParser.SiStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#hazHastaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHazHastaStmt(SimpleParser.HazHastaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#hastaStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHastaStmt(SimpleParser.HastaStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#hazMientrasStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHazMientrasStmt(SimpleParser.HazMientrasStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#mientrasStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMientrasStmt(SimpleParser.MientrasStmtContext ctx);
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
	 * Visit a parse tree produced by {@link SimpleParser#divisionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivisionExpr(SimpleParser.DivisionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#productoExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProductoExpr(SimpleParser.ProductoExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleParser#relExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpr(SimpleParser.RelExprContext ctx);
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
	 * Visit a parse tree produced by {@link SimpleParser#sumaExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumaExpr(SimpleParser.SumaExprContext ctx);
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