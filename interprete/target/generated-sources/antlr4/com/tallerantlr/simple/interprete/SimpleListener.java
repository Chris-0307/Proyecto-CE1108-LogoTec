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
	 * Enter a parse tree produced by {@link SimpleParser#repiteStmt}.
	 * @param ctx the parse tree
	 */
	void enterRepiteStmt(SimpleParser.RepiteStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#repiteStmt}.
	 * @param ctx the parse tree
	 */
	void exitRepiteStmt(SimpleParser.RepiteStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ejecutaStmt}.
	 * @param ctx the parse tree
	 */
	void enterEjecutaStmt(SimpleParser.EjecutaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ejecutaStmt}.
	 * @param ctx the parse tree
	 */
	void exitEjecutaStmt(SimpleParser.EjecutaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#esperaStmt}.
	 * @param ctx the parse tree
	 */
	void enterEsperaStmt(SimpleParser.EsperaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#esperaStmt}.
	 * @param ctx the parse tree
	 */
	void exitEsperaStmt(SimpleParser.EsperaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#centroStmt}.
	 * @param ctx the parse tree
	 */
	void enterCentroStmt(SimpleParser.CentroStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#centroStmt}.
	 * @param ctx the parse tree
	 */
	void exitCentroStmt(SimpleParser.CentroStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ponColorLapizStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonColorLapizStmt(SimpleParser.PonColorLapizStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponColorLapizStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonColorLapizStmt(SimpleParser.PonColorLapizStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#subeLapizStmt}.
	 * @param ctx the parse tree
	 */
	void enterSubeLapizStmt(SimpleParser.SubeLapizStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#subeLapizStmt}.
	 * @param ctx the parse tree
	 */
	void exitSubeLapizStmt(SimpleParser.SubeLapizStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#bajaLapizStmt}.
	 * @param ctx the parse tree
	 */
	void enterBajaLapizStmt(SimpleParser.BajaLapizStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#bajaLapizStmt}.
	 * @param ctx the parse tree
	 */
	void exitBajaLapizStmt(SimpleParser.BajaLapizStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ponYStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonYStmt(SimpleParser.PonYStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponYStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonYStmt(SimpleParser.PonYStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ponXStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonXStmt(SimpleParser.PonXStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponXStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonXStmt(SimpleParser.PonXStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#rumboStmt}.
	 * @param ctx the parse tree
	 */
	void enterRumboStmt(SimpleParser.RumboStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#rumboStmt}.
	 * @param ctx the parse tree
	 */
	void exitRumboStmt(SimpleParser.RumboStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ponRumboStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonRumboStmt(SimpleParser.PonRumboStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponRumboStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonRumboStmt(SimpleParser.PonRumboStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ponPosStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonPosStmt(SimpleParser.PonPosStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponPosStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonPosStmt(SimpleParser.PonPosStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ocultaTortugaStmt}.
	 * @param ctx the parse tree
	 */
	void enterOcultaTortugaStmt(SimpleParser.OcultaTortugaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ocultaTortugaStmt}.
	 * @param ctx the parse tree
	 */
	void exitOcultaTortugaStmt(SimpleParser.OcultaTortugaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#giraIzquierdaStmt}.
	 * @param ctx the parse tree
	 */
	void enterGiraIzquierdaStmt(SimpleParser.GiraIzquierdaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#giraIzquierdaStmt}.
	 * @param ctx the parse tree
	 */
	void exitGiraIzquierdaStmt(SimpleParser.GiraIzquierdaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#giraDerechaStmt}.
	 * @param ctx the parse tree
	 */
	void enterGiraDerechaStmt(SimpleParser.GiraDerechaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#giraDerechaStmt}.
	 * @param ctx the parse tree
	 */
	void exitGiraDerechaStmt(SimpleParser.GiraDerechaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#retrocedeStmt}.
	 * @param ctx the parse tree
	 */
	void enterRetrocedeStmt(SimpleParser.RetrocedeStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#retrocedeStmt}.
	 * @param ctx the parse tree
	 */
	void exitRetrocedeStmt(SimpleParser.RetrocedeStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#incStmt}.
	 * @param ctx the parse tree
	 */
	void enterIncStmt(SimpleParser.IncStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#incStmt}.
	 * @param ctx the parse tree
	 */
	void exitIncStmt(SimpleParser.IncStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#avanzaStmt}.
	 * @param ctx the parse tree
	 */
	void enterAvanzaStmt(SimpleParser.AvanzaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#avanzaStmt}.
	 * @param ctx the parse tree
	 */
	void exitAvanzaStmt(SimpleParser.AvanzaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#inicStmt}.
	 * @param ctx the parse tree
	 */
	void enterInicStmt(SimpleParser.InicStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#inicStmt}.
	 * @param ctx the parse tree
	 */
	void exitInicStmt(SimpleParser.InicStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#hazStmt}.
	 * @param ctx the parse tree
	 */
	void enterHazStmt(SimpleParser.HazStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#hazStmt}.
	 * @param ctx the parse tree
	 */
	void exitHazStmt(SimpleParser.HazStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#siStmt}.
	 * @param ctx the parse tree
	 */
	void enterSiStmt(SimpleParser.SiStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#siStmt}.
	 * @param ctx the parse tree
	 */
	void exitSiStmt(SimpleParser.SiStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#hazHastaStmt}.
	 * @param ctx the parse tree
	 */
	void enterHazHastaStmt(SimpleParser.HazHastaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#hazHastaStmt}.
	 * @param ctx the parse tree
	 */
	void exitHazHastaStmt(SimpleParser.HazHastaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#hastaStmt}.
	 * @param ctx the parse tree
	 */
	void enterHastaStmt(SimpleParser.HastaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#hastaStmt}.
	 * @param ctx the parse tree
	 */
	void exitHastaStmt(SimpleParser.HastaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#hazMientrasStmt}.
	 * @param ctx the parse tree
	 */
	void enterHazMientrasStmt(SimpleParser.HazMientrasStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#hazMientrasStmt}.
	 * @param ctx the parse tree
	 */
	void exitHazMientrasStmt(SimpleParser.HazMientrasStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#mientrasStmt}.
	 * @param ctx the parse tree
	 */
	void enterMientrasStmt(SimpleParser.MientrasStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#mientrasStmt}.
	 * @param ctx the parse tree
	 */
	void exitMientrasStmt(SimpleParser.MientrasStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(SimpleParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(SimpleParser.RelExprContext ctx);
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