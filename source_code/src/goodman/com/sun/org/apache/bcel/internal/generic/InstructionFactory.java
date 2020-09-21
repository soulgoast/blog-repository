/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package goodman.com.sun.org.apache.bcel.internal.generic;

/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" and
 *    "Apache BCEL" must not be used to endorse or promote products
 *    derived from this software without prior written permission. For
 *    written permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    "Apache BCEL", nor may "Apache" appear in their name, without
 *    prior written permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.generic.*;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ArrayInstruction;
import com.sun.org.apache.bcel.internal.generic.ArrayType;
import com.sun.org.apache.bcel.internal.generic.BasicType;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ClassGen;
import com.sun.org.apache.bcel.internal.generic.ClassGenException;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.DLOAD;
import com.sun.org.apache.bcel.internal.generic.DSTORE;
import com.sun.org.apache.bcel.internal.generic.FLOAD;
import com.sun.org.apache.bcel.internal.generic.FSTORE;
import com.sun.org.apache.bcel.internal.generic.FieldInstruction;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.GOTO_W;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.IFGE;
import com.sun.org.apache.bcel.internal.generic.IFGT;
import com.sun.org.apache.bcel.internal.generic.IFLE;
import com.sun.org.apache.bcel.internal.generic.IFLT;
import com.sun.org.apache.bcel.internal.generic.IFNE;
import com.sun.org.apache.bcel.internal.generic.IFNONNULL;
import com.sun.org.apache.bcel.internal.generic.IFNULL;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPNE;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPEQ;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPGE;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPGT;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPLE;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPLT;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPNE;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.InvokeInstruction;
import com.sun.org.apache.bcel.internal.generic.JSR;
import com.sun.org.apache.bcel.internal.generic.JSR_W;
import com.sun.org.apache.bcel.internal.generic.LLOAD;
import com.sun.org.apache.bcel.internal.generic.LSTORE;
import com.sun.org.apache.bcel.internal.generic.MULTIANEWARRAY;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.org.apache.bcel.internal.generic.ObjectType;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.bcel.internal.generic.PUTSTATIC;
import com.sun.org.apache.bcel.internal.generic.ReferenceType;
import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.bcel.internal.generic.Type;

/**
 * Instances of this class may be used, e.g., to generate typed
 * versions of instructions. Its main purpose is to be used as the
 * byte code generating backend of a compiler. You can subclass it to
 * add your own create methods.
 *
 * @author <A HREF="mailto:markus.dahm@berlin.de">M. Dahm</A>
 * @see Constants
 */
