package br.ufg.inf.dev.calculadoraantlr;

import br.ufg.inf.dev.calculadoraantlr.antlr.calculadoraBaseListener;
import br.ufg.inf.dev.calculadoraantlr.antlr.calculadoraParser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class MinhaCalculadora extends calculadoraBaseListener {

    ParseTreeProperty<Integer> values = new ParseTreeProperty();

    public void setValue(ParseTree node, int value) {
        values.put(node, value);
    }

    public int getValue(ParseTree node) {
        return values.get(node);
    }

    @Override
    public void exitPar(@NotNull calculadoraParser.ParContext ctx) {
        int valor = getValue(ctx.expr());
        setValue(ctx, valor);
    }

    @Override
    public void exitNum(@NotNull calculadoraParser.NumContext ctx) {
        int valor = Integer.parseInt(ctx.INT().getText());
        setValue(ctx, valor);
    }

    private int resposta;

    @Override
    public void exitProg(@NotNull calculadoraParser.ProgContext ctx) {
        resposta = getValue(ctx.expr());
    }

    @Override
    public void exitOpBin(@NotNull calculadoraParser.OpBinContext ctx) {
        int esq = getValue(ctx.expr(0));
        int dir = getValue(ctx.expr(1));
        final int valor;
        String texto = ctx.op.getText();
        if(texto.equals("*")) {
            valor = esq * dir;
        } else if(texto.equals("/")) {
            valor = esq / dir;
        } else if(texto.equals("+")) {
            valor = esq + dir;
        } else {
            valor = esq - dir;
        }

        setValue(ctx, valor);
    }

    public int resultado() {
        return resposta;
    }
}
