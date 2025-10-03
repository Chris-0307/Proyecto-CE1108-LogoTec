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
	 * Enter a parse tree produced by {@link SimpleParser#ejecutaStmt}.
	 * @param ctx the parse tree
	 */
	void enterEjecutaStmt(@NotNull SimpleParser.EjecutaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ejecutaStmt}.
	 * @param ctx the parse tree
	 */
	void exitEjecutaStmt(@NotNull SimpleParser.EjecutaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ponYStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonYStmt(@NotNull SimpleParser.PonYStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponYStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonYStmt(@NotNull SimpleParser.PonYStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#repiteStmt}.
	 * @param ctx the parse tree
	 */
	void enterRepiteStmt(@NotNull SimpleParser.RepiteStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#repiteStmt}.
	 * @param ctx the parse tree
	 */
	void exitRepiteStmt(@NotNull SimpleParser.RepiteStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ponPosStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonPosStmt(@NotNull SimpleParser.PonPosStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponPosStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonPosStmt(@NotNull SimpleParser.PonPosStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#centroStmt}.
	 * @param ctx the parse tree
	 */
	void enterCentroStmt(@NotNull SimpleParser.CentroStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#centroStmt}.
	 * @param ctx the parse tree
	 */
	void exitCentroStmt(@NotNull SimpleParser.CentroStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#giraDerechaStmt}.
	 * @param ctx the parse tree
	 */
	void enterGiraDerechaStmt(@NotNull SimpleParser.GiraDerechaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#giraDerechaStmt}.
	 * @param ctx the parse tree
	 */
	void exitGiraDerechaStmt(@NotNull SimpleParser.GiraDerechaStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#ocultaTortugaStmt}.
	 * @param ctx the parse tree
	 */
	void enterOcultaTortugaStmt(@NotNull SimpleParser.OcultaTortugaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ocultaTortugaStmt}.
	 * @param ctx the parse tree
	 */
	void exitOcultaTortugaStmt(@NotNull SimpleParser.OcultaTortugaStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#rumboStmt}.
	 * @param ctx the parse tree
	 */
	void enterRumboStmt(@NotNull SimpleParser.RumboStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#rumboStmt}.
	 * @param ctx the parse tree
	 */
	void exitRumboStmt(@NotNull SimpleParser.RumboStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#ponXStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonXStmt(@NotNull SimpleParser.PonXStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponXStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonXStmt(@NotNull SimpleParser.PonXStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#retrocedeStmt}.
	 * @param ctx the parse tree
	 */
	void enterRetrocedeStmt(@NotNull SimpleParser.RetrocedeStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#retrocedeStmt}.
	 * @param ctx the parse tree
	 */
	void exitRetrocedeStmt(@NotNull SimpleParser.RetrocedeStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#hazStmt}.
	 * @param ctx the parse tree
	 */
	void enterHazStmt(@NotNull SimpleParser.HazStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#hazStmt}.
	 * @param ctx the parse tree
	 */
	void exitHazStmt(@NotNull SimpleParser.HazStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ponColorLapizStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonColorLapizStmt(@NotNull SimpleParser.PonColorLapizStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponColorLapizStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonColorLapizStmt(@NotNull SimpleParser.PonColorLapizStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#esperaStmt}.
	 * @param ctx the parse tree
	 */
	void enterEsperaStmt(@NotNull SimpleParser.EsperaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#esperaStmt}.
	 * @param ctx the parse tree
	 */
	void exitEsperaStmt(@NotNull SimpleParser.EsperaStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#giraIzquierdaStmt}.
	 * @param ctx the parse tree
	 */
	void enterGiraIzquierdaStmt(@NotNull SimpleParser.GiraIzquierdaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#giraIzquierdaStmt}.
	 * @param ctx the parse tree
	 */
	void exitGiraIzquierdaStmt(@NotNull SimpleParser.GiraIzquierdaStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#subeLapizStmt}.
	 * @param ctx the parse tree
	 */
	void enterSubeLapizStmt(@NotNull SimpleParser.SubeLapizStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#subeLapizStmt}.
	 * @param ctx the parse tree
	 */
	void exitSubeLapizStmt(@NotNull SimpleParser.SubeLapizStmtContext ctx);
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
	 * Enter a parse tree produced by {@link SimpleParser#inicStmt}.
	 * @param ctx the parse tree
	 */
	void enterInicStmt(@NotNull SimpleParser.InicStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#inicStmt}.
	 * @param ctx the parse tree
	 */
	void exitInicStmt(@NotNull SimpleParser.InicStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#bajaLapizStmt}.
	 * @param ctx the parse tree
	 */
	void enterBajaLapizStmt(@NotNull SimpleParser.BajaLapizStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#bajaLapizStmt}.
	 * @param ctx the parse tree
	 */
	void exitBajaLapizStmt(@NotNull SimpleParser.BajaLapizStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#ponRumboStmt}.
	 * @param ctx the parse tree
	 */
	void enterPonRumboStmt(@NotNull SimpleParser.PonRumboStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#ponRumboStmt}.
	 * @param ctx the parse tree
	 */
	void exitPonRumboStmt(@NotNull SimpleParser.PonRumboStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#incStmt}.
	 * @param ctx the parse tree
	 */
	void enterIncStmt(@NotNull SimpleParser.IncStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#incStmt}.
	 * @param ctx the parse tree
	 */
	void exitIncStmt(@NotNull SimpleParser.IncStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleParser#avanzaStmt}.
	 * @param ctx the parse tree
	 */
	void enterAvanzaStmt(@NotNull SimpleParser.AvanzaStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleParser#avanzaStmt}.
	 * @param ctx the parse tree
	 */
	void exitAvanzaStmt(@NotNull SimpleParser.AvanzaStmtContext ctx);
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