public class InstructionFactory
  implements InstructionConstants, java.io.Serializable
{
  protected com.sun.org.apache.bcel.internal.generic.ClassGen cg;
  protected com.sun.org.apache.bcel.internal.generic.ConstantPoolGen cp;

  public InstructionFactory(com.sun.org.apache.bcel.internal.generic.ClassGen cg, com.sun.org.apache.bcel.internal.generic.ConstantPoolGen cp) {
    this.cg = cg;
    this.cp = cp;
  }

  /** Initialize with ClassGen object
   */
  public InstructionFactory(com.sun.org.apache.bcel.internal.generic.ClassGen cg) {
    this(cg, cg.getConstantPool());
  }

  /** Initialize just with ConstantPoolGen object
   */
  public InstructionFactory(com.sun.org.apache.bcel.internal.generic.ConstantPoolGen cp) {
    this(null, cp);
  }

  /** Create an invoke instruction.
   *
   * @param class_name name of the called class
   * @param name name of the called method
   * @param ret_type return type of method
   * @param arg_types argument types of method
   * @param kind how to invoke, i.e., INVOKEINTERFACE, INVOKESTATIC, INVOKEVIRTUAL,
   * or INVOKESPECIAL
   * @see Constants
   */
  public com.sun.org.apache.bcel.internal.generic.InvokeInstruction createInvoke(String class_name, String name, com.sun.org.apache.bcel.internal.generic.Type ret_type,
                                                                                 com.sun.org.apache.bcel.internal.generic.Type[] arg_types, short kind) {
    int    index;
    int    nargs      = 0;
    String signature  = com.sun.org.apache.bcel.internal.generic.Type.getMethodSignature(ret_type, arg_types);

    for(int i=0; i < arg_types.length; i++) // Count size of arguments
      nargs += arg_types[i].getSize();

    if(kind == Constants.INVOKEINTERFACE)
      index = cp.addInterfaceMethodref(class_name, name, signature);
    else
      index = cp.addMethodref(class_name, name, signature);

    switch(kind) {
    case Constants.INVOKESPECIAL:   return new INVOKESPECIAL(index);
    case Constants.INVOKEVIRTUAL:   return new com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL(index);
    case Constants.INVOKESTATIC:    return new INVOKESTATIC(index);
    case Constants.INVOKEINTERFACE: return new INVOKEINTERFACE(index, nargs + 1);
    default:
      throw new RuntimeException("Oops: Unknown invoke kind:" + kind);
    }
  }

  /** Create a call to the most popular System.out.println() method.
   *
   * @param s the string to print
   */
  public com.sun.org.apache.bcel.internal.generic.InstructionList createPrintln(String s) {
    com.sun.org.apache.bcel.internal.generic.InstructionList il      = new InstructionList();
    int             out     = cp.addFieldref("java.lang.System", "out",
                                             "Ljava/io/PrintStream;");
    int             println = cp.addMethodref("java.io.PrintStream", "println",
                                              "(Ljava/lang/String;)V");

    il.append(new com.sun.org.apache.bcel.internal.generic.GETSTATIC(out));
    il.append(new com.sun.org.apache.bcel.internal.generic.PUSH(cp, s));
    il.append(new INVOKEVIRTUAL(println));

    return il;
  }

  /** Uses PUSH to push a constant value onto the stack.
   * @param value must be of type Number, Boolean, Character or String
   */
  public com.sun.org.apache.bcel.internal.generic.Instruction createConstant(Object value) {
    com.sun.org.apache.bcel.internal.generic.PUSH push;

    if(value instanceof Number)
      push = new com.sun.org.apache.bcel.internal.generic.PUSH(cp, (Number)value);
    else if(value instanceof String)
      push = new com.sun.org.apache.bcel.internal.generic.PUSH(cp, (String)value);
    else if(value instanceof Boolean)
      push = new com.sun.org.apache.bcel.internal.generic.PUSH(cp, (Boolean)value);
    else if(value instanceof Character)
      push = new PUSH(cp, (Character)value);
    else
      throw new ClassGenException("Illegal type: " + value.getClass());

    return push.getInstruction();
  }

  private static class MethodObject {
    com.sun.org.apache.bcel.internal.generic.Type[]   arg_types;
    com.sun.org.apache.bcel.internal.generic.Type result_type;
    String[] arg_names;
    String   class_name;
    String   name;
    int      access;

    MethodObject(String c, String n, com.sun.org.apache.bcel.internal.generic.Type r, com.sun.org.apache.bcel.internal.generic.Type[] a, int acc) {
      class_name  = c;
      name        = n;
      result_type = r;
      arg_types   = a;
      access      = acc;
    }
  }

  private InvokeInstruction createInvoke(MethodObject m, short kind) {
    return createInvoke(m.class_name, m.name, m.result_type, m.arg_types, kind);
  }

  private static MethodObject[] append_mos = {
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER,
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.STRING }, Constants.ACC_PUBLIC),
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER,
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.OBJECT }, Constants.ACC_PUBLIC),
    null, null, // indices 2, 3
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER,
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.BOOLEAN }, Constants.ACC_PUBLIC),
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER,
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.CHAR }, Constants.ACC_PUBLIC),
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER,
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.FLOAT }, Constants.ACC_PUBLIC),
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER,
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.DOUBLE }, Constants.ACC_PUBLIC),
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER,
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.INT }, Constants.ACC_PUBLIC),
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER, // No append(byte)
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.INT }, Constants.ACC_PUBLIC),
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER, // No append(short)
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.INT }, Constants.ACC_PUBLIC),
    new MethodObject("java.lang.StringBuffer", "append", com.sun.org.apache.bcel.internal.generic.Type.STRINGBUFFER,
                     new com.sun.org.apache.bcel.internal.generic.Type[] { com.sun.org.apache.bcel.internal.generic.Type.LONG }, Constants.ACC_PUBLIC)
  };

  private static final boolean isString(com.sun.org.apache.bcel.internal.generic.Type type) {
    return ((type instanceof com.sun.org.apache.bcel.internal.generic.ObjectType) &&
            ((com.sun.org.apache.bcel.internal.generic.ObjectType)type).getClassName().equals("java.lang.String"));
  }

  public com.sun.org.apache.bcel.internal.generic.Instruction createAppend(com.sun.org.apache.bcel.internal.generic.Type type) {
    byte t = type.getType();

    if(isString(type))
      return createInvoke(append_mos[0], Constants.INVOKEVIRTUAL);

    switch(t) {
    case Constants.T_BOOLEAN:
    case Constants.T_CHAR:
    case Constants.T_FLOAT:
    case Constants.T_DOUBLE:
    case Constants.T_BYTE:
    case Constants.T_SHORT:
    case Constants.T_INT:
    case Constants.T_LONG
      :   return createInvoke(append_mos[t], Constants.INVOKEVIRTUAL);
    case Constants.T_ARRAY:
    case Constants.T_OBJECT:
      return createInvoke(append_mos[1], Constants.INVOKEVIRTUAL);
    default:
      throw new RuntimeException("Oops: No append for this type? " + type);
    }
  }

  /** Create a field instruction.
   *
   * @param class_name name of the accessed class
   * @param name name of the referenced field
   * @param type  type of field
   * @param kind how to access, i.e., GETFIELD, PUTFIELD, GETSTATIC, PUTSTATIC
   * @see Constants
   */
  public FieldInstruction createFieldAccess(String class_name, String name, com.sun.org.apache.bcel.internal.generic.Type type, short kind) {
    int    index;
    String signature  = type.getSignature();

    index = cp.addFieldref(class_name, name, signature);

    switch(kind) {
    case Constants.GETFIELD:  return new com.sun.org.apache.bcel.internal.generic.GETFIELD(index);
    case Constants.PUTFIELD:  return new com.sun.org.apache.bcel.internal.generic.PUTFIELD(index);
    case Constants.GETSTATIC: return new com.sun.org.apache.bcel.internal.generic.GETSTATIC(index);
    case Constants.PUTSTATIC: return new com.sun.org.apache.bcel.internal.generic.PUTSTATIC(index);

    default:
      throw new RuntimeException("Oops: Unknown getfield kind:" + kind);
    }
  }

  /** Create reference to `this'
   */
  public static com.sun.org.apache.bcel.internal.generic.Instruction createThis() {
    return new com.sun.org.apache.bcel.internal.generic.ALOAD(0);
  }

  /** Create typed return
   */
  public static ReturnInstruction createReturn(com.sun.org.apache.bcel.internal.generic.Type type) {
    switch(type.getType()) {
    case Constants.T_ARRAY:
    case Constants.T_OBJECT:  return ARETURN;
    case Constants.T_INT:
    case Constants.T_SHORT:
    case Constants.T_BOOLEAN:
    case Constants.T_CHAR:
    case Constants.T_BYTE:    return IRETURN;
    case Constants.T_FLOAT:   return FRETURN;
    case Constants.T_DOUBLE:  return DRETURN;
    case Constants.T_LONG:    return LRETURN;
    case Constants.T_VOID:    return RETURN;

    default:
      throw new RuntimeException("Invalid type: " + type);
    }
  }

  private static final ArithmeticInstruction createBinaryIntOp(char first, String op) {
    switch(first) {
    case '-' : return ISUB;
    case '+' : return IADD;
    case '%' : return IREM;
    case '*' : return IMUL;
    case '/' : return IDIV;
    case '&' : return IAND;
    case '|' : return IOR;
    case '^' : return IXOR;
    case '<' : return ISHL;
    case '>' : return op.equals(">>>")? (ArithmeticInstruction)IUSHR :
      (ArithmeticInstruction)ISHR;
    default: throw new RuntimeException("Invalid operand " + op);
    }
  }

  private static final ArithmeticInstruction createBinaryLongOp(char first, String op) {
    switch(first) {
    case '-' : return LSUB;
    case '+' : return LADD;
    case '%' : return LREM;
    case '*' : return LMUL;
    case '/' : return LDIV;
    case '&' : return LAND;
    case '|' : return LOR;
    case '^' : return LXOR;
    case '<' : return LSHL;
    case '>' : return op.equals(">>>")? (ArithmeticInstruction)LUSHR :
      (ArithmeticInstruction)LSHR;
    default: throw new RuntimeException("Invalid operand " + op);
    }
  }

  private static final ArithmeticInstruction createBinaryFloatOp(char op) {
    switch(op) {
    case '-' : return FSUB;
    case '+' : return FADD;
    case '*' : return FMUL;
    case '/' : return FDIV;
    default: throw new RuntimeException("Invalid operand " + op);
    }
  }

  private static final ArithmeticInstruction createBinaryDoubleOp(char op) {
    switch(op) {
    case '-' : return DSUB;
    case '+' : return DADD;
    case '*' : return DMUL;
    case '/' : return DDIV;
    default: throw new RuntimeException("Invalid operand " + op);
    }
  }

  /**
   * Create binary operation for simple basic types, such as int and float.
   *
   * @param op operation, such as "+", "*", "<<", etc.
   */
  public static ArithmeticInstruction createBinaryOperation(String op, com.sun.org.apache.bcel.internal.generic.Type type) {
    char first = op.toCharArray()[0];

    switch(type.getType()) {
    case Constants.T_BYTE:
    case Constants.T_SHORT:
    case Constants.T_INT:
    case Constants.T_CHAR:    return createBinaryIntOp(first, op);
    case Constants.T_LONG:    return createBinaryLongOp(first, op);
    case Constants.T_FLOAT:   return createBinaryFloatOp(first);
    case Constants.T_DOUBLE:  return createBinaryDoubleOp(first);
    default:        throw new RuntimeException("Invalid type " + type);
    }
  }

  /**
   * @param size size of operand, either 1 (int, e.g.) or 2 (double)
   */
  public static com.sun.org.apache.bcel.internal.generic.StackInstruction createPop(int size) {
    return (size == 2)? (com.sun.org.apache.bcel.internal.generic.StackInstruction)POP2 :
      (com.sun.org.apache.bcel.internal.generic.StackInstruction)POP;
  }

  /**
   * @param size size of operand, either 1 (int, e.g.) or 2 (double)
   */
  public static com.sun.org.apache.bcel.internal.generic.StackInstruction createDup(int size) {
    return (size == 2)? (com.sun.org.apache.bcel.internal.generic.StackInstruction)DUP2 :
      (com.sun.org.apache.bcel.internal.generic.StackInstruction)DUP;
  }

  /**
   * @param size size of operand, either 1 (int, e.g.) or 2 (double)
   */
  public static com.sun.org.apache.bcel.internal.generic.StackInstruction createDup_2(int size) {
    return (size == 2)? (com.sun.org.apache.bcel.internal.generic.StackInstruction)DUP2_X2 :
      (com.sun.org.apache.bcel.internal.generic.StackInstruction)DUP_X2;
  }

  /**
   * @param size size of operand, either 1 (int, e.g.) or 2 (double)
   */
  public static com.sun.org.apache.bcel.internal.generic.StackInstruction createDup_1(int size) {
    return (size == 2)? (com.sun.org.apache.bcel.internal.generic.StackInstruction)DUP2_X1 :
      (StackInstruction)DUP_X1;
  }

  /**
   * @param index index of local variable
   */
  public static LocalVariableInstruction createStore(com.sun.org.apache.bcel.internal.generic.Type type, int index) {
    switch(type.getType()) {
    case Constants.T_BOOLEAN:
    case Constants.T_CHAR:
    case Constants.T_BYTE:
    case Constants.T_SHORT:
    case Constants.T_INT:    return new ISTORE(index);
    case Constants.T_FLOAT:  return new FSTORE(index);
    case Constants.T_DOUBLE: return new DSTORE(index);
    case Constants.T_LONG:   return new LSTORE(index);
    case Constants.T_ARRAY:
    case Constants.T_OBJECT: return new ASTORE(index);
    default:       throw new RuntimeException("Invalid type " + type);
    }
  }

  /**
   * @param index index of local variable
   */
  public static LocalVariableInstruction createLoad(com.sun.org.apache.bcel.internal.generic.Type type, int index) {
    switch(type.getType()) {
    case Constants.T_BOOLEAN:
    case Constants.T_CHAR:
    case Constants.T_BYTE:
    case Constants.T_SHORT:
    case Constants.T_INT:    return new ILOAD(index);
    case Constants.T_FLOAT:  return new FLOAD(index);
    case Constants.T_DOUBLE: return new DLOAD(index);
    case Constants.T_LONG:   return new LLOAD(index);
    case Constants.T_ARRAY:
    case Constants.T_OBJECT: return new ALOAD(index);
    default:       throw new RuntimeException("Invalid type " + type);
    }
  }

  /**
   * @param type type of elements of array, i.e., array.getElementType()
   */
  public static com.sun.org.apache.bcel.internal.generic.ArrayInstruction createArrayLoad(com.sun.org.apache.bcel.internal.generic.Type type) {
    switch(type.getType()) {
    case Constants.T_BOOLEAN:
    case Constants.T_BYTE:   return BALOAD;
    case Constants.T_CHAR:   return CALOAD;
    case Constants.T_SHORT:  return SALOAD;
    case Constants.T_INT:    return IALOAD;
    case Constants.T_FLOAT:  return FALOAD;
    case Constants.T_DOUBLE: return DALOAD;
    case Constants.T_LONG:   return LALOAD;
    case Constants.T_ARRAY:
    case Constants.T_OBJECT: return AALOAD;
    default:       throw new RuntimeException("Invalid type " + type);
    }
  }

  /**
   * @param type type of elements of array, i.e., array.getElementType()
   */
  public static ArrayInstruction createArrayStore(com.sun.org.apache.bcel.internal.generic.Type type) {
    switch(type.getType()) {
    case Constants.T_BOOLEAN:
    case Constants.T_BYTE:   return BASTORE;
    case Constants.T_CHAR:   return CASTORE;
    case Constants.T_SHORT:  return SASTORE;
    case Constants.T_INT:    return IASTORE;
    case Constants.T_FLOAT:  return FASTORE;
    case Constants.T_DOUBLE: return DASTORE;
    case Constants.T_LONG:   return LASTORE;
    case Constants.T_ARRAY:
    case Constants.T_OBJECT: return AASTORE;
    default:       throw new RuntimeException("Invalid type " + type);
    }
  }


  /** Create conversion operation for two stack operands, this may be an I2C, instruction, e.g.,
   * if the operands are basic types and CHECKCAST if they are reference types.
   */
  public com.sun.org.apache.bcel.internal.generic.Instruction createCast(com.sun.org.apache.bcel.internal.generic.Type src_type, com.sun.org.apache.bcel.internal.generic.Type dest_type) {
    if((src_type instanceof com.sun.org.apache.bcel.internal.generic.BasicType) && (dest_type instanceof com.sun.org.apache.bcel.internal.generic.BasicType)) {
      byte dest = dest_type.getType();
      byte src  = src_type.getType();

      if(dest == Constants.T_LONG && (src == Constants.T_CHAR || src == Constants.T_BYTE ||
                                      src == Constants.T_SHORT))
        src = Constants.T_INT;

      String[] short_names = { "C", "F", "D", "B", "S", "I", "L" };

      String name = "com.sun.org.apache.bcel.internal.generic." + short_names[src - Constants.T_CHAR] +
        "2" + short_names[dest - Constants.T_CHAR];

      com.sun.org.apache.bcel.internal.generic.Instruction i = null;
      try {
        i = (com.sun.org.apache.bcel.internal.generic.Instruction) Class.forName(name).newInstance();
      } catch(Exception e) {
        throw new RuntimeException("Could not find instruction: " + name);
      }

      return i;
    } else if((src_type instanceof com.sun.org.apache.bcel.internal.generic.ReferenceType) && (dest_type instanceof com.sun.org.apache.bcel.internal.generic.ReferenceType)) {
      if(dest_type instanceof com.sun.org.apache.bcel.internal.generic.ArrayType)
        return new com.sun.org.apache.bcel.internal.generic.CHECKCAST(cp.addArrayClass((com.sun.org.apache.bcel.internal.generic.ArrayType)dest_type));
      else
        return new com.sun.org.apache.bcel.internal.generic.CHECKCAST(cp.addClass(((com.sun.org.apache.bcel.internal.generic.ObjectType)dest_type).getClassName()));
    }
    else
      throw new RuntimeException("Can not cast " + src_type + " to " + dest_type);
  }

  public com.sun.org.apache.bcel.internal.generic.GETFIELD createGetField(String class_name, String name, com.sun.org.apache.bcel.internal.generic.Type t) {
    return new GETFIELD(cp.addFieldref(class_name, name, t.getSignature()));
  }

  public com.sun.org.apache.bcel.internal.generic.GETSTATIC createGetStatic(String class_name, String name, com.sun.org.apache.bcel.internal.generic.Type t) {
    return new GETSTATIC(cp.addFieldref(class_name, name, t.getSignature()));
  }

  public com.sun.org.apache.bcel.internal.generic.PUTFIELD createPutField(String class_name, String name, com.sun.org.apache.bcel.internal.generic.Type t) {
    return new PUTFIELD(cp.addFieldref(class_name, name, t.getSignature()));
  }

  public com.sun.org.apache.bcel.internal.generic.PUTSTATIC createPutStatic(String class_name, String name, com.sun.org.apache.bcel.internal.generic.Type t) {
    return new PUTSTATIC(cp.addFieldref(class_name, name, t.getSignature()));
  }

  public com.sun.org.apache.bcel.internal.generic.CHECKCAST createCheckCast(com.sun.org.apache.bcel.internal.generic.ReferenceType t) {
    if(t instanceof com.sun.org.apache.bcel.internal.generic.ArrayType)
      return new com.sun.org.apache.bcel.internal.generic.CHECKCAST(cp.addArrayClass((com.sun.org.apache.bcel.internal.generic.ArrayType)t));
    else
      return new CHECKCAST(cp.addClass((com.sun.org.apache.bcel.internal.generic.ObjectType)t));
  }

  public com.sun.org.apache.bcel.internal.generic.INSTANCEOF createInstanceOf(ReferenceType t) {
    if(t instanceof com.sun.org.apache.bcel.internal.generic.ArrayType)
      return new com.sun.org.apache.bcel.internal.generic.INSTANCEOF(cp.addArrayClass((com.sun.org.apache.bcel.internal.generic.ArrayType)t));
    else
      return new INSTANCEOF(cp.addClass((com.sun.org.apache.bcel.internal.generic.ObjectType)t));
  }

  public com.sun.org.apache.bcel.internal.generic.NEW createNew(com.sun.org.apache.bcel.internal.generic.ObjectType t) {
    return new com.sun.org.apache.bcel.internal.generic.NEW(cp.addClass(t));
  }

  public NEW createNew(String s) {
    return createNew(new com.sun.org.apache.bcel.internal.generic.ObjectType(s));
  }

  /** Create new array of given size and type.
   * @return an instruction that creates the corresponding array at runtime, i.e. is an AllocationInstruction
   */
  public com.sun.org.apache.bcel.internal.generic.Instruction createNewArray(com.sun.org.apache.bcel.internal.generic.Type t, short dim) {
    if(dim == 1) {
      if(t instanceof com.sun.org.apache.bcel.internal.generic.ObjectType)
        return new com.sun.org.apache.bcel.internal.generic.ANEWARRAY(cp.addClass((ObjectType)t));
      else if(t instanceof com.sun.org.apache.bcel.internal.generic.ArrayType)
        return new ANEWARRAY(cp.addArrayClass((com.sun.org.apache.bcel.internal.generic.ArrayType)t));
      else
        return new NEWARRAY(((BasicType)t).getType());
    } else {
      com.sun.org.apache.bcel.internal.generic.ArrayType at;

      if(t instanceof com.sun.org.apache.bcel.internal.generic.ArrayType)
        at = (com.sun.org.apache.bcel.internal.generic.ArrayType)t;
      else
        at = new ArrayType(t, dim);

      return new MULTIANEWARRAY(cp.addArrayClass(at), dim);
    }
  }

  /** Create "null" value for reference types, 0 for basic types like int
   */
  public static Instruction createNull(Type type) {
    switch(type.getType()) {
    case Constants.T_ARRAY:
    case Constants.T_OBJECT:  return ACONST_NULL;
    case Constants.T_INT:
    case Constants.T_SHORT:
    case Constants.T_BOOLEAN:
    case Constants.T_CHAR:
    case Constants.T_BYTE:    return ICONST_0;
    case Constants.T_FLOAT:   return FCONST_0;
    case Constants.T_DOUBLE:  return DCONST_0;
    case Constants.T_LONG:    return LCONST_0;
    case Constants.T_VOID:    return NOP;

    default:
      throw new RuntimeException("Invalid type: " + type);
    }
  }

  /** Create branch instruction by given opcode, except LOOKUPSWITCH and TABLESWITCH.
   * For those you should use the SWITCH compound instruction.
   */
  public static BranchInstruction createBranchInstruction(short opcode, InstructionHandle target) {
    switch(opcode) {
    case Constants.IFEQ:      return new IFEQ(target);
    case Constants.IFNE:      return new IFNE(target);
    case Constants.IFLT:      return new IFLT(target);
    case Constants.IFGE:      return new IFGE(target);
    case Constants.IFGT:      return new IFGT(target);
    case Constants.IFLE:      return new IFLE(target);
    case Constants.IF_ICMPEQ: return new IF_ICMPEQ(target);
    case Constants.IF_ICMPNE: return new IF_ICMPNE(target);
    case Constants.IF_ICMPLT: return new IF_ICMPLT(target);
    case Constants.IF_ICMPGE: return new IF_ICMPGE(target);
    case Constants.IF_ICMPGT: return new IF_ICMPGT(target);
    case Constants.IF_ICMPLE: return new IF_ICMPLE(target);
    case Constants.IF_ACMPEQ: return new IF_ACMPEQ(target);
    case Constants.IF_ACMPNE: return new IF_ACMPNE(target);
    case Constants.GOTO:      return new GOTO(target);
    case Constants.JSR:       return new JSR(target);
    case Constants.IFNULL:    return new IFNULL(target);
    case Constants.IFNONNULL: return new IFNONNULL(target);
    case Constants.GOTO_W:    return new GOTO_W(target);
    case Constants.JSR_W:     return new JSR_W(target);
    default:
        throw new RuntimeException("Invalid opcode: " + opcode);
    }
  }

  public void            setClassGen(com.sun.org.apache.bcel.internal.generic.ClassGen c)            { cg = c; }
  public ClassGen getClassGen()                      { return cg; }
  public void            setConstantPool(com.sun.org.apache.bcel.internal.generic.ConstantPoolGen c) { cp = c; }
  public ConstantPoolGen getConstantPool()                  { return cp; }
}
