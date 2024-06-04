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
sipush 2
sipush 1
if_icmpgt L3
sipush 0
goto L4
L3:
sipush 1
L4:
ifeq L1
sipush 2
goto L2
L1:
sipush 1
L2:
return
.end method
