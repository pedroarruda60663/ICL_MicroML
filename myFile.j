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
L12:
sipush 1
ifeq L14
aconst_null
pop
sipush 2
sipush 2
iadd
pop
L14:
return
.end method
