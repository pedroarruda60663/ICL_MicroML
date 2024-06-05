/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
package parser;
import ast.*;
import ast.bools.*;
import ast.declarations.*;
import ast.nums.*;
import ast.references.*;
import ast.functions.*;
import ast.arrays.*;
import symbols.*;
import types.*;
import java.util.List;
import java.util.ArrayList;

public class Parser implements ParserConstants {

  final public Exp Start() throws ParseException {Exp e;
    e = SeqExp();
    jj_consume_token(EL);
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
}

  final public Exp SeqExp() throws ParseException {Exp e1, e2;
    e1 = Decl();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case SEQ:{
      jj_consume_token(SEQ);
      e2 = SeqExp();
e1 = new ASTSeq(e1, e2);
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      ;
    }
{if ("" != null) return e1;}
    throw new Error("Missing return statement in function");
}

  final public Exp Decl() throws ParseException {List<ASTVarDecl> varDecls = new ArrayList<>(); Exp e; ASTVarDecl varDecl;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LET:{
      jj_consume_token(LET);
      label_1:
      while (true) {
        varDecl = LetAssignment();
varDecls.add(varDecl);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case Id:{
          ;
          break;
          }
        default:
          jj_la1[1] = jj_gen;
          break label_1;
        }
      }
      jj_consume_token(IN);
      e = SeqExp();
{if ("" != null) return new ASTLet(varDecls, e);}
      break;
      }
    case DOUBLE:
    case Num:
    case MINUS:
    case LPAR:
    case FALSE:
    case TRUE:
    case NEW:
    case DEREF:
    case FUN:
    case ARRAY:
    case Id:
    case NOT:{
      e = Assignment();
{if ("" != null) return e;}
      break;
      }
    case WHILE:{
      e = WhileDo();
{if ("" != null) return e;}
      break;
      }
    case IF:{
      e = IfElse();
{if ("" != null) return e;}
      break;
      }
    case PRINT:{
      e = Print();
{if ("" != null) return e;}
      break;
      }
    case GARRAY:{
      e = ArrayAccess();
{if ("" != null) return e;}
      break;
      }
    case SARRAY:{
      e = ArrayAssign();
{if ("" != null) return e;}
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Exp ArrayAccess() throws ParseException {Exp e1, e2;
    jj_consume_token(GARRAY);
    e1 = BExp();
    jj_consume_token(SLPAR);
    e2 = BExp();
    jj_consume_token(SRPAR);
{if ("" != null) return new ASTArrayAccess(e1, e2);}
    throw new Error("Missing return statement in function");
}

  final public Exp ArrayAssign() throws ParseException {Exp e1, e2, e3;
    jj_consume_token(SARRAY);
    e1 = BExp();
    jj_consume_token(SLPAR);
    e2 = BExp();
    jj_consume_token(SRPAR);
    jj_consume_token(EQ);
    e3 = BExp();
{if ("" != null) return new ASTArrayAssign(e1, e2, e3);}
    throw new Error("Missing return statement in function");
}

  final public Exp ArrayDec() throws ParseException {Token type; Exp size;
    jj_consume_token(ARRAY);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:{
      type = jj_consume_token(INT);
      break;
      }
    case BOOL:{
      type = jj_consume_token(BOOL);
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    size = Fact();
{if ("" != null) return new ASTNewArray(type.image, size);}
    throw new Error("Missing return statement in function");
}

  final public Exp FunDef() throws ParseException {List<Pair<String, Type>> params;
  Exp body;
    jj_consume_token(FUN);
    jj_consume_token(LPAR);
    params = ParamList();
    jj_consume_token(RPAR);
    jj_consume_token(ARROW);
    body = SeqExp();
    jj_consume_token(END);
{if ("" != null) return new ASTFunDef(params, body);}
    throw new Error("Missing return statement in function");
}

  final public List<Pair<String, Type>> ParamList() throws ParseException {Token id;
  Type type;
  List<Pair<String, Type>> params = new ArrayList<>();
    id = jj_consume_token(Id);
    jj_consume_token(COLON);
    type = Type();
params.add(new Pair<>(id.image, type));
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      id = jj_consume_token(Id);
      jj_consume_token(COLON);
      type = Type();
params.add(new Pair<>(id.image, type));
    }
{if ("" != null) return params;}
    throw new Error("Missing return statement in function");
}

//meter parenteses quando o argumento é uma função
  final public Type Type() throws ParseException {Type t;
  List<Type> types = new ArrayList<>();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:{
      jj_consume_token(INT);
{if ("" != null) return IntType.getInstance();}
      break;
      }
    case DOUBLETYPE:{
      jj_consume_token(DOUBLETYPE);
{if ("" != null) return DoubleType.getInstance();}
      break;
      }
    case BOOL:{
      jj_consume_token(BOOL);
{if ("" != null) return BoolType.getInstance();}
      break;
      }
    case UNIT:{
      jj_consume_token(UNIT);
{if ("" != null) return UnitType.getInstance();}
      break;
      }
    case REF:{
      jj_consume_token(REF);
      t = Type();
{if ("" != null) return new RefType(t);}
      break;
      }
    case LPAR:{
      jj_consume_token(LPAR);
      t = Type();
types.add(t);
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[5] = jj_gen;
          break label_3;
        }
        jj_consume_token(COMMA);
        t = Type();
types.add(t);
      }
      jj_consume_token(ARROW);
      t = Type();
      jj_consume_token(RPAR);
{if ("" != null) return new FunType(types, t);}
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Exp Print() throws ParseException {Exp e;
    jj_consume_token(PRINT);
    e = Decl();
{if ("" != null) return new ASTPrint(e);}
    throw new Error("Missing return statement in function");
}

  final public Exp IfElse() throws ParseException {Exp e1, e2, e3 = null;
    jj_consume_token(IF);
    jj_consume_token(LPAR);
    e1 = BExp();
    jj_consume_token(RPAR);
    jj_consume_token(THEN);
    e2 = SeqExp();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ELSE:{
      jj_consume_token(ELSE);
      e3 = SeqExp();
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      ;
    }
    jj_consume_token(END);
{if ("" != null) return new ASTIf(e1, e2, e3);}
    throw new Error("Missing return statement in function");
}

  final public Exp WhileDo() throws ParseException {Exp e1, e2;
    jj_consume_token(WHILE);
    jj_consume_token(LPAR);
    e1 = BExp();
    jj_consume_token(RPAR);
    jj_consume_token(DO);
    e2 = SeqExp();
    jj_consume_token(END);
{if ("" != null) return new ASTWhile(e1, e2);}
    throw new Error("Missing return statement in function");
}

  final public ASTVarDecl LetAssignment() throws ParseException {Token x; Exp e;
    x = jj_consume_token(Id);
    jj_consume_token(EQ);
    e = BExp();
{if ("" != null) return new ASTVarDecl(x.image, e);}
    throw new Error("Missing return statement in function");
}

  final public Exp Assignment() throws ParseException {Exp e1, e2;
    e1 = BExp();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ASSIGN:{
      jj_consume_token(ASSIGN);
      e2 = BExp();
e1 = new ASTAssign(e1, e2);
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      ;
    }
{if ("" != null) return e1;}
    throw new Error("Missing return statement in function");
}

  final public Exp BExp() throws ParseException {Exp e1, e2;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case DOUBLE:
    case Num:
    case MINUS:
    case LPAR:
    case FALSE:
    case TRUE:
    case NEW:
    case DEREF:
    case Id:
    case NOT:{
      e1 = Cmp();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:
      case OR:
      case NOT:{
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case AND:{
          jj_consume_token(AND);
          e2 = BExp();
e1 = new ASTAnd(e1,e2);
          break;
          }
        case OR:{
          jj_consume_token(OR);
          e2 = BExp();
e1 = new ASTOr(e1,e2);
          break;
          }
        case NOT:{
          jj_consume_token(NOT);
          e2 = BExp();
e1 = new ASTNot(e2);
          break;
          }
        default:
          jj_la1[9] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        ;
      }
{if ("" != null) return e1;}
      break;
      }
    case FUN:{
      e1 = FunDef();
{if ("" != null) return e1;}
      break;
      }
    case ARRAY:{
      e1 = ArrayDec();
{if ("" != null) return e1;}
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Exp Cmp() throws ParseException {Exp e1, e2;
    e1 = Expr();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LESS:
      case GREATER:
      case LESSEQ:
      case GREATEREQ:
      case EQUALS:
      case NOTEQUALS:{
        ;
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQUALS:{
        jj_consume_token(EQUALS);
        e2 = Expr();
e1 = new ASTEquals(e1, e2);
        break;
        }
      case NOTEQUALS:{
        jj_consume_token(NOTEQUALS);
        e2 = Expr();
e1 = new ASTNotEquals(e1, e2);
        break;
        }
      case LESS:{
        jj_consume_token(LESS);
        e2 = Expr();
e1 = new ASTLess(e1, e2);
        break;
        }
      case GREATER:{
        jj_consume_token(GREATER);
        e2 = Expr();
e1 = new ASTGreater(e1, e2);
        break;
        }
      case LESSEQ:{
        jj_consume_token(LESSEQ);
        e2 = Expr();
e1 = new ASTLessEq(e1, e2);
        break;
        }
      case GREATEREQ:{
        jj_consume_token(GREATEREQ);
        e2 = Expr();
e1 = new ASTGreaterEq(e1, e2);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return e1;}
    throw new Error("Missing return statement in function");
}

  final public Exp Expr() throws ParseException {Exp e1, e2;
    e1 = Term();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        jj_consume_token(PLUS);
        e2 = Term();
e1 = new ASTAdd(e1,e2);
        break;
        }
      case MINUS:{
        jj_consume_token(MINUS);
        e2 = Term();
e1 = new ASTSub(e1,e2);
        break;
        }
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return e1;}
    throw new Error("Missing return statement in function");
}

  final public Exp Term() throws ParseException {Exp e1, e2;
    e1 = fcall();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:
      case DIV:{
        ;
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:{
        jj_consume_token(TIMES);
        e2 = fcall();
e1 = new ASTMult(e1,e2);
        break;
        }
      case DIV:{
        jj_consume_token(DIV);
        e2 = fcall();
e1 = new ASTDiv(e1,e2);
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return e1;}
    throw new Error("Missing return statement in function");
}

  final public Exp fcall() throws ParseException {Exp e;
    e = Fact();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LPAR:{
        ;
        break;
        }
      default:
        jj_la1[18] = jj_gen;
        break label_7;
      }
      e = gets(e);
    }
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
}

  final public Exp gets(Exp exp) throws ParseException {ASTFunCall e1; Exp e2;
    jj_consume_token(LPAR);
e1 = new ASTFunCall(exp);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case DOUBLE:
    case Num:
    case MINUS:
    case LPAR:
    case FALSE:
    case TRUE:
    case NEW:
    case DEREF:
    case FUN:
    case ARRAY:
    case Id:
    case NOT:{
      e2 = BExp();
e1.addArg(e2);
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[19] = jj_gen;
          break label_8;
        }
        jj_consume_token(COMMA);
        e2 = BExp();
e1.addArg(e2);
      }
      break;
      }
    default:
      jj_la1[20] = jj_gen;
      ;
    }
    jj_consume_token(RPAR);
{if ("" != null) return e1;}
    throw new Error("Missing return statement in function");
}

  final public Exp Fact() throws ParseException {Token x; Exp e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Num:{
      x = jj_consume_token(Num);
{if ("" != null) return new ASTInt(Integer.parseInt(x.image));}
      break;
      }
    case DOUBLE:{
      x = jj_consume_token(DOUBLE);
{if ("" != null) return new ASTDouble(Double.parseDouble(x.image));}
      break;
      }
    case MINUS:{
      jj_consume_token(MINUS);
      e = Fact();
{if ("" != null) return new ASTMult(new ASTInt(-1), e);}
      break;
      }
    case TRUE:{
      x = jj_consume_token(TRUE);
{if ("" != null) return new ASTBool(true);}
      break;
      }
    case FALSE:{
      x = jj_consume_token(FALSE);
{if ("" != null) return new ASTBool(false);}
      break;
      }
    case LPAR:{
      jj_consume_token(LPAR);
      e = Par();
{if ("" != null) return e;}
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
      e = Fact();
{if ("" != null) return new ASTNot(e);}
      break;
      }
    case Id:{
      x = jj_consume_token(Id);
{if ("" != null) return new ASTId(x.image);}
      break;
      }
    case NEW:{
      jj_consume_token(NEW);
      e = Fact();
{if ("" != null) return new ASTNew(e);}
      break;
      }
    case DEREF:{
      jj_consume_token(DEREF);
      e = Fact();
{if ("" != null) return new ASTDeref(e);}
      break;
      }
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  final public Exp Par() throws ParseException {Exp e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case RPAR:{
      jj_consume_token(RPAR);
{if ("" != null) return new ASTUnit();}
      break;
      }
    case DOUBLE:
    case Num:
    case MINUS:
    case LPAR:
    case FALSE:
    case TRUE:
    case LET:
    case NEW:
    case DEREF:
    case IF:
    case WHILE:
    case PRINT:
    case FUN:
    case ARRAY:
    case GARRAY:
    case SARRAY:
    case Id:
    case NOT:{
      e = Decl();
      jj_consume_token(RPAR);
{if ("" != null) return e;}
      break;
      }
    default:
      jj_la1[22] = jj_gen;
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
  final private int[] jj_la1 = new int[23];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x10000000,0x0,0x626b84b0,0x0,0x80000000,0x80000000,0x400,0x1000000,0x100000,0x0,0x0,0x402984b0,0x0,0x0,0xc0,0xc0,0x300,0x300,0x400,0x80000000,0x402984b0,0x2984b0,0x626b8cb0,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x400,0x241c,0xa0,0x0,0x0,0x3e0,0x0,0x0,0x3800,0x3800,0x2404,0x1bc000,0x1bc000,0x0,0x0,0x0,0x0,0x0,0x0,0x2404,0x2400,0x241c,};
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
	 for (int i = 0; i < 23; i++) jj_la1[i] = -1;
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
	 for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new ParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 23; i++) jj_la1[i] = -1;
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

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[53];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 23; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 53; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
