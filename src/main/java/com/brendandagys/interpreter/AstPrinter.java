package com.brendandagys.interpreter;

class AstPrinter implements Expr.Visitor<String> {
  String print(Expr expr) {
    return expr.accept(this);
  }

  @Override
  public String visitBinaryExpr(Expr.Binary expr) {
    return parenthesize(expr.operator.lexeme, expr.left, expr.right);
  }

  @Override
  public String visitGroupingExpr(Expr.Grouping expr) {
    return parenthesize("group", expr.expression);
  }

  @Override
  public String visitVariableExpr(Expr.Variable expr) {
    return expr.name.lexeme;
  }

  @Override
  public String visitCallExpr(Expr.Call expr) {
    return expr.callee.toString();
  }

  @Override
  public String visitSuperExpr(Expr.Super expr) {
    return "Keyword: " + expr.keyword.lexeme + ", method: " + expr.method.lexeme;
  }

  @Override
  public String visitThisExpr(Expr.This expr) {
    return expr.keyword.lexeme;
  }

  @Override
  public String visitGetExpr(Expr.Get expr) {
    return expr.name.lexeme;
  }

  @Override
  public String visitSetExpr(Expr.Set expr) {
    return expr.object.toString() + expr.name + expr.value.toString();
  }

  @Override
  public String visitAssignExpr(Expr.Assign expr) {
    return expr.name.lexeme + " = " + expr.value;
  }

  @Override
  public String visitLogicalExpr(Expr.Logical expr) {
    return expr.left + " " + expr.operator + " " + expr.right;
  }

  @Override
  public String visitLiteralExpr(Expr.Literal expr) {
    if (expr.value == null)
      return "nil";
    return expr.value.toString();
  }

  @Override
  public String visitUnaryExpr(Expr.Unary expr) {
    return parenthesize(expr.operator.lexeme, expr.right);
  }

  private String parenthesize(String name, Expr... exprs) {
    StringBuilder builder = new StringBuilder();

    builder.append("(").append(name);
    for (Expr expr : exprs) {
      builder.append(" ");
      builder.append(expr.accept(this));
    }
    builder.append(")");

    return builder.toString();
  }

  public static void main(String[] args) {
    Expr expression = new Expr.Binary(
        new Expr.Unary(
            new Token(TokenType.MINUS, "-", null, 1),
            new Expr.Literal(123)),
        new Token(TokenType.STAR, "*", null, 1),
        new Expr.Grouping(
            new Expr.Literal(45.67)));

    System.out.println(new AstPrinter().print(expression));
  }
}
