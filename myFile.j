.class public Demo
.super java/lang/Object
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
.method public static main([Ljava/lang/String;)V
 .limit locals 10
 .limit stack 256
L0:

new frame_0
dup
invokespecial frame_0/<init>()V
dup
aload 0
putfield frame_0/sl Ljava/lang/Object;
astore 0
aload 0
new closure_0
dup
invokespecial closure_0/<init>()V
dup
aload 0
putfield closure_0/sl Lframe_0;
putfield frame_0/loc_0 Lfun_int_bool_int;
aload 0
getfield frame_0/loc_0 Lfun_int_bool_int;
checkcast fun_int_bool_int
sipush 5
sipush 1
invokeinterface fun_int_bool_int/apply(IZ)I 3
aload 0
getfield frame_0/sl Ljava/lang/Object;
astore 0
return
.end method
