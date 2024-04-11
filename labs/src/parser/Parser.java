/* Generated By:JavaCC: Do not edit this line. Parser.java */
package parser;
import ast.*;
import java.util.List;
import java.util.ArrayList;

public class Parser implements ParserConstants {

  final public Exp Start() throws ParseException {
  Exp e;
    e = decl();
    jj_consume_token(EL);
                    {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public Exp decl() throws ParseException {
  List<ASTVarDecl> varDecls = new ArrayList<>(); Exp e; ASTVarDecl varDecl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LET:
      jj_consume_token(LET);
      label_1:
      while (true) {
        varDecl = assignment();
                                    varDecls.add(varDecl);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case Id:
          ;
          break;
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
      }
      jj_consume_token(IN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EL:
        jj_consume_token(EL);
        break;
      default:
        jj_la1[1] = jj_gen;
        ;
      }
      e = decl();
                                                                                        {if (true) return new ASTLet(varDecls, e);}
      break;
    case Num:
    case LPAR:
    case FALSE:
    case TRUE:
    case Id:
    case NOT:
      e = BExp();
                 {if (true) return e;}
      break;
    case IF:
      e = IfElse();
                   {if (true) return e;}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ASTVarDecl assignment() throws ParseException {
  Token x; Exp e;
    x = jj_consume_token(Id);
    jj_consume_token(EQ);
    e = BExp();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EL:
      jj_consume_token(EL);
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
                                    {if (true) return new ASTVarDecl(x.image, e);}
    throw new Error("Missing return statement in function");
  }

  final public Exp IfElse() throws ParseException {
  Exp e1, e2, e3;
    jj_consume_token(IF);
    jj_consume_token(LPAR);
    e1 = BExp();
    jj_consume_token(RPAR);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EL:
      jj_consume_token(EL);
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    e2 = Expr();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EL:
      jj_consume_token(EL);
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(ELSE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EL:
      jj_consume_token(EL);
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    e3 = Expr();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EL:
      jj_consume_token(EL);
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
    jj_consume_token(END);
                                                                                                    {if (true) return new ASTIf(e1, e2, e3);}
    throw new Error("Missing return statement in function");
  }

  final public Exp BExp() throws ParseException {
  Exp e1, e2;
    e1 = Cmp();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AND:
    case OR:
    case NOT:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        jj_consume_token(AND);
        e2 = BExp();
                         e1 = new ASTAnd(e1,e2);
        break;
      case OR:
        jj_consume_token(OR);
        e2 = BExp();
                        e1 = new ASTOr(e1,e2);
        break;
      case NOT:
        jj_consume_token(NOT);
        e2 = BExp();
                         e1 = new ASTNot(e2);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
     {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public Exp Cmp() throws ParseException {
  Exp e1, e2;
    e1 = Expr();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LESS:
    case GREATER:
    case LESSEQ:
    case GREATEREQ:
    case EQUALS:
    case NOTEQUALS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LESS:
        jj_consume_token(LESS);
        e2 = Expr();
                          e1 = new ASTLess(e1,e2);
        break;
      case GREATER:
        jj_consume_token(GREATER);
        e2 = Expr();
                             e1 = new ASTGreater(e1,e2);
        break;
      case LESSEQ:
        jj_consume_token(LESSEQ);
        e2 = Expr();
                            e1 = new ASTLessEq(e1,e2);
        break;
      case GREATEREQ:
        jj_consume_token(GREATEREQ);
        e2 = Expr();
                               e1 = new ASTGreaterEq(e1,e2);
        break;
      case EQUALS:
        jj_consume_token(EQUALS);
        e2 = Expr();
                            e1 = new ASTEquals(e1,e2);
        break;
      case NOTEQUALS:
        jj_consume_token(NOTEQUALS);
        e2 = Expr();
                               e1 = new ASTNotEquals(e1,e2);
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
     {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public Exp Expr() throws ParseException {
  Exp e1, e2;
    e1 = Term();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        e2 = Expr();
                            e1 = new ASTAdd(e1,e2);
        break;
      case MINUS:
        jj_consume_token(MINUS);
        e2 = Expr();
                             e1 = new ASTSub(e1,e2);
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
       {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public Exp Term() throws ParseException {
  Exp e1, e2;
    e1 = Fact();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TIMES:
    case DIV:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
        jj_consume_token(TIMES);
        e2 = Term();
                             e1 = new ASTMult(e1,e2);
        break;
      case DIV:
        jj_consume_token(DIV);
        e2 = Term();
                           e1 = new ASTDiv(e1,e2);
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
       {if (true) return e1;}
    throw new Error("Missing return statement in function");
  }

  final public Exp Fact() throws ParseException {
  Token x; Exp e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Num:
      x = jj_consume_token(Num);
    {if (true) return new ASTInt(Integer.parseInt(x.image));}
      break;
    case TRUE:
      x = jj_consume_token(TRUE);
                   {if (true) return new ASTBool(true);}
      break;
    case FALSE:
      x = jj_consume_token(FALSE);
                    {if (true) return new ASTBool(false);}
      break;
    case LPAR:
      jj_consume_token(LPAR);
      e = decl();
      jj_consume_token(RPAR);
                                 {if (true) return e;}
      break;
    case NOT:
      jj_consume_token(NOT);
      e = Fact();
                         {if (true) return new ASTNot(e);}
      break;
    case Id:
      x = jj_consume_token(Id);
                 {if (true) return new ASTId(x.image);}
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[17];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x80000,0x800,0x48f210,0x800,0x800,0x800,0x800,0x800,0x700000,0x700000,0x37800000,0x37800000,0x60,0x60,0x180,0x180,0x483210,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[30];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 17; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 30; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
