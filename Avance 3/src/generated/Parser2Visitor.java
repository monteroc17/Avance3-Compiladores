// Generated from E:/TEC/Semestre 1 2018/Compiladores/Avance3-Compiladores/Avance 3\Parser2.g4 by ANTLR 4.7
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Parser2}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Parser2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code progAST}
	 * labeled alternative in {@link Parser2#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgAST(Parser2.ProgASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stLETAST}
	 * labeled alternative in {@link Parser2#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStLETAST(Parser2.StLETASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stRETURNAST}
	 * labeled alternative in {@link Parser2#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStRETURNAST(Parser2.StRETURNASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stExprAST}
	 * labeled alternative in {@link Parser2#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStExprAST(Parser2.StExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lsAsignAST}
	 * labeled alternative in {@link Parser2#letStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsAsignAST(Parser2.LsAsignASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rsExprAST}
	 * labeled alternative in {@link Parser2#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsExprAST(Parser2.RsExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code esExprAST}
	 * labeled alternative in {@link Parser2#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEsExprAST(Parser2.EsExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAddAST}
	 * labeled alternative in {@link Parser2#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddAST(Parser2.ExprAddASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compAST}
	 * labeled alternative in {@link Parser2#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompAST(Parser2.CompASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExprAST}
	 * labeled alternative in {@link Parser2#additionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExprAST(Parser2.AddExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addFactorAST}
	 * labeled alternative in {@link Parser2#additionFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddFactorAST(Parser2.AddFactorASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExprAST}
	 * labeled alternative in {@link Parser2#multiplicationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExprAST(Parser2.MultExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multFactorAST}
	 * labeled alternative in {@link Parser2#multiplicationFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultFactorAST(Parser2.MultFactorASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elemExprPEElemAccessAST}
	 * labeled alternative in {@link Parser2#elementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemExprPEElemAccessAST(Parser2.ElemExprPEElemAccessASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elemExprPECallExpAST}
	 * labeled alternative in {@link Parser2#elementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemExprPECallExpAST(Parser2.ElemExprPECallExpASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elemExprPEEmptyAST}
	 * labeled alternative in {@link Parser2#elementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemExprPEEmptyAST(Parser2.ElemExprPEEmptyASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elemAccessAST}
	 * labeled alternative in {@link Parser2#elementAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemAccessAST(Parser2.ElemAccessASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExprAST}
	 * labeled alternative in {@link Parser2#callExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExprAST(Parser2.CallExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprINTAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprINTAST(Parser2.PExprINTASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprSTRINGAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprSTRINGAST(Parser2.PExprSTRINGASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprIDAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprIDAST(Parser2.PExprIDASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprTRUEAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprTRUEAST(Parser2.PExprTRUEASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprFALSEAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprFALSEAST(Parser2.PExprFALSEASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprGroupAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprGroupAST(Parser2.PExprGroupASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprArrayLitAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprArrayLitAST(Parser2.PExprArrayLitASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprArrayFuncAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprArrayFuncAST(Parser2.PExprArrayFuncASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprFuncLitAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprFuncLitAST(Parser2.PExprFuncLitASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprHashLitAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprHashLitAST(Parser2.PExprHashLitASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprPrintExprAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprPrintExprAST(Parser2.PExprPrintExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pExprIfExprAST}
	 * labeled alternative in {@link Parser2#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExprIfExprAST(Parser2.PExprIfExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code afLENAST}
	 * labeled alternative in {@link Parser2#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAfLENAST(Parser2.AfLENASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code afFIRSTAST}
	 * labeled alternative in {@link Parser2#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAfFIRSTAST(Parser2.AfFIRSTASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code afLASTAST}
	 * labeled alternative in {@link Parser2#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAfLASTAST(Parser2.AfLASTASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code afRESTAST}
	 * labeled alternative in {@link Parser2#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAfRESTAST(Parser2.AfRESTASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code afPUSHAST}
	 * labeled alternative in {@link Parser2#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAfPUSHAST(Parser2.AfPUSHASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLitAST}
	 * labeled alternative in {@link Parser2#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLitAST(Parser2.ArrayLitASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcLitAST}
	 * labeled alternative in {@link Parser2#functionLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncLitAST(Parser2.FuncLitASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcParamAST}
	 * labeled alternative in {@link Parser2#functionParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncParamAST(Parser2.FuncParamASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreIdentsAST}
	 * labeled alternative in {@link Parser2#moreIdentifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreIdentsAST(Parser2.MoreIdentsASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hashLitAST}
	 * labeled alternative in {@link Parser2#hashLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHashLitAST(Parser2.HashLitASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hashContentAST}
	 * labeled alternative in {@link Parser2#hashContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHashContentAST(Parser2.HashContentASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreHashContentAST}
	 * labeled alternative in {@link Parser2#moreHashContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreHashContentAST(Parser2.MoreHashContentASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprListMoreExprAST}
	 * labeled alternative in {@link Parser2#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListMoreExprAST(Parser2.ExprListMoreExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprListEmptyAST}
	 * labeled alternative in {@link Parser2#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListEmptyAST(Parser2.ExprListEmptyASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreExprAST}
	 * labeled alternative in {@link Parser2#moreExpressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreExprAST(Parser2.MoreExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printExprAST}
	 * labeled alternative in {@link Parser2#printExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExprAST(Parser2.PrintExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExprAST}
	 * labeled alternative in {@link Parser2#ifExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExprAST(Parser2.IfExprASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStatementAST}
	 * labeled alternative in {@link Parser2#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatementAST(Parser2.BlockStatementASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opSumAST}
	 * labeled alternative in {@link Parser2#addOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpSumAST(Parser2.OpSumASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opSubAST}
	 * labeled alternative in {@link Parser2#addOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpSubAST(Parser2.OpSubASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opMulAST}
	 * labeled alternative in {@link Parser2#mulOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpMulAST(Parser2.OpMulASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opDivAST}
	 * labeled alternative in {@link Parser2#mulOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpDivAST(Parser2.OpDivASTContext ctx);
	/**
	 * Visit a parse tree produced by {@link Parser2#compOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompOperator(Parser2.CompOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idAST}
	 * labeled alternative in {@link Parser2#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdAST(Parser2.IdASTContext ctx);
}