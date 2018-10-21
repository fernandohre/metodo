package com.github.saulocalixto.calculadoraantlr.antlr;// Generated from calculadora.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link calculadoraParser}.
 */
public interface calculadoraListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link calculadoraParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(calculadoraParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculadoraParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(calculadoraParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code par}
	 * labeled alternative in {@link calculadoraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPar(calculadoraParser.ParContext ctx);
	/**
	 * Exit a parse tree produced by the {@code par}
	 * labeled alternative in {@link calculadoraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPar(calculadoraParser.ParContext ctx);
	/**
	 * Enter a parse tree produced by the {@code num}
	 * labeled alternative in {@link calculadoraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNum(calculadoraParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code num}
	 * labeled alternative in {@link calculadoraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNum(calculadoraParser.NumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpBin}
	 * labeled alternative in {@link calculadoraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOpBin(calculadoraParser.OpBinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpBin}
	 * labeled alternative in {@link calculadoraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOpBin(calculadoraParser.OpBinContext ctx);
